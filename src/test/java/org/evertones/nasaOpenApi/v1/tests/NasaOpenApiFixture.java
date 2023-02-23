package org.evertones.nasaOpenApi.v1.tests;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.evertones.nasaOpenApi.v1.apiProperties.MarsRovers;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.format.DateTimeFormatter;


public class NasaOpenApiFixture {

    private static final String BASE_URL_TEMPLATE = "https://api.nasa.gov";
    private static final String MARS_ROVER_API_URI_TEMPLATE = "/mars-photos/api/v1/rovers/%s/photos";

    public static final String EARTH_DATE_1000_SOL = "2015-05-30";

    public DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private Config config = ConfigFactory.load();

    private final String nasaApiKey = config.getString("nasa.openapi.key");

    private final URL baseUrl;

    private final URL marsPhotosByCuriosityApiUrl;

    private final URL marsPhotosByOpportunityApiUrl;

    private final URL marsPhotosBySpiritApiUrl;

    public NasaOpenApiFixture() {
        try {
            baseUrl = new URL(BASE_URL_TEMPLATE);

            URI marsPhotosByCuriosityApiUri = new URI(String.format(MARS_ROVER_API_URI_TEMPLATE, MarsRovers.CURIOSITY));
            URI marsPhotosByOpportunityApiUri = new URI(String.format(MARS_ROVER_API_URI_TEMPLATE, MarsRovers.OPPORTUNITY));
            URI marsPhotosBySpiritApiUri = new URI(String.format(MARS_ROVER_API_URI_TEMPLATE, MarsRovers.SPIRIT));

            marsPhotosByCuriosityApiUrl = baseUrl.toURI().resolve(marsPhotosByCuriosityApiUri).toURL();
            marsPhotosByOpportunityApiUrl = baseUrl.toURI().resolve(marsPhotosByOpportunityApiUri).toURL();
            marsPhotosBySpiritApiUrl = baseUrl.toURI().resolve(marsPhotosBySpiritApiUri).toURL();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public Config getConfig() {
        return config;
    }

    public String getNasaApiKey() {
        return nasaApiKey;
    }

    public URL getBaseUrl() {
        return baseUrl;
    }

    public URL getMarsPhotosByCuriosityApiUrl() {
        return marsPhotosByCuriosityApiUrl;
    }

    public URL getMarsPhotosByOpportunityApiUrl() {
        return marsPhotosByOpportunityApiUrl;
    }

    public URL getMarsPhotosBySpiritApiUrl() {
        return marsPhotosBySpiritApiUrl;
    }
}
