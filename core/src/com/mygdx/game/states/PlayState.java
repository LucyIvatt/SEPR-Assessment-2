package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.misc.Button;
import com.mygdx.game.misc.Timer;
import com.mygdx.game.Kroy;
import com.mygdx.game.sprites.*;

import java.util.ArrayList;
import java.util.Random;

/**
 * Implementation of the abstract class State which contains the methods and attributes required to control the
 * gameplay logic for each level as well as rendering to the screen.
 *
 * @author Lucy Ivatt
 */

public class PlayState extends State {

    private final float GAME_WIDTH = 1856;
    private final float GAME_HEIGHT = 832;

    private Texture background;
    private Texture map;

    private boolean levelLost;
    private boolean levelWon;
    private Preferences saveData;

    private Button quitLevel;
    private Button quitGame;

    private Timer timer;
    private float alienSpawnCountdown;
    private float timeSinceAlienKilled;
    private float timeLimit;

    private Entity fireStation;
    private Fortress fortress;
    private Firetruck firetruck1;
    private Firetruck firetruck2;
    private ArrayList<Entity> obstacles = new ArrayList<Entity>();
    public ArrayList<Firetruck> firetrucks = new ArrayList<Firetruck>();
    private ArrayList<Firetruck> destroyedFiretrucks = new ArrayList<Firetruck>();
    private ArrayList<Alien> aliens = new ArrayList<Alien>();
    private ArrayList<Projectile> bullets = new ArrayList<Projectile>();
    private ArrayList<Projectile> water = new ArrayList<Projectile>();

    private BitmapFont ui;
    private BitmapFont healthBars;
    private String level;
    private Sound waterShoot = Gdx.audio.newSound(Gdx.files.internal("honk.wav"));

    public PlayState(GameStateManager gsm, int level) {
        super(gsm);
        background = new Texture("LevelProportions.png");
        map = new Texture("level1background.png");
        quitLevel = new Button(new Texture("PressedQuitLevel.png"), new Texture("NotPressedQuitLevel.png"),
                350 / 2, 100 / 2, new Vector2(30, 30), false, false);
        quitGame = new Button(new Texture("PressedQuitGame.png"), new Texture("NotPressedQuitGame.png"),
                350 / 2, 100 / 2, new Vector2(1920 - 30 - 350 / 2, 30), false, false);
        this.level = Integer.toString(level);
        levelLost = false;
        levelWon = false;
        saveData = Gdx.app.getPreferences("My Preferences");
        timer = new Timer();
        ui = new BitmapFont(Gdx.files.internal("font.fnt"));
        healthBars = new BitmapFont();
        //waterShoot
        ArrayList<Vector2> spawnCoordinates = new ArrayList<Vector2>();

        if (level == 1) { // Bottom left coord --> (33, 212)
            timeLimit = 120;

            // Level 1 Obstacles
            obstacles.add(new Entity(new Vector2(257, 628), 64, 64, new Texture("teal.jpg")));
            obstacles.add(new Entity(new Vector2(257, 724), 64, 32, new Texture("teal.jpg")));
            obstacles.add(new Entity(new Vector2(289, 756), 32, 32, new Texture("teal.jpg")));
            obstacles.add(new Entity(new Vector2(257, 820), 64, 32, new Texture("teal.jpg")));
            obstacles.add(new Entity(new Vector2(257, 564), 64, 32, new Texture("teal.jpg")));
            obstacles.add(new Entity(new Vector2(289, 532), 32, 32, new Texture("teal.jpg")));

            obstacles.add(new Entity(new Vector2(513, 532), 32, 32, new Texture("teal.jpg")));
            obstacles.add(new Entity(new Vector2(513, 564), 64, 32, new Texture("teal.jpg")));

            obstacles.add(new Entity(new Vector2(577, 692), 32, 32, new Texture("teal.jpg")));
            obstacles.add(new Entity(new Vector2(577, 724), 64, 32, new Texture("teal.jpg")));
            obstacles.add(new Entity(new Vector2(577, 436), 64, 32, new Texture("teal.jpg")));
            obstacles.add(new Entity(new Vector2(609, 468), 64, 32, new Texture("teal.jpg")));

            obstacles.add(new Entity(new Vector2(737, 404), 64, 32, new Texture("teal.jpg")));
            obstacles.add(new Entity(new Vector2(737, 692), 64, 64, new Texture("teal.jpg")));
            obstacles.add(new Entity(new Vector2(833, 404), 64, 32, new Texture("teal.jpg")));
            obstacles.add(new Entity(new Vector2(929, 404), 64, 32, new Texture("teal.jpg")));
            obstacles.add(new Entity(new Vector2(1025, 404), 64, 32, new Texture("teal.jpg")));
            obstacles.add(new Entity(new Vector2(1057, 436), 32, 32, new Texture("teal.jpg")));

            obstacles.add(new Entity(new Vector2(1121, 404), 64, 64, new Texture("teal.jpg")));
            obstacles.add(new Entity(new Vector2(1121, 500), 32, 32, new Texture("teal.jpg")));
            obstacles.add(new Entity(new Vector2(1121, 532), 64, 32, new Texture("teal.jpg")));

            obstacles.add(new Entity(new Vector2(1217, 404), 64, 64, new Texture("teal.jpg")));
            obstacles.add(new Entity(new Vector2(1217, 532), 64, 32, new Texture("teal.jpg")));

            obstacles.add(new Entity(new Vector2(1345, 436), 32, 96, new Texture("teal.jpg")));
            obstacles.add(new Entity(new Vector2(1377, 468), 32, 96, new Texture("teal.jpg")));

            obstacles.add(new Entity(new Vector2(961, 692), 64, 32, new Texture("teal.jpg")));
            obstacles.add(new Entity(new Vector2(1249, 692), 64, 32, new Texture("teal.jpg")));
            obstacles.add(new Entity(new Vector2(1249, 628), 64, 32, new Texture("teal.jpg")));
            obstacles.add(new Entity(new Vector2(1345, 692), 64, 32, new Texture("teal.jpg")));
            obstacles.add(new Entity(new Vector2(1345, 628), 64, 32, new Texture("teal.jpg")));

            fireStation = new Entity(new Vector2(33, 212), 128, 128, new Texture("teal.jpg"));

            // Level 1 Alien Spawn Coordinates
            spawnCoordinates.add(new Vector2(1696 - 64 * 5, 212 + (GAME_HEIGHT / 2) - 64 / 2));
            spawnCoordinates.add(new Vector2(1696 - 64 * 5 + 64 + 32, 212 + (GAME_HEIGHT / 2) + 64));
            spawnCoordinates.add(new Vector2(1696 - 64 * 5 + 64 + 32, 212 + (GAME_HEIGHT / 2) + 160));
            spawnCoordinates.add(new Vector2(1696 - 64 * 5 + 64 + 32, 212 + (GAME_HEIGHT / 2) - 128));
            spawnCoordinates.add(new Vector2(1696 - 64 * 5 + 64 + 32, 212 + (GAME_HEIGHT / 2) - 224));
            spawnCoordinates.add(new Vector2(1696 - 64 * 5 + 64 + 32 + 64 + 32, 212 + (GAME_HEIGHT / 2) - 320));
            spawnCoordinates.add(new Vector2(1696 - 64 * 5 + 64 + 32 + 64 + 32, 212 + (GAME_HEIGHT / 2) + 256));

            // Level 1 Fortress
            fortress = new Fortress(new Vector2(1696, 212 + (GAME_HEIGHT / 2) - 300 / 2), 100, 300, new Texture("grey.png"),
                    1000, spawnCoordinates, 2);
        }

        else if (level == 2) {

            timeLimit = 120;

            map = new Texture("level2background.png");
            // Level 2 Obstacles
            fireStation = new Entity(new Vector2(33, 212), 128, 128, new Texture("teal.jpg"));

            // Level 2 Alien Spawn Coordinates
            spawnCoordinates.add(new Vector2(1696 - 64 * 5, 212 + (GAME_HEIGHT / 2) - 64 / 2));
            spawnCoordinates.add(new Vector2(1696 - 64 * 5 + 64 + 32, 212 + (GAME_HEIGHT / 2) + 64));
            spawnCoordinates.add(new Vector2(1696 - 64 * 5 + 64 + 32, 212 + (GAME_HEIGHT / 2) + 160));
            spawnCoordinates.add(new Vector2(1696 - 64 * 5 + 64 + 32, 212 + (GAME_HEIGHT / 2) - 128));
            spawnCoordinates.add(new Vector2(1696 - 64 * 5 + 64 + 32, 212 + (GAME_HEIGHT / 2) - 224));
            spawnCoordinates.add(new Vector2(1696 - 64 * 5 + 64 + 32 + 64 + 32, 212 + (GAME_HEIGHT / 2) - 320));
            spawnCoordinates.add(new Vector2(1696 - 64 * 5 + 64 + 32 + 64 + 32, 212 + (GAME_HEIGHT / 2) + 256));

            // Level 2 Fortress
            fortress = new Fortress(new Vector2(1696, 212 + (GAME_HEIGHT / 2) - 300 / 2), 100, 300, new Texture("grey.png"),
                    1000, spawnCoordinates, 2);
        }
        else if (level == 3) {

            timeLimit = 120;

            map = new Texture("level3background.png");
            // Level 2 Obstacles
            fireStation = new Entity(new Vector2(33, 212), 128, 128, new Texture("teal.jpg"));

            // Level 2 Alien Spawn Coordinates
            spawnCoordinates.add(new Vector2(1696 - 64 * 5, 212 + (GAME_HEIGHT / 2) - 64 / 2));
            spawnCoordinates.add(new Vector2(1696 - 64 * 5 + 64 + 32, 212 + (GAME_HEIGHT / 2) + 64));
            spawnCoordinates.add(new Vector2(1696 - 64 * 5 + 64 + 32, 212 + (GAME_HEIGHT / 2) + 160));
            spawnCoordinates.add(new Vector2(1696 - 64 * 5 + 64 + 32, 212 + (GAME_HEIGHT / 2) - 128));
            spawnCoordinates.add(new Vector2(1696 - 64 * 5 + 64 + 32, 212 + (GAME_HEIGHT / 2) - 224));
            spawnCoordinates.add(new Vector2(1696 - 64 * 5 + 64 + 32 + 64 + 32, 212 + (GAME_HEIGHT / 2) - 320));
            spawnCoordinates.add(new Vector2(1696 - 64 * 5 + 64 + 32 + 64 + 32, 212 + (GAME_HEIGHT / 2) + 256));

            // Level 2 Fortress
            fortress = new Fortress(new Vector2(1696, 212 + (GAME_HEIGHT / 2) - 300 / 2), 100, 300, new Texture("grey.png"),
                    1000, spawnCoordinates, 2);
        }


        alienSpawnCountdown = fortress.getSpawnRate();
        ui.setColor(Color.DARK_GRAY);
        timeSinceAlienKilled = -1;

        firetruck1 = new Firetruck(new Vector2(50, 550), 25, 25, new Texture("truck.png"), 50, 200,
                null, 250, 10, 10, 150,
                true, 5);
        firetruck2 = new Firetruck(new Vector2(300, 550), 25, 25, new Texture("truck.png"), 100, 200,
                null, 250, 10, 10, 100,
                false, 5);
        firetrucks.add(firetruck1);
        firetrucks.add(firetruck2);
    }

    /**
     * The game logic which is executed due to specific user inputs. Is called in the update method.
     */
    public void handleInput() {

        if (quitGame.mouseInRegion()){
            quitGame.setActive(true);
            if (Gdx.input.isTouched()) {
                Gdx.app.exit();
                System.exit(0);
                }
            }

        else {
            quitGame.setActive(false);
        }

        if (quitLevel.mouseInRegion()){
            quitLevel.setActive(true);
            if (Gdx.input.isTouched()) {
                gameStateManager.pop();
            }
        }

        else {
            quitLevel.setActive(false);
        }

        // Handles input for firetruck attacks
        for (Firetruck firetruck : firetrucks) {
            if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && firetruck.isSelected() && firetruck.getCurrentWater() > 0) {
                Projectile drop = new Projectile(new Vector2(firetruck.getPosition().x + firetruck.getWidth() / 2, firetruck.getPosition().y + firetruck.getHeight() / 2), 5, 5,
                        new Texture("lightblue.jpg"), (new Vector2(Gdx.input.getX(), Kroy.HEIGHT - Gdx.input.getY())), 5, firetruck.getDamage(), firetruck.getRange());
                water.add(drop);
                waterShoot.play();
                firetruck.updateCurrentWater(1);
                firetruck.resetTimeSinceAttack();
            }
        }

        // Opens pause menu
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            gameStateManager.push(new OptionState(gameStateManager));
        }

        // Switches active firetruck
        Vector2 mousePos = new Vector2(Gdx.input.getX(), Kroy.HEIGHT - Gdx.input.getY());
        if (Gdx.input.isTouched()) {
            for (Firetruck truck : firetrucks) {
                if (mousePos.x >= (truck.getPosition().x) && mousePos.x <= (truck.getPosition().x + truck.getWidth())
                        && mousePos.y >= (truck.getPosition().y) && mousePos.y <= (truck.getPosition().y
                        + truck.getHeight())) {
                    for (Firetruck clearTruck : firetrucks) {
                        clearTruck.setSelected(false);
                    }
                    truck.setSelected(true);

                }
            }
        }

        // Handles Truck Movement
        if (firetruck1.isSelected()) {
            truckMovement(firetruck1);
        } else if (firetruck2.isSelected()) {
            truckMovement(firetruck2);
        }


        if ((levelLost || levelWon) && Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
            gameStateManager.set(new LevelSelectState(gameStateManager));
        }
    }

    /**
     * Updates the game logic before the next render() is called
     * @param deltaTime the amount of time which has passed since the last render() call
     */
    @Override
    public void update(float deltaTime) {

        // Calls input handler and updates timer each tick
        handleInput();
        timer.update();

        // Updates aliens and handles automatic attacks
        for (Alien alien : aliens) {
            alien.update();
            alien.truckInRange(firetrucks);
            if (alien.getTimeSinceAttack() >= alien.getAttackCooldown()) {
                if (alien.hasTarget()) {
                    Projectile bullet = new Projectile(new Vector2(alien.getPosition().x + alien.getWidth() / 2, alien.getPosition().y + alien.getHeight() / 2), 5, 5,
                            new Texture("red.png"), (new Vector2(alien.getTarget().getPosition().x, alien.getTarget().getPosition().y)), 5, alien.getDamage());
                    bullets.add(bullet);
                    alien.resetTimeSinceAttack();
                }
            }
            alien.updateTimeSinceAttack(deltaTime);
        }
        alienSpawnCountdown -= deltaTime;
        timeSinceAlienKilled -= deltaTime;


        // Respawns aliens
        if (alienSpawnCountdown <= 0 && timeSinceAlienKilled <= 0) {
            spawnAlien();
            alienSpawnCountdown = fortress.getSpawnRate();
            timeSinceAlienKilled = 0;
        }

        // Handles alien projectile movement and collision
        for (Projectile bullet : new ArrayList<Projectile>(bullets)) {
            bullet.update();
            for (Firetruck truck : new ArrayList<Firetruck>(firetrucks)) {
                if (bullet.hitUnit(truck)) {
                    truck.takeDamage(bullet.getDamage());
                    bullets.remove(bullet);
                    if (truck.getCurrentHealth() == 0) {
                        truck.setSelected(false);
                        firetrucks.remove(truck);
                        destroyedFiretrucks.add(truck);
                    }
                }
            }
        }

        // Refills tank if truck overlaps the  refill location.
        for (Firetruck truck : firetrucks) {
            if (!(truck.getTopRight().y < fireStation.getPosition().y || truck.getPosition().y > fireStation.getTopRight().y ||
                    truck.getTopRight().x < fireStation.getPosition().x || truck.getPosition().x > fireStation.getTopRight().x)) {
                // Would call minigame here
                truck.setCurrentWater(truck.getMaxWater());
            }
        }

        // Handles movement and collision for firetruck projectiles
        for (Projectile drop : new ArrayList<Projectile>(water)) {
            drop.update();
            if (drop.getLength() > drop.getMaxLength()) {
                water.remove(drop);
            }
            for (Alien alien : new ArrayList<Alien>(aliens)) {
                if (drop.hitUnit(alien)) {
                    alien.takeDamage(drop.getDamage());
                    water.remove(drop);
                    if (alien.getCurrentHealth() == 0) {
                        fortress.getAlienPositions().add(alien.getPosition());
                        aliens.remove(alien);
                        timeSinceAlienKilled = fortress.getSpawnRate();
                    }
                }
            }
            if (drop.hitUnit(fortress)) {
                fortress.takeDamage(2);
                if (fortress.getCurrentHealth() == 0) {
                    levelWon = true;
                    saveData.putBoolean(level, true);
                    saveData.flush();
                }
            }
        }

        // Handles game end states
        if (firetrucks.size() == 0 || timer.getTime() > timeLimit) {
            levelLost = true;
        }

        fortress.addHealth(1);
    }

    /**
     * Used to draw elements onto the screen.
     * @param spriteBatch a container for all elements which need rendering to the screen.
     */
    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.begin();
        spriteBatch.draw(background, 0, 0, Kroy.WIDTH, Kroy.HEIGHT);
        spriteBatch.draw(map, 33, 212, 1856, 832);
        spriteBatch.draw(quitLevel.getTexture(), quitLevel.getPosition().x, quitLevel.getPosition().y,
                quitLevel.getWidth(), quitLevel.getHeight());
        spriteBatch.draw(quitGame.getTexture(), quitGame.getPosition().x, quitGame.getPosition().y,
                quitGame.getWidth(), quitGame.getHeight());

        spriteBatch.draw(fireStation.getTexture(), fireStation.getPosition().x, fireStation.getPosition().y, fireStation.getWidth(),
                fireStation.getHeight());

        for (Firetruck truck : firetrucks) {
            spriteBatch.draw(truck.getTexture(), truck.getPosition().x, truck.getPosition().y, truck.getWidth(),
                    truck.getHeight());
            healthBars.draw(spriteBatch, "Water: " + truck.getCurrentWater(), truck.getPosition().x, truck.getPosition().y + truck.getHeight() + 10);
        }

        spriteBatch.draw(fortress.getTexture(), fortress.getPosition().x, fortress.getPosition().y, fortress.getWidth(),
                fortress.getHeight());
        healthBars.draw(spriteBatch, "HP: " + fortress.getCurrentHealth(), fortress.getPosition().x,
                fortress.getPosition().y + fortress.getHeight() + 10);

        for (Alien alien : aliens) {
            spriteBatch.draw(alien.getTexture(), alien.getPosition().x, alien.getPosition().y, alien.getWidth(),
                    alien.getHeight());
            healthBars.draw(spriteBatch, "HP: " + alien.getCurrentHealth(), alien.getPosition().x, alien.getPosition().y + alien.getHeight() + 10);

        }
        for (Projectile bullet : bullets) {
            spriteBatch.draw(bullet.getTexture(), bullet.getPosition().x, bullet.getPosition().y, bullet.getWidth(),
                    bullet.getHeight());
        }

        for (Projectile drop : water) {
            spriteBatch.draw(drop.getTexture(), drop.getPosition().x, drop.getPosition().y, drop.getWidth(),
                    drop.getHeight());
        }
        for (Entity obstacle : obstacles) {
            spriteBatch.draw(obstacle.getTexture(), obstacle.getPosition().x, obstacle.getPosition().y, obstacle.getWidth(),
                    obstacle.getHeight());
        }

        timer.drawTime(spriteBatch, ui);
        ui.setColor(Color.WHITE);
        if ((timeLimit - 15) < timer.getTime() && timer.getTime() < (timeLimit - 10)) {
            ui.draw(spriteBatch, "15 seconds remaining!", 150, 1000);
        }
        ui.setColor(Color.DARK_GRAY);
        ui.draw(spriteBatch, "Truck 1 Health: " + Integer.toString(firetruck1.getCurrentHealth()), 70,
                Kroy.HEIGHT - 920);
        ui.draw(spriteBatch, "Truck 2 Health: " + Integer.toString(firetruck2.getCurrentHealth()), 546,
                Kroy.HEIGHT - 920);
        ui.draw(spriteBatch, "Truck 3 Health: N/A", 1023, Kroy.HEIGHT - 920);
        ui.draw(spriteBatch, "Truck 4 Health: N/A", 1499, Kroy.HEIGHT - 920);

        if (levelLost) {
            spriteBatch.draw(new Texture("levelFail.png"), 480, 270);
        }

        if (levelWon) {
            spriteBatch.draw(new Texture("LevelWon.png"), 480, 270);
        }

        spriteBatch.end();
    }

    /**
     * Used to dispose of all textures, music etc. when no longer required to avoid memory leaks
     */
    @Override
    public void dispose() {

    }

    /**
     * Used to call the correct methods to move the trucks position depending on obstacles and which truck is
     * currently selected.
     * @param truck the truck which is currently selected
     */
    public void truckMovement(Firetruck truck) {
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            truck.setTexture(new Texture("truck.png"));
            boolean obstacleCollision = false;
            if (truck.getPosition().y >= 1043 - truck.getHeight()) {
                obstacleCollision = true;
            }
            for (Entity obstacle : obstacles) {
                if (truck.willCollide(obstacle, 3)) {
                    obstacleCollision = true;
                }
            }
            if (!obstacleCollision) {
                truck.move(3);
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            truck.setTexture(new Texture("truckdown.png"));
            boolean obstacleCollision = false;
            if (truck.getPosition().y <= 212) {
                obstacleCollision = true;
            }
            for (Entity obstacle : obstacles) {
                if (truck.willCollide(obstacle, 4)) {
                    obstacleCollision = true;
                }
            }
            if (!obstacleCollision) {
                truck.move(4);
            }
        }

        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            truck.setTexture(new Texture("truckleft.png"));
            boolean obstacleCollision = false;
            if (truck.getPosition().x <= 33) {
                obstacleCollision = true;
            }
            for (Entity obstacle : obstacles) {
                if (truck.willCollide(obstacle, 1)) {
                    obstacleCollision = true;
                }
            }
            if (!obstacleCollision) {
                truck.move(1);
            }
        }

        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            truck.setTexture(new Texture("truckright.png"));
            boolean obstacleCollision = false;
            if (truck.getPosition().x >= 1888 - truck.getWidth()) {
                obstacleCollision = true;
            }
            for (Entity obstacle : obstacles) {
                System.out.println("truck will collide" + truck.willCollide(obstacle, 2));
                if (truck.willCollide(obstacle, 2)) {
                    obstacleCollision = true;
                }
            }
            if (!obstacleCollision) {
                truck.move(2);
            }
        }
    }

    /**
     * Used to spawn the Aliens around the fortress by accessing the spawnRate and alienPositions stored within
     * the fortress object.
     */
    public void spawnAlien() {
        Random rand = new Random();
        if (fortress.getAlienPositions().size() > 0) {
            Vector2 coordinate = fortress.getAlienPositions().get(rand.nextInt(fortress.getAlienPositions().size()));
            Alien alien = new Alien(coordinate, 32, 32, new Texture("alien.gif"), 100,
                    250, null, 1, 10, new Vector2[]{new Vector2(coordinate.x, coordinate.y),
                    new Vector2(coordinate.x, coordinate.y + 30)}, 5);
            aliens.add(alien);
            fortress.getAlienPositions().remove(coordinate);
        }
    }
}

// Used to freeze the game when the level is completed while the notifier pops up but currently hangs the game.
//    public void freezeLevel() {
//        timer.stop();
//        for (Firetruck truck : firetrucks) {
//            truck.setSpeed(0);
//            truck.setDps(0);
//        }
//        for (Alien alien : aliens) {
//            alien.setSpeed(0);
//            alien.setDps(0);
//        }
//
//    }