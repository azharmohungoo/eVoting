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
        Person p =  pr.getPersonByIdNumAndPassword(_p.getIdNum(), _p.getPassword());

        if (p == null)
        {
            return false;
        }

<<<<<<< HEAD
=======
        if (p.getIdNum().equals(_p.getIdNum()) && p.getPassword().equals(_p.getPassword()))
        {
            return true;
        }
>>>>>>> e5d79075944d184b4ca2a67842142c0cfa2d0fe3

        return false;
    }

    public boolean activateUser()
    {
        return false;
    }
}
