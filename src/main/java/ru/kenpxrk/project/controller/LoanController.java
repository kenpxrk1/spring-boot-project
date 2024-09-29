package ru.kenpxrk.project.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kenpxrk.project.service.LoanService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class LoanController {
    private final LoanService loanService;

    @GetMapping("/loan")
    public Map<String, Double> getLoanAmount(@Param("userId") Long userId) {
        Map<String, Double> response = new HashMap<>();
        response.put("approvedAmount", loanService.getLoanAmount(userId));
        return response;
    }
}
