package com.stefania.my_bottles.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

public class Bottle {

    private long id;

    @NotBlank
    @Length(max = 250) // TODO agree these lengths
    private String name;

    @Length(max = 250)
    private String producer;

    public Bottle() {
        // TODO what happens if this doens't exist?
    }

    public Bottle(long id, String name, String producer) {
        this.id = id;
        this.name = name;
        this.producer = producer;
    }

    @JsonProperty
    public long getId() {
        return id;
    }

    @JsonProperty
    public String getName() {
        return name;
    }

    @JsonProperty
    public String getProducer() {
        return producer;
    }
}
