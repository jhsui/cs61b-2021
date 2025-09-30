package deque;

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
//        ad.addLast(8);
//        ad.addLast(9);
//        ad.addLast(10);


        assertEquals(Integer.valueOf(0), ad.get(0));
        assertEquals(Integer.valueOf(1), ad.get(1));
        assertEquals(Integer.valueOf(2), ad.get(2));
        assertEquals(Integer.valueOf(3), ad.get(3));
        assertEquals(Integer.valueOf(4), ad.get(4));
        assertEquals(Integer.valueOf(5), ad.get(5));
        assertEquals(Integer.valueOf(6), ad.get(6));
        assertEquals(Integer.valueOf(7), ad.get(7));
//        assertEquals(Integer.valueOf(8), ad.get(8));
//        assertEquals(Integer.valueOf(9), ad.get(9));
//        assertEquals(Integer.valueOf(10), ad.get(10));

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

//        ad.addFirst(8);
//        ad.addFirst(9);
//        ad.addFirst(10);
//        ad.addFirst(11);


//        System.out.println(ad.get(7));
//        System.out.println(ad.get(6));
//        System.out.println(ad.get(5));
//        System.out.println(ad.get(4));
//        System.out.println(ad.get(3));
//        System.out.println(ad.get(2));
//        System.out.println(ad.get(1));
//        System.out.println(ad.get(0));


        // get() is wrong, the index needs to be modified.
        // the index of 0 shall be 7 and the index of 1 shall be 6.
        // the way I deal with nextFirst and nextLast!!!


        assertEquals(Integer.valueOf(0), ad.get(7));
        assertEquals(Integer.valueOf(1), ad.get(6));
        assertEquals(Integer.valueOf(2), ad.get(5));
        assertEquals(Integer.valueOf(3), ad.get(4));
        assertEquals(Integer.valueOf(4), ad.get(3));
        assertEquals(Integer.valueOf(5), ad.get(2));
        assertEquals(Integer.valueOf(6), ad.get(1));
        assertEquals(Integer.valueOf(7), ad.get(0));


//        assertEquals(Integer.valueOf(0), ad.get(11));
//        assertEquals(Integer.valueOf(1), ad.get(10));
//        assertEquals(Integer.valueOf(2), ad.get(9));
//        assertEquals(Integer.valueOf(3), ad.get(8));
//        assertEquals(Integer.valueOf(4), ad.get(7));
//        assertEquals(Integer.valueOf(5), ad.get(6));
//        assertEquals(Integer.valueOf(6), ad.get(5));
//        assertEquals(Integer.valueOf(7), ad.get(4));
//        assertEquals(Integer.valueOf(8), ad.get(3));
//        assertEquals(Integer.valueOf(9), ad.get(2));
//        assertEquals(Integer.valueOf(10), ad.get(1));
//        assertEquals(Integer.valueOf(11), ad.get(0));

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

//        for (int i = 0; i < 8; i++) {
//            assertEquals(Integer.valueOf(7 - i), ad.removeLast());
//        }

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

        for (int i = 0; i < 8; i++) {
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


}
