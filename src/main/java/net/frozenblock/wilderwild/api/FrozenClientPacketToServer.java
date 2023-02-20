package net.frozenblock.wilderwild.api;

import io.netty.buffer.Unpooled;
import net.frozenblock.wilderwild.init.WWNetwork;
import net.frozenblock.wilderwild.network.RequestLoopingSoundSyncPacket;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class FrozenClientPacketToServer {
    public FrozenClientPacketToServer() {
    }

    public static void sendFrozenSoundSyncRequest(int id, ResourceKey<Level> level) {
        FriendlyByteBuf byteBuf = new FriendlyByteBuf(Unpooled.buffer());
        byteBuf.writeVarInt(id);
        byteBuf.writeResourceKey(level);
        WWNetwork.INSTANCE.sendToServer(new RequestLoopingSoundSyncPacket(id, level));
    }

}
