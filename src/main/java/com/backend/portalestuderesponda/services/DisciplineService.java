package com.backend.portalestuderesponda.services;

import com.backend.portalestuderesponda.dtos.DisciplineDTO;
import com.backend.portalestuderesponda.entities.Discipline;
import com.backend.portalestuderesponda.exceptions.NotFoundException;
import com.backend.portalestuderesponda.mappers.DisciplineMapper;
import com.backend.portalestuderesponda.repositories.DisciplineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        discipline.setActive(disciplineDTO.getActive());

        return new DisciplineDTO(disciplineRepository.saveAndFlush(discipline));
    }

    @Transactional(readOnly = true)
    public List<DisciplineDTO> findAll() {
        List<Discipline> disciplines = disciplineRepository.findAll();

        return disciplines.stream().map(DisciplineDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public DisciplineDTO findById(Long id) {
        Discipline discipline = disciplineRepository.findById(id).orElseThrow(
                ()-> new NotFoundException("Disciplina não encontrada")
        );
        return disciplineMapper.toDisciplineDTO(discipline);

    }

    @Transactional()
    public DisciplineDTO update(Long id, DisciplineDTO disciplineDTO) {
        Discipline discipline = disciplineRepository.findById(id).orElseThrow(
                ()-> new NotFoundException("Disciplina não encontrada")
        );
        discipline.setName(disciplineDTO.getName());
        discipline.setActive(disciplineDTO.getActive());

        return disciplineMapper.toDisciplineDTO(disciplineRepository.saveAndFlush(discipline));
    }

    public void delete(Long id) {
        if (!disciplineRepository.existsById(id)) {
            throw new NotFoundException("Disciplina não encontrada");
        }
        disciplineRepository.deleteById(id);
    }


}
