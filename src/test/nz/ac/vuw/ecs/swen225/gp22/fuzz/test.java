package nz.ac.vuw.ecs.swen225.gp22.fuzz;
package nz.ac.vuw.ecs.swen225.gp22.app;

import java.util.ArrayList;
import java.util.List;
import nz.ac.vuw.ecs.swen225.gp22.app.*;

//import java.awt.AWTException;
//import games.GameHandler;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.*;

class test{
    static final Random random = new Random();
    private List<char> randomKeys(int size){
        return IntStream.range(0, size)
                .mapToObj(i -> (char) random.nextInt(100))
    }

//    enum Keys{
//
//    }
//    private List<Int> randomKey(int size){
//        return IntStream.range(0, size)
//                .map(i -> (int) (Math.random() * 100))
//                .boxed()
//                .collect(Collectors.toList());
//    }
//
//    @Test
//    void  propertyTest() throws InterruptedException {
//        GameHandler gameHandler = new GameHandler();
//        gameHandler.start();
//        var keys = randomKey(100);
//        Robot robot = new Robot();
//        for (int i = 0; i < keys.size(); i++) {
//            robot.keyPress(keys.get(i));
//            robot.keyRelease(keys.get(i));
//        }
//        Thread.sleep(1000);
//        assertEquals(100, GameClock.get().currentTick());
//    }

}

