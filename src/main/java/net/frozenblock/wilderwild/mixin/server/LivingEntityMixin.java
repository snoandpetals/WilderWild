package net.frozenblock.wilderwild.mixin.server;

import io.netty.buffer.Unpooled;
import net.frozenblock.wilderwild.api.FrozenClientPacketInbetween;
import net.frozenblock.wilderwild.api.MovingLoopingFadingDistanceSoundEntityManager;
import net.frozenblock.wilderwild.api.MovingLoopingSoundEntityManager;
import net.frozenblock.wilderwild.api.PlayerDamageSourceSounds;
import net.frozenblock.wilderwild.init.WWItemTags;
import net.frozenblock.wilderwild.init.WWNetwork;
import net.frozenblock.wilderwild.network.HurtSoundPacket;
import net.frozenblock.wilderwild.util.NetworkUtil;
import net.frozenblock.wilderwild.util.interfaces.EntityLoopingFadingDistanceSoundInterface;
import net.frozenblock.wilderwild.util.interfaces.EntityLoopingSoundInterface;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.PacketDistributor;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin implements EntityLoopingSoundInterface, EntityLoopingFadingDistanceSoundInterface {
    @Shadow
    protected ItemStack useItem;
    @Shadow
    protected int useItemRemaining;
    @Unique
    public MovingLoopingSoundEntityManager frozenLib$loopingSoundManager;
    @Unique
    public MovingLoopingFadingDistanceSoundEntityManager frozenLib$loopingFadingDistanceSoundManager;
    @Unique
    public boolean frozenLib$clientFrozenSoundsSynced;

    public LivingEntityMixin() {
    }

    @Inject(
            method = {"<init>"},
            at = {@At("TAIL")}
    )
    private void setLoopingSoundManagers(EntityType<? extends LivingEntity> entityType, Level level, CallbackInfo info) {
        LivingEntity entity = LivingEntity.class.cast(this);
        this.frozenLib$loopingSoundManager = new MovingLoopingSoundEntityManager(entity);
        this.frozenLib$loopingFadingDistanceSoundManager = new MovingLoopingFadingDistanceSoundEntityManager(entity);
    }

    @Inject(
            method = {"startUsingItem"},
            at = {@At("HEAD")},
            cancellable = true
    )
    private void preventStartingGameEvent(InteractionHand hand, CallbackInfo info) {
        LivingEntity entity = LivingEntity.class.cast(this);
        ItemStack stack = entity.getItemInHand(hand);
        if (!stack.isEmpty() && !entity.isUsingItem() && stack.is(WWItemTags.NO_USE_GAME_EVENTS)) {
            info.cancel();
            this.useItem = stack;
            this.useItemRemaining = stack.getUseDuration();
            if (!entity.level.isClientSide) {
                this.setLivingEntityFlag(1, true);
                this.setLivingEntityFlag(2, hand == InteractionHand.OFF_HAND);
            }
        }

    }

    @Inject(
            method = {"stopUsingItem"},
            at = {@At("HEAD")},
            cancellable = true
    )
    public void preventStoppingGameEvent(CallbackInfo info) {
        LivingEntity entity = LivingEntity.class.cast(this);
        if (!entity.level.isClientSide) {
            ItemStack stack = entity.getUseItem();
            if (stack.is(WWItemTags.NO_USE_GAME_EVENTS)) {
                this.setLivingEntityFlag(1, false);
                info.cancel();
            }
        }

    }

    @Inject(
            method = {"addAdditionalSaveData"},
            at = {@At("TAIL")}
    )
    public void addLoopingSoundData(CompoundTag compoundTag, CallbackInfo info) {
        if (this.frozenLib$loopingSoundManager != null) {
            this.frozenLib$loopingSoundManager.save(compoundTag);
        }

        if (this.frozenLib$loopingFadingDistanceSoundManager != null) {
            this.frozenLib$loopingFadingDistanceSoundManager.save(compoundTag);
        }

    }

    @Inject(
            method = {"readAdditionalSaveData"},
            at = {@At("TAIL")}
    )
    public void readLoopingSoundData(CompoundTag compoundTag, CallbackInfo info) {
        this.frozenLib$loopingSoundManager.load(compoundTag);
        this.frozenLib$loopingFadingDistanceSoundManager.load(compoundTag);
    }

    @Inject(
            method = {"tick"},
            at = {@At("TAIL")}
    )
    public void tickSounds(CallbackInfo info) {
        LivingEntity entity = LivingEntity.class.cast(this);
        if (!entity.level.isClientSide) {
            this.frozenLib$loopingSoundManager.tick();
            this.frozenLib$loopingFadingDistanceSoundManager.tick();
        } else if (!this.frozenLib$clientFrozenSoundsSynced) {
            FrozenClientPacketInbetween.requestFrozenSoundSync(entity.getId(), entity.level.dimension());
            this.frozenLib$clientFrozenSoundsSynced = true;
        }

    }

    @Inject(
            method = {"hurt"},
            at = {@At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/Level;broadcastEntityEvent(Lnet/minecraft/world/entity/Entity;B)V",
                    ordinal = 2
            )},
            locals = LocalCapture.CAPTURE_FAILHARD
    )
    private void hurt(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir, float f, boolean bl, float g, boolean bl2, Entity entity2, byte event) {
        LivingEntity entity = LivingEntity.class.cast(this);
        if (entity instanceof Player && event == 2 && PlayerDamageSourceSounds.containsSource(source)) {
            FriendlyByteBuf byteBuf = new FriendlyByteBuf(Unpooled.buffer());
            byteBuf.writeVarInt(entity.getId());
            byteBuf.writeResourceLocation(PlayerDamageSourceSounds.getDamageID(source));
            byteBuf.writeFloat(this.getSoundVolume());

            for (ServerPlayer player : NetworkUtil.tracking((ServerLevel) entity.level, entity.blockPosition())) {
                WWNetwork.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new HurtSoundPacket(entity.getId(), PlayerDamageSourceSounds.getDamageID(source), this.getSoundVolume()));
            }
        }

    }

    @Redirect(
            method = {"handleEntityEvent"},
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/LivingEntity;getHurtSound(Lnet/minecraft/world/damagesource/DamageSource;)Lnet/minecraft/sounds/SoundEvent;"
            )
    )
    public SoundEvent stopHurtSoundIfTwo(LivingEntity par1, DamageSource par2, byte id) {
        return par1 instanceof Player && id == 2 && PlayerDamageSourceSounds.containsSource(par2) ? null : this.getHurtSound(par2);
    }

    @Shadow
    protected void setLivingEntityFlag(int mask, boolean value) {
        throw new UnsupportedOperationException("Mixin injection failed. - FrozenLib LivingEntityMixin.");
    }

    @Unique
    public boolean hasSyncedClient() {
        return this.frozenLib$clientFrozenSoundsSynced;
    }

    @Unique
    public MovingLoopingSoundEntityManager getSounds() {
        return this.frozenLib$loopingSoundManager;
    }

    @Unique
    public void addSound(ResourceLocation soundID, SoundSource category, float volume, float pitch, ResourceLocation restrictionId) {
        this.frozenLib$loopingSoundManager.addSound(soundID, category, volume, pitch, restrictionId);
    }

    @Unique
    public boolean hasSyncedFadingDistanceClient() {
        return this.frozenLib$clientFrozenSoundsSynced;
    }

    @Unique
    public MovingLoopingFadingDistanceSoundEntityManager getFadingDistanceSounds() {
        return this.frozenLib$loopingFadingDistanceSoundManager;
    }

    @Unique
    public void addFadingDistanceSound(ResourceLocation soundID, ResourceLocation sound2ID, SoundSource category, float volume, float pitch, ResourceLocation restrictionId, float fadeDist, float maxDist) {
        this.frozenLib$loopingFadingDistanceSoundManager.addSound(soundID, sound2ID, category, volume, pitch, restrictionId, fadeDist, maxDist);
    }

    @Shadow
    protected @Nullable SoundEvent getHurtSound(DamageSource damageSource) {
        throw new RuntimeException("Mixin injection failed - FrozenLib LivingEntityMixin");
    }

    @Shadow
    protected float getSoundVolume() {
        throw new RuntimeException("Mixin injection failed - FrozenLib LivingEntityMixin");
    }
}
