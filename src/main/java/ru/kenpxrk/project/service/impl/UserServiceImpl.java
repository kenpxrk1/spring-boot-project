package ru.kenpxrk.project.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import ru.kenpxrk.project.model.UserEntity;
import ru.kenpxrk.project.repository.UserRepository;
import ru.kenpxrk.project.service.UserService;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    @Override
    public List<UserEntity> getUsers() {
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public UserEntity getUserById(Long id) {
        Optional<UserEntity> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return user.get();
    }
}
