package com.evoting;

import com.evoting.domain.Person;
import com.evoting.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Azhar on 2016/09/01.
 */

@Component
public class DatabaseService
{
    @Autowired
    PersonRepository pr;

    public boolean validateUser(Person _p)
    {
        Person p  = pr.getPersonByIdNumAndPassword(_p.getIdNum(), _p.getPassword());

        if (p == null)
        {
            return false;
        }

        

        return true;
    }

    public boolean activateUser()
    {
        return false;
    }
}
