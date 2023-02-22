package org.evertones.nasaOpenApi.v1.tests;

import org.evertones.nasaOpenApi.v1.apiProperties.MarsRoverMartianSolParameter;
import org.evertones.model.MarsPhotos;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class NasaOpenApiTest
    extends NasaOpenApiFixture {

    @Test
    public void testGet10FirstPhotosByCuriosityOn1000Sol() {
        MarsPhotos marsPhotos = given()
                    .param(MarsRoverMartianSolParameter.API_KEY.getValue(), getNasaApiKey())
                    .param(MarsRoverMartianSolParameter.SOL.getValue(), "1000")
                    .param(MarsRoverMartianSolParameter.PAGE.getValue(), "1")
                .when().log().all()
                .get(getMarsPhotosApiUrl().toString())
                .as(MarsPhotos.class);

        marsPhotos.getPhotos().forEach(photo -> {
            System.out.println(photo.toString());
        });

        assertEquals(marsPhotos.getPhotos().size(), 25);
    }

}
