package net.sydokiddo.endlessencore.util;

import net.minecraft.client.Camera;

// Elytra Camera Tilt Utility Code
// Forked from Nimble, a Fabric 1.18 mod by Snowzee which has an MIT licence

public class CameraSetup {

    private final Camera camera;
    private float roll;

    public CameraSetup(Camera camera) {
        this.camera = camera;
    }

    public Camera getInfo() {
        return camera;
    }

    public void setRoll(float roll) {
        this.roll = roll;
    }

    public float getRoll() {
        return roll;
    }
}