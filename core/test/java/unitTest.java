import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.sprites.Unit;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//
//NEED TO CHECK IF CREATING AN INSTANCE IS GOOD OR I SHOULD MOCK INSTEAD (no dependencies)

//Instance of the Unit class to test on (is abstract)
class unitTestClass extends Unit {

    public unitTestClass(Vector2 position, int width, int height, Texture texture, int maxHealth) {
        super(position, width, height, texture, maxHealth);
    }
}


//Class to test the methods in the Unit Class
public class unitTest {

    //Instance of the Unit class to test on
    Unit testUnit = new unitTestClass(new Vector2(0, 0),100, 100, null, 100);

    //Test for constructor that takes maxHealth
    @Test
    public void testShouldChangeMaxHealthToGivenParameter(){
    }


    //Test if isDead() works when health is above 0
    @Test
    public void testIsDeadStandard(){
        testUnit.setCurrentHealth(70);
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

    //Test if setCurrentHealth() will cap the health input to the max from constructor
    @Test
    public void testSetCurrentHealthShouldCapMaxHealth() {
        testUnit.setCurrentHealth(100000);
        assertEquals(100, testUnit.getCurrentHealth());
    }


    //Test if addHealth() will add health (not hitting max health)
    @Test
    public void testAddHealthWithNormalValues(){
        testUnit.setCurrentHealth(50);
        testUnit.addHealth(20);
        assertEquals(70, testUnit.getCurrentHealth());
    }

    //Test if setCurrentHealth will cap the additional health to maxHealth
    @Test
    public void testAddHealthWillCapAddedHealth(){
        testUnit.setCurrentHealth(100);
        testUnit.addHealth(50);
        assertEquals(100, testUnit.getCurrentHealth());
    }

    //Test if addHealth will take the absolute value of the argument
    @Test
    public void testAddHealthRejectNegativeHeal(){
        testUnit.setCurrentHealth(50);
        testUnit.addHealth(-50);
        assertEquals(100, testUnit.getCurrentHealth());
    }

    //Test if takeDamage works within a standard range (not 0 or negatives)
    @Test
    public void testTakeDamageStandard(){
        testUnit.setCurrentHealth(100);
        testUnit.takeDamage(50);
        assertEquals(50, testUnit.getCurrentHealth());
    }

    //Test if takeDamage caps the health at 0.
    @Test
    public void testTakeDamageCapsCurrentHealth(){
        testUnit.setCurrentHealth(100);
        testUnit.takeDamage(101);
        assertEquals(0, testUnit.getCurrentHealth());
        assertTrue(testUnit.isDead(), "Test Unit took 101 damage (-1 in health) so should be dead");
    }



}
