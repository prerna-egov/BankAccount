package com.bank.account.repository;

import com.bank.account.model.Account;
import com.bank.account.model.Amount;

public interface AccountRepository {

    int createAccount (Account accountInfo);

    Account getAccountByAccountNumber(Amount amount);
}
