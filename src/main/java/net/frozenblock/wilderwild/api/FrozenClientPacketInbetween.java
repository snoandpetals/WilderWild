package net.frozenblock.wilderwild.api;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;

public final class FrozenClientPacketInbetween {
    private FrozenClientPacketInbetween() {
        throw new UnsupportedOperationException("FrozenClientPacketInbetween contains only static declarations.");
    }

    public static void requestFrozenSoundSync(int id, ResourceKey<Level> level) {
        FrozenClientPacketToServer.sendFrozenSoundSyncRequest(id, level);
    }

}
