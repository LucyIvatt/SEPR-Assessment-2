package com.mygdx.game.sprites;

public abstract class Character extends Unit {
    private Unit target;
    private int speed;
    private int dps;
    private int bearing;

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
