package com.tohant.platformer2d.custom;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.tohant.platformer2d.actor.CollisionActor;
import com.tohant.platformer2d.actor.HeroActor;
import com.tohant.platformer2d.input.KeyboardInputProcessor;

import static com.badlogic.gdx.Input.Keys.*;

public class MovementProcessor {

    private KeyboardInputProcessor keyboardInputProcessor;
    private HeroActor heroActor;
    private Environment environment;
    private JumpMovement jumpMovement;

    public MovementProcessor(KeyboardInputProcessor keyboardInputProcessor, HeroActor heroActor, Environment environment) {
        this.keyboardInputProcessor = keyboardInputProcessor;
        this.heroActor = heroActor;
        this.environment = environment;
        jumpMovement = new JumpMovement(environment.getGravitationSpeed(), environment.getGravitationAcceleration(),
                heroActor.getWeight(), heroActor.getStrength());
    }

    public void updateMovement(float delta) {
        float deltaX = 0;
        float deltaY = 0;
        for (int key : keyboardInputProcessor.getKeysPressed().iterator().toArray().items) {
            if (key == A) {
                deltaX -= heroActor.getWeight() * heroActor.getStrength() / environment.getSpeed() * delta + 5;
            }
            if (key == D) {
                deltaX += heroActor.getWeight() * heroActor.getStrength() / environment.getSpeed()  * delta + 5;
            }
            if (key == SPACE) {
                if (!jumpMovement.isUp()) {
                jumpMovement = new JumpMovement(environment.getGravitationSpeed(), environment.getGravitationAcceleration(),
                        heroActor.getWeight(), heroActor.getStrength());
                    jumpMovement.initJump();
                }
            }
        }
        if (jumpMovement.isUp()) {
            jumpMovement.processJump(delta);
            deltaY += jumpMovement.getGravitationDelta();
        } else {
            deltaY -= environment.getGravitationSpeed();
            System.out.println("deltaY = " + deltaY + ", environment.getSpeed() = " + environment.getSpeed());
            System.out.println(heroActor.getCollisionZone().getY());
        }
        boolean collideX = false;
        boolean collideY = false;
        Rectangle heroZone = heroActor.getCollisionZone();
        for (CollisionActor collider : environment.getStaticActors()) {
                Rectangle colliderZone = collider.getCollisionZone();
                if (checkNextXCollide(collider, deltaX) && checkCollideX(heroZone, colliderZone) && !collideX) {
                    collideX = true;
                }
                if (checkNextYCollide(collider, deltaY) && checkCollideY(heroZone, colliderZone) && !collideY) {
                    collideY = true;
                }
        }
        if (!collideX) {
            heroActor.setX(heroActor.getX() + deltaX);
        }
        if (!collideY) {
            heroActor.setY(heroActor.getY() + deltaY);
        }
    }

    private boolean checkCollideX(Rectangle heroZone, Rectangle colliderZone) {
        return heroZone.x + heroZone.width > colliderZone.x || heroZone.x < colliderZone.x + colliderZone.width;
    }

    private boolean checkCollideY(Rectangle heroZone, Rectangle colliderZone) {
        return heroZone.y + heroZone.height > colliderZone.y || heroZone.y < colliderZone.y + colliderZone.height;
    }

    private boolean checkNextXCollide(CollisionActor collider, float deltaX) {
        Rectangle heroZone = heroActor.getCollisionZone();
        Rectangle nextZone = new Rectangle(heroZone.x + deltaX, heroZone.y, heroZone.width, heroZone.height);
        Rectangle colliderZone = collider.getCollisionZone();
        return nextZone.overlaps(colliderZone);
    }

    private boolean checkNextYCollide(CollisionActor collider, float deltaY) {
        Rectangle heroZone = heroActor.getCollisionZone();
        Rectangle nextZone = new Rectangle(heroZone.x, heroZone.y + deltaY, heroZone.width, heroZone.height);
        Rectangle colliderZone = collider.getCollisionZone();
        return nextZone.overlaps(colliderZone);
    }

}
