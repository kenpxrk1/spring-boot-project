package ru.kenpxrk.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kenpxrk.project.model.Car;


@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

}
