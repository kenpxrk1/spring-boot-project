package ru.kenpxrk.project.service;

import ru.kenpxrk.project.model.UserEntity;

import java.util.List;

public interface UserService {
    List<UserEntity> getUsers();

    UserEntity getUserById(Long id);
}
