package org.example;

/**
 * Interfaz para colas con prioridad.
 * @param <E> Tipo de elementos con prioridad (deben ser comparables).
 */
public interface IPriorityQueue<E> {

    boolean add(E element);

    E remove();


    E peek();


    boolean isEmpty();


    int size();
}
