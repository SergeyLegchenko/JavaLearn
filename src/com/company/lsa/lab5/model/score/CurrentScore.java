package com.company.lsa.lab5.model.score;

import com.company.lsa.lab5.model.account.Account;
import com.company.lsa.lab5.model.money.Money;

public class CurrentScore extends Score {
    private final DebitScore debitScore;

    public CurrentScore(Money balance, Account owner, Integer number, DebitScore debitScore) {
        super(balance, owner, number);
        this.debitScore = debitScore;
    }

    public DebitScore getDebitScore() {
        return debitScore;
    }

    @Override
    public void addMoney(Money money) {
        super.addMoney(money);
        // реализуем условие :
        // "При пополнении текущего счета более чем на 1 000 000 за операцию- на
        //  дебетовый счет автоматически поступает +2 000"
        // т.к. валюта не указана, берем валюту счета, как для Текущего счета, так и для Дебетового
        // и валюты этих счетов могут быть разные
        if (money.getValue() > 1000000)
            debitScore.addMoney(new Money(2000, debitScore.getMoneyWithoutLess().getCurrency().getName()));
    }

}
