package com.group.librarymanagementweb.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByName(String name);

    List<User> findAllByNameContainingOrPhoneNumberContainingOrBirthDateContaining(
            String name,
            String phoneNumber,
            String birthDate);
}
