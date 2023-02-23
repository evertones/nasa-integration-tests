package org.evertones.nasaOpenApi.v1.tests;

import org.evertones.model.MarsPhotos;
import org.evertones.nasaOpenApi.v1.apiProperties.MarsRoverParameter;
import org.evertones.nasaOpenApi.v1.apiProperties.MarsRovers;
import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.logging.Logger;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertTrue;

public class NasaOpenApiOtherTest
        extends NasaOpenApiFixture {

    private static final Logger LOGGER = Logger.getLogger(NasaOpenApiOtherTest.class.getName());

    /**
     * Method to test a basic request with valid parameters.
     * The NASA Open API returns an HTTP status 200.
     */
    @Test
    public void testGetResponse200Ok() {
        given()
                .param(MarsRoverParameter.API_KEY.getValue(), getNasaApiKey())
                .param(MarsRoverParameter.SOL.getValue(), "1000")
                .param(MarsRoverParameter.PAGE.getValue(), "1")
                .when().log().all()
                .get(getMarsPhotosByCuriosityApiUrl().toString())
                .then()
                .statusCode(200);
    }

    /**
     * Method to test the request with an invalid URL.
     * The NASA Open API returns an HTTP status 404.
     */
    @Test
    public void testGetResponse404NotFound() throws URISyntaxException, MalformedURLException {

        String invalidURITemplate = "/mars-photos/api/vXYZ/rovers/%s/photos";
        URI invalidURI = new URI(String.format(invalidURITemplate, MarsRovers.CURIOSITY));
        URL url = getBaseUrl().toURI().resolve(invalidURI).toURL();

        given()
                .param(MarsRoverParameter.API_KEY.getValue(), getNasaApiKey())
                .param(MarsRoverParameter.SOL.getValue(), "1000")
                .param(MarsRoverParameter.PAGE.getValue(), "1")
                .when().log().all()
                .get(url.toString())
                .then()
                .statusCode(404);
    }

    /**
     * Method to test the request with an invalid value for the parameter `api_key`.
     * The NASA Open API returns an HTTP status 403.
     */
    @Test
    public void testGetResponse403Forbidden() {
        given()
                .param(MarsRoverParameter.API_KEY.getValue(), "everton")
                .param(MarsRoverParameter.SOL.getValue(), "1000")
                .param(MarsRoverParameter.PAGE.getValue(), "1")
                .when().log().all()
                .get(getMarsPhotosByCuriosityApiUrl().toString())
                .then()
                .statusCode(403);
    }

    /**
     * Method to test the request without a date parameter (either `sol=1000` or an `earth_date=<date>`).
     * The NASA Open API returns an empty object when this parameter is not sent.
     */
    @Test
    public void testGetResponseEmpty() {
        MarsPhotos marsPhotos = given()
                .param(MarsRoverParameter.API_KEY.getValue(), getNasaApiKey())
                .param(MarsRoverParameter.PAGE.getValue(), "1")
                .when().log().all()
                .get(getMarsPhotosByCuriosityApiUrl().toString())
                .as(MarsPhotos.class);

        assertTrue(marsPhotos.getPhotos().isEmpty());
    }

    /**
     * Method to test the request with an invalid value for the parameter `earth_date`.
     * The NASA Open API breaks and returns an HTTP status 500.
     */
    @Test
    public void testGetResponse500InvalidDate() {
        given()
                .param(MarsRoverParameter.API_KEY.getValue(), getNasaApiKey())
                .param(MarsRoverParameter.EARTH_DATE.getValue(), "2015-05-34")
                .param(MarsRoverParameter.PAGE.getValue(), "1")
                .when().log().all()
                .get(getMarsPhotosByCuriosityApiUrl().toString())
                .then()
                .statusCode(500);
    }

}
