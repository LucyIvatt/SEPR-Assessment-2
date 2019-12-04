import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.sprites.Unit;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.mygdx.game.sprites.Character;

//Instance of the Unit class to test on (is abstract)
class characterTestClass extends Character {

    public characterTestClass(Vector2 position, int width, int height,  Texture texture, int maxHealth, int range,
                              Unit target, int speed, int dps) {
        super(position, width, height, texture, maxHealth, range, target, speed, dps);
    }
}

public class testCharacter {
//    Character testCharacter = new characterTestClass();



}
