package trou.arch.network;

import dev.architectury.networking.NetworkManager;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import trou.arch.item.ItemExpContainer;

import java.util.UUID;
import java.util.function.Supplier;

public class MessageThrowExp {
    public final UUID targetPlayerUUID;
    public MessageThrowExp(FriendlyByteBuf buf) {
        this(buf.readUUID());
    }

    public MessageThrowExp(UUID targetPlayerUUID) {
        this.targetPlayerUUID = targetPlayerUUID;
    }

    public void encode(FriendlyByteBuf buf) {
        buf.writeUUID(targetPlayerUUID);
    }

    public void apply(Supplier<NetworkManager.PacketContext> contextSupplier) {
        Player self = contextSupplier.get().getPlayer();
        Player target = self.level.getPlayerByUUID(targetPlayerUUID);
        if (target == null) return;
        ItemStack stack = self.getItemInHand(InteractionHand.MAIN_HAND);
        ItemExpContainer.doThrow(stack, target);
    }
}
