package com.endava.universalbank.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;

@Slf4j
public class CustomResponseHandler extends DefaultResponseErrorHandler {

    public void handleError(ClientHttpResponse response){
      log.error("ClientHttp error {}", response);
    }
}
