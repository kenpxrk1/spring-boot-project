package ru.kenpxrk.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kenpxrk.project.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
