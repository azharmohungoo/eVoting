package com.evoting.repositories;

import com.evoting.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;



public interface PersonRepository extends JpaRepository<Person, Integer>
{
    Person getPersonByIdNumAndPassword(String idNum, String password);
    Person getPersonByName(String name);
    Person getPersonByIdNum(String idNum);
}
