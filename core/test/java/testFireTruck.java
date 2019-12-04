import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.sprites.Unit;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.mygdx.game.sprites.Firetruck;

//Instance of the Unit class to test on (is abstract)
class unitForFireTruck extends Unit {

    public unitForFireTruck( Vector2 position, int width, int height, Texture texture) {
        super(position, width, height, texture);
    }
}


public class testFireTruck {

    //Instance of the Unit class to test on (standard maxHealth: 100, range = 5).
    Unit testUnit = new unitTestClass(new Vector2(0, 0),100, 100, null);

    //Instance of the FireTruck class to test on
    Firetruck testFireTruck = new Firetruck(new Vector2(100, 100), 10, 10, null,
            100, 10, null, 10, 10, 10, 100, true);

    //Test that FireTruck will damage a unit (covers both alien and fortress since both children of Unit)
    //And that the currentWater
    @Test
    public void testFireTruckShouldDamageUnit() {
        testFireTruck.firetruckAttack(testUnit);
        assertEquals(90, testFireTruck.getCurrentWater());
        assertEquals(90, testUnit.getCurrentHealth());
    }

    //Test that FireTruck does continuous damage
    @Test
    public void testTestFireTruckShouldContinuousDamage() {
        for(int i=0; i < 9; i++) {
            testFireTruck.firetruckAttack(testUnit);
        }
        assertEquals(10, testFireTruck.getCurrentWater());
        assertEquals(10, testUnit.getCurrentHealth());
    }

    //Test that FireTruck does not go into negative currentWater and testUnit is dead after going to 0 health from
    //attacks
    @Test
    public void testFireTruckShouldNotHaveNegativeCurrentWater() {
        for(int i=0; i <= 10; i++) {
            testFireTruck.firetruckAttack(testUnit);
        }
        assertEquals(0, testFireTruck.getCurrentWater());
        assertEquals(0, testUnit.getCurrentHealth());
        assertTrue(testUnit.isDead());
    }

    //No tests needed for:
        //No variable passed: we won't call the method without a target to pass
        //If the target isDead(): only pass alive aliens and even if dead the alien can't go into negative through
            //setCurrentHealth()

        //move(): it works in game
        //willCollide(): already works



}
