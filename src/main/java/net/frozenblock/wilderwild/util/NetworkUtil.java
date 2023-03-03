package net.frozenblock.wilderwild.util;

import io.netty.buffer.Unpooled;
import net.frozenblock.wilderwild.WilderWild;
import net.frozenblock.wilderwild.init.WWNetwork;
import net.frozenblock.wilderwild.network.ControlledSeedPacket;
import net.frozenblock.wilderwild.network.FloatingSculkBubblePacket;
import net.frozenblock.wilderwild.network.HiccupPacket;
import net.frozenblock.wilderwild.network.MoveRestrictionLoopingPacket;
import net.frozenblock.wilderwild.network.MovingRestrictionLoopingFadingDistancePacket;
import net.frozenblock.wilderwild.network.PlayLocalSoundPacket;
import net.frozenblock.wilderwild.network.SeedPacket;
import net.frozenblock.wilderwild.network.TermiteParticlePacket;
import net.frozenblock.wilderwild.util.interfaces.EntityLoopingSoundInterface;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.PacketDistributor;

import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;
import java.util.stream.Collectors;

public class NetworkUtil {

    public static void createMovingRestrictionSound(Level world, Entity entity, SoundEvent sound, SoundSource category, float volume, float pitch, ResourceLocation id) {
        if (!world.isClientSide) {
            FriendlyByteBuf byteBuf = new FriendlyByteBuf(Unpooled.buffer());
            byteBuf.writeVarInt(entity.getId());
            byteBuf.writeId(Registry.SOUND_EVENT, sound);
            byteBuf.writeEnum(category);
            byteBuf.writeFloat(volume);
            byteBuf.writeFloat(pitch);
            byteBuf.writeResourceLocation(id);

            for (ServerPlayer player : tracking((ServerLevel) world, entity.blockPosition())) {
                WWNetwork.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new MoveRestrictionLoopingPacket(entity.getId(), sound, category, volume, pitch, id));
            }
        }

    }

    public static void createMovingRestrictionLoopingSound(Level world, Entity entity, SoundEvent sound, SoundSource category, float volume, float pitch, ResourceLocation id) {
        if (!world.isClientSide) {
            for (ServerPlayer player : tracking((ServerLevel) world, entity.blockPosition())) {
                WWNetwork.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new MoveRestrictionLoopingPacket(entity.getId(), sound, category, volume, pitch, id));
            }
            if (entity instanceof LivingEntity living) {
                ((EntityLoopingSoundInterface)living).addSound(Registry.SOUND_EVENT.getKey(sound), category, volume, pitch, id);
            }
        }
    }

    public static void createMovingRestrictionLoopingSound(ServerPlayer player, Entity entity, SoundEvent sound, SoundSource category, float volume, float pitch, ResourceLocation id) {
        WWNetwork.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new MoveRestrictionLoopingPacket(entity.getId(), sound, category, volume, pitch, id));
    }

    public static void createMovingRestrictionLoopingFadingDistanceSound(ServerPlayer player, Entity entity, SoundEvent sound, SoundEvent sound2, SoundSource category, float volume, float pitch, ResourceLocation id, float fadeDist, float maxDist) {
        WWNetwork.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new MovingRestrictionLoopingFadingDistancePacket(entity.getId(), sound, sound2, category, volume, pitch, fadeDist, maxDist, id));
    }

    public static void sendControlledParticle(Level level, Vec3 pos, double xvel, double yvel, double zvel, int count, boolean isMilkweed, int radius) {
        if (level.isClientSide)
            throw new IllegalStateException("Particle attempting spawning on THE CLIENT JESUS CHRIST WHAT THE HECK SPAWN ON SERVER NEXT TIME PLS");
        for (ServerPlayer player : around((ServerLevel) level, pos, radius)) {
            WWNetwork.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new ControlledSeedPacket(pos.x, pos.y, pos.z, xvel * 1.5, yvel, zvel * 1.5, count, isMilkweed));
        }
    }

    public static void sendHiccupParticle(Level level, Vec3 pos) {
        if (level.isClientSide)
            throw new IllegalStateException("Particle attempting spawning on THE CLIENT JESUS CHRIST WHAT THE HECK SPAWN ON SERVER NEXT TIME STUPID IDIOT");
        for (ServerPlayer player : around((ServerLevel) level, pos, 32)) {
            WWNetwork.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new HiccupPacket(pos.x, pos.y, pos.z));
        }
    }

    public static void sendSeedParticlePacket(Level level, Vec3 pos, int count, boolean isMilkweed, int radius) {
        if (level.isClientSide)
            throw new IllegalStateException("Particle attempting spawning on THE CLIENT JESUS CHRIST WHAT THE HECK SPAWN ON SERVER NEXT TIME PLS");
        for (ServerPlayer player : around((ServerLevel) level, pos, 128)) {
            WWNetwork.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new SeedPacket(pos.x, pos.y, pos.z, count, isMilkweed));
        }
    }

    public static void sendParticles(Level level, Vec3 pos, int count) {
        if (level.isClientSide)
            throw new IllegalStateException("Particle attempting spawning on THE CLIENT JESUS CHRIST WHAT THE HECK SPAWN ON SERVER NEXT TIME PLS");
        for (ServerPlayer player : tracking((ServerLevel) level, new BlockPos(pos))) {
            WWNetwork.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new TermiteParticlePacket(pos.x, pos.y, pos.z, count));
        }
    }

    public static void sendSculkBubblesParticle(Level level, Vec3 pos, int size, int maxAge, double yVel, int count) {
        if (level.isClientSide)
            throw new IllegalStateException("Particle attempting spawning on THE CLIENT JESUS CHRIST WHAT THE HECK SPAWN ON SERVER NEXT TIME PLS");
        FriendlyByteBuf byteBuf = new FriendlyByteBuf(Unpooled.buffer());
        byteBuf.writeDouble(pos.x);
        byteBuf.writeDouble(pos.y);
        byteBuf.writeDouble(pos.z);
        byteBuf.writeVarInt(size);
        byteBuf.writeVarInt(maxAge);
        byteBuf.writeDouble(yVel);
        byteBuf.writeVarInt(count);
        for (ServerPlayer player : around((ServerLevel) level, pos, 32)) {
            WWNetwork.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new FloatingSculkBubblePacket(pos.x, pos.y, pos.z, size, maxAge, yVel, count));
        }
    }

    public static void createLocalSound(Level level, BlockPos pos, SoundEvent sound, SoundSource source, float volume, float pitch, boolean distanceDelay) {
        if (!level.isClientSide) {
            for (ServerPlayer player : tracking((ServerLevel) level, pos)) {
                WWNetwork.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new PlayLocalSoundPacket(pos.getX(), pos.getY(), pos.getZ(), sound, source, volume, pitch, distanceDelay));
            }
        }

    }

    public static Collection<ServerPlayer> around(ServerLevel world, Vec3 pos, double radius) {
        double radiusSq = radius * radius;

        return world.players()
                .stream()
                .filter((p) -> p.distanceToSqr(pos) <= radiusSq)
                .collect(Collectors.toList());
    }

    public static Collection<ServerPlayer> getPlayersInChunk(BlockEntity blockEntity) {
        if (!blockEntity.hasLevel() || blockEntity.getLevel().isClientSide()) {
            throw new IllegalArgumentException("Only supported on server worlds!");
        }
        return tracking((ServerLevel) blockEntity.getLevel(), new ChunkPos(blockEntity.getBlockPos()));
    }

    public static Collection<ServerPlayer> tracking(ServerLevel world, BlockPos pos) {
        Objects.requireNonNull(pos, "BlockPos cannot be null");

        return tracking(world, new ChunkPos(pos));
    }

    public static Collection<ServerPlayer> tracking(ServerLevel world, ChunkPos pos) {
        Objects.requireNonNull(world, "The world cannot be null");
        Objects.requireNonNull(pos, "The chunk pos cannot be null");

        return world.getChunkSource().chunkMap.getPlayers(pos, false);
    }

}
