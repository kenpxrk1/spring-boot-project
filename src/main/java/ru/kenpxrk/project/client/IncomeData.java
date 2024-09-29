package ru.kenpxrk.project.client;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
public class IncomeData implements Serializable {
    private int id;
    private int income;
}
