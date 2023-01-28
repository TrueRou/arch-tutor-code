package trou.arch.item;

import dev.architectury.event.EventResult;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import trou.arch.network.ModPackets;

import java.util.List;

public class ItemExpContainer extends Item {
    public ItemExpContainer() {
        super(new Properties().stacksTo(1).tab(CreativeModeTab.TAB_TOOLS));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(@NotNull Level level, @NotNull Player player, @NotNull InteractionHand usedHand) {
        ItemStack stack = player.getItemInHand(usedHand);
        if (level.isClientSide || usedHand == InteractionHand.OFF_HAND) return InteractionResultHolder.fail(stack);
        CompoundTag tag = stack.hasTag() ? stack.getTag() : new CompoundTag();
        assert tag != null;
        int mode = tag.getInt("mode");
        if (player.isShiftKeyDown()) {
            int storedExp = tag.getInt("exp");
            int amount = Math.min(mode, storedExp);
            player.giveExperiencePoints(amount);
            tag.putInt("exp", storedExp - amount);
        } else {
            int ownedExp = player.totalExperience;
            int cost = Math.min(ownedExp, mode);
            player.giveExperiencePoints(-cost);
            tag.putInt("exp", tag.getInt("exp") + cost);
        }
        stack.setTag(tag);
        return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());
    }

    public static void appendTooltip(ItemStack stack, List<Component> lines, TooltipFlag flag) {
        if (stack.getItem() instanceof ItemExpContainer) {
            CompoundTag tag = stack.hasTag() ? stack.getTag() : new CompoundTag();
            assert tag != null;
            lines.add(new TranslatableComponent("tooltip.exp_container", tag.getInt("exp")));
            lines.add(new TranslatableComponent("tooltip.exp_container_mode", tag.getInt("mode")));
        }
    }

    public static EventResult mouseScroll(Minecraft minecraft, double v) {
        assert minecraft.player != null;
        ItemStack heldItem = minecraft.player.getItemInHand(InteractionHand.MAIN_HAND);
        if (heldItem.getItem() instanceof ItemExpContainer) {
            if (minecraft.player.isShiftKeyDown()) {
                ModPackets.sendExpContainerMousePacket((int) v);
                return EventResult.interruptFalse();
            }
        }
        return EventResult.pass();
    }

    public static void raiseMode(ItemStack stack, int value) {
        if (!(stack.getItem() instanceof ItemExpContainer)) return;
        CompoundTag tag = stack.hasTag() ? stack.getTag() : new CompoundTag();
        assert tag != null;
        int newValue = Math.max(tag.getInt("mode") + value, 0);
        tag.putInt("mode", newValue);
    }

    public static void doThrow(ItemStack stack, Player target) {
        if (!(stack.getItem() instanceof ItemExpContainer)) return;
        CompoundTag tag = stack.hasTag() ? stack.getTag() : new CompoundTag();
        assert tag != null;
        int storedExp = tag.getInt("exp");
        int mode = tag.getInt("mode");
        int amount = Math.min(mode, storedExp);
        target.giveExperiencePoints(amount);
        tag.putInt("exp", storedExp - amount);
    }
}
