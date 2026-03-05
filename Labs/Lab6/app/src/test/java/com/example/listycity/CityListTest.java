package com.example.listycity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link CityList}.
 * Covers add, getCities, hasCity, delete, and countCities operations.
 */
public class CityListTest {

    private CityList cityList;

    @BeforeEach
    void setUp() {
        cityList = new CityList();
    }

    /**
     * Verifies that adding a city increases the collection size
     * and the city actually appears in the retrieved list.
     */
    @Test
    void addShouldIncreaseSize() {
        City edmonton = new City("Edmonton", "AB");
        cityList.add(edmonton);
        assertEquals(1, cityList.getCities().size());

        City calgary = new City("Calgary", "AB");
        cityList.add(calgary);
        assertEquals(2, cityList.getCities().size());
        assertTrue(cityList.getCities().contains(calgary));
    }

    /**
     * Verifies that inserting a duplicate city triggers
     * an {@link IllegalArgumentException}.
     */
    @Test
    void addDuplicateShouldThrow() {
        City winnipeg = new City("Winnipeg", "MB");
        cityList.add(winnipeg);

        assertThrows(IllegalArgumentException.class, () -> {
            cityList.add(new City("Winnipeg", "MB"));
        });
    }

    /**
     * Verifies that getCities returns the cities in
     * alphabetical (sorted) order.
     */
    @Test
    void getCitiesShouldReturnSortedOrder() {
        City vancouver = new City("Vancouver", "BC");
        City ottawa = new City("Ottawa", "ON");
        cityList.add(vancouver);
        cityList.add(ottawa);

        // Ottawa should come before Vancouver alphabetically
        assertEquals("Ottawa", cityList.getCities().get(0).getCityName());
        assertEquals("Vancouver", cityList.getCities().get(1).getCityName());
    }

    /**
     * Verifies that hasCity returns true when the city
     * exists in the list and false when it does not.
     * Uses equals/hashCode so a new object with the same
     * fields should still match.
     */
    @Test
    void hasCityShouldReturnTrueForExistingCity() {
        City saskatoon = new City("Saskatoon", "SK");
        cityList.add(saskatoon);

        // same fields, different object reference
        City lookup = new City("Saskatoon", "SK");
        assertTrue(cityList.hasCity(lookup));
    }

    @Test
    void hasCityShouldReturnFalseForMissingCity() {
        City halifax = new City("Halifax", "NS");
        cityList.add(halifax);

        City missing = new City("Montreal", "QC");
        assertFalse(cityList.hasCity(missing));
    }

    /**
     * Verifies that deleting a city actually removes it
     * from the collection and decreases the count.
     */
    @Test
    void deleteShouldRemoveCityFromList() {
        City toronto = new City("Toronto", "ON");
        City victoria = new City("Victoria", "BC");
        cityList.add(toronto);
        cityList.add(victoria);

        // sanity check before deletion
        assertEquals(2, cityList.countCities());

        cityList.delete(toronto);
        assertEquals(1, cityList.countCities());
        assertFalse(cityList.hasCity(toronto));
    }

    /**
     * Verifies that attempting to delete a city that
     * is not in the list throws an {@link IllegalArgumentException}.
     */
    @Test
    void deleteNonExistentCityShouldThrow() {
        City fredericton = new City("Fredericton", "NB");
        cityList.add(fredericton);

        City ghost = new City("Iqaluit", "NU");
        assertThrows(IllegalArgumentException.class, () -> {
            cityList.delete(ghost);
        });
    }

    /**
     * Verifies that countCities accurately reflects the
     * number of entries after adds and deletes.
     */
    @Test
    void countCitiesShouldTrackCollectionSize() {
        assertEquals(0, cityList.countCities());

        cityList.add(new City("Regina", "SK"));
        assertEquals(1, cityList.countCities());

        cityList.add(new City("Whitehorse", "YT"));
        assertEquals(2, cityList.countCities());

        cityList.delete(new City("Regina", "SK"));
        assertEquals(1, cityList.countCities());
    }
}