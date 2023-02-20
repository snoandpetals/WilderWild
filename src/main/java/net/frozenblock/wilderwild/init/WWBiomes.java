package net.frozenblock.wilderwild.init;

import net.frozenblock.wilderwild.WilderWild;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.placement.MiscOverworldPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.Music;
import net.minecraft.sounds.Musics;
import net.minecraft.world.level.biome.AmbientMoodSettings;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static net.minecraft.data.worldgen.biome.OverworldBiomes.swamp;

public class WWBiomes {

    public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(Registry.BIOME_REGISTRY, WilderWild.MOD_ID);

    public static final RegistryObject<Biome> CYPRESS_WETLANDS = BIOMES.register("cypress_wetlands", WWBiomes::cypressWetlands);

    public static Biome cypressWetlands() {
        MobSpawnSettings.Builder builder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(builder);
        BiomeGenerationSettings.Builder builder2 = new BiomeGenerationSettings.Builder();
        BiomeDefaultFeatures.addDefaultCarversAndLakes(builder2);
        BiomeDefaultFeatures.addDefaultCrystalFormations(builder2);
        BiomeDefaultFeatures.addDefaultMonsterRoom(builder2);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(builder2);
        BiomeDefaultFeatures.addSurfaceFreezing(builder2);
        Music musicSound = Musics.createGameMusic(WWSoundEvents.MUSIC_OVERWORLD_WILD_FORESTS.get());
        return new Biome.BiomeBuilder()
                .precipitation(Biome.Precipitation.RAIN)
                .temperature(0.6F)
                .downfall(0.7F)
                .specialEffects(
                        new BiomeSpecialEffects.Builder()
                                .waterColor(4552818)
                                .waterFogColor(4552818)
                                .fogColor(12638463)
                                .skyColor(swamp().getSkyColor())
                                .foliageColorOverride(5877296)
                                .grassColorOverride(7979098)
                                .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                                .backgroundMusic(musicSound).build())
                .mobSpawnSettings(builder.build())
                .generationSettings(builder2.build())
                .build();
    }

}
