package ru.kenpxrk.project.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.kenpxrk.project.model.Car;
import ru.kenpxrk.project.service.CarServiceImpl;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CarController {
    private final CarServiceImpl carService;

    @GetMapping("cars/{count}")
    public List<Car> getCars(@PathVariable int count) {
        return carService.findAll(count);
    }
}
