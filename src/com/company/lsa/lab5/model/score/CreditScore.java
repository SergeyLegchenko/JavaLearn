package com.company.lsa.lab5.model.score;

import com.company.lsa.lab5.model.account.Account;
import com.company.lsa.lab5.model.money.Money;

public class CreditScore extends Score {
    public CreditScore(Money balance, Account owner, Integer number) {
        super(balance, owner, number);
    }

    // Не нашел что переопределить для Кредитного счета.
    // Просто вызываем базовые методы.
    // Можно было этого вообще не делать, у нас все в абстактном классе описано,
    // т.е. он абстрактный только декларативно, а не по сути своей.
    @Override
    public void addMoney(Money money) {
        super.addMoney(money);
    }

    @Override
    public Money getMoney(double balanceLess) {
        return super.getMoney(balanceLess);
    }

    @Override
    public Money getMoneyWithoutLess() {
        return super.getMoneyWithoutLess();
    }

}
