package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class EndState extends State{
    private Texture background;

    public EndState(GameStateManager gsm, boolean status) {
        super(gsm);
        if (status) {
            background = new Texture("levelcomplete.png");
        }
        else {
            background = new Texture("levelFail.png");
        }
    }

    public void handleInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            gameStateManager.pop();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(background, 0, 0);
        sb.end();
    }

    @Override
    public void dispose() {

    }
}
