package ru.yandex.practicum.gym;

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

    public ArrayList<CounterOfTrainings> getCountByCoaches() {
        ArrayList<CounterOfTrainings> listOrderCoaches = new ArrayList<>();
        return listOrderCoaches;
    }
/*
    @Override
    public String toString() {
        String result = new String();
        for (DayOfWeek dayOfWeek : timetable.keySet()) {
            result = result + "День " + dayOfWeek+":\n";
            result = result + timetable.get(dayOfWeek) +"\n";
        }
        return(result);
    }
*/
}
