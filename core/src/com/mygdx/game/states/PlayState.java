package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.misc.Timer;
import com.mygdx.game.Kroy;
import com.mygdx.game.sprites.*;

import java.util.ArrayList;
import java.util.Random;

public class PlayState extends State {

    private final float gameWidth = 1856;
    private final float gameHeight = 832;

    private Texture background;
    private Preferences settings;
    private Timer timer;

    private boolean winCondition;

    private Entity obstacle;
    private Entity obstacle2;

    private Firetruck truck1;
    private Firetruck truck2;

    private Fortress minster;

    private Alien alien1;
    private ArrayList<Vector2> spawnCoordinates = new ArrayList<Vector2>();

    private int alienSpawnCountdown = 100;

    public ArrayList<Entity> obstacles = new ArrayList<Entity>();
    public ArrayList<Firetruck> trucks = new ArrayList<Firetruck>();
    public ArrayList<Firetruck> destroyedTrucks = new ArrayList<Firetruck>();
    public ArrayList<Alien> aliens = new ArrayList<Alien>();
    private ArrayList<Bullet> bullets = new ArrayList<Bullet>();
    private BitmapFont font;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("LevelProportions.png");
        settings = Gdx.app.getPreferences("My Preferences");
        timer = new Timer();
        font = new BitmapFont(Gdx.files.internal("font.fnt"));
        winCondition = false;

        // Level Obstacles

        // Firetrucks
        truck1 = new Firetruck(new Vector2(50, 550), 64, 64, new Texture("truckthin.png"), 100, 2,
                 null, 200, 10, 10, 10,
                true);
        truck2 = new Firetruck(new Vector2(300, 550), 64, 64, new Texture("truckthin.png"), 100, 2,
                 null, 200, 10, 10, 10,
                false);
        trucks.add(truck1);
        trucks.add(truck2);

        // Aliens
        spawnCoordinates.add(new Vector2(1696 - 64 * 5, 212 + (gameHeight / 2) - 64 / 2));
        spawnCoordinates.add(new Vector2(1696 - 64 * 5 + 64 + 32, 212 + (gameHeight / 2) + 64));
        spawnCoordinates.add(new Vector2(1696 - 64 * 5 + 64 + 32, 212 + (gameHeight / 2) + 160));
        spawnCoordinates.add(new Vector2(1696 - 64 * 5 + 64 + 32, 212 + (gameHeight / 2) - 128 ));
        spawnCoordinates.add(new Vector2(1696 - 64 * 5 + 64 + 32, 212 + (gameHeight / 2) - 224));
        spawnCoordinates.add(new Vector2(1696 - 64 * 5 + 64 + 32 + 64 + 32, 212 + (gameHeight / 2) - 320));
        spawnCoordinates.add(new Vector2(1696 - 64 * 5 + 64 + 32 + 64 + 32, 212 + (gameHeight / 2) +  256));

        // Fortress
        minster = new Fortress(new Vector2(1696, 212 + (gameHeight / 2) - 300 / 2), 100, 300, new Texture("grey.png"),
                10);
        font.getData().setScale(1);
        font.setColor(Color.DARK_GRAY);
    }

    public void handleInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            gameStateManager.push(new EndState(gameStateManager));
        }

        if (Gdx.input.isKeyPressed(Input.Keys.E)) {
            endLevel();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.L)) {
            gameStateManager.push(new MenuState(gameStateManager));
        }
        Vector2 mousePos = new Vector2(Gdx.input.getX(), Kroy.HEIGHT - Gdx.input.getY());
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
                            new Texture("blue.jpg"), (new Vector2(alien.getTarget().getPosition().x + 45, alien.getTarget().getPosition().y + 50)), 5);
                    bullets.add(bullet);
                    alien.resetTimeSinceAttack();
                }
            }
            alien.updateTimeSinceAttack(dt);
        }
        alienSpawnCountdown -= dt;

        if (alienSpawnCountdown <= 0 ) {
            produceAlien();
            alienSpawnCountdown = 100;
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

        if (trucks.size() == 0) {
            gameStateManager.push(new EndState(gameStateManager));
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(background, 0, 0, Kroy.WIDTH, Kroy.HEIGHT);
        sb.draw(truck1.getTexture(), truck1.getPosition().x, truck1.getPosition().y, truck1.getWidth(),
                truck1.getHeight());
        sb.draw(truck2.getTexture(), truck2.getPosition().x, truck2.getPosition().y, truck2.getWidth(),
                truck2.getHeight());
        sb.draw(minster.getTexture(), minster.getPosition().x, minster.getPosition().y, minster.getWidth(),
                minster.getHeight());

        for (Alien alien : aliens){
            sb.draw(alien.getTexture(), alien.getPosition().x, alien.getPosition().y, alien.getWidth(),
                    alien.getHeight());
        }
        for(Bullet bullet : bullets) {
            sb.draw(bullet.getTexture(), bullet.getPosition().x, bullet.getPosition().y, bullet.getWidth(),
                    bullet.getHeight());
        }
        for(Entity obstacle : obstacles) {
            sb.draw(obstacle.getTexture(), obstacle.getPosition().x, obstacle.getPosition().y, obstacle.getWidth(),
                    obstacle.getHeight());
        }

        timer.drawTime(sb, font);
        font.draw(sb, "Truck 1 Health: " + Integer.toString(truck1.getCurrentHealth()), 70,
                Kroy.HEIGHT - 920);
        font.draw(sb, "Truck 2 Health: " + Integer.toString(truck2.getCurrentHealth()), 546,
                Kroy.HEIGHT - 920);
        font.draw(sb, "Truck 3 Health: N/A", 1023, Kroy.HEIGHT - 920);
        font.draw(sb, "Truck 4 Health: N/A", 1499, Kroy.HEIGHT - 920);

        sb.end();
    }

    public void truckMovement(Firetruck truck) {
        if(Gdx.input.isKeyPressed(Input.Keys.W)) {
            boolean obstacleCollision = false;
            if(truck.getPosition().y >= 1043 - truck.getHeight()) {
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
            if(truck.getPosition().y <= 212) {
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
            if(truck.getPosition().x <= 33) {
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
            if(truck.getPosition().x >= 1888 - truck.getWidth()) {
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
        if (spawnCoordinates.size() > 0) {
            Vector2 coordinate = spawnCoordinates.get(rand.nextInt(spawnCoordinates.size()));
            Alien alien = new Alien(coordinate, 64, 64, new Texture("alien.png"), 100, 200,
                    null, 1, 10, 10, new Vector2[]{new Vector2(coordinate.x, coordinate.y),
                    new Vector2(coordinate.x + 10, coordinate.y)});
            aliens.add(alien);
            spawnCoordinates.remove(coordinate);
        }
        }

    @Override
    public void dispose() {

    }
}