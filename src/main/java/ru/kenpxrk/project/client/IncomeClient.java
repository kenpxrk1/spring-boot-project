package ru.kenpxrk.project.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;


@Component
public class IncomeClient {
    private final WebClient webClient = WebClient.create();
    @Value("${loan.income-api}")
    private String incomeApi;

    public List<IncomeData> getUserIncomes() {
        return webClient.get().uri(incomeApi).retrieve().bodyToFlux(IncomeData.class).collectList().block();
    }

}
