package com.bank.account.model;

import lombok.*;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    private UUID id;
    private int accountNumber;
    private double accountBalance;
    private String accountType;
    private String username;
    private String userEmail;
    private String contactNumber;
    private long createdTime;

    public Account(double accountBalance, String accountType, String username, String userEmail, String contactNumber) {
        this.accountBalance = accountBalance;
        this.accountType = accountType;
        this.username = username;
        this.userEmail = userEmail;
        this.contactNumber = contactNumber;
    }
}
