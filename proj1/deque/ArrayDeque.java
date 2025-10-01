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
    private int firstLength;
    private int lastLength;

    public ArrayDeque() {
        arr = (T[]) new Object[8];
        this.size = 0;

        this.nextFirst = 7;
        this.nextLast = 0;

        this.firstLength = 0;
        this.lastLength = 0;
    }


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

        // now I put the array at the one fourth position.


        // restriction the other half? // firstLength and lastlength are both 0;
        if (this.firstLength == 0 && this.lastLength != 0) {
            System.arraycopy(this.arr, 0, newArray, 0, this.lastLength);
        } else if (this.lastLength == 0 && this.firstLength != 0) {
            // this.nextFirst + 1 is wrong,
            // why wrong?
            // because next first is incorrect. I need it to be 0; but it is 1 which is correct when the size does not change.
            // the 1 is coming from last addFirst, it became -1 and then added the arr.length, so it became 1.
            // it jumped.
            // what are other values here? the firstLength is 2 and lastLength is 0;
            // the nextFirst passed is the old one, but I want the one without jumped.
            int srcPos = this.nextFirst + 1;
            if (srcPos >= this.arr.length) {
                srcPos -= this.arr.length;
            }
            System.arraycopy(this.arr, srcPos, newArray, newArray.length - this.firstLength, this.firstLength);
        } else {
            // second half.
            System.arraycopy(this.arr, this.firstLength, newArray, 0, this.lastLength);
            // first half.
            System.arraycopy(this.arr, this.lastLength + 1, newArray, newArray.length - this.firstLength, this.firstLength);

        }


        // firstly, we copy the second half part.

        // we are copying the first half into the new array.
        // I need to figure out the position in the original array.
        // Is it lastLength+1?
        // If the lastLength > 0, then yes, otherwise it is actually the negative firstLength.
        // So, if it is <= 0, then I should just copy the other half. And the rest should be skipped.


        // I did not null the original array, I need to make sure there is no removed element being copied to the newArray.

        this.nextLast = this.lastLength;
        this.nextFirst = newArray.length - this.firstLength - 1;

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
    public void addFirst(T item) {
        if (this.size() == arr.length) {
            this.resize(this.arr.length * 2);
        }

        arr[this.nextFirst] = item;

        this.nextFirst--;
        if (this.nextFirst < 0) {
            this.nextFirst += this.arr.length;
        }

        this.firstLength++;

        this.size++;
    }

    public void addLast(T item) {
        if (this.size() == arr.length) {
            this.resize(this.arr.length * 2);
        }

        arr[this.nextLast] = item;

        this.nextLast++;
//        if (this.nextLast > this.arr.length - 1) {
//            this.nextLast -= this.size();
//        }

        this.lastLength++;

        this.size++;
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
        // nextFist + 1 is the actual location in the array, but here i should pass the index used by user.
        T removedItem = this.get(0);

        this.nextFirst++;
        if (this.nextFirst > this.arr.length - 1) {
            this.nextFirst -= this.arr.length;
        }


        this.firstLength--;

        this.size--;
        if (this.size() < 0) {
            this.size = 0;
        }

        if (this.size() < arr.length * 0.25) {
            this.resize(this.arr.length / 2);
        }
        return removedItem;
    }

    public T removeLast() {
        T removedItem = this.get(this.size() - 1);

        this.nextLast--;
        if (this.nextLast < 0) {
            this.nextLast += this.arr.length;
        }

        this.lastLength--;

        this.size--;
        if (this.size() < 0) {
            this.size = 0;
        }

        if (this.size() < arr.length * 0.25) {
            this.resize(this.arr.length / 2);
        }

        return removedItem;
    }

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
