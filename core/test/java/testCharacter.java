import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.sprites.Unit;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.mygdx.game.sprites.Character;

//Instance of the Unit class to test on (is abstract)
class characterTestClass extends Character {

    public characterTestClass(Vector2 position, int width, int height, int maxHealth, int range, Texture texture, Unit target, int speed, int dps) {
        super(position, width, height, maxHealth, range, texture, target, speed, dps);
    }
}

public class testCharacter {


}
