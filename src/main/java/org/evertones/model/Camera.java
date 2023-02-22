package org.evertones.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Camera {

    Integer id;

    String name;

    @JsonProperty("rover_id")
    Integer roverId;

    @JsonProperty("full_name")
    String fullName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRoverId() {
        return roverId;
    }

    public void setRoverId(Integer roverId) {
        this.roverId = roverId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
