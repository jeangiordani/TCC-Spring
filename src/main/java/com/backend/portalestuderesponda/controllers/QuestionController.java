package com.backend.portalestuderesponda.controllers;

import com.backend.portalestuderesponda.dtos.QuestionDTO;
import com.backend.portalestuderesponda.dtos.QuestionInsertDTO;
import com.backend.portalestuderesponda.dtos.ResponseDTO;
import com.backend.portalestuderesponda.services.QuestionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<QuestionDTO> createQuestion(@Valid @RequestBody QuestionInsertDTO questionDTO) {
        QuestionDTO question = questionService.createQuestion(questionDTO);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(question.getId()).toUri();

        return ResponseEntity.created(uri).body(question);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findQuestionById(@PathVariable UUID id) {
        return ResponseDTO.response(questionService.findById(id), HttpStatus.OK);
    }
}
