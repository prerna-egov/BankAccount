package com.bank.account.service;

import com.bank.account.model.Account;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    //checks whether the entered contact number is valid or not
    boolean isValidNumber(String contactNumber) {
        return false;
    }

    //checks whether the entered email is valid or not
    boolean isValidEmail(String email){
        return false;
    }
}
