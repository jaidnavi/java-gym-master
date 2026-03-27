package ru.yandex.practicum.gym;
import java.util.Collections;

import java.util.*;

public class Timetable {



    private HashMap<DayOfWeek, DayTable> timetable = new HashMap<>();

    public void addNewTrainingSession(TrainingSession trainingSession) {
        //сохраняем занятие в расписании
        DayTable dayTable = timetable.getOrDefault(trainingSession.getDayOfWeek(), new DayTable());
        dayTable.addTraining(trainingSession.getTimeOfDay(), trainingSession);
        timetable.put(trainingSession.getDayOfWeek(), dayTable);
    }

    public ArrayList<TrainingSession> getAllTrainingSessions() {
        ArrayList<TrainingSession> trainingSessions = new ArrayList<>();
        for (DayOfWeek dayOfWeek : timetable.keySet()) {
            if (timetable.get(dayOfWeek) != null) {
                trainingSessions.addAll(timetable.get(dayOfWeek).getAllTrainings());
            }
        }
        return trainingSessions;
    }

    public ArrayList<TrainingSession> getTrainingSessionsForDay(DayOfWeek dayOfWeek) {
        ArrayList<TrainingSession> trainingSessions = new ArrayList<>();
        if (timetable.get(dayOfWeek) != null) {
            trainingSessions.addAll(timetable.get(dayOfWeek).getAllTrainings());
        }
        return trainingSessions;
    }

    public ArrayList<TrainingSession> getTrainingSessionsForDayAndTime(DayOfWeek dayOfWeek, TimeOfDay timeOfDay) {
        ArrayList<TrainingSession> trainingSessions = new ArrayList<>();
        if (timetable.get(dayOfWeek) != null) {
            if (timetable.get(dayOfWeek).getTrainings(timeOfDay) != null) {
                trainingSessions.addAll(timetable.get(dayOfWeek).getTrainings(timeOfDay));
            }
        }
        return trainingSessions;
    }

    public List<CounterOfTrainings> getCountByCoaches() {
        List<CounterOfTrainings> listOrderCoaches = new ArrayList<>();

        Map<Coach, Integer> listCoaches = new LinkedHashMap<>();
        ArrayList<TrainingSession> listTrainingSession = getAllTrainingSessions();
        for (TrainingSession trainingSession : listTrainingSession) {
            listCoaches.put(trainingSession.getCoach(), listCoaches.getOrDefault(trainingSession.getCoach(), 0) + 1);
        }

        for (Coach coach : listCoaches.keySet()) {
            CounterOfTrainings counterOfTrainings = new CounterOfTrainings(listCoaches.get(coach), coach);
            listOrderCoaches.add(counterOfTrainings);
        }

        Collections.sort(listOrderCoaches);
        return listOrderCoaches;
    }

}
