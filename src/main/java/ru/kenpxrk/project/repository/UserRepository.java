package ru.kenpxrk.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kenpxrk.project.model.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
