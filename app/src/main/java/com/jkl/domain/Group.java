package com.jkl.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Aguiar on 12/07/2015.
 */
public class Group {


    /** The name. */
    @JsonProperty("nome")
    private String name;


    /** The ID. */
    @JsonProperty("id")
    private String id;

    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name.
     *
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the id.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the id.
     *
     * @param id the new id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets the last name.
     *
     * @return the last name
     */
}
