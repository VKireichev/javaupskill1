package com.luxoft.lab11.model.score;

import com.luxoft.lab11.model.account.Account;
import com.luxoft.lab11.model.annotation.Loggable;
import com.luxoft.lab11.model.annotation.MethodLimit;
import com.luxoft.lab11.model.money.Money;
import com.luxoft.lab11.model.money.MoneyInterface;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public abstract class Score implements MoneyInterface {
    private static final String LIMIT_IS_OVER_MSG = "Method limit is over!";
    private final Map<String, Integer> methodConstraintMap = new HashMap<>();
    private final Map<String, Integer> methodCallMap = new HashMap<>();
    private Money balance;
    private Account owner;
    private Integer number;

    protected Score(Money balance, Account owner, Integer number) {
        this.balance = balance;
        this.owner = owner;
        this.number = number;
        for (Method method : this.getClass().getDeclaredMethods()) {
            MethodLimit annotation = method.getDeclaredAnnotation(MethodLimit.class);
            if (annotation != null) {
                int limit = annotation.limit();
                methodConstraintMap.put(method.getName(), limit);
                methodCallMap.put(method.getName(), 0);
            }
        }
    }

    protected void logIfNeeded(String methodName) {
        Loggable annotation = this.getClass().getAnnotation(Loggable.class);
        if (annotation != null) {
            System.out.println("We call method " + methodName);
        }
    }

    protected boolean isNotMethodAvailableByOperationLimit(String methodName) {
        if (methodConstraintMap.containsKey(methodName)) {
            int currentCalls = methodCallMap.get(methodName);
            int limitCalls = methodConstraintMap.get(methodName);

            if (currentCalls >= limitCalls) {
                return true;
            }
            currentCalls++;
            methodCallMap.put(methodName, currentCalls);
        }
        return false;
    }

    public Money getBalance() {
        logIfNeeded("getBalance");
        return balance;
    }

    public void setBalance(Money balance) {
        logIfNeeded("setBalance");
        this.balance = balance;
    }

    public Account getOwner() {
        logIfNeeded("getOwner");
        return owner;
    }

    public void setOwner(Account owner) {
        logIfNeeded("setOwner");
        this.owner = owner;
    }

    public Integer getNumber() {
        logIfNeeded("getNumber");

        return number;
    }

    public void setNumber(Integer number) {
        logIfNeeded("setNumber");
        this.number = number;
    }

    @Override
    public void addMoney(Money money) {
        logIfNeeded("addMoney");
        if (isNotMethodAvailableByOperationLimit("addMoney")) {
            System.out.println(LIMIT_IS_OVER_MSG);
        }
        if (!balance.getCurrency().getName().equals(money.getCurrency().getName())) {
            System.out.println("Operation impossible due to incorrect currency!");
        }
        balance.setValue(balance.getValue() + money.getValue());
    }

    public Money getMoney(Money money) {
        logIfNeeded("getMoney");
        if (isNotMethodAvailableByOperationLimit("getMoney")) {
            System.out.println(LIMIT_IS_OVER_MSG);
            return null;
        }
        if (!balance.getCurrency().getName().equals(money.getCurrency().getName())) {
            System.out.println("Operation impossible due to different currency!");
        } else if (money.getValue() > 30000) {
            System.out.println("Wrong balance less!");
        } else if (balance.getValue() < money.getValue()) {
            System.out.println("You have no so much!");
        } else {
            balance.setValue(balance.getValue() - money.getValue());
        }
        return balance;
    }

    public Money getMoneyWithoutLess() {
        logIfNeeded("getMoneyWithoutLess");
        if (isNotMethodAvailableByOperationLimit("getMoneyWithoutLess")) {
            System.out.println(LIMIT_IS_OVER_MSG);
        }
        return this.balance;
    }
}
