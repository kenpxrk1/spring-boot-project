package ru.kenpxrk.project.service;

import org.springframework.beans.factory.annotation.Value;
import ru.kenpxrk.project.model.Car;

import java.util.List;

public interface CarService {
    List<Car> findAll(int count);
    List<Car> findAll();
}
