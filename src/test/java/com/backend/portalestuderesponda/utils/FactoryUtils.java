package com.backend.portalestuderesponda.utils;

import com.backend.portalestuderesponda.entities.Discipline;
import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FactoryUtils {

    final static Faker faker = new Faker();
    final static Random rand = new Random();

    public static List<Discipline> generateDisciplines(int qty) {
        List<Discipline> disciplines = new ArrayList<Discipline>();
        for (int i = 0; i < qty; i++) {
            disciplines.add(new Discipline(
                    faker.book().genre(),
                    rand.nextBoolean()
            ));

        }

        return disciplines;
    }
}
