package trou.arch.network;

import dev.architectury.networking.NetworkChannel;
import net.minecraft.resources.ResourceLocation;

public class ModChannels {
    public static final NetworkChannel CHANNEL = NetworkChannel.create(new ResourceLocation("arch", "exp_container"));

    public static void register() {
        CHANNEL.register(MessageThrowExp.class, MessageThrowExp::encode, MessageThrowExp::new, MessageThrowExp::apply);
    }
}
