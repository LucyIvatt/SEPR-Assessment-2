import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.sprites.Fortress;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

//66% line coverage

public class FortressTest {

    //Instance of Fortress to ensure the constructor works as intended
    Fortress testFortress = new Fortress(new Vector2(100, 100), 100, 100, null,
            100, 5.0f, 1);

    //testing to ensure the constructor worked through testing of getters
    @Test
    public void getSpawnRateShouldReturnGivenValue() {
        assertEquals(5.0f, testFortress.getSpawnRate());
    }

}
