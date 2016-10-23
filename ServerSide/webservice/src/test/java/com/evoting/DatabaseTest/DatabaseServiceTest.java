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
        addressRepository.saveAndFlush(new Address("Admin", "192.168.1.20", "7419", "multichainrpc", "Gnb5RsXa783K9LbJGjtfZNpnJg8UbDu8bza8htd9DMPX", "1LEekRGT54DZK1CdR57riPzvVw6kJPMWbJRQBW"));
        addressRepository.saveAndFlush(new Address("Pretoria", "192.168.1.30", "7419", "multichainrpc", "Gwj3pNxJZoTA3sMJbwyNUfgYfCUGiyv4MyNg6MnwAPZn", "1PSBDgE6XSaifMEJ6gV8TZXwnrKitsKgv33yG4"));//Voting Node 1

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

        Person p = new Person("0987654321098", "e6c3da5b206634d7f3f3586d747ffdb36b5c675757b380c6a5fe5c570c714349", "name1", "surname1", "Pretoria", "0987654321", "email1@gmail.com", 0, false, false, false); //password: "pass1"
        p.setPermissions(setP);
        p.setUserType(new UserType("Admin"));
        personRepository.saveAndFlush(p);

        p = new Person("0987654321765", "1ba3d16e9881959f8c9a9762854f72c6e6321cdd44358a10a4e939033117eab9", "name2", "surname2", "Joburg", "9876543210", "email2@gmail.com", 2, false, false, false); //password: "pass1"
        p.setUserType(new UserType("Voter"));
        personRepository.saveAndFlush(p);

        p = new Person("0987654321432", "3acb59306ef6e660cf832d1d34c4fba3d88d616f0bb5c2a9e0f82d18ef6fc167", "name3", "surname3", "Cape Town", "8765432109", "email3@gmail.com", 2, false, false, false); //password: "pass3"
        p.setUserType(new UserType("Voter"));
        personRepository.saveAndFlush(p);

        setP = new HashSet<Permission>(0);
        setP.add(new Permission("Update"));

        p = new Person("0987654321123", "a417b5dc3d06d15d91c6687e27fc1705ebc56b3b2d813abe03066e5643fe4e74", "name4", "surname4", "Durban", "7654321098", "email4@gmail.com", 2, false, false, false);//password: "pass4"
        p.setPermissions(setP);
        p.setUserType(new UserType("Activator"));
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

        PoliticalParty p = new PoliticalParty("partyId1", "5c4950c94a3461441c356afa783f76b83b38fd65f730f291403efbcc798acc1f", "Party1", 0, 0, "1QoKsm2sTax6ejR959iej5s1AdHuqpfBqRRZ4z","multichainrpc","BNnekCRDZvdRXR7L5CNkQLZbvG2ireydBVPVdAhQNEj9" , "192.168.1.60", "1ZwBGVH9rJ75hQvbMRCM3BXWTGgbJi74Aqvfg8","multichainrpc","DJL3J3wNRD9qJdDLkVGCvbSkXyoVYE6pw8yrG8maM8ZX", "192.168.1.65", "partyDecs1", "party1.img");
        p.setPolls(setP);
        politicalPartyRepository.saveAndFlush(p);

        p = new PoliticalParty("partyId2", "5c4950c94a3461441c356afa783f76b83b38fd65f730f291403efbcc798acc1f", "Party2", 0, 0, "nbgfr567ujbvfrtyuirtyhgfer","rpcUsername","rpcPassword", "192.168.1.200", "nbgfr567ujbvfrtyuirtyhgfer","rpcUsername","rpcPassword", "192.168.1.200", "partyDecs2", "party2.img");
        politicalPartyRepository.saveAndFlush(p);

        setP = new HashSet<Poll>(0);
        setP.add(new Poll("National Election"));
        setP.add(new Poll("Provincial Election"));

        p = new PoliticalParty("partyId3", "5c4950c94a3461441c356afa783f76b83b38fd65f730f291403efbcc798acc1f", "Party3", 0, 0, "7tfyhbef3ufy3ivr3v3iiwhbci","rpcUsername","rpcPassword", "192.168.1.200", "7tfyhbef3ufy3ivr3v3iiwhbci","rpcUsername","rpcPassword", "192.168.1.200", "partyDecs3", "party3.img");
        p.setPolls(setP);
        politicalPartyRepository.saveAndFlush(p);

        setP = new HashSet<Poll>(0);
        setP.add(new Poll("National Election"));

        p = new PoliticalParty("partyId4", "5c4950c94a3461441c356afa783f76b83b38fd65f730f291403efbcc798acc1f", "Party4", 0, 0, "kiu7654ewsdfgyuikmnhytushg", "rpcUsername","rpcPassword","192.168.1.200", "kiu7654ewsdfgyuikmnhytushg","rpcUsername","rpcPassword", "192.168.1.200", "partyDecs4", "party4.img");
        p.setPolls(setP);
        politicalPartyRepository.saveAndFlush(p);

        setP = new HashSet<Poll>(0);
        setP.add(new Poll("Provincial Election"));

        p = new PoliticalParty("partyId5", "5c4950c94a3461441c356afa783f76b83b38fd65f730f291403efbcc798acc1f", "Party5", 0, 0, "bvcde4567ujkmhgredvhjytrfd","rpcUsername","rpcPassword", "192.168.1.200", "bvcde4567ujkmhgredvhjytrfd","rpcUsername","rpcPassword", "192.168.1.200", "partyDecs5", "party5.img");
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
    public void validatePartyTest()
    {
        String idNum = "partyii";
        String password = "ppp";

        PoliticalParty p = new PoliticalParty();
        p.setPartyId(idNum);
        p.setPassword(password);

        Assert.assertFalse(ds.validateParty(p));
    }

    @Test
    public void activateVoterTest()
    {
        Person p = new Person();
        p.setIdNum("0987654321765");
        p.setPassword("1ba3d16e9881959f8c9a9762854f72c6e6321cdd44358a10a4e939033117eab9");

        Assert.assertTrue(ds.activateVoter(p));
    }

    @Test
    public void addVoterTest()
    {
        Person p = new Person("5679303249786", "5c4950c94a3461441c356afa783f76b83b38fd65f730f291403efbcc798acc1f", "newVoterName", "NewVoterSurname", "Pretoria - UP", "0987654399", "newVoter@gmail.com", 2, false, false, false);
        p.setUserType(new UserType("Voter"));

        ds.addVoter(p);

        Assert.assertNotNull(personRepository.getPersonByIdNumAndPassword(p.getIdNum(), p.getPassword()));
    }

    @Test
    public void addAdminTest()
    {
        Set<Permission> setP = new HashSet<Permission>(0);
        setP.add(new Permission("Insert"));
        setP.add(new Permission("Update"));
        setP.add(new Permission("Delete"));

        Person p = new Person("9979303249786", "5c4950c94a3461441c356afa783f76b83b38fd65f730f291403efbcc798acc1f", "newAdminName", "NewAdminSurname", "", "2287654399", "newAdmin@gmail.com", 0, false, false, false);
        p.setPermissions(setP);
        p.setUserType(new UserType("Admin"));

        ds.addAdmin(p);

        Assert.assertNotNull(personRepository.getPersonByIdNumAndPassword(p.getIdNum(), p.getPassword()));
    }

    @Test
    public void addActivatorTest()
    {
        Set<Permission> setP = new HashSet<Permission>(0);
        setP.add(new Permission("Update"));

        Person p = new Person("2793032497862", "5c4950c94a3461441c356afa783f76b83b38fd65f730f291403efbcc798acc1f", "newActivatorName", "NewActivatorSurname", "", "7787654399", "newActivator@gmail.com", 0, false, false, false);
        p.setPermissions(setP);
        p.setUserType(new UserType("Activator"));

        ds.addActivator(p);

        Assert.assertNotNull(personRepository.getPersonByIdNumAndPassword(p.getIdNum(), p.getPassword()));
    }


    @Test
    public void addPartyTest()
    {
        Set<Poll> setP = new HashSet<Poll>(0);
        setP.add(new Poll("National Election"));
        setP.add(new Poll("Provincial Election"));

        PoliticalParty p = new PoliticalParty("partyId6", "5c4950c94a3461441c356afa783f76b83b38fd65f730f291403efbcc798acc1f", "Party6", 0, 0, "bvcde4567ujkmhgredvhjytrfd", "rpcUsername","rpcPassword","192.168.1.200", "bvcde4567ujkmhgredvhjytrfd","rpcUsername","rpcPassword", "192.168.1.200", "partyDecs6", "party6.img");
        p.setPolls(setP);
        politicalPartyRepository.saveAndFlush(p);

        Assert.assertNotNull(politicalPartyRepository.getPartyByPartyIdAndPassword(p.getPartyId(), p.getPassword()));
    }
}