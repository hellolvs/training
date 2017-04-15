/* CityClient.java
 * 
 * Copyright (c) 2014 Qunar.com. All Rights Reserved. */
package com.qunar.fresh2017.cityclient;

import com.google.common.base.MoreObjects;
import com.google.common.collect.*;

import java.util.Map;

/**
 * * CityClient is used to query information about a city. <br/>
 * * A city can be identified by either a cityId or a cityUrl, and both of them are overall unique. Every city belongs
 * to a single province, i.e. there is an one to many relationship between provinces and cites
 */
public class CityClient {

    private ImmutableMap<Integer, City> cityMap;
    private ImmutableMap<Integer, Province> provinceMap;
    private ImmutableMap<Integer, Province> cityToProvinceMap;
    private ImmutableMultimap<Integer, City> provinceToCityMap;
    private ImmutableBiMap<Integer, String> cityIdAndUrlMap;


    public static Builder create() {
        return new Builder();
    }

    private CityClient(Builder builder) {
        this.cityMap = ImmutableMap.copyOf(builder.cityMap);
        this.provinceMap = ImmutableMap.copyOf(builder.provinceMap);
        this.cityToProvinceMap = ImmutableMap.copyOf(builder.cityToProvinceMap);
        this.provinceToCityMap = ImmutableSetMultimap.copyOf(builder.provinceToCityMap);
        this.cityIdAndUrlMap = ImmutableBiMap.copyOf(builder.cityIdAndUrlMap);
    }

    /**
     * get all cities under an province identified by the given provinceId
     *
     * @param provinceId used to identify the target province
     * @return the set of cities in the target province, or empty city if the target province doesn't exist
     */
    public ImmutableSet<City> getCities(int provinceId) {
        return ImmutableSet.copyOf(provinceToCityMap.get(provinceId));
    }

    /**
     * get all provinces in the CityClient
     */
    public ImmutableSet<Province> getProvinces() {
        return ImmutableSet.copyOf(provinceMap.values());
    }

    /**
     * query for the province a city belonging to
     *
     * @param cityId the id used to identify the city
     * @return the province that contains the city, or null if the city doesn't exist
     */
    public Province getProvinceFor(int cityId) {
        return cityToProvinceMap.get(cityId);
    }

    /**
     * find a province by its id
     *
     * @param provinceId the id used to query Province
     * @return the target province or null
     */
    public Province getProvince(int provinceId) {
        return provinceMap.get(provinceId);
    }

    /**
     * find a city by its id
     */
    public City getCity(int cityId) {
        return cityMap.get(cityId);
    }

    /**
     * get corresponding cityUrl for a cityId
     *
     * @return the corresponding cityUrl or null if not exists
     */
    public String getCityUrlFor(int cityId) {
        return cityIdAndUrlMap.get(cityId);
    }

    /**
     * get corresponding cityId for a city
     *
     * @return the corresponding cityId or -1 if not exists
     */
    public int getCityIdFor(String cityUrl) {
        return cityIdAndUrlMap.inverse().get(cityUrl);
    }

    public final static class Builder {

        private Map<Integer, City> cityMap = Maps.newHashMap();
        private Map<Integer, Province> provinceMap = Maps.newHashMap();
        private Map<Integer, Province> cityToProvinceMap = Maps.newHashMap();
        private Multimap<Integer, City> provinceToCityMap = HashMultimap.create();
        private BiMap<Integer, String> cityIdAndUrlMap = HashBiMap.create();

        private Builder() {
        }

        public Builder cityEntry(CityEntry cityEntry) {

            int cityId = cityEntry.getCityId();
            String cityUrl = cityEntry.getCityUrl();
            String cityName = cityEntry.getCityName();
            int provinceId = cityEntry.getProvinceId();
            String proveinceName = cityEntry.getProvinceName();
            City city = new City(cityId, cityUrl, cityName);
            Province province = new Province(provinceId, proveinceName);

            cityMap.put(cityId, city);
            provinceMap.put(provinceId, province);
            cityToProvinceMap.put(cityId, province);
            provinceToCityMap.put(provinceId, city);
            cityIdAndUrlMap.put(cityId, cityUrl);

            return this;
        }

        public Builder cityEntries(Iterable<CityEntry> cityEntries) {
            for (CityEntry cityEntry : cityEntries) {
                cityEntry(cityEntry);
            }
            return this;
        }

        public CityClient build() {
            return new CityClient(this);
        }
    }

    public static class CityEntry {

        private int provinceId;

        private String provinceName;

        private int cityId;

        private String cityUrl;

        private String cityName;

        public int getProvinceId() {
            return provinceId;
        }

        public CityEntry setProvinceId(int provinceId) {
            this.provinceId = provinceId;
            return this;
        }

        public String getProvinceName() {
            return provinceName;
        }

        public CityEntry setProvinceName(String provinceName) {
            this.provinceName = provinceName;
            return this;
        }

        public int getCityId() {
            return cityId;
        }

        public CityEntry setCityId(int cityId) {
            this.cityId = cityId;
            return this;
        }

        public String getCityUrl() {
            return cityUrl;
        }

        public CityEntry setCityUrl(String cityUrl) {
            this.cityUrl = cityUrl;
            return this;
        }

        public String getCityName() {
            return cityName;
        }

        public CityEntry setCityName(String cityName) {
            this.cityName = cityName;
            return this;
        }
    }

    public static class City {

        private final int cityId;

        private final String cityUrl;

        private final String name;

        public City(int cityId, String cityUrl, String name) {
            this.cityId = cityId;
            this.cityUrl = cityUrl;
            this.name = name;
        }

        public int getCityId() {
            return cityId;
        }

        public String getCityUrl() {
            return cityUrl;
        }

        public String getName() {
            return name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (!(o instanceof City))
                return false;

            City city = (City) o;

            if (cityId != city.cityId)
                return false;
            if (cityUrl != null ? !cityUrl.equals(city.cityUrl) : city.cityUrl != null)
                return false;
            if (name != null ? !name.equals(city.name) : city.name != null)
                return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = cityId;
            result = 31 * result + (cityUrl != null ? cityUrl.hashCode() : 0);
            result = 31 * result + (name != null ? name.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return MoreObjects.toStringHelper(this).add("cityId", cityId).add("cityUrl", cityUrl).add("name", name)
                    .toString();
        }
    }

    public static class Province {

        private final int provinceId;

        private final String name;

        public Province(int provinceId, String name) {
            this.provinceId = provinceId;
            this.name = name;
        }

        public int getProvinceId() {
            return provinceId;
        }

        public String getName() {
            return name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (!(o instanceof Province))
                return false;

            Province province = (Province) o;

            if (provinceId != province.provinceId)
                return false;
            if (name != null ? !name.equals(province.name) : province.name != null)
                return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = provinceId;
            result = 31 * result + (name != null ? name.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return MoreObjects.toStringHelper(this).add("provinceId", provinceId).add("name", name).toString();
        }
    }

}
