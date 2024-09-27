package ru.kenpxrk.project.service;

import ru.kenpxrk.project.model.Car;

import java.util.List;

public interface CarService {
    List<Car> findAll(int count);

    List<Car> findAll();
}
