package com.evoting.repositories;

import com.evoting.domain.PoliticalParty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PoliticalPartyRepository extends JpaRepository<PoliticalParty, Integer>
{
    PoliticalParty findById(int id);
    PoliticalParty findByPartyName(String partyName);
    PoliticalParty getPartyByPartyIdAndPassword(String partyId, String password);
    PoliticalParty getPartyByPartyId(String partyId);
}
