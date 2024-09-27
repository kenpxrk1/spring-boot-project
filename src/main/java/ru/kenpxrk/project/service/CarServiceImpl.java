package ru.kenpxrk.project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kenpxrk.project.model.Car;
import ru.kenpxrk.project.repository.CarRepository;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {
    private final CarRepository repository;

    @Transactional
    @Override
    public List<Car> findAll(int count) {
        return repository.findAll(PageRequest.of(0, count)).toList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Car> findAll() {
        return repository.findAll();
    }
}
