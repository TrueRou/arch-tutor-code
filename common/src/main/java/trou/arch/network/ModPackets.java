package trou.arch.network;

import dev.architectury.networking.NetworkManager;
import io.netty.buffer.Unpooled;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import trou.arch.item.ItemExpContainer;

public class ModPackets {
    public static final ResourceLocation EXP_CONTAINER_MODE = new ResourceLocation("arch", "exp_container_change_mode");

    public static void register() {
        NetworkManager.registerReceiver(NetworkManager.Side.C2S, EXP_CONTAINER_MODE, (buf, context) -> {
            Player player = context.getPlayer();
            int deltaMode = buf.readInt();
            ItemStack stack = player.getItemInHand(InteractionHand.MAIN_HAND);
            ItemExpContainer.raiseMode(stack, deltaMode);
        });
    }

    public static void sendExpContainerMousePacket(int deltaMode) {
        FriendlyByteBuf buf = new FriendlyByteBuf(Unpooled.buffer());
        buf.writeInt(deltaMode);
        NetworkManager.sendToServer(EXP_CONTAINER_MODE, buf);
    }
}
