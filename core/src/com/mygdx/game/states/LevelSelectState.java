package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Kroy;
import com.mygdx.game.misc.Button;
import com.mygdx.game.sprites.Alien;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * An implementation of the abstract class State which controls the
 * menu screen rendering and input handling.
 *
 * @author Lucy Ivatt
 * @since 11/1/2020
 */

public class LevelSelectState extends State{

    private Texture background;
    private Button level1;
    private Button level2;
    private Button level3;
    private Button level4;
    private Button level5;
    private Button level6;
    private Button back;
    private ArrayList<Button> buttons = new ArrayList<Button>();

    protected LevelSelectState(GameStateManager gameStateManager) {
        super(gameStateManager);
        back = new Button(new Texture("backbutton2.png"), new Texture("backbutton1.png"),
                100, 100, new Vector2(30, 960), false);

        level1 = new Button(new Texture("PressedBlue1.png"), new Texture("NotPressedBlue1.png"),
                350, 100, new Vector2(Kroy.WIDTH / 2 - 350 / 2 - 100 - 350, 400), false);

        level2 = new Button(new Texture("PressedBlue2.png"), new Texture("NotPressedBlue2.png"),
                350, 100, new Vector2(Kroy.WIDTH / 2 - 350 / 2 - 100 - 350, 200), false);

        level3 = new Button(new Texture("PressedBlue3.png"), new Texture("NotPressedBlue3.png"),
                350, 100, new Vector2(Kroy.WIDTH / 2 - 350 / 2, 400), false);

        level4 = new Button(new Texture("PressedBlue4.png"), new Texture("NotPressedBlue4.png"),
                350, 100, new Vector2(Kroy.WIDTH / 2 - 350 / 2, 200), false);

        level5 = new Button(new Texture("PressedBlue5.png"), new Texture("NotPressedBlue5.png"),
                350, 100, new Vector2(Kroy.WIDTH / 2 + 350 / 2 + 100, 400), false);

        level6 = new Button(new Texture("PressedBlue6.png"), new Texture("NotPressedBlue6.png"),
                350, 100, new Vector2(Kroy.WIDTH / 2 + 350 / 2 + 100, 200), false);

        buttons.add(level1);
        buttons.add(level2);
        buttons.add(level3);
        buttons.add(level4);
        buttons.add(level5);
        buttons.add(level6);
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
        for (Button level : buttons) {
            spriteBatch.draw(level.getTexture(), level.getPosition().x, level.getPosition().y, level.getWidth(), level.getHeight());
        }
        spriteBatch.end();
    }

    @Override
    public void dispose() {

    }
}
