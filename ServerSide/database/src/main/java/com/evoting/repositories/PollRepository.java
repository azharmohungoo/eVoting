package com.evoting.repositories;

import com.evoting.domain.Poll;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Azhar on 2016/07/14.
 */

public interface PollRepository extends JpaRepository<Poll, Integer>
{
    Poll findById(int id);
}
