package com.ecommerce.cartapi.exceptions;

import java.io.IOException;
import java.io.InputStream;

import org.apache.coyote.BadRequestException;


import com.fasterxml.jackson.databind.ObjectMapper;

import feign.Response;
import feign.codec.ErrorDecoder;

public class RetreiveMessageErrorDecoder implements ErrorDecoder{

    private ErrorDecoder errorDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {
        FeignExceptionModel message = null;
        try (InputStream bodyIs = response.body()
            .asInputStream()) {
            ObjectMapper mapper = new ObjectMapper();
            message = mapper.readValue(bodyIs, FeignExceptionModel.class);
        } catch (IOException e) {
            return new Exception(e.getMessage());
        }
        switch (response.status()) {
        case 400:
            return new BadRequestException(message.getMessage() != null ? message.getMessage() : "Bad Request");
        case 404:
            return new Exception(message.getMessage());
        default:
            return errorDecoder.decode(methodKey, response);
        }
    }

    
}


