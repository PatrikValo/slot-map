package com.valo.util;

import java.util.ArrayList;
import java.util.List;

public class SlotMap<E> {
    private List<E> data = new ArrayList<>();

    public boolean isEmpty() {
        return data.isEmpty();
    }

    public int size() {
        return data.size();
    }

    public Handle insert(E value) {
        return null;
    }

    public boolean valid(Handle handle) {
        return true;
    }

    public void erase(Handle handle) {}

    public E get() {
        return (E) new Object();
    }
}

