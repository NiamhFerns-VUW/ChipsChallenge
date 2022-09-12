package nz.ac.vuw.ecs.swen225.gp22.app;

public class GameClock {
    private static GameClock clock;
    private static final Runnable onTick = () -> {
        // Do something to determine time to wait for a tick... Need to talk to Borgar about it.
        get().tickIncrement();
    };
    private static final Thread tickThread = new Thread(onTick, "Tick Thread");

    private long tick;
    private long levelTick;
    private long time;
    private long levelTime;

    public static GameClock get() {
        if (clock == null) clock = new GameClock();
        return clock;
    }

    public long currentTick()      { return tick; }
    public long currentTime()      { return time; }
    public long currentLevelTick() { return levelTick; }
    public long currentLevelTime() { return levelTime; }

    protected void setLevelTime(long time) {
        this.time = time;
    }
    protected  void tickIncrement() {
        levelTick++;
        tick++;
    }
    protected  void resetLevelTick() { levelTick = 0L; }

    protected static void stop() {
        tickThread.stop(); // This is bad but it works for now.
    }

    private GameClock() {
        tick = 0;
        levelTick = 0;
        time = 0;
        levelTime = 0;
        tickThread.start();
    }

}
