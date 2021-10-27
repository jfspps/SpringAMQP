package com.example.springamqp.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Param implements Serializable {

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    public Param() {
    }

    public Param(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
