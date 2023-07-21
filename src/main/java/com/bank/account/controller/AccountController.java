package com.bank.account.controller;

import com.bank.account.model.Account;
import com.bank.account.model.Amount;
import com.bank.account.repository.AccountRepository;
import com.bank.account.repository.TransactionRepository;
import com.bank.account.repository.UserRepository;
import com.bank.account.service.AccountService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bank")
public class AccountController {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    AccountService accountService;

    @PostMapping("/accounts")
    public ResponseEntity<String> createAccount(@RequestBody Account account) {
        try {
            accountRepository.createAccount(account);
            return new ResponseEntity<>("account was created successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/account")
    public ResponseEntity<Account> getAccount(@RequestBody Account account){
        try{
            Account account1 = accountRepository.getAccount(account);
            return new ResponseEntity<>(account1, HttpStatus.FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping("/update/user")
    public ResponseEntity<String> updateAccount(@RequestBody Account account) {
        try {
            userRepository.updateUserInfo(account);
            return new ResponseEntity<>("User info was updated successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/credit")
    public ResponseEntity<String> creditAmount(@RequestBody Amount amount ){
        try {
            transactionRepository.creditAmount(amount);
            return new ResponseEntity<>("Your account to credited with " + amount.getAmount() +"  .", HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/debit")
    public ResponseEntity<String> debitAmount(@RequestBody Amount amount){
        try {
            transactionRepository.debitAmount(amount);
            return new ResponseEntity<>("Your account to debited to " + amount.getAmount() +"  .", HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
