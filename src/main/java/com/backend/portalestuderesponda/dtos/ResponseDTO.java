package com.backend.portalestuderesponda.dtos;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ResponseDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    public static ResponseEntity<Object> response(Object object, HttpStatus status) {
        Map<String, Object> map = new HashMap<String, Object>();

        map.put("status", status.value());
        map.put("data", object);
        return new ResponseEntity<Object>(map, status);
    }
}
