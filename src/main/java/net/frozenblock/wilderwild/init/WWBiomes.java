package net.frozenblock.wilderwild.init;

import net.frozenblock.wilderwild.WilderWild;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.biome.OverworldBiomes;
import net.minecraft.sounds.Music;
import net.minecraft.sounds.Musics;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.AmbientAdditionsSettings;
import net.minecraft.world.level.biome.AmbientMoodSettings;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import static net.minecraft.data.worldgen.biome.OverworldBiomes.jungle;

public class WWBiomes {

    public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(Registry.BIOME_REGISTRY, WilderWild.MOD_ID);

    public static final RegistryObject<Biome> CYPRESS_WETLANDS = BIOMES.register("cypress_wetlands", WWBiomes::cypressWetlands);
    public static final RegistryObject<Biome> JELLYFISH_CAVES = BIOMES.register("jellyfish_caves", WWBiomes::jellyfishCaves);
    public static final RegistryObject<Biome> MIXED_FOREST = BIOMES.register("mixed_forest", WWBiomes::mixedForest);

    public static Biome mixedForest() {
        MobSpawnSettings.Builder builder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(builder);
        BiomeDefaultFeatures.plainsSpawns(builder);
        builder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.WOLF, 5, 4, 4));
        BiomeGenerationSettings.Builder builder2 = new BiomeGenerationSettings.Builder();
        BiomeDefaultFeatures.addDefaultCarversAndLakes(builder2);
        BiomeDefaultFeatures.addDefaultCrystalFormations(builder2);
        BiomeDefaultFeatures.addDefaultMonsterRoom(builder2);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(builder2);
        BiomeDefaultFeatures.addSurfaceFreezing(builder2);
        BiomeDefaultFeatures.addDefaultSprings(builder2);
        BiomeDefaultFeatures.addForestFlowers(builder2);
        BiomeDefaultFeatures.addForestGrass(builder2);
        BiomeDefaultFeatures.addDefaultOres(builder2);
        BiomeDefaultFeatures.addDefaultSoftDisks(builder2);
        Music musicSound = Musics.createGameMusic(WWSoundEvents.MUSIC_OVERWORLD_WILD_FORESTS.get());
        return new Biome.BiomeBuilder()
                .precipitation(Biome.Precipitation.RAIN)
                .temperature(0.7F)
                .downfall(0.7F)
                .specialEffects(
                        new BiomeSpecialEffects.Builder()
                                .waterColor(4159204)
                                .waterFogColor(329011)
                                .fogColor(12638463)
                                .skyColor(jungle().getSkyColor())
                                .foliageColorOverride(5879634)
                                .grassColorOverride(8437607)
                                .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                                .backgroundMusic(musicSound).build())
                .mobSpawnSettings(builder.build())
                .generationSettings(builder2.build())
                .build();
    }

    public static Biome jellyfishCaves() {
        MobSpawnSettings.Builder builder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(builder);
        BiomeGenerationSettings.Builder builder2 = new BiomeGenerationSettings.Builder();
        BiomeDefaultFeatures.addDefaultCrystalFormations(builder2);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(builder2);
        BiomeDefaultFeatures.addSurfaceFreezing(builder2);
        BiomeDefaultFeatures.addPlainGrass(builder2);
        BiomeDefaultFeatures.addDefaultOres(builder2, true);
        BiomeDefaultFeatures.addDefaultSoftDisks(builder2);
        BiomeDefaultFeatures.addPlainVegetation(builder2);
        BiomeDefaultFeatures.addDefaultMushrooms(builder2);
        BiomeDefaultFeatures.addDefaultExtraVegetation(builder2);
        BiomeDefaultFeatures.addDefaultCarversAndLakes(builder2);
        Music music = Musics.createGameMusic(WWSoundEvents.MUSIC_OVERWORLD_JELLYFISH_CAVES.get());
        return new Biome.BiomeBuilder()
                .precipitation(Biome.Precipitation.RAIN)
                .temperature(0.8F)
                .downfall(0.4F)
                .specialEffects(
                        new BiomeSpecialEffects.Builder()
                                .waterColor(9817343)
                                .waterFogColor(6069471)
                                .fogColor(0)
                                .skyColor(calculateSkyColor(0.8F))
                                .ambientLoopSound(WWSoundEvents.AMBIENT_JELLYFISH_CAVES_LOOP.get())
                                .ambientAdditionsSound(new AmbientAdditionsSettings(WWSoundEvents.AMBIENT_JELLYFISH_CAVES_ADDITIONS.get(), 0.0005D))
                                .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                                .backgroundMusic(music).build())
                .mobSpawnSettings(builder.build())
                .generationSettings(builder2.build())
                .build();
    }

    public static Biome cypressWetlands() {
        MobSpawnSettings.Builder builder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(builder);
        BiomeGenerationSettings.Builder builder2 = new BiomeGenerationSettings.Builder();
        BiomeDefaultFeatures.addDefaultCarversAndLakes(builder2);
        BiomeDefaultFeatures.addDefaultCrystalFormations(builder2);
        BiomeDefaultFeatures.addDefaultMonsterRoom(builder2);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(builder2);
        BiomeDefaultFeatures.addSurfaceFreezing(builder2);
        BiomeDefaultFeatures.addSwampExtraVegetation(builder2);
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
                                .skyColor(OverworldBiomes.swamp().getSkyColor())
                                .foliageColorOverride(5877296)
                                .grassColorOverride(7979098)
                                .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                                .backgroundMusic(musicSound).build())
                .mobSpawnSettings(builder.build())
                .generationSettings(builder2.build())
                .build();
    }

    protected static int calculateSkyColor(float p_194844_) {
        float $$1 = p_194844_ / 3.0F;
        $$1 = Mth.clamp($$1, -1.0F, 1.0F);
        return Mth.hsvToRgb(0.62222224F - $$1 * 0.05F, 0.5F + $$1 * 0.1F, 1.0F);
    }

}
