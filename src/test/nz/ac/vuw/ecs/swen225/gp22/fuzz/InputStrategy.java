package nz.ac.vuw.ecs.swen225.gp22.fuzz;

interface InputStrategy {
    int nextInput();
    boolean isPressCtrl();
}