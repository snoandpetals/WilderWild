package net.frozenblock.wilderwild.network;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.Registry;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class PlayLocalSoundPacket {
    private final double x;
    private final double y;
    private final double z;
    private final SoundEvent soundEvent;
    private final SoundSource source;
    private final float volume;
    private final float pitch;
    private final boolean distanceDelay;

    public PlayLocalSoundPacket(double x, double y, double z, SoundEvent soundEvent, SoundSource source, float volume, float pitch, boolean distanceDelay) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.soundEvent = soundEvent;
        this.source = source;
        this.volume = volume;
        this.pitch = pitch;
        this.distanceDelay = distanceDelay;
    }

    public static void write(PlayLocalSoundPacket packet, FriendlyByteBuf buf) {
        buf.writeDouble(packet.x);
        buf.writeDouble(packet.y);
        buf.writeDouble(packet.z);
        buf.writeId(Registry.SOUND_EVENT, packet.soundEvent);
        buf.writeEnum(packet.source);
        buf.writeFloat(packet.volume);
        buf.writeFloat(packet.pitch);
        buf.writeBoolean(packet.distanceDelay);
    }

    public static PlayLocalSoundPacket read(FriendlyByteBuf buf) {
        return new PlayLocalSoundPacket(buf.readDouble(), buf.readDouble(), buf.readDouble(), buf.readById(Registry.SOUND_EVENT), buf.readEnum(SoundSource.class), buf.readFloat(), buf.readFloat(), buf.readBoolean());
    }

    public static void handle(PlayLocalSoundPacket packet, Supplier<NetworkEvent.Context> ctx) {
        double x = packet.x;
        double y = packet.y;
        double z = packet.z;
        SoundEvent sound = packet.soundEvent;
        SoundSource source = packet.source;
        float volume = packet.volume;
        float pitch = packet.pitch;
        boolean distanceDelay = packet.distanceDelay;
        ctx.get().enqueueWork(() -> {
            ClientLevel level = Minecraft.getInstance().level;
            if (level != null) {
                level.playLocalSound(x, y, z, sound, source, volume, pitch, distanceDelay);
            }
        });
        ctx.get().setPacketHandled(true);
    }

}
