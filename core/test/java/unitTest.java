import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.mygdx.game.sprites.Unit;

//Instance of the Unit class to test on (is abstract)
class unitTestClass extends Unit {

    public unitTestClass(int width, int height, Texture texture, Vector2 position) {
        super(width, height, texture, position);
    }
}




//Class to test the methods in the Unit Class
public class unitTest {

    //Instance of the Unit class to test on
    Unit testUnit = new unitTestClass(100, 100, null, new Vector2(0, 0));

    //Test if isDead() works when health is above 0
    @Test
    public void testIsDeadStandard(){
        assertFalse(testUnit.isDead(), "Unit had full health during test");
    }

    //Test if isDead() works when health is zero
    @Test
    public void testIsDeadShouldReturnTrueWhenZero(){
        testUnit.setCurrentHealth(0);
        assertTrue(testUnit.isDead(), "Unit had '0' health during test");
    }

    //Test if setCurrentHealth throws IllegalArgumentException when health is negative
    @Test
    public void testSetCurrentHealthThrowsExceptionWithNegative(){
        testUnit.setCurrentHealth(-1);
        assertTrue(testUnit.isDead(), "Unit had '-1' health during test");
    }


}
