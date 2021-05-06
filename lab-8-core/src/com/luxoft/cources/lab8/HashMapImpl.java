package com.luxoft.cources.lab8;

import java.util.ArrayList;
import java.util.Objects;

public class HashMapImpl<K, V> {
    private int capacity;
    private int numOfBuckets;
    private int size = 0;
    private ArrayList<Entry<K, V>>[] bucketArray;

    public HashMapImpl() {
        this(1024);
    }

    @SuppressWarnings("unchecked")
    public HashMapImpl(int capacity) {
        this.capacity = capacity;
        numOfBuckets = capacity / 16 + 1;            // The last bucket is for key = null

        bucketArray = new ArrayList[numOfBuckets];
        for (var i = 0; i < numOfBuckets; i++) {
            bucketArray[i] = new ArrayList<>(16);
        }
    }

    private int hashing(int hashCode) {
        // Except the null bucket
        return (hashCode > 0 ? hashCode : -hashCode) % (numOfBuckets - 1);
    }

    private ArrayList<Entry<K, V>> getBucket(K key) {
        if (key == null)
            return bucketArray[numOfBuckets - 1];   // the last bucket for null key
        return bucketArray[hashing(key.hashCode())];
    }

    private void makeResize(int newCapacity) {
        System.out.println("\nResizing from: " + capacity + " to: " + newCapacity + "\n");
        HashMapImpl<K, V> newHashMap = new HashMapImpl<>(newCapacity);
        for (var k = 0; k < numOfBuckets; k++) {
            ArrayList<Entry<K, V>> bucket = bucketArray[k];
            for (Entry<K, V> entry : bucket) {
                newHashMap.put(entry.getKey(), entry.getValue());
            }
        }
        capacity = newCapacity;
        numOfBuckets = newCapacity / 16 + 1;
        this.bucketArray = newHashMap.bucketArray;
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    public int size() {
        return size;
    }

    public boolean containsKey(K key) {
        ArrayList<Entry<K, V>> bucket = getBucket(key);
        for (Entry<K, V> entry : bucket) {
            if (Objects.equals(key, entry.getKey()))
                return true;
        }
        return false;
    }

    public boolean containsValue(V value) {
        for (ArrayList<Entry<K, V>> bucket : bucketArray) {
            for (Entry<K, V> entry : bucket) {
                if (Objects.equals(value, entry.getValue()))
                    return true;
            }
        }
        return false;
    }

    public V get(K key) {
        for (Entry<K, V> entry : getBucket(key)) {
            if (Objects.equals(key, entry.getKey())) {
                return entry.getValue();
            }
        }
        return null;
    }

    public V put(K key, V value) {
        ArrayList<Entry<K, V>> bucket = getBucket(key);
        for (Entry<K, V> entry : bucket) {
            if (Objects.equals(key, entry.getKey()))
                return entry.setValue(value);
        }
        bucket.add(new Entry<>(key, value));
        if (size++ > capacity) {
            makeResize(capacity * 4);
        }
        return null;
    }

    public V remove(K key) {
        ArrayList<Entry<K, V>> bucket = getBucket(key);
        for (var i = 0; i < bucket.size(); i++) {
            if (Objects.equals(key, bucket.get(i).getKey())) {
                var oldValue = bucket.get(i).getValue();
                bucket.remove(i);
                if (size-- < capacity / 8 && capacity > 256)
                    makeResize(capacity / 4);
                return oldValue;
            }
        }
        return null;
    }

    public String toString() {
        var result = new StringBuilder("\n");
        for (ArrayList<Entry<K, V>> bucket : bucketArray) {
            for (Entry<K, V> entry : bucket) {
                result.append("Key: ")
                        .append(entry.getKey())
                        .append("   Value: ")
                        .append(entry.getValue())
                        .append("\n");
            }
        }
        return result.toString();
    }
}
