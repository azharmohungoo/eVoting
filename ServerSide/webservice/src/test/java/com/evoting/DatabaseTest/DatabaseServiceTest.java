package com.evoting.DatabaseTest;

import com.evoting.DatabaseService;
import com.evoting.domain.Person;
import com.evoting.repositories.PersonRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DatabaseServiceTest
{
    @Autowired
    PersonRepository pr;

    @Autowired
    DatabaseService ds;

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
}