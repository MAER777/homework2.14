package com.example.demo1;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class IntegerListTest {
    private IntegerListImpl out = new IntegerListImpl(5);

    public static Stream<Arguments> argumentsForContainsPositiveTest() {
        return Stream.of(
                Arguments.of(1),
                Arguments.of(2),
                Arguments.of(3)
        );
    }
    public static Stream<Arguments> argumentsForContainsNegativeTest() {
        return Stream.of(
                Arguments.of(6),
                Arguments.of(7),
                Arguments.of(8)
        );
    }

    public static Stream<Arguments> argumentsIndexForContainsPositiveTest() {
        return Stream.of(
                Arguments.of(1, 0),
                Arguments.of(2, 1),
                Arguments.of(3, 2)
        );
    }

    public static Stream<Arguments> argumentsIndexForContainsNegativeTest() {
        return Stream.of(
                Arguments.of(6, -1),
                Arguments.of(7, -1),
                Arguments.of(8, -1)
        );
    }

    public static Stream<Arguments> argumentsLastIndexTest() {
        return Stream.of(
                Arguments.of(1, 5),
                Arguments.of(2, 6),
                Arguments.of(6, -1)
        );
    }

    public static Stream<Arguments> argumentsGetPositiveTest() {
        return Stream.of(
                Arguments.of(1, 0),
                Arguments.of(2, 1),
                Arguments.of(3, 2)
        );
    }

    public static Stream<Arguments> argumentsEqualsNegativeTest() {
        return Stream.of(
                Arguments.of((new IntegerListImpl(1, 2, 3))),
                Arguments.of(new IntegerListImpl(1, 4, 7, 2)),
                Arguments.of(new IntegerListImpl(3, 2, 1))
        );
    }

    @BeforeEach
    public void fillList() {
        out.add(1);
        out.add(2);
        out.add(3);
        out.add(4);
        out.add(5);
    }

    @AfterEach
    public void clearList() {
        out.clear();
    }

    @Test
    public void addPositiveTest() {
        int size = out.size();
        assertEquals(6, out.add(6));
        assertEquals(size + 1, out.size());
    }

    @Test
    public void addIndexPositiveTest() {
        int size = out.size();
        int index = 1;
        assertEquals(6, out.add(index, 6));
        assertEquals(index, out.indexOf(6));
        assertEquals(size + 1, out.size());
    }

    @Test
    public void addIndexNegativeTest() {
        assertThrows(StringListIndexOutBoundsException.class, () -> out.add(5, 6));
    }

    @Test
    public void setPositiveTest() {
        int size = out.size();
        int index = 1;
        assertEquals(6, out.set(index, 6));
        assertEquals(index, out.indexOf(6));
        assertEquals(size, out.size());
    }

    @Test
    public void setNegativeTest() {
        assertThrows(StringListIndexOutBoundsException.class, () -> out.set(5, 6));
    }

    @Test
    public void removeByValuePositiveTest() {
        int size = out.size();
        assertEquals(1, out.remove(0));
        assertEquals(size - 1, out.size());
    }

    @Test
    public void removeByValueNegativeTest() {
        assertThrows(StringListIndexOutBoundsException.class, () -> out.remove(6));
    }

    @Test
    public void removeByIndexPositiveTest() {
        int size = out.size();
        assertEquals(1, out.remove(0));
        assertEquals(size - 1, out.size());
    }

    @Test
    public void removeByIndexNegativeTest() {
        assertThrows(StringListIndexOutBoundsException.class, () -> out.remove(5));
    }

    @ParameterizedTest
    @MethodSource("argumentsForContainsPositiveTest")
    public void containsPositiveTest(Integer str) {
        assertTrue(out.contains(str));
    }

    @ParameterizedTest
    @MethodSource("argumentsForContainsNegativeTest")
    public void containsNegativeTest (Integer str) {
        assertFalse(out.contains(str));
    }

    @ParameterizedTest
    @MethodSource("argumentsIndexForContainsPositiveTest")
    public void argumentsIndexForContainsPositiveTest (Integer str, int index) {
        assertEquals(index, out.indexOf(str));
    }

    @ParameterizedTest
    @MethodSource("argumentsIndexForContainsNegativeTest")
    public void argumentsIndexForContainsNegativeTest (Integer str, int index) {
        assertEquals(index, out.indexOf(str));
    }

    @ParameterizedTest
    @MethodSource("argumentsLastIndexTest")
    public void argumentsLastIndexTest(Integer str, int index) {
        out.add(1);
        out.add(2);
        assertEquals(index, out.lastIndexOf(str));
    }

    @ParameterizedTest
    @MethodSource("argumentsGetPositiveTest")
    public void argumentsGetPositiveTest(Integer str, int index) {
        assertEquals(str, out.get(index));
    }

    @Test
    public void getNegativeTest() {
        assertThrows(StringListIndexOutBoundsException.class, () -> out.get(5));
    }

    @Test
    public void equalsPositiveTest() {
        IntegerListImpl test = new IntegerListImpl(5);
        test.add(1);
        test.add(2);
        test.add(3);
        test.add(4);
        test.add(5);
        assertTrue(out.equals(test));
    }

    @Test
    public void equalsNullNegativeTest() {
        assertThrows(InvalidArgumentException.class, () -> out.equals(null));
    }

    @ParameterizedTest
    @MethodSource("argumentsEqualsNegativeTest")
    public void argumentsEqualsNegativeTest(IntegerListImpl str) {
        assertFalse(out.equals(str));
    }

    @Test
    public void isEmptyPositiveTest() {
        IntegerListImpl test = new IntegerListImpl(5);
        assertTrue(test.isEmpty());
    }

    @Test
    public void isEmptyNegativeTest() {
        assertFalse(out.isEmpty());
    }

    @Test
    public void toArrayTest() {
        Integer[] test = {1, 2, 3, 4,5};
        assertArrayEquals(test, out.toArray());
    }
}
