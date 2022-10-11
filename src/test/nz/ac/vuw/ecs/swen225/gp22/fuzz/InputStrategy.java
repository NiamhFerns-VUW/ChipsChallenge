package nz.ac.vuw.ecs.swen225.gp22.fuzz;

public interface InputStrategy {
    int nextInput();
    boolean isPressCtrl();
}
