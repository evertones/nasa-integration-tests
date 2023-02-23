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

}
