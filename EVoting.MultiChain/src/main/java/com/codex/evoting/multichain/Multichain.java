package com.codex.evoting.multichain;

/**
 * Created by Andreas on 2016/07/17.
 */
public class Multichain {

    public Multichain(){

    }

    public boolean sendVote(String votingNode, String partyNode, String voteType){

        if (!votingNode.equals("") && !partyNode.equals("") && !voteType.equals("")) {
            return true;
        }

        return false;
    }
}
