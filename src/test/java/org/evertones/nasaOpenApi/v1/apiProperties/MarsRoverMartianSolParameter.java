package org.evertones.nasaOpenApi.v1.apiProperties;

public enum MarsRoverMartianSolParameter {
    SOL("sol"),
    CAMERA("camera"),
    PAGE("page"),
    API_KEY("api_key");

    private final String value;

    private MarsRoverMartianSolParameter(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
