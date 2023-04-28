package com.tohant.platformer2d.actor;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class StaticActor extends CollisionActor {

    private Texture texture;

    public StaticActor(Texture texture, float weight, float density) {
        super(weight, density);
        this.texture = texture;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(texture, getX(), getY());
    }

}
