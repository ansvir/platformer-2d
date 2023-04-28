package com.tohant.platformer2d.actor;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class HeroActor extends CollisionActor {

    private Texture texture;
    private float strength;

    public HeroActor(Texture texture, float weight, float density, float strength) {
        super(weight, density);
        this.texture = texture;
        this.strength = strength;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(texture, getX(), getY());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    public float getStrength() {
        return strength;
    }
}
