package nz.ac.vuw.ecs.swen225.gp22.app;

import java.util.ArrayList;
import java.util.List;

public class GameClock implements Subject {
    private static GameClock clock;
    private final ArrayList<Observer> timedElements; // Move the observer stuff to a separate class.

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

    @Override
    public void update() {
        List.copyOf(timedElements).forEach(Observer::update);
    }
    @Override
    public void register(Observer ob) {
        timedElements.add(ob);
    }
    @Override
    public void unregister(Observer ob) {
        timedElements.remove(ob);
    }

    public long currentTick()      { return tickCount; }
    public long currentTime()      { return timePlayed; }
    public long currentLevelTick() { return levelTickCount; }
    public long currentLevelTime() { return timeRemaining; }

    protected void setLevelTime(long time) {
        this.timeRemaining = time;
    }
    protected  void tickIncrement() {
        levelTickCount++;
        tickCount++;
    }
    protected  void resetLevelTick() { levelTickCount = 0L; }


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
        timedElements = new ArrayList<>();
    }
}
