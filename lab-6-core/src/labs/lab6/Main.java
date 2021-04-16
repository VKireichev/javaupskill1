package labs.lab6;

import java.util.Scanner;

public class Main {
    private static final Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        String report;
        int summ;
        Customer customer1 = new Customer("Mike"); // Create a new customer
        for (int i = 0; i < 5; i++) {
            if (!customer1.makePurchase(in)) {
                break;                            // Customer finished shopping
            }
        }
        // Print reports:
        report = customer1.getPurchaseList();
        if (!"".equals(report))
            System.out.println("\nProduct name list: \n" + report);
        summ = customer1.getTotalSum();
        if (summ > 0)
            System.out.println("Total summ: \n" + summ + "\n");
        report = customer1.getTopPurchases();
        if (!"".equals(report))
            System.out.println("Top products: \n" + report);
    }
}
