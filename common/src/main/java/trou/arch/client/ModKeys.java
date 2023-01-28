package trou.arch.client;

import com.mojang.blaze3d.platform.InputConstants;
import dev.architectury.registry.client.keymappings.KeyMappingRegistry;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import trou.arch.network.MessageThrowExp;
import trou.arch.network.ModChannels;

import java.util.UUID;

public class ModKeys {
    public static final KeyMapping THROW_EXP = new KeyMapping("key.arch.throw_exp", InputConstants.Type.KEYSYM, InputConstants.KEY_Z, "category.arch");

    public static void handleThrowExpKey(Minecraft minecraft) {
        while (THROW_EXP.consumeClick()) {
            if (minecraft.player == null) return;
            if (minecraft.crosshairPickEntity instanceof Player) {
                UUID playerUUID = minecraft.crosshairPickEntity.getUUID();
                ModChannels.CHANNEL.sendToServer(new MessageThrowExp(playerUUID));
            }
        }
    }

    public static void register() {
        KeyMappingRegistry.register(THROW_EXP);
    }
}
