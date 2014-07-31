package ca.noko.exponentialdecaysort;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ExponentialDecaySortTest {

    private ExponentialDecaySort expSorter;

    @Before
    public void setUp() throws Exception {
        expSorter = new ExponentialDecaySort();
    }

    @Test
    public void testExponentialDecaySort1to5() throws Exception {
        ArrayList<Integer> testArray = new ArrayList<Integer>(){{
            add(5); add(4); add(3); add(2); add(1);
        }};
        ArrayList<Integer> sortedArray = new ArrayList<Integer>(){{
            add(1); add(2); add(3); add(4); add(5);
        }};
        expSorter.exponentialDecaySort(testArray);
        assertEquals(sortedArray, testArray);
    }
    @Test
    public void testExponentialDecaySort0to5() throws Exception {
        ArrayList<Integer> testArray = new ArrayList<Integer>(){{
            add(5); add(4); add(3); add(1); add(0);
        }};
        ArrayList<Integer> sortedArray = new ArrayList<Integer>(){{
            add(0); add(1); add(3); add(4); add(5);
        }};
        expSorter.exponentialDecaySort(testArray);
        assertEquals(sortedArray, testArray);
    }
    @Test
    public void testExponentialDecaySortNeg5to0() throws Exception {
        ArrayList<Integer> testArray = new ArrayList<Integer>(){{
            add(0); add(-1); add(-3); add(-4); add(-5);
        }};
        ArrayList<Integer> sortedArray = new ArrayList<Integer>(){{
            add(-5); add(-4); add(-3); add(-1); add(0);
        }};
        expSorter.exponentialDecaySort(testArray);
        assertEquals(sortedArray, testArray);
    }
}