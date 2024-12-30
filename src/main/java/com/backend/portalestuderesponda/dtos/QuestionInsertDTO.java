package com.backend.portalestuderesponda.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class QuestionInsertDTO {

    @NotBlank(message = "O campo statement é obrigatório")
    private String statement;

    @NotBlank(message = "O campo postStatement é obrigatório")
    private String postStatement;

    @NotNull(message = "O campo active é obrigatório")
    private Boolean active;

    @NotNull(message = "O campo disciplineId é obrigatório")
    private Long disciplineId;

    public QuestionInsertDTO(String statement, String postStatement, Boolean isActive, Long disciplineId) {
        this.statement = statement;
        this.postStatement = postStatement;
        this.active = isActive;
        this.disciplineId = disciplineId;
    }

    public QuestionInsertDTO(String statement, String postStatement, Boolean isActive) {
        this.statement = statement;
        this.postStatement = postStatement;
        this.active = isActive;
    }

    public QuestionInsertDTO() {
    }

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

    public  String getPostStatement() {
        return postStatement;
    }

    public void setPostStatement(String postStatement) {
        this.postStatement = postStatement;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Long getDisciplineId() {
        return disciplineId;
    }

    public void setDisciplineId(Long disciplineId) {
        this.disciplineId = disciplineId;
    }


}
