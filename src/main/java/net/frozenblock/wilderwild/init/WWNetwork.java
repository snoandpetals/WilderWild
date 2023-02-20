package net.frozenblock.wilderwild.init;

import net.frozenblock.wilderwild.WilderWild;
import net.frozenblock.wilderwild.network.ControlledSeedPacket;
import net.frozenblock.wilderwild.network.FloatingSculkBubblePacket;
import net.frozenblock.wilderwild.network.HiccupPacket;
import net.frozenblock.wilderwild.network.HurtSoundPacket;
import net.frozenblock.wilderwild.network.MoveRestrictionLoopingPacket;
import net.frozenblock.wilderwild.network.MovingRestrictionLoopingFadingDistancePacket;
import net.frozenblock.wilderwild.network.PlayLocalSoundPacket;
import net.frozenblock.wilderwild.network.RequestLoopingSoundSyncPacket;
import net.frozenblock.wilderwild.network.SeedPacket;
import net.frozenblock.wilderwild.network.TermiteParticlePacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

import java.util.Optional;

public class WWNetwork {
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel INSTANCE = NetworkRegistry.ChannelBuilder.named(
                    new ResourceLocation(WilderWild.MOD_ID, "network"))
            .clientAcceptedVersions(PROTOCOL_VERSION::equals)
            .serverAcceptedVersions(PROTOCOL_VERSION::equals)
            .networkProtocolVersion(() -> PROTOCOL_VERSION)
            .simpleChannel();

    protected static int packetID = 0;

    public WWNetwork() {
    }

    public static void init() {
        INSTANCE.registerMessage(getPacketID(), TermiteParticlePacket.class, TermiteParticlePacket::write, TermiteParticlePacket::read, TermiteParticlePacket::handle, Optional.of(NetworkDirection.PLAY_TO_CLIENT));
        INSTANCE.registerMessage(getPacketID(), FloatingSculkBubblePacket.class, FloatingSculkBubblePacket::write, FloatingSculkBubblePacket::read, FloatingSculkBubblePacket::handle, Optional.of(NetworkDirection.PLAY_TO_CLIENT));
        INSTANCE.registerMessage(getPacketID(), PlayLocalSoundPacket.class, PlayLocalSoundPacket::write, PlayLocalSoundPacket::read, PlayLocalSoundPacket::handle, Optional.of(NetworkDirection.PLAY_TO_CLIENT));
        INSTANCE.registerMessage(getPacketID(), SeedPacket.class, SeedPacket::write, SeedPacket::read, SeedPacket::handle, Optional.of(NetworkDirection.PLAY_TO_CLIENT));
        INSTANCE.registerMessage(getPacketID(), HiccupPacket.class, HiccupPacket::write, HiccupPacket::read, HiccupPacket::handle, Optional.of(NetworkDirection.PLAY_TO_CLIENT));
        INSTANCE.registerMessage(getPacketID(), ControlledSeedPacket.class, ControlledSeedPacket::write, ControlledSeedPacket::read, ControlledSeedPacket::handle, Optional.of(NetworkDirection.PLAY_TO_CLIENT));
        INSTANCE.registerMessage(getPacketID(), MoveRestrictionLoopingPacket.class, MoveRestrictionLoopingPacket::write, MoveRestrictionLoopingPacket::read, MoveRestrictionLoopingPacket::handle, Optional.of(NetworkDirection.PLAY_TO_CLIENT));
        INSTANCE.registerMessage(getPacketID(), HurtSoundPacket.class, HurtSoundPacket::write, HurtSoundPacket::read, HurtSoundPacket::handle, Optional.of(NetworkDirection.PLAY_TO_CLIENT));
        INSTANCE.registerMessage(getPacketID(), RequestLoopingSoundSyncPacket.class, RequestLoopingSoundSyncPacket::write, RequestLoopingSoundSyncPacket::read, RequestLoopingSoundSyncPacket::handle, Optional.of(NetworkDirection.PLAY_TO_SERVER));
        INSTANCE.registerMessage(getPacketID(), MovingRestrictionLoopingFadingDistancePacket.class, MovingRestrictionLoopingFadingDistancePacket::write, MovingRestrictionLoopingFadingDistancePacket::read, MovingRestrictionLoopingFadingDistancePacket::handle, Optional.of(NetworkDirection.PLAY_TO_CLIENT));
    }

    public static int getPacketID() {
        return packetID++;
    }
}
