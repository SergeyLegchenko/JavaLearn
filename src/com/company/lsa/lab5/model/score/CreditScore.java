package com.company.lsa.lab5.model.score;

import com.company.lsa.lab5.model.Loggable;
import com.company.lsa.lab5.model.account.Account;
import com.company.lsa.lab5.model.money.Money;

@Loggable
public class CreditScore extends Score {
    public CreditScore(Money balance, Account owner, Integer number) {
        super(balance, owner, number);
    }

}
