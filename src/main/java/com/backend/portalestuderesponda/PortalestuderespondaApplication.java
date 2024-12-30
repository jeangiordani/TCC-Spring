package com.backend.portalestuderesponda;

import com.backend.portalestuderesponda.entities.Discipline;
import com.backend.portalestuderesponda.entities.Question;
import com.backend.portalestuderesponda.repositories.DisciplineRepository;
import com.backend.portalestuderesponda.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PortalestuderespondaApplication implements CommandLineRunner {
	@Autowired
	DisciplineRepository disciplineRepository;

	@Autowired
	QuestionRepository questionRepository;

	public static void main(String[] args) {
		SpringApplication.run(PortalestuderespondaApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		Discipline discipline = new Discipline();
		discipline.setName("Discipline 1");
		discipline.setActive(true);

		Discipline newDiscipline = disciplineRepository.saveAndFlush(discipline);

		Question question = new Question();
		question.setDiscipline(newDiscipline);
		question.setStatement("teste");
		question.setPostStatement("teste2");
		question.setActive(true);

		System.out.println(questionRepository.saveAndFlush(question).getId());
	}
}
