import java.util.*;
import java.util.stream.Collectors;

public class SlotMap<E> implements Iterable<E>, Map<E> {
    private class SlotMapIterator implements Iterator<E> {
        private int index;
        private final List<E> data;

        SlotMapIterator(List<E> data) {
            index = 0;
            this.data = data;
        }

        @Override
        public boolean hasNext() {
            return index < data.size();
        }

        @Override
        public E next() {
            return data.get(index++);
        }
    }

    private static class Node {
        private int index;
        private int generation;
        private boolean used;

        public Node() {
            index = 0;
            generation = 0;
            used = false;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public int getGeneration() {
            return generation;
        }

        public void incrementGeneration() {
            generation++;
        }

        public boolean isUsed() {
            return used;
        }

        public void setUsed(boolean used) {
            this.used = used;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return index == node.index && generation == node.generation;
        }

        @Override
        public int hashCode() {
            return Objects.hash(index, generation);
        }

        @Override
        public String toString() {
            return "Node{" + "index=" + index + ", generation=" + generation + '}';
        }
    }

    private final List<Node> indices = new ArrayList<>();
    private final List<Pair<E, Integer>> data = new ArrayList<>();
    private final LinkedList<Integer> free = new LinkedList<>();

    @Override
    public void clear() {
        indices.clear();
        data.clear();
        free.clear();
    }

    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }

    @Override
    public int size() {
        return data.size();
    }

    @Override
    public Handle insert(E value) {
        if (value == null) {
            throw new RuntimeException("Value can't be null!");
        }

        int indexToIndices = getFreeIndexToIndices();

        // insert into data value and store information about indices position
        data.add(new Pair<>(value, indexToIndices));
        int indexToData = data.size() - 1;

        // refresh information in indices
        Node node = indices.get(indexToData);
        node.setIndex(indexToData);
        node.incrementGeneration();
        node.setUsed(true);

        return new Handle(indexToIndices, node.getGeneration());
    }

    @Override
    public boolean valid(Handle handle) {
        if (handle == null) {
            return false;
        }

        Node node = indices.get(handle.getIndex());

        if (!node.isUsed()) {
            return false;
        }

        if (handle.getIndex() >= size()) {
            return false;
        }

        return handle.getGeneration() == node.getGeneration();
    }

    @Override
    public void erase(Handle handle) {
        if (!valid(handle)) {
            throw new RuntimeException("Invalid handle!");
        }


        int indexToData = indices.get(handle.getIndex()).getIndex();
        int indexToIndices = data.get(indexToData).getRight();

        data.remove(handle.getIndex());
        clearIndicesOnIndex(indexToIndices);
    }

    @Override
    public E get(Handle handle) {
        if (!valid(handle)) {
            throw new RuntimeException("Invalid handle!");
        }

        int indexToData = indices.get(handle.getIndex()).getIndex();
        return data.get(indexToData).getLeft();
    }

    @Override
    public Collection<E> values() {
        return getValuesList();
    }

    @Override
    public Iterator<E> iterator() {
        return new SlotMapIterator(getValuesList());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SlotMap<?> slotMap = (SlotMap<?>) o;
        return  Objects.equals(indices, slotMap.indices) &&
                Objects.equals(data, slotMap.data) &&
                Objects.equals(free, slotMap.free);
    }

    @Override
    public int hashCode() {
        return Objects.hash(indices, data, free);
    }

    @Override
    public String toString() {
        Optional<String> str = data.stream().map(pair -> {
            Node node = indices.get(pair.getRight());
            Handle handle = new Handle(pair.getRight(), node.getGeneration());
            return handle.toString() + ": " + pair.getLeft().toString();
        }).reduce((acc, val) -> acc + ", " + val);

        return String.format("SlotMap{%s}", str.orElse("Empty"));
    }

    private int getFreeIndexToIndices() {
        if (free.isEmpty()) {
            indices.add(new Node());
            return indices.size() - 1;
        }

        return free.removeFirst();
    }

    private void clearIndicesOnIndex(int index) {
        indices.get(index).setUsed(false);
        free.add(index);
    }

    private List<E> getValuesList() {
        return data.stream().map(Pair::getLeft).collect(Collectors.toList());
    }
}
