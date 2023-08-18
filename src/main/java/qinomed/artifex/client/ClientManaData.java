package qinomed.artifex.client;

public class ClientManaData {
    private static int mana;

    public static void set(int mana) {
        ClientManaData.mana = mana;
    }

    public static int getMana() {
        return mana;
    }
}
