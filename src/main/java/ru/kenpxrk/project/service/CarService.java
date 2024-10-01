package ru.kenpxrk.project.service;

import ru.kenpxrk.project.model.CarEntity;

import java.util.List;

public interface CarService {
    List<CarEntity> findAll(Integer count, String sortBy);

    CarEntity getCarByUserId(Long userId);
}
