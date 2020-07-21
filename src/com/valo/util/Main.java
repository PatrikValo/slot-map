package com.valo.util;

public class Main {

    public static void main(String[] args) {
        SlotMap<Integer> slotmap = new SlotMap<>();
        var a = slotmap.insert(25);
        slotmap.insert(26);
        slotmap.erase(a);
        slotmap.insert(90);

        System.out.println(slotmap);
    }
}
