/* CityClientTest.java
 * 
 * Copyright (c) 2014 Qunar.com. All Rights Reserved. */
package com.qunar.fresh2017.cityclient;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;

/**
 * @author bin.qiao
 */
public class CityClientTest {

    @Test
    public void testCityIdFor() {
        CityClient cityClient = build();
        Assert.assertEquals(cityClient.getCityIdFor("beijing_city"), 1);
    }

    @Test
    public void testCityUrlFor() {
        CityClient cityClient = build();
        Assert.assertEquals(cityClient.getCityUrlFor(200), "xiamen");
    }

    @Test
    public void testProvince() {
        CityClient cityClient = build();
        Assert.assertEquals(cityClient.getProvince(10), new CityClient.Province(10, "云南"));
    }

    @Test
    public void testProvinceFor() {
        CityClient cityClient = build();
        Assert.assertEquals(cityClient.getProvinceFor(101), new CityClient.Province(10, "云南"));
    }

    @Test
    public void testCity() {
        CityClient cityClient = build();
        Assert.assertEquals(cityClient.getCity(2), new CityClient.City(2, "chongqing_city", "重庆"));
    }

    @Test
    public void testCities() {
        CityClient cityClient = build();
        ImmutableSet<CityClient.City> cities = cityClient.getCities(10);
        Assert.assertEquals(cities.size(), 3);
        Assert.assertTrue(cities.contains(new CityClient.City(102, "kumming", "昆明")));
    }

    @Test
    public void testProvinces() {
        CityClient cityClient = build();
        ImmutableSet<CityClient.Province> provinces = cityClient.getProvinces();
        Assert.assertEquals(provinces.size(), 4);
        Assert.assertTrue(provinces.contains(new CityClient.Province(1, "北京")));
    }

    private CityClient build() {
        return CityClient
                .create()
                .cityEntry(
                        new CityClient.CityEntry().setCityId(1).setCityName("北京").setCityUrl("beijing_city")
                                .setProvinceId(1).setProvinceName("北京"))
                .cityEntry(
                        new CityClient.CityEntry().setCityId(100).setCityName("丽江").setCityUrl("lijiang")
                                .setProvinceId(10).setProvinceName("云南"))
                .cityEntry(
                        new CityClient.CityEntry().setCityId(101).setCityName("大理").setCityUrl("dali")
                                .setProvinceId(10).setProvinceName("云南"))
                .cityEntry(
                        new CityClient.CityEntry().setCityId(102).setCityName("昆明").setCityUrl("kumming")
                                .setProvinceId(10).setProvinceName("云南"))
                .cityEntries(
                        Lists.newArrayList(
                                new CityClient.CityEntry().setCityId(200).setCityName("厦门").setCityUrl("xiamen")
                                        .setProvinceId(20).setProvinceName("福建"),
                                new CityClient.CityEntry().setCityId(201).setCityName("福州").setCityUrl("fuzhou_fujian")
                                        .setProvinceId(20).setProvinceName("福建")))
                .cityEntry(
                        new CityClient.CityEntry().setCityId(2).setCityName("重庆").setCityUrl("chongqing_city")
                                .setProvinceId(2).setProvinceName("重庆")).build();
    }
}
