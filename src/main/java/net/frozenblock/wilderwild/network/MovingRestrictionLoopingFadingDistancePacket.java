package net.frozenblock.wilderwild.network;

import net.frozenblock.wilderwild.api.RestrictedMovingFadingDistanceSwitchingSoundLoop;
import net.frozenblock.wilderwild.api.SoundPredicate;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.Registry;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class MovingRestrictionLoopingFadingDistancePacket {
    private final int id;
    private final SoundEvent sound;
    private final SoundEvent sound2;
    private final SoundSource category;
    private final float volume;
    private final float pitch;
    private final float fadeDist;
    private final float maxDist;
    private final ResourceLocation resourceLocation;

    public MovingRestrictionLoopingFadingDistancePacket(int id, SoundEvent sound, SoundEvent sound2, SoundSource category, float volume, float pitch, float fadeDist, float maxDist, ResourceLocation resourceLocation) {
        this.id = id;
        this.sound = sound;
        this.sound2 = sound2;
        this.category = category;
        this.volume = volume;
        this.pitch = pitch;
        this.fadeDist = fadeDist;
        this.maxDist = maxDist;
        this.resourceLocation = resourceLocation;
    }

    public static void write(MovingRestrictionLoopingFadingDistancePacket packet, FriendlyByteBuf buf) {
        buf.writeVarInt(packet.id);
        buf.writeId(Registry.SOUND_EVENT, packet.sound);
        buf.writeId(Registry.SOUND_EVENT, packet.sound2);
        buf.writeEnum(packet.category);
        buf.writeFloat(packet.volume);
        buf.writeFloat(packet.pitch);
        buf.writeFloat(packet.fadeDist);
        buf.writeFloat(packet.maxDist);
        buf.writeResourceLocation(packet.resourceLocation);
    }

    public static MovingRestrictionLoopingFadingDistancePacket read(FriendlyByteBuf buf) {
        return new MovingRestrictionLoopingFadingDistancePacket(buf.readVarInt(), buf.readById(Registry.SOUND_EVENT), buf.readById(Registry.SOUND_EVENT), buf.readEnum(SoundSource.class), buf.readFloat(), buf.readFloat(), buf.readFloat(), buf.readFloat(), buf.readResourceLocation());
    }

    public static void handle(MovingRestrictionLoopingFadingDistancePacket packet, Supplier<NetworkEvent.Context> ctx) {
        Minecraft minecraft = Minecraft.getInstance();
        ClientLevel level = minecraft.level;
        SoundEvent soundEvent = packet.sound;
        SoundEvent soundEvent1 = packet.sound2;
        SoundSource soundSource = packet.category;
        float volume = packet.volume;
        float pitch = packet.pitch;
        float fadeDist = packet.fadeDist;
        float maxDist = packet.maxDist;
        ctx.get().enqueueWork(() -> {
            if (level != null) {
                Entity entity = level.getEntity(packet.id);
                if (entity != null) {
                    SoundPredicate.LoopPredicate<?> predicate = SoundPredicate.getPredicate(packet.resourceLocation);
                    minecraft.getSoundManager().play(new RestrictedMovingFadingDistanceSwitchingSoundLoop(entity, soundEvent, soundSource, volume, pitch, predicate, fadeDist, maxDist, volume, false));
                    minecraft.getSoundManager().play(new RestrictedMovingFadingDistanceSwitchingSoundLoop(entity, soundEvent1, soundSource, volume, pitch, predicate, fadeDist, maxDist, volume, true));
                }
            }
        });
        ctx.get().setPacketHandled(true);
    }

}
