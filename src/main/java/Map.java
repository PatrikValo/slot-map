import java.util.Collection;

public interface Map<E> {
    void clear();

    boolean isEmpty();

    int size();

    Handle insert(E value);

    boolean valid(Handle handle);

    void erase(Handle handle);

    E get(Handle handle);

    Collection<E> values();
}