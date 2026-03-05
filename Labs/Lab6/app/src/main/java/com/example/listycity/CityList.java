package com.example.listycity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Maintains an ordered collection of {@link City} objects.
 * Provides operations to insert, remove, query, and count
 * cities while enforcing uniqueness constraints.
 *
 * @author Lab6
 * @version 1.0
 */
public class CityList {

    private final List<City> cities = new ArrayList<>();

    /**
     * Inserts a new city into the collection. Duplicate entries
     * are not permitted; attempting to add a city that already
     * exists will result in an exception.
     *
     * @param city the {@link City} to insert
     * @throws IllegalArgumentException if the city is already present
     */
    public void add(City city) {
        if (cities.contains(city)) {
            throw new IllegalArgumentException("Duplicate city: " + city.getCityName());
        }
        cities.add(city);
    }

    /**
     * Returns a sorted snapshot of the current city collection.
     * The list is sorted in natural (alphabetical) order based
     * on city names.
     *
     * @return a {@link List} of {@link City} objects in sorted order
     */
    public List<City> getCities() {
        List<City> sortedCopy = new ArrayList<>(cities);
        Collections.sort(sortedCopy);
        return sortedCopy;
    }

    /**
     * Checks whether a given city exists within this collection.
     * Comparison is performed using the {@link City#equals(Object)}
     * method, meaning both name and province must match.
     *
     * @param city the {@link City} to look for
     * @return {@code true} if the city is found; {@code false} otherwise
     */
    public boolean hasCity(City city) {
        return cities.contains(city);
    }

    /**
     * Removes a city from the collection. If the specified city
     * is not found, an {@link IllegalArgumentException} is thrown
     * to signal the caller.
     *
     * @param city the {@link City} to remove
     * @throws IllegalArgumentException if the city does not exist in the list
     */
    public void delete(City city) {
        if (!hasCity(city)) {
            throw new IllegalArgumentException("City not found: " + city.getCityName());
        }
        cities.remove(city);
    }

    /**
     * Returns the total number of cities currently stored
     * in this collection.
     *
     * @return the count of cities as an {@code int}
     */
    public int countCities() {
        return cities.size();
    }
}