package com.reward.demo.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Reward {

    Long customerId;

    LocalDate startDate;
    LocalDate endDate;
}
