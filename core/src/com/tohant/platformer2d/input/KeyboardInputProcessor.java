package com.tohant.platformer2d.input;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.utils.IntSet;

import static com.badlogic.gdx.Input.Keys.*;

public class KeyboardInputProcessor implements InputProcessor {

    private IntSet keysPressed;

    public KeyboardInputProcessor() {
        keysPressed = new IntSet();
    }

    @Override
    public boolean keyDown(int keycode) {
        keysPressed.add(keycode);
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        keysPressed.remove(keycode);
        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }

    public IntSet getKeysPressed() {
        return keysPressed;
    }
}
