package net.frozenblock.wilderwild.datagen;

import com.google.common.collect.Maps;
import com.mojang.serialization.JsonOps;
import net.frozenblock.wilderwild.WilderWild;
import net.frozenblock.wilderwild.init.WWBiomeTags;
import net.frozenblock.wilderwild.init.WWBiomes;
import net.frozenblock.wilderwild.init.WWEntityTypes;
import net.frozenblock.wilderwild.world.feature.WilderMiscPlaced;
import net.frozenblock.wilderwild.world.feature.WilderPlacedFeatures;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.worldgen.placement.CavePlacements;
import net.minecraft.data.worldgen.placement.MiscOverworldPlacements;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.RegistryOps;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.JsonCodecProvider;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WilderWildBiomeModifierProvider {
    private static final RegistryAccess ACCESS = RegistryAccess.builtinCopy();
    private static final Registry<Biome> BIOME_REGISTRY = ACCESS.registryOrThrow(Registry.BIOME_REGISTRY);
    private static final Registry<PlacedFeature> PLACED_FEATURES = ACCESS.registryOrThrow(Registry.PLACED_FEATURE_REGISTRY);
    private static final HashMap<ResourceLocation, BiomeModifier> MODIFIERS = Maps.newHashMap();

    //TODO: Add stone pools and deepslate pools
    public static JsonCodecProvider<BiomeModifier> bootstrap(DataGenerator dataGenerator, ExistingFileHelper existingFileHelper) {
        addReplacements();
        addFlowers();
        addWildGrass();
        addTrees();
        addMushrooms();
        addMiscFeatures();

        addModdedBiomesFeatures();
        addModdedSpawns();

        addFeature("add_pollen", WWBiomeTags.HAS_POLLEN, GenerationStep.Decoration.VEGETAL_DECORATION, WilderPlacedFeatures.POLLEN_PLACED);

        return JsonCodecProvider.forDatapackRegistry(dataGenerator, existingFileHelper, WilderWild.MOD_ID, RegistryOps.create(JsonOps.INSTANCE, ACCESS), ForgeRegistries.Keys.BIOME_MODIFIERS, MODIFIERS);
    }

    private static void addModdedSpawns() {
        addDefaultSpawns();
        addCypressWetlandsSpawns();
        addMixedForestSpawns();
    }

    private static void addDefaultSpawns() {
        addSpawn("add_jellyfish", WWBiomeTags.HAS_JELLYFISH, new MobSpawnSettings.SpawnerData(WWEntityTypes.JELLYFISH.get(), 2, 1, 1));
        addSpawn("add_daylight_fireflies", WWBiomeTags.FIREFLY_SPAWNABLE_DURING_DAY, new MobSpawnSettings.SpawnerData(WWEntityTypes.FIREFLY.get(), 12, 2, 4));
        addSpawn("add_underground_fireflies", WWBiomeTags.FIREFLY_SPAWNABLE_CAVE, new MobSpawnSettings.SpawnerData(WWEntityTypes.FIREFLY.get(), 5, 2, 4));
        addSpawn("add_fireflies", WWBiomeTags.FIREFLY_SPAWNABLE, new MobSpawnSettings.SpawnerData(WWEntityTypes.FIREFLY.get(), 5, 1, 2));
    }

    private static void addModdedBiomesFeatures() {
        addCypressWetlandsFeatures();
        addJellyfishCavesFeatures();
        addMixedForestFeatures();
    }

    private static void addMixedForestSpawns() {
        addSpawn("add_mixed_forest_spawns", WWBiomes.MIXED_FOREST, new MobSpawnSettings.SpawnerData(EntityType.WOLF, 5, 4, 4));
    }

    private static void addCypressWetlandsSpawns() {
        addSpawn("add_cypress_wetlands_spawns", WWBiomes.CYPRESS_WETLANDS, new MobSpawnSettings.SpawnerData(EntityType.COD, 5, 2, 6), new MobSpawnSettings.SpawnerData(EntityType.FROG, 12, 4, 5), new MobSpawnSettings.SpawnerData(EntityType.PIG, 3, 2, 4), new MobSpawnSettings.SpawnerData(EntityType.CHICKEN, 4, 2, 4), new MobSpawnSettings.SpawnerData(EntityType.COW, 6, 4, 4), new MobSpawnSettings.SpawnerData(EntityType.RABBIT, 10, 4, 4), new MobSpawnSettings.SpawnerData(WWEntityTypes.FIREFLY.get(), 1, 2, 6));
    }

    private static void addMixedForestFeatures() {
        addFeature("add_mixed_forest_vegetal_features", WWBiomes.MIXED_FOREST, GenerationStep.Decoration.VEGETAL_DECORATION, WilderPlacedFeatures.SEEDING_DANDELION_MIXED, WilderPlacedFeatures.MIXED_MUSHROOMS_PLACED, WilderPlacedFeatures.MIXED_TREES, WilderPlacedFeatures.FALLEN_TREES_MIXED_PLACED);
        addFeature("add_mixed_forest_top_layer_modifications", WWBiomes.MIXED_FOREST, GenerationStep.Decoration.TOP_LAYER_MODIFICATION, WilderMiscPlaced.COARSE_PATH_5);
    }

    private static void addCypressWetlandsFeatures() {
        addFeature("add_cypress_wetlands_vegetal_features", WWBiomes.CYPRESS_WETLANDS, GenerationStep.Decoration.VEGETAL_DECORATION, WilderPlacedFeatures.DENSE_FERN_PLACED, WilderPlacedFeatures.DENSE_TALL_GRASS_PLACED, WilderPlacedFeatures.SEAGRASS_CYPRESS, WilderPlacedFeatures.SEEDING_DANDELION_CYPRESS, WilderPlacedFeatures.MILKWEED_CYPRESS, WilderPlacedFeatures.FLOWER_FOREST_FLOWERS, WilderPlacedFeatures.CYPRESS_WETLANDS_TREES, WilderPlacedFeatures.CYPRESS_WETLANDS_TREES_WATER, WilderPlacedFeatures.FALLEN_OAK_AND_CYPRESS_PLACED);
        addFeature("add_cypress_wetlands_paths", WWBiomes.CYPRESS_WETLANDS, GenerationStep.Decoration.UNDERGROUND_ORES, WilderMiscPlaced.UNDER_WATER_SAND_PATH, WilderMiscPlaced.UNDER_WATER_GRAVEL_PATH, WilderMiscPlaced.UNDER_WATER_CLAY_PATH);
//        addFeature("add_cypress_wetlands_springs", WWBiomes.CYPRESS_WETLANDS, GenerationStep.Decoration.FLUID_SPRINGS, MiscOverworldPlacements.SPRING_WATER);
    }

    private static void addJellyfishCavesFeatures() {
        addFeature("add_jellyfish_caves_vegetal_features", WWBiomes.JELLYFISH_CAVES, GenerationStep.Decoration.VEGETAL_DECORATION, WilderPlacedFeatures.NEMATOCYST, WilderPlacedFeatures.NEMATOCYST_PURPLE);
        addFeature("add_jellyfish_caves_udnerground_decorations", WWBiomes.JELLYFISH_CAVES, GenerationStep.Decoration.VEGETAL_DECORATION, WilderPlacedFeatures.JELLYFISH_CAVES_BLUE_MESOGLEA, WilderPlacedFeatures.JELLYFISH_CAVES_PURPLE_MESOGLEA, WilderPlacedFeatures.JELLYFISH_CAVES_UPSIDE_DOWN_BLUE_MESOGLEA, WilderPlacedFeatures.JELLYFISH_CAVES_UPSIDE_DOWN_PURPLE_MESOGLEA, WilderMiscPlaced.MESOGLEA_PILLAR, WilderMiscPlaced.PURPLE_MESOGLEA_PILLAR);
        addFeature("add_jellyfish_caves_underground_ores", WWBiomes.JELLYFISH_CAVES, GenerationStep.Decoration.UNDERGROUND_ORES, WilderMiscPlaced.ORE_CALCITE);
        addFeature("add_jellyfish_caves_underground_structures", WWBiomes.JELLYFISH_CAVES, GenerationStep.Decoration.UNDERGROUND_STRUCTURES, WilderMiscPlaced.JELLYFISH_DEEPSLATE_POOL, WilderMiscPlaced.JELLYFISH_STONE_POOL);
    }

    private static void addMiscFeatures() {
        addFeature("add_decorative_mud", WWBiomeTags.HAS_DECORATIVE_MUD, GenerationStep.Decoration.TOP_LAYER_MODIFICATION, WilderMiscPlaced.DISK_MUD, WilderMiscPlaced.MUD_PATH);
        addFeature("add_taiga_forest_rocks", WWBiomeTags.HAS_TAIGA_FOREST_ROCK, GenerationStep.Decoration.TOP_LAYER_MODIFICATION, WilderMiscPlaced.FOREST_ROCK_TAIGA);
        addFeature("add_coarse_dirt_path", WWBiomeTags.HAS_COARSE_DIRT_PATH, GenerationStep.Decoration.TOP_LAYER_MODIFICATION, WilderMiscPlaced.COARSE_PATH);
        addFeature("add_moss_path", WWBiomeTags.HAS_MOSS_PATH, GenerationStep.Decoration.TOP_LAYER_MODIFICATION, WilderMiscPlaced.MOSS_PATH);
        addFeature("add_packed_mud_path", WWBiomeTags.HAS_PACKED_MUD_ORE, GenerationStep.Decoration.TOP_LAYER_MODIFICATION, WilderMiscPlaced.PACKED_MUD_PATH);
        addFeature("add_termite", BiomeTags.IS_SAVANNA, GenerationStep.Decoration.VEGETAL_DECORATION, WilderPlacedFeatures.TERMITE_PLACED);
        addFeature("add_packed_mud_ore", WWBiomeTags.HAS_PACKED_MUD_ORE, GenerationStep.Decoration.UNDERGROUND_ORES, WilderMiscPlaced.ORE_PACKED_MUD);
        addFeature("add_under_water_clay_path", WWBiomeTags.HAS_CLAY_PATH, GenerationStep.Decoration.UNDERGROUND_ORES, WilderMiscPlaced.UNDER_WATER_CLAY_PATH_BEACH);
    }

    private static void addReplacements() {
        addMultipleFeatures("forest_grass", WWBiomeTags.FOREST_GRASS, GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_GRASS_FOREST, WilderPlacedFeatures.GRASS_PLACED, WilderPlacedFeatures.TALL_GRASS);
        replaceFeature("birch_trees", WWBiomeTags.HAS_BIRCH, GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.TREES_BIRCH, WilderPlacedFeatures.TREES_BIRCH);
        replaceFeature("tall_birch_trees", WWBiomeTags.HAS_TALL_BIRCH, GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.BIRCH_TALL, WilderPlacedFeatures.BIRCH_TALL);
        replaceMultipleFeatures("flower_forest_trees", WWBiomeTags.HAS_FLOWER_FOREST_TREES, GenerationStep.Decoration.VEGETAL_DECORATION, WilderPlacedFeatures.TREES_FLOWER_FOREST, VegetationPlacements.TREES_BIRCH_AND_OAK, VegetationPlacements.TREES_FLOWER_FOREST);
        replaceFeature("plains_trees", WWBiomeTags.NON_FROZEN_PLAINS, GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.TREES_PLAINS, WilderPlacedFeatures.TREES_PLAINS);
        replaceFeature("swamp_trees", WWBiomeTags.SWAMP_TREES, GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.TREES_SWAMP, WilderPlacedFeatures.TREES_SWAMP);
        replaceFeature("taiga_trees", WWBiomeTags.SHORT_TAIGA, GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.TREES_TAIGA, WilderPlacedFeatures.SPRUCE_PLACED);
        replaceFeature("old_growth_pine_taiga_trees", WWBiomeTags.TALL_PINE_TAIGA, GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.TREES_OLD_GROWTH_PINE_TAIGA, WilderPlacedFeatures.TREES_OLD_GROWTH_PINE_TAIGA1);
        replaceFeature("old_growth_spruce_taiga_trees", WWBiomeTags.TALL_SPRUCE_TAIGA, GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.TREES_OLD_GROWTH_SPRUCE_TAIGA, WilderPlacedFeatures.TREES_OLD_GROWTH_SPRUCE_TAIGA1);
        replaceFeature("grove_trees", WWBiomeTags.GROVE, GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.TREES_GROVE, WilderPlacedFeatures.TREES_GROVE);
        replaceFeature("savanna_trees", WWBiomeTags.NORMAL_SAVANNA, GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.TREES_SAVANNA, WilderPlacedFeatures.SAVANNA_TREES);
        replaceFeature("windswept_savanna_trees", WWBiomeTags.WINDSWEPT_SAVANNA, GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.TREES_WINDSWEPT_SAVANNA, WilderPlacedFeatures.WINDSWEPT_SAVANNA_TREES);
        replaceFeature("snowy_plains_trees", WWBiomeTags.SNOWY_PLAINS, GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.TREES_SNOWY, WilderPlacedFeatures.TREES_SNOWY);
        replaceFeature("windswept_hills_trees", WWBiomeTags.WINDSWEPT_HILLS, GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.TREES_WINDSWEPT_HILLS, WilderPlacedFeatures.TREES_WINDSWEPT_HILLS);
        replaceFeature("dark_forest_vegetation", WWBiomeTags.DARK_FOREST, GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.DARK_FOREST_VEGETATION, WilderPlacedFeatures.DARK_FOREST_VEGETATION);
        replaceFeature("meadow_trees", WWBiomeTags.MEADOW, GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.TREES_MEADOW, WilderPlacedFeatures.TREES_MEADOW);
    }

    private static void addWildGrass() {
        addFeature("add_rare_grass", WWBiomeTags.HAS_NEW_RARE_GRASS, GenerationStep.Decoration.VEGETAL_DECORATION, WilderPlacedFeatures.RARE_GRASS_PLACED);
        addFeature("add_rare_large_fern_and_grass", WWBiomeTags.HAS_LARGE_FERN_AND_GRASS_RARE, GenerationStep.Decoration.VEGETAL_DECORATION, WilderPlacedFeatures.LARGE_FERN_AND_GRASS_RARE);
        addFeature("add_large_fern_and_grass", WWBiomeTags.HAS_LARGE_FERN_AND_GRASS, GenerationStep.Decoration.VEGETAL_DECORATION, WilderPlacedFeatures.LARGE_FERN_AND_GRASS);
    }

    private static void addFlowers() {
        addFeature("add_carnation", WWBiomeTags.HAS_CARNATION, GenerationStep.Decoration.VEGETAL_DECORATION, WilderPlacedFeatures.CARNATION);
        addFeature("add_datura", WWBiomeTags.HAS_DATURA, GenerationStep.Decoration.VEGETAL_DECORATION, WilderPlacedFeatures.DATURA_BIRCH);
        addFeature("add_glory_of_the_snow", WWBiomeTags.HAS_GLORY_OF_THE_SNOW, GenerationStep.Decoration.VEGETAL_DECORATION, WilderPlacedFeatures.GLORY_OF_THE_SNOW);
        addFeature("add_brown_shelf_fungus", WWBiomeTags.HAS_BROWN_SHELF_FUNGUS, GenerationStep.Decoration.VEGETAL_DECORATION, WilderPlacedFeatures.BROWN_SHELF_FUNGUS_PLACED);
        addFeature("add_red_shelf_fungus", WWBiomeTags.HAS_RED_SHELF_FUNGUS, GenerationStep.Decoration.VEGETAL_DECORATION, WilderPlacedFeatures.RED_SHELF_FUNGUS_PLACED);
        addFeature("add_flowering_lily_pads", WWBiomeTags.HAS_FLOWERING_WATER_LILY, GenerationStep.Decoration.VEGETAL_DECORATION, WilderPlacedFeatures.PATCH_FLOWERED_WATERLILY);
        addFeature("add_cattails", WWBiomeTags.HAS_CATTAIL, GenerationStep.Decoration.VEGETAL_DECORATION, WilderPlacedFeatures.PATCH_CATTAIL);
        addFeature("add_algae", BiomeTags.ALLOWS_SURFACE_SLIME_SPAWNS, GenerationStep.Decoration.VEGETAL_DECORATION, WilderPlacedFeatures.PATCH_ALGAE_5);
        addFeature("add_rare_algae_patch", WWBiomeTags.HAS_ALGAE_PATCH, GenerationStep.Decoration.VEGETAL_DECORATION, WilderPlacedFeatures.PATCH_ALGAE);
        addFeature("add_seeding_dandelions", WWBiomeTags.HAS_SEEDING_DANDELION, GenerationStep.Decoration.VEGETAL_DECORATION, WilderPlacedFeatures.SEEDING_DANDELION);
        addFeature("add_milkweed", WWBiomeTags.HAS_MILKWEED, GenerationStep.Decoration.VEGETAL_DECORATION, WilderPlacedFeatures.MILKWEED);
        addFeature("add_plains_flowers", WWBiomeTags.HAS_PLAINS_FLOWERS, GenerationStep.Decoration.VEGETAL_DECORATION, WilderPlacedFeatures.FLOWER_PLAINS);
        addFeature("add_forest_berry_patch", WWBiomeTags.HAS_BERRY_PATCH, GenerationStep.Decoration.VEGETAL_DECORATION, WilderPlacedFeatures.PATCH_BERRY_FOREST);
    }

    private static void addTrees() {
        addFeature("add_fallen_birch", WWBiomeTags.HAS_FALLEN_BIRCH_TREES, GenerationStep.Decoration.VEGETAL_DECORATION, WilderPlacedFeatures.FALLEN_BIRCH_PLACED);
        addFeature("add_fallen_spruce", BiomeTags.IS_TAIGA, GenerationStep.Decoration.VEGETAL_DECORATION, WilderPlacedFeatures.FALLEN_SPRUCE_PLACED);
        addFeature("add_fallen_oak_and_spruce", WWBiomeTags.HAS_FALLEN_OAK_AND_SPRUCE_TREES, GenerationStep.Decoration.VEGETAL_DECORATION, WilderPlacedFeatures.FALLEN_OAK_AND_SPRUCE_PLACED);
        addFeature("add_fallen_oak_and_birch", WWBiomeTags.HAS_FALLEN_OAK_AND_BIRCH_TREES, GenerationStep.Decoration.VEGETAL_DECORATION, WilderPlacedFeatures.FALLEN_OAK_AND_BIRCH_PLACED);
        addFeature("add_wild_trees", WWBiomeTags.HAS_WILD_TREES, GenerationStep.Decoration.VEGETAL_DECORATION, WilderPlacedFeatures.TREES_BIRCH_AND_OAK);
        addFeature("add_short_spruce", WWBiomeTags.HAS_SHORT_SPRUCE, GenerationStep.Decoration.VEGETAL_DECORATION, WilderPlacedFeatures.SHORT_SPRUCE_PLACED);
    }

    private static void addMushrooms() {
        addFeature("add_huge_red_mushrooms", WWBiomeTags.HAS_HUGE_RED_MUSHROOMS, GenerationStep.Decoration.VEGETAL_DECORATION, WilderPlacedFeatures.HUGE_RED_MUSHROOM_PLACED);
        addFeature("add_mushrooms", WWBiomeTags.HAS_MUSHROOMS, GenerationStep.Decoration.VEGETAL_DECORATION, WilderPlacedFeatures.MUSHROOM_PLACED);
        addFeature("add_huge_brown_mushrooms", WWBiomeTags.HAS_BROWN_MUSHROOMS, GenerationStep.Decoration.VEGETAL_DECORATION, WilderPlacedFeatures.BROWN_MUSHROOM_PLACED);
        addFeature("add_swamp_mushrooms", WWBiomeTags.HAS_SWAMP_MUSHROOMS, GenerationStep.Decoration.VEGETAL_DECORATION, WilderPlacedFeatures.HUGE_MUSHROOMS_SWAMP);
    }

    @SafeVarargs
    private static void replaceFeature(String name, TagKey<Biome> tagKey, GenerationStep.Decoration step, Holder<PlacedFeature> feature, Holder<PlacedFeature>... newFeature) {
        MODIFIERS.put(new ResourceLocation(WilderWild.MOD_ID, "remove_" + name), new ForgeBiomeModifiers.RemoveFeaturesBiomeModifier(new HolderSet.Named<>(BIOME_REGISTRY, tagKey), featureSet(feature), Set.of(step)));
        addFeature("add_" + name, tagKey, step, newFeature);
    }

    @SafeVarargs
    private static void replaceMultipleFeatures(String name, TagKey<Biome> tagKey, GenerationStep.Decoration step, Holder<PlacedFeature> newFeature, Holder<PlacedFeature>... feature) {
        MODIFIERS.put(new ResourceLocation(WilderWild.MOD_ID, "remove_" + name), new ForgeBiomeModifiers.RemoveFeaturesBiomeModifier(new HolderSet.Named<>(BIOME_REGISTRY, tagKey), featureSet(feature), Set.of(step)));
        addFeature("add_" + name, tagKey, step, newFeature);
    }

    @SafeVarargs
    private static void addMultipleFeatures(String name, TagKey<Biome> tagKey, GenerationStep.Decoration step, Holder<PlacedFeature> feature, Holder<PlacedFeature>... newFeature) {
        MODIFIERS.put(new ResourceLocation(WilderWild.MOD_ID, "remove_" + name), new ForgeBiomeModifiers.RemoveFeaturesBiomeModifier(new HolderSet.Named<>(BIOME_REGISTRY, tagKey), featureSet(feature), Set.of(step)));
        addFeature("add_" + name, tagKey, step, newFeature);
    }

    private static void addSpawn(String name, RegistryObject<Biome> biome, MobSpawnSettings.SpawnerData... spawnerData) {
        MODIFIERS.put(new ResourceLocation(WilderWild.MOD_ID, name), new ForgeBiomeModifiers.AddSpawnsBiomeModifier(HolderSet.direct(BIOME_REGISTRY.getOrCreateHolderOrThrow(biome.getKey())), Arrays.stream(spawnerData).toList()));
    }

    private static void addSpawn(String name, TagKey<Biome> tagKey, MobSpawnSettings.SpawnerData... spawnerData) {
        MODIFIERS.put(new ResourceLocation(WilderWild.MOD_ID, name), new ForgeBiomeModifiers.AddSpawnsBiomeModifier(new HolderSet.Named<>(BIOME_REGISTRY, tagKey), Arrays.stream(spawnerData).toList()));
    }

    @SafeVarargs
    private static void addFeature(String name, RegistryObject<Biome> biome, GenerationStep.Decoration step, Holder<PlacedFeature>... features) {
        MODIFIERS.put(new ResourceLocation(WilderWild.MOD_ID, name), new ForgeBiomeModifiers.AddFeaturesBiomeModifier(HolderSet.direct(BIOME_REGISTRY.getOrCreateHolderOrThrow(biome.getKey())), featureSet(features), step));
    }

    @SafeVarargs
    private static void addFeature(String name, TagKey<Biome> tagKey, GenerationStep.Decoration step, Holder<PlacedFeature>... features) {
        MODIFIERS.put(new ResourceLocation(WilderWild.MOD_ID, name), new ForgeBiomeModifiers.AddFeaturesBiomeModifier(new HolderSet.Named<>(BIOME_REGISTRY, tagKey), featureSet(features), step));
    }

    private static void addFeature(String name, TagKey<Biome> tagKey, GenerationStep.Decoration step, Holder<PlacedFeature> features) {
        MODIFIERS.put(new ResourceLocation(WilderWild.MOD_ID, name), new ForgeBiomeModifiers.AddFeaturesBiomeModifier(new HolderSet.Named<>(BIOME_REGISTRY, tagKey), featureSet(features), step));
    }

    @SafeVarargs
    private static HolderSet<PlacedFeature> featureSet(Holder<PlacedFeature>... features) {
        return HolderSet.direct(Stream.of(features).map(holder -> PLACED_FEATURES.getOrCreateHolderOrThrow(holder.unwrapKey().get())).collect(Collectors.toList()));
    }

}
