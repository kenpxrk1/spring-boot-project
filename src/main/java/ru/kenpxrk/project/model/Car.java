package ru.kenpxrk.project.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Comparator;


@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "model", nullable = false, length = 200)
    private String model;

    @Column(name = "color", nullable = false, length = 50)
    private String color;

    @Column(name = "price", nullable = false)
    private Long price;

    public static Comparator<Car> getComparator(String sortBy){
        switch (sortBy){
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
}
