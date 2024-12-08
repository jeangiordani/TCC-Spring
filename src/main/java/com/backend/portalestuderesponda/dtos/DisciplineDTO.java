package com.backend.portalestuderesponda.dtos;

import com.backend.portalestuderesponda.entities.Discipline;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.time.LocalDateTime;

public class DisciplineDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    @NotBlank(message = "name é obrigatório")
    @Length(min = 5, message = "name deve ser maior que 5")
    private String name;

    @NotNull(message = "isActive é obrigatório")
    private Boolean isActive;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public DisciplineDTO() {}

    public DisciplineDTO(Long id, String name, Boolean isActive, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.isActive = isActive;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;

    }

    public DisciplineDTO(Discipline discipline) {
        this.id = discipline.getId();
        this.name = discipline.getName();
        this.isActive = discipline.getIsActive();
        this.createdAt = discipline.getCreatedAt();
        this.updatedAt = discipline.getUpdatedAt();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
