import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.sprites.Entity;
import com.mygdx.game.sprites.Firetruck;
import com.mygdx.game.sprites.Unit;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

//Instance of the Unit class to test on (is abstract)
class unitForFireTruck extends Unit {

    private int maxWater;
    private int currentWater;
    private boolean selected;

    public unitForFireTruck(Vector2 position, int width, int height, Texture texture, int maxHealth, int range, Unit target,
                            int speed, int dps, int bearing, int maxWater, boolean selected, float attackCooldown) {
        super(position, width, height, texture, maxHealth);
        this.maxWater = maxWater;
        this.currentWater = maxWater;
        this.selected = selected;
    }

}


public class testFireTruck {

    //Instance of entity class to test methods on
    Entity testEntity = new Entity(new Vector2(200, 200), 100, 100, null);

    //Instance of the FireTruck class to test on
    Firetruck testFireTruck = new Firetruck(new Vector2(100, 100 ), 101, 102, null,
            103, 10, null, 10, 12, 13, true);


    //Testing basic constructor functionality with getters
    @Test
    public void constructorShouldSetCorrectParametersToVariables() {
        assertEquals(13, testFireTruck.getMaxWater());
        assertEquals(13, testFireTruck.getCurrentWater());
        assertTrue(testFireTruck.isSelected());
    }

    //Testing that updateCurrentWater works with standard input
    @Test
    public void updateCurrentWaterShouldChangeCurrentWaterStandard() {
        testFireTruck.updateCurrentWater(10);
        assertEquals(3, testFireTruck.getCurrentWater());
    }

    //Testing that update water works with boundary value to go to 0
    @Test
    public void updateCurrentWaterShouldAllowForWaterToBeZero() {
        testFireTruck.updateCurrentWater(13);
        assertEquals(0, testFireTruck.getCurrentWater());
    }

    //Testing that update water if in the negative water level sets it to 0
    @Test
    public void updateCurrentWaterShouldSetNegativeWaterLevelsToZero() {
        testFireTruck.updateCurrentWater(14);
        assertEquals(0, testFireTruck.getCurrentWater());
    }



}
