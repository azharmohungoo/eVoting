package com.evoting;

import com.evoting.domain.Permission;
import com.evoting.domain.Person;
import com.evoting.domain.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import voter.VoterService;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Azhar on 2016/10/16.
 */

@RestController
public class AdminController
{
    @Autowired
    DatabaseService dbService;

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

                setP = new HashSet<Permission>(0);
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
}
