package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Kroy;

public class CreditState extends State {

    private Texture background;
    private com.mygdx.game.misc.Button back;

    public CreditState(GameStateManager gameStateManager) {
        super(gameStateManager);
        background = new Texture("CreditsBackground.png");
        back = new com.mygdx.game.misc.Button(new Texture("backbutton2.png"),
                new Texture("backbutton1.png"), 100, 100,
                new Vector2(30, 960), false, false);

    }

    public void handleInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            gameStateManager.pop();
        }

        if (back.mouseInRegion()){
            back.setActive(true);
            if (Gdx.input.isTouched()) {
                gameStateManager.pop();
            }
        }
        else {
            back.setActive(false);
        }
    }

    @Override
    public void update(float deltaTime) {
        handleInput();

    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.begin();
        spriteBatch.draw(background, 0, 0, Kroy.WIDTH, Kroy.HEIGHT);
        spriteBatch.draw(back.getTexture(), back.getPosition().x, back.getPosition().y,
                back.getWidth(), back.getHeight());
        spriteBatch.end();

    }

    @Override
    public void dispose() {
        background.dispose();
    }
}

