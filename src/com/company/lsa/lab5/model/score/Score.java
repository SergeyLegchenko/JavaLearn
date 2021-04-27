package com.company.lsa.lab5.model.score;

import com.company.lsa.lab5.model.account.Account;
import com.company.lsa.lab5.model.money.Money;
import com.company.lsa.lab5.model.money.MoneyInterface;

public abstract class Score implements MoneyInterface {
    private Money balance;
    private Account owner;
    private Integer number;

    public Score(Money balance, Account owner, Integer number) {
        this.balance = balance;
        this.owner = owner;
        this.number = number;
    }

    public Money getBalance() {
        return balance;
    }

    public void setBalance(Money balance) {
        this.balance = balance;
    }

    public Account getOwner() {
        return owner;
    }

    public void setOwner(Account owner) {
        this.owner = owner;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Override
    public void addMoney(Money money) {
        double usdValueIn = money.getValue() * money.getCurrency().getUsdCource();
        double usdValueThis = this.balance.getValue() * this.balance.getCurrency().getUsdCource();
// Не понял, зачем при добавлении валюты проверяем, что столько же уже есть на счету??
//        if (usdValueThis < usdValueIn) {
//            System.out.println("You have no so much!");
//            return;
//        }

        // добавим специфичную проверку, которую могут переопределять наследующие классы
        if (checkBefore()) {
            this.balance.setValue((usdValueThis + usdValueIn) * this.balance.getCurrency().getUsdCource());
        } else {
            System.out.println("No check!");
            return;
        }
    }

    @Override
    public Money getMoney(double balanceLess) {
        // добавим проверку, что снимать более 30000 за раз низя.
        if (balanceLess > 30000) {
            throw new IllegalArgumentException("Wrong balance less!");
        }
        // добавим специфичную проверку, которую могут переопределять наследующие классы
        if (checkBefore()) {
            this.balance.setValue(this.balance.getValue() - balanceLess);
        } else {
            System.out.println("No check!");
            return this.balance;
        }
        return this.balance;
    }

    @Override
    public Money getMoneyWithoutLess() {
        return this.balance;
    }

    //сама специфичная проверка, которую могут переопределять наследующие классы
    public boolean checkBefore() {
        return true;
    }
}
