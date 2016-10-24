package com.evoting;

import org.json.JSONObject;


interface BlockchainInterface {

    public void setNodeIP(String nodeIP);

    public void setNodePort(String nodePort);

    public void setRPCUsername(String RPCUsername);

    public void setRPCPassword(String RPCPassword);

    public JSONObject getBalance();

    public int getPartyBalance();

    public JSONObject sendVoteToNode(String address, double ammount);
}
