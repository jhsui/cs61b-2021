package deque;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RandomTest {

    @Test
    public void randomizedAFRLTest() {
        ArrayDeque<Integer> ad = new ArrayDeque<>();
        LinkedListDeque<Integer> lld = new LinkedListDeque<>();

        int N = 100000;

//        StdRandom.setSeed(1111111111L);

        for (int i = 0; i < N; i++) {
            int operationNumber = StdRandom.uniform(0, 20);

            if (operationNumber <= 10) {
                ad.addFirst(i);
                lld.addFirst(i);
//                System.out.println(i);
                assertEquals(lld.get(0), ad.get(0));
                assertEquals(lld.size(), ad.size());
            } else if (operationNumber <= 15) {
                Integer l = lld.removeLast();
                Integer a = ad.removeLast();
//                System.out.println(i);
                assertEquals(l, a);
                assertEquals(lld.get(lld.size() - 1), ad.get(lld.size() - 1));
                assertEquals(lld.size(), ad.size());

            } else {
                assertEquals(ad.isEmpty(), lld.isEmpty());
                assertEquals(lld.size(), ad.size());

            }
        }
    }

    // I think I figured out the reason. It is because that when I was going to copy the array when the size was full.
    // I just copied it. without considering the sequence.
    // Now when I am going to copy, I just o
    @Test
    public void randomizedALRFTest() {
        ArrayDeque<Integer> ad = new ArrayDeque<>();
        LinkedListDeque<Integer> lld = new LinkedListDeque<>();

        int N = 100000;

//        StdRandom.setSeed(1111111111L);

        for (int i = 0; i < N; i++) {
            int operationNumber = StdRandom.uniform(0, 20);

            if (operationNumber <= 10) {
                ad.addLast(i);
                lld.addLast(i);
//                System.out.println(i);
                assertEquals(lld.get(lld.size() - 1), ad.get(ad.size() - 1));
                assertEquals(lld.size(), ad.size());
            } else if (operationNumber <= 15) {
                Integer l = lld.removeFirst();
                Integer a = ad.removeFirst();
//                System.out.println(i);
                assertEquals(l, a);
                assertEquals(lld.get(0), ad.get(0));
                assertEquals(lld.size(), ad.size());

            } else {
                assertEquals(ad.isEmpty(), lld.isEmpty());
                assertEquals(lld.size(), ad.size());

            }
        }
    }

    @Test
    public void randomizedTest() {
        ArrayDeque<Integer> ad = new ArrayDeque<>();
        LinkedListDeque<Integer> lld = new LinkedListDeque<>();

        int N = 100000;

        StdRandom.setSeed(1760037818277L);

//        long seed = System.currentTimeMillis();
//        StdRandom.setSeed(seed);
//        System.out.println("Seed used: " + seed);
// 1760037891140

        for (int i = 0; i < N; i++) {
            int operationNumber = StdRandom.uniform(0, 20);

            if (operationNumber <= 3) {
                System.out.println("add last: " + i);

                ad.addLast(i);
                lld.addLast(i);
                assertEquals(lld.get(lld.size() - 1), ad.get(ad.size() - 1));
                assertEquals(lld.size(), ad.size());
            } else if (operationNumber <= 7) {
                System.out.println("remove first: " + i);

                Integer l = lld.removeFirst();
                Integer a = ad.removeFirst();
                assertEquals(l, a);
                assertEquals(lld.get(0), ad.get(0));
                assertEquals(lld.size(), ad.size());

            } else if (operationNumber <= 11) {
                System.out.println("add first: " + i);
                ad.addFirst(i);
                lld.addFirst(i);
                assertEquals(lld.get(0), ad.get(0));
                assertEquals(lld.size(), ad.size());

            } else if (operationNumber <= 15) {
                System.out.println("remove last: " + i);

                Integer l = lld.removeLast();
                Integer a = ad.removeLast();
//                System.out.println(i);
                assertEquals(l, a);
                assertEquals(lld.get(lld.size() - 1), ad.get(lld.size() - 1));
                assertEquals(lld.size(), ad.size());
            } else {
                System.out.println("check size: " + i);

                assertEquals(ad.isEmpty(), lld.isEmpty());
                assertEquals(lld.size(), ad.size());

            }
        }
    }
}
