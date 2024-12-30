package com.backend.portalestuderesponda.repositories;

import com.backend.portalestuderesponda.entities.Alternative;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AlternativeRepository extends JpaRepository<Alternative, Long> {
    List<Alternative> findByQuestionId(UUID questionId);
}
