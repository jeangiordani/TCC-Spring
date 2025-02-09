package com.backend.portalestuderesponda.utils;

import com.backend.portalestuderesponda.entities.Discipline;
import com.backend.portalestuderesponda.entities.Role;
import com.backend.portalestuderesponda.entities.User;
import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FactoryUtils {

    final static Faker faker = new Faker();
    final static Random rand = new Random();

    public static List<Discipline> generateDisciplines(int qty) {
        List<Discipline> disciplines = new ArrayList<Discipline>();
        String name = faker.book().genre();

        while (name.length() <= 5) {
            name = faker.book().genre();
        }

        for (int i = 0; i < qty; i++) {
            disciplines.add(new Discipline(
                    name,
                    rand.nextBoolean()
            ));

        }

        return disciplines;
    }

    public static List<User> generateUsers(int qty, Role role) {
        List<User> users = new ArrayList<User>();


        for (int i = 0; i < qty; i++) {
            users.add(new User(
                    faker.name().fullName(),
                    faker.internet().emailAddress(),
                    faker.internet().password(6, 10),
                    role
            ));

        }

        return users;
    }
}
