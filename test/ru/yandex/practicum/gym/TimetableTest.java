package ru.yandex.practicum.gym;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TimetableTest {

    @Test
    void testGetTrainingSessionsForDaySingleSession() {
        Timetable timetable = new Timetable();

        Group group = new Group("Акробатика для детей", Age.CHILD, 60);
        Coach coach = new Coach("Васильев", "Николай", "Сергеевич");
        TrainingSession singleTrainingSession = new TrainingSession(group, coach,
                DayOfWeek.MONDAY, new TimeOfDay(13, 0));

        timetable.addNewTrainingSession(singleTrainingSession);

        //Проверить, что за понедельник вернулось одно занятие
        ArrayList<TrainingSession> dayTraining1 = timetable.getTrainingSessionsForDay(DayOfWeek.MONDAY);
        Assertions.assertEquals(1, dayTraining1.size());

        //Проверить, что за вторник не вернулось занятий
        ArrayList<TrainingSession> dayTraining2 = timetable.getTrainingSessionsForDay(DayOfWeek.TUESDAY);
        Assertions.assertEquals(0, dayTraining2.size());

    }

    @Test
    void testGetTrainingSessionsForDayMultipleSessions() {
        Timetable timetable = new Timetable();

        Coach coach = new Coach("Васильев", "Николай", "Сергеевич");

        Group groupAdult = new Group("Акробатика для взрослых", Age.ADULT, 90);
        TrainingSession thursdayAdultTrainingSession = new TrainingSession(groupAdult, coach,
                DayOfWeek.THURSDAY, new TimeOfDay(20, 0));

        timetable.addNewTrainingSession(thursdayAdultTrainingSession);

        Group groupChild = new Group("Акробатика для детей", Age.CHILD, 60);
        TrainingSession mondayChildTrainingSession = new TrainingSession(groupChild, coach,
                DayOfWeek.MONDAY, new TimeOfDay(13, 0));
        TrainingSession thursdayChildTrainingSession = new TrainingSession(groupChild, coach,
                DayOfWeek.THURSDAY, new TimeOfDay(13, 0));
        TrainingSession saturdayChildTrainingSession = new TrainingSession(groupChild, coach,
                DayOfWeek.SATURDAY, new TimeOfDay(10, 0));


        timetable.addNewTrainingSession(mondayChildTrainingSession);
        timetable.addNewTrainingSession(thursdayChildTrainingSession);
        timetable.addNewTrainingSession(saturdayChildTrainingSession);

        // Проверить, что за понедельник вернулось одно занятие
        ArrayList<TrainingSession> dayTraining1 = timetable.getTrainingSessionsForDay(DayOfWeek.MONDAY);
        Assertions.assertEquals(1, dayTraining1.size());

        // Проверить, что за четверг вернулось два занятия в правильном порядке: сначала в 13:00, потом в 20:00
        ArrayList<TrainingSession> dayTraining4 = timetable.getTrainingSessionsForDay(DayOfWeek.THURSDAY);
        Assertions.assertEquals(2, dayTraining4.size());
        assertEquals(new TimeOfDay(13, 0), dayTraining4.get(0).getTimeOfDay());
        assertEquals(new TimeOfDay(20, 0), dayTraining4.get(1).getTimeOfDay());


        // Проверить, что за вторник не вернулось занятий
        ArrayList<TrainingSession> dayTraining2 = timetable.getTrainingSessionsForDay(DayOfWeek.TUESDAY);
        Assertions.assertEquals(0, dayTraining2.size());

    }

    @Test
    void testGetTrainingSessionsForDayAndTime() {
        Timetable timetable = new Timetable();

        Group group = new Group("Акробатика для детей", Age.CHILD, 60);
        Coach coach = new Coach("Васильев", "Николай", "Сергеевич");
        TrainingSession singleTrainingSession = new TrainingSession(group, coach,
                DayOfWeek.MONDAY, new TimeOfDay(13, 0));

        timetable.addNewTrainingSession(singleTrainingSession);

        //Проверить, что за понедельник в 13:00 вернулось одно занятие
        ArrayList<TrainingSession> dayTraining1_13 = timetable.getTrainingSessionsForDayAndTime(DayOfWeek.MONDAY, new TimeOfDay(13, 0));
        Assertions.assertEquals(1, dayTraining1_13.size());

        //Проверить, что за понедельник в 14:00 не вернулось занятий
        ArrayList<TrainingSession> dayTraining1_14 = timetable.getTrainingSessionsForDayAndTime(DayOfWeek.MONDAY, new TimeOfDay(14, 0));
        Assertions.assertEquals(0, dayTraining1_14.size());

        //Проверить, что за среда в 14:00 не вернулось занятий
        ArrayList<TrainingSession> dayTraining3_14 = timetable.getTrainingSessionsForDayAndTime(DayOfWeek.WEDNESDAY, new TimeOfDay(14, 0));
        Assertions.assertEquals(0, dayTraining3_14.size());

    }

    @Test
    void testGetTrainingSessionsForDayAndTimeInOneTime() {
        Timetable timetable = new Timetable();

        Group group1 = new Group("Акробатика для детей", Age.CHILD, 60);
        Coach coach1 = new Coach("Васильев", "Николай", "Сергеевич");
        Group group2 = new Group("Спортивное программирование для детей", Age.CHILD, 60);
        Coach coach2 = new Coach("Костицын", "Роман", "Игоревич");

        TrainingSession trainingSession1_13_1 = new TrainingSession(group1, coach1,
                DayOfWeek.MONDAY, new TimeOfDay(13, 0));
        TrainingSession trainingSession1_13_2 = new TrainingSession(group2, coach2,
                DayOfWeek.MONDAY, new TimeOfDay(13, 0));


        timetable.addNewTrainingSession(trainingSession1_13_1);
        timetable.addNewTrainingSession(trainingSession1_13_2);

        //Проверить, что за понедельник в 13:00 вернулось 2 занятия
        ArrayList<TrainingSession> dayTraining1_13 = timetable.getTrainingSessionsForDayAndTime(DayOfWeek.MONDAY, new TimeOfDay(13, 0));
        Assertions.assertEquals(2, dayTraining1_13.size());

    }

    @Test
    void testGetTrainingSessionsForDayInOneTime() {
        Timetable timetable = new Timetable();

        Group group1 = new Group("Акробатика для детей", Age.CHILD, 60);
        Coach coach1 = new Coach("Васильев", "Николай", "Сергеевич");
        Group group2 = new Group("Спортивное программирование для детей", Age.CHILD, 60);
        Coach coach2 = new Coach("Костицын", "Роман", "Игоревич");

        TrainingSession trainingSession1_13_1 = new TrainingSession(group1, coach1,
                DayOfWeek.MONDAY, new TimeOfDay(13, 0));
        TrainingSession trainingSession1_13_2 = new TrainingSession(group2, coach2,
                DayOfWeek.MONDAY, new TimeOfDay(13, 0));


        timetable.addNewTrainingSession(trainingSession1_13_1);
        timetable.addNewTrainingSession(trainingSession1_13_2);

        //Проверить, что за понедельник вернулось 2 занятия
        ArrayList<TrainingSession> dayTraining1 = timetable.getTrainingSessionsForDay(DayOfWeek.MONDAY);
        Assertions.assertEquals(2, dayTraining1.size());

    }

    @Test
    void testGetAllTrainingSessions() {
        Timetable timetable = new Timetable();

        Group group1 = new Group("Акробатика для детей", Age.CHILD, 60);
        Coach coach1 = new Coach("Васильев", "Николай", "Сергеевич");
        Group group2 = new Group("Спортивное программирование для детей", Age.CHILD, 60);
        Coach coach2 = new Coach("Костицын", "Роман", "Игоревич");

        TrainingSession trainingSession1_13_1 = new TrainingSession(group1, coach1,
                DayOfWeek.MONDAY, new TimeOfDay(13, 0));
        TrainingSession trainingSession1_13_2 = new TrainingSession(group2, coach2,
                DayOfWeek.MONDAY, new TimeOfDay(13, 0));
        TrainingSession trainingSession7_13 = new TrainingSession(group2, coach2,
                DayOfWeek.SUNDAY, new TimeOfDay(13, 0));

        timetable.addNewTrainingSession(trainingSession1_13_1);
        timetable.addNewTrainingSession(trainingSession1_13_2);
        timetable.addNewTrainingSession(trainingSession7_13);

        //Проверить, что всего вернулось 3 занятия
        ArrayList<TrainingSession> dayTrainingAll = timetable.getAllTrainingSessions();
        Assertions.assertEquals(3, dayTrainingAll.size());
    }


    @Test
    void testGetCountByCoachesOrder() {
        Timetable timetable = new Timetable();

        Group group1 = new Group("Акробатика для детей", Age.CHILD, 60);
        Coach coach1 = new Coach("Васильев", "Николай", "Сергеевич");
        Group group2 = new Group("Спортивное программирование для детей", Age.CHILD, 60);
        Coach coach2 = new Coach("Костицын", "Роман", "Игоревич");
        Group group3 = new Group("Спортивное программирование для чудаков", Age.ADULT, 60);
        Coach coach3 = new Coach("Кривошеев", "Пётр", "Семёнович");
        Group group4 = new Group("Спортивное программирование для чудаков", Age.ADULT, 60);
        Coach coach4 = new Coach("Кривошеев", "Пётр 2", "Семёнович");

        TrainingSession trainingSession1_13_1 = new TrainingSession(group1, coach1,
                DayOfWeek.MONDAY, new TimeOfDay(13, 0));
        TrainingSession trainingSession1_13_2 = new TrainingSession(group2, coach2,
                DayOfWeek.MONDAY, new TimeOfDay(13, 0));
        TrainingSession trainingSession7_13 = new TrainingSession(group2, coach2,
                DayOfWeek.SUNDAY, new TimeOfDay(13, 0));
        TrainingSession trainingSession7_14 = new TrainingSession(group3, coach3,
                DayOfWeek.SUNDAY, new TimeOfDay(14, 0));
        TrainingSession trainingSession7_15 = new TrainingSession(group4, coach4,
                DayOfWeek.SUNDAY, new TimeOfDay(15, 0));


        timetable.addNewTrainingSession(trainingSession1_13_1);
        timetable.addNewTrainingSession(trainingSession1_13_1);
        timetable.addNewTrainingSession(trainingSession1_13_1);
        timetable.addNewTrainingSession(trainingSession1_13_1);
        timetable.addNewTrainingSession(trainingSession1_13_1);
        timetable.addNewTrainingSession(trainingSession1_13_1);
        timetable.addNewTrainingSession(trainingSession1_13_2);
        timetable.addNewTrainingSession(trainingSession7_13);
        timetable.addNewTrainingSession(trainingSession7_14);
        timetable.addNewTrainingSession(trainingSession7_14);
        timetable.addNewTrainingSession(trainingSession7_15);

        ArrayList<CounterOfTrainings> listCounterOfTrainings = timetable.getCountByCoaches();

        for (int i = 0; i < listCounterOfTrainings.size() - 1; i++) {
            Assertions.assertTrue(listCounterOfTrainings.get(i).getCount() >= listCounterOfTrainings.get(i + 1).getCount());
        }

    }

    @Test
    void testGetCountByCoachesAddIsPositive() {
        Timetable timetable = new Timetable();

        Group group1 = new Group("Акробатика для детей", Age.CHILD, 60);
        Coach coach1 = new Coach("Васильев", "Николай", "Сергеевич");
        Group group2 = new Group("Спортивное программирование для детей", Age.CHILD, 60);
        Coach coach2 = new Coach("Костицын", "Роман", "Игоревич");
        Group group3 = new Group("Спортивное программирование для чудаков", Age.ADULT, 60);
        Coach coach3 = new Coach("Кривошеев", "Пётр", "Семёнович");
        Group group4 = new Group("Спортивное программирование для чудаков", Age.ADULT, 60);
        Coach coach4 = new Coach("Кривошеев", "Пётр 2", "Семёнович");

        TrainingSession trainingSession1_13_1 = new TrainingSession(group1, coach1,
                DayOfWeek.MONDAY, new TimeOfDay(13, 0));
        TrainingSession trainingSession1_13_2 = new TrainingSession(group2, coach2,
                DayOfWeek.MONDAY, new TimeOfDay(13, 0));
        TrainingSession trainingSession7_13 = new TrainingSession(group2, coach2,
                DayOfWeek.SUNDAY, new TimeOfDay(13, 0));
        TrainingSession trainingSession7_14 = new TrainingSession(group3, coach3,
                DayOfWeek.SUNDAY, new TimeOfDay(14, 0));
        TrainingSession trainingSession7_15 = new TrainingSession(group4, coach4,
                DayOfWeek.SUNDAY, new TimeOfDay(15, 0));


        timetable.addNewTrainingSession(trainingSession1_13_1);
        timetable.addNewTrainingSession(trainingSession1_13_1);
        timetable.addNewTrainingSession(trainingSession1_13_1);
        timetable.addNewTrainingSession(trainingSession1_13_1);
        timetable.addNewTrainingSession(trainingSession1_13_1);
        timetable.addNewTrainingSession(trainingSession1_13_1);
        timetable.addNewTrainingSession(trainingSession1_13_2);
        timetable.addNewTrainingSession(trainingSession7_13);
        timetable.addNewTrainingSession(trainingSession7_14);
        timetable.addNewTrainingSession(trainingSession7_14);
        timetable.addNewTrainingSession(trainingSession7_15);

        ArrayList<CounterOfTrainings> listCounterOfTrainings = timetable.getCountByCoaches();
        System.out.println(listCounterOfTrainings.size());
        assertEquals(4, listCounterOfTrainings.size());

    }

}
