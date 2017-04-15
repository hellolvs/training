package com.qunar.fresh2017.lru;

import java.util.LinkedHashMap;
import java.util.Map;


/**
 * LRU缓存
 * Created by shuai.lv on 2017/3/1.
 */
public class LRU<K, V> {

    private final Map<K, V> cache;// 缓存
    private Memory<K, V> memory; //当前内存的引用
    private int visit; //访问次数
    private int miss; //丢失次数

    private LRU(Builder<K, V> builder) {
        cache = builder.cache;
        memory = builder.memory;
    }

    /*获取建造者*/
    public static <K, V> Builder<K, V> create(int capacity, Memory memory) {
        return new Builder<K, V>(capacity, memory);
    }

    /*增加缓存元素*/
    public void put(K key, V value) {
        synchronized (this) {
            cache.put(key, value);
        }
    }

    /*由内存载入缓存元素*/
    public void load(K key) {
        put(key, memory.get(key));
    }

    /*获取缓存元素*/
    public V get(K key) {
        synchronized (this) {
            visit++;
            V value = cache.get(key);
            if (value == null) { //缓存未命中，则由内存获取元素，并将其载入缓存
                miss++;
                load(key);
                value = memory.get(key);
            }
            return value;
        }
    }

    /*移除缓存元素*/
    public void remove(K key) {
        synchronized (this) {
            cache.remove(key);
        }
    }

    /*获取命中率*/
    public double getHitRate() {
        return (double) (visit - miss) / (double) visit;
    }

    public int size() {
        return cache.size();
    }

    @Override
    public String toString() {
        return "LRUcache" + cache.toString();
    }

    /*建造者*/
    public static class Builder<K, V> {

        private final int capacity; // 缓存最大容量
        private Memory<K, V> memory; //内存
        private Map<K, V> cache; // 缓存

        public Builder(int capacity, Memory<K, V> memory) {
            this.capacity = capacity;
            this.memory = memory;
        }


        public LRU<K, V> build() {
            if (capacity <= 0) {
                throw new IllegalArgumentException("capacity:" + capacity);
            }
            cache = new LRULinkedHashMap<K, V>(capacity);
            return new LRU<K, V>(this);
        }
    }

    /*重写LinkedHashMap，实现LRU缓存*/
    public static class LRULinkedHashMap<K, V> extends LinkedHashMap<K, V> {

        private static final long serialVersionUID = 1L;
        private static final float LOAD_FACTOR = 0.75f;// 负载因子
        private static final int INITIAL_CAPACITY = 16; //LinkedHashMap初始大小

        private int capacity;//定义缓存的容量

        LRULinkedHashMap(int capacity) {
            super(INITIAL_CAPACITY, LOAD_FACTOR, true);//调用LinkedHashMap的构造器，true表示按访问顺序存储
            this.capacity = capacity;//传入指定的缓存最大容量
        }

        //实现LRU的关键方法，如果map里面的元素个数大于了缓存最大容量，则删除链表的顶端元素（最近最少访问）
        @Override
        public boolean removeEldestEntry(Map.Entry<K, V> eldest) {
            return size() > capacity;
        }
    }
}
