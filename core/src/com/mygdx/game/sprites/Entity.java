package com.mygdx.game.sprites;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

/**
 * The class containing the 5 primary attributes and their relevant getters and/or setters which are inherited by
 * multiple classes and is itself implemented to create obstacle object.
 *
 * @author Bethany Gilmore
 */

public class Entity {

    private Texture texture;
    private Vector2 position;
    private int width;
    private int height;
    private Vector2 topRight;

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
//        if (x < 0 || y < 0 ){
//            throw new IllegalArgumentException("X and Y position cannot be negative");
//        }
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
