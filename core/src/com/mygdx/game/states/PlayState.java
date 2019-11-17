package com.mygdx.game.states;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Test;
import com.mygdx.game.sprites.Entity;
import com.mygdx.game.sprites.Unit;

public class PlayState extends State {
    private Texture background;
    private Unit test;
    private Entity obstacle;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("playbg.png");
        obstacle = new Entity(500, 400);
    }

    @Override
    public void handleInput() {
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
