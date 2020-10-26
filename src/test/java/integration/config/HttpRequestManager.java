package integration.config;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

public class HttpRequestManager {

    private static final String APPLICATION_JSON = "application/json";

    private final WireMockServer wireMockServer = new WireMockServer(options().dynamicPort());
    private final CloseableHttpClient httpClient = HttpClients.createDefault();
    private HttpResponse currentHttpResponse;

    public HttpResponse getCurrentHttpResponse() {
        return currentHttpResponse;
    }

    public void executePostQuery(String urlPath, String body) throws IOException {
        wireMockServer.start();

        configureFor("localhost", wireMockServer.port());
        stubFor(post(urlEqualTo(urlPath))
                .withHeader("content-type", equalTo(APPLICATION_JSON))
                .willReturn(aResponse().withStatus(200)));

        HttpPost request = new HttpPost("http://localhost:" + wireMockServer.port() + urlPath);
        StringEntity entity = new StringEntity(body);
        request.addHeader("content-type", APPLICATION_JSON);
        request.setEntity(entity);
        currentHttpResponse = httpClient.execute(request);

        wireMockServer.stop();
    }

    public void executeGetRequest(String urlPath, String responseBody) throws IOException {
        wireMockServer.start();

        configureFor("localhost", wireMockServer.port());
        stubFor(get(urlEqualTo(urlPath)).withHeader("accept", equalTo(APPLICATION_JSON))
                .willReturn(aResponse().withBody(responseBody)));

        HttpGet request = new HttpGet("http://localhost:" + wireMockServer.port() + urlPath);
        request.addHeader("accept", APPLICATION_JSON);
        currentHttpResponse = httpClient.execute(request);

        wireMockServer.stop();
    }
}

