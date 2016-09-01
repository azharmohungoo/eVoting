package com.evoting.repositories;

import com.evoting.domain.UserType;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Azhar on 2016/07/14.
 */

public interface UserTypeRepository extends JpaRepository<UserType, Integer>
{
    UserType findById(int id);
}
