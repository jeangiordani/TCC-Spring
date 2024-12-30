package com.backend.portalestuderesponda.services;

import com.backend.portalestuderesponda.dtos.AlternativeDTO;
import com.backend.portalestuderesponda.dtos.QuestionDTO;
import com.backend.portalestuderesponda.dtos.QuestionInsertDTO;
import com.backend.portalestuderesponda.entities.Alternative;
import com.backend.portalestuderesponda.entities.Discipline;
import com.backend.portalestuderesponda.entities.Question;
import com.backend.portalestuderesponda.exceptions.NotFoundException;
import com.backend.portalestuderesponda.mappers.AlternativeMapper;
import com.backend.portalestuderesponda.mappers.QuestionMapper;
import com.backend.portalestuderesponda.repositories.AlternativeRepository;
import com.backend.portalestuderesponda.repositories.DisciplineRepository;
import com.backend.portalestuderesponda.repositories.QuestionRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
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

    @Autowired
    private AlternativeMapper alternativeMapper;

    @Autowired
    private AlternativeRepository alternativeRepository;

    @Autowired
    private EntityManager entityManager;

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

    public List<QuestionDTO> findAll() {
        return questionRepository.
                findAll().
                stream().
                map(
                    question -> questionMapper.toDTO(question)
                ).toList();
    }

    @Transactional
    public QuestionDTO updateQuestion(UUID id, QuestionInsertDTO questionDto) {
         Question question = questionRepository.findById(id).orElseThrow(
                 () -> new NotFoundException("Questão não encontrada")
         );

         question.setStatement(questionDto.getStatement());
         question.setPostStatement(questionDto.getPostStatement());
         question.setActive(questionDto.getActive());

         if (!question.getDiscipline().getId().equals(questionDto.getDisciplineId())){
             Discipline discipline = disciplineRepository.findById(questionDto.getDisciplineId()).orElseThrow(
                     () -> new NotFoundException("Disciplina não encontrada")
             );

             question.setDiscipline(discipline);
         }

         return questionMapper.toDTO(questionRepository.saveAndFlush(question));
    }

    @Transactional
    public void deleteQuestion(UUID id) {
        Question question = questionRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Questão não encontrada")
        );

        questionRepository.delete(question);
    }

    @Transactional
    public AlternativeDTO addAlternative(UUID id, AlternativeDTO alternativeDTO) {
        Question question = questionRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Questão não encontrada")
        );

        Alternative alternative = alternativeMapper.toAlternative(alternativeDTO);
        alternative.setQuestion(question);

        return alternativeMapper.toAlternativeDTO(
                alternativeRepository.saveAndFlush(alternative)
        );

    }
}
