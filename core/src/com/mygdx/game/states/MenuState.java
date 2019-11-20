package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.mygdx.game.Test;

public class MenuState extends State {

    private Texture background;
    private Texture buttonTexture;
    private Vector2 position;


    public MenuState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("menuExample.png");
        buttonTexture = new Texture("blue_button00.png");

    }

    @Override
    public void handleInput() {
        // Got hover button working but the coordinate system for position go from top left and the coordinate system for drawing goes from bottom right BRUH
        position = new Vector2(Gdx.input.getX(), 700 - Gdx.input.getY());
        if (position.x >= 300 && position.x <= 300 + 100 && position.y >= 200 && position.y <= 200 + 20) {
            buttonTexture = new Texture("green_button00.png");
            }
        else {
            buttonTexture = new Texture("blue_button00.png");
        }
        System.out.println(position);
        }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(background, 0, 0, Test.WIDTH, Test.HEIGHT);
        sb.draw(buttonTexture, 300, 200, 100, 20);
        sb.end();

    }

    @Override
    public void dispose() {
        background.dispose();
    }
}
