package com.example.liquidbase.repositories;

import com.example.liquidbase.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findOneById(Integer id);
}
