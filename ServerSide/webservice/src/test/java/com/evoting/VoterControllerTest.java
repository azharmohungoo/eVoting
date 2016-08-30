package com.evoting;

import com.evoting.domain.Person;
import com.evoting.domain.UserType;
import com.evoting.repositories.PersonRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import voter.VoterService;

/**
 * Created by Gift on 29/08/16.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class VoterControllerTest {

    @Autowired
    PersonRepository pr;

    @Autowired
    VoterController vc;

    @Test
    public void registerTest()
    {
        VoterService newPerson = new VoterService();



         String idNum = "8999388821";
         String password = "password";
         String name = "aName";
         String surname = "aSurname";
         String locationRegistered = "aLocation";
         String cellphone = "09384722";
         String email = "joidh@jod.com";

        VoterService voter = new VoterService();
        voter.setPassword(password);
        voter.setLocationRegistered(locationRegistered);
        voter.setEmail(email);
        voter.setIdNum(idNum);
        voter.setCellphone(cellphone);
        voter.setName(name);
        voter.setSurname(surname);


        vc.register(voter);

        Person testPerson = pr.findOne(1);
        Assert.assertNotNull(testPerson);

    }

}
