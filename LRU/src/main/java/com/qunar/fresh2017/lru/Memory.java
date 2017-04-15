package com.qunar.fresh2017.lru;

import java.util.Map;

/**
 * 内存
 * Created by shuai.lv on 2017/3/2.
 */
public class Memory<K,V>{
    private Map<K, V> memory; //内存表

    public Memory(Map<K, V> memory) {
        this.memory = memory;
    }

    public void put(K key, V value) {
        memory.put(key, value);
    }

    public V get(K key) {
        return memory.get(key);
    }

    @Override
    public String toString() {
        return "Memory" + memory.toString();
    }
}
