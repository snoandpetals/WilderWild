package net.frozenblock.wilderwild.fabric.world.gen;

import dev.architectury.registry.level.biome.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
import net.frozenblock.wilderwild.fabric.WilderWildFabric;
import net.minecraft.world.level.biome.Biomes;

public final class WilderBiomeSettings {

    static void init() {
        BiomeModifications.replaceProperties((context) -> context.getKey().orElseThrow().equals(Biomes.DEEP_DARK.location()),
                (context, mutable) -> mutable.getEffectsProperties().setFogColor(0));

        WilderMusic.playMusic();
        WilderSpawns.addFireflies();
        WilderSpawns.addJellyfish();
    }
}
