package nz.ac.vuw.ecs.swen225.gp22.fuzz;
import java.awt.event.KeyEvent;
import java.util.Random;

public class ProbInput implements InputStrategy {
    private int key;
    private int prevKey;
    private InputStrategy inputStrategy;
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

    @Override
    public int nextInput() {
        int[] keysWithoutUp = { KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_SPACE, KeyEvent.VK_ESCAPE, KeyEvent.VK_S, KeyEvent.VK_R };
        int[] keysWithoutDown = { KeyEvent.VK_UP, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_SPACE, KeyEvent.VK_ESCAPE, KeyEvent.VK_S, KeyEvent.VK_R };
        int[] keysWithoutLeft = { KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_RIGHT, KeyEvent.VK_SPACE, KeyEvent.VK_ESCAPE, KeyEvent.VK_S, KeyEvent.VK_R };
        int[] keysWithoutRight = { KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_SPACE, KeyEvent.VK_ESCAPE, KeyEvent.VK_S, KeyEvent.VK_R };

        int[] from = switch (prevKey) {
            case KeyEvent.VK_UP -> keysWithoutDown;
            case KeyEvent.VK_DOWN -> keysWithoutUp;
            case KeyEvent.VK_LEFT -> keysWithoutRight;
            case KeyEvent.VK_RIGHT -> keysWithoutLeft;
            default -> keys;
        };
        if (prevKey == KeyEvent.VK_S) {
            inputStrategy = new FollowedInput();
        }

        key = from[random.nextInt(from.length)];
        prevKey = key;
        return key;
    }

    @Override
    public boolean isPressCtrl() {
        return (key == KeyEvent.VK_S || key == KeyEvent.VK_R);
    }
}

