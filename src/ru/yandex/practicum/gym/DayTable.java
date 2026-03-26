package ru.yandex.practicum.gym;

import java.util.Map;
import java.util.TreeMap;
import java.util.ArrayList;
import java.util.Comparator;

public class DayTable {

    private final Map<TimeOfDay, ArrayList<TrainingSession>> dayTable;

    public DayTable() {
        Comparator<TimeOfDay> comparator = Comparator.comparingInt(timeOfDay -> timeOfDay.getHours() * 60 + timeOfDay.getMinutes());
        dayTable = new TreeMap<>(comparator);
    }

    public void addTraining(TimeOfDay timeOfDay, TrainingSession trainingSession) {
        ArrayList<TrainingSession> daysTable = dayTable.getOrDefault(timeOfDay, new ArrayList<>());
        daysTable.add(trainingSession);
        dayTable.put(timeOfDay, daysTable);
    }

    public ArrayList<TrainingSession> getTrainings(TimeOfDay timeOfDay) {
        return dayTable.get(timeOfDay);
    }

    public ArrayList<TrainingSession> getAllTrainings() {
        ArrayList<TrainingSession> trainingSessions = new ArrayList<>();
        for (TimeOfDay timeOfDay : dayTable.keySet()) {
            trainingSessions.addAll(dayTable.get(timeOfDay));
        }
        return trainingSessions;
    }
/*
    @Override
    public String toString() {
        String result = "";
        for (TimeOfDay timeOfDay : dayTable.keySet()) {
            result = result + "Время: " + timeOfDay.getHours() + ":" + timeOfDay.getMinutes() + ":\n";
            for (int i = 0; i < dayTable.get(timeOfDay).size(); i++) {
                result = result + dayTable.get(timeOfDay).get(i).getGroup().getTitle() + "\n";
            }
        }
        return (result);
    }
*/
}