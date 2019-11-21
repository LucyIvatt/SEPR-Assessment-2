package com.mygdx.game.sprites;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class Entity {

    private Texture texture;
    private Vector3 position;
    private int width;
    private int height;

    public void setPosition(float x, float y) {
        this.position = new Vector3(x, y, 0);
    }

    public Vector3 getPosition() {
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

    public Entity(int x, int y, int width, int height, Texture texture) {
        this.position = new Vector3(x, y, 0);
        this.width = width;
        this.height = height;
        this.texture = texture;
    }
}
