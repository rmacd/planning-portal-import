package com.rmacd.queue;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;

import java.io.IOException;

public class CustomHttpClientResponseHandler implements ResponseHandler {
    @Override
    public Object handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
        return null;
    }
}
