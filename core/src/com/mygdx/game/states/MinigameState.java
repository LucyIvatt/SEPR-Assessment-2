package com.mygdx.game.states;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.Button;
import com.mygdx.game.Test;
import com.mygdx.game.sprites.Entity;
import com.mygdx.game.sprites.Pipe;
import com.mygdx.game.sprites.TestEntity;

public class MinigameState extends State{
    private Texture background;
    private Pipe[] pipes;
    private Button checkCorrect;
    private Vector3 touchPos;

    public MinigameState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("brickwall.png");
        Texture ob = new Texture("teal.jpg");
        checkCorrect = new Button(new Texture("blue.jpg"), new Texture("green.jpg"), 100, 20, new Vector2(800, 200));
        for (int i=0; i<3; i++){
            pipes[i] = new Pipe(20, (200*i)+100, 150, 150, ob, new int[]{1, 3}, 2, new int[]{1});
        }
        for (int i=0; i<3; i++){
            pipes[i] = new Pipe(200, (200*i)+100, 150, 150, ob, new int[]{1, 3}, 2, new int[]{1});
        }
        for (int i=0; i<3; i++){
            pipes[i] = new Pipe(380, (200*i)+100, 150, 150, ob, new int[]{1, 3}, 2, new int[]{1});
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()) {
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            int ordinal = 0;
            if ((touchPos.y > 100) && (touchPos.y < 250)) {
                if ((touchPos.x > 20) && (touchPos.x < 170)) {
                    pipes[0].rotateOnClick();
                } else if ((touchPos.x > 200) && (touchPos.x < 350)) {
                    pipes[1].rotateOnClick();
                } else if ((touchPos.x > 380) && (touchPos.x < 530)) {
                    pipes[2].rotateOnClick();
                }
            } else if ((touchPos.y > 300) && (touchPos.y < 450)) {
                if ((touchPos.x > 20) && (touchPos.x < 170)) {
                    pipes[3].rotateOnClick();
                } else if ((touchPos.x > 200) && (touchPos.x < 350)) {
                    pipes[4].rotateOnClick();
                } else if ((touchPos.x > 380) && (touchPos.x < 530)) {
                    pipes[5].rotateOnClick();
                }
            } else if ((touchPos.y > 500) && (touchPos.y < 650)) {
                if ((touchPos.x > 20) && (touchPos.x < 170)) {
                    pipes[6].rotateOnClick();
                } else if ((touchPos.x > 200) && (touchPos.x < 350)) {
                    pipes[7].rotateOnClick();
                } else if ((touchPos.x > 380) && (touchPos.x < 530)) {
                    pipes[8].rotateOnClick();
                }
            }
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(background, 0, 0, Test.WIDTH, Test.HEIGHT);
        for (Pipe pipe : pipes){
            sb.draw(pipe.getTexture(), pipe.getPosition().x, pipe.getPosition().y, pipe.getWidth(), pipe.getHeight());
        }
        sb.draw(checkCorrect.getTexture(), 800, 200, 100, 20);
        sb.end();
    }

    @Override
    public void dispose() {

    }
}
