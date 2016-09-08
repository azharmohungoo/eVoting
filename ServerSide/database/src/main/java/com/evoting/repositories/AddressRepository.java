package com.evoting.repositories;

import com.evoting.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Azhar on 2016/09/04.
 */

public interface AddressRepository extends JpaRepository<Address, Integer>
{
    Address findById(int id);
    Address findByNodeName(String nodeName);
}