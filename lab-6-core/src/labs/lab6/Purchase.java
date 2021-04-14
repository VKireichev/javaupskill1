package labs.lab6;

import java.util.Scanner;

public class Purchase {
    private final int status;
    private String productName;
    private Integer productCost;
    private Integer productCount;

    public Purchase(Scanner in) {
        status = makePurchase(in);
    }

    public int getStatus() {
        return status;
    }

    public String getProductName() {
        return productName;
    }

    public Integer getProductCost() {
        return productCost;
    }

    public Integer getProductCount() {
        return productCount;
    }

    private int makePurchase(Scanner in) {
        String inputString = "";
        while ("".equals(inputString)) {       // Read non empty input line.
            System.out.print("Enter: 'product_name cost count' or 'q' to quit :> ");            // Print a prompt
            inputString = in.nextLine();
            if ("q".equals(inputString)) {         // EXIT sign
                return -1;
            }
            inputString = inputString.trim();
            String[] parts = inputString.split(" +");
            if (parts.length != 3) {
                System.out.println("Wrong number of arguments! Retry!" + parts.length + " " + parts[0]);
                inputString = "";
                continue;
            }
            try {
                productName = parts[0];
                productCost = Integer.parseInt(parts[1]);
                productCount = Integer.parseInt(parts[2]);
            } catch (NumberFormatException e) {
                System.out.println("invalid number. " + e.getMessage() + " Retry!");
                inputString = "";
            }
        }
        return 0;
    }
}
