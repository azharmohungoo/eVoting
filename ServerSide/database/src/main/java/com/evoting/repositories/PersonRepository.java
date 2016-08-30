package com.evoting.repositories;

import com.evoting.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Savepoint;

/**
 * Created by Azhar on 2016/07/14.
 */

public interface PersonRepository extends JpaRepository<Person, Integer> {

}
