package net.frozenblock.wilderwild.api;

import net.frozenblock.wilderwild.WilderWild;
import net.frozenblock.wilderwild.init.WWRegistries;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import org.jetbrains.annotations.Nullable;

public final class SoundPredicate<T extends Entity> {
    public static final ResourceLocation DEFAULT_ID = WilderWild.id("default");
    public static final ResourceLocation NOT_SILENT_AND_ALIVE_ID = WilderWild.id("not_silent_and_alive");
    private final LoopPredicate<T> predicate;

    public static <T extends Entity> SoundPredicate<T> register(ResourceLocation id, LoopPredicate<T> predicate) {
        return Registry.register(WWRegistries.SOUND_PREDICATE, id, new SoundPredicate<>(predicate));
    }

    public SoundPredicate(LoopPredicate<T> predicate) {
        this.predicate = predicate;
    }

    public static <T extends Entity> LoopPredicate<T> getPredicate(@Nullable ResourceLocation id) {
        if (id != null) {
            SoundPredicate predicate;
            if (WWRegistries.SOUND_PREDICATE.containsKey(id)) {
                predicate = WWRegistries.SOUND_PREDICATE.get(id);
                if (predicate != null) {
                    return predicate.predicate;
                }
            }

            WilderWild.LOGGER.error("Unable to find sound predicate " + id + "! Using default sound predicate instead!");
        }

        return defaultPredicate();
    }

    public static <T extends Entity> LoopPredicate<T> defaultPredicate() {
        return (entity) -> !entity.isSilent();
    }

    public static <T extends Entity> LoopPredicate<T> notSilentAndAlive() {
        return (entity) -> !entity.isSilent();
    }

    public static void init() {
        register(DEFAULT_ID, defaultPredicate());
        register(NOT_SILENT_AND_ALIVE_ID, notSilentAndAlive());
    }

    @FunctionalInterface
    public interface LoopPredicate<T extends Entity> {
        boolean test(T var1);

        default void onStart(@Nullable T entity) {
        }

        default void onStop(@Nullable T entity) {
        }
    }
}
