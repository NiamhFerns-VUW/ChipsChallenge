package nz.ac.vuw.ecs.swen225.gp22.app;

interface Subject {
    void update();
    void register(Observer ob);
    void unregister(Observer ob);
}
