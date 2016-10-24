package com.evoting.repositories;

import com.evoting.domain.Poll;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PollRepository extends JpaRepository<Poll, Integer>
{
    Poll findById(int id);
}
