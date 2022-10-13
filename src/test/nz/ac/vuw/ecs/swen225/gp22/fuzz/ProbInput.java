package nz.ac.vuw.ecs.swen225.gp22.fuzz;

import java.awt.event.KeyEvent;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * This class is used to generate random inputs for the game with different probabilities.
 */

public class ProbInput implements InputStrategy {
    Random random = new Random();
    private int[] keys = {
            KeyEvent.VK_UP,
            KeyEvent.VK_DOWN,
            KeyEvent.VK_LEFT,
            KeyEvent.VK_RIGHT,
            KeyEvent.VK_SPACE,
            KeyEvent.VK_ESCAPE,
            KeyEvent.VK_S,
            KeyEvent.VK_R
    };
    /**
     * This is the constructor of the ProbInput class
     */
    public ProbInput() {}
    /**
     * This method is used to generate random inputs for the game with different probabilities
     * @param input the input
     * @return the next input
     */
    @Override
    public int nextInput(Input input) {
        double[] weightMost = {0.2, 0.2, 0.2, 0.2, 0.1, 0.1, 0.1, 0.1};
        double[] weightAfterS = {0, 0, 0, 0, 0, 0, 0, 1};
        double[] weightAfterSpace = {0.1, 0.1, 0.1, 0.1, 0.1, 0.3, 0.1, 0.1};

        double[] fromProb = switch (input.prevKey) {
            case KeyEvent.VK_S -> weightAfterS;
            case KeyEvent.VK_SPACE -> weightAfterSpace;
            default -> weightMost;
        };

        int[] from = IntStream.range(0, fromProb.length)
                .filter(i -> fromProb[i] > 0)
                .mapToObj(i -> IntStream.range(0, (int) (fromProb[i] * 100)).map(j -> keys[i]).toArray())
                .flatMapToInt(IntStream::of)
                .toArray();

        return from[random.nextInt(from.length)];
    }
    /**
     * This method is used to check if the next input is a ctrl key
     * @param input the input
     * @return true if the next input is a ctrl key
     */
    @Override
    public boolean isPressCtrl(Input input) {
        return (input.key == KeyEvent.VK_S || input.key == KeyEvent.VK_R);
    }
}

