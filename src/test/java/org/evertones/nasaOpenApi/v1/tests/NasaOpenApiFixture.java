package org.evertones.nasaOpenApi.v1.tests;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;


public class NasaOpenApiFixture {

    private static final String BASE_URL = "https://api.nasa.gov";
    private static final String MARS_ROVER_API_URI = "/mars-photos/api/v1/rovers/curiosity/photos";

    private Config config = ConfigFactory.load();

    private final String nasaApiKey = config.getString("nasa.openapi.key");

    private final URL baseUrl;

    private final URL marsPhotosApiUrl;


    public NasaOpenApiFixture() {
        try {
            baseUrl = new URL(BASE_URL);

            URI marsPhotosApiUri = new URI(MARS_ROVER_API_URI);
            marsPhotosApiUrl = baseUrl.toURI().resolve(marsPhotosApiUri).toURL();
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

    public URL getMarsPhotosApiUrl() {
        return marsPhotosApiUrl;
    }

}
