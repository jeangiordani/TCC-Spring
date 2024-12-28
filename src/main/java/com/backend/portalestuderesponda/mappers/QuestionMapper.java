package com.backend.portalestuderesponda.mappers;

import com.backend.portalestuderesponda.dtos.QuestionDTO;
import com.backend.portalestuderesponda.dtos.QuestionInsertDTO;
import com.backend.portalestuderesponda.entities.Question;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface QuestionMapper {

    Question toQuestion(QuestionDTO questionDTO);
    QuestionDTO toDTO(Question question);
    QuestionInsertDTO toInsertDTO(Question question);
    Question toQuestion(QuestionInsertDTO insertDTO);
}
