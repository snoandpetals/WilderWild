package net.frozenblock.wilderwild.mixin.server.enderman_sound;

import net.frozenblock.lib.core.sound.FrozenSoundPackets;
import net.frozenblock.wilderwild.WilderWild;
import net.frozenblock.wilderwild.misc.ClientMethodInteractionHandler;
import net.frozenblock.wilderwild.registry.RegisterSounds;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EnderMan.class)
public abstract class EnderManMixin extends Monster {

    @Shadow
    private int lastStareSound;

    private EnderManMixin(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
    }

    @Inject(method = "playStareSound", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;playLocalSound(DDDLnet/minecraft/sounds/SoundEvent;Lnet/minecraft/sounds/SoundSource;FFZ)V"), cancellable = true)
    public void playStareSound(CallbackInfo ci) {
        //NOTE: This only runs on the client.
		if (this.level.isClientSide) {
			ClientMethodInteractionHandler.playClientEnderManSound(EnderMan.class.cast(this));
		}
		ci.cancel();
    }

    @Inject(method = "setTarget", at = @At("TAIL"))
    public void setTarget(@Nullable LivingEntity target, CallbackInfo ci) {
        if (target != null) {
            EnderMan enderMan = EnderMan.class.cast(this);
            if (!enderMan.level.isClientSide) {
                FrozenSoundPackets.createMovingRestrictionLoopingSound(enderMan.level, enderMan, RegisterSounds.ENTITY_ENDERMAN_ANGER_LOOP, SoundSource.HOSTILE, 0.1F, 0.9F, WilderWild.id("enderman_anger"));
            }
        }
    }

}
