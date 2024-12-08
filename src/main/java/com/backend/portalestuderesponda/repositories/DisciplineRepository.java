package com.backend.portalestuderesponda.repositories;

import com.backend.portalestuderesponda.entities.Discipline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisciplineRepository extends JpaRepository<Discipline, Integer> {
}
