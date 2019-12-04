package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.Test;
import com.mygdx.game.sprites.Entity;
import com.mygdx.game.sprites.Firetruck;
import com.mygdx.game.sprites.TestEntity;

import java.util.ArrayList;
//import com.mygdx.game.sprites.Firetruck;

public class PlayState extends State {

    private Texture background;
    private Entity obstacle;
    private Entity obstacle2;
    private Preferences settings;
    private Firetruck truck1;
    private Firetruck truck2;
    public ArrayList<Entity> obstacles = new ArrayList<Entity>();
    public ArrayList<Firetruck> trucks = new ArrayList<Firetruck>();

    public PlayState(GameStateManager gsm) {
        super(gsm);
        // https://github.com/libgdx/libgdx/wiki/Tile-maps possible way of making a map?
        background = new Texture("playbg.png");
        obstacle = new Entity(100, 100, new Texture("blue.jpg"), new Vector2(500, 400));
        obstacle2 = new Entity(400, 400, new Texture("green.jpg"), new Vector2(400, 100));
        settings = Gdx.app.getPreferences("My Preferences");
        truck1 = new Firetruck(new Vector2(100, 550), 100, 100, 5, 2,
                new Texture("truck.png"), null, 10, 10, 10, 10, true);
        truck2 = new Firetruck(new Vector2(100, 100), 100, 100, 5, 2,
                new Texture("truck.png"), null, 10, 10, 10, 10, false);
        trucks.add(truck1);
        trucks.add(truck2);
    }

    @Override
    public void handleInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            gsm.push(new EndState(gsm));
        }

        if (Gdx.input.isKeyPressed(Input.Keys.L)) {
            gsm.push(new MenuState(gsm));
        }
        Vector2 mousePos = new Vector2(Gdx.input.getX(), Test.HEIGHT - Gdx.input.getY());
        if (Gdx.input.isTouched()) {
            for (Firetruck truck : trucks) {
                if (mousePos.x >= (truck.getPosition().x) && mousePos.x <= (truck.getPosition().x + truck.getWidth())
                        && mousePos.y >= (truck.getPosition().y) && mousePos.y <= (truck.getPosition().y + truck.getHeight())) {
                    for (Firetruck clearTruck : trucks) {
                        clearTruck.setSelected(false);
                    }
                    truck.setSelected(true);
                }
            }
        }
        if (truck1.isSelected()) {
            truckMovement(truck1);
        } else if (truck2.isSelected()){
            truckMovement(truck2);
        }

    }

    @Override
    public void update(float dt) { ;
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(background, 0, 0, Test.WIDTH, Test.HEIGHT);
        sb.draw(obstacle.getTexture(), obstacle.getPosition().x, obstacle.getPosition().y, obstacle.getWidth(), obstacle.getHeight());
        sb.draw(obstacle2.getTexture(), obstacle2.getPosition().x, obstacle2.getPosition().y, obstacle2.getWidth(), obstacle2.getHeight());
        sb.draw(truck1.getTexture(), truck1.getPosition().x, truck1.getPosition().y, 100, 100);
        sb.draw(truck2.getTexture(), truck2.getPosition().x, truck2.getPosition().y, 100, 100);
        sb.end();
    }
     // https://stackoverflow.com/questions/33283867/how-to-make-a-sprite-move-with-keyboard-in-javalibgdx?rq=1 source used
    // Should really have a 'SPEED' constant instead of using the number 10 so that it can be changed easily.

    public void truckMovement(Firetruck truck) {
        if(Gdx.input.isKeyPressed(Input.Keys.W)) {
            if(truck.getPosition().y == 550) {
                truck.setPosition(truck.getPosition().x, 550);
            }
            else if (truck.willCollide(obstacle2, "up")) {
            }
            else {
                truck.move("up");
            }
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S)) {
            if(truck.getPosition().y == 50) {
                truck.setPosition(truck.getPosition().x, 50);
            }
            else if (truck.willCollide(obstacle2, "down")) {
            }
            else {
                truck.move("down");
            }
        }

        if(Gdx.input.isKeyPressed(Input.Keys.A)) {
            if(truck.getPosition().x == 50) {
                truck.setPosition(50, truck.getPosition().y);
            }
            else if (truck.willCollide(obstacle2, "left")) {
            }
            else {
                truck.move("left");
            }
        }

        if(Gdx.input.isKeyPressed(Input.Keys.D)) {
            if(truck.getPosition().x == 850) {
                truck.setPosition(850, truck.getPosition().y);
            }
            else if (truck.willCollide(obstacle2, "right")) {
            }
            else {
                truck.move("right");
            }
        }
    }

    @Override
    public void dispose() {

    }
}
