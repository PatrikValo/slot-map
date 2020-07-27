import java.util.Objects;

public class Handle {

    private final int index;
    private final int generation;

    public Handle(int ind, int gen) {
        index = ind;
        generation = gen;
    }

    public int getIndex() {
        return index;
    }

    public int getGeneration() {
        return generation;
    }

    @Override
    public String toString() {
        return "Handle{" +
                "ind: " + index +
                ", gen: " + generation +
                "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Handle handle = (Handle) o;
        return index == handle.index &&
                generation == handle.generation;
    }

    @Override
    public int hashCode() {
        return Objects.hash(index, generation);
    }
}