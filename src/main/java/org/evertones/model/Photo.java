package org.evertones.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

import java.time.LocalDate;


public class Photo {

    Integer id;

    Integer sol;

    Camera camera;

    @JsonProperty("img_src")
    String imgSrc;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonProperty("earth_date")
    LocalDate earthDate;

    Rover rover;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSol() {
        return sol;
    }

    public void setSol(Integer sol) {
        this.sol = sol;
    }

    public Camera getCamera() {
        return camera;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public LocalDate getEarthDate() {
        return earthDate;
    }

    public void setEarthDate(LocalDate earthDate) {
        this.earthDate = earthDate;
    }

    public Rover getRover() {
        return rover;
    }

    public void setRover(Rover rover) {
        this.rover = rover;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("photo: { \n");
        builder.append(String.format("  id: %s \n", this.id));
        builder.append(String.format("  sol: %s \n", this.sol));
//        builder.append(String.format("  camera: %s \n", this.camera));
        builder.append(String.format("  imgSrc: %s \n", this.imgSrc));
        builder.append(String.format("  earthDate: %s \n", this.earthDate));
//        builder.append(this.rover.toString());
        builder.append("}");

        return builder.toString();

    }
}
