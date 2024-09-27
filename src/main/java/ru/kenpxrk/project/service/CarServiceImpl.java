package ru.kenpxrk.project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import ru.kenpxrk.project.config.AppConfig;
import ru.kenpxrk.project.model.Car;
import ru.kenpxrk.project.repository.CarRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {
    private final CarRepository repository;
    private final AppConfig config;

    private static Comparator<Car> getComparator(String sortBy) {
        switch (sortBy) {
            case "model":
                return Comparator.comparing(Car::getModel);
            case "color":
                return Comparator.comparing(Car::getColor);
            case "price":
                return Comparator.comparing(Car::getPrice);
            default:
                throw new IllegalArgumentException("Incorrect sort field");
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<Car> findAll(Integer count, String sortBy) {
        if (sortBy != null && !config.getEnabledSortFields().contains(sortBy)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        List<Car> cars;

        if (count != null) {
            cars = repository.findAll(PageRequest.of(0, count)).toList();
        } else {
            cars = repository.findAll();
        }

        if (sortBy != null) {
            cars.stream().sorted(getComparator(sortBy)).collect(Collectors.toList());
        }

        return cars;
    }
}
