package com.americanexpress.synapse.service.rest.controller.helpers;

import com.americanexpress.synapse.service.rest.model.BaseServiceResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class MonoResponseEntityCreator<O extends BaseServiceResponse> {

    public ResponseEntity<O> create(O serviceResponse) {
        return serviceResponse == null ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : ResponseEntity.ok(serviceResponse);
    }
}
