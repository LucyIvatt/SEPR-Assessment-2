package com.mygdx.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class Firetruck extends Character {

    // Instance variables
    private int max_water;
    private int current_water;
    private boolean selected;
    private int BORDER; //move to more suitable place

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

    // constructor
    public Firetruck(int max_water, boolean selected) {
        this.max_water = max_water;
        this.current_water = max_water;
        this.selected = selected;
    }

    public void refillWater(){
        //need to call the mini-game
    }

    public void select(){
        // make the selected attribute true for the selected firetruck and make the rest false
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
