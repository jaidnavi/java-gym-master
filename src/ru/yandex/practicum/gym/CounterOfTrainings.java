package ru.yandex.practicum.gym;

import java.util.Objects;
import java.util.Comparator;

public class CounterOfTrainings implements Comparator<CounterOfTrainings> {
    private Integer count;
    private Coach coach;

    public CounterOfTrainings(Integer count, Coach coach) {
        this.count = count;
        this.coach = coach;
    }

    public Integer getCount() {
        return count;
    }

    public Coach getCoach() {
        return coach;
    }

    @Override
    public int compare(CounterOfTrainings item1, CounterOfTrainings item2) {
        return Integer.compare(item1.count, item2.count);
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

}
