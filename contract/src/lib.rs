use near_sdk::borsh::{self, BorshDeserialize, BorshSerialize};
use near_sdk::{env, near_bindgen, PanicOnDefault};


#[global_allocator]
static ALLOC: wee_alloc::WeeAlloc = wee_alloc::WeeAlloc::INIT;

const BOARD_SIDE: usize = 1000;
// const SHARD_SIDE: usize = 250;
const BOARD_DIMENSIONS: usize = BOARD_SIDE * BOARD_SIDE;
// const SHARD_DIMENSIONS: usize = SHARD_SIDE * SHARD_SIDE;


#[near_bindgen]
#[derive(BorshDeserialize, BorshSerialize, PanicOnDefault)]
pub struct NearCanvas {
    board: Vec<u8> // this design deploys fine, but produces "Exceeded the prepaid gas" when put_pixel called.
}

#[near_bindgen]
impl NearCanvas {

    #[init]
    pub fn new() -> Self {
        Self {
            board: vec![0; BOARD_DIMENSIONS]
        }

    }

    pub fn put_pixel(&mut self, x: usize, y: usize, color: u8) {
        // let applicant = env::signer_account_id();
        env::log(b"put_pixel!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        self.board[x + y * BOARD_SIDE] = color
    }

}