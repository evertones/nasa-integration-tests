package org.evertones.nasaOpenApi.v1.tests;

import org.evertones.model.MarsPhotos;
import org.evertones.model.Photo;
import org.evertones.nasaOpenApi.v1.apiProperties.MarsRoverCamera;
import org.evertones.nasaOpenApi.v1.apiProperties.MarsRoverParameter;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class NasaOpenApiTest
    extends NasaOpenApiFixture {

    private static final Logger LOGGER = Logger.getLogger(NasaOpenApiTest.class.getName());

    /**
     * Method to get the first page of results from NASA Open API by "Curiosity" on 1000 sol.
     * The API can return pages with 25 items, not 10.
     *
     * Checks:
     *    - Response returns 25 items.
     *    - Response has only items with 1000 Martian sol.
     *    - Response does not have photos from cameras that are not available in "Curiosity"
     */
    @Test
    public void testGet10FirstPhotosByCuriosityOn1000Sol() {
        MarsPhotos marsPhotos = given()
                    .param(MarsRoverParameter.API_KEY.getValue(), getNasaApiKey())
                    .param(MarsRoverParameter.SOL.getValue(), "1000")
                    .param(MarsRoverParameter.PAGE.getValue(), "1")
                .when().log().all()
                .get(getMarsPhotosByCuriosityApiUrl().toString())
                .as(MarsPhotos.class);

        hasExpectedPageSize(marsPhotos.getPhotos());
        hasResponsesWithValue1000sol(marsPhotos.getPhotos());

        // Ensure that the response does not have photos taken by other cameras not available in "Curiosity"
        Set<String> cameras = marsPhotos.getPhotos().stream().map(photo -> photo.getCamera().getName()).collect(Collectors.toSet());
        cameras.stream().map(name -> String.format("Camera name: %s", name)).forEach(LOGGER::info);
        assertFalse(cameras.contains(MarsRoverCamera.PANCAM));
        assertFalse(cameras.contains(MarsRoverCamera.MINITES));
    }

    /**
     * Method to get the first page of results from NASA Open API by "Curiosity" using the earth_date with the equivalent values of 1000 sol.
     * The API can return pages with 25 items, not 10.
     *
     * Checks:
     *    - Response returns 25 items.
     *    - Response has only items with 1000 Martian sol.
     */
    @Test
    public void testGet10FirstPhotosByCuriosityOnEarthDateEqualsTo1000Sol() {
        MarsPhotos marsPhotos = given()
                .param(MarsRoverParameter.API_KEY.getValue(), getNasaApiKey())
                .param(MarsRoverParameter.EARTH_DATE.getValue(), EARTH_DATE_1000_SOL)
                .param(MarsRoverParameter.PAGE.getValue(), "1")
                .when().log().all()
                .get(getMarsPhotosByCuriosityApiUrl().toString())
                .as(MarsPhotos.class);

        hasExpectedPageSize(marsPhotos.getPhotos());
        hasResponsesWithValue1000sol(marsPhotos.getPhotos());

        // Ensure that the response has only photos taken on earth_date equals to 1000 Martian sol.
        LocalDate earthDate = LocalDate.parse(EARTH_DATE_1000_SOL, formatter);
        marsPhotos.getPhotos().stream().forEach(photo -> assertEquals(photo.getEarthDate(), earthDate));
    }

    /**
     * Method to get the first page of results from NASA Open API by "Curiosity" using 2 requests:
     *    - Request 1 is made with 1000 Martian sol.
     *    - Request 2 is made with the earth date equals to 1000 Martian sol.
     * The API can return pages with 25 items, not 10.
     *
     * Checks:
     *    - All photos in the response from "Request 1" are equal to the photos in "Response 2".
     */
    @Test
    public void testGet10FirstPhotosByCuriosityOn1000SolAndCompareWith10FirstPhotosByCuriosityOnEarthDate() {
        MarsPhotos marsPhotosOn1000sol = given()
                .param(MarsRoverParameter.API_KEY.getValue(), getNasaApiKey())
                .param(MarsRoverParameter.SOL.getValue(), "1000")
                .param(MarsRoverParameter.PAGE.getValue(), "1")
                .when().log().all()
                .get(getMarsPhotosByCuriosityApiUrl().toString())
                .as(MarsPhotos.class);

        MarsPhotos marsPhotosOnEarthDate = given()
                .param(MarsRoverParameter.API_KEY.getValue(), getNasaApiKey())
                .param(MarsRoverParameter.EARTH_DATE.getValue(), EARTH_DATE_1000_SOL)
                .param(MarsRoverParameter.PAGE.getValue(), "1")
                .when().log().all()
                .get(getMarsPhotosByCuriosityApiUrl().toString())
                .as(MarsPhotos.class);

        List<Photo> photosOn1000Sol   = marsPhotosOn1000sol.getPhotos();
        List<Photo> photosOnEarthDate = marsPhotosOnEarthDate.getPhotos();

        int index = 0;
        for (Photo photo: photosOn1000Sol) {
            assertTrue(photo.equals(photosOnEarthDate.get(index)));
            index++;
        }
    }

    /**
     * Method to get the first page of results from NASA Open API by "Curiosity" using 2 requests:
     *    - Request 1 is made with 1000 Martian sol.
     *    - Request 2 is made with the earth date equals to 1000 Martian sol.
     * The API can return pages with 25 items, not 10.
     *
     * Checks:
     *    - All photos in the response from "Request 1" are equal to the photos in "Response 2".
     */
    @Test
    public void testGetAmountOfPhotosFromCuriosityNotGreaterThan10TimesAmountFromOtherCameras() {
        MarsPhotos marsPhotosCuriosity = given()
                .param(MarsRoverParameter.API_KEY.getValue(), getNasaApiKey())
                .param(MarsRoverParameter.SOL.getValue(), "1000")
                .when().log().all()
                .get(getMarsPhotosByCuriosityApiUrl().toString())
                .as(MarsPhotos.class);

        MarsPhotos marsPhotosOpportunity = given()
                .param(MarsRoverParameter.API_KEY.getValue(), getNasaApiKey())
                .param(MarsRoverParameter.SOL.getValue(), "1000")
                .when().log().all()
                .get(getMarsPhotosByOpportunityApiUrl().toString())
                .as(MarsPhotos.class);

        MarsPhotos marsPhotosSpirit = given()
                .param(MarsRoverParameter.API_KEY.getValue(), getNasaApiKey())
                .param(MarsRoverParameter.SOL.getValue(), "1000")
                .when().log().all()
                .get(getMarsPhotosBySpiritApiUrl().toString())
                .as(MarsPhotos.class);

        Integer totalCuriosity   = marsPhotosCuriosity.getPhotos().size();
        Integer totalOpportunity = marsPhotosOpportunity.getPhotos().size();
        Integer totalSpirit      = marsPhotosSpirit.getPhotos().size();

        LOGGER.info(String.format("Total curiosity: %s", totalCuriosity));
        LOGGER.info(String.format("Total opportunity: %s", totalOpportunity));
        LOGGER.info(String.format("Total spirit: %s", totalSpirit));

        Integer totalOpportunitySpririt = totalOpportunity + totalSpirit;
        LOGGER.info(String.format("%s is not greater than %s", totalOpportunitySpririt, totalCuriosity));

        assertFalse((totalOpportunity + totalSpirit) > totalCuriosity);
    }

    private void hasExpectedPageSize(List<Photo> list) {
        assertEquals(list.size(), 25);
    }

    private void hasResponsesWithValue1000sol(List<Photo> list) {
        Set<Integer> sols = list.stream().map(photo -> photo.getSol()).collect(Collectors.toSet());
        assertTrue(sols.size() == 1);
        assertTrue(sols.stream().findFirst().get() == 1000);
    }

}
