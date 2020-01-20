import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.sprites.Fortress;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

//66% line coverage

public class FortressTest {

    //Instance of Fortress to ensure the constructor works as intended with level 1
    Fortress testFortressLevel1 = new Fortress(new Vector2(100, 100), 100, 100, null,
            100, 5.0f, 1);

    //testing to ensure the constructor worked through testing of getters
    @Test
    public void constructorShouldGiveCorrectSpawnLevel1() {
        assertEquals(5.0f, testFortressLevel1.getSpawnRate());
    }

    //Instance of Fortress to ensure the constructor works as intended with level 2
    Fortress testFortressLevel2 = new Fortress(new Vector2(100, 100), 100, 100, null,
            100, 10.0f, 2);

    //Fortress testFortressLevel2
    @Test
    public void constructorShouldGiveCorrectSpawnLevel2() {
        assertEquals(10.0f, testFortressLevel2.getSpawnRate());
    }

    //Instance of Fortress to ensure the constructor works as intended with level 3
    Fortress testFortressLevel3 = new Fortress(new Vector2(100, 100), 100, 100, null,
            100, 15.0f, 3);

    //Fortress testFortressLevel3
    @Test
    public void constructorShouldGiveCorrectSpawnLevel3() {
        assertEquals(15.0f, testFortressLevel3.getSpawnRate());
    }

}
