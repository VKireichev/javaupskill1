package model.score;

import model.annotation.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import model.account.*;
import model.money.*;

@OperationLimitATM(limit = 3)
public class Atm implements AtmInterface {
    private int operLimit;
    private int currentOpers;
    private boolean operLimitToggl = true;

    public Atm(int operLimit, int currentOpers) {
        this.operLimit = operLimit;
        this.currentOpers = currentOpers;
        Class<?> thisClass = this.getClass();
        for (Annotation annotation :
                thisClass.getAnnotations()) {
            if (annotation instanceof OperationLimitATM) {
                this.operLimit = ((OperationLimitATM) annotation).limit();
                this.operLimitToggl = true;
            }
        }
    }

    @Override
    public void getMoneyFromScore(Score score, Money money) {
        // For proxy test only.
        try {
            Thread.sleep(2500);
        }
        catch (Exception e) {}

        if (operLimitToggl && currentOpers >= operLimit) {
            System.out.println("The operation is rejected due to Limit ends!");
            return;
        }
        score.getMoney(money);
        currentOpers++;
    }

    @Override
    public void addMoneyToScore(Score score, Money money) {
        if (operLimitToggl && currentOpers >= operLimit) {
            System.out.println("The operation is rejected due to Limit ends!");
            return;
        }
        score.addMoney(money);
        currentOpers++;
    }

    public static void main(String[] args) {
        AtmInterface proxyAtm = (AtmInterface) Proxy.newProxyInstance(
                AtmInterface.class.getClassLoader(),
                new Class[]{AtmInterface.class},
                new AtmProxy(new Atm(4, 0)));

        Account account = new Account(
                new Principal("Valeriy", "Kireichev", "Mikhailovich", (short) 65), "login", "password");
        Money rur = new Money(10000, "RUR");
        Money usd = new Money(1000, "USD");
        CreditScore creditScore = new CreditScore(rur, account, 1001);
        DebetScore debetScore = new DebetScore(usd, account, 870);
        account.addScore(creditScore);
        account.addScore(debetScore);

        System.out.println("Initial score balances for: " + account);
         for(Score score : account.getScoreMap().values()) {
            System.out.println("   " + score.getClass().getName().substring(12) + " "  + score.getBalance().getValue() + " " +score.getBalance().getCurrency().getName());
        }
        System.out.println("");

        System.out.println("Get 300 USD from debit score \n");
        proxyAtm.getMoneyFromScore(debetScore, new Money(300, "USD"));

        System.out.println("Get 7000 RUR from Credit score\n ");
        proxyAtm.getMoneyFromScore(creditScore, new Money(7000, "RUR"));

        System.out.println("Add 1000 USD to debit score\n");
        proxyAtm.addMoneyToScore(debetScore, new Money(1000, "USD"));

        System.out.println("Add 5000 RUR to credit score\n");
        proxyAtm.addMoneyToScore(creditScore, new Money(5000, "RUR"));

        System.out.println("Final score balances for: " + account);
        for(Score score : account.getScoreMap().values()) {
            System.out.println("   " + score.getClass().getName().substring(12) + " "  + score.getBalance().getValue() + " " +score.getBalance().getCurrency().getName());
        }
    }

}

