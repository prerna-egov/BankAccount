package com.bank.account.repository;

import com.bank.account.model.Account;

public interface AccountRepository {

    int createAccount (Account accountInfo);

    Account getAccount(Account account);
}
