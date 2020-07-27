import org.junit.Test;
import static org.junit.Assert.*;

public class HandleTests {
    @Test
    public void handlePositiveIndConstructor() {
        int ind = 3580;
        int gen = 2578;

        Handle a = new Handle(ind, gen);
        assertEquals("Index should be " + ind, ind, a.getIndex());
        assertEquals("Generation should be " + gen, gen, a.getGeneration());
    }

    @Test
    public void handlePositiveGenConstructor() {
        int ind = 0;
        int gen = 158;

        Handle a = new Handle(ind, gen);
        assertEquals("Index should be " + ind, ind, a.getIndex());
        assertEquals("Generation should be " + gen, gen, a.getGeneration());
    }

    @Test
    public void handleNegativeIndConstructor() {
        int ind = -258;
        int gen = 1;

        Handle a = new Handle(ind, gen);
        assertEquals("Index should be " + ind, ind, a.getIndex());
        assertEquals("Generation should be " + gen, gen, a.getGeneration());
    }

    @Test
    public void handleNegativeGenConstructor() {
        int ind = 10258;
        int gen = -1;

        Handle a = new Handle(ind, gen);
        assertEquals("Index should be " + ind, ind, a.getIndex());
        assertEquals("Generation should be " + gen, gen, a.getGeneration());
    }

    @Test
    public void toStringTest() {
        int ind = -258;
        int gen = 1561;

        Handle a = new Handle(ind, gen);
        assertEquals( "Handle{" + "ind: " + ind + ", gen: " + gen + "}", a.toString());
    }
}