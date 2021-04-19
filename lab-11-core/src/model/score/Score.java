package model.score;

import model.account.Account;
import model.money.Money;
import model.money.MoneyInterface;
import model.annotation.*;
import java.util.*;
import java.lang.reflect.Method;
import java.lang.annotation.Annotation;

import java.lang.annotation.*;
import java.lang.reflect.*;

public abstract class Score implements MoneyInterface {
    private Money balance;
    private Account owner;
    private Integer number;
    private Map<String, Integer> methodConstraintMap = new HashMap<>();
    private Map<String, Integer> methodCallMap = new HashMap<>();

    protected Score(Money balance, Account owner, Integer number) {
        this.balance = balance;
        this.owner = owner;
        this.number = number;
        Class thisClass = this.getClass();
        for (Method method :
                thisClass.getDeclaredMethods()) {
            for (Annotation annotation :
                    method.getDeclaredAnnotations()) {
                if (annotation instanceof MethodLimit) {
                    int limit = ((MethodLimit) annotation).limit();
                    methodConstraintMap.put(method.getName(), limit);
                    methodCallMap.put(method.getName(), 0);
                }
            }
        }
    }

    protected void logIfneeded(String methodName) {
        Class thisClass = this.getClass();
        for (Annotation annotation :
                thisClass.getAnnotations()) {
            if (annotation instanceof Loggable) {
                System.out.println("We call method " + methodName);
            }
        }
    }

    protected boolean isMethodAvailableByOperLimit(String methodName) {
        if (methodConstraintMap.containsKey(methodName)) {
            int currentCalls = methodCallMap.get(methodName);
            int limitCalls = methodConstraintMap.get(methodName);

            if (currentCalls >= limitCalls) {
                return false;
            }
            currentCalls++;
            methodCallMap.put(methodName, currentCalls);
        }
        return true;
    }

    public Money getBalance() {
        logIfneeded("getBalance");
        return balance;
    }

    public void setBalance(Money balance) {
        logIfneeded("setBalance");
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
        if (!isMethodAvailableByOperLimit("addMoney")) {
            System.out.println("Method limit is over!");
        }
        if (!balance.getCurrency().getName().equals(money.getCurrency().getName())) {
            System.out.println("Operation impossible due to different valuta!");
            }
        balance.setValue(balance.getValue() + money.getValue());
     }

    public Money getMoney(Money money) {
        logIfneeded("getMoney");
        if (!isMethodAvailableByOperLimit("getMoney")) {
            System.out.println("Method limit is over!");
            return null;
        }
        if (!balance.getCurrency().getName().equals(money.getCurrency().getName())) {
            System.out.println("Operation impossible due to different valuta!");
        }
        else if (money.getValue() > 30000) {
            System.out.println("Wrong balance less!");
        }
        else if (balance.getValue() < money.getValue()) {
            System.out.println("You have no so much!");
        } else {
            balance.setValue(balance.getValue() - money.getValue());
        }
        return balance;
    }

    public Money getMoneyWithoutLess() {
        logIfneeded("getMoneyWithoutLess");
        if (!isMethodAvailableByOperLimit("getMoneyWithoutLess")) {
            System.out.println("Method limit is over!");
        }
        return this.balance;
    }
}
