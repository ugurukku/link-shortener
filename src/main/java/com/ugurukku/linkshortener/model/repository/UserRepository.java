package com.ugurukku.linkshortener.model.repository;

import com.ugurukku.linkshortener.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findUserByEmail(String email);

    boolean existsByEmail(String email);

}
