package com.qunar.fresh2017.lru;

import com.google.common.collect.Maps;
import junit.framework.TestCase;

import java.util.Map;

public class LRUTest extends TestCase {
    public static void main(String[] args) {

        Memory memory = initial();
        System.out.println("当前内存为:" + memory.toString());
        int capacity = 5;
        LRU lru = LRU.create(capacity,memory).build();
        System.out.println("缓存初始化，容量为:" + capacity);
        lru.load("a");
        System.out.println("载入a:" + lru.toString());
        lru.load("b");
        System.out.println("载入b:" + lru.toString());
        lru.load("c");
        System.out.println("载入c:" + lru.toString());
        lru.load("d");
        System.out.println("载入d:" + lru.toString());
        lru.load("e");
        System.out.println("载入e:" + lru.toString());

        Object a = lru.get("a");
        System.out.println("访问a:" + lru.toString() + " a=" + a);
        System.out.println("缓存命中率为："+lru.getHitRate());

        lru.load("f");
        System.out.println("载入f:" + lru.toString());

        Object b = lru.get("b");
        System.out.println("访问b:" + lru.toString() + " b=" + b);
        System.out.println("缓存命中率为："+lru.getHitRate());
    }

    public static Memory<String,Integer> initial(){
        Map<String,Integer> map = Maps.newLinkedHashMap();
        map.put("a",1);
        map.put("b",2);
        map.put("c",3);
        map.put("d",4);
        map.put("e",5);
        map.put("f",6);
        map.put("g",7);
        return new Memory(map);
    }
}