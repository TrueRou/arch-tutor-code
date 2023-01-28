package trou.arch.object;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.Registry;
import net.minecraft.world.item.Item;
import trou.arch.Arch;
import trou.arch.item.ItemExpContainer;

public class ModItems {
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Arch.MOD_ID, Registry.ITEM_REGISTRY);
    public static final RegistrySupplier<Item> EXP_CONTAINER_ITEM = ITEMS.register("exp_container_item", ItemExpContainer::new);
    public static void register() {
        ITEMS.register();
    }
}
