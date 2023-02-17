package net.frozenblock.wilderwild.network;

import net.minecraft.client.Minecraft;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkEvent;

import java.util.Optional;
import java.util.function.Supplier;

public class HiccupPacket {
    private final double x;
    private final double y;
    private final double z;

    public HiccupPacket(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public static void write(HiccupPacket packet, FriendlyByteBuf buf) {
        buf.writeDouble(packet.x);
        buf.writeDouble(packet.y);
        buf.writeDouble(packet.z);
    }

    public static HiccupPacket read(FriendlyByteBuf buf) {
        return new HiccupPacket(buf.readDouble(), buf.readDouble(), buf.readDouble());
    }

    public static void handle(HiccupPacket packet, Supplier<NetworkEvent.Context> ctx) {
        Vec3 pos = new Vec3(packet.x, packet.y, packet.z);
        ctx.get().enqueueWork(() -> {
            Optional.ofNullable(Minecraft.getInstance().level).ifPresent(world -> {
                int i = 5578058;
                boolean bl2 = world.random.nextBoolean();
                if (bl2) {
                    double d = (double) (i >> 16 & 255) / 255.0D;
                    double e = (double) (i >> 8 & 255) / 255.0D;
                    double f = (double) (i & 255) / 255.0D;
                    world.addParticle(ParticleTypes.ENTITY_EFFECT, pos.x, pos.y, pos.z, d, e, f);
                }
            });
        });
        ctx.get().setPacketHandled(true);
    }

}
