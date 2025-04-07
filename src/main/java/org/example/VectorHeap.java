package org.example;

import java.util.ArrayList;


public class VectorHeap<E extends Comparable<E>> implements IPriorityQueue<E> {
    private ArrayList<E> data;

    public VectorHeap() {
        data = new ArrayList<>();
    }

    private static int parent(int i) {
        return (i - 1) / 2;
    }

    private static int left(int i) {
        return 2 * i + 1;
    }

    private static int right(int i) {
        return 2 * i + 2;
    }

    private void percolateUp(int index) {
        E value = data.get(index);
        while (index > 0) {
            int p = parent(index);
            E parentValue = data.get(p);
            if (value.compareTo(parentValue) >= 0) {
                break;
            }
            data.set(index, parentValue);
            index = p;
        }
        data.set(index, value);
    }

    private void pushDownRoot(int index) {
        int heapSize = data.size();
        E value = data.get(index);
        while (true) {
            int leftChild = left(index);
            if (leftChild >= heapSize) {
                break;
            }
            int rightChild = right(index);
            int minChild = leftChild;
            if (rightChild < heapSize && data.get(rightChild).compareTo(data.get(leftChild)) < 0) {
                minChild = rightChild;
            }
            if (data.get(minChild).compareTo(value) >= 0) {
                break;
            }
            data.set(index, data.get(minChild));
            index = minChild;
        }
        data.set(index, value);
    }

    @Override
    public boolean add(E element) {
        data.add(element);
        percolateUp(data.size() - 1);
        return true;
    }

    @Override
    public E remove() {
        if (data.isEmpty()) {
            return null;
        }
        E min = data.get(0);
        E last = data.remove(data.size() - 1);
        if (!data.isEmpty()) {
            data.set(0, last);
            pushDownRoot(0);
        }
        return min;
    }

    @Override
    public E peek() {
        return data.isEmpty() ? null : data.get(0);
    }

    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }

    @Override
    public int size() {
        return data.size();
    }

    @Override
    public String toString() {
        return data.toString();
    }
}
