import org.junit.Test;
import static org.junit.Assert.*;

public class HandleTests {
    @Test
    public void handlePositiveIndConstructor() {
        int ind = 3580;
        int gen = 2578;

        Handle a = new Handle(ind, gen);
        assertEquals("Index should be " + ind, a.getIndex(), ind);
        assertEquals("Generation should be " + gen, a.getGeneration(), gen);
    }

    @Test
    public void handlePositiveGenConstructor() {
        int ind = 0;
        int gen = 158;

        Handle a = new Handle(ind, gen);
        assertEquals("Index should be " + ind, a.getIndex(), ind);
        assertEquals("Generation should be " + gen, a.getGeneration(), gen);
    }

    @Test
    public void handleNegativeIndConstructor() {
        int ind = -258;
        int gen = 1;

        Handle a = new Handle(ind, gen);
        assertEquals("Index should be " + ind, a.getIndex(), ind);
        assertEquals("Generation should be " + gen, a.getGeneration(), gen);
    }

    @Test
    public void handleNegativeGenConstructor() {
        int ind = 10258;
        int gen = -1;

        Handle a = new Handle(ind, gen);
        assertEquals("Index should be " + ind, a.getIndex(), ind);
        assertEquals("Generation should be " + gen, a.getGeneration(), gen);
    }

    @Test
    public void toStringTest() {
        int ind = -258;
        int gen = 1561;

        Handle a = new Handle(ind, gen);
        assertEquals(a.toString(), "Handle{" + "ind: " + ind + ", gen: " + gen + "}");
    }
}