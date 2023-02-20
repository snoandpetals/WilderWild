package net.frozenblock.wilderwild.network;

import net.frozenblock.wilderwild.api.RestrictedMovingSoundLoop;
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

public class MoveRestrictionLoopingPacket {
    private final int id;
    private final SoundEvent soundEvent;
    private final SoundSource category;
    private final float volume;
    private final float pitch;
    private final ResourceLocation resourceLocation;

    public MoveRestrictionLoopingPacket(int id, SoundEvent sound, SoundSource category, float volume, float pitch, ResourceLocation resourceLocation) {
        this.id = id;
        this.soundEvent = sound;
        this.category = category;
        this.volume = volume;
        this.pitch = pitch;
        this.resourceLocation = resourceLocation;
    }

    public static void write(MoveRestrictionLoopingPacket packet, FriendlyByteBuf buf) {
        buf.writeVarInt(packet.id);
        buf.writeId(Registry.SOUND_EVENT, packet.soundEvent);
        buf.writeEnum(packet.category);
        buf.writeFloat(packet.volume);
        buf.writeFloat(packet.pitch);
        buf.writeResourceLocation(packet.resourceLocation);
    }

    public static MoveRestrictionLoopingPacket read(FriendlyByteBuf buf) {
        return new MoveRestrictionLoopingPacket(buf.readVarInt(), buf.readById(Registry.SOUND_EVENT), buf.readEnum(SoundSource.class), buf.readFloat(), buf.readFloat(), buf.readResourceLocation());
    }

    public static void handle(MoveRestrictionLoopingPacket packet, Supplier<NetworkEvent.Context> ctx) {
        int id = packet.id;
        SoundEvent sound = packet.soundEvent;
        SoundSource category = packet.category;
        float volume = packet.volume;
        float pitch = packet.pitch;
        ResourceLocation predicateId = packet.resourceLocation;
        ctx.get().enqueueWork(() -> {
            Minecraft client = Minecraft.getInstance();
            ClientLevel level = client.level;
            if (level != null) {
                Entity entity = level.getEntity(id);
                if (entity != null) {
                    SoundPredicate.LoopPredicate predicate = SoundPredicate.getPredicate(predicateId);
                    client.getSoundManager().play(new RestrictedMovingSoundLoop(entity, sound, category, volume, pitch, predicate));
                }
            }

        });
    }

}
