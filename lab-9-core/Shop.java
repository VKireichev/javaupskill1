
import java.util.*;

public class Shop {
    private Scanner in;
    private TreeMap<String, TreeMap<String, Integer>> clients;

    public Shop(Scanner in) {
        this.in = in;
        clients = new TreeMap<String, TreeMap<String, Integer>>();
    }

    public void makePurchases() {
        String inputString = "";
        String clientName = "";
        String productName = "";
        Integer productCount = 0;

        while (true) {
            System.out.print("Enter: 'client_name product_name count' or 'q' to quit :> ");            // Print a prompt
            inputString = in.nextLine();
            if ("q".equals(inputString)) {         // EXIT sign
                return;
            }
            inputString = inputString.trim();
            String[] parts = inputString.split(" +");
            if (parts.length != 3) {
                System.out.println("Wrong number of arguments! Retry!" + parts.length + " " + parts[0]);
                inputString = "";
                continue;
            }
            try {
                clientName = parts[0];
                productName = parts[1];
                productCount = Integer.parseInt(parts[2]);
                updateClientsMap(clientName, productName, productCount);
            } catch (NumberFormatException e) {
                System.out.println("invalid number. " + e.getMessage() + " Retry!");
                inputString = "";
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
        TreeMap<String, Integer> client;
        if (!clients.containsKey(clientName)) {
            client = new TreeMap<String, Integer>();
            client.put(productName, productCount);
            clients.put(clientName, client);
        }
        else {
            client = clients.get(clientName);
            if (!client.containsKey(productName)) {
                client.put(productName, productCount);
            } else {
                int oldProductCount = client.get(productName);
                client.put(productName, productCount + oldProductCount);
            }
        }
    }

    public static void main(String[] args) {
        Shop shop = new Shop(new Scanner(System.in));
        shop.makePurchases();
        shop.printStatistic();
    }
}
