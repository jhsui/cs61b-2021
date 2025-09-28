package deque;

import java.util.HashMap;

public class ArrayDeque<T> {

//    private static class Node<U> {
//
//        private final U[] arrNode;
////        private final U item;
////        private final int realIndex;
//
//        public Node(U item, int realIndex) {
////            this.item = item;
////            this.realIndex = realIndex;
//            this.arrNode = (U[]) new Object[]{item, realIndex};
//
//        }
//    }


    /*
     * We strongly recommend that you treat your array as circular for this exercise.
     * In other words, if your front item is at position zero, and you addFirst,
     * the new front should loop back around to the end of the array
     * (so the new front item in the deque will be the last item in the underlying array).
     */

    /// I must use array. That is a problem. How can make an array circular.

    private T[] arr;
    private int size;
    private int nextFirst;
    private int nextLast;
//    private int realIndex;

    public ArrayDeque() {
        arr = (T[]) new Object[8];
        this.size = 0;
        this.nextFirst = 7;

        // TODO: should they both start from 0 ???

        this.nextLast = 0;

        //TODO: modify nextFirst and nextLast when add, remove and when they are the same.

//        this.realIndex = 0;
    }

//    public ArrayDeque(T item) {
//        arr = (T[]) new Object[8];
//        this.size = 0;
//        this.nextFirst = 0;
//        this.nextLast = 1;
//        this.addFirst(item);
//    }

    /// we need to implement it!
    // TODO: we can not think about the resize now!!

//    private void resize(int capacity) {
//        T[] newArray = (T[]) new Object[capacity];
//        System.arraycopy(arr, 0, newArray, 0, this.size());
//        this.nextLast = this.arr.length - 1;
//        arr = newArray;
//        this.nextFirst = this.arr.length - 1;
//
//
//    }
    public void addFirst(T item) {

//        if (this.size() == arr.length) {
//
//            this.resize(arr.length * 2);
//
//            // nextfirst and nextlast also need modify
//
//            // we need to make sure there is not too much empty space when the original array is not very symmetric.
//
//            // what is the location of the arr in the new arr?
//            // at index 0!
//
//            // also need to modify the add and remove logic.
//            // that is, after first = 0, what should be the next,
//            // same for last after length - 1.
//
//            // array doesn't understand negative index!
//
//            // and I can not ignore the removed item.
//
//
//        }


        this.size++;

        // there is smt wrong. it overwrote the original index 0 when the arr is new.

        arr[this.nextFirst] = item;
        this.nextFirst--;
//        if (this.nextFirst == -1) {
//            this.nextFirst = this.nextLast + (this.arr.length - this.size());
//        }

    }

    public void addLast(T item) {

        // TODO: we need to deal with the situation when nextFirst = nextLast.

//        if (this.nextLast < this.nextFirst || this.nextLast > arr.length - 1) {
//            this.resize(arr.length * 2);
//        }
//
//        if (this.size() == arr.length) {
//            this.resize(arr.length * 2);
//        }

        this.size++;
        arr[this.nextLast] = item;
        this.nextLast++;
    }


    public boolean isEmpty() {
        return this.size() == 0;
    }

    public int size() {
        return this.size;
    }

    public void printDeque() {
        for (T a : arr) {
            System.out.print(a.toString() + " ");
        }
        System.out.println();
    }


    public T removeFirst() {

//        // we need to contract.
//        if (this.size() < arr.length * 0.25) {
//            this.resize(arr.length / 2);
//        }

        this.size--;
        if (this.size() < 0) {
            this.size = 0;
        }
        this.nextFirst++;
        return this.get(this.nextFirst);
    }

    public T removeLast() {

//        if (this.size() < arr.length * 0.25) {
//            this.resize(arr.length / 2);
//        }

        this.size--;
        if (this.size() < 0) {
            this.size = 0;
        }
        this.nextLast--;
        return this.get(this.nextLast);

    }

    public T get(int index) {
        if (this.size() == 0 || index < 0 || index > arr.length) {
            return null;
        }

        // index IS index, not count!!!
        // but in our practical array, index also means how many index + 1 in front of this item.
        // This first item's index is 0!!

        int realIndex = this.nextFirst + 1 + index;
        if (realIndex > this.arr.length - 1) {
            realIndex -= this.arr.length;
        }
        return this.arr[realIndex];

//        int actualIndex = this.nextFirst + 1 + index;
//        if (actualIndex > arr.length - 1) {
//            //TODO: how to deal the get? I think it's related to how i copy the array.
//            //actualIndex -= this.size();
//            //actualIndex = 0 + (actualIndex + 1 - (this.arr.length - (this.nextFirst + 1)));
//            //actualIndex = this.nextLast + (this.arr.length - (this.nextFirst + 1));
//            actualIndex = this.nextLast - this.size() - (this.arr.length - this.nextFirst);
//        }
//        return arr[actualIndex];

    }


//    public Iterator<T> iterator() {
//    }

    public boolean equals(Object o) {
        if (o instanceof ArrayDeque) {
            for (int i = 0; i < this.size(); i++) {
                if (((ArrayDeque<?>) o).get(i) == null || ((ArrayDeque<?>) o).get(i) != this.get(i)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

}
