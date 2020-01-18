import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.sprites.Character;
import com.mygdx.game.sprites.Unit;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

//Instance of the Unit class to test on (is abstract)
class characterTestClass extends Character {

    private Unit target;
    private int speed;
    private int dps;
    private float attackCooldown;
    private float timeSinceAttack;
    private int range;

    public characterTestClass(Vector2 position, int width, int height, Texture texture, int maxHealth, int range, Unit target,
                              int speed, int dps, float attackCooldown) {
        super(position, width, height, texture, maxHealth, range, target, speed, dps, attackCooldown);
        this.target = target;
        this.speed = speed;
        this.dps = dps;
        this.attackCooldown = attackCooldown;
        this.timeSinceAttack = 0;
    }
}

//55% line coverage - only getters and setters

//No functions to test - will just test the constructor
public class testCharacter {
    //Constructor used to ensure it works as intended
    Character testCharacter = new characterTestClass(new Vector2(100, 100 ), 100, 100, null,
            100, 10, null, 10, 10, 11);

    //Testing to ensure constructor works as intended through basic getter functions
    @Test
    public void constructorShouldInitializeCorrectly() {
        assertEquals(null, testCharacter.getTarget());
        assertEquals(10, testCharacter.getDamage());
        assertEquals(11, testCharacter.getAttackCooldown());
    }

}
