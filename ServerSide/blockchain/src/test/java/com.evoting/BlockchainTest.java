package com.evoting;

/**
 * Created by Andreas on 2016/09/02.
 */

import junit.framework.TestCase;
import org.json.JSONObject;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

public class BlockchainTest extends TestCase {

    //Test if Node configuration was not entered. I.E. empty object.
    @Test
    public void testEmptyParameters() {
        Blockchain tester = new Blockchain();

        JSONObject testObject = new JSONObject();
        testObject.put("success","false");
        testObject.put("response", BlockchainErrorMessages.EmptyNodeRPCConfiguration.toString());

        // assert statements
        JSONAssert.assertEquals(testObject.toString(), tester.getBalance().toString(), true);
    }

    //Test if incorrect IP address was entered.
    @Test
    public void testInvalidIPAddress() {
        Blockchain tester = new Blockchain("192.168.1.254","7419", "multichainrpc","Gnb5RsXa783K9LbJGjtfZNpnJg8UbDu8bza8htd9DMPX");

        JSONObject testObject = new JSONObject();
        testObject.put("success","false");
        testObject.put("response", BlockchainErrorMessages.InvalidIP.toString());

        // assert statements
        JSONAssert.assertEquals(testObject.toString(), tester.getBalance().toString(), true);
    }
/*
    //InvalidRPCCredentials
    @Test
    public void testInvalidIPAddress() {
        BlockchainMock tester = new BlockchainMock("192.168.1.254","7419", "multichainrpc","Gnb5RsXa783K9LbJGjtfZNpnJg8UbDu8bza8htd9DMPX");

        JSONObject testObject = new JSONObject();
        testObject.put("success","false");
        testObject.put("response", BlockchainErrorMessages.InvalidIP.toString());

        // assert statements
        JSONAssert.assertEquals(testObject.toString(), tester.getBalance().toString(), true);
    }
*/
    //Test if Admin returns a balance.
    @Test
    public void testAdminBalance() {
        Blockchain tester = new Blockchain("192.168.1.20","7419", "multichainrpc","Gnb5RsXa783K9LbJGjtfZNpnJg8UbDu8bza8htd9DMPX");

        JSONObject testObject = new JSONObject();
        testObject.put("success","true");
        //testObject.put("response", BlockchainErrorMessages.InvalidIP.toString());

        // assert statements
        assertEquals(testObject.get("success").toString(), tester.getBalance().get("success").toString(), "true");
    }

    //Test if Admin node can send a vote to a Voting Node.
    @Test
    public void testSendVoteFromAdmin() {
        Blockchain tester = new Blockchain("192.168.1.20","7419", "multichainrpc","Gnb5RsXa783K9LbJGjtfZNpnJg8UbDu8bza8htd9DMPX");

        JSONObject testObject = new JSONObject();
        testObject.put("success","true");
        //testObject.put("response", BlockchainErrorMessages.InvalidIP.toString());

        // assert statements
        assertEquals(testObject.get("success").toString(), tester.sendVoteToNode("1PSBDgE6XSaifMEJ6gV8TZXwnrKitsKgv33yG4",1).get("success").toString(), "true");
    }

    //Test if Voting Node returns a balance.
    @Test
    public void testVotingNodeBalance() {
        Blockchain tester = new Blockchain("192.168.1.30","7419", "multichainrpc","Gwj3pNxJZoTA3sMJbwyNUfgYfCUGiyv4MyNg6MnwAPZn");

        JSONObject testObject = new JSONObject();
        testObject.put("success","true");
        //testObject.put("response", BlockchainErrorMessages.InvalidIP.toString());

        // assert statements
        assertEquals(testObject.get("success").toString(), tester.getBalance().get("success").toString(), "true");
    }

    //Test if Voting Node can send a vote to Admin Node.
    @Test
    public void testSendVoteFromVotingNode() {
        Blockchain tester = new Blockchain("192.168.1.30","7419", "multichainrpc","Gwj3pNxJZoTA3sMJbwyNUfgYfCUGiyv4MyNg6MnwAPZn");

        JSONObject testObject = new JSONObject();
        testObject.put("success","true");
        //testObject.put("response", BlockchainErrorMessages.InvalidIP.toString());



        // assert statements
        assertEquals(testObject.get("success").toString(), tester.sendVoteToNode("1LEekRGT54DZK1CdR57riPzvVw6kJPMWbJRQBW",1).get("success").toString(), "true");

    }
}
