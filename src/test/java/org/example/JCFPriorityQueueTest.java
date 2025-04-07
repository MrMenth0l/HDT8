package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class JCFPriorityQueueTest {

    @Test
    public void testEmptyQueue() {
        IPriorityQueue<Integer> queue = new JCFPriorityQueue<>();
        assertTrue(queue.isEmpty(), "Queue should be empty initially");
        assertEquals(0, queue.size(), "Size should be 0");
        assertNull(queue.peek(), "Peek on empty queue should be null");
        assertNull(queue.remove(), "Remove on empty queue should be null");
    }

    @Test
    public void testAddPeekAndSize() {
        IPriorityQueue<Integer> queue = new JCFPriorityQueue<>();
        queue.add(5);
        queue.add(1);
        queue.add(3);
        assertFalse(queue.isEmpty(), "Queue should not be empty after adds");
        assertEquals(3, queue.size(), "Size should reflect number of elements");
        assertEquals(1, queue.peek(), "Peek should return the smallest element");
    }

    @Test
    public void testRemoveOrder() {
        IPriorityQueue<Integer> queue = new JCFPriorityQueue<>();
        int[] values = {4, 2, 7, 2, 5};
        for (int v : values) {
            queue.add(v);
        }
        int[] expectedOrder = {2, 2, 4, 5, 7};
        for (int expected : expectedOrder) {
            assertEquals(expected, queue.remove(), "Elements should be removed in ascending order");
        }
        assertTrue(queue.isEmpty(), "Queue should be empty after removing all elements");
    }

    @Test
    public void testWithPacientes() {
        IPriorityQueue<Paciente> queue = new JCFPriorityQueue<>();
        Paciente p1 = new Paciente("X", "s", 'C');
        Paciente p2 = new Paciente("Y", "s", 'A');
        Paciente p3 = new Paciente("Z", "s", 'B');
        queue.add(p1);
        queue.add(p2);
        queue.add(p3);
        assertEquals(p2, queue.remove(), "Paciente with code A should come first");
        assertEquals(p3, queue.remove(), "Paciente with code B should come second");
        assertEquals(p1, queue.remove(), "Paciente with code C should come last");
    }
}
