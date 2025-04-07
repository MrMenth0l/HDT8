package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias para la implementación VectorHeap.
 */
public class VectorHeapTest {

    @Test
    public void testEmptyHeap() {
        VectorHeap<Integer> heap = new VectorHeap<>();
        assertTrue(heap.isEmpty(), "Heap should be empty initially");
        assertEquals(0, heap.size(), "Size should be 0");
        assertNull(heap.peek(), "Peek on empty heap should be null");
        assertNull(heap.remove(), "Remove on empty heap should be null");
    }

    @Test
    public void testAddPeekAndSize() {
        VectorHeap<Integer> heap = new VectorHeap<>();
        heap.add(5);
        heap.add(1);
        heap.add(3);
        assertFalse(heap.isEmpty(), "Heap should not be empty after adds");
        assertEquals(3, heap.size(), "Size should reflect number of elements");
        assertEquals(1, heap.peek(), "Peek should return the smallest element");
    }

    @Test
    public void testRemoveOrder() {
        VectorHeap<Integer> heap = new VectorHeap<>();
        int[] values = {4, 2, 7, 2, 5};
        for (int v : values) {
            heap.add(v);
        }
        int[] expectedOrder = {2, 2, 4, 5, 7};
        for (int expected : expectedOrder) {
            assertEquals(expected, heap.remove(), "Elements should be removed in ascending order");
        }
        assertTrue(heap.isEmpty(), "Heap should be empty after removing all elements");
    }

    @Test
    public void testWithPacientes() {
        VectorHeap<Paciente> heap = new VectorHeap<>();
        Paciente p1 = new Paciente("X", "s", 'C');
        Paciente p2 = new Paciente("Y", "s", 'A');
        Paciente p3 = new Paciente("Z", "s", 'B');
        heap.add(p1);
        heap.add(p2);
        heap.add(p3);
        assertEquals(p2, heap.remove(), "Paciente with code A should come first");
        assertEquals(p3, heap.remove(), "Paciente with code B should come second");
        assertEquals(p1, heap.remove(), "Paciente with code C should come last");
    }
}
