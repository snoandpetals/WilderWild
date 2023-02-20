package net.frozenblock.wilderwild.api;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RestrictedMovingFadingDistanceSwitchingSoundLoop<T extends Entity> extends RestrictedSoundInstance {
    private final T entity;
    private final SoundPredicate.LoopPredicate<T> predicate;
    private final boolean isFarSound;
    private final double maxDist;
    private final double fadeDist;
    private final float maxVol;

    public RestrictedMovingFadingDistanceSwitchingSoundLoop(T entity, SoundEvent sound, SoundSource category, float volume, float pitch, SoundPredicate.LoopPredicate<T> predicate, double fadeDist, double maxDist, float maxVol, boolean isFarSound) {
        super(sound, category, SoundInstance.createUnseededRandom());
        this.entity = entity;
        this.looping = true;
        this.delay = 0;
        this.volume = volume;
        this.pitch = pitch;
        this.x = (double)((float)entity.getX());
        this.y = (double)((float)entity.getY());
        this.z = (double)((float)entity.getZ());
        this.predicate = predicate;
        this.isFarSound = isFarSound;
        this.maxDist = maxDist;
        this.fadeDist = fadeDist;
        this.maxVol = maxVol;
    }

    public boolean canPlaySound() {
        return !this.entity.isSilent();
    }

    public boolean canStartSilent() {
        return true;
    }

    public void stop() {
        this.predicate.onStop(this.entity);
        super.stop();
    }

    public void tick() {
        Minecraft client = Minecraft.getInstance();
        if (this.entity.isRemoved()) {
            this.stop();
        } else if (!this.predicate.test(this.entity)) {
            this.stop();
        } else {
            this.x = (float)this.entity.getX();
            this.y = (float)this.entity.getY();
            this.z = (float)this.entity.getZ();
            if (client.player != null) {
                float distance = client.player.distanceTo(this.entity);
                if ((double)distance < this.fadeDist) {
                    this.volume = !this.isFarSound ? this.maxVol : 0.001F;
                } else if ((double)distance > this.maxDist) {
                    this.volume = this.isFarSound ? this.maxVol : 0.001F;
                } else {
                    float fadeProgress = (float)((this.maxDist - (double)distance) / (this.maxDist - this.fadeDist));
                    this.volume = this.isFarSound ? (1.0F - fadeProgress) * this.maxVol : fadeProgress * this.maxVol;
                }
            }
        }

    }
}

