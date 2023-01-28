package trou.arch;

import trou.arch.client.ModKeys;
import trou.arch.network.ModChannels;
import trou.arch.network.ModPackets;
import trou.arch.object.ModEvents;
import trou.arch.object.ModItems;

public class Arch {
    public static final String MOD_ID = "arch";

    public static void init() {
        System.out.println("Hello Architectury!");
        Storyteller.tellStory();
        ModItems.register();
        ModKeys.register();
        ModEvents.register();
        ModPackets.register();
        ModChannels.register();
    }
}
