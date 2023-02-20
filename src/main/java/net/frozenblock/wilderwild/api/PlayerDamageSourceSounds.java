package net.frozenblock.wilderwild.api;

import net.frozenblock.wilderwild.WilderWild;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;

import java.util.HashMap;
import java.util.Map;

public final class PlayerDamageSourceSounds {
    private PlayerDamageSourceSounds() {
        throw new UnsupportedOperationException("PlayerDamageSourceSounds contains only static declarations.");
    }

    private static final Map<DamageSource, ResourceLocation> DAMAGE_SOURCE_RESOURCE_LOCATION_MAP = new HashMap<>();
    private static final Map<ResourceLocation, SoundEvent> RESOURCE_LOCATION_SOUND_EVENT_MAP = new HashMap<>();
    private static final ResourceLocation DEFAULT_ID = WilderWild.id("default_damage_source");

    public static void addDamageSound(DamageSource source, SoundEvent sound, ResourceLocation registry) {
        DAMAGE_SOURCE_RESOURCE_LOCATION_MAP.put(source, registry);
        RESOURCE_LOCATION_SOUND_EVENT_MAP.put(registry, sound);
    }

    public static SoundEvent getDamageSound(DamageSource source) {

        return DAMAGE_SOURCE_RESOURCE_LOCATION_MAP.containsKey(source) ? getDamageSound(DAMAGE_SOURCE_RESOURCE_LOCATION_MAP.get(source)) : SoundEvents.PLAYER_HURT;
    }

    public static SoundEvent getDamageSound(ResourceLocation location) {
        return RESOURCE_LOCATION_SOUND_EVENT_MAP.getOrDefault(location, SoundEvents.PLAYER_HURT);
    }

    public static ResourceLocation getDamageID(DamageSource source) {
        return DAMAGE_SOURCE_RESOURCE_LOCATION_MAP.getOrDefault(source, DEFAULT_ID);
    }

    public static boolean containsSource(DamageSource source) {
        return DAMAGE_SOURCE_RESOURCE_LOCATION_MAP.containsKey(source);
    }

    public static boolean containsSource(ResourceLocation location) {
        return RESOURCE_LOCATION_SOUND_EVENT_MAP.containsKey(location);
    }
}
