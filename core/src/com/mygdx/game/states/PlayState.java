package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.Test;
import com.mygdx.game.sprites.Entity;
import com.mygdx.game.sprites.Unit;

public class PlayState extends State {

    private Texture background;
    private Entity obstacle;
    private Vector3 touchPos;

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

        truckMovement(obstacle);
    }

    public void truckMovement(Entity truck) {
        if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
            if(truck.getPosition().y == 550) {
                truck.setPosition(truck.getPosition().x, 550);
            }
            else {
                truck.move(0, 10);
            }
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            if(truck.getPosition().y == 50) {
                truck.setPosition(truck.getPosition().x, 50);
            }
            else {
                truck.move(0, -10);
            }
        }

        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            if(truck.getPosition().x == 50) {
                truck.setPosition(50, truck.getPosition().y);
            }
            else {
                truck.move(-10, 0);
            }
        }

        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            if(truck.getPosition().x == 850) {
                truck.setPosition(850, truck.getPosition().y);
            }
            else {
                truck.move(10, 0);
            }
        }
    }

    @Override
    public void dispose() {

    }
}
