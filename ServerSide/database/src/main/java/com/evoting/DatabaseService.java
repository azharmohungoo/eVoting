<<<<<<< HEAD
package com.evoting;

import com.evoting.domain.Person;
import com.evoting.domain.PoliticalParty;
import com.evoting.repositories.PersonRepository;
import com.evoting.repositories.PoliticalPartyRepository;
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
    @Autowired
    PoliticalPartyRepository ppr;

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

    public boolean validateParty(PoliticalParty _p)
    {
        PoliticalParty p = ppr.getPartyByPartyIdAndPassword(_p.getPartyId(), _p.getPassword());

        if (p == null)
        {
            return false;
        }

        if (p.getPartyId().equals(_p.getPartyId()) && p.getPassword().equals(_p.getPassword()))
        {
            System.out.println("true");
            return true;
        }

        return false;
    }

    public boolean activateVoter(Person _p)
    {
        Person p = pr.getPersonByIdNum(_p.getIdNum());

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

    public boolean deactivateVoter(Person _p)
    {
        Person p = pr.getPersonByIdNum(_p.getIdNum());

        if (p == null)
        {
            return false;
        }

        if (p.getIdNum().equals(_p.getIdNum()))
        {
            p.setActive(false);
            System.out.println("Voter deactivated");
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

    public boolean addParty(Person _p)
    {
        if (_p != null)
        {
            pr.saveAndFlush(_p);
            System.out.println("Party added");

            return true;
        }

        return false;
    }
}
=======
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
        Person p = pr.getPersonByIdNum(_p.getIdNum());

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

    public boolean addParty(Person _p)
    {
        if (_p != null)
        {
            pr.saveAndFlush(_p);
            System.out.println("Party added");

            return true;
        }

        return false;
    }
}
>>>>>>> 11d375a55a3486846e81638e43bf34f75f42ce13
