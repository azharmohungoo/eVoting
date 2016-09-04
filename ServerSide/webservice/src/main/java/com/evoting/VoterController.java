package com.evoting;


import com.evoting.domain.Person;
import com.evoting.domain.UserType;
import com.evoting.repositories.PersonRepository;
import com.evoting.repositories.UserTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.support.HttpRequestWrapper;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.support.HttpRequestHandlerServlet;
import voter.VoterService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by Gift on 21/08/16.
 */

@RestController
public class VoterController {

    @Autowired
    PersonRepository pr;

    @Autowired
    UserTypeRepository userType;

    @CrossOrigin
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Boolean register(@RequestBody VoterService newVoter){

        Person newPerson = new Person();
        newPerson.setName(newVoter.getName());
        newPerson.setSurname(newVoter.getSurname());
        newPerson.setCellphone(newVoter.getCellphone());
        newPerson.setEmail(newVoter.getEmail());
        newPerson.setPassword(newVoter.getPassword());
        newPerson.setUserType(userType.findById(1));
        newPerson.setIdNum(newVoter.getIdNum());
        newPerson.setLocationRegistered(newVoter.getLocationRegistered());
        newPerson.setActive(false);
        newPerson.setVotedNationalElection(false);
        newPerson.setVotedProvincialElection(false);

        System.out.println("trying to persist");
        pr.saveAndFlush(newPerson);
       // pr.save(newPerson);
        System.out.println("Successful save");

        return false;
    }

    @CrossOrigin
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Boolean login(@RequestBody VoterService voterLogin){

        System.out.println(voterLogin.getIdNum());
        System.out.println(voterLogin.getPassword());


        System.out.println("we are loggin in");

        Person aPerson = new Person();
        aPerson.setIdNum(voterLogin.getIdNum());
        aPerson.setPassword(voterLogin.getPassword());

        DatabaseService dbService = new DatabaseService();


        return dbService.validateUser(aPerson);

    }



}
