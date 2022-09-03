package nz.ac.vuw.ecs.swen225.gp22.persistency.domain_classes;

public class Chap implements Entity {
    Direction direction;
    public Chap(Direction direction) {
        this.direction = direction;
    }
    public Chap(){}
    void move(Direction direction) {

    }
    void interact() {

    }

    public Direction getDirection() {
        return direction;
    }
}
