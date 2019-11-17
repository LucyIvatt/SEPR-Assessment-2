package com.mygdx.game.sprites;

public class Firetruck extends Character {

    // Instance variables
    private int max_water;
    private int current_water;
    private boolean selected;

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

    public Firetruck(int max_water, boolean selected) {
        this.max_water = max_water;
        this.current_water = max_water;
        this.selected = selected;
    }
}
