package net.frozenblock.wilderwild.network;

import net.frozenblock.wilderwild.api.PlayerDamageSourceSounds;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class HurtSoundPacket {
    private final int id;
    private final ResourceLocation damageId;
    private final float soundVolume;

    public HurtSoundPacket(int id, ResourceLocation damageId, float soundVolume) {
        this.id = id;
        this.damageId = damageId;
        this.soundVolume = soundVolume;
    }

    public static void write(HurtSoundPacket packet, FriendlyByteBuf buf) {
        buf.writeVarInt(packet.id);
        buf.writeResourceLocation(packet.damageId);
        buf.writeFloat(packet.soundVolume);
    }

    public static HurtSoundPacket read(FriendlyByteBuf buf) {
        return new HurtSoundPacket(buf.readVarInt(), buf.readResourceLocation(), buf.readFloat());
    }

    public static void handle(HurtSoundPacket packet, Supplier<NetworkEvent.Context> ctx) {
        int id = packet.id;
        ResourceLocation damageLocation = packet.damageId;
        float volume = packet.soundVolume;
        ctx.get().enqueueWork(() -> {
            Minecraft client = Minecraft.getInstance();
            ClientLevel level = client.level;
            if (level != null) {
                Entity entity = level.getEntity(id);
                if (entity instanceof Player player) {
                    SoundEvent soundEvent = PlayerDamageSourceSounds.getDamageSound(damageLocation);
                    player.playSound(soundEvent, volume, (player.getRandom().nextFloat() - player.getRandom().nextFloat()) * 0.2F + 1.0F);
                }
            }
        });
        ctx.get().setPacketHandled(true);
    }

}
