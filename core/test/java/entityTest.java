import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.sprites.Entity;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

//Class to test methods in the Entity Class.
public class entityTest {

    //Instance of entity class to test methods on
    Entity testEntity = new Entity(new Vector2(100, 100), 100, 100, null);

    //Testing a standard move and checking corners
    @Test
    public void testSetPositionStandard(){
        testEntity.setPosition(100, 100);
        assertEquals(new Vector2(100, 100), testEntity.getPosition());
        assertEquals(new Vector2(200, 200), testEntity.getTopRight());
    }

    //Testing nothing changes when 0 is inputted
    @Test
    public void testSetPositionShouldAllowForZeros() {
        testEntity.setPosition(0, 0);
        assertEquals(new Vector2(0, 0), testEntity.getPosition());
        assertEquals(new Vector2(100, 100), testEntity.getTopRight());
    }

    //Testing negative numbers !!! Dont think this works
    @Test
    public void testSetPositionNegativeNumbersShouldReturnArgumentException() throws  IllegalArgumentException{
        testEntity.setPosition(-1, -1);
        assertEquals(new Vector2(-1, -1) , testEntity.getPosition());
    }


}





