package deque;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RandomTest {

    @Test
    public void randomizedTest() {
        ArrayDeque<Integer> ad = new ArrayDeque<>();
        LinkedListDeque<Integer> lld = new LinkedListDeque<>();

        int N = 100000;

        for (int i = 0; i < N; i++) {

            int operationNumber = StdRandom.uniform(0, 20);


            if (operationNumber <= 10) {
                ad.addFirst(i);
                lld.addFirst(i);
                assertEquals(lld.get(0), ad.get(0));
                assertEquals(lld.size(), ad.size());
            } else if (operationNumber <= 15) {
                Integer a = ad.removeLast();
                Integer l = lld.removeLast();
                assertEquals(a, l);
                assertEquals(lld.get(lld.size() - 1), ad.get(lld.size() - 1));
                assertEquals(lld.size(), ad.size());

            } else {
                assertEquals(ad.isEmpty(), lld.isEmpty());
                assertEquals(lld.size(), ad.size());

            }
        }
    }
}
