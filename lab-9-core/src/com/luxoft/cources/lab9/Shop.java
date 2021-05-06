package com.luxoft.cources.lab9;

import java.util.Scanner;
import java.util.TreeMap;

public class Shop {
    private Scanner in;
    private TreeMap<String, TreeMap<String, Integer>> clients;

    public Shop(Scanner in) {
        this.in = in;
        clients = new TreeMap<>();
    }

    public static void main(String[] args) {
        var shop = new Shop(new Scanner(System.in));
        shop.makePurchases();
        shop.printStatistic();
    }

    public void makePurchases() {
        while (true) {
            System.out.print("Enter: 'client_name product_name count' or 'q' to quit :> ");            // Print a prompt
            var inputString = in.nextLine();
            if ("q".equals(inputString)) {         // EXIT sign
                return;
            }
            inputString = inputString.trim();
            String[] parts = inputString.split(" +");
            if (parts.length != 3) {
                System.out.println("Wrong number of arguments! Retry!" + parts.length + " " + parts[0]);
                continue;
            }
            try {
                var clientName = parts[0];
                var productName = parts[1];
                var productCount = Integer.parseInt(parts[2]);
                updateClientsMap(clientName, productName, productCount);
            } catch (NumberFormatException e) {
                System.out.println("invalid number. " + e.getMessage() + " Retry!");
            } catch (Exception e) {
                System.out.println("Enexpected error. " + e.getMessage());
                return;
            }
        }
    }

    public void printStatistic() {
        TreeMap<String, Integer> client;
        for (String clientKey : clients.navigableKeySet()) {
            System.out.println(clientKey + ":");
            client = clients.get(clientKey);
            for (String productKey : client.navigableKeySet()) {
                int count = client.get(productKey);
                System.out.println(productKey + " " + count);
            }
        }
    }

    private void updateClientsMap(String clientName, String productName, int productCount) {
        clients.putIfAbsent(clientName, new TreeMap<>());
        TreeMap<String, Integer> client = clients.get(clientName);
        client.computeIfPresent(productName, (k, v) -> v + productCount);
        client.putIfAbsent(productName, productCount);
    }
}
