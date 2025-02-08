package com.backend.portalestuderesponda.services;

import com.backend.portalestuderesponda.dtos.DisciplineDTO;
import com.backend.portalestuderesponda.entities.Discipline;
import com.backend.portalestuderesponda.mappers.DisciplineMapper;
import com.backend.portalestuderesponda.repositories.DisciplineRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest
class DisciplineServiceTest {

    @InjectMocks
    private DisciplineService disciplineService;

    @Mock
    private DisciplineRepository disciplineRepository;

    @Mock
    private DisciplineMapper disciplineMapper;

    @Test
    void shouldReturnAllDisciplines() {

        final var validDisciplineId = 1L;

        when(disciplineRepository.findById(anyLong())).thenReturn(Optional.of(new Discipline()));
        when(disciplineMapper.toDisciplineDTO(any(Discipline.class))).thenReturn(new DisciplineDTO());

        final var result = disciplineService.findById(validDisciplineId);

        assertNotNull(result);
        assertInstanceOf(DisciplineDTO.class, result);

        verify(disciplineRepository, times(1)).findById(validDisciplineId);
        verify(disciplineMapper, times(1)).toDisciplineDTO(any(Discipline.class));
    }

}