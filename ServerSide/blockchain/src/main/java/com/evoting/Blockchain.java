package com.evoting;


import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.*;
import java.io.*;

import java.net.*;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.*;
import org.json.*;

//import org.apache.logging.log4j.Logger;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.*;

import org.apache.log4j.Logger;


/**
 * Created by Andreas on 2016/08/20.
 */
public class Blockchain {

    private String NodeIP;
    private String NodePort;
    private String RPCUsername;
    private String RPCPassword;


    public Blockchain() {
        NodeIP = "";
        NodePort = "";
        RPCUsername = "";
        RPCPassword = "";
    }

    public Blockchain(String nodeIP, String nodePort, String username, String password){
        NodeIP = nodeIP;
        NodePort = nodePort;
        RPCUsername = username;
        RPCPassword = password;

        Logger.getLogger("org.apache.http").setLevel(org.apache.log4j.Level.OFF);
        //System.setProperty("log4j.logger.org.apache.http", "ERROR");
        //Logger.getLogger("log4j.logger.org.apache.http").setLevel(Level.ERROR);



    }

    public void setNodeIP(String nodeIP) {
        NodeIP = nodeIP;
    }

    public void setNodePort(String nodePort) {
        NodePort = nodePort;
    }

    public void setRPCUsername(String RPCUsername) {
        this.RPCUsername = RPCUsername;
    }

    public void setRPCPassword(String RPCPassword) {
        this.RPCPassword = RPCPassword;
    }

    public JSONObject getBalance(){

        JSONObject result = new JSONObject();

        if (NodeIP == null || NodeIP == "" || NodePort == null || NodePort == "" || RPCUsername == null || RPCUsername == "" || RPCPassword == null || RPCPassword == ""){

            result.put("success","false");
            result.put("response", BlockchainErrorMessages.EmptyNodeRPCConfiguration.toString());
            return result;
        }

        if (pingNode()){ //Test if code can reach server.
            if (pingNodeWithPort()){ //Test if IP and Port is correct.

                JSONObject body = new JSONObject();
                body.put("method", "getinfo");

                JSONObject returnedFromNode = getNodeResponse(body);
                //example return object: {"result":{"protocolversion":10005,"relayfee":0,"timeoffset":0,"blocks":110,"description":"MultiChain blockchain10","burnaddress":"1XXXXXXXJ4XXXXXXrfXXXXXXKfXXXXXXZvV7w9","version":"1.0 alpha 21","keypoolsize":2,"paytxfee":0,"chainname":"blockchain15","difficulty":1.526E-5,"proxy":"","protocol":"multichain","incomingpaused":false,"walletversion":60000,"nodeaddress":"blockchain15@192.168.8.178:6287","balance":1,"keypoololdest":1472842598,"port":6287,"testnet":false,"miningpaused":false,"connections":0,"errors":"","setupblocks":60},"id":null,"error":null}{"result":{"protocolversion":10005,"relayfee":0,"timeoffset":0,"blocks":110,"description":"MultiChain blockchain10","burnaddress":"1XXXXXXXJ4XXXXXXrfXXXXXXKfXXXXXXZvV7w9","version":"1.0 alpha 21","keypoolsize":2,"paytxfee":0,"chainname":"blockchain15","difficulty":1.526E-5,"proxy":"","protocol":"multichain","incomingpaused":false,"walletversion":60000,"nodeaddress":"blockchain15@192.168.8.178:6287","balance":1,"keypoololdest":1472842598,"port":6287,"testnet":false,"miningpaused":false,"connections":0,"errors":"","setupblocks":60},"id":null,"error":null}

                result.put("success","true");
                result.put("response",returnedFromNode.getJSONObject("result").get("balance").toString());
            }
            else {
                result.put("success","false");
                result.put("response", BlockchainErrorMessages.InvalidPort.toString());
            }
        }
        else{
            result.put("success","false");
            result.put("response", BlockchainErrorMessages.InvalidIP.toString());
        }
        return result;
    }

    public JSONObject sendVoteToNode(String address, double ammount){
        JSONObject result = new JSONObject();

        if (NodeIP == null || NodeIP == "" || NodePort == null || NodePort == "" || RPCUsername == null || RPCUsername == "" || RPCPassword == null || RPCPassword == ""){

            result.put("success","false");
            result.put("response", BlockchainErrorMessages.EmptyNodeRPCConfiguration.toString());
            return result;
        }

        if (pingNode()){ //Test if code can reach server.
            if (pingNodeWithPort()){ //Test if IP and Port is correct.

                JSONObject body = new JSONObject();
                JSONArray array = new JSONArray();
                array.put(address);
                array.put(ammount);

                body.put("method","sendtoaddress");
                body.put("params", array);

                JSONObject returnedFromNode = getNodeResponse(body);
                //example error: Invalid address: "{"result":null,"error":{"code":-5,"message":"Invalid address"},"id":null}[\n]"
                //example error: Insufficient funds: "{"result":null,"error":{"code":-6,"message":"Insufficient funds"},"id":null}[\n]"

                if (returnedFromNode.get("result").toString() == "null") {

                    result.put("success","false");

                    switch (returnedFromNode.getJSONObject("error").get("message").toString())
                    {
                        case "Invalid address":
                            result.put("response", BlockchainErrorMessages.InvalidToAddress.toString());
                            break;

                        case "Insufficient funds":
                            result.put("response", BlockchainErrorMessages.InvalidVotesLeft.toString());
                            break;

                        default:
                            result.put("response", BlockchainErrorMessages.Unknown.toString());
                            break;
                    }


                }
                else {
                    result.put("success", "true");
                    result.put("response", returnedFromNode.getJSONObject("result").get("balance").toString());
                }
            }
            else {
                result.put("success","false");
                result.put("response", BlockchainErrorMessages.InvalidPort.toString());
            }
        }
        else{
            result.put("success","false");
            result.put("response", BlockchainErrorMessages.InvalidIP.toString());
        }
        return result;
    }


    private JSONObject getNodeResponse(JSONObject body){
        JSONObject result = new JSONObject();

        try {
            CloseableHttpClient client = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost("http://"+NodeIP+":"+NodePort+"/");
            httpPost.addHeader("authorization", "Basic " + new String(Base64.encodeBase64((RPCUsername+":"+RPCPassword).getBytes("UTF-8"))));

            StringEntity params = new StringEntity(body.toString());

            httpPost.setEntity(params);
            HttpResponse response = client.execute(httpPost);

            InputStream ips  = response.getEntity().getContent();
            BufferedReader buf = new BufferedReader(new InputStreamReader(ips,"UTF-8"));

            if(response.getStatusLine().getStatusCode() == HttpStatus.SC_UNAUTHORIZED) {
                JSONObject errorMesg = new JSONObject();
                errorMesg.put("message", BlockchainErrorMessages.InvalidRPCCredentials.toString());
                result.put("result","null");
                result.put("error",errorMesg);
                return result;
            }

                try{
                    StringBuilder sb = new StringBuilder();
                    String s;
                    while(true )
                    {
                        s = buf.readLine();
                        if(s==null || s.length()==0)
                            break;
                        sb.append(s);

                    }
                    buf.close();
                    ips.close();

                    return new JSONObject(sb.toString());
                }
                catch(Exception e){
                    return null;
                }
        }
        catch(UnsupportedEncodingException e){
            System.out.println("UnsupportedEncodingException thrown: " + e.getMessage());
            return null;
        }
        catch (IOException e){
            System.out.println("IOException was thrown: " + e.getMessage());
            return null;
        }
    }


    //This function is to test if the specified IP Address responds to requests.
    public boolean pingNode(){

        try {
            InetAddress byName = InetAddress.getByName(NodeIP);
            return byName.isReachable(1000);
        }
        catch(UnknownHostException e){
            return false;
        }
        catch (IOException e) {
            return false;
        }
    }

    //This function is to test if the specified IP Address and port responds to requests.
    public boolean pingNodeWithPort(){
            try (Socket socket = new Socket()) {
                socket.connect(new InetSocketAddress(NodeIP, Integer.parseInt(NodePort)), 3000);
                return true;
            } catch (IOException e) {
                return false;
            }
    }
}
