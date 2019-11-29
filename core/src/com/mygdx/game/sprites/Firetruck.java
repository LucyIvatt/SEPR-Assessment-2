package com.mygdx.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Firetruck extends Character {

    // Instance variables
    private int max_water;
    private int current_water;
    private boolean selected;
   // private int BORDER; //move to more suitable place


    // Getters and Setters
    public int getMax_water() {
        return max_water;
    }

    public int getCurrent_water() {
        return current_water;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setCurrent_water(int current_water) {
        this.current_water = current_water;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    // Constructor
    public Firetruck(Vector2 position, int width, int height, int maxHealth, int range, Texture texture, Unit target, int speed, int dps, int bearing, int max_water, int current_water, boolean selected, int BORDER) {
        super(position, width, height, maxHealth, range, texture, target, speed, dps, bearing);
        this.max_water = max_water;
        this.current_water = current_water;
        this.selected = false;
       // this.BORDER = BORDER;
    }

    // User attacks alien/fortress and firetruck water level decreases
    public void firetruckAttack(Unit target, int current_water){
        if (this.current_water == 0){
            //show message telling them they have ran out of water and to go refill to continue
        } else {
            setCurrent_water(current_water - 10);
            target.takeDamage(10);
        }
    }

    // Refill water level of firetruck
    public void refillWater(){
        //Call Mini-game
    }

    // Set which firetruck is selected
    public void select(Firetruck firetruck){
        // when the firetruck is clicked, set the 'selected' attribute for that object true

    }

    public void move(String direction) {
        if (direction == "right") {
            setPosition(getPosition().x + getSpeed(), getPosition().y);
        }
        else if (direction == "left") {
            setPosition(getPosition().x - getSpeed(), getPosition().y);
        }
        else if (direction == "up") {
            setPosition(getPosition().x, getPosition().y + getSpeed());
        }
        else if (direction == "down") {
            setPosition(getPosition().x, getPosition().y - getSpeed());
        }
    }

    public boolean willCollide(Entity other, String direction) {
        if (direction == "up") {
            if (getPosition().y > other.getTopRight().y || getTopRight().y + getSpeed() < getPosition().y) {
                return false;
            } else if (getPosition().x > other.getTopRight().x || getTopRight().x < other.getPosition().x) {
                return false;
            }
        }

        else if (direction == "down") {
            if (getPosition().y - getSpeed() > other.getTopRight().y || getTopRight().y < other.getPosition().y) {
                return false;
            }
            else if (getPosition().x > other.getTopRight().x || getTopRight().x < other.getPosition().x) {
                return false;
            }
        }

        else if (direction == "right") {
            if (getTopRight().x + getSpeed() < other.getPosition().x || getPosition().x > other.getTopRight().x) {
                    return false;
                }
            else if (getPosition().y > other.getTopRight().y || getTopRight().y < other.getPosition().y) {
                return false;
            }
        }
        else if (direction == "left") {
            if (getPosition().x - getSpeed() > other.getTopRight().x || getTopRight().x < other.getPosition().x) {
                return false;
            }
            else if (getPosition().y > other.getTopRight().y || getTopRight().y < other.getPosition().y) {
                return false;
            }
        }
        return true;
        }














//    public void moveTruck(){
//        //move up by 10 unless the firetruck is at the top of the border already
//
//        if(Gdx.input.isKeyPressed(Input.Keys.UP)){
//            if(this.getPosition().y == 550){
//                this.setPosition(this.getPosition().x, 550);
//            }
//            else {
//                this.move(0, 10);
//            }
//        }
//
//        //move down by 10 unless the firetruck is at the bottom of the border already
//
//        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
//             if(this.getPosition().y == 50) {
//                this.setPosition(this.getPosition().x, 50);
//            }
//            else {
//                this.move(0, -10);
//            }
//        }
//
//        //move left 10 unless the firetruck is at the left of the border already
//
//        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
//            if(this.getPosition().x == 50) {
//                this.setPosition(50, this.getPosition().y);
//            }
//            else {
//                this.move(-10, 0);
//            }
//        }
//
//        //move right 10 unless the firetruck is at the right of the border already
//
//        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
//            if(this.getPosition().x == 850) {
//                this.setPosition(850, this.getPosition().y);
//            }
//            else {
//                this.move(10, 0);
//            }
//        }
//    }

}
