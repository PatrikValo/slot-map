public class Main {

    public static void main(String[] args) {
        SlotMap<Integer> slotmap = new SlotMap<>();
        Handle a = slotmap.insert(25);
        slotmap.insert(26);
        System.out.println(slotmap.get(a));
        slotmap.erase(a);
        System.out.println(slotmap.valid(a));
        slotmap.insert(90);
        System.out.println(slotmap.isEmpty());

        System.out.println(slotmap);
    }
}
