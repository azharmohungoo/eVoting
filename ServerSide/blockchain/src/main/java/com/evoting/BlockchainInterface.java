package com.evoting;

import org.json.JSONObject;

/**
 * Created by Andreas on 2016/09/04.
 */
interface BlockchainInterface {

    public void setNodeIP(String nodeIP);

    public void setNodePort(String nodePort);

    public void setRPCUsername(String RPCUsername);

    public void setRPCPassword(String RPCPassword);

    public JSONObject getBalance();

    public JSONObject sendVoteToNode(String address, double ammount);
}
