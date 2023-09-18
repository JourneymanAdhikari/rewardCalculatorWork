package com.reward.demo.controller;

import com.reward.demo.model.Customer;
import com.reward.demo.repository.CustomerRepository;
import com.reward.demo.service.RewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/api/rewards")
public class RewardController {
    @Autowired
    private RewardService rewardService;

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<Integer> calculateRewardPoints(
            @PathVariable Long customerId,
            @RequestParam  String startDate,
            @RequestParam  String endDate
//            @RequestParam  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)  LocalDate startDate,
//            @RequestParam  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)  LocalDate endDate
    ) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // Parse the string into a LocalDate
        LocalDate localStartDate = LocalDate.parse(startDate, formatter);
        LocalDate localEndDate = LocalDate.parse(endDate, formatter);
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));

        int rewardPoints = rewardService.calculateRewardPoints(customer, localStartDate, localEndDate);
        return ResponseEntity.ok(rewardPoints);
    }
}
