package frc.vision.drivers;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import frc.common.math.MathUtils;
import frc.common.math.Vector2;


public final class Limelight {
    public final NetworkTable table;

    public final NetworkTableEntry tv;
    public final NetworkTableEntry tx;
    public final NetworkTableEntry ty;
    public final NetworkTableEntry ta;
    public final NetworkTableEntry ts;
    public final NetworkTableEntry tl;

    private final NetworkTableEntry tcornx;
    private final NetworkTableEntry tcorny;
    private final NetworkTableEntry camtran;

    private final NetworkTableEntry ledMode;
    private final NetworkTableEntry camMode;
    private final NetworkTableEntry pipeline;
    private final NetworkTableEntry stream;
    private final NetworkTableEntry snapshot;

    public Limelight(NetworkTable table) {
        this.table = table;

        tv = table.getInstance().getEntry("tv");
        tx = table.getInstance().getEntry("tx");
        ty = table.getInstance().getEntry("ty");
        ta = table.getInstance().getEntry("ta");
        ts = table.getInstance().getEntry("ts");
        tl = table.getInstance().getEntry("tl");

        tcornx = table.getInstance().getEntry("tcornx");
        tcorny = table.getInstance().getEntry("tcorny");
        camtran = table.getInstance().getEntry("camtran");

        ledMode = table.getInstance().getEntry("ledMode");
        camMode = table.getInstance().getEntry("camMode");
        pipeline = table.getInstance().getEntry("pipeline");
        stream = table.getInstance().getEntry("stream");
        snapshot = table.getInstance().getEntry("snapshot");
    }

    public boolean hasTarget() {
        return MathUtils.epsilonEquals(tv.getDouble(0), 1);
    }

    public double getTargetArea() {
        return ta.getDouble(0);
    }

    public Vector2 getTargetPosition() {
        return new Vector2(Math.toRadians(tx.getDouble(0)), Math.toRadians(ty.getDouble(0)));
    }

    public double getTargetSkew() {
        return ts.getDouble(0);
    }

    public boolean getCorners(int numOfCorners, double[][] corners) {
        double[] x = tcornx.getDoubleArray(new double[]{0.0, 0.0});
        double[] y = tcorny.getDoubleArray(new double[]{0.0, 0.0});
        if (x.length != numOfCorners) {
            return false;
        }
        for (int i = 0; i < x.length; i++) {
            corners[i][0] = x[i];
            corners[i][1] = y[i];
        }
        return true;
    }

    public Number[] getPosition() {
        Number[] defaultValue = {0, 0};
        return camtran.getNumberArray(defaultValue);
    }

    public void setCamMode(CamMode mode) {
        switch (mode) {
            case VISION:
                camMode.setNumber(0);
                break;
            case DRIVER:
                camMode.setNumber(1);
        }
    }

    public void setLedMode(LedMode mode) {
        switch (mode) {
            case DEFAULT:
                ledMode.setNumber(0);
                break;
            case OFF:
                ledMode.setNumber(1);
                break;
            case BLINK:
                ledMode.setNumber(2);
                break;
            case ON:
                ledMode.setNumber(3);
                break;
        }
    }

    public void setSnapshotsEnabled(boolean enabled) {
        if (enabled) {
            snapshot.setNumber(1);
        } else {
            snapshot.setNumber(0);
        }
    }

    public void setPipeline(int pipeline) {
        this.pipeline.setNumber(pipeline);
    }

    public void setStreamMode(StreamMode mode) {
        switch (mode) {
            case STANDARD:
                stream.setNumber(0);
                break;
            case PIP_MAIN:
                stream.setNumber(1);
                break;
            case PIP_SECONDARY:
                stream.setNumber(2);
                break;
        }
    }

    public enum CamMode {
        VISION,
        DRIVER
    }

    public enum LedMode {
        DEFAULT,
        ON,
        OFF,
        BLINK
    }

    public enum StreamMode {
        STANDARD,
        PIP_MAIN,
        PIP_SECONDARY
    }

    public double getDistHighGoal(){
        return 0.0;
    }
}
