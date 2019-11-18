package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.Test;
import com.mygdx.game.sprites.Entity;
import com.mygdx.game.sprites.Unit;

public class PlayState extends State {
    private Texture background;
    private Unit test;
    private Entity obstacle;

    Vector3 touchPos;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("playbg.png");
        obstacle = new Entity(500, 400);
    }

    @Override
    public void handleInput() {
        if (Gdx.input.isTouched()) {
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);

        }
    }

    @Override
    public void update(float dt) {
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(background, 0, 0, Test.WIDTH, Test.HEIGHT);
        sb.draw(obstacle.getTexture(), obstacle.getPosition().x, obstacle.getPosition().y);
        sb.end();
    }

    @Override
    public void dispose() {

    }
}
