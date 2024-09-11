package com.tanisha.javafullstack.backend.repository;

import com.tanisha.javafullstack.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
