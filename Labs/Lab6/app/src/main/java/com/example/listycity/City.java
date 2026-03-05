package com.example.listycity;

import java.util.Objects;

/**
 * Represents a single city entry with its associated province.
 * Each City instance stores the name of a city along with the
 * province or territory it belongs to.
 *
 * @author Lab6
 * @version 1.0
 */
public class City implements Comparable<City> {

    private String name;
    private String province;

    /**
     * Constructs a City with the specified name and province.
     *
     * @param name     the name of the city (e.g., "Edmonton")
     * @param province the province or territory (e.g., "AB")
     * @throws NullPointerException if either argument is null
     */
    City(String name, String province) {
        this.name = name;
        this.province = province;
    }

    /**
     * Retrieves the name of this city.
     *
     * @return a {@link String} containing the city name
     */
    String getCityName() {
        return this.name;
    }

    /**
     * Retrieves the province or territory this city belongs to.
     *
     * @return a {@link String} containing the province name
     */
    String getProvinceName() {
        return this.province;
    }

    /**
     * Compares this city to another city alphabetically by name.
     * This ordering is used when sorting a collection of cities.
     *
     * @param other the {@link City} to compare against
     * @return a negative integer, zero, or a positive integer depending on
     *         whether this city's name is lexicographically less than,
     *         equal to, or greater than the other city's name
     */
    @Override
    public int compareTo(City other) {
        return this.name.compareTo(other.getCityName());
    }

    /**
     * Determines whether this city is equal to another object.
     * Two cities are considered equal when both their name and
     * province fields match exactly.
     *
     * @param obj the reference object to compare with
     * @return {@code true} if the given object represents an equivalent city;
     *         {@code false} otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof City)) {
            return false;
        }
        City other = (City) obj;
        return Objects.equals(this.name, other.name)
                && Objects.equals(this.province, other.province);
    }

    /**
     * Returns a hash code value for this city. The hash is computed
     * from both the name and province fields using {@link Objects#hash}.
     *
     * @return an integer hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, province);
    }
}