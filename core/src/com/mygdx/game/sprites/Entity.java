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

    public void setPosition(int x, int y) {
        this.position = new Vector2(x, y);
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

    public Entity(int width, int height, Texture texture, Vector2 position) {
        this.position = position;
        this.width = width;
        this.height = height;
        this.texture = texture;
    }
}
