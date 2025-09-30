package com.solvd.itcompany.employee;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class GenericDataStore<K, V> implements Iterable<V> {

    private final Map<K, V> data;

    public GenericDataStore() {
        this.data = new HashMap<>();
    }

    public void add(K key, V value) {
        data.put(key, value);
    }

    public V get(K key) {
        return data.get(key);
    }

    public int size() {
        return data.size();
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    @Override
    public Iterator<V> iterator() {
        return data.values().iterator();
    }

    public Set<Map.Entry<K, V>> getEntrySet() {
        return data.entrySet();
    }
}