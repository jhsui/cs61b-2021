package randomizedtest;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestThreeAddThreeRemove {

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


}
