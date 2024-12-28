package com.backend.portalestuderesponda.dtos;

import com.backend.portalestuderesponda.entities.Discipline;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class QuestionDTO extends QuestionInsertDTO {
    private UUID id;

    private Boolean isActive;

    private DisciplineDTO discipline;

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
}
