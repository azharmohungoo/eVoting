package com.evoting.repositories;

import com.evoting.domain.UserType;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserTypeRepository extends JpaRepository<UserType, Integer>
{
    UserType findById(int id);
    UserType findUserTypeByUserType(String userType);
}
