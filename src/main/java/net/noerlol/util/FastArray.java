package net.noerlol.util;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class FastArray<T> implements Iterable<T> {
    private T[] actual;
    private T[] temp;

    public FastArray() {
        actual = (T[]) new Object[0];
        temp = (T[]) new Object[0];
    }

    public FastArray<T> loadArray(T[] array) {
        actual = array;
        return this;
    }

    public FastArray<T> add(T element) {
        temp = actual;
        actual = (T[]) new Object[temp.length + 1];
        for (int i = 0; i < temp.length; i++) {
            actual[i] = temp[i];
        }
        actual[actual.length - 1] = element;
        return this;
    }

    public FastArray<T> remove(int index) {
        if (index < 0 || index >= actual.length) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + actual.length);
        }

        T[] newArray = (T[]) new Object[actual.length - 1];
        for (int i = 0, j = 0; i < actual.length; i++) {
            if (i != index) {
                newArray[j++] = actual[i];
            }
        }

        actual = newArray;
        return this;
    }

    public FastArray<T> remove(T element) {
        int index = -1;
        // Find the index of the element to remove
        for (int i = 0; i < actual.length; i++) {
            if (actual[i].equals(element)) {
                index = i;
                break;
            }
        }

        // If the element is not found, return without modifying the array
        if (index == -1) {
            return this;
        }

        // Create a new array with one less element
        T[] newArray = (T[]) new Object[actual.length - 1];
        for (int i = 0, j = 0; i < actual.length; i++) {
            if (i != index) {
                newArray[j++] = actual[i];
            }
        }

        actual = newArray;
        return this;
    }

    public T get(int index) {
        return actual[index];
    }

    public int get(T t) {
        int index = 0;
        for (int i = 0; i < actual.length; i++) {
            if (actual[i].equals(t)) {
                index = i;
                break;
            }
        }
        return index;
    }

    public T[] toArray() {
        return actual;
    }

    public int size() {
        return actual.length;
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<T> iterator() {
        return null;
    }

    private class FastArrayIterator implements Iterator<T> {
        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < actual.length;
        }

        @Override
        public T next() {
            return actual[currentIndex++];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Remove not supported.");
        }// Todo: diz
    }
}
