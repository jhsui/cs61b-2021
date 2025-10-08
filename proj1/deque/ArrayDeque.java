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
    private int firstLength;
    private int lastLength;
//    private int trueFirst;

    public ArrayDeque() {
        arr = (T[]) new Object[8];
        this.size = 0;

        this.nextFirst = 7;
        this.nextLast = 0;

        this.firstLength = 0;
        this.lastLength = 0;


    }

    // the problem I ignore is that the new copied arr in the newArray may be in sequence
    // if the operation is just add one side and remove another side.
    private void resize(int capacity) {
        T[] newArray = (T[]) new Object[capacity];


        if (this.firstLength < 0) {
            this.lastLength += this.firstLength;
            this.firstLength = 0;
        } else if (this.lastLength < 0) {
            this.firstLength += this.lastLength;
            this.lastLength = 0;
        }

        // the only thing I can decide is where the new array at.

        if (this.firstLength == 0 && this.lastLength != 0 && this.lastLength < this.arr.length) {

            System.arraycopy(this.arr, 0, newArray, 0, this.lastLength);

        } else if (this.lastLength == 0 && this.firstLength != 0 && this.firstLength < this.arr.length) {


            int srcPos = this.nextFirst + 1;
            if (srcPos >= this.arr.length) {
                srcPos -= this.arr.length;
            }
            System.arraycopy(this.arr, srcPos, newArray, newArray.length - this.firstLength, this.firstLength);

        } else {

            // second half
            System.arraycopy(this.arr, 0, newArray, 0, this.lastLength);
            // first half
            System.arraycopy(this.arr, this.lastLength, newArray, newArray.length - this.firstLength, this.firstLength);

        }


        this.nextLast = this.lastLength;
        this.nextFirst = newArray.length - this.firstLength - 1;
        if (this.nextFirst < 0) {
            this.nextFirst = 0;
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

        arr[this.nextFirst] = item;

        this.firstLength++;

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
//        if (this.nextLast > this.arr.length - 1) {
//            this.nextLast -= this.size();
//        }

        this.lastLength++;

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
        // nextFist + 1 is the actual location in the array, but here I should pass the index used by user.
        T removedItem = this.get(0);

        this.firstLength--;

        this.nextFirst++;
        if (this.nextFirst > this.arr.length - 1 && this.firstLength < 0) {
            this.nextFirst = this.nextFirst - this.arr.length;
        }
        if (this.nextFirst > this.arr.length - 1) {
            this.nextFirst = this.firstLength + 1 - this.arr.length;
        }


        this.size--;
//        if (this.size() == 0) {
//            this.nextLast--;
//        }
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

//        this.nextLast--;
//        if (this.nextLast < 0) {
//            this.nextLast += this.arr.length;
//        }

        this.lastLength--;

        this.nextLast--;
        if (this.nextLast < 0) {
            this.nextLast = this.arr.length + this.lastLength;
        }

        this.size--;
        // when the item is the last one! aka when nextLast == nextFirst
//        if (this.size() == 0) {
//            this.nextFirst--;
//        }


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

        // is here correct?
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
