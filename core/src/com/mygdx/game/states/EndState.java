package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class EndState extends State{
    private Texture background;

    public EndState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("EndScreen.png");
    }

    @Override
    public void handleInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            gsm.pop();
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
