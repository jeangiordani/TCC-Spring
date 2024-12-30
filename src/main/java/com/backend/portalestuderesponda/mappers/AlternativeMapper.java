package com.backend.portalestuderesponda.mappers;

import com.backend.portalestuderesponda.dtos.AlternativeDTO;
import com.backend.portalestuderesponda.entities.Alternative;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AlternativeMapper {

    Alternative toAlternative(AlternativeDTO alternativeDTO);
    AlternativeDTO toAlternativeDTO(Alternative alternative);
}
