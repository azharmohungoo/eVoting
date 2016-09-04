package com.evoting;

/**
 * Created by Andreas on 2016/09/02.
 */

import junit.framework.TestCase;
import org.json.JSONObject;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import static org.junit.Assert.assertEquals;


public class BlockchainTest extends TestCase {

    @Test
    public void testEmptyParameters() {
        Blockchain tester = new Blockchain(); // Blockchain is tested

        JSONObject testObject = new JSONObject();
        testObject.put("success","false");
        testObject.put("response", BlockchainErrorMessages.EmptyNodeRPCConfiguration.toString());

        // assert statements
        JSONAssert.assertEquals(testObject.toString(), tester.getBalance().toString(), true);
    }

    @Test
    public void testInvalidIPAddress() {
        Blockchain tester = new Blockchain("192.168.8.255","7419", "multichainrpc","2J4gfza47chfXbFQAmqMEffLSktkJ4ujjvQ7LMr2hTSK"); // Blockchain is tested

        JSONObject testObject = new JSONObject();
        testObject.put("success","false");
        testObject.put("response", BlockchainErrorMessages.InvalidIP.toString());

        // assert statements
        JSONAssert.assertEquals(testObject.toString(), tester.getBalance().toString(), true);
    }


    //STILL MORE TESTS BUT WE NEED STATIC IP DECISION FOR BLOCKCHAIN
}
