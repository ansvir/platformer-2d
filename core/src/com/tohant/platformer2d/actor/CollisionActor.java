package com.tohant.platformer2d.actor;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class CollisionActor extends Actor {

    private Rectangle collisionZone;
    private boolean inCollision;
    private float weight;
    private float density;

    public CollisionActor(float weight, float density) {
        this.weight = weight;
        this.density = density;
        collisionZone = new Rectangle(getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        collisionZone.x = getX();
        collisionZone.y = getY();
        collisionZone.width = getWidth();
        collisionZone.height = getHeight();
    }

    public boolean inCollision(CollisionActor collisionActor) {
        return collisionZone.overlaps(collisionActor.collisionZone);
    }

    public boolean nextInCollision(CollisionActor collisionActor, Vector2 position) {
        Rectangle prev = new Rectangle(collisionZone.x, collisionZone.y, collisionZone.width, collisionZone.height);
        this.collisionZone = new Rectangle(position.x, position.y, collisionZone.width, collisionZone.height);
        boolean result = inCollision(collisionActor);
        this.collisionZone = prev;
        return result;
    }

    public boolean XInCollision(CollisionActor collisionActor) {
        return collisionZone.x <= collisionActor.collisionZone.x
                && collisionZone.x + collisionZone.width >= collisionActor.collisionZone.x;
    }

    public boolean XRightInCollision(CollisionActor collisionActor) {
        return collisionZone.x + collisionZone.width >= collisionActor.collisionZone.x;
    }

    public boolean XLeftInCollision(CollisionActor collisionActor) {
        return collisionZone.x <= collisionActor.collisionZone.x;
    }

    public boolean YInCollision(CollisionActor collisionActor) {
        return collisionZone.y <= collisionActor.collisionZone.y
                && collisionZone.y + collisionZone.height >= collisionActor.collisionZone.y;
    }

    public boolean YTopInCollision(CollisionActor collisionActor) {
        return collisionZone.y + collisionZone.height >= collisionActor.collisionZone.y;
    }

    public boolean YBottomInCollision(CollisionActor collisionActor) {
        return collisionZone.y <= collisionActor.collisionZone.y;
    }

    public boolean isInCollision() {
        return inCollision;
    }

    public void setInCollision(boolean inCollision) {
        this.inCollision = inCollision;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getDensity() {
        return density;
    }

    public Rectangle getCollisionZone() {
        return collisionZone;
    }
}
