package ru.yandex.practicum.gym;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;


class TrainingSessionTest {

    Group group = new Group("Акробатика для детей", Age.CHILD, 60);
    Coach coach = new Coach("Васильев", "Николай", "Сергеевич");

    TrainingSession trainingSession = new TrainingSession(group, coach,
            DayOfWeek.MONDAY, new TimeOfDay(13, 0));


    @Test
    void testGetGroup() {
        Assertions.assertEquals(trainingSession.getGroup(), group);
    }

    @Test
    void testGetCoach() {
        Assertions.assertEquals(trainingSession.getCoach(), coach);
    }

    @Test
    void testGetDayOfWeek() {
        Assertions.assertEquals(DayOfWeek.MONDAY, trainingSession.getDayOfWeek());
    }

    @Test
    void testGetTimeOfDay() {
        Assertions.assertEquals(new TimeOfDay(13, 0), trainingSession.getTimeOfDay());
    }
}