import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.sprites.Projectile;
import com.mygdx.game.sprites.Unit;
import org.junit.Before;
import org.junit.experimental.categories.Category;
import org.junit.jupiter.api.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runner.notification.Failure;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;


import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;


class unitForProjectile extends Unit {

    public unitForProjectile(Vector2 position, int width, int height, Texture texture, int maxHealth) {
        super(position, width, height, texture, maxHealth);
    }
}


@RunWith(Parameterized.class)
public class ProjectileTest {
    private Unit instance;
    private boolean expected;
    private Projectile projectile;

    private static Unit instance1 = new unitForProjectile(new Vector2(200, 200),100, 100, null,
            100);
    private static Unit instance2 = new unitForProjectile(new Vector2(150, 150),100, 100, null,
            100);
    private static Unit instance3 = new unitForProjectile(new Vector2(0, 0),1, 1, null,
            100);



    @Before
    public void initialise(){

        projectile = new Projectile(new Vector2(100, 100), 10, 10, null,
                new Vector2(200, 200), 10.0f, 10, 10);

    }

    public ProjectileTest(Unit instance, boolean expected){
        this.instance = instance;
        this.expected = expected;

    }

    @Parameterized.Parameters
    public static Collection input() {
        return Arrays.asList(new Object[][] { {instance1, true}, {instance2, true}, {instance3, false} });
    }


    //Testing through parameterized testing that hitUnit() works with both false and true values.
    @Test
    public void testHitUnit() {
        System.out.println("The projectile aiming at Unit should: " + expected);
        assertEquals(expected, projectile.hitUnit(instance));
    }

}


