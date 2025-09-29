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
        this.nextFirst = 3;
        this.nextLast = 4;
    }


    // When the nextFist = nextLast, what should we do?
    public void addFirst(T item) {
        arr[this.nextFirst] = item;
        this.nextFirst--;

        if (this.nextFirst < 0) {
            this.nextFirst += this.arr.length;
        }

        this.size++;

        if (this.size() == arr.length) {
            // resize;
        }
    }

    public void addLast(T item) {
        arr[this.nextLast] = item;
        this.nextLast++;

        if (this.nextLast > this.arr.length - 1) {
            this.nextLast -= this.arr.length;
        }

        this.size++;

        if (this.size() == arr.length) {
            // resize;
        }
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
        this.nextFirst++;

        this.size--;
        if (this.size() < 0) {
            this.size = 0;
        }

        if (this.size() < arr.length * 0.25) {
            // resize;
        }
        return removedItem;
    }

    public T removeLast() {
        T removedItem = this.get(this.nextLast - 1);
        this.nextLast--;

        this.size--;
        if (this.size() < 0) {
            this.size = 0;
        }

        if (this.size() < arr.length * 0.25) {
            // resize;
        }

        return removedItem;
    }

    public T get(int index) {
        if (this.size() == 0 || index < 0 || index > this.size() - 1) {
            return null;
        }

        int realIndex = this.nextFirst + 1 + index;

        return this.arr[realIndex];
    }


//    public boolean equals(Object o) {
//        if (o instanceof ArrayDeque) {
//            for (int i = 0; i < this.size(); i++) {
//                if (((ArrayDeque<?>) o).get(i) == null || ((ArrayDeque<?>) o).get(i) != this.get(i)) {
//                    return false;
//                }
//            }
//            return true;
//        }
//        return false;
//    }

}
