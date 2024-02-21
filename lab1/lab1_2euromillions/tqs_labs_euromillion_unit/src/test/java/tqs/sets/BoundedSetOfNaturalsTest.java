/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tqs.sets;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import tqs.sets.BoundedSetOfNaturals;

/**
 * @author ico0
 */
class BoundedSetOfNaturalsTest {
    private BoundedSetOfNaturals setA;
    private BoundedSetOfNaturals setB;
    private BoundedSetOfNaturals setC;

    private BoundedSetOfNaturals setD;


    @BeforeEach
    public void setUp() {
        setA = new BoundedSetOfNaturals(1);
        setB = BoundedSetOfNaturals.fromArray(new int[]{10, 20, 30, 40, 50, 60});
        setC = BoundedSetOfNaturals.fromArray(new int[]{50, 60});
        setD = new BoundedSetOfNaturals(3);
    }

    @AfterEach
    public void tearDown() {
        setA = setB = setC = setD = null;
    }

    //@Disabled("TODO revise test logic")
    @Test
    public void testAddElement() {
        setD.add(99);
        assertThrows(IllegalArgumentException.class, () -> setD.add(99), "add: duplicate element not detected.");

        assertThrows(IllegalArgumentException.class, () -> setD.add(-99), "add: negative element not detected.");

        setA.add(99);
        assertTrue(setA.contains(99), "add: added element not found in set.");
        assertEquals(1, setA.size());

    }

    //@Disabled("TODO revise to test the construction from invalid arrays")
    @Test
    public void testAddFromBadArray() {
        int[] elems = new int[]{10, -20, -30};

        // must fail with exception
        assertThrows(IllegalArgumentException.class, () -> setA.add(elems));
    }

    @Test
    public void testIntersection() {
        setD.add(10);
        assertTrue(setB.intersects(setC));
        assertFalse(setD.intersects(setC));
    }

}
