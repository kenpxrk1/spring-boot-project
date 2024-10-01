package ru.kenpxrk.project.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kenpxrk.project.model.CarEntity;
import ru.kenpxrk.project.service.CarService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CarController {
    private final CarService carService;

    @GetMapping("/cars")
    public String getCars(@RequestParam(value = "count", required = false) Integer count,
                          @RequestParam(value = "sortBy", required = false) String sortBy, Model model) {

        List<CarEntity> cars = carService.findAll(count, sortBy);

        model.addAttribute("cars", cars);
        return "cars";
    }
}
