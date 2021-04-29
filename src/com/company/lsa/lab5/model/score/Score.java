package com.company.lsa.lab5.model.score;

import com.company.lsa.lab5.model.Loggable;
import com.company.lsa.lab5.model.account.Account;
import com.company.lsa.lab5.model.money.Money;
import com.company.lsa.lab5.model.money.MoneyInterface;

import java.lang.annotation.*;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public abstract class Score implements MoneyInterface {
    private Money balance;
    private Account owner;
    private Integer number;

    private Map<String, Integer> methodConstraintMap;
    private Map<String, Integer> methodCallMap;
    private boolean methodConstraintToggl;

    {
        methodConstraintMap = new HashMap<>();
        methodCallMap = new HashMap<>();
        methodConstraintToggl = false;
    }

    protected Score(Money balance, Account owner, Integer number) {
        this.balance = balance;
        this.owner = owner;
        this.number = number;

        Class<? extends Score> thisClass = this.getClass();
        for (Method method:
                thisClass.getDeclaredMethods()) {
            for (Annotation annotation:
                    method.getDeclaredAnnotations()) {
                if(annotation instanceof MethodLimit){
                    int limit = ((MethodLimit)annotation).limit();
                    methodConstraintMap.put(method.getName(), limit);
                    methodCallMap.put(method.getName(), 0);
                    methodConstraintToggl = true;
                }
            }
        }
    }

    public Money getBalance() {
        logIfneeded("getBalance");
        if(isMethodAvailableByOperLimit("getBalance")) return null;
        return balance;
    }

    public void setBalance(Money balance) {
        logIfneeded("setBalance");
        if(isMethodAvailableByOperLimit("setBalance")) return;
        this.balance = balance;
    }

    public Account getOwner() {
        logIfneeded("getOwner");
        return owner;
    }

    public void setOwner(Account owner) {
        logIfneeded("setOwner");
        this.owner = owner;
    }

    public Integer getNumber() {
        logIfneeded("getNumber");
        return number;
    }

    public void setNumber(Integer number) {
        logIfneeded("setNumber");
        this.number = number;
    }

    @Override
    public void addMoney(Money money) {
        logIfneeded("addMoney");
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
        }
    }

    @Override
    public Money getMoney(double balanceLess) {
        logIfneeded("getMoney");
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
        logIfneeded("getMoneyWithoutLess");
        return this.balance;
    }

    //сама специфичная проверка, которую могут переопределять наследующие классы
    public boolean checkBefore() {
        return true;
    }

    protected void logIfneeded(String methodName){
        Class<? extends Score> thisClass = this.getClass();
        for (Annotation annotation:
                thisClass.getAnnotations()) {
            if(annotation instanceof Loggable){
                System.out.println("We call method " + methodName);
            }
        }
    }

    protected boolean isMethodAvailableByOperLimit(String methodName){
        if(methodConstraintMap!=null && methodConstraintMap.containsKey(methodName)){
            int currentCalls = methodCallMap.get(methodName);
            int limitCalls = methodConstraintMap.get(methodName);
            if(currentCalls >= limitCalls){
                System.out.println("Method " + methodName+" limit is over!");
                return true;
            }
            currentCalls++;
            methodCallMap.put(methodName, currentCalls);
        }
        return false;
    }
}
