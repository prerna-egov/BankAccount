package com.bank.account.repository;

import com.bank.account.model.Account;
import com.bank.account.model.Amount;
import com.bank.account.service.AccountService;
import com.sun.org.apache.xml.internal.dtm.Axis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class JdbcRepository implements AccountRepository, UserRepository, TransactionRepository{

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    AccountService accountService;
    @Override
    public int createAccount(Account accountInfo) {
        return jdbcTemplate.update("INSERT INTO accounts ( accountBalance, accountType, username, userEmail, contactNumber) VALUES( ?, ?, ?, ?, ?)",
                 accountInfo.getAccountBalance(), accountInfo.getAccountType(), accountInfo.getUsername(), accountInfo.getUserEmail(), accountInfo.getContactNumber());
    }

    @Override
    public Account getAccount(Account account) {
        try {
            Account account1 = jdbcTemplate.queryForObject("SELECT * FROM accounts WHERE accountNumber = ?",
                    BeanPropertyRowMapper.newInstance(Account.class), account.getAccountNumber());
            return account1;
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

    @Override
    public int updateUserInfo(Account accountInfo) {
        StringBuilder queryBuilder = new StringBuilder("UPDATE accounts SET ");

        List<String> updateFields = new ArrayList<>();
        List<Object> queryParams = new ArrayList<>();

        if (accountInfo.getUsername() != null) {
            updateFields.add("username = ?");
            queryParams.add(accountInfo.getUsername());
        }
        if (accountInfo.getUserEmail() != null) {
            updateFields.add("userEmail = ?");
            queryParams.add(accountInfo.getUserEmail());
        }
        if (accountInfo.getContactNumber() != null) {
            updateFields.add("contactNumber = ?");
            queryParams.add(accountInfo.getContactNumber());
        }

        queryBuilder.append(String.join(", ", updateFields));
        queryBuilder.append(" WHERE accountNumber = ?");

        queryParams.add(accountInfo.getAccountNumber());

        Object[] queryParamsArr = queryParams.toArray();

        return jdbcTemplate.update(queryBuilder.toString(), queryParamsArr);
    }

    @Override
    public int creditAmount(Amount amount) {
        return jdbcTemplate.update("UPDATE accounts SET accountBalance = accountBalance + ? WHERE accountNumber = ?",
                amount.getAmount() ,amount.getAccountNumber());
    }

    @Override
    public int debitAmount(Amount amount) {
        return jdbcTemplate.update("UPDATE accounts SET accountBalance = accountBalance - ? WHERE accountNumber = ?",
                amount.getAmount() ,amount.getAccountNumber());
    }


}
