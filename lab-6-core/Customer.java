import java.util.*;

public class Customer {
    private final String name;
    private Map<String, CostCount> purchases;

    public Customer(String name) {
        this.name = name;
        purchases = new HashMap<String, CostCount>();
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
        String result = "";
        String product = "";
        Set<String> list = new TreeSet<>(purchases.keySet());
        while (true) {
            product = ((TreeSet<String>) list).pollFirst();
            if (product == null)
                break;
            result += product + "\n";
        }
        return result;
    }

    public int getTotalSumm() {
        int summ = 0;
        CostCount costCount;

        for (Iterator<String> i = purchases.keySet().iterator(); i.hasNext(); ) {
            String key = i.next();
            costCount = purchases.get(key);
            summ += costCount.getCost() * costCount.getCount();
        }
        return summ;
    }

    public String getTopPurchases() {
        Integer count;
        CostCount costCount;
        String result = "";
        SortedMap<Integer, String> sortedMap = new TreeMap<Integer, String>();

        for (Iterator<String> i = purchases.keySet().iterator(); i.hasNext(); ) {
            String key = i.next();
            costCount = purchases.get(key);
            count = costCount.getCount();                  // Put count productName pairs in sorted map
            sortedMap.put(count, key);
        }
        for (int i = 0; i < 3; i++) {                      // Get top 3 products from sorted TreeMap
            Map.Entry<Integer, String> entry = ((TreeMap<Integer, String>) sortedMap).pollLastEntry();
            if (entry == null)
                break;
            result += entry.getValue() + "\n";
        }

        return result;
    }

    public final class CostCount {
        private int cost;
        private int count;

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

        public void setCost(int cost) {
            this.cost = cost;
        }

        public void setCount(int count) {
            this.count = count;
        }
    }
}
