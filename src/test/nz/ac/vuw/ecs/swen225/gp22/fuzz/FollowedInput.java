package nz.ac.vuw.ecs.swen225.gp22.fuzz;

import nz.ac.vuw.ecs.swen225.gp22.fuzz.InputStrategy;

import java.awt.event.KeyEvent;
import java.util.Random;

class FollowedInput implements InputStrategy {
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
     * This is the constructor of the FollowedInput class
     */
    public FollowedInput() {}
    /**
     * This method is used to generate random inputs for the game by following the previous input
     * @param input the input
     * @return the next input
     */
    @Override
    public int nextInput( Input input) {
        int[] keysWithoutUp = { KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_SPACE, KeyEvent.VK_ESCAPE, KeyEvent.VK_S, KeyEvent.VK_R };
        int[] keysWithoutDown = { KeyEvent.VK_UP, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_SPACE, KeyEvent.VK_ESCAPE, KeyEvent.VK_S, KeyEvent.VK_R };
        int[] keysWithoutLeft = { KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_RIGHT, KeyEvent.VK_SPACE, KeyEvent.VK_ESCAPE, KeyEvent.VK_S, KeyEvent.VK_R };
        int[] keysWithoutRight = { KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_SPACE, KeyEvent.VK_ESCAPE, KeyEvent.VK_S, KeyEvent.VK_R };
        int[] keyAfterS = { KeyEvent.VK_R };
        int[] keyAfterR = { KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_SPACE, KeyEvent.VK_ESCAPE, KeyEvent.VK_S, KeyEvent.VK_R };
        int[] keyAfterSpace = { KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_SPACE, KeyEvent.VK_ESCAPE, KeyEvent.VK_S, KeyEvent.VK_R };
        int[] keyAfterEscape = { KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_SPACE, KeyEvent.VK_ESCAPE, KeyEvent.VK_S, KeyEvent.VK_R };

        int[] from = switch (input.prevKey) {
            case KeyEvent.VK_UP -> keysWithoutDown;
            case KeyEvent.VK_DOWN -> keysWithoutUp;
            case KeyEvent.VK_LEFT -> keysWithoutRight;
            case KeyEvent.VK_RIGHT -> keysWithoutLeft;
            case KeyEvent.VK_S -> keyAfterS;
            case KeyEvent.VK_R -> keyAfterR;
            case KeyEvent.VK_SPACE -> keyAfterSpace;
            case KeyEvent.VK_ESCAPE -> keyAfterEscape;
            default -> keys;
        };

        return from[random.nextInt(from.length)];
    }

    /**
     * This method is used to check if the input is a control key
     * @param input the input
     * @return true if the input is a control key
     */
    @Override
    public boolean isPressCtrl( Input input) {
        return (input.key == KeyEvent.VK_S || input.key == KeyEvent.VK_R);
    }
}
