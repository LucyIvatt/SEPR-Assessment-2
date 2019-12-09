package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Timer;
import com.mygdx.game.Test;
import com.mygdx.game.sprites.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
//import com.mygdx.game.sprites.Firetruck;

public class PlayState extends State {

    private Texture background;
    private Preferences settings;
    public Timer timer;

    private boolean winCondition;

    private Entity obstacle;
    private Entity obstacle2;

    private Firetruck truck1;
    private Firetruck truck2;

    private Fortress minster;

    private Alien alien1;
    private ArrayList<Vector2> spawnCoords = new ArrayList<Vector2>();

    private int alienSpawnCountdown = 300;
    private int alienShootCountdown = 150;

    public ArrayList<Entity> obstacles = new ArrayList<Entity>();
    public ArrayList<Firetruck> trucks = new ArrayList<Firetruck>();
    public ArrayList<Firetruck> destroyedTrucks = new ArrayList<Firetruck>();
    public ArrayList<Alien> aliens = new ArrayList<Alien>();
    private ArrayList<Bullet> bullets = new ArrayList<Bullet>();
    private BitmapFont font;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("playbg.png");
        timer = new Timer();
        font = new BitmapFont();

        winCondition = false;
        obstacle = new Entity(new Vector2(500, 400),100, 100, new Texture("blue.jpg"));
        obstacle2 = new Entity(new Vector2(200, 400),100, 100, new Texture("green.jpg"));
        obstacles.add(obstacle);
        obstacles.add(obstacle2);

        settings = Gdx.app.getPreferences("My Preferences");

        truck1 = new Firetruck(new Vector2(50, 550), 90, 100, new Texture("truckthin.png"), 100, 2,
                 null, 10, 10, 10, 10,
                true);
        truck2 = new Firetruck(new Vector2(300, 550), 90, 100, new Texture("truckthin.png"), 100, 2,
                 null, 10, 10, 10, 10,
                false);

        trucks.add(truck1);
        trucks.add(truck2);

        Vector2[] vectors = new Vector2[]{new Vector2(100,100), new Vector2(100, 150)};

        alien1 = new Alien(new Vector2(100, 100), 100, 100, new Texture("alien.png"), 100, 200,
                 null, 1, 10, 10,
                vectors);
        aliens.add(alien1);

        minster = new Fortress(new Vector2(800, 200), 100, 300, new Texture("grey.png"), 2);
        spawnCoords.add(new Vector2(700, 100));
        spawnCoords.add(new Vector2(700, 200));
        spawnCoords.add(new Vector2(700, 300));
        spawnCoords.add(new Vector2(700, 400));
        spawnCoords.add(new Vector2(700, 500));
        spawnCoords.add(new Vector2(800, 500));
        spawnCoords.add(new Vector2(800, 100));

        minster = new Fortress(new Vector2(800, 200), 100, 300, new Texture("grey.png"),
                10);
    }

    @Override
    public void handleInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            gsm.push(new EndState(gsm));
        }

        if (Gdx.input.isKeyPressed(Input.Keys.E)) {
            endLevel();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.L)) {
            gsm.push(new MenuState(gsm));
        }
        Vector2 mousePos = new Vector2(Gdx.input.getX(), Test.HEIGHT - Gdx.input.getY());
        if (Gdx.input.isTouched()) {
            for (Firetruck truck : trucks) {
                if (mousePos.x >= (truck.getPosition().x) && mousePos.x <= (truck.getPosition().x + truck.getWidth())
                        && mousePos.y >= (truck.getPosition().y) && mousePos.y <= (truck.getPosition().y
                        + truck.getHeight())) {
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
    public void update(float dt) {
        timer.update();
        for (Alien alien : aliens) {
            alien.update();
            alien.truckInAttackRange(trucks);
            System.out.println(alien.getTimeSinceAttack());
            if (alien.getTimeSinceAttack() >= 5) {
                if (alien.hasTarget()) {
                    Bullet bullet = new Bullet(new Vector2(alien.getPosition().x + alien.getWidth() / 2, alien.getPosition().y + alien.getHeight() / 2), 5, 5,
                            new Texture("blue.jpg"), (new Vector2(alien.getTarget().getPosition().x + 45, alien.getTarget().getPosition().y + 50)), 15);
                    bullets.add(bullet);
                    alien.resetTimeSinceAttack();
                }
            }
            alienSpawnCountdown -= dt;
            alien.updateTimeSinceAttack(dt);

        }
        if (alienSpawnCountdown <= 0 ) {
            produceAlien();
            alienSpawnCountdown = 300;
        }
        handleInput();
        for (Bullet bullet : new ArrayList<Bullet>(bullets)) {
            bullet.update();
            for(Firetruck truck : new ArrayList<Firetruck>(trucks)) {
                if (bullet.hitTruck(truck)) {
                    truck.takeDamage(10);
                    bullets.remove(bullet);
                    if(truck.getCurrentHealth() == 0) {
                        trucks.remove(truck);
                        destroyedTrucks.add(truck);

                    }
                }
            }
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(background, 0, 0, Test.WIDTH, Test.HEIGHT);
        sb.draw(obstacle.getTexture(), obstacle.getPosition().x, obstacle.getPosition().y, obstacle.getWidth(),
                obstacle.getHeight());
        sb.draw(obstacle2.getTexture(), obstacle2.getPosition().x, obstacle2.getPosition().y, obstacle2.getWidth(),
                obstacle2.getHeight());
        sb.draw(truck1.getTexture(), truck1.getPosition().x, truck1.getPosition().y, truck1.getWidth(), truck1.getHeight());
        sb.draw(truck2.getTexture(), truck2.getPosition().x, truck2.getPosition().y, truck2.getWidth(), truck2.getHeight());
        sb.draw(alien1.getTexture(), alien1.getPosition().x, alien1.getPosition().y, alien1.getWidth(), alien1.getHeight());
        sb.draw(minster.getTexture(), minster.getPosition().x, minster.getPosition().y, minster.getWidth(), minster.getHeight());
        for (Alien alien : aliens){
            sb.draw(alien.getTexture(), alien.getPosition().x, alien.getPosition().y, alien.getWidth(), alien.getHeight());
        }
        for(Bullet bullet : bullets) {
            sb.draw(bullet.getTexture(), bullet.getPosition().x, bullet.getPosition().y, bullet.getWidth(), bullet.getHeight());
        }
        timer.drawTime(sb);
        font.draw(sb, "Truck 1 Health: " + Integer.toString(truck1.getCurrentHealth()), 200, 30);
        font.draw(sb, "Truck 2 Health: " + Integer.toString(truck2.getCurrentHealth()), 400, 30);
        sb.end();
    }
    //https://stackoverflow.com/questions/33283867/how-to-make-a-sprite-move-with-keyboard-in-javalibgdx?rq=1 source
    //used. Should really have a 'SPEED' constant instead of using the number 10 so that it can be changed easily.

    public void truckMovement(Firetruck truck) {
        if(Gdx.input.isKeyPressed(Input.Keys.W)) {
            boolean obstacleCollision = false;
            if(truck.getPosition().y == 550) {
                obstacleCollision = true;
            }
            for (Entity obstacle : obstacles) {
                if (truck.willCollide(obstacle, "up")) {
                    obstacleCollision = true;
                }
            }
            if (!obstacleCollision) {
                truck.move("up");
            }
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S)) {
            boolean obstacleCollision = false;
            if(truck.getPosition().y == 50) {
                obstacleCollision = true;
            }
            for (Entity obstacle : obstacles) {
                if (truck.willCollide(obstacle, "down")) {
                    obstacleCollision = true;
                }
            }
            if (!obstacleCollision) {
                truck.move("down");
            }
        }

        if(Gdx.input.isKeyPressed(Input.Keys.A)) {
            boolean obstacleCollision = false;
            if(truck.getPosition().x == 50) {
                obstacleCollision = true;
            }
            for (Entity obstacle : obstacles) {
                if (truck.willCollide(obstacle, "left")) {
                    obstacleCollision = true;
                }
            }
            if (!obstacleCollision) {
                truck.move("left");
            }
        }

        if(Gdx.input.isKeyPressed(Input.Keys.D)) {
            boolean obstacleCollision = false;
            if(truck.getPosition().x == 850) {
                obstacleCollision = true;
            }
            for (Entity obstacle : obstacles) {
                if (truck.willCollide(obstacle, "right")) {
                    obstacleCollision = true;
                }
            }
            if (!obstacleCollision) {
                truck.move("right");
            }
        }
    }


    public void endLevel(){
        timer.stop();
        for (Firetruck truck : trucks){
            truck.setSpeed(0);
            truck.setDps(0);
        }
        for (Alien alien : aliens){
            alien.setSpeed(0);
            alien.setDps(0);
        }
    }

    public void produceAlien() {
        Random rand = new Random();
        if (spawnCoords.size() > 0) {
            Vector2 coordinate = spawnCoords.get(rand.nextInt(spawnCoords.size()));
            Alien alien = new Alien(coordinate, 100, 100, new Texture("alien.png"), 100, 200,
                    null, 1, 10, 10, new Vector2[]{new Vector2(coordinate.x, coordinate.y),
                    new Vector2(coordinate.x + 10, coordinate.y)});
            aliens.add(alien);
            spawnCoords.remove(coordinate);
        }
        }

    @Override
    public void dispose() {

    }
}