package ru.yandex.practicum.gym;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class GroupTest {
    Group group = new Group("Акробатика для детей", Age.CHILD, 60);

    @Test
    void testGetTitle() {
        Assertions.assertEquals("Акробатика для детей", group.getTitle());
    }

    @Test
    void testGetAge() {
        Assertions.assertEquals(Age.CHILD, group.getAge());
    }

    @Test
    void testGetDuration() {
        Assertions.assertEquals(60, group.getDuration());
    }
}