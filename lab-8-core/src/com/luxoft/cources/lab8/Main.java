package com.luxoft.cources.lab8;

public class Main {
    /****  Test MyArrayList  ***/
    public static void main(String[] args) {

       // Create,fill up (check put() method) and print map
        HashMapImpl<String, Integer> map = new HashMapImpl<>();
        for (var i = 0; i < 20; i++) {
            String str = "Key" + i;
            map.put(str, i * 100);
        }
        System.out.println("Map with size=" + map.size());
        System.out.println(map + "\n\n");

        // Check get() method
        for (var i = 0; i < map.size(); i += 4) {
            String key = "Key" + i;
            System.out.println("map.get(" + key + ") = " + map.get(key));
        }

        // Check remove() method
        for (int i = map.size(); i >= 0; i -= 2) {
            String key = "Key" + i;
            map.remove(key);
        }
        System.out.println("Map with size=" + map.size());
        System.out.println(map + "\n");

        // null key and value test
        System.out.println("\n is null key: " + map.containsKey(null));
        System.out.println(" is null value: " + map.containsValue(null));
        map.put(null, -10000);
        map.put("NullValue", null);
        System.out.println("\n put(null, -10000) and put(\"NullValue\", null) are made.");
        System.out.println("\n is null key: " + map.containsKey(null));
        System.out.println(" is null value: " + map.containsValue(null));
        System.out.println(" value of null key: " +   map.get(null));
        System.out.println(" value of \"NullValue\" key: " +   map.get("NullValue"));
        System.out.println("\nMap with size=" + map.size());
        System.out.println(map + "\n");
    }

}
