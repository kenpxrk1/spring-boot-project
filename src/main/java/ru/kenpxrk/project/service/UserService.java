package ru.kenpxrk.project.service;

import ru.kenpxrk.project.model.User;

import java.util.List;

public interface UserService {
    List<User> getUsers();

    User getUserById(Long id);
}
