package com.mygdx.game.sprites;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class Pipe extends Entity{

    private int[] exits;
    private int rotation;
    private int[] correctRotations;

    public Pipe(int x, int y, int width, int height, Texture texture, int[] exits, int rotation, int[] correctRotations){
        super(x, y, width, height, texture);
        setExits(exits);
        setCorrectRotations(correctRotations);
        setRotation(rotation);
    }
    public int[] getCorrectRotations() {
        return correctRotations;
    }

    public void setCorrectRotations(int[] correctRotations) {
        this.correctRotations = correctRotations;
    }

    public int[] getExits() {
        return exits;
    }

    public void setExits(int[] exits) {
        this.exits = exits;
    }

    public int getRotation() {
        return rotation;
    }

    public void setRotation(int rot){
        this.rotation = rot;
    }

    public void rotateOnClick(){
        int newRot = (this.rotation + 1) % 4;
        setRotation(newRot);
    }
    public boolean isCorrect(){
        boolean flag = false;
        for (int rot: correctRotations){
            if (rot == this.rotation){
                flag = true;
            }
        }
        return flag;
    }

}
