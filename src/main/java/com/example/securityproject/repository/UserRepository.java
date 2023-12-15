package com.example.securityproject.repository;

import com.example.securityproject.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface UserRepository extends CrudRepository<User, Long> {
    @Query(value = "SELECT u FROM User u where u.userName=:name")
    User getUserByName(@Param("name") String name);
}
