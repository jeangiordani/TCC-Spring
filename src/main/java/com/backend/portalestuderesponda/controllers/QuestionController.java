package com.backend.portalestuderesponda.controllers;

import com.backend.portalestuderesponda.dtos.AlternativeDTO;
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
    public ResponseEntity<QuestionDTO> createQuestion(
            @Valid @RequestBody QuestionInsertDTO questionDTO
    ) {
        QuestionDTO question = questionService.createQuestion(questionDTO);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(question.getId()).toUri();

        return ResponseEntity.created(uri).body(question);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findQuestionById(@PathVariable UUID id) {
        return ResponseDTO.response(questionService.findById(id), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<Object> findAllQuestion() {
        return ResponseDTO.response(questionService.findAll(), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(
            @PathVariable UUID id, @Valid @RequestBody QuestionInsertDTO questionDto
    ) {
        QuestionDTO question = questionService.updateQuestion(id, questionDto);
        return ResponseDTO.response(question, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        questionService.deleteQuestion(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/{id}/alternative")
    public ResponseEntity<Object> addAlternative(
            @PathVariable UUID id, @Valid @RequestBody AlternativeDTO alternativeDTO
    ){
        return ResponseDTO.response(
                questionService.addAlternative(id, alternativeDTO),
                HttpStatus.CREATED
        );
    }
}
