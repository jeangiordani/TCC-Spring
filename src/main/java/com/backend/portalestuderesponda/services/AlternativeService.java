package com.backend.portalestuderesponda.services;

import com.backend.portalestuderesponda.dtos.AlternativeDTO;
import com.backend.portalestuderesponda.mappers.AlternativeMapper;
import com.backend.portalestuderesponda.repositories.AlternativeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class AlternativeService {

    @Autowired
    private AlternativeRepository alternativeRepository;

    @Autowired
    private AlternativeMapper alternativeMapper;

    public List<AlternativeDTO> getAlternativesByQuestionId(UUID questionId) {
        return alternativeRepository.findByQuestionId(questionId)
                .stream()
                .map(alt -> alternativeMapper.toAlternativeDTO(alt))
                .toList();
    }
}
