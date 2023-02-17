package net.frozenblock.wilderwild.network;

import net.frozenblock.wilderwild.init.WWParticles;
import net.minecraft.client.Minecraft;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class ControlledSeedPacket {
    private final double x;
    private final double y;
    private final double z;
    private final double velX;
    private final double velY;
    private final double velZ;
    private final int count;
    private final boolean milkweed;

    public ControlledSeedPacket(double x, double y, double z, double velX, double velY, double velZ, int count, boolean milkweed) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.velX = velX;
        this.velY = velY;
        this.velZ = velZ;
        this.count = count;
        this.milkweed = milkweed;
    }

    public static void write(ControlledSeedPacket packet, FriendlyByteBuf buf) {
        buf.writeDouble(packet.x);
        buf.writeDouble(packet.y);
        buf.writeDouble(packet.z);
        buf.writeDouble(packet.velX);
        buf.writeDouble(packet.velY);
        buf.writeDouble(packet.velZ);
        buf.writeVarInt(packet.count);
        buf.writeBoolean(packet.milkweed);
    }

    public static ControlledSeedPacket read(FriendlyByteBuf buf) {
        return new ControlledSeedPacket(buf.readDouble(), buf.readDouble(), buf.readDouble(), buf.readDouble(), buf.readDouble(), buf.readDouble(), buf.readVarInt(), buf.readBoolean());
    }

    public static void handle(ControlledSeedPacket packet, Supplier<NetworkEvent.Context> ctx) {
        Vec3 pos = new Vec3(packet.x, packet.y, packet.z);
        double velx = packet.velX;
        double vely = packet.velY;
        double velz = packet.velZ;
        int count = packet.count;
        ParticleOptions particle = packet.milkweed ? WWParticles.CONTROLLED_MILKWEED_SEED.get() : WWParticles.CONTROLLED_DANDELION_SEED.get();
        ctx.get().enqueueWork(() -> {
            if (Minecraft.getInstance().level == null)
                throw new IllegalStateException("why is your world null");
            for (int i = 0; i < count; i++) {
                Minecraft.getInstance().level.addParticle(particle, pos.x, pos.y, pos.z, velx, vely, velz);
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
