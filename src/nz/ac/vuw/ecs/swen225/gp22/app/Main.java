package nz.ac.vuw.ecs.swen225.gp22.app;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        GameHandler g = new GameHandler();
        g.start();
        GameClock.get().start();
        Thread.sleep(10000);
        GameClock.get().stop();
    }
}