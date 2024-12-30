package com.backend.portalestuderesponda.controllers;

import com.backend.portalestuderesponda.dtos.ResponseDTO;
import com.backend.portalestuderesponda.services.AlternativeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/alternative")
public class AlternativeController {

    @Autowired
    private AlternativeService alternativeService;

    @GetMapping("/{questionId}")
    public ResponseEntity<Object> findAlternativesByQuestionId(@PathVariable UUID questionId) {
        return ResponseDTO.response(
                alternativeService.getAlternativesByQuestionId(questionId),
                HttpStatus.OK);
    }
}
