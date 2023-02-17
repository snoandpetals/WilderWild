package net.frozenblock.wilderwild.network;

import net.frozenblock.wilderwild.init.WWParticles;
import net.frozenblock.wilderwild.util.MathUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkEvent;

import java.util.Optional;
import java.util.function.Supplier;

public class TermiteParticlePacket {
    private final double x;
    private final double y;
    private final double z;
    private final int count;

    public TermiteParticlePacket(double x, double y, double z, int count) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.count = count;
    }

    public static void write(TermiteParticlePacket packet, FriendlyByteBuf buf) {
        buf.writeDouble(packet.x);
        buf.writeDouble(packet.y);
        buf.writeDouble(packet.z);
        buf.writeVarInt(packet.count);
    }

    public static TermiteParticlePacket read(FriendlyByteBuf buf) {
        return new TermiteParticlePacket(buf.readDouble(), buf.readDouble(), buf.readDouble(), buf.readVarInt());
    }

    public static void handle(TermiteParticlePacket packet, Supplier<NetworkEvent.Context> ctx) {
        Vec3 pos = new Vec3(packet.x, packet.y, packet.z);
        int count = packet.count;
        ctx.get().enqueueWork(() -> {
            Optional.ofNullable(Minecraft.getInstance().level).ifPresent(world -> {
                for (int i = 0; i < count; i++) {
                    world.addParticle(WWParticles.TERMITE.get(), pos.x, pos.y, pos.z, MathUtil.randomPosNeg() / 14, MathUtil.randomPosNeg() / 14, MathUtil.randomPosNeg() / 14);
                }
            });
        });
        ctx.get().setPacketHandled(true);
    }

}
