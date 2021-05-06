package com.luxoft.cources.lab8;

public class Entry<K, V> {
    private final K key;
    private V value;

    public Entry(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public V setValue(V value) {
        var oldValue = this.value;
        this.value = value;
        return oldValue;
    }

}
