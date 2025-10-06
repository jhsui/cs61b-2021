package deque;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

import static org.junit.Assert.*;


public class ArrayDequeTest {


    @Test
    public void addLastBasicTest() {
        ArrayDeque<Integer> ad = new ArrayDeque<>();

        ad.addLast(0);
        ad.addLast(1);
        ad.addLast(2);
        ad.addLast(3);
        ad.addLast(4);
        ad.addLast(5);
        ad.addLast(6);
        ad.addLast(7);

        assertEquals(Integer.valueOf(0), ad.get(0));
        assertEquals(Integer.valueOf(1), ad.get(1));
        assertEquals(Integer.valueOf(2), ad.get(2));
        assertEquals(Integer.valueOf(3), ad.get(3));
        assertEquals(Integer.valueOf(4), ad.get(4));
        assertEquals(Integer.valueOf(5), ad.get(5));
        assertEquals(Integer.valueOf(6), ad.get(6));
        assertEquals(Integer.valueOf(7), ad.get(7));
    }

    @Test
    public void addFirstBasicTest() {
        ArrayDeque<Integer> ad = new ArrayDeque<>();

        ad.addFirst(0);
        ad.addFirst(1);
        ad.addFirst(2);
        ad.addFirst(3);
        ad.addFirst(4);
        ad.addFirst(5);
        ad.addFirst(6);
        ad.addFirst(7);

        assertEquals(Integer.valueOf(0), ad.get(7));
        assertEquals(Integer.valueOf(1), ad.get(6));
        assertEquals(Integer.valueOf(2), ad.get(5));
        assertEquals(Integer.valueOf(3), ad.get(4));
        assertEquals(Integer.valueOf(4), ad.get(3));
        assertEquals(Integer.valueOf(5), ad.get(2));
        assertEquals(Integer.valueOf(6), ad.get(1));
        assertEquals(Integer.valueOf(7), ad.get(0));

    }


    @Test
    public void removeFirstBasicTest() {
        ArrayDeque<Integer> ad = new ArrayDeque<>();

        ad.addFirst(0);
        ad.addFirst(1);
        ad.addFirst(2);
        ad.addFirst(3);
        ad.addFirst(4);
        ad.addFirst(5);
        ad.addFirst(6);
        ad.addFirst(7);

        assertEquals(Integer.valueOf(7), ad.removeFirst());
        assertEquals(Integer.valueOf(6), ad.removeFirst());
        assertEquals(Integer.valueOf(5), ad.removeFirst());
        assertEquals(Integer.valueOf(4), ad.removeFirst());
        assertEquals(Integer.valueOf(3), ad.removeFirst());
        assertEquals(Integer.valueOf(2), ad.removeFirst());
        assertEquals(Integer.valueOf(1), ad.removeFirst());
        assertEquals(Integer.valueOf(0), ad.removeFirst());
    }

    @Test
    public void removeLastBasicTest() {
        ArrayDeque<Integer> ad = new ArrayDeque<>();

        ad.addLast(0);
        ad.addLast(1);
        ad.addLast(2);
        ad.addLast(3);
        ad.addLast(4);
        ad.addLast(5);
        ad.addLast(6);
        ad.addLast(7);

        assertEquals(Integer.valueOf(7), ad.removeLast());
        assertEquals(Integer.valueOf(6), ad.removeLast());
        assertEquals(Integer.valueOf(5), ad.removeLast());
        assertEquals(Integer.valueOf(4), ad.removeLast());
        assertEquals(Integer.valueOf(3), ad.removeLast());
        assertEquals(Integer.valueOf(2), ad.removeLast());
        assertEquals(Integer.valueOf(1), ad.removeLast());
        assertEquals(Integer.valueOf(0), ad.removeLast());
    }

    @Test
    public void addGetTest() {
        ArrayDeque<Integer> ad = new ArrayDeque<>();

        ad.addLast(4);
        ad.addLast(5);
        ad.addLast(6);
        ad.addLast(7);
        ad.addLast(8);

        assertEquals(Integer.valueOf(8), ad.get(4));
    }

    @Test
    public void fillUpEmptyFillUpAgainUsingFirst() {
        ArrayDeque<Integer> ad = new ArrayDeque<>();

        for (int i = 0; i < 8; i++) {
            ad.addFirst(i);
        }

        for (int i = 0; i < 8; i++) {
            ad.removeFirst();
        }

        for (int i = 0; i < 8; i++) {
            ad.addFirst(i);
        }

        for (Integer i : ad) {
            assertEquals(Integer.valueOf(7 - i), ad.get(i));
        }
    }

    @Test
    public void fillUpEmptyFillUpAgainUsingLast() {
        ArrayDeque<Integer> ad = new ArrayDeque<>();

        for (int i = 0; i < 8; i++) {
            ad.addLast(i);
        }

        for (int i = 0; i < 8; i++) {
            ad.removeLast();
        }

        for (int i = 0; i < 8; i++) {
            ad.addLast(i);
        }

        for (int i = 0; i < 8; i++) {
            assertEquals(Integer.valueOf(i), ad.get(i));
        }
    }

    @Test
    public void fillUpEmptyFillUpAgainUsingFirstAndLast() {
        ArrayDeque<Integer> ad = new ArrayDeque<>();

        for (int i = 0; i < 4; i++) {
            ad.addFirst(3 - i);
        }
        for (int i = 4; i < 8; i++) {
            ad.addLast(i);
        }

        for (int i = 0; i < 4; i++) {
            ad.removeFirst();
        }
        for (int i = 0; i < 4; i++) {
            ad.removeLast();
        }

        for (int i = 0; i < 4; i++) {
            ad.addFirst(3 - i);
        }
        for (int i = 4; i < 8; i++) {
            ad.addLast(i);
        }

        for (int i = 0; i < 8; i++) {
            assertEquals(Integer.valueOf(i), ad.get(i));
        }
    }

    @Test
    public void addLastTestAdd713() {
        ArrayDeque<Integer> ad = new ArrayDeque<>();

        for (int i = 0; i < 713; i++) {
            ad.addLast(i);
        }

        for (int i = 0; i < 713; i++) {
            assertEquals(Integer.valueOf(i), ad.get(i));
        }
    }

    @Test
    public void addFirstTestAdd15() {
        ArrayDeque<Integer> ad = new ArrayDeque<>();

        for (int i = 0; i < 16; i++) {
            ad.addFirst(i);
        }

        for (int i = 0; i < 16; i++) {
            assertEquals(Integer.valueOf(15 - i), ad.get(i));
        }
    }

    @Test
    public void fillUpEmptyFillUpAgainUsingFirst16() {
        ArrayDeque<Integer> ad = new ArrayDeque<>();

        for (int i = 0; i < 16; i++) {
            ad.addFirst(15 - i);
        }
        for (int i = 0; i < 16; i++) {
            ad.removeFirst();
        }

        for (int i = 0; i < 16; i++) {
            ad.addFirst(15 - i);
        }
        for (int i = 0; i < 16; i++) {
            assertEquals(Integer.valueOf(i), ad.get(i));
        }
    }

    @Test
    public void fillUpUsingFirstEmptyFillUpUsingLastAgain16() {
        ArrayDeque<Integer> ad = new ArrayDeque<>();

        for (int i = 0; i < 16; i++) {
            ad.addFirst(15 - i);
        }
        for (int i = 0; i < 16; i++) {
            ad.removeFirst();
        }

        for (int i = 0; i < 16; i++) {
            ad.addLast(i);
        }
        for (int i = 0; i < 16; i++) {
            assertEquals(Integer.valueOf(i), ad.get(i));
        }
    }


    @Test
    public void randomizedAddLastTest() {

        for (int j = 0; j < 10000; j++) {
            ArrayDeque<Integer> ad = new ArrayDeque<>();

            int N = StdRandom.uniform(0, 100000);

            for (int i = 0; i < N; i++) {
                ad.addLast(i);
            }

            for (int i = 0; i < N; i++) {
                assertEquals(Integer.valueOf(i), ad.get(i));
            }
        }

    }

    @Test
    public void randomizedAddFirstTest() {
        for (int j = 0; j < 10000; j++) {

            ArrayDeque<Integer> ad = new ArrayDeque<>();

            int N = StdRandom.uniform(0, 100000);

            for (int i = 0; i < N; i++) {
                ad.addFirst(N - 1 - i);
            }

            for (int i = 0; i < N; i++) {
                assertEquals(Integer.valueOf(i), ad.get(i));
            }
        }

    }

    @Test
    public void randomizedFillUpUsingFirstEmptyUsingLastFillUpUsingLastTest() {
        for (int j = 0; j < 10000; j++) {

            ArrayDeque<Integer> ad = new ArrayDeque<>();

            int N = StdRandom.uniform(0, 100000);

            for (int i = 0; i < N; i++) {
                ad.addFirst(N - 1 - i);
            }

            for (int i = 0; i < 3 * N; i++) {
                ad.removeLast();
            }

            for (int i = 0; i < N; i++) {
                ad.addLast(i);
            }

            for (int i = 0; i < N; i++) {
                assertEquals(Integer.valueOf(i), ad.get(i));
            }
        }
    }

    @Test
    public void randomizedFillUpUsingLastEmptyUsingFirstFillUpUsingFirstTest() {
        for (int j = 0; j < 10000; j++) {

            ArrayDeque<Integer> ad = new ArrayDeque<>();

            int N = StdRandom.uniform(0, 100000);

            for (int i = 0; i < N; i++) {
                ad.addLast(i);
            }

            for (int i = 0; i < 2 * N; i++) {
                ad.removeFirst();
            }

            for (int i = 0; i < N; i++) {
                ad.addFirst(N - 1 - i);
            }

            for (int i = 0; i < N; i++) {
                assertEquals(Integer.valueOf(i), ad.get(i));
            }
        }
    }

    @Test
    public void keepRemovingFirst() {
        ArrayDeque<Integer> ad = new ArrayDeque<>();

        for (int i = 0; i < 100; i++) {
            ad.removeFirst();
        }
    }

    @Test
    public void keepRemovingLast() {
        ArrayDeque<Integer> ad = new ArrayDeque<>();

        for (int i = 0; i < 100; i++) {
            ad.removeLast();
        }
    }

    @Test
    public void equalsTest() {
        ArrayDeque<Integer> ad1 = new ArrayDeque<>();
        ArrayDeque<Integer> ad2 = new ArrayDeque<>();

        int N = StdRandom.uniform(0, 1000);

        for (int i = 0; i < N; i++) {
            ad1.addLast(i);
            ad2.addLast(i);
        }

        assertEquals(ad1, ad2);
    }

    @Test
    public void IntegerEqualTest() {
        Integer a = 128;
        Integer b = 128;
//        assertTrue(a == b);
        System.out.println(a == b);
    }


    @Test
    public void randomAddSizeTest() {

        ArrayDeque<Integer> ad = new ArrayDeque<>();

        ad.size();
        ad.addLast(1);
        ad.addLast(2);
        ad.addFirst(3);
        ad.addLast(4);
        ad.addFirst(5);
        ad.size();
        ad.addFirst(7);
        ad.addFirst(8);
        ad.addLast(9);
        ad.addFirst(10);
    }

}
