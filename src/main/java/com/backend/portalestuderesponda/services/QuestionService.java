package com.backend.portalestuderesponda.services;

import com.backend.portalestuderesponda.dtos.QuestionDTO;
import com.backend.portalestuderesponda.dtos.QuestionInsertDTO;
import com.backend.portalestuderesponda.entities.Discipline;
import com.backend.portalestuderesponda.entities.Question;
import com.backend.portalestuderesponda.exceptions.NotFoundException;
import com.backend.portalestuderesponda.mappers.QuestionMapper;
import com.backend.portalestuderesponda.repositories.DisciplineRepository;
import com.backend.portalestuderesponda.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private DisciplineRepository disciplineRepository;

    @Autowired
    private QuestionMapper questionMapper;

    @Transactional
    public QuestionDTO createQuestion(QuestionInsertDTO questionDTO) {
        Optional<Discipline> discipline = disciplineRepository.findById(questionDTO.getDisciplineId());

        if (discipline.isEmpty()) {
            throw new NotFoundException("Disciplina não encontrada");
        }

        Question question = questionMapper.toQuestion(questionDTO);
        question.setDiscipline(discipline.get());

        return questionMapper.toDTO(questionRepository.saveAndFlush(question));
    }

    public QuestionDTO findById(UUID id) {
        Optional<Question> question = questionRepository.findById(id);
        if (question.isEmpty()) {
            throw new NotFoundException("Disciplina não encontrada");
        }

        return questionMapper.toDTO(question.get());
    }
}
