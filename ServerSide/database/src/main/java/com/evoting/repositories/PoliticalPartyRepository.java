package com.evoting.repositories;

import com.evoting.domain.PoliticalParty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Azhar on 2016/07/14.
 */

public interface PoliticalPartyRepository extends JpaRepository<PoliticalParty, Integer> {
    PoliticalParty findById(int id);
}
