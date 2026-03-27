package ru.yandex.practicum.gym;

import java.util.Objects;
import java.util.Comparator;

public class CounterOfTrainings implements Comparable<CounterOfTrainings> {
    private Integer count;
    private Coach coach;

    public CounterOfTrainings(Integer count, Coach coach) {
        this.count = count;
        this.coach = coach;
    }

    @Override
    public int compareTo(CounterOfTrainings o) {
        return Integer.compare(o.count,this.count);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CounterOfTrainings counterOfTrainings = (CounterOfTrainings) o;
        return Objects.equals(counterOfTrainings.coach, coach);
    }

    @Override
    public int hashCode() {
        return Objects.hash(count, coach);
    }

    public Integer getCount() {
        return count;
    }

    public Coach getCoach() {
        return coach;
    }

}
