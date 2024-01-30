package com.example.codeupspringcapstone.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.codeupspringcapstone.models.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    User getUsersById(long id);



    // Add this method
    boolean existsByUsername(String username);

    boolean existsByEmail(String email);


}


