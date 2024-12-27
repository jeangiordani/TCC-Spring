package com.backend.portalestuderesponda.services;

import com.backend.portalestuderesponda.dtos.DisciplineDTO;
import com.backend.portalestuderesponda.entities.Discipline;
import com.backend.portalestuderesponda.exceptions.DataExistsException;
import com.backend.portalestuderesponda.exceptions.NotFoundException;
import com.backend.portalestuderesponda.mappers.DisciplineMapper;
import com.backend.portalestuderesponda.repositories.DisciplineRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DisciplineService {

    @Autowired
    private DisciplineRepository disciplineRepository;

    @Autowired
    private DisciplineMapper disciplineMapper;

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

    @Transactional(readOnly = true)
    public DisciplineDTO findById(Long id) {
        Optional<Discipline> discipline = disciplineRepository.findById(id);

        if (discipline.isEmpty()) {
            throw new NotFoundException("Disciplina não encontrada");
        }

        return disciplineMapper.toDisciplineDTO(discipline.get());

    }

    @Transactional()
    public DisciplineDTO update(Long id, DisciplineDTO disciplineDTO) {
        Optional<Discipline> discipline = disciplineRepository.findById(id);

        if (discipline.isEmpty()) {
            throw new NotFoundException("Disciplina não encontrada");
        }
        discipline.get().setName(disciplineDTO.getName());
        discipline.get().setIsActive(disciplineDTO.getIsActive());
        discipline.get().setUpdatedAt(LocalDateTime.now());

        disciplineRepository.save(discipline.get());

        return disciplineMapper.toDisciplineDTO(discipline.get());
    }

    public void delete(Long id) {
        if (!disciplineRepository.existsById(id)) {
            throw new NotFoundException("Disciplina não encontrada");
        }
        disciplineRepository.deleteById(id);
    }


}
