import be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.utilities.Vector2D;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Vector2DTests {
    @Test
    public void equalityTest() {
        Vector2D vector1 = new Vector2D(1.0, 2.0);
        Vector2D vector2 = new Vector2D(1, 2);
        Assertions.assertEquals(vector1, vector2);
    }

    @Test
    public void getterTest() {
        Vector2D vector1 = new Vector2D(1.0, 2.0);
        Vector2D vector2 = new Vector2D(1, 2);
        Assertions.assertEquals(1, vector2.getX());
        Assertions.assertEquals(2.0, vector2.y);
        Assertions.assertEquals(1, vector1.getX());
        Assertions.assertEquals(2.0, vector2.y);
    }

    @Test
    public void directionTest() {
        Vector2D vector1 = new Vector2D(1, 1);
        Vector2D vector2 = new Vector2D(-2, -2);
        Vector2D vector3 = new Vector2D(0, 1);
        Vector2D vector4 = new Vector2D(0, -1);
        Vector2D vector5 = new Vector2D(3, 0);
        Vector2D vector6 = new Vector2D(-2, 0);
        Vector2D vector7 = new Vector2D(0, 0);

        Assertions.assertEquals(Math.PI / 4, vector1.getDirection());
        Assertions.assertEquals(5 * Math.PI / 4, vector2.getDirection());
        Assertions.assertEquals(Math.PI / 2, vector3.getDirection());
        Assertions.assertEquals(2 * Math.PI - Math.PI / 2, vector4.getDirection());
        Assertions.assertEquals(0, vector5.getDirection());
        Assertions.assertEquals(Math.PI, vector6.getDirection());
        Assertions.assertEquals(0, vector7.getDirection());
    }

    @Test
    public void sizeTest() {
        Vector2D vector1 = new Vector2D(0, 0);
        Vector2D vector2 = new Vector2D(2, 2);
        Vector2D vector3 = new Vector2D(3, 4);

        Assertions.assertEquals(0, vector1.getSize());
        Assertions.assertEquals(0, vector1.getSizeSquared());
        Assertions.assertEquals(Math.sqrt(8), vector2.getSize());
        Assertions.assertEquals(8, vector2.getSizeSquared());
        Assertions.assertEquals(5, vector3.getSize());
        Assertions.assertEquals(25, vector3.getSizeSquared());
    }

    @Test
    public void angleFromTest() {
        Vector2D vector1 = new Vector2D(1, 0);
        Vector2D vector2 = new Vector2D(0, 1);
        Vector2D vector3 = new Vector2D(-1, 1);

        Assertions.assertEquals(0, vector1.angleFrom(vector1));
        Assertions.assertEquals(Math.PI / 2, vector2.angleFrom(vector1));
        Assertions.assertEquals(3 * Math.PI / 4, vector3.angleFrom(vector1));
        Assertions.assertEquals(-Math.PI / 4, vector2.angleFrom(vector3));
    }

    @Test
    public void rotateTest() {
        for (int i = 0; i < 100; i++) {
            Vector2D vector = new Vector2D(Math.random() - 0.5, Math.random() - 0.5);
            double direction = vector.getDirection();
            double size = vector.getSize();
            double angle = Math.random()*Math.PI*10;
            vector.rotate(angle);
            Assertions.assertEquals(size, vector.getSize(), 0.001);
            Assertions.assertEquals((direction + angle)%(Math.PI*2), vector.getDirection(), 0.001);
        }
    }
}
