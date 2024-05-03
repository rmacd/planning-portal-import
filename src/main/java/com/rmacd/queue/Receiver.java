package com.rmacd.queue;

import com.rmacd.models.ProxymanRequest;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@Component
public class Receiver {

    private static final Logger logger = LoggerFactory.getLogger(Receiver.class);

    @JmsListener(destination = "plans", containerFactory = "myFactory")
    public void receiveMessage(ProxymanRequest request) {
        logger.info("received message: {}", request.toString());

        try {
            URIBuilder builder = new URIBuilder(
                    new URI(request.getScheme().toString(), request.getHost(), request.getPath(), null)
            );
            for (String s : request.getQueries().keySet()) {
                if (s.equals("f")) {
                    builder.addParameter("f", "geoJSON");
                }
                else {
                    builder.addParameter(s, request.getQueries().get(s));
                }
            }
            HttpGet httpGet = new HttpGet(builder.build());
            for (String key : request.getHeaders().keySet()) {
                httpGet.addHeader(key, request.getHeaders().get(key));
            }

            try (CloseableHttpClient client = HttpClients.createDefault();
                 CloseableHttpResponse response = (CloseableHttpResponse) client
                         .execute(httpGet, new CustomHttpClientResponseHandler())) {

                final int statusCode = response.getStatusLine().getStatusCode();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

}
