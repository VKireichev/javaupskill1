package labs.lab6;

import java.util.*;

public class Customer {
    private final String name;
    private final Map<String, CostCount> purchases;

    public Customer(String name) {
        this.name = name;
        purchases = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public boolean makePurchase(Scanner in) {
        Purchase purchase = new Purchase(in);
        if (purchase.getStatus() != 0)          // Some problem. May be end of hopping.
            return false;
        String productName = purchase.getProductName();
        int cost = purchase.getProductCost();
        int count = purchase.getProductCount();
        purchases.put(productName, new CostCount(cost, count));
        return true;
    }

    public String getPurchaseList() {
        StringBuilder result = new StringBuilder();
        for (String product : purchases.keySet()) {
            result.append(product)
                    .append("\n");
        }
        return result.toString();
    }

    public int getTotalSum() {
        return purchases.values().stream()
                .mapToInt(c -> c.getCost() * c.getCount())
                .sum();
    }

    public String getTopPurchases() {
        int count;
        CostCount costCount;
        StringBuilder result = new StringBuilder();
        TreeMap<Integer, String> map = new TreeMap<>();

        for (String key : purchases.keySet()) {
            costCount = purchases.get(key);
            count = costCount.getCount();                  // Put count productName pairs in sorted map
            map.put(count, key);
        }
        for (int i = 0; i < 3; i++) {                      // Get top 3 products from sorted TreeMap
            Map.Entry<Integer, String> entry = map.pollLastEntry();
            if (entry == null)
                break;
            result.append(entry.getValue())
                    .append("\n");
        }

        return result.toString();
    }

    //Можно было использовать стандартный Map<Integer, Integer> вместо CostCount
    private static final class CostCount {
        private final int cost;
        private final int count;

        CostCount(int cost, int count) {
            this.cost = cost;
            this.count = count;
        }

        public int getCost() {
            return cost;
        }

        public int getCount() {
            return count;
        }
    }
}
