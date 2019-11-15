package frc.common.math.spline;

import frc.common.math.Rotation2;
import frc.common.math.Vector2;

public abstract class Spline {

    public abstract Vector2 getPoint(double t);

    public abstract Rotation2 getHeading(double t);
}
