package ru.kenpxrk.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.kenpxrk.project.model.Car;

import java.util.List;


@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    @Query(value = "SELECT * FROM cars LIMIT ?1", nativeQuery = true)
    List<Car> findAll(int count);
}
