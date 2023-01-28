package trou.arch.object;

import dev.architectury.event.events.client.ClientRawInputEvent;
import dev.architectury.event.events.client.ClientTickEvent;
import dev.architectury.event.events.client.ClientTooltipEvent;
import trou.arch.client.ModKeys;
import trou.arch.item.ItemExpContainer;

public class ModEvents {
    public static void register() {
        ClientTooltipEvent.ITEM.register(ItemExpContainer::appendTooltip);
        ClientRawInputEvent.MOUSE_SCROLLED.register(ItemExpContainer::mouseScroll);
        ClientTickEvent.CLIENT_POST.register(ModKeys::handleThrowExpKey);
    }
}
