package ru.kenpxrk.project.service.impl;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.kenpxrk.project.client.IncomeClient;
import ru.kenpxrk.project.client.IncomeData;
import ru.kenpxrk.project.model.Car;
import ru.kenpxrk.project.service.CarService;
import ru.kenpxrk.project.service.LoanService;


@Slf4j
@Service
@Getter
@Setter
@RequiredArgsConstructor
public class LoanServiceImpl implements LoanService {
    private final CarService carService;
    private final IncomeClient incomeClient;

    @Value("${loan.minimalIncome}")
    private int MINIMAL_INCOME;

    @Value("${loan.minimalCarPrice}")
    private Long MINIMAL_CAR_PRICE;

    @Value("${loan.maxAnnualIncomeLoanAmount}")
    private double MAX_ANNUAL_INCOME_LOAN_AMOUNT;

    @Value("${loan.maxCarPriceLoanAmount}")
    private double MAX_CAR_PRICE_LOAN_AMOUNT;


    @Override
    public double getLoanAmount(Long userId) {
        int userIncome = incomeClient.getUserIncomes()
                .stream()
                .filter(incomeData -> incomeData.getId() == userId)
                .map(IncomeData::getIncome)
                .findFirst()
                .orElse(0);

        int userYearIncome = userIncome * 12;

        Car userCar = carService.getCarByUserId(userId);
        Long userCarPrice = (userCar == null) ? null : userCar.getPrice();

        double loanAmountByIncomes = getLoanAmountByIncomes(userYearIncome);
        double loanAmountByCar = getLoanAmountByCar(userCarPrice);
        return Math.max(loanAmountByCar, loanAmountByIncomes);

    }

    private double getLoanAmountByIncomes(int userIncome) {
        double loanAmount = 0;
        if (userIncome > MINIMAL_INCOME) {
            loanAmount = userIncome * MAX_ANNUAL_INCOME_LOAN_AMOUNT;
            log.info("income loan amount:" + userIncome + " * " + MAX_ANNUAL_INCOME_LOAN_AMOUNT + " = " + loanAmount);
        }
        return loanAmount;
    }

    private double getLoanAmountByCar(Long carPrice) {
        if (carPrice == null) {
            return 0;
        }
        double loanAmount = 0;
        if (carPrice > MINIMAL_CAR_PRICE) {
            loanAmount = carPrice * MAX_CAR_PRICE_LOAN_AMOUNT;
            log.info("car loan amount:" + carPrice + " * " + MAX_CAR_PRICE_LOAN_AMOUNT + " = " + loanAmount);
        }
        return loanAmount;
    }
}
