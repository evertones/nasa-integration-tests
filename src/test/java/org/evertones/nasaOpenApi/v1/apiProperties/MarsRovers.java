package org.evertones.nasaOpenApi.v1.apiProperties;

public enum MarsRovers {
    CURIOSITY("curiosity"),
    OPPORTUNITY("opportunity"),
    SPIRIT("spirit");

    private final String value;

    private MarsRovers(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
