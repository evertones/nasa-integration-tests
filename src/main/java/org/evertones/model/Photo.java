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
        builder.append(String.format("  imgSrc: %s \n", this.imgSrc));
        builder.append(String.format("  earthDate: %s \n", this.earthDate));
        builder.append("}");

        return builder.toString();
    }

    @Override
    public boolean equals(Object obj) {
        Photo photo = obj instanceof Photo ? (Photo) obj : null;

        Boolean equalProperties = this.getId().equals(photo.getId())
                && this.getSol().equals(photo.getSol())
                && this.getEarthDate().equals(photo.getEarthDate())
                && this.getImgSrc().equals(photo.getImgSrc());

        Camera camera1 = this.getCamera();
        Camera camera2 = photo.getCamera();
        Boolean equalCamera = camera1.getId().equals(camera2.getId())
                && camera1.getName().equals(camera2.getName())
                && camera1.getFullName().equals(camera2.getFullName())
                && camera1.getRoverId().equals(camera2.getRoverId());

        Rover rover1 = this.getRover();
        Rover rover2 = photo.getRover();
        Boolean equalRover = rover1.getId().equals(rover2.getId())
                && rover1.getName().equals(rover2.getName())
                && rover1.getStatus().equals(rover2.getStatus())
                && rover1.getLandingDate().equals(rover2.getLandingDate())
                && rover1.getLaunchDate().equals(rover2.getLaunchDate());

        return equalProperties && equalCamera && equalRover;
    }
}
