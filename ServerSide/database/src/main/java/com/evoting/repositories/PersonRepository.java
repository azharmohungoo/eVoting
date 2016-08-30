package com.evoting.repositories;

import com.evoting.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Azhar on 2016/07/14.
 */

public interface PersonRepository extends JpaRepository<Person, Integer> {
    Person findById(int id);
}
