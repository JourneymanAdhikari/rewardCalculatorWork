package com.reward.demo.service;

import com.reward.demo.model.Customer;
import com.reward.demo.model.Transaction;
import com.reward.demo.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class RewardService {
    @Autowired
    private TransactionRepository transactionRepository;

    public int calculateRewardPoints(Customer customer, LocalDate startDate, LocalDate endDate) {
        int totalRewardPoints = 0;
        try {
            //Next parse the date from the @RequestParam, specifying the TO type as a TemporalQuery:
            List<Transaction> transactions = transactionRepository.findByCustomerAndTransactionDateBetween(
                    customer, startDate, endDate);
            for (Transaction transaction : transactions) {
                BigDecimal amount = transaction.getAmount();
                int rewardPoints = 0;

                if (amount.compareTo(new BigDecimal("100")) > 0) {
                    rewardPoints += 2 * (amount.subtract(new BigDecimal("100")).intValue());
                    amount = new BigDecimal("100");
                }

                if (amount.compareTo(new BigDecimal("50")) >= 0) {
                    rewardPoints += (amount.subtract(new BigDecimal("50")).intValue());
                }

                totalRewardPoints += rewardPoints;
            }
        } catch (Exception e) {

        }
        return totalRewardPoints;
    }
}
