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
<<<<<<< HEAD
        Person p = pr.getPersonByIdNum(_p.getIdNum());
=======
        System.out.println(_p.getPassword());
        Person p = pr.getPersonByIdNumAndPassword(_p.getIdNum(), _p.getPassword());
>>>>>>> 63c10b69fb5e7c85540acb88681dbe9770d625e9

        if (p == null)
        {
            return false;
        }

        if (p.getIdNum().equals(_p.getIdNum()))
        {
            p.setActive(true);
            System.out.println("Voter activated");
            pr.save(p);
            return true;
        }

        return false;
    }

    public boolean isActive(Person _p)
    {
        Person p = pr.getPersonByIdNumAndPassword(_p.getIdNum(), _p.getPassword());

        if (p == null)
        {
            return false;
        }

        return p.isActive();
    }

    public boolean findVoter(Person _p)
    {
        Person p = pr.getPersonByIdNum(_p.getIdNum());

        if (p == null)
        {
            return false;
        }

        return true;
    }

    public boolean canVoteNational(Person _p)
    {
        Person p = pr.getPersonByIdNumAndPassword(_p.getIdNum(), _p.getPassword());

        if (p == null)
        {
            return false;
        }

        return p.isVotedNationalElection();
    }

    public boolean canVoteProvicial(Person _p)
    {
        Person p = pr.getPersonByIdNumAndPassword(_p.getIdNum(), _p.getPassword());

        if (p == null)
        {
            return false;
        }

        return p.isVotedProvincialElection();
    }

    public boolean addVoter(Person _p)
    {
        if (_p != null)
        {
            pr.saveAndFlush(_p);
            System.out.println("Voter added");

            return true;
        }

        return false;
    }

    public boolean addAdmin(Person _p)
    {
        if (_p != null)
        {
            pr.saveAndFlush(_p);
            System.out.println("Admin added");

            return true;
        }

        return false;
    }

    public boolean addActivator(Person _p)
    {
        if (_p != null)
        {
            pr.saveAndFlush(_p);
            System.out.println("Activator added");

            return true;
        }

        return false;
    }
}
