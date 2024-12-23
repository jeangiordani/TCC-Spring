package com.backend.portalestuderesponda.controllers;

import com.backend.portalestuderesponda.dtos.DisciplineDTO;
import com.backend.portalestuderesponda.dtos.ResponseDTO;
import com.backend.portalestuderesponda.services.DisciplineService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/discipline")
public class DisciplineController {

    @Autowired
    private DisciplineService disciplineService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<DisciplineDTO> create(@Valid @RequestBody DisciplineDTO dto) {
        DisciplineDTO created = disciplineService.create(dto);

        URI uri = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uri).body(created);
    }

    @GetMapping
    public ResponseEntity<Object> findAll() {
//        return ResponseEntity.ok(disciplineService.findAll());
        return ResponseDTO.response(disciplineService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable Long id) {
        return ResponseDTO.response(disciplineService.findById(id), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @Valid @RequestBody DisciplineDTO dto) {
        DisciplineDTO updated = disciplineService.update(id, dto);

        return ResponseDTO.response(updated, HttpStatus.OK);
    }
}
