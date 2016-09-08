package com.evoting;

/**
 * Created by Andreas on 2016/08/20.
 */
public class BlockchainTestMain {

    public static void main(String[] args){

        Blockchain blockchain = new Blockchain("196.248.196.124","7419", "multichainrpc","51i1XY2ELS96V7xGEA3cGh5iy8KDTxpo2ckaXZ7CBM43");

        System.out.println("Current balance: " + blockchain.getBalance());
       // System.out.println("Sending 1 coin to: 1DRUkW7U98SpNtLg1nh3GshzgwMecb4XfgFfZP" + " Result: " + blockchain.sendVoteToNode("15DmYUc17VEx7zvJoAxcPu1fBAREGYVj4ScVwe",1000));

    }

}
