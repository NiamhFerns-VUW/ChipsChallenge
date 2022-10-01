package nz.ac.vuw.ecs.swen225.gp22.app;

import javax.swing.*;

public class GameClock extends Subject {
    private static GameClock clock;
    private Timer timer;
    public static final int FRAMERATE = 60;

    private long tickCount;
    private long levelTickCount;
    private long timePlayed;
    private long timeRemaining;

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
            timer = new Timer(1000 / FRAMERATE, _e -> {
                tickIncrement();
                update();
            });
            timer.start();
    }
    protected void stop() {
        timer.stop();
    }
    public boolean isRunning() {
        return timer.isRunning();
    }

    private GameClock() {
        tickCount = 0;
        levelTickCount = 0;
        timePlayed = 0;
        timeRemaining = 0;
    }
}
