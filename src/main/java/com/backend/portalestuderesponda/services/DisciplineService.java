package com.backend.portalestuderesponda.services;

import com.backend.portalestuderesponda.dtos.DisciplineDTO;
import com.backend.portalestuderesponda.entities.Discipline;
import com.backend.portalestuderesponda.repositories.DisciplineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DisciplineService {

    @Autowired
    private DisciplineRepository disciplineRepository;

    @Transactional
    public DisciplineDTO create(DisciplineDTO disciplineDTO) {
        Discipline discipline = new Discipline();

        discipline.setName(disciplineDTO.getName());
        discipline.setIsActive(disciplineDTO.getIsActive());

        Discipline newDiscipline = disciplineRepository.save(discipline);
        newDiscipline.setCreatedAt(LocalDateTime.now());
        newDiscipline.setUpdatedAt(LocalDateTime.now());
        return new DisciplineDTO(newDiscipline);
    }

    @Transactional(readOnly = true)
    public List<DisciplineDTO> findAll() {
        List<Discipline> disciplines = disciplineRepository.findAll();

        return disciplines.stream().map(DisciplineDTO::new).toList();
    }
}
