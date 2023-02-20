package org.evertones.nasaOpenApi.v1.apiProperties;

public enum MarsRoverCamera {
    FHAZ("FHAZ"),
    RHAZ("RHAZ"),
    MAST("MAST"),
    CHEMCAM("CHEMCAM"),
    MAHLI("MAHLI"),
    MARDI("MARDI"),
    NAVCAM("NAVCAM"),
    PANCAM("PANCAM"),
    MINITES("MINITES");

    private final String value;

    private MarsRoverCamera(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
