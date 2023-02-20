package net.frozenblock.wilderwild.network;

import com.google.common.graph.Network;
import net.frozenblock.wilderwild.api.MovingLoopingFadingDistanceSoundEntityManager;
import net.frozenblock.wilderwild.api.MovingLoopingSoundEntityManager;
import net.frozenblock.wilderwild.util.NetworkUtil;
import net.frozenblock.wilderwild.util.interfaces.EntityLoopingFadingDistanceSoundInterface;
import net.frozenblock.wilderwild.util.interfaces.EntityLoopingSoundInterface;
import net.minecraft.client.Minecraft;
import net.minecraft.core.Registry;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.server.ServerLifecycleHooks;

import java.util.Iterator;
import java.util.function.Supplier;

public class RequestLoopingSoundSyncPacket {
    private final int id;
    private final ResourceKey<Level> level;

    public RequestLoopingSoundSyncPacket(int id, ResourceKey<Level> level) {
        this.id = id;
        this.level = level;
    }

    public static void write(RequestLoopingSoundSyncPacket packet, FriendlyByteBuf buf) {
        buf.writeVarInt(packet.id);
        buf.writeResourceKey(packet.level);
    }

    public static RequestLoopingSoundSyncPacket read(FriendlyByteBuf buf) {
        return new RequestLoopingSoundSyncPacket(buf.readVarInt(), buf.readResourceKey(Registry.DIMENSION_REGISTRY));
    }

    public static void handle(RequestLoopingSoundSyncPacket packet, Supplier<NetworkEvent.Context> ctx) {
        int id = packet.id;
        Level dimension = ServerLifecycleHooks.getCurrentServer().getLevel(packet.level);
        ServerPlayer player = ctx.get().getSender();
        ctx.get().enqueueWork(() -> {
            if (dimension != null) {
                Entity entity = dimension.getEntity(id);
                if (entity instanceof LivingEntity livingEntity && livingEntity instanceof EntityLoopingSoundInterface entityLoopingSoundInterface) {
                    Iterator var5 = entityLoopingSoundInterface.getSounds().getSounds().iterator();

                    while(var5.hasNext()) {
                        MovingLoopingSoundEntityManager.SoundLoopData nbt = (MovingLoopingSoundEntityManager.SoundLoopData) var5.next();
                        NetworkUtil.createMovingRestrictionLoopingSound(player, entity, Registry.SOUND_EVENT.get(nbt.getSoundEventID()), SoundSource.valueOf(SoundSource.class, nbt.getOrdinal()), nbt.volume, nbt.pitch, nbt.restrictionID);
                    }

                    var5 = ((EntityLoopingFadingDistanceSoundInterface)livingEntity).getFadingDistanceSounds().getSounds().iterator();

                    while(var5.hasNext()) {
                        MovingLoopingFadingDistanceSoundEntityManager.FadingDistanceSoundLoopNBT nbtx = (MovingLoopingFadingDistanceSoundEntityManager.FadingDistanceSoundLoopNBT)var5.next();
                        NetworkUtil.createMovingRestrictionLoopingFadingDistanceSound(player, entity, (SoundEvent)Registry.SOUND_EVENT.get(nbtx.getSoundEventID()), (SoundEvent)Registry.SOUND_EVENT.get(nbtx.getSound2EventID()), (SoundSource)SoundSource.valueOf(SoundSource.class, nbtx.getOrdinal()), nbtx.volume, nbtx.pitch, nbtx.restrictionID, nbtx.fadeDist, nbtx.maxDist);
                    }
                }
            }
        });
        ctx.get().setPacketHandled(true);
    }

}
