package nz.ac.vuw.ecs.swen225.gp22.app;

public class InputGenerator {
    GameHandler game;

    public void up() {
//        game.input.mvUp();
        System.out.println("Generated input: mvUp()");
    }
    public void down() {
//        game.input.mvDown();
        System.out.println("Generated input: mvDown()");
    }
    public void left() {
//        game.input.mvLeft();
        System.out.println("Generated input: mvLeft()");
    }
    public void right() {
//        game.input.mvRight();
        System.out.println("Generated input: mvRight()");
    }

    public InputGenerator(GameHandler game) {
        this.game = game;
    }
}
