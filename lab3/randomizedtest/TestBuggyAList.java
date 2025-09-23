package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
    // YOUR TESTS HERE

    @Test
    public void testThreeAddThreeRemove() {

        AListNoResizing<Integer> anr = new AListNoResizing<>();
        BuggyAList<Integer> ba = new BuggyAList<Integer>();


        anr.addLast(4);
        anr.addLast(5);
        anr.addLast(6);

        ba.addLast(4);
        ba.addLast(5);
        ba.addLast(6);

//        System.out.println("Hey I'm here!");
//        Object removedLast = anr.removeLast();
//        System.out.println(removedLast);
//        System.out.println(removedLast.getClass().getName());

        assertEquals(anr.removeLast(), ba.removeLast());
        assertEquals(anr.removeLast(), ba.removeLast());
        assertEquals(anr.removeLast(), ba.removeLast());
    }

    @Test
    public void randomizedTest() {

        AListNoResizing<Integer> L = new AListNoResizing<>();
        int N = 500;

        for (int i = 0; i < N; i += 1) {

            int operationNumber = StdRandom.uniform(0, 4);

            if (operationNumber == 0) {

                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                System.out.println("addLast(" + randVal + ")");

            } else if (operationNumber == 1) {

                // size
                int size = L.size();
                System.out.println("size: " + size);

            } else if (operationNumber == 2) {

                //getLast
                if (L.size() > 0) {
                    System.out.println("getLast(" + L.getLast() + ")");
                }
            } else if (operationNumber == 3) {

                //removeLast
                if (L.size() > 0) {
                    System.out.println("getLast(" + L.removeLast() + ")");
                }
            }
        }
    }


}
