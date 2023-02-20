package org.evertones.nasaOpenApi.v1.apiProperties;

public enum MarsRoverEarthDateParameter {
    EARTH_DATE("earth_date"),
    CAMERA("camera"),
    PAGE("page"),
    API_KEY("api_key");

    private final String value;

    private MarsRoverEarthDateParameter(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
