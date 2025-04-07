package org.example;

import java.util.PriorityQueue;

/**
 * Adaptador de java.util.PriorityQueue para cumplir la interfaz IPriorityQueue.
 * @param <E> Tipo de elementos con prioridad (deben ser comparables).
 */
public class JCFPriorityQueue<E extends Comparable<E>> implements IPriorityQueue<E> {
    private PriorityQueue<E> pq = new PriorityQueue<>();

    @Override
    public boolean add(E element) {
        return pq.add(element);
    }

    @Override
    public E remove() {
        // poll() devuelve null si la cola está vacía
        return pq.poll();
    }

    @Override
    public E peek() {
        return pq.peek();
    }

    @Override
    public boolean isEmpty() {
        return pq.isEmpty();
    }

    @Override
    public int size() {
        return pq.size();
    }
}
