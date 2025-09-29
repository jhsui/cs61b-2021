package deque;

public class ArrayDeque<T> {

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

    public void addFirst(T item) {
        this.size++;
        arr[this.nextFirst] = item;
        this.nextFirst--;
    }

    public void addLast(T item) {
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
        T removedItem = this.get(this.nextFirst + 1);
        this.size--;
        if (this.size() < 0) {
            this.size = 0;
        }
        this.nextFirst = this.nextFirst + 1;
        return removedItem;
    }

    public T removeLast() {
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

        int realIndex = this.nextFirst + 1 + index;
        if (realIndex > this.arr.length - 1) {
            realIndex -= this.arr.length;
        }
        return this.arr[realIndex];
    }


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
