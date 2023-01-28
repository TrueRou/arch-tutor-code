package trou.arch.forge;

import dev.architectury.platform.forge.EventBuses;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import trou.arch.Arch;

@Mod(Arch.MOD_ID)
public class ArchForge {
    public ArchForge() {
        EventBuses.registerModEventBus(Arch.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        Arch.init();
    }
}
