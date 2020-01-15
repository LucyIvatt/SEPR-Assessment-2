package com.mygdx.game.sprites;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

/**
 * The class containing the 5 primary attributes and their relevant getters and/or setters which are inherited by
 * multiple classes and is itself implemented to create obstacle object.
 *
 * @author Matthew Gilmore
 */

public class Entity {

    private Texture texture;
    private Vector2 position;
    private int width;
    private int height;
    private Vector2 topRight;

    /**
     * A constructor which includes the new attributes which will be super called by the Projectile and Unit classes.
     * Will also be used to initialize objects which are impassable by the Firetruck during gameplay.
     */
    public Entity(Vector2 position, int width, int height, Texture texture) {
        this.position = position;
        this.width = width;
        this.height = height;
        this.texture = texture;
        this.topRight = new Vector2(position.x + width, position.y + height);
    }

    public void dispose() {
        texture.dispose();
    }

    public void setPosition(float x, float y) {
        this.position = new Vector2(x, y);
        this.topRight = new Vector2(x + width, y + height);
    }

    public Vector2 getPosition() {
        return position;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public Vector2 getTopRight() {
        return topRight;
    }
}
