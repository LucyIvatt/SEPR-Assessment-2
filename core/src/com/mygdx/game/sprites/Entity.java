package com.mygdx.game.sprites;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class Entity {

    private Texture texture;
    private Vector2 position;
    private int width;
    private int height;
    private Vector2 topRight; // Not needed as an argument for the constructor as it is calculated using position,
    // width and height

    public Entity(Vector2 position, int width, int height, Texture texture) {
        this.position = position;
        this.width = width;
        this.height = height;
        this.texture = texture;
        this.topRight = new Vector2(position.x + width, position.y + height);
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
