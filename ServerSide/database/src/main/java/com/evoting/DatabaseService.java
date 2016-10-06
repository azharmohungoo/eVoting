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
        Person p = pr.getPersonByIdNumAndPassword(_p.getIdNum(), _p.getPassword());

        if (p == null)
        {
            return false;
        }

        if (p.getIdNum().equals(_p.getIdNum()) && p.getPassword().equals(_p.getPassword()))
        {
            return true;
        }

        return false;
    }

    public boolean activateVoter(Person _p)
    {
        Person p = pr.getPersonByIdNumAndPassword(_p.getIdNum(), _p.getPassword());

        if (p == null)
        {
            return false;
        }

        if (p.getIdNum().equals(_p.getIdNum()) && p.getPassword().equals(_p.getPassword()))
        {
            pr.setActivationStatusFor(true, p.getIdNum(), p.getPassword());
            return true;
        }

        return false;
    }

    public boolean isActive(Person _p)
    {
        Person p = pr.getPersonByIdNumAndPassword(_p.getIdNum(), _p.getPassword());

        return p.isActive();
    }

    public boolean canVoteNational(Person _p)
    {
        Person p = pr.getPersonByIdNumAndPassword(_p.getIdNum(), _p.getPassword());

        return p.isVotedNationalElection();
    }

    public boolean canVoteProvicial(Person _p)
    {
        Person p = pr.getPersonByIdNumAndPassword(_p.getIdNum(), _p.getPassword());

        return p.isVotedProvincialElection();
    }
}
