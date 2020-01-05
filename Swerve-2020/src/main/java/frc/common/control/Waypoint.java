package frc.common.control;

import frc.common.math.Rotation2;
import frc.common.math.Vector2;

public class Waypoint {
    public final Vector2 position;
    public final Rotation2 heading;
    public final Rotation2 rotation;

    public Waypoint(Vector2 position, Rotation2 heading) {
        this.position = position;
        this.heading = heading;
        this.rotation = heading;
    }

    public Waypoint(Vector2 position, Rotation2 heading, Rotation2 rotation) {
        this.position = position;
        this.heading = heading;
        this.rotation = rotation;
    }
}
