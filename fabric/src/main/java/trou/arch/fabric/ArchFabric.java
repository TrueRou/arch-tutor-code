package trou.arch.fabric;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.api.ModInitializer;
import trou.arch.Arch;

@Environment(EnvType.SERVER)
public class ArchFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        Arch.init();
    }
}
