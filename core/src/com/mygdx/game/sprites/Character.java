package com.mygdx.game.sprites;

public abstract class Character extends Unit {
    private Unit target;
    private int speed;
    private int dps;
    private int bearing;

    // Constructor
    public Character(Unit target, int speed, int dps, int bearing){
        this.target = target;
        this.speed = speed;
        this.dps = dps;
        this.bearing = bearing;
    }

    // Getters and Setters
    public Unit getTarget() {
        return target;
    }

    public int getSpeed() {
        return speed;
    }

    public int getDps() {
        return dps;
    }

    public int getBearing() {
        return bearing;
    }

    public void setTarget(Unit target) {
        this.target = target;
    }

    public void setBearing(int bearing) {
        this.bearing = bearing;
    }


}
