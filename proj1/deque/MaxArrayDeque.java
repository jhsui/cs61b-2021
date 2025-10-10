package deque;

import java.util.Comparator;


public class MaxArrayDeque<T> extends ArrayDeque<T> {

    private Comparator<T> c;

    public MaxArrayDeque(Comparator<T> c) {
        this.c = c;
    }

    public T max() {
        if (this.size() == 0) {
            return null;
        }

        T max = this.get(0);

        for (int i = 0; i < this.size(); i++) {
            if (this.c.compare(max, this.get(i)) < 0) {
                max = this.get(i);
            }
        }

        return max;
    }

    public T max(Comparator<T> comparator) {
        if (this.size() == 0) {
            return null;
        }

        T max = this.get(0);

        for (int i = 0; i < this.size(); i++) {
            if (comparator.compare(max, this.get(i)) < 0) {
                max = this.get(i);
            }
        }


        return max;
    }


}

