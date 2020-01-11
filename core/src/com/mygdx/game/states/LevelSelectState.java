package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Kroy;
import com.mygdx.game.misc.Button;

import java.util.ArrayList;

public class LevelSelectState extends State{

    private Texture background;
    private Button level1;
    private Button level2;
    private Button level3;
    private Button level4;
    private Button level5;
    private Button level6;
    private Button back;
    private ArrayList buttons = new ArrayList<Button>();

    protected LevelSelectState(GameStateManager gameStateManager) {
        super(gameStateManager);
        back = new Button(new Texture("backbutton2.png"), new Texture("backbutton1.png"),
                100, 100, new Vector2(30, 960), false);

        level1 = new Button(new Texture(".png"), new Texture(".png"),
                350, 100, new Vector2(30, 960), false);

        level2 = new Button(new Texture(".png"), new Texture(".png"),
                350, 100, new Vector2(30, 960), false);

        level3 = new Button(new Texture(".png"), new Texture(".png"),
                350, 100, new Vector2(30, 960), false);

        level4 = new Button(new Texture(".png"), new Texture(".png"),
                350, 100, new Vector2(30, 960), false);

        level5 = new Button(new Texture(".png"), new Texture(".png"),
                350, 100, new Vector2(30, 960), false);

        level6 = new Button(new Texture(".png"), new Texture(".png"),
                350, 100, new Vector2(30, 960), false);
        background = new Texture("Menu.jpg");
    }

    @Override
    public void update(float deltaTime) {

        if (back.mouseInRegion()) {
            back.setActive(true);
            if (Gdx.input.isTouched()) {
                gameStateManager.pop();
            }
        } else {
            back.setActive(false);

        }
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.begin();
        spriteBatch.draw(background, 0, 0, Kroy.WIDTH, Kroy.HEIGHT);
        spriteBatch.draw(back.getTexture(), back.getPosition().x, back.getPosition().y, back.getWidth(), back.getHeight());
        spriteBatch.end();
    }

    @Override
    public void dispose() {

    }
}
