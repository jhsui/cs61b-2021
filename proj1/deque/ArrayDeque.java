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


    public ArrayDeque() {
        arr = (T[]) new Object[8];
        this.size = 0;

        this.nextFirst = 7;
        this.nextLast = 0;


    }

    // the problem I ignore is that the new copied arr in the newArray may be in sequence
    // if the operation is just add one side and remove another side.
    private void resize(int capacity) {
        T[] newArray = (T[]) new Object[capacity];


        if (capacity > this.arr.length) {
            // first half
            System.arraycopy(this.arr, this.nextFirst, newArray, newArray.length - (this.arr.length - this.nextFirst), this.arr.length - this.nextFirst);

            // second half
            System.arraycopy(this.arr, 0, newArray, 0, this.nextFirst + 1);

            this.nextLast = this.nextFirst + 1;
            this.nextFirst = newArray.length - (this.arr.length - this.nextFirst);
        }

        if (capacity < this.arr.length) {
            if (this.nextFirst > this.nextLast) {
                System.arraycopy(this.arr, this.nextFirst + 1, newArray, Math.abs((this.arr.length - this.nextFirst) - newArray.length - 1), this.arr.length - 1 - this.nextFirst);
                System.arraycopy(this.arr, 0, newArray, 0, this.nextLast);

                this.nextLast = this.nextLast;
                this.nextFirst = this.nextFirst - newArray.length;
            }

            if (this.nextFirst < this.nextLast) {
                System.arraycopy(this.arr, this.nextFirst + 1, newArray, Math.abs((this.arr.length - this.nextFirst) - newArray.length - 1), this.size());

                this.nextLast = this.nextLast - newArray.length;
                this.nextFirst = this.nextFirst;
            }


        }


        this.arr = newArray;

        /* how to deal with the nextFirst and nextLast?
         *
         * 1. re-put them as the order of user array.
         * 2. keep the original sequence.
         *
         * let us first think about big case:
         *
         * no matter what, it is a circular,
         * so, I should put space between first and last;
         *
         * yes, let's first think about the case of size = 16.
         */

    }

    // When the nextFist = nextLast, what should we do?
    @Override
    public void addFirst(T item) {
        if (this.size() == arr.length) {
            this.resize(this.arr.length * 2);
        }

        if (this.nextFirst < 0) {
            this.nextFirst += this.arr.length;
        }
        if (this.nextFirst > this.arr.length - 1) {
            this.nextFirst -= this.arr.length;
        }

        arr[this.nextFirst] = item;


        this.nextFirst--;
        if (this.nextFirst < 0) {
            this.nextFirst += this.arr.length;
        }


        this.size++;
    }

    @Override
    public void addLast(T item) {
        if (this.size() == arr.length) {
            this.resize(this.arr.length * 2);
        }

        if (this.nextLast > this.arr.length - 1) {
            this.nextLast -= this.arr.length;
        }

        arr[this.nextLast] = item;

        this.nextLast++;

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
        if (this.nextFirst > this.arr.length) {
            this.nextFirst = this.nextFirst - this.arr.length;
        }

        this.size--;
        if (this.size() < 0) {
            this.size = 0;
        }


        if (this.size() <= arr.length * 0.25 && arr.length > 8) {
            this.resize(this.arr.length / 2);
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
        if (this.nextLast < 0) {
            this.nextLast += this.arr.length;
        }


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

        // here happened a jump because of the size became equal to the arr.length.
        int realIndex = this.nextFirst + 1 + index;

        // is it here correct?
        if (realIndex > this.arr.length - 1) {
            realIndex -= this.arr.length;
        }
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
