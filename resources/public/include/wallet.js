const nearAPI = require('near-api-js');
const { socket } = require('./socket');
const { ls } = require('./storage');

module.exports.wallet = (function() {
  const self = {
    elems: {
      connectWalletButton: null
    },
    walletConnection: null,
    contract: null,

    getWallet: function () {
      return self.walletConnection
    },

    init() {
      ls.set('auth_same_window', true);

      self.elems.connectWalletButton = document.querySelector('#connect_wallet');


      socket.on('nearConfig', function (nearConfig) {

        console.log("Got config: ", nearConfig);

        // create a keyStore for signing transactions using the user's key
        // which is located in the browser local storage after user logs in
        const keyStore = new nearAPI.keyStores.BrowserLocalStorageKeyStore();

        // Initializing connection to the NEAR testnet
        nearAPI.connect({ keyStore, ...{
            networkId: nearConfig.networkId,
            nodeUrl: nearConfig.nodeUrl,
            contractName: nearConfig.contractName,
            walletUrl: nearConfig.walletUrl,
            helperUrl: nearConfig.helperUrl
          } }).then( function(near) {
          // Initialize wallet connection
          const walletConnection = new nearAPI.WalletConnection(near);
          console.log(walletConnection.isSignedIn());
          self.walletConnection = walletConnection;

          if(!self.walletConnection.isSignedIn()) {
            $('#connect_wallet').fadeIn(200);
            $('#wallet_account').fadeOut(200);
            console.log(localStorage.getItem('near_account'));
          }
          $('#signup-username-input').val(localStorage.getItem('near_account'));


          self.contract = new nearAPI.Contract(
              // User's accountId as a string
              self.walletConnection.account(),
              // accountId of the contract we will be loading
              // NOTE: All contracts on NEAR are deployed to an account and
              // accounts can only have one contract deployed to them.
              nearConfig.contractName,
              {
                // View methods are read-only – they don't modify the state, but usually return some value
                viewMethods: ['get_pixel'],
                // Change methods can modify the state, but you don't receive the returned value when called
                changeMethods: ['put_pixel'],
                // Sender is the account ID to initialize transactions.
                // getAccountId() will return empty string if user is still unauthorized
                sender: self.walletConnection.getAccountId(),
              });

          self.elems.connectWalletButton.addEventListener('click', function(e) {
            self.walletConnection.requestSignIn({
              contractId: nearConfig.contractName, // optional, contract requesting access
              methodNames: ['put_pixel'], // optional
              successUrl: window.location.origin + '/auth/near?json=1&state=|redirect', // optional
              failureUrl: window.location.origin + '/auth/near-failed?json=1' // optional
            });
          });
        });
      });

      socket.on('ACK', function (data) {
        console.log("Invoking the smart contract...");
        self.contract.put_pixel({"x": data.x, "y": data.y, "color" : data.color});
      });
    }
  };
  return {
    init: self.init,
    getWallet: self.getWallet
  };
})();
