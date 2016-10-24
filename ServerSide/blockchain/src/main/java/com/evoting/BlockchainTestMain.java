package com.evoting;


public class BlockchainTestMain {

    public static void main(String[] args){

        Blockchain blockchain = new Blockchain("196.248.196.124","7419", "multichainrpc","51i1XY2ELS96V7xGEA3cGh5iy8KDTxpo2ckaXZ7CBM43");

        System.out.println("Current balance: " + blockchain.getBalance());
    }

}
