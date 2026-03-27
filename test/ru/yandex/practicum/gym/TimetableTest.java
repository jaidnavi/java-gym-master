package ru.yandex.practicum.gym;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TimetableTest {

    Timetable timetable = new Timetable();

    @Test
    void testGetTrainingSessionsForDaySingleSession() {
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
    void testGetCountByCoaches() {
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

        List<CounterOfTrainings> listCounterOfTrainings = timetable.getCountByCoaches();
        //Проверить, что без тренировок список пуст
        assertEquals(0, listCounterOfTrainings.size());

        timetable.addNewTrainingSession(trainingSession1_13_1);
        listCounterOfTrainings = timetable.getCountByCoaches();
        //Проверить, что в список добавилось одно значение
        assertEquals(1, listCounterOfTrainings.size());
        //Проверить, что счетчик = 1
        assertEquals(1, listCounterOfTrainings.getFirst().getCount());

        timetable.addNewTrainingSession(trainingSession1_13_1);
        listCounterOfTrainings = timetable.getCountByCoaches();
        //Проверить, что при добавлении того же тренера, список не расширяется
        assertEquals(1, listCounterOfTrainings.size());
        //Проверить, что счетчик растет
        assertEquals(2, listCounterOfTrainings.getFirst().getCount());

        timetable.addNewTrainingSession(trainingSession1_13_1);
        timetable.addNewTrainingSession(trainingSession1_13_1);
        timetable.addNewTrainingSession(trainingSession1_13_1);
        timetable.addNewTrainingSession(trainingSession1_13_1);
        listCounterOfTrainings = timetable.getCountByCoaches();
        //Проверить, что при добавлении того же тренера, список не расширяется
        assertEquals(1, listCounterOfTrainings.size());

        timetable.addNewTrainingSession(trainingSession1_13_2);
        listCounterOfTrainings = timetable.getCountByCoaches();

        //Проверить, что при добавлении нового тренера, список расширяется
        assertEquals(2, listCounterOfTrainings.size());

        timetable.addNewTrainingSession(trainingSession7_13);
        timetable.addNewTrainingSession(trainingSession7_14);
        timetable.addNewTrainingSession(trainingSession7_14);
        timetable.addNewTrainingSession(trainingSession7_15);

        //Проверить итоговое количество тренеров в расписании
        listCounterOfTrainings = timetable.getCountByCoaches();
        assertEquals(4, listCounterOfTrainings.size());


        //для визуальной проверки сортировки
        /*
        for (CounterOfTrainings listCounterOfTraining : listCounterOfTrainings) {
            System.out.println(listCounterOfTraining.getCount() + " - " + listCounterOfTraining.getCoach());
        }
         */

        //Проверить корректность сортировки в списке
        for (int i = 0; i < listCounterOfTrainings.size() - 1; i++) {
            Assertions.assertTrue(listCounterOfTrainings.get(i).getCount() >= listCounterOfTrainings.get(i + 1).getCount());
        }
    }
}
