package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Bullet extends Entity
{
    private Vector2 targetCoords;
    private float speed;
    private Vector2 direction;

    public Bullet(Vector2 position, int width, int height, Texture texture, Vector2 targetCoords, float speed) {
        super(position, width, height, texture);
        this.targetCoords = targetCoords;
        this.speed = speed;
        this.direction = new Vector2(targetCoords.x - position.x,targetCoords.y - position.y).nor();
    }

    public void update() {
       setPosition(getPosition().x + speed * direction.x, getPosition().y + speed * direction.y);
    }
    public boolean hitTruck(Firetruck firetruck) {
        if (getTopRight().y < firetruck.getPosition().y || getPosition().y > firetruck.getTopRight().y ||
                getTopRight().x < firetruck.getPosition().x || getPosition().x > firetruck.getTopRight().x) {
            return false;
        } else {
            return true;
        }
    }

    public void dispose() {

    }
}
//    Bullet bottom left: (295.36026,208.78906)
//        Bullet top right: (105.0,106.0)
//        truck bottom left: (290.0,180.0)
//        truck top right: (380.0,280.0)
//        false