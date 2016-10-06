package com.evoting.repositories;

import com.evoting.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Azhar on 2016/07/14.
 */

public interface PersonRepository extends JpaRepository<Person, Integer>
{
    Person getPersonByIdNumAndPassword(String idNum, String password);
    Person getPersonByName(String name);
    //Person setActivationStatusFor(boolean activate, String idNum, String password);
}
