package net.frozenblock.wilderwild.api;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.Dynamic;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.frozenblock.wilderwild.WilderWild;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class MovingLoopingSoundEntityManager {
    private final ArrayList<SoundLoopData> sounds = new ArrayList();
    public LivingEntity entity;

    public MovingLoopingSoundEntityManager(LivingEntity entity) {
        this.entity = entity;
    }

    public void load(CompoundTag nbt) {
        if (nbt.contains("frozenSounds", 9)) {
            this.sounds.clear();
            DataResult<List<SoundLoopData>> var10000 = MovingLoopingSoundEntityManager.SoundLoopData.CODEC.listOf().parse(new Dynamic(NbtOps.INSTANCE, nbt.getList("frozenSounds", 10)));
            Logger var10001 = WilderWild.LOGGER4;
            Objects.requireNonNull(var10001);
            Objects.requireNonNull(var10001);
            Optional<List<SoundLoopData>> list = var10000.resultOrPartial(var10001::error);
            if (list.isPresent()) {
                List<SoundLoopData> allSounds = (List)list.get();
                this.sounds.addAll(allSounds);
            }
        }

    }

    public void save(CompoundTag nbt) {
        DataResult<Tag> var10000 = MovingLoopingSoundEntityManager.SoundLoopData.CODEC.listOf().encodeStart(NbtOps.INSTANCE, this.sounds);
        Logger var10001 = WilderWild.LOGGER4;
        Objects.requireNonNull(var10001);
        Objects.requireNonNull(var10001);
        var10000.resultOrPartial(var10001::error).ifPresent((cursorsNbt) -> {
            nbt.put("frozenSounds", cursorsNbt);
        });
    }

    public void addSound(ResourceLocation soundID, SoundSource category, float volume, float pitch, ResourceLocation restrictionId) {
        this.sounds.add(new SoundLoopData(soundID, category, volume, pitch, restrictionId));
    }

    public ArrayList<SoundLoopData> getSounds() {
        return this.sounds;
    }

    public void tick() {
        if (!this.sounds.isEmpty()) {
            ArrayList<SoundLoopData> soundsToRemove = new ArrayList();
            Iterator var2 = this.sounds.iterator();

            while(var2.hasNext()) {
                SoundLoopData nbt = (SoundLoopData)var2.next();
                SoundPredicate.LoopPredicate<LivingEntity> predicate = SoundPredicate.getPredicate(nbt.restrictionID);
                if (!predicate.test(this.entity)) {
                    soundsToRemove.add(nbt);
                    predicate.onStop(this.entity);
                }
            }

            this.sounds.removeAll(soundsToRemove);
        }

    }

    public static class SoundLoopData {
        public final ResourceLocation soundEventID;
        public final String categoryOrdinal;
        public final float volume;
        public final float pitch;
        public final ResourceLocation restrictionID;
        public static final Codec<SoundLoopData> CODEC = RecordCodecBuilder.create((instance) -> instance.group(ResourceLocation.CODEC.fieldOf("soundEventID").forGetter(SoundLoopData::getSoundEventID), Codec.STRING.fieldOf("categoryOrdinal").forGetter(SoundLoopData::getOrdinal), Codec.FLOAT.fieldOf("volume").forGetter(SoundLoopData::getVolume), Codec.FLOAT.fieldOf("pitch").forGetter(SoundLoopData::getPitch), ResourceLocation.CODEC.fieldOf("restrictionID").forGetter(SoundLoopData::getRestrictionID)).apply(instance, SoundLoopData::new));

        public SoundLoopData(ResourceLocation soundEventID, String ordinal, float vol, float pitch, ResourceLocation restrictionID) {
            this.soundEventID = soundEventID;
            this.categoryOrdinal = ordinal;
            this.volume = vol;
            this.pitch = pitch;
            this.restrictionID = restrictionID;
        }

        public SoundLoopData(ResourceLocation soundEventID, SoundSource category, float vol, float pitch, ResourceLocation restrictionID) {
            this.soundEventID = soundEventID;
            this.categoryOrdinal = category.toString();
            this.volume = vol;
            this.pitch = pitch;
            this.restrictionID = restrictionID;
        }

        public ResourceLocation getSoundEventID() {
            return this.soundEventID;
        }

        public String getOrdinal() {
            return this.categoryOrdinal;
        }

        public float getVolume() {
            return this.volume;
        }

        public float getPitch() {
            return this.pitch;
        }

        public ResourceLocation getRestrictionID() {
            return this.restrictionID;
        }
    }
}
