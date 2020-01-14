package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Projectile extends Entity
{
    private Vector2 STARTPOSITION;
    private float speed;
    private Vector2 direction;
    private float length;

    public Projectile(Vector2 position, int width, int height, Texture texture, Vector2 targetCoords, float speed) {
        super(position, width, height, texture);
        STARTPOSITION = position;
        this.speed = speed;
        this.direction = new Vector2(targetCoords.x - position.x,targetCoords.y - position.y).nor();
    }

    public void update() {
       setPosition(getPosition().x + speed * direction.x, getPosition().y + speed * direction.y);
       setLength();
    }
    public boolean hitUnit(Unit instance) {
        if (getTopRight().y < instance.getPosition().y || getPosition().y > instance.getTopRight().y ||
                getTopRight().x < instance.getPosition().x || getPosition().x > instance.getTopRight().x) {
            return false;
        } else {
            return true;
        }
    }

    public void setLength() {
        this.length = getPosition().dst(STARTPOSITION);
    }

    public float getLength() {
        return length;
    }

    public void dispose() {

    }
}
