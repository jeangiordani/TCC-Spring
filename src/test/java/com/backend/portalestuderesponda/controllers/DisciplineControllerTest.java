package com.backend.portalestuderesponda.controllers;

import com.backend.portalestuderesponda.entities.Discipline;
import com.backend.portalestuderesponda.repositories.DisciplineRepository;
import com.backend.portalestuderesponda.utils.FactoryUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;


import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class DisciplineControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DisciplineRepository disciplineRepository;

    @Test
    void givenValidDisciplineId_whenFindById_thenReturnDisciplineSuccessfully() throws Exception {

        Discipline discipline = FactoryUtils.generateDisciplines(1).get(0);
        disciplineRepository.save(discipline);


        mockMvc.perform(get("/discipline/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.name").value(discipline.getName()))
                .andExpect(jsonPath("$.data.active").value(discipline.getActive()));

        disciplineRepository.delete(discipline);
    }

    @Test
    void givenInvalidDisciplineId_whenFindById_thenReturnStatus404NotFound() throws Exception {
        mockMvc.perform(get("/discipline/{id}", 1))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.description").value("Disciplina não encontrada"));

    }

    @Test
    @WithMockUser(username = "jean@gmail.com", authorities = {"ADMIN"})
    void givenValidDiscipline_whenCreate_thenReturnCreatedDisciplineSuccessfully() throws Exception {
        Discipline discipline = FactoryUtils.generateDisciplines(1).get(0);

        mockMvc.perform(post("/discipline")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(discipline)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value(discipline.getName()))
                .andExpect(jsonPath("$.active").value(discipline.getActive()));

        List<Discipline> disciplines = disciplineRepository.findAll();

        assertEquals(1, disciplines.size());
        assertTrue(disciplines.stream().map(d -> d.getName().equals(discipline.getName())).findFirst().isPresent());


    }

    @Test
    @WithMockUser(username = "jean@gmail.com", authorities = {"ADMIN"})
    void givenInvalidDisciplineName_whenCreate_thenReturnValidationError() throws Exception {
        Discipline discipline = FactoryUtils.generateDisciplines(1).get(0);

        discipline.setName(discipline.getName().substring(0,4));

        mockMvc.perform(post("/discipline")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(discipline)))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(jsonPath("$.description").value("name deve ser maior que 5"));

        List<Discipline> disciplines = disciplineRepository.findAll();

        assertEquals(0, disciplines.size());
    }

    @Test
    @WithMockUser(username = "jean@gmail.com", authorities = {"ADMIN"})
    void givenDisciplineAndValidId_whenUpdate_thenReturnUpdatedDiscipline() throws Exception {
        Discipline discipline = FactoryUtils.generateDisciplines(1).get(0);
        Discipline expectedDiscipline = disciplineRepository.save(discipline);

        expectedDiscipline.setName("Updated");
        expectedDiscipline.setActive(true);
        expectedDiscipline.setCreatedAt(null);
        expectedDiscipline.setUpdatedAt(null);

        mockMvc.perform(put("/discipline/{id}", expectedDiscipline.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(expectedDiscipline)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.name").value(expectedDiscipline.getName()))
                .andExpect(jsonPath("$.data.active").value(expectedDiscipline.getActive()));

        List<Discipline> disciplines = disciplineRepository.findAll();

        assertEquals(1, disciplines.size());
        assertTrue(disciplines
                .stream()
                .map(d -> d.getName().equals(expectedDiscipline.getName()))
                .findFirst()
                .isPresent()
        );
    }

    @Test
    @WithMockUser(username = "jean@gmail.com", authorities = {"ADMIN"})
    void givenDisciplineAndInvalidId_whenUpdate_thenReturnUpdatedDiscipline() throws Exception {
        Discipline discipline = FactoryUtils.generateDisciplines(1).get(0);
        Discipline expectedDiscipline = disciplineRepository.save(discipline);

        expectedDiscipline.setName("Updated");
        expectedDiscipline.setActive(true);
        expectedDiscipline.setCreatedAt(null);
        expectedDiscipline.setUpdatedAt(null);

        mockMvc.perform(put("/discipline/{id}", expectedDiscipline.getId()+1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(expectedDiscipline)))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.description").value("Disciplina não encontrada"));

        List<Discipline> disciplines = disciplineRepository.findAll();

        assertEquals(1, disciplines.size());
        assertTrue(disciplines
                .stream()
                .noneMatch(d -> d.getName().equals(expectedDiscipline.getName()))
        );
    }

}