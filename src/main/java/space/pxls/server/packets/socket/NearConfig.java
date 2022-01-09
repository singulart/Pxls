package space.pxls.server.packets.socket;

public class NearConfig {
    private final String type = "nearConfig";

    private final String networkId = System.getProperty("near.network", "testnet");
    private final String contractName = System.getProperty("near.contract.name");
    private final String nodeUrl = System.getProperty("near.node.url");
    private final String walletUrl = System.getProperty("near.wallet.url");
    private final String helperUrl = System.getProperty("near.helper.url");

}
