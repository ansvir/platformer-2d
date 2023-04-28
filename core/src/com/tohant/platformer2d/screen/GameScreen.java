package com.tohant.platformer2d.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.tohant.platformer2d.actor.HeroActor;
import com.tohant.platformer2d.actor.StaticActor;
import com.tohant.platformer2d.custom.Environment;
import com.tohant.platformer2d.custom.MovementProcessor;
import com.tohant.platformer2d.input.KeyboardInputProcessor;

public class GameScreen implements Screen {

    private Game game;
    private HeroActor heroActor;
    private StaticActor staticActorGround;
    private Stage gameStage;
    private Viewport viewport;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private InputMultiplexer multiplexer;
    private MovementProcessor movementProcessor;
    private Environment environment;

    public GameScreen(Game game) {
        this.game = game;
    }

    @Override
    public void show() {
        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera = new OrthographicCamera();
        batch = new SpriteBatch();
        gameStage = new Stage(viewport, batch);
        Pixmap pixmap = new Pixmap(100, 200, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.RED);
        pixmap.fill();
        heroActor = new HeroActor(new Texture(pixmap), 85, 95.0f, 7);
        heroActor.setPosition(200, 251);
        heroActor.setSize(100, 200);
        Pixmap pixmap2 = new Pixmap(Gdx.graphics.getWidth(), 250, Pixmap.Format.RGBA8888);
        pixmap2.setColor(Color.GREEN);
        pixmap2.fill();
        staticActorGround = new StaticActor(new Texture(pixmap2), 2000, 100.0f);
        staticActorGround.setPosition(0, 0);
        staticActorGround.setSize(Gdx.graphics.getWidth(), 250);
        Pixmap pixmap3 = new Pixmap(250, 250, Pixmap.Format.RGBA8888);
        pixmap3.setColor(Color.YELLOW);
        pixmap3.fill();
        StaticActor block = new StaticActor(new Texture(pixmap3), 150, 100.0f);
        block.setPosition(1000, 250);
        block.setSize(250, 250);
        environment = new Environment(20, 3.5f);
        environment.getStaticActors().addAll(staticActorGround, block);
        environment.getHeroActors().add(heroActor);
        gameStage.addActor(heroActor);
        gameStage.addActor(staticActorGround);
        gameStage.addActor(block);
        KeyboardInputProcessor keyboardInputProcessor = new KeyboardInputProcessor();
        multiplexer = new InputMultiplexer(keyboardInputProcessor);
        Gdx.input.setInputProcessor(multiplexer);
        movementProcessor = new MovementProcessor(keyboardInputProcessor, heroActor, environment);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.WHITE);
        batch.begin();
        batch.end();
        gameStage.draw();
        gameStage.act(delta);
        movementProcessor.updateMovement(delta);
        camera.update();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        gameStage.dispose();
        batch.dispose();
    }
}
