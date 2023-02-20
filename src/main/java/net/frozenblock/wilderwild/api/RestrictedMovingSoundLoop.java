package net.frozenblock.wilderwild.api;

import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RestrictedMovingSoundLoop<T extends Entity> extends RestrictedSoundInstance {
    private final T entity;
    private final SoundPredicate.LoopPredicate<T> predicate;

    public RestrictedMovingSoundLoop(T entity, SoundEvent sound, SoundSource category, float volume, float pitch, SoundPredicate.LoopPredicate<T> predicate) {
        super(sound, category, SoundInstance.createUnseededRandom());
        this.entity = entity;
        this.looping = true;
        this.delay = 0;
        this.volume = volume;
        this.pitch = pitch;
        this.x = (float)entity.getX();
        this.y = (float)entity.getY();
        this.z = (float)entity.getZ();
        this.predicate = predicate;
        this.predicate.onStart(this.entity);
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
        if (this.entity.isRemoved()) {
            this.stop();
        } else if (!this.predicate.test(this.entity)) {
            this.stop();
        } else {
            this.x = (float)this.entity.getX();
            this.y = (float)this.entity.getY();
            this.z = (float)this.entity.getZ();
        }

    }
}
