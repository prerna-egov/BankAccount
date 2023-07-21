package com.bank.account.repository;

import com.bank.account.model.Account;
import com.bank.account.model.Amount;

public interface TransactionRepository {

    int creditAmount(Amount amount);

    int debitAmount(Amount amount);

}
