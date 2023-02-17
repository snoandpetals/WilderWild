package net.frozenblock.wilderwild.network;

import net.frozenblock.wilderwild.init.WWParticles;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkEvent;

import java.util.Optional;
import java.util.function.Supplier;

public class FloatingSculkBubblePacket {
    private final double x;
    private final double y;
    private final double z;
    private final int size;
    private final int maxAge;
    private final double yVel;
    private final int count;

    public FloatingSculkBubblePacket(double x, double y, double z, int size, int maxAge, double yVel, int count) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.size = size;
        this.maxAge = maxAge;
        this.yVel = yVel;
        this.count = count;
    }

    public static void write(FloatingSculkBubblePacket packet, FriendlyByteBuf buf) {
        buf.writeDouble(packet.x);
        buf.writeDouble(packet.y);
        buf.writeDouble(packet.z);
        buf.writeVarInt(packet.size);
        buf.writeVarInt(packet.maxAge);
        buf.writeDouble(packet.yVel);
        buf.writeVarInt(packet.count);
    }

    public static FloatingSculkBubblePacket read(FriendlyByteBuf buf) {
        return new FloatingSculkBubblePacket(buf.readDouble(), buf.readDouble(), buf.readDouble(), buf.readVarInt(), buf.readVarInt(), buf.readDouble(), buf.readVarInt());
    }

    public static void handle(FloatingSculkBubblePacket packet, Supplier<NetworkEvent.Context> ctx) {
        Vec3 pos = new Vec3(packet.x, packet.y, packet.z);
        int count = packet.count;
        int size = packet.size;
        int maxAge = packet.maxAge;
        double yVel = packet.yVel;
        ctx.get().enqueueWork(() -> {
            Optional.ofNullable(Minecraft.getInstance().level).ifPresent(world -> {
                for (int i = 0; i < count; i++) {
                    world.addParticle(WWParticles.FLOATING_SCULK_BUBBLE.get(), pos.x, pos.y, pos.z, size, maxAge, yVel);
                }
            });
        });
        ctx.get().setPacketHandled(true);
    }

}
