package com.bank.account.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.support.NullValue;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AccountService {

    @Autowired
    JdbcTemplate jdbcTemplate;

}
