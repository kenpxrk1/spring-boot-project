package ru.kenpxrk.project.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import ru.kenpxrk.project.config.AppConfig;
import ru.kenpxrk.project.model.Car;
import ru.kenpxrk.project.service.CarServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class CarController {
    private final CarServiceImpl carService;
    private final AppConfig config;

    @GetMapping("/cars")
    public String getCars(@RequestParam(value = "count", required = false) Integer count,
                          @RequestParam(value = "sortBy", required = false) String sortBy, Model model) {
        if (sortBy != null && !config.getEnabledSortFields().contains(sortBy)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        List<Car> cars = (count != null) ? carService.findAll(count) : carService.findAll();

        if (sortBy != null) {
            cars = cars.stream()
                       .sorted(Car.getComparator(sortBy))
                       .collect(Collectors.toList());
        }
        model.addAttribute("cars", cars);
        return "cars";
    }
}
