package org.evertones.nasaOpenApi.v1.tests;

import org.evertones.nasaOpenApi.v1.apiProperties.MarsRoverCamera;
import org.evertones.nasaOpenApi.v1.apiProperties.MarsRoverMartianSolParameter;
import org.junit.Test;

import java.net.HttpURLConnection;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.everyItem;
import static org.hamcrest.Matchers.hasSize;

public class NasaOpenApiTest
    extends NasaOpenApiFixture {

    @Test
    public void testGet10FirstPhotosByCuriosityOn1000Sol() {

        given()
            .param(MarsRoverMartianSolParameter.API_KEY.getValue(), getNasaApiKey())
            .param(MarsRoverMartianSolParameter.SOL.getValue(), "1000")
            .param(MarsRoverMartianSolParameter.PAGE.getValue(), "1")
        .when()
        .log().all()
        .get(getMarsPhotosApiUrl().toString())
        .then()
            .statusCode(HttpURLConnection.HTTP_OK);
    }

}
