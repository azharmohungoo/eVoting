package com.evoting;

import com.evoting.domain.*;
import com.evoting.repositories.AddressRepository;
import com.evoting.repositories.PersonRepository;
import com.evoting.repositories.PoliticalPartyRepository;
import com.evoting.repositories.UserTypeRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import voter.PartyService;
import voter.VoteRequest;
import voter.VoterService;

import javax.json.Json;
import javax.json.JsonObject;
import java.util.HashSet;
import java.util.Set;


/**
 * Created by Gift on 21/08/16.
 */

@RestController
public class VoterController {

    @Autowired
    PersonRepository pr;

    @Autowired
    UserTypeRepository userType;

    @Autowired
    DatabaseService dbService;

    @Autowired
    PoliticalPartyRepository ppr;

    @Autowired
    AddressRepository ar;




    @CrossOrigin
    @RequestMapping(value = "/getPartyStats" , method = RequestMethod.POST, produces = "application/JSON")
    public String getPartyStats(@RequestBody VoteRequest voteRequest)
    {
        System.out.println("inside get stats");
        System.out.println("the party name bih " + voteRequest.getPartyName());

        double nationalPercentage, provincialPercentage;

        PoliticalParty theParty = ppr.getPartyByPartyId(voteRequest.getPartyName());
        if(theParty == null)
        {
            System.out.println("the party is null");

        }
        else
        {
            System.out.println("the party name is " + theParty.getPartyName());
        }

       // int allProvincialVotes = getTheProvincialBalance("Party1") + getTheProvincialBalance("Party2") + getTheProvincialBalance("Party3") + getTheProvincialBalance("Party4");
        //int allNationalVotes =  getTheNationalBalance("Party1") + getTheNationalBalance("Party2") + getTheNationalBalance("Party3") + getTheNationalBalance("Party4");

        //nationalPercentage = (getTheNationalBalance(theParty.getPartyName())/allNationalVotes) * 100;
        //provincialPercentage  = (getTheProvincialBalance(theParty.getPartyName())/allProvincialVotes) * 100;


//       int allProvincialVotes = party1.getProvincialVoteCount() + party2.getProvincialVoteCount() + party3.getProvincialVoteCount() + party4.getProvincialVoteCount();
  //     int allNationalVotes = party1.getNationalVoteCount() + party2.getNationalVoteCount() + party3.getProvincialVoteCount() + party4.getNationalVoteCount();

       // nationalPercentage = (theParty.getNationalVoteCount()/allNationalVotes) * 100;
        //provincialPercentage  = (theParty.getProvincialVoteCount()/allProvincialVotes) * 100;

       // nationalPercentage = 23.64;
         //       provincialPercentage = 45.32;

        int provincial = theParty.getProvincialVoteCount();
        int national = theParty.getNationalVoteCount();

        JsonObject result = Json.createObjectBuilder()
                .add("partyName", "Party1")
                .add("nationalPercentage", national)
                .add("provincialPercentage", provincial)
                .build();

        return result.toString();
    }

    @CrossOrigin
    @RequestMapping(value = "/getParty" , method = RequestMethod.POST, produces = "application/JSON")
    public String getParty(@RequestBody VoteRequest voteRequest)
    {
        System.out.println("inside get party");
        System.out.println(voteRequest.getPartyName());

        PoliticalParty party = ppr.findByPartyName(voteRequest.getPartyName());

      //  System.out.println("returned from db " + party.getPartyName());
        if(party == null)
        {
            System.out.println("party is null");
            JsonObject result = Json.createObjectBuilder()
                    .add("partyName", "Null")
                    .build();

            return result.toString();
        }
        else {

            JsonObject result = Json.createObjectBuilder()
                    .add("partyName", party.getPartyName())
                    .build();

            return result.toString();
        }

    }

    @CrossOrigin
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Boolean register(@RequestBody VoterService newVoter)
    {
        Person newPerson = new Person();
        newPerson.setName(newVoter.getName());
        newPerson.setSurname(newVoter.getSurname());
        newPerson.setCellphone(newVoter.getCellphone());
        newPerson.setEmail(newVoter.getEmail());
        newPerson.setPassword(newVoter.getPassword());
        newPerson.setIdNum(newVoter.getIdNum());
        newPerson.setLocationRegistered(newVoter.getLocationRegistered());
        newPerson.setActive(false);
        newPerson.setVotedNationalElection(false);
        newPerson.setVotedProvincialElection(false);
        newPerson.setVotes(2);
        newPerson.setUserType(new UserType("Voter"));

        System.out.println("Trying to persist new Voter");
        pr.saveAndFlush(newPerson);
        System.out.println("Successful save");

        return true;
    }

    @CrossOrigin
    @RequestMapping(value = "/login", method = RequestMethod.POST , produces = "application/JSON")
    public String login(@RequestBody VoterService voterLogin)
    {

        System.out.println(voterLogin.getIdNum());
        System.out.println(voterLogin.getPassword());

        System.out.println("Logging in user");

        Person aPerson = new Person();
        aPerson.setIdNum(voterLogin.getIdNum());
        aPerson.setPassword(voterLogin.getPassword());

       // System.out.println(aPerson.toString());
        Person loggedInAs;
        boolean successful = dbService.validateUser(aPerson);
        if(successful == true)
        {
            loggedInAs  = pr.getPersonByIdNumAndPassword(aPerson.getIdNum(),aPerson.getPassword());
            JsonObject result = Json.createObjectBuilder()
                    .add("success", successful)
                    .add("name", loggedInAs.getName())
                    .add("surname", loggedInAs.getSurname())
                    .add("IDNum", loggedInAs.getIdNum())
                    .add("password", loggedInAs.getPassword())
                    .add("votes",loggedInAs.getVotes())
                    .add("votedNational", loggedInAs.isVotedNationalElection())
                    .add("votedProvincial", loggedInAs.isVotedProvincialElection())
                    .add("email", loggedInAs.getEmail())
                    .add("cellphone", loggedInAs.getCellphone())
                    .add("activated", loggedInAs.isActive())
                    .add("locationRegistered", loggedInAs.getLocationRegistered())
                    .add("userType", loggedInAs.getUserType().getUserType())
                    .build();

            return result.toString();
        }
        else {

             JsonObject result = Json.createObjectBuilder()
                    .add("success",successful)
                    .add("reason" , "Invalid User")
                    .build();
            return result.toString();
        }

    }

    @CrossOrigin
    @RequestMapping(value = "/castVote", method = RequestMethod.POST)
    public String castVote(@RequestBody VoteRequest voteRequest)
    {
        System.out.println("Cast Vote Request");

        Person voter = pr.getPersonByIdNumAndPassword(voteRequest.getVoterID(),voteRequest.getVoterPassword());
        PoliticalParty theParty = ppr.findByPartyName(voteRequest.getPartyName());


        if(voter == null)
        {
            System.out.println("voter is null");
            JsonObject result = Json.createObjectBuilder()
                    .add("success", false)
                    .add("reason" , "Invalid request : Voter not found.")
                    .build();
            return result.toString();
        }
        else
        {
            System.out.println("voter is not null");
            if(voter.isActive() == false)
            {
                System.out.println("voter is not activated");
                JsonObject result = Json.createObjectBuilder()
                        .add("success", false)
                        .add("reason" , "Invalid request : You have not been activated.")
                        .build();
                return result.toString();
            }
            else
            {
                if(voteRequest.getVoteType().equals("National"))
                {
                    if(voter.isVotedNationalElection() == false)
                    {
                        System.out.println("voting national");
                        Boolean success = castTheVote(voteRequest.getPartyName(), voter.getLocationRegistered(), voteRequest.getVoteType());

                        if (success == true) {
                            voter.setVotedNationalElection(true);
                            voter.setVotes(voter.getVotes() - 1);
                            pr.save(voter);

                            theParty.setNationalVoteCount(theParty.getNationalVoteCount() + 1);
                            ppr.save(theParty);


                            JsonObject result = Json.createObjectBuilder()
                                    .add("success", success)
                                    .add("reason", "Success: You have cast your National Vote for " + voteRequest.getPartyName())
                                    .build();
                            return result.toString();

                        }
                    }
                    else
                    {
                        JsonObject result = Json.createObjectBuilder()
                                .add("success", false)
                                .add("reason", "You have already cast a " + voteRequest.getVoteType() + " vote.")
                                .build();
                        return result.toString();
                    }
                }
               else if(voteRequest.getVoteType().equals("Provincial"))
                {
                    if(voter.isVotedProvincialElection() == false)
                    {

                        Boolean success = castTheVote(voteRequest.getPartyName(), voter.getLocationRegistered(), voteRequest.getVoteType());

                        if (success == true) {
                            voter.setVotedProvincialElection(true);
                            voter.setVotes(voter.getVotes() - 1);
                            pr.save(voter);

                            theParty.setProvincialVoteCount(theParty.getProvincialVoteCount() + 1);
                            ppr.save(theParty);

                            JsonObject result = Json.createObjectBuilder()
                                    .add("success", success)
                                    .add("reason", "Success: You have cast your Provincial Vote for " + voteRequest.getPartyName())
                                    .build();
                            return result.toString();
                        }
                    }
                    else
                    {
                        JsonObject result = Json.createObjectBuilder()
                                .add("success", false)
                                .add("reason", "You have already cast a " + voteRequest.getVoteType() + " vote.")
                                .build();
                        return result.toString();

                    }
                }

            }

        }

        JsonObject result = Json.createObjectBuilder()
                .add("success", false)
                .add("reason" , "Unable to cast vote because something we dont know")
                .build();
        return result.toString();

    }

    public int getTheProvincialBalance(String partyName)

    {  PoliticalParty party = ppr.findByPartyName(partyName);

        Blockchain blockchain = new Blockchain(party.getIpProvincialAddress(),"7419", party.getRpcProvincialUsername(),party.getRpcProvincialPassword());

        int result = blockchain.getPartyBalance(); //sendVoteToNode(party.getBlockchainNodeAddress(),1000);
        return result;
    }

    public int getTheNationalBalance(String partyName)

    {  PoliticalParty party = ppr.findByPartyName(partyName);

        Blockchain blockchain = new Blockchain(party.getIpNationalAddress(),"7419", party.getRpcNationalUsername(),party.getRpcNationalPassword());

        int result = blockchain.getPartyBalance(); //sendVoteToNode(party.getBlockchainNodeAddress(),1000);
        return result;

    }




    public Boolean castTheVote(String partyName, String location , String voteType)
    {  PoliticalParty party = ppr.findByPartyName(partyName);
        Address votingNode = ar.findByNodeName(location);
        //BlockchainMock blockchain = new BlockchainMock("196.248.196.124","7419", "multichainrpc","51i1XY2ELS96V7xGEA3cGh5iy8KDTxpo2ckaXZ7CBM43");
        //JSONObject result = blockchain.sendVoteToNode("15DmYUc17VEx7zvJoAxcPu1fBAREGYVj4ScVwe",1000);

         Blockchain blockchain = new Blockchain(votingNode.getIpAddress(),"7419", votingNode.getRpcUsername(),votingNode.getRpcPassword());

        if(voteType.equals("National")) {
            JSONObject result = blockchain.sendVoteToNode(party.getBlockchainNationalNodeAddress(), 1);

            if (result.get("success").toString().equals("true"))
                return true;
            else
                return false;
        }
        else if(voteType.equals("Provincial")) {
            JSONObject result = blockchain.sendVoteToNode(party.getBlockchainProvincialNodeAddress(), 1);

            if (result.get("success").toString().equals("true"))
                return true;
            else
                return false;
        }

        return false;
    }

    @CrossOrigin
    @RequestMapping(value = "/adminRegister", method = RequestMethod.POST)
    public Boolean adminRegister(@RequestBody VoterService newUser)
    {
        Person p = new Person();
        p.setName(newUser.getName());
        p.setSurname(newUser.getSurname());
        p.setCellphone(newUser.getCellphone());
        p.setEmail(newUser.getEmail());
        p.setPassword(newUser.getPassword());
        p.setIdNum(newUser.getIdNum());
        p.setLocationRegistered(newUser.getLocationRegistered());
        p.setActive(false);
        p.setVotedNationalElection(false);
        p.setVotedProvincialElection(false);
        p.setVotes(0);

        Set<Permission> setP;

        switch (newUser.getUserType())
        {
            case "admin":
                p.setUserType(new UserType("Admin"));

                setP = new HashSet<>(0);
                setP.add(new Permission("Insert"));
                setP.add(new Permission("Update"));
                setP.add(new Permission("Delete"));

                p.setPermissions(setP);

                dbService.addAdmin(p);
                break;

            case "activator":
                p.setUserType(new UserType("Activator"));

                setP = new HashSet<Permission>(0);
                setP.add(new Permission("Update"));

                p.setPermissions(setP);

                dbService.addActivator(p);
                break;

            case "party":
                p.setUserType(new UserType("Party"));

                dbService.addParty(p);
                break;
        }

        return true;
    }

    @CrossOrigin
    @RequestMapping(value = "/search", method = RequestMethod.POST , produces = "application/JSON")
    public String search(@RequestBody VoterService voterLogin)
    {

        System.out.println(voterLogin.getIdNum());

        System.out.println("Searching for user");

        Person aPerson = new Person();
        aPerson.setIdNum(voterLogin.getIdNum());

        // System.out.println(aPerson.toString());
        Person found;
        boolean successful = dbService.findVoter(aPerson);
        if(successful == true)
        {
            found  = pr.getPersonByIdNum(aPerson.getIdNum());
            JsonObject result = Json.createObjectBuilder()
                    .add("success", successful)
                    .add("name", found.getName())
                    .add("surname", found.getSurname())
                    .add("IDNum", found.getIdNum())
                    .add("votes",found.getVotes())
                    .add("votedNational", found.isVotedNationalElection())
                    .add("votedProvincial", found.isVotedProvincialElection())
                    .add("email", found.getEmail())
                    .add("cellphone", found.getCellphone())
                    .add("activated", found.isActive())
                    .add("locationRegistered", found.getLocationRegistered())
                    .add("userType", found.getUserType().getUserType())
                    .build();

            return result.toString();
        }
        else {

            JsonObject result = Json.createObjectBuilder()
                    .add("success",successful)
                    .add("reason" , "Invalid User")
                    .build();
            return result.toString();
        }
    }

    @CrossOrigin
    @RequestMapping(value = "/activate", method = RequestMethod.POST , produces = "application/JSON")
    public String activate(@RequestBody VoterService voterLogin)
    {

        System.out.println(voterLogin.getIdNum());

        System.out.println("Searching for user");

        Person aPerson = new Person();
        aPerson.setIdNum(voterLogin.getIdNum());

        // System.out.println(aPerson.toString());
        Person found;
        boolean successful1 = dbService.findVoter(aPerson);
        boolean successful2 = dbService.activateVoter(aPerson);

        if(successful1 == true && successful2 == true)
        {
            found  = pr.getPersonByIdNum(aPerson.getIdNum());
            JsonObject result = Json.createObjectBuilder()
                    .add("success", successful1)
                    .add("name", found.getName())
                    .add("surname", found.getSurname())
                    .add("IDNum", found.getIdNum())
                    .add("votes",found.getVotes())
                    .add("votedNational", found.isVotedNationalElection())
                    .add("votedProvincial", found.isVotedProvincialElection())
                    .add("email", found.getEmail())
                    .add("cellphone", found.getCellphone())
                    .add("activated", found.isActive())
                    .add("locationRegistered", found.getLocationRegistered())
                    .add("userType", found.getUserType().getUserType())
                    .build();

            return result.toString();
        }
        else {

            JsonObject result = Json.createObjectBuilder()
                    .add("success1",successful1)
                    .add("success2",successful2)
                    .add("reason" , "Invalid User")
                    .build();
            return result.toString();
        }
    }

    @CrossOrigin
    @RequestMapping(value = "/deactivate", method = RequestMethod.POST , produces = "application/JSON")
    public String deactivate(@RequestBody VoterService voterLogin)
    {

        System.out.println(voterLogin.getIdNum());

        System.out.println("Searching for user");

        Person aPerson = new Person();
        aPerson.setIdNum(voterLogin.getIdNum());

        // System.out.println(aPerson.toString());
        Person found;
        boolean successful1 = dbService.findVoter(aPerson);
        boolean successful2 = dbService.deactivateVoter(aPerson);

        if(successful1 == true && successful2 == true)
        {
            found  = pr.getPersonByIdNum(aPerson.getIdNum());
            JsonObject result = Json.createObjectBuilder()
                    .add("success", successful1)
                    .add("name", found.getName())
                    .add("surname", found.getSurname())
                    .add("IDNum", found.getIdNum())
                    .add("votes",found.getVotes())
                    .add("votedNational", found.isVotedNationalElection())
                    .add("votedProvincial", found.isVotedProvincialElection())
                    .add("email", found.getEmail())
                    .add("cellphone", found.getCellphone())
                    .add("activated", found.isActive())
                    .add("locationRegistered", found.getLocationRegistered())
                    .add("userType", found.getUserType().getUserType())
                    .build();

            return result.toString();
        }
        else {

            JsonObject result = Json.createObjectBuilder()
                    .add("success1",successful1)
                    .add("success2",successful2)
                    .add("reason" , "Invalid User")
                    .build();
            return result.toString();
        }
    }

    @CrossOrigin
    @RequestMapping(value = "/registerParty", method = RequestMethod.POST)
    public Boolean registerParty(@RequestBody PartyService newParty)
    {
        PoliticalParty newPoliticalParty = new PoliticalParty();
        newPoliticalParty.setPartyId(newParty.getPartyId());
        newPoliticalParty.setPassword(newParty.getPassword());
        newPoliticalParty.setPartyName(newParty.getPartyName());
        newPoliticalParty.setNationalVoteCount(newParty.getNationalVoteCount());
        newPoliticalParty.setProvincialVoteCount(newParty.getProvincialVoteCount());
        newPoliticalParty.setBlockchainNationalNodeAddress(newParty.getBlockchainNodeAddress());
        newPoliticalParty.setIpNationalAddress(newParty.getIpAddress());
        newPoliticalParty.setBlockchainProvincialNodeAddress(newParty.getBlockchainNodeAddress());
        newPoliticalParty.setIpProvincialAddress(newParty.getIpAddress());
        newPoliticalParty.setPartyDescription(newParty.getPartyDescription());
        newPoliticalParty.setImgURL(newParty.getImgURL());

        System.out.println("Trying to persist new Voter");
        ppr.saveAndFlush(newPoliticalParty);
        System.out.println("Successful save");

        return true;
    }



    @CrossOrigin
    @RequestMapping(value = "/loginP", method = RequestMethod.POST , produces = "application/JSON")
    public String loginP(@RequestBody PartyService party)
    {
        System.out.println(party.getPartyId());
        System.out.println(party.getPassword());

        System.out.println("Logging in party");

        PoliticalParty aVoter = new PoliticalParty();
        aVoter.setPartyId(party.getPartyId());
        aVoter.setPassword(party.getPassword());

        // System.out.println(aPerson.toString());
        PoliticalParty loggedInAs;
        boolean successful = dbService.validateParty(aVoter);
        if(successful == true)
        {
            loggedInAs  = ppr.getPartyByPartyIdAndPassword(aVoter.getPartyId(),aVoter.getPassword());
            JsonObject result = Json.createObjectBuilder()
                    .add("success", successful)
                    .add("partyId", loggedInAs.getPartyId())
                    .add("password", loggedInAs.getPassword())
                    .add("partyName", loggedInAs.getPartyName())
                    .add("nationalVoteCount", loggedInAs.getNationalVoteCount())
                    .add("provincialVoteCount",loggedInAs.getProvincialVoteCount())
                    .add("partyDescription", loggedInAs.getPartyDescription())
                    .add("imgURL", loggedInAs.getImgURL())
                    .build();

            return result.toString();
        }
        else {

            JsonObject result = Json.createObjectBuilder()
                    .add("success",successful)
                    .add("reason" , "Invalid User")
                    .build();
            return result.toString();
        }

    }



}