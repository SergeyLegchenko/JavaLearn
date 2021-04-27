package com.company.lsa.lab5.model.score;

import com.company.lsa.lab5.model.account.Account;
import com.company.lsa.lab5.model.money.Money;

public class DebitScore extends Score {
    private final CreditScore creditScore;

    public DebitScore(Money balance, Account owner, Integer number, CreditScore creditScore) {
        super(balance, owner, number);
        this.creditScore = creditScore;
    }

    @Override
    public Money getMoney(double balanceLess) {
        // Проверка на снятие более 30000 вынесена в базовый класс.
        // Туда же вынесена спец проверка, которую мы переопределили в этом классе.
        // Остается проверить на снятие налички не превышающей баланс - счет то дебетовый.
        if (this.getMoneyWithoutLess().getValue() < balanceLess)
            throw new IllegalArgumentException("You have no so much!");

        return super.getMoney(balanceLess);
    }

    // вот спец. проверка, которую мы переопределили для обеспечения условия:
    // "Наличие кредитного счета с балансом менее минус 20 000 запрещает
    // работу с дебетовым счетом"
    @Override
    public boolean checkBefore() {
        // проверку делаем в текущей валюте, т.к. в задаче не указано иное
        if (this.creditScore.getMoneyWithoutLess().getValue() < -20000)
            throw new IllegalArgumentException("Credit score balance < -20000!");
        return true;
    }

}
