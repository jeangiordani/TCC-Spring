package com.backend.portalestuderesponda.mappers;

import com.backend.portalestuderesponda.dtos.DisciplineDTO;
import com.backend.portalestuderesponda.entities.Discipline;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DisciplineMapper {

    DisciplineDTO toDisciplineDTO(Discipline discipline);

    Discipline toDiscipline(DisciplineDTO dto);
}
