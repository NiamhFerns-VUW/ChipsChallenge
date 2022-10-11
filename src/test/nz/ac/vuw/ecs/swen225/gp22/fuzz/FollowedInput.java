package nz.ac.vuw.ecs.swen225.gp22.fuzz;
import java.awt.event.KeyEvent;
import java.util.Random;

public class FollowedInput implements InputStrategy {
    public final int[] keys = {
            KeyEvent.VK_UP,
            KeyEvent.VK_DOWN,
            KeyEvent.VK_LEFT,
            KeyEvent.VK_RIGHT,
            KeyEvent.VK_SPACE,
            KeyEvent.VK_ESCAPE,
            KeyEvent.VK_S,
            KeyEvent.VK_R
    };
    private int key;
    private int prevKey;
    private InputStrategy inputStrategy;
    Random random = new Random();
    public FollowedInput() {
        key = keys[random.nextInt(keys.length)];
    }

    @Override
    public int nextInput() {
        if (prevKey == KeyEvent.VK_S) {
            key = KeyEvent.VK_R;
        } else {
            inputStrategy = new ProbInput();
        }
        prevKey = key;
        return key;
    }

    @Override
    public boolean isPressCtrl() {
        return (key == KeyEvent.VK_S || key == KeyEvent.VK_R);
    }
}
