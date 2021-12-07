package com.github.javafaker;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.isIn;
import static org.hamcrest.Matchers.isOneOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class OptionsTest extends AbstractFakerTest {

    private String[] options;

    @Before
    public void setupOptions() {
        options = new String[]{"A", "B", "C"};
    }

    @Test
    public void testOptionWithArray() {
        assertThat(faker.options().option(options), isOneOf(options));
    }

    @Test
    public void testOptionWithVarargsString() {
        assertThat(faker.options().option("A", "B", "C"), isOneOf(options));
    }

    @Test
    public void testOptionWithVarargsInteger() {
        Integer[] integerOptions = new Integer[] { 1, 3, 4, 5};
        assertThat(faker.options().option(1, 3, 4, 5), isOneOf(integerOptions));
    }

    @Test
    public void testOptionWithEnum() {
        assertThat(faker.options().option(Day.class), isOneOf(Day.values()));
    }

    @Test
    public void testNextArrayElement() {
        Integer[] array = new Integer[] { 1, 2, 3, 5, 8, 13, 21 };

        for (int i = 1; i < 10; i++) {
            assertThat(faker.options().nextElement(array), isIn(array));
        }
    }

    @Test
    public void testNextListElement() {
        List<Integer> list = Arrays.asList(1, 2, 3, 5, 8, 13, 21);
        for (int i = 1; i < 10; i++) {
            assertThat(faker.options().nextElement(list), isIn(list));
        }
    }


    // CS427 Issue link: https://github.com/DiUS/java-faker/issues/664
    @Test
    public void testRandomlyNull() {
        String randomlyNull = faker.options().randomlyNull(faker.company().logo());
        assertTrue(helper(randomlyNull));
    }

    public enum Day {
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
    }

    public Boolean helper(String random) {
        if ( random == null || random instanceof String) {
            return true;
        }
        return false;
    }

}


