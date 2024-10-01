package ru.kenpxrk.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.kenpxrk.project.model.CarEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<CarEntity, Long> {
    @Query(value = "SELECT * FROM cars LIMIT :count", nativeQuery = true)
    List<CarEntity> findAll(@Param("count") int count);

    @Query(value = "SELECT u.car FROM User u WHERE u.id = :userId")
    Optional<CarEntity> findCarByUserId(@Param("userId") Long id);
}
