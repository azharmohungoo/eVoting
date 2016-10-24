package com.evoting.repositories;

import com.evoting.domain.ActivationStation;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ActivationStationRepository extends JpaRepository<ActivationStation, Integer>
{
    ActivationStation findById(int id);
}
