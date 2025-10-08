package deque;

import java.util.Iterator;
import java.util.Objects;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {

    /*
     * We strongly recommend that you treat your array as circular for this exercise.
     * In other words, if your front item is at position zero, and you addFirst,
     * the new front should loop back around to the end of the array
     * (so the new front item in the deque will be the last item in the underlying array).
     */

    private T[] arr;
    private int size;
    private int nextFirst;
    private int nextLast;
    private int firstPartInTheSecondHalf;


    public ArrayDeque() {
        arr = (T[]) new Object[8];
        this.size = 0;

        this.nextFirst = 3;
        this.nextLast = 4;

        this.firstPartInTheSecondHalf = 0;

    }


    private void resize(int capacity) {
        T[] newArray = (T[]) new Object[capacity];

        if (this.firstPartInTheSecondHalf == 0) {
            System.arraycopy(this.arr, this.nextFirst + 1, newArray, newArray.length / 4, this.arr.length);

        } else {
            for(int i = 0; i <= this.firstPartInTheSecondHalf; i++){
                newArray[]
            }

        }


    }

    @Override
    public void addFirst(T item) {
        if (this.size() == arr.length) {
            this.resize(this.arr.length * 2);
        }

        arr[this.nextFirst] = item;

        this.nextFirst--;
        if (this.nextFirst < 0) {
            this.nextFirst += this.arr.length;
        }

        if (this.nextFirst > this.arr.length * 1.0 / 2) {
            this.firstPartInTheSecondHalf += 1;
        }

        this.size++;
    }

    @Override
    public void addLast(T item) {
        if (this.size() == arr.length) {
            this.resize(this.arr.length * 2);
        }

        arr[this.nextLast] = item;

        this.nextLast++;
        if (this.nextLast > this.arr.length - 1) {
            this.nextLast -= this.arr.length;
        }

        this.size++;
    }


    @Override
    public int size() {
        return this.size;
    }


    @Override
    public void printDeque() {
        for (T a : this.arr) {
            System.out.print(a.toString() + " ");
        }
        System.out.println();
    }


    @Override
    public T removeFirst() {
        if (this.size() == 0) {
            return null;
        }

        T removedItem = this.get(0);

        this.nextFirst++;

        this.size--;
        if (this.size() < 0) {
            this.size = 0;
        }
        if (this.size() <= arr.length * 0.25 && arr.length > 8) {
            this.resize(this.arr.length / 2);
        }

        if (this.nextFirst > this.arr.length * 1.0 / 2) {
            this.firstPartInTheSecondHalf -= 1;
        }

        return removedItem;
    }


    @Override
    public T removeLast() {
        if (this.size() == 0) {
            return null;
        }

        T removedItem = this.get(this.size() - 1);

        this.nextLast--;

        this.size--;

        if (this.size() <= arr.length * 0.25 && arr.length > 8) {
            this.resize(this.arr.length / 2);
        }

        return removedItem;
    }


    @Override
    public T get(int index) {
        if (this.size() == 0 || index < 0 || index > this.size() - 1) {
            return null;
        }

        int realIndex = this.nextFirst + 1 + index;

        if (realIndex > this.arr.length - 1) {
            realIndex -= this.arr.length;
        }

        return this.arr[realIndex];
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o instanceof ArrayDeque) {
            if (this.size() != ((ArrayDeque<?>) o).size()) {
                return false;
            }
            for (int i = 0; i < this.size(); i++) {
                if (!Objects.equals(((ArrayDeque<?>) o).get(i), this.get(i))) {
                    return false;
                }
            }
            return true;
        }

        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    private class ArrayDequeIterator implements Iterator<T> {

        private int iteratorIndex;

        public ArrayDequeIterator() {
            this.iteratorIndex = 0;
        }

        @Override
        public boolean hasNext() {
            return this.iteratorIndex < size();
        }

        @Override
        public T next() {
            T next = get(iteratorIndex);
            iteratorIndex += 1;
            return next;
        }
    }


}
