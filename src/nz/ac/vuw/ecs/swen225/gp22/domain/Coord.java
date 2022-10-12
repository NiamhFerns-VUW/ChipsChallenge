package nz.ac.vuw.ecs.swen225.gp22.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

/**
 *
 */
@JsonAutoDetect(fieldVisibility = Visibility.ANY)
@JsonSerialize
public final class Coord {

    @JsonProperty("x")
    private final int x;
    @JsonProperty("y")
    private final int y;

    /**
     * @param x - x position of the coordinate
     * @param y - y position of the coordinate
     */
    public Coord(
        @JsonProperty("x") int x,
        @JsonProperty("y") int y
    ) {
        this.x = x;
        this.y = y;
    }
    public Coord() {
        this.x = -1;
        this.y=-1;
    }

    @JsonProperty("x")
    public int x() {
        return x;
    }

    @JsonProperty("y")
    public int y() {
        return y;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        var that = (Coord) obj;
        return this.x == that.x &&
            this.y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Coord[" +
            "x=" + x + ", " +
            "y=" + y + ']';
    }

}
