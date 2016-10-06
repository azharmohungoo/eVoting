package com.evoting.repositories;

import com.evoting.domain.ActivationStation;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Azhar on 2016/10/06.
 */
public interface ActivationStationRepository extends JpaRepository<ActivationStation, Integer>
{
    ActivationStation findById(int id);
}
