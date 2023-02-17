package net.frozenblock.wilderwild.network;

import net.frozenblock.wilderwild.init.WWParticles;
import net.minecraft.client.Minecraft;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkEvent;

import java.util.Optional;
import java.util.function.Supplier;

public class SeedPacket {
    private final double x;
    private final double y;
    private final double z;
    private final int count;
    private final boolean milkweed;

    public SeedPacket(double x, double y, double z, int count, boolean milkweed) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.count = count;
        this.milkweed = milkweed;
    }

    public static void write(SeedPacket packet, FriendlyByteBuf buf) {
        buf.writeDouble(packet.x);
        buf.writeDouble(packet.y);
        buf.writeDouble(packet.z);
        buf.writeVarInt(packet.count);
        buf.writeBoolean(packet.milkweed);
    }

    public static SeedPacket read(FriendlyByteBuf buf) {
        return new SeedPacket(buf.readDouble(), buf.readDouble(), buf.readDouble(), buf.readVarInt(), buf.readBoolean());
    }

    public static void handle(SeedPacket packet, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            Vec3 pos = new Vec3(packet.x, packet.y, packet.z);
            int count = packet.count;
            ParticleOptions particle = packet.milkweed ? WWParticles.MILKWEED_SEED.get() : WWParticles.DANDELION_SEED.get();
            Optional.ofNullable(Minecraft.getInstance().level).ifPresent(world -> {
                for (int i = 0; i < count; i++) {
                    world.addParticle(particle, pos.x, pos.y, pos.z, 0, 0, 0);
                }
            });
        });
        ctx.get().setPacketHandled(true);
    }

}
