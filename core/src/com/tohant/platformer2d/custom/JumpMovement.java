package com.tohant.platformer2d.custom;

public class JumpMovement {

    private float upSpeed;
    private float gravitationDelta;
    private boolean isUp;

    public JumpMovement(float gravitationSpeed, float gravitationAcceleration, float weight, float strength) {
        this.upSpeed = (strength * weight) / (gravitationSpeed * gravitationAcceleration);
        this.gravitationDelta = this.upSpeed;
    }

    public void initJump() {
        isUp = true;
    }

    public void processJump(float delta) {
        if (gravitationDelta <= 0) {
            isUp = false;
        }
        if (isUp) {
            gravitationDelta += upSpeed * delta;
            upSpeed -= 0.5;
        }
    }

    public boolean isUp() {
        return isUp;
    }

    public float getGravitationDelta() {
        return gravitationDelta;
    }
}
