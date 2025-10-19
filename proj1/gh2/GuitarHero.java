package gh2;

import edu.princeton.cs.algs4.StdAudio;
import edu.princeton.cs.algs4.StdDraw;

/**
 * A client that uses the synthesizer package to replicate a plucked guitar string sound
 */
public class GuitarHero {

    private static final String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";

    private static GuitarString[] CONCERTS = new GuitarString[37];


    public static void main(String[] args) {

        for (int i = 0; i < CONCERTS.length; i++) {
            CONCERTS[i] = new GuitarString( 440.0 * Math.pow(2, (i - 24) * 1.0 / 12));
        }

        while (true) {
            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                int index = keyboard.indexOf(key);

                if (index != -1) {
                    CONCERTS[index].pluck();
                }
            }

            double sample = 0;
            for (GuitarString gs : CONCERTS) {
                sample += gs.sample();
            }

            StdAudio.play(sample);

            for (GuitarString gs : CONCERTS) {
                gs.tic();
            }
        }
    }
}

