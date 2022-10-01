package nz.ac.vuw.ecs.swen225.gp22.app;

import nz.ac.vuw.ecs.swen225.gp22.domain.Domain;

import javax.swing.*;
import java.util.List;

interface GameState {
    Runnable action(Domain d);
    public List<JPanel> panels();

    public GameState nextLevel();
}

