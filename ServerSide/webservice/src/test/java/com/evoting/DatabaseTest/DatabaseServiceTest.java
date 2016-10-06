package com.evoting.DatabaseTest;

import com.evoting.DatabaseService;
import com.evoting.domain.*;
import com.evoting.repositories.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Azhar on 2016/09/02.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class DatabaseServiceTest
{
    @Autowired
    DatabaseService ds;

    @Autowired
    ActivationStationRepository activationStationRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    PermissionRepository permissionRepository;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    PoliticalPartyRepository politicalPartyRepository;

    @Autowired
    PollRepository pollRepository;

    @Autowired
    UserTypeRepository userTypeRepository;

    @Test
    public void insertAddress()
    {
        addressRepository.saveAndFlush(new Address("Pretoria", "0.0.0.1", "9182", "rpcUser1", "rpcPass1", "kjytdcvbji8ygfdswe456ujkiuytrd"));
        addressRepository.saveAndFlush(new Address("Joburg", "0.0.0.2", "9732", "rpcUser2", "rpcPass2", "jytredvhju7654ewe4567uvfrtyuse"));
        addressRepository.saveAndFlush(new Address("Cape Town", "0.0.0.3", "9652", "rpcUser3", "rpcPass3", "juy5redcvbju765redvbnji876rkuy"));
        addressRepository.saveAndFlush(new Address("Durban", "0.0.0.4", "9382", "rpcUser4", "rpcPass4", "jytredvhju7654ewe4567uvfrtyuse"));
        addressRepository.saveAndFlush(new Address("Centurion", "0.0.0.5", "9292", "rpcUser5", "rpcPass5", "juy5redcvbju765redvbnji876rkuy"));

        Assert.assertTrue(true);
    }

    @Test
    public void insertPermission()
    {
        permissionRepository.saveAndFlush(new Permission("Insert"));
        permissionRepository.saveAndFlush(new Permission("Update"));
        permissionRepository.saveAndFlush(new Permission("Delete"));

        Assert.assertTrue(true);
    }

    @Test
    public void insertUserType()
    {
        userTypeRepository.saveAndFlush(new UserType("Admin"));
        userTypeRepository.saveAndFlush(new UserType("Voter"));
        userTypeRepository.saveAndFlush(new UserType("Party"));
        userTypeRepository.saveAndFlush(new UserType("Activator"));

        Assert.assertTrue(true);
    }

    @Test
    public void insertPerson()
    {
        Set<Permission> setP = new HashSet<Permission>(0);
        setP.add(new Permission("Insert"));
        setP.add(new Permission("Update"));
        setP.add(new Permission("Delete"));

        Person p = new Person("0987654321098", "pass1", "name1", "surname1", "Pretoria", "0987654321", "email1@gmail.com", 2, false, false, false);
        p.setPermissions(setP);
        p.setUserType(new UserType("Admin"));
        personRepository.saveAndFlush(p);

        p = new Person("0987654321765", "pass2", "name2", "surname2", "Joburg", "9876543210", "email2@gmail.com", 2, false, false, false);
        p.setUserType(new UserType("Voter"));
        personRepository.saveAndFlush(p);

     /*   p = new Person("931119", "password", "Gift", "Sefako", "Joburg", "0605250402", "giftsefako@gmail.com", 2, false, false, false);
        p.setUserType(new UserType("Voter"));
        personRepository.saveAndFlush(p);

*/

        p = new Person("0987654321432", "pass3", "name3", "surname3", "Cape Town", "8765432109", "email3@gmail.com", 2, false, false, false);
        p.setUserType(new UserType("Voter"));
        personRepository.saveAndFlush(p);

        setP = new HashSet<Permission>(0);
        setP.add(new Permission("Update"));

        p = new Person("0987654321123", "pass4", "name4", "surname4", "Durban", "7654321098", "email4@gmail.com", 2, false, false, false);
        p.setPermissions(setP);
        p.setUserType(new UserType("Activator"));
        personRepository.saveAndFlush(p);

        p = new Person("0987654321456", "pass5", "name5", "surname5", "Centurion", "6543210987", "email5@gmail.com", 2, false, false, false);
        p.setUserType(new UserType("Party"));
        personRepository.saveAndFlush(p);

        Assert.assertTrue(true);
    }

    @Test
    public void insertPoll()
    {
        pollRepository.saveAndFlush(new Poll("National Election"));
        pollRepository.saveAndFlush(new Poll("Provincial Election"));

        Assert.assertTrue(true);
    }

    @Test
    public void insertPoliticalParty()
    {
        Set<Poll> setP = new HashSet<Poll>(0);
        setP.add(new Poll("National Election"));
        setP.add(new Poll("Provincial Election"));

        PoliticalParty p = new PoliticalParty("Party1", 0, 0, "ju765redcvbnjiu765rdfghjki", "0.0.0.1");
        p.setPolls(setP);
        politicalPartyRepository.saveAndFlush(p);

        p = new PoliticalParty("Party2", 0, 0, "nbgfr567ujbvfrtyuirtyhgfer", "0.0.0.3");
        politicalPartyRepository.saveAndFlush(p);

        setP = new HashSet<Poll>(0);
        setP.add(new Poll("National Election"));
        setP.add(new Poll("Provincial Election"));

        p = new PoliticalParty("Party3", 0, 0, "7tfyhbef3ufy3ivr3v3iiwhbci", "0.0.0.5");
        p.setPolls(setP);
        politicalPartyRepository.saveAndFlush(p);

        setP = new HashSet<Poll>(0);
        setP.add(new Poll("National Election"));

        p = new PoliticalParty("Party4", 0, 0, "kiu7654ewsdfgyuikmnhytushg", "0.0.0.4");
        p.setPolls(setP);
        politicalPartyRepository.saveAndFlush(p);

        setP = new HashSet<Poll>(0);
        setP.add(new Poll("Provincial Election"));

        p = new PoliticalParty("Party5", 0, 0, "bvcde4567ujkmhgredvhjytrfd", "0.0.0.2");
        p.setPolls(setP);
        politicalPartyRepository.saveAndFlush(p);

        Assert.assertTrue(true);
    }

    @Test
    public void insertActivationStation()
    {
        activationStationRepository.saveAndFlush(new ActivationStation("Pretoria", "University of Pretoria"));
        activationStationRepository.saveAndFlush(new ActivationStation("Pretoria", "Tshwane University of Technologu"));
        activationStationRepository.saveAndFlush(new ActivationStation("Johannesburg", "University of Johannesburg"));
        activationStationRepository.saveAndFlush(new ActivationStation("Johannesburg", "University of the Witwatersrand"));
        activationStationRepository.saveAndFlush(new ActivationStation("Cape Town", "University of Cape Town"));
        activationStationRepository.saveAndFlush(new ActivationStation("Stellenbosch", "University of Stellenbosch"));
        activationStationRepository.saveAndFlush(new ActivationStation("Durban", "University of Kwa-Zulu Natal"));
        activationStationRepository.saveAndFlush(new ActivationStation("Durban", "Durban University of Technology"));

        Assert.assertTrue(true);
    }

    @Test
    public void validateUserTest()
    {
        String idNum = "0987656789531";
        String password = "aPassword";

        Person p = new Person();
        p.setIdNum(idNum);
        p.setPassword(password);

        Assert.assertFalse(ds.validateUser(p));
    }

    @Test
    public void activateVoterTest()
    {
        Person p = new Person();
        p.setIdNum("0987654321456");
        p.setPassword("pass5");

        Assert.assertTrue(ds.activateVoter(p));
    }
}