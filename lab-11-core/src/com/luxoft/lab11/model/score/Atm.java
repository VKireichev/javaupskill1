package com.luxoft.lab11.model.score;

import com.luxoft.lab11.model.account.Account;
import com.luxoft.lab11.model.account.Principal;
import com.luxoft.lab11.model.annotation.OperationLimitATM;
import com.luxoft.lab11.model.money.Money;

import java.lang.annotation.Annotation;
import java.lang.reflect.Proxy;

@OperationLimitATM(limit = 3)
public class Atm implements AtmInterface {
    private int operationLimit;
    private int currentOperationVolume;
    private boolean isOperationLimitDefined = false;

    public Atm(int operationLimit, int currentOperationVolume) {
        this.operationLimit = operationLimit;
        this.currentOperationVolume = currentOperationVolume;
        Class<?> thisClass = this.getClass();
        for (Annotation annotation :
                thisClass.getAnnotations()) {
            if (annotation instanceof OperationLimitATM) {
                this.operationLimit = ((OperationLimitATM) annotation).limit();
                this.isOperationLimitDefined = true;
            }
        }
    }

    public static void main(String[] args) {
        AtmInterface proxyAtm = (AtmInterface) Proxy.newProxyInstance(
                AtmInterface.class.getClassLoader(),
                new Class[]{AtmInterface.class},
                new AtmProxy(new Atm(4, 0)));

        Account account = new Account(
                new Principal("Valeriy", "Kireichev", "Mikhailovich", (short) 65),
                "login", "password");
        Money rur = new Money(10000, "RUR");
        Money usd = new Money(1000, "USD");

        CreditScore creditScore = new CreditScore(rur, account, 1001);
        DebitScore debitScore = new DebitScore(usd, account, 870);
        account.addScore(creditScore);
        account.addScore(debitScore);

        System.out.println("Initial score balances for: " + account);
        for (Score score : account.getScoreMap().values()) {
            System.out.println("   " + score.getClass().getName().substring(12)
                    + " " + score.getBalance().getValue()
                    + " " + score.getBalance().getCurrency().getName());
        }
        System.out.println();

        System.out.println("Get 300 USD from debit score \n");
        proxyAtm.getMoneyFromScore(debitScore, new Money(300, "USD"));

        System.out.println("Get 7000 RUR from Credit score\n ");
        proxyAtm.getMoneyFromScore(creditScore, new Money(7000, "RUR"));

        System.out.println("Add 1000 USD to debit score\n");
        proxyAtm.addMoneyToScore(debitScore, new Money(1000, "USD"));

        System.out.println("Add 5000 RUR to credit score\n");
        proxyAtm.addMoneyToScore(creditScore, new Money(5000, "RUR"));

        System.out.println("Final score balances for: " + account);
        for (Score score : account.getScoreMap().values()) {
            System.out.println("   " + score.getClass().getName().substring(12) + " " + score.getBalance().getValue() + " " + score.getBalance().getCurrency().getName());
        }
    }

    @Override
    public void getMoneyFromScore(Score score, Money money) {
        // For proxy test only.
        try {
            Thread.sleep(2500);
        } catch (InterruptedException e) {
            System.out.println("Interrupted!");
        }

        if (isOperationLimitDefined && currentOperationVolume >= operationLimit) {
            System.out.println("The operation is rejected due to Limit ends!");
            return;
        }
        score.getMoney(money);
        currentOperationVolume++;
    }

    @Override
    public void addMoneyToScore(Score score, Money money) {
        if (isOperationLimitDefined && currentOperationVolume >= operationLimit) {
            System.out.println("The operation is rejected due to Limit ends!");
            return;
        }
        score.addMoney(money);
        currentOperationVolume++;
    }

}

