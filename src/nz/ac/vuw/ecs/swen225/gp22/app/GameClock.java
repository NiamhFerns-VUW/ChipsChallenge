package nz.ac.vuw.ecs.swen225.gp22.app;

import java.util.ArrayList;
import java.util.List;

public class GameClock extends Subject {
    private static GameClock clock;
    Thread tickThread;

    private long tickCount;
    private long levelTickCount;
    private long timePlayed;
    private long timeRemaining;
    private boolean running;

    public static GameClock get() {
        if (clock == null) {
            clock = new GameClock();
        }
        return clock;
    }

    public long currentTick()      { return tickCount; }
    public long currentTime()      { return timePlayed; }
    public long currentLevelTick() { return levelTickCount; }
    public long currentLevelTime() { return timeRemaining; }

    protected void setLevelTime(long time) {
        this.timeRemaining = time;
    }
    protected  void resetLevelTick() { levelTickCount = 0L; }

    private  void tickIncrement() {
        levelTickCount++;
        tickCount++;
    }

    protected void start() {
        // Set up our clock to tick.
        Runnable onTick = () -> {
            while (running) {
                tickIncrement();
                update();
                try {
                    Thread.sleep(16);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        tickThread = new Thread(onTick, "Tick Thread");

        running = true;
        tickThread.start();
    }
    protected void stop() {
        running = false;
    }
    protected Thread thread() { return tickThread; }

    private GameClock() {
        tickCount = 0;
        levelTickCount = 0;
        timePlayed = 0;
        timeRemaining = 0;
    }
}
