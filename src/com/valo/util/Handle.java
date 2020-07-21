package com.valo.util;

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
}

