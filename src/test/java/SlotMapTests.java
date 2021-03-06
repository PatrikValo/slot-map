import org.junit.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.*;

public class SlotMapTests {
    @Test
    public void isEmptyTest() {
        Map<Integer> slotmap = new SlotMap<>();

        assertTrue(slotmap.isEmpty());

        Handle a = slotmap.insert(5);
        assertFalse(slotmap.isEmpty());
        Handle b = slotmap.insert(8);
        assertFalse(slotmap.isEmpty());
        slotmap.erase(a);
        assertFalse(slotmap.isEmpty());
        slotmap.erase(b);

        assertTrue(slotmap.isEmpty());
    }

    @Test
    public void clearTest() {
        Map<Integer> slotmap = new SlotMap<>();

        slotmap.insert(5);
        slotmap.insert(8);
        assertFalse(slotmap.isEmpty());
        slotmap.clear();
        assertTrue(slotmap.isEmpty());
        slotmap.insert(4);
        assertFalse(slotmap.isEmpty());
        slotmap.clear();
        assertTrue(slotmap.isEmpty());
    }

    @Test
    public void sizeTest() {
        Map<Integer> slotmap = new SlotMap<>();

        assertEquals( 0, slotmap.size());
        slotmap.insert(5);
        assertEquals(1, slotmap.size());
        slotmap.insert(8);
        assertEquals(2, slotmap.size());
        slotmap.clear();
        assertEquals(0, slotmap.size());
        Handle a = slotmap.insert(4);
        assertEquals(1, slotmap.size());
        Handle b = slotmap.insert(25);
        assertEquals(2, slotmap.size());
        Handle c = slotmap.insert(5);
        assertEquals(3, slotmap.size());
        slotmap.erase(a);
        assertEquals(2, slotmap.size());
        slotmap.erase(b);
        assertEquals(1, slotmap.size());
        slotmap.erase(c);
        assertEquals(0, slotmap.size());
    }

    @Test
    public void validTest() {
        Map<String> slotmap = new SlotMap<>();
        Handle h = slotmap.insert("ahoj");
        Handle h2 = slotmap.insert("ahoj2");

        assertTrue(slotmap.valid(h));
        assertTrue(slotmap.valid(h2));
        assertFalse(slotmap.valid(null));
        assertFalse(slotmap.valid(new Handle(-2, 6)));
        assertFalse(slotmap.valid(new Handle(0, 6)));
        assertFalse(slotmap.valid(new Handle(2, 6)));
        assertFalse(slotmap.valid(new Handle(2, 0)));
        assertFalse(slotmap.valid(new Handle(2, -1)));
        assertFalse(slotmap.valid(new Handle(2, -10)));
        slotmap.erase(h);
        assertFalse(slotmap.valid(h));
    }

    @Test(expected = RuntimeException.class)
    public void insertNullTest() {
        Map<String> slotmap = new SlotMap<>();

        slotmap.insert(null);
    }

    @Test(expected = RuntimeException.class)
    public void eraseNullTest() {
        Map<String> slotmap = new SlotMap<>();

        slotmap.erase(null);
    }

    @Test
    public void insertAndEraseTest() {
        Map<String> slotmap = new SlotMap<>();

        var a = slotmap.insert("one");
        assertEquals(new Handle(0, 1), a);
        var b = slotmap.insert("two");
        assertEquals(new Handle(1, 1), b);
        var c = slotmap.insert("three");
        assertEquals(new Handle(2, 1), c);
        slotmap.erase(b);
        b = slotmap.insert("four");
        assertEquals(new Handle(1, 2), b);
        slotmap.erase(a);
        slotmap.erase(c);
        a = slotmap.insert("five");
        assertEquals(new Handle(0, 2), a);
        slotmap.erase(a);
        c = slotmap.insert("six");
        assertEquals(new Handle(2, 2), c);
        a = slotmap.insert("seven");
        assertEquals(new Handle(0, 3), a);
    }

    @Test
    public void getTest() {
        Map<Integer> slotmap = new SlotMap<>();

        var a = slotmap.insert(2);
        var b = slotmap.insert(0);
        var c = slotmap.insert(-1);
        var d = slotmap.insert(25888);

        assertEquals(2, (int) slotmap.get(a));
        assertEquals(0, (int) slotmap.get(b));
        assertEquals(-1, (int) slotmap.get(c));
        assertEquals(25888, (int) slotmap.get(d));
    }

    @Test(expected = RuntimeException.class)
    public void getNullTest() {
        Map<Integer> slotmap = new SlotMap<>();

        slotmap.get(null);
    }

    @Test(expected = RuntimeException.class)
    public void getInvalidTest() {
        Map<Integer> slotmap = new SlotMap<>();

        slotmap.get(new Handle(25, 1));
    }

    @Test
    public void valuesTest() {
        List<Integer> alist = Arrays.asList(1, 2, 3, 5, 8);
        Map<Integer> slotmap = new SlotMap<>();

        slotmap.insert(1);
        var h = slotmap.insert(5);
        slotmap.insert(2);
        slotmap.erase(h);
        slotmap.insert(3);
        slotmap.insert(5);
        slotmap.insert(8);
        assertEquals( alist, slotmap.values());
    }

    @Test
    public void forCycleTest() {
        List<Integer> alist = Arrays.asList(1, 2, 3, 5, 8);
        Map<Integer> slotmap = new SlotMap<>();

        slotmap.insert(1);
        var h = slotmap.insert(5);
        slotmap.insert(2);
        slotmap.erase(h);
        slotmap.insert(3);
        slotmap.insert(5);
        slotmap.insert(8);

        int index = 0;
        for (Integer number : slotmap) {
            assertEquals(alist.get(index), number);
            index++;
        }
    }
}