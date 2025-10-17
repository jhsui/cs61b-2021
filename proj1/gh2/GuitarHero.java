package gh2;

import edu.princeton.cs.algs4.StdAudio;
import edu.princeton.cs.algs4.StdDraw;

/**
 * A client that uses the synthesizer package to replicate a plucked guitar string sound
 */
public class GuitarHero {
//    public static final double CONCERT_A = 440.0;
//    public static final double CONCERT_C = CONCERT_A * Math.pow(2, 3.0 / 12.0);

    private static final String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";

    private static GuitarString[] CONCERTS = new GuitarString[37];


    public static void main(String[] args) {
        /* create two guitar strings, for concert A and C */
//        GuitarString stringA = new GuitarString(CONCERT_A);
//        GuitarString stringC = new GuitarString(CONCERT_C);


        for (int i = 0; i < CONCERTS.length; i++) {
            CONCERTS[i] = new GuitarString(440.0 * Math.pow(2, (i - 24) * 1.0 / 12));
        }

        while (true) {
            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                int index = keyboard.indexOf(key);

//                if (keyboard.contains(String.valueOf(key))) {
//
//                    CONCERTS[keyboard.indexOf(key)].pluck();
//                    StdAudio.play(CONCERTS[keyboard.indexOf(key)].sample());
//                    CONCERTS[keyboard.indexOf(key)].tic();
//                }
                if (index != -1) {
                    CONCERTS[index].pluck();
                }
            }

//            /* compute the superposition of samples */
//            double sample = stringA.sample() + stringC.sample();
            double sample = 0;
            for (GuitarString gs : CONCERTS) {
                sample += gs.sample();
            }

//
//            /* play the sample on standard audio */
            StdAudio.play(sample);
//
//            /* advance the simulation of each guitar string by one step */
//            stringA.tic();
//            stringC.tic();
            for (GuitarString gs : CONCERTS) {
                gs.tic();
            }
        }
    }
}

