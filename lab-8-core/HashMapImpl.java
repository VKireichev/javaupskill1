import sun.nio.cs.CharsetMapping;

import java.util.ArrayList;

public class HashMapImpl<K, V> {
    private int capacity;
    private int numOfBuckets;
    private int size = 0;
    private ArrayList<Entry> bucket;
    private ArrayList<Entry>[] bucketArray;

    public HashMapImpl() {
        this(1024);
    }

    public HashMapImpl(int capacity) {
        this.capacity = capacity;
        numOfBuckets = capacity / 16 + 1;            // The last bucket is for key = null
        bucketArray = new ArrayList[numOfBuckets];
        for (int i = 0; i < numOfBuckets; i++) {
            bucketArray[i] = new ArrayList<Entry>(16);
        }
    }

    private int Hashing(int hashCode) {
        hashCode = hashCode > 0 ? hashCode : - hashCode;
        int location = hashCode % (numOfBuckets - 1);   // Except the null bucket
        // System.out.println("Location: " + location);
        return location;
    }

    private ArrayList<Entry> getBucket(K key) {
        if (key == null)
            return bucketArray[numOfBuckets - 1];   // the last bucket for null key
        int bucketNumber = Hashing(key.hashCode());
        return bucketArray[bucketNumber];
    }

    private void makeResize(int newCapacity) {
        System.out.println("\nResizing from: " + capacity + " to: " + newCapacity + "\n");
        HashMapImpl newHashMap = new HashMapImpl(newCapacity);
        for (int k = 0; k < numOfBuckets; k++) {
            ArrayList<Entry> bucket = bucketArray[k];
            for (int i = 0; i < bucket.size(); i++) {
                newHashMap.put(bucket.get(i).getKey(), bucket.get(i).getValue());
            }
        }
        capacity = newCapacity;
        numOfBuckets = newCapacity / 16 + 1;
        this.bucketArray = newHashMap.bucketArray;
    }

    private boolean isEquals(Object obj1, Object obj2) {
        if (obj1 == null)
            return obj2 == null ? true : false;
        return obj1.equals(obj2);
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    public int size() {
        return size;
    }

    public boolean containsKey(K key) {
        ArrayList<Entry> bucket = getBucket(key);
        for (int i = 0; i < bucket.size(); i++) {
            if (isEquals(key, bucket.get(i).getKey()));
                return true;
        }
        return false;
    }

    public boolean containsValue(V value) {
        for (int k = 0; k < numOfBuckets; k++) {
            ArrayList<Entry> bucket = bucketArray[k];
            for (int i = 0; i < bucket.size(); i++) {
                if (isEquals(value, bucket.get(i).getValue()))
                    return true;
            }
        }
        return false;
    }

    public V get(K key) {
        ArrayList<Entry> bucket = getBucket(key);
        for (int i = 0; i < bucket.size(); i++) {
            if (isEquals(key, bucket.get(i).getKey())) {
                return (V) bucket.get(i).getValue();
            }
        }
        return null;
    }

    public V put(K key, V value) {
        ArrayList<Entry> bucket = getBucket(key);
        for (int i = 0; i < bucket.size(); i++) {
            if (isEquals(key, bucket.get(i).getKey()))
                return (V) bucket.get(i).setValue(value);
        }
        bucket.add(new Entry(key, value));
        if (size++ > capacity) {
            makeResize(capacity * 4);
        }
        return null;
    }

    public V remove(K key) {
        ArrayList<Entry> bucket = getBucket(key);
        for (int i = 0; i < bucket.size(); i++) {
            if (isEquals(key, bucket.get(i).getKey())) {
                V oldValue = (V) bucket.get(i).getValue();
                bucket.remove(i);
                if (size-- < capacity / 8 && capacity > 256)
                    makeResize(capacity / 4);
                return oldValue;
            }
        }
        return null;
    }

    public String toString() {
        String result = "\n";
        for (int k = 0; k < numOfBuckets; k++) {
            ArrayList<Entry> bucket = bucketArray[k];
            for (int i = 0; i < bucket.size(); i++) {
                result += "Key: " + bucket.get(i).getKey() + "   Value: " + bucket.get(i).getValue() + "\n";
            }
        }
        return result;
    }
}
