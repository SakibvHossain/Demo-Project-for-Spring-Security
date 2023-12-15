package com.example.securityproject.tdd;

import com.example.securityproject.entity.User;
import com.example.securityproject.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class UserTest {
    @Autowired
    private UserRepository repository;

    //Testing the database working properly or not
    //Condition: Success
    @Test
    void createUser(){
        User user = new User();
        user.setFirstName("Md.");
        user.setLastName("Ali");
        user.setUserName("Ali21");
        user.setSalt("utf8mb*ds");
        user.setPassword("abcxyz#");

        User savedData = repository.save(user);
        Assertions.assertThat(savedData).isNotNull();
        Assertions.assertThat(savedData.getId()).isGreaterThan(0);
    }

    //Testing that our repository Query getUserByName working properly or not
    //Condition: Success
    @Test
    void getUserByName(){
        String name = "Ali21";
        User user = repository.getUserByName(name);
        Assertions.assertThat(user.getLastName()).isEqualTo("Ali");
    }
}
