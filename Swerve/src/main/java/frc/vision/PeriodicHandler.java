package frc.vision;

import frc.api.PeriodicPacket;
import frc.api.Ports;
import frc.api.VisionClient;
import frc.api.VisionServer;
import frc.common.Logger;

import java.io.IOException;

public class PeriodicHandler implements Runnable {
    private static final Logger LOGGER = new Logger(PeriodicHandler.class);

    private VisionServer<Object, PeriodicPacket> server;

    public PeriodicHandler() throws IOException {
        this.server = new VisionServer<>(Ports.PERIODIC_PORT);
    }

    @Override
    public void run() {
        while (!Thread.interrupted() && server.getSocket().isBound()) {
            try (VisionClient<Object, PeriodicPacket> connection = server.accept()) {
                LOGGER.info("Client connected from IP: %s", connection.getSocket().getInetAddress().getHostName());
                while (!Thread.interrupted() && connection.getSocket().isConnected()) {
                    PeriodicPacket packet = connection.receivePacket();

                    RobotStateEstimator.addState(
                            packet.getTimestamp(),
                            packet.getPose(),
                            packet.getVelocity(),
                            packet.getRotationalVelocity()
                    );
                }
                LOGGER.info("Connection to client (%s) was closed.", connection.getSocket().getInetAddress().getHostName());
            } catch (IOException e) {
                LOGGER.error(e);
            }
        }
    }
}
