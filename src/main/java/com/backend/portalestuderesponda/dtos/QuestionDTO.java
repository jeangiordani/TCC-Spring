package com.backend.portalestuderesponda.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@JsonPropertyOrder(
        {"id", "statement", "postStatement", "active", "discipline", "createdAt", "updatedAt"}
)
public class QuestionDTO extends QuestionInsertDTO {
    private UUID id;

    private DisciplineDTO discipline;

    private List<AlternativeDTO> alternatives;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @JsonIgnore
    private Long disciplineId;

    public QuestionDTO() {
    }

    public QuestionDTO(UUID id, String statement, String postStatement, Boolean isActive, DisciplineDTO discipline, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(statement, postStatement, isActive);
        this.id = id;
        this.discipline = discipline;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UUID getId() {
        return id;
    }

    public DisciplineDTO getDiscipline() {
        return discipline;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setDiscipline(DisciplineDTO discipline) {
        this.discipline = discipline;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<AlternativeDTO> getAlternatives() {
        return alternatives;
    }

    public void setAlternatives(List<AlternativeDTO> alternatives) {
        this.alternatives = alternatives;
    }

}
