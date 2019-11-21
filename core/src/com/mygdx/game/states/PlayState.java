package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.Test;
import com.mygdx.game.sprites.TestEntity;

public class PlayState extends State {

    private Texture background;
    private TestEntity obstacle;
    private Vector3 touchPos;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        // https://github.com/libgdx/libgdx/wiki/Tile-maps possible way of making a map?
        background = new Texture("playbg.png");
        obstacle = new TestEntity(500, 400);
    }

    @Override
    public void handleInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            gsm.push(new EndState(gsm));
        }
//        truckMovement(obstacle);
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(background, 0, 0, Test.WIDTH, Test.HEIGHT);
        sb.draw(obstacle.getTexture(), obstacle.getPosition().x, obstacle.getPosition().y);
        sb.end();
    }
     // https://stackoverflow.com/questions/33283867/how-to-make-a-sprite-move-with-keyboard-in-javalibgdx?rq=1 source used
    // Should really have a 'SPEED' constant instead of using the number 10 so that it can be changed easily.

//    public void truckMovement(Entity truck) {
//        if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
//            if(truck.getPosition().y == 550) {
//                truck.setPosition(truck.getPosition().x, 550);
//            }
//            else {
//                truck.move(0, 10);
//            }
//        }
//        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
//            if(truck.getPosition().y == 50) {
//                truck.setPosition(truck.getPosition().x, 50);
//            }
//            else {
//                truck.move(0, -10);
//            }
//        }
//
//        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
//            if(truck.getPosition().x == 50) {
//                truck.setPosition(50, truck.getPosition().y);
//            }
//            else {
//                truck.move(-10, 0);
//            }
//        }
//
//        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
//            if(truck.getPosition().x == 850) {
//                truck.setPosition(850, truck.getPosition().y);
//            }
//            else {
//                truck.move(10, 0);
//            }
//        }
//    }

    @Override
    public void dispose() {

    }
}
