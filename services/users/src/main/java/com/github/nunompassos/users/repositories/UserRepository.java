package com.github.nunompassos.users.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.nunompassos.users.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByName(final String name);

}
