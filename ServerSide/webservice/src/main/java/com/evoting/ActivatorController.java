package com.evoting;

import com.evoting.domain.Person;
import com.evoting.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import voter.VoterService;

import javax.json.Json;
import javax.json.JsonObject;

/**
 * Created by Azhar on 2016/10/16.
 */

@RestController
public class ActivatorController
{
    @Autowired
    PersonRepository pr;

    @Autowired
    DatabaseService dbService;

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
}
