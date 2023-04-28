package com.tohant.platformer2d.custom;

import com.badlogic.gdx.utils.Array;
import com.tohant.platformer2d.actor.HeroActor;
import com.tohant.platformer2d.actor.StaticActor;

public class Environment {

    private Array<HeroActor> heroActors;
    private Array<StaticActor> staticActors;
    private float gravitationSpeed;
    private float gravitationAcceleration;
    private float speed;

    public Environment(float gravitationSpeed, float gravitationAcceleration) {
        heroActors = new Array<>();
        staticActors = new Array<>();
        this.gravitationSpeed = gravitationSpeed;
        this.gravitationAcceleration = gravitationAcceleration;
        this.speed = gravitationSpeed * gravitationAcceleration;
    }

    public Array<HeroActor> getHeroActors() {
        return heroActors;
    }

    public Array<StaticActor> getStaticActors() {
        return staticActors;
    }

    public float getGravitationSpeed() {
        return gravitationSpeed;
    }

    public float getGravitationAcceleration() {
        return gravitationAcceleration;
    }

    public float getSpeed() {
        return speed;
    }

}
