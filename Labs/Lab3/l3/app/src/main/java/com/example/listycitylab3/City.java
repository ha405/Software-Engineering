package com.example.listycitylab3;

import java.io.Serializable;

/**
 * Represents a city model with a name and a province.
 * Implements Serializable to allow passing between fragments.
 */
public class City implements Serializable {
    private String name;
    private String province;

    // Default constructor might be useful for serialization, though not strictly needed if we always use the parameterized one
    public City(String name, String province){
        this.name = name;
        this.province = province;
    }

    // Getters and Setters
    public String getName(){
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvince(){
        return this.province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    // Optional: Override toString for debugging or simple list views (if needed later)
    @Override
    public String toString() {
        return this.name;
    }
}