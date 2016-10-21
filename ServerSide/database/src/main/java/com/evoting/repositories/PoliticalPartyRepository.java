<<<<<<< HEAD
package com.evoting.repositories;

import com.evoting.domain.PoliticalParty;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Azhar on 2016/07/14.
 */

public interface PoliticalPartyRepository extends JpaRepository<PoliticalParty, Integer>
{
    PoliticalParty findById(int id);
    PoliticalParty findByPartyName(String partyName);
    PoliticalParty getPartyByPartyIdAndPassword(String partyId, String password);
}
=======
package com.evoting.repositories;

import com.evoting.domain.PoliticalParty;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Azhar on 2016/07/14.
 */

public interface PoliticalPartyRepository extends JpaRepository<PoliticalParty, Integer>
{
    PoliticalParty findById(int id);
    PoliticalParty findByPartyName(String partyName);
}
>>>>>>> 11d375a55a3486846e81638e43bf34f75f42ce13
