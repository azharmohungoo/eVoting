package com.evoting;

import org.json.JSONObject;

/**
 * Created by Andreas on 2016/09/04.
 */

//This is a Mock object of the Blockchain module.
//It will always return successfull cases of all method calls.

//At each method call, comments will be there for the expected successfull returns.
public class BlockchainMock implements BlockchainInterface {

    private String NodeIP;
    private String NodePort;
    private String RPCUsername;
    private String RPCPassword;

    public BlockchainMock() {
        NodeIP = "";
        NodePort = "";
        RPCUsername = "";
        RPCPassword = "";

        System.out.println("Blockchain: Using mock version of Blockchain module!");
    }

    public BlockchainMock(String nodeIP, String nodePort, String username, String password){
        NodeIP = nodeIP;
        NodePort = nodePort;
        RPCUsername = username;
        RPCPassword = password;

        System.out.println("Blockchain: Using mock version of Blockchain module!");
    }

    //Successfull return: void
    public void setNodeIP(String nodeIP) {
        NodeIP = nodeIP;
    }

    //Successfull return: void
    public void setNodePort(String nodePort) {
        NodePort = nodePort;
    }

    //Successfull return: void
    public void setRPCUsername(String RPCUsername) {
        this.RPCUsername = RPCUsername;
    }

    //Successfull return: void
    public void setRPCPassword(String RPCPassword) {
        this.RPCPassword = RPCPassword;
    }


    //Successfull return: JSONObject = "{"success":"true","response":1000}"
    public JSONObject getBalance(){
        JSONObject result = new JSONObject();
        result.put("success", "true");
        result.put("response", 1000);

        return result;
    };

    //Successfull return: JSONObject = "{"result":"46069f50da316908b535afc308f9d65dd01ba00e6bc25bed41cba132dfa41aa7","id":null,"error":null}"
    public JSONObject sendVoteToNode(String address, double ammount){
        JSONObject result = new JSONObject();
        result.put("success", "true");
        result.put("response", "46069f50da316908b535afc308f9d65dd01ba00e6bc25bed41cba132dfa41aa7");

        return result;
    };

    public int getPartyBalance(){return 1;}
}
