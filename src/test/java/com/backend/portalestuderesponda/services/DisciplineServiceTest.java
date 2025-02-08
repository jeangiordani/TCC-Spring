package com.backend.portalestuderesponda.services;

import com.backend.portalestuderesponda.dtos.DisciplineDTO;
import com.backend.portalestuderesponda.entities.Discipline;
import com.backend.portalestuderesponda.exceptions.NotFoundException;
import com.backend.portalestuderesponda.mappers.DisciplineMapper;
import com.backend.portalestuderesponda.repositories.DisciplineRepository;
import com.backend.portalestuderesponda.utils.FactoryUtils;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
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
    void shouldReturnDisciplineWhenValidDisciplineId() {

        final var validDisciplineId = 1L;

        when(disciplineRepository.findById(anyLong())).thenReturn(Optional.of(new Discipline()));
        when(disciplineMapper.toDisciplineDTO(any(Discipline.class))).thenReturn(new DisciplineDTO());

        final var result = disciplineService.findById(validDisciplineId);

        assertNotNull(result);
        assertInstanceOf(DisciplineDTO.class, result);

        verify(disciplineRepository, times(1)).findById(validDisciplineId);
        verify(disciplineMapper, times(1)).toDisciplineDTO(any(Discipline.class));
    }

    @Test
    void shouldThrowExceptionWhenDisciplineNotFound() {
        final var invalidDisciplineId = 1L;

        when(disciplineRepository.findById(anyLong())).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> disciplineService.findById(invalidDisciplineId));

        assertEquals("Disciplina não encontrada", exception.getMessage());

        verify(disciplineRepository, times(1)).findById(invalidDisciplineId);
        verify(disciplineMapper, times(0)).toDisciplineDTO(any(Discipline.class));
    }

    @Test
    void shouldReturnAllDisciplines(){
        when(disciplineRepository.findAll()).thenReturn(List.of(new Discipline()));

        final var result = disciplineService.findAll();

        assertNotNull(result);
        assertEquals(result.get(0).getClass(), DisciplineDTO.class);
        assertInstanceOf(List.class, result);

        verify(disciplineRepository, times(1)).findAll();

    }

    @Test
    void shouldCreateDisciplineSuccessfully(){
        Discipline expectedDiscipline = FactoryUtils.generateDisciplines(1).get(0);

        when(disciplineRepository.saveAndFlush(any(Discipline.class))).thenReturn(new Discipline());

        DisciplineDTO newDiscipline = disciplineService.create(new DisciplineDTO(expectedDiscipline));

        verify(disciplineRepository, times(1)).saveAndFlush(any(Discipline.class));

        assertEquals(newDiscipline.getClass(), DisciplineDTO.class);
        assertNotNull(newDiscipline);

    }

    @Test
    void shouldUpdateDisciplineSuccessfully(){

        Discipline discipline = FactoryUtils.generateDisciplines(1).get(0);
        final Long disciplineId = 1L;

        when(disciplineRepository.findById(disciplineId)).thenReturn(Optional.of(new Discipline()));
        when(disciplineRepository.saveAndFlush(any(Discipline.class))).thenReturn(new Discipline());
        when(disciplineMapper.toDisciplineDTO(any(Discipline.class))).thenReturn(new DisciplineDTO());

        DisciplineDTO updatedDiscipline = disciplineService.update(disciplineId, new DisciplineDTO(discipline));

        verify(disciplineRepository).findById(disciplineId);
        verify(disciplineRepository).saveAndFlush(any(Discipline.class));
        verify(disciplineMapper).toDisciplineDTO(any(Discipline.class));

        assertNotNull(updatedDiscipline);
        assertEquals(DisciplineDTO.class, updatedDiscipline.getClass());

    }

    @Test
    void shouldNotUpdateAndThrownExceptionWhenDisciplineNotFound(){

        Discipline discipline = FactoryUtils.generateDisciplines(1).get(0);
        final Long disciplineId = 1L;

        when(disciplineRepository.findById(disciplineId)).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(
                NotFoundException.class,
                () -> disciplineService.update(disciplineId, new DisciplineDTO(discipline))
        );

        verify(disciplineRepository).findById(disciplineId);
        verify(disciplineRepository, times(0)).saveAndFlush(any(Discipline.class));
        verify(disciplineMapper, times(0)).toDisciplineDTO(any(Discipline.class));

        assertNotNull(exception);
        assertEquals(exception.getMessage(), "Disciplina não encontrada");
    }

    @Test
    void shouldDeleteDisciplineSuccessfully(){
        final Long disciplineId = 1L;

        when(disciplineRepository.existsById(anyLong())).thenReturn(true);

        disciplineService.delete(disciplineId);

        verify(disciplineRepository).existsById(disciplineId);
        verify(disciplineRepository).deleteById(disciplineId);
    }

    @Test
    void shouldNotDeleteDisciplineWhenDataNotFound(){
        final Long disciplineId = 1L;

        when(disciplineRepository.existsById(anyLong())).thenReturn(false);

        NotFoundException exception = assertThrows(
                NotFoundException.class,
                () -> disciplineService.delete(disciplineId)
        );

        verify(disciplineRepository).existsById(disciplineId);
        verify(disciplineRepository, times(0)).deleteById(disciplineId);

        assertNotNull(exception);
        assertEquals(exception.getMessage(), "Disciplina não encontrada");
    }
}