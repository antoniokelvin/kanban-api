package com.pepkor.kanban.repository;

import com.pepkor.kanban.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
