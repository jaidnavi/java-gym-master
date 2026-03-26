package ru.yandex.practicum.gym;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CoachTest {
    Coach coach = new Coach("Костицын", "Роман", "Игоревич");
    @Test
    void testGetSurname() {
        Assertions.assertEquals("Костицын", coach.getSurname());
    }

    @Test
    void testGetName() {
        Assertions.assertEquals("Роман", coach.getName());
    }

    @Test
    void testGetMiddleName() {
        Assertions.assertEquals("Игоревич", coach.getMiddleName());
    }
}