package com.company.lsa.lab5.model.score;

import com.company.lsa.lab5.model.account.Account;
import com.company.lsa.lab5.model.money.Money;

public class CurrentScore extends Score{
    private DebetScore debetScore;

    public CurrentScore(Money balance, Account owner, Integer number, DebetScore debetScore) {
        super(balance, owner, number);
        this.debetScore = debetScore;
    }

    @Override
    public void addMoney(Money money) {
        super.addMoney(money);
        // реализуем условие :
        // "При пополнении текущего счета более чем на 1 000 000 за операцию- на
        //  дебетовый счет автоматически поступает +2 000"
        // т.к. валюта не указана, берем валюту счета, как для Текущего счета, так и для Дебетового
        // и валюты этих счетов могут быть разные
        if (money.getValue()>1000000)
            debetScore.addMoney(new Money(2000, debetScore.getMoneyWithoutLess().getCurrency().getName()));
    }

    // остальные методы можно было не переопределять
    @Override
    public Money getMoney(double balanceLess) {
        return super.getMoney(balanceLess);
    }

    @Override
    public Money getMoneyWithoutLess() {
        return super.getMoneyWithoutLess();
    }
}
