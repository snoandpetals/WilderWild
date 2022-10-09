package net.frozenblock.wilderwild.fabric;

import com.chocohead.mm.api.ClassTinkerers;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.serialization.Codec;
import dev.architectury.platform.Mod;
import dev.architectury.platform.Platform;
import net.fabricmc.api.ModInitializer;
import net.frozenblock.lib.common.worldgen.biome.api.modifications.FrozenBiomeModifications;
import net.frozenblock.lib.common.worldgen.biome.api.modifications.FrozenBiomeSelectors;
import net.frozenblock.wilderwild.common.WilderWild;
import net.frozenblock.wilderwild.fabric.block.entity.TermiteMoundBlockEntity;
import net.frozenblock.wilderwild.fabric.entity.Firefly;
import net.frozenblock.wilderwild.fabric.misc.BlockSoundGroupOverwrites;
import net.frozenblock.wilderwild.fabric.misc.FireflyColor;
import net.frozenblock.wilderwild.fabric.registry.*;
import net.frozenblock.wilderwild.fabric.world.feature.*;
import net.frozenblock.wilderwild.fabric.world.feature.features.*;
import net.frozenblock.wilderwild.fabric.world.feature.features.config.*;
import net.frozenblock.wilderwild.fabric.world.gen.WilderWorldGen;
import net.frozenblock.wilderwild.fabric.world.gen.trunk.BaobabTrunkPlacer;
import net.frozenblock.wilderwild.fabric.world.gen.trunk.FallenTrunkWithLogs;
import net.frozenblock.wilderwild.fabric.world.gen.trunk.StraightTrunkWithLogs;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.util.datafix.schemas.NamespacedSchema;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.ProbabilityFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import org.quiltmc.qsl.frozenblock.common.misc.datafixerupper.api.QuiltDataFixerBuilder;
import org.quiltmc.qsl.frozenblock.common.misc.datafixerupper.api.QuiltDataFixes;
import org.quiltmc.qsl.frozenblock.common.misc.datafixerupper.api.SimpleFixes;

import java.util.HashMap;
import java.util.Map;

public final class WilderWildFabric implements ModInitializer {

    public static boolean hasCloth = Platform.isModLoaded("cloth-config");
    public static boolean hasPipes = Platform.isModLoaded("copper_pipe");
    public static boolean hasSodium = Platform.isModLoaded("sodium");
    public static boolean hasTerraBlender = Platform.isModLoaded("terrablender");
    public static boolean hasTerralith = Platform.isModLoaded("terralith");

    public static final TrunkPlacerType<StraightTrunkWithLogs> STRAIGHT_TRUNK_WITH_LOGS_PLACER_TYPE = registerTrunk("straight_trunk_logs_placer", StraightTrunkWithLogs.CODEC);
    public static final TrunkPlacerType<FallenTrunkWithLogs> FALLEN_TRUNK_WITH_LOGS_PLACER_TYPE = registerTrunk("fallen_trunk_logs_placer", FallenTrunkWithLogs.CODEC);
    public static final TrunkPlacerType<BaobabTrunkPlacer> BAOBAB_TRUNK_PLACER = registerTrunk("baobab_trunk_placer", BaobabTrunkPlacer.CODEC);
    public static final Feature<ShelfFungusFeatureConfig> SHELF_FUNGUS_FEATURE = new ShelfFungusFeature(ShelfFungusFeatureConfig.CODEC);
    public static final CattailFeature CATTAIL_FEATURE = new CattailFeature(ProbabilityFeatureConfiguration.CODEC);
    public static final AlgaeFeature ALGAE_FEATURE = new AlgaeFeature(ProbabilityFeatureConfiguration.CODEC);
    public static final NoisePathFeature NOISE_PATH_FEATURE = new NoisePathFeature(PathFeatureConfig.CODEC);
    public static final NoisePlantFeature NOISE_PLANT_FEATURE = new NoisePlantFeature(PathFeatureConfig.CODEC);
    public static final NoisePathUnderWaterFeature NOISE_PATH_UNDER_WATER_FEATURE = new NoisePathUnderWaterFeature(PathFeatureConfig.CODEC);
    public static final ColumnWithDiskFeature COLUMN_WITH_DISK_FEATURE = new ColumnWithDiskFeature(ColumnWithDiskFeatureConfig.CODEC);
    public static final UpwardsPillarFeature UPWARDS_PILLAR_FEATURE = new UpwardsPillarFeature(WilderPillarConfig.CODEC);
    public static final DownwardsPillarFeature DOWNWARDS_PILLAR_FEATURE = new DownwardsPillarFeature(WilderPillarConfig.CODEC);
    public static final NematocystFeature NEMATOCYST_FEATURE = new NematocystFeature(NematocystFeatureConfig.CODEC);

    //Fabric ASM
    public static final MobCategory FIREFLIES = ClassTinkerers.getEnum(MobCategory.class, "WILDERWILDFIREFLIES");
    public static final MobCategory JELLYFISH = ClassTinkerers.getEnum(MobCategory.class, "WILDERWILDJELLYFISH");

    public static RandomSource random() {
        return RandomSource.create();
    }

    @Override
    public void onInitialize() {
        startMeasuring(this);
        var dataFixer = applyDataFixes(Platform.getMod(WilderWild.MOD_ID));
        WilderWild.init();

        WilderRegistry.initRegistry();
        RegisterBlocks.registerBlocks();
        RegisterItems.registerItems();
        WilderConfiguredFeatures.registerConfiguredFeatures();
        WilderPlacedFeatures.init();
        WilderTreeConfigured.registerTreeConfigured();
        WilderTreePlaced.registerTreePlaced();
        WilderMiscConfigured.registerMiscPlaced();
        WilderWorldGen.generateWildWorldGen();
        RegisterGameEvents.registerEvents();
        RegisterWorldgen.registerWorldgen();
        RegisterStructures.init();

        RegisterSounds.init();
        RegisterBlockSoundGroups.init();
        RegisterBlockEntities.register();
        RegisterEntities.init();
        BlockSoundGroupOverwrites.init();
        RegisterLootTables.init();
        RegisterParticles.registerParticles();

        Registry.register(Registry.FEATURE, id("shelf_fungus_feature"), SHELF_FUNGUS_FEATURE);
        Registry.register(Registry.FEATURE, id("cattail_feature"), CATTAIL_FEATURE);
        Registry.register(Registry.FEATURE, id("algae_feature"), ALGAE_FEATURE);
        Registry.register(Registry.FEATURE, id("noise_path_feature"), NOISE_PATH_FEATURE);
        Registry.register(Registry.FEATURE, id("noise_plant_feature"), NOISE_PLANT_FEATURE);
        Registry.register(Registry.FEATURE, id("noise_path_under_water_feature"), NOISE_PATH_UNDER_WATER_FEATURE);
        Registry.register(Registry.FEATURE, id("column_with_disk_feature"), COLUMN_WITH_DISK_FEATURE);
        Registry.register(Registry.FEATURE, id("upwards_pillar"), UPWARDS_PILLAR_FEATURE);
        Registry.register(Registry.FEATURE, id("downwards_pillar"), DOWNWARDS_PILLAR_FEATURE);
        Registry.register(Registry.FEATURE, id("nematocyst_feature"), NEMATOCYST_FEATURE);


        TermiteMoundBlockEntity.Termite.addDegradableBlocks();
        TermiteMoundBlockEntity.Termite.addNaturalDegradableBlocks();

        if (hasTerralith) {
            terralith();
        }

        stopMeasuring(this);
    }

    public static final int DATA_VERSION = 8;

    private static QuiltDataFixerBuilder applyDataFixes(Mod mod) {
        log("Applying DataFixes for Wilder Wild", true);
        var builder = new QuiltDataFixerBuilder(DATA_VERSION);
        builder.addSchema(0, QuiltDataFixes.BASE_SCHEMA);
        Schema schemaV1 = builder.addSchema(1, NamespacedSchema::new);
        SimpleFixes.addBlockRenameFix(builder, "Rename white_dandelion to blooming_dandelion", id("white_dandelion"), id("blooming_dandelion"), schemaV1);
        SimpleFixes.addBlockRenameFix(builder, "Rename potted_white_dandelion to potted_blooming_dandelion", id("potted_white_dandelion"), id("potted_blooming_dandelion"), schemaV1);
        Schema schemaV2 = builder.addSchema(2, NamespacedSchema::new);
        SimpleFixes.addBlockRenameFix(builder, "Rename blooming_dandelion to seeding_dandelion", id("blooming_dandelion"), id("seeding_dandelion"), schemaV2);
        SimpleFixes.addBlockRenameFix(builder, "Rename potted_blooming_dandelion to potted_seeding_dandelion", id("potted_blooming_dandelion"), id("potted_seeding_dandelion"), schemaV2);
        Schema schemaV3 = builder.addSchema(3, NamespacedSchema::new);
        SimpleFixes.addBlockRenameFix(builder, "Rename floating_moss to algae", id("floating_moss"), id("algae"), schemaV3);
        SimpleFixes.addItemRenameFix(builder, "Rename floating_moss to algae", id("floating_moss"), id("algae"), schemaV3);
        Schema schemaV4 = builder.addSchema(4, NamespacedSchema::new);
        SimpleFixes.addBlockRenameFix(builder, "Rename test_1 to null_block", id("test_1"), id("null_block"), schemaV4);
        Schema schemaV5 = builder.addSchema(5, NamespacedSchema::new);
        SimpleFixes.addBlockRenameFix(builder, "Rename sculk_echoer to null_block", id("sculk_echoer"), id("null_block"), schemaV5);
        SimpleFixes.addBlockRenameFix(builder, "Rename sculk_jaw to null_block", id("sculk_jaw"), id("null_block"), schemaV5);
        Schema schemaV6 = builder.addSchema(6, NamespacedSchema::new);
        SimpleFixes.addBlockRenameFix(builder, "Rename baobab_sapling to baobab_nut", id("baobab_sapling"), id("baobab_nut"), schemaV6);
        SimpleFixes.addBlockRenameFix(builder, "Rename baobab_nut_sapling to baobab_nut", id("baobab_nut_sapling"), id("baobab_nut"), schemaV6);
        SimpleFixes.addBlockRenameFix(builder, "Rename potted_baobab_sapling to potted_baobab_nut", id("potted_baobab_sapling"), id("potted_baobab_nut"), schemaV6);
        Schema schemaV7 = builder.addSchema(7, NamespacedSchema::new);
        SimpleFixes.addBlockRenameFix(builder, "Rename firefly_lantern to display_lantern", id("firefly_lantern"), id("display_lantern"), schemaV7);
        SimpleFixes.addBlockRenameFix(builder, "Rename mesoglea to blue_pearlescent_mesoglea", id("mesoglea"), id("blue_pearlescent_mesoglea"), schemaV7);
        SimpleFixes.addItemRenameFix(builder, "Rename mesoglea to blue_pearlescent_mesoglea", id("mesoglea"), id("blue_pearlescent_mesoglea"), schemaV7);
        Schema schemaV8 = builder.addSchema(8, NamespacedSchema::new);
        SimpleFixes.addBlockStateRenameFix(builder, "display_lantern_rename_fix", id("display_lantern"), "light", "0", "display_light", schemaV8);

        QuiltDataFixes.buildAndRegisterFixer(mod, builder);
        log("DataFixes for Wilder Wild have been applied", true);
        return builder;
    }

    //MOD COMPATIBILITY
    public static void terralith() {
        var frostfire = new ResourceLocation("terralith", "cave/frostfire_caves");
        var thermal = new ResourceLocation("terralith", "cave/thermal_caves");
        var undergroundJungle = new ResourceLocation("terralith", "cave/underground_jungle");

        Firefly.FireflyBiomeColorRegistry.addBiomeColor(frostfire, FireflyColor.BLUE);
        Firefly.FireflyBiomeColorRegistry.addBiomeColor(frostfire, FireflyColor.LIGHT_BLUE);

        Firefly.FireflyBiomeColorRegistry.addBiomeColor(thermal, FireflyColor.RED);
        Firefly.FireflyBiomeColorRegistry.addBiomeColor(thermal, FireflyColor.ORANGE);

        FrozenBiomeModifications.addSpawn(FrozenBiomeSelectors.includeByKey(ResourceKey.create(Registry.BIOME_REGISTRY, undergroundJungle)),
                WilderWildFabric.FIREFLIES, RegisterEntities.FIREFLY, 12, 2, 4);
    }

    public static boolean isCopperPipe(BlockState state) {
        if (hasPipes) {
            ResourceLocation id = Registry.BLOCK.getKey(state.getBlock());
            return id.getNamespace().equals("lunade") && id.getPath().contains("pipe");
        }
        return false;
    }

    //LOGGING
    public static void log(String string, boolean shouldLog) {
        if (shouldLog) {
            WilderWild.LOGGER.info(string);
        }
    }

    public static void logInsane(String string, boolean shouldLog) {
        if (shouldLog) {
            for (int i = 0; i < Math.random() * 5; i++) {
                WilderWild.LOGGER.warn(string);
                WilderWild.LOGGER.error(string);
                WilderWild.LOGGER.warn(string);
                WilderWild.LOGGER.error(string);
                WilderWild.LOGGER.warn(string);
                WilderWild.LOGGER.error(string);
                WilderWild.LOGGER.warn(string);
                WilderWild.LOGGER.error(string);
            }
        }
    }

    public static void log(Entity entity, String string, boolean shouldLog) {
        if (shouldLog) {
            WilderWild.LOGGER.info(entity.toString() + " : " + string + " : " + entity.position());
        }
    }

    public static void log(Block block, String string, boolean shouldLog) {
        if (shouldLog) {
            WilderWild.LOGGER.info(block.toString() + " : " + string + " : ");
        }
    }

    public static void log(Block block, BlockPos pos, String string, boolean shouldLog) {
        if (shouldLog) {
            WilderWild.LOGGER.info(block.toString() + " : " + string + " : " + pos);
        }
    }

    public static void logWild(String string, boolean shouldLog) {
        if (shouldLog) {
            WilderWild.LOGGER.info(string + " " + WilderWild.MOD_ID);
        }
    }

    private static <P extends TrunkPlacer> TrunkPlacerType<P> registerTrunk(String id, Codec<P> codec) {
        return Registry.register(Registry.TRUNK_PLACER_TYPES, id(id), new TrunkPlacerType<>(codec));
    }

    //MEASURING
    public static Map<Object, Long> instantMap = new HashMap<>();

    public static void startMeasuring(Object object) {
        long started = System.nanoTime();
        String name = object.getClass().getName();
        WilderWild.LOGGER.error("Started measuring {}", name.substring(name.lastIndexOf(".") + 1));
        instantMap.put(object, started);
    }

    public static void stopMeasuring(Object object) {
        if (instantMap.containsKey(object)) {
            String name = object.getClass().getName();
            WilderWild.LOGGER.error("{} took {} nanoseconds", name.substring(name.lastIndexOf(".") + 1), System.nanoTime() - instantMap.get(object));
            instantMap.remove(object);
        }
    }

    //GAMERULES
    public static final GameRules.Key<GameRules.BooleanValue> STONE_CHEST_CLOSES =
            GameRules.register("stoneChestCloses", GameRules.Category.MISC, GameRules.BooleanValue.create(true));

    //IDENTIFIERS
    public static final ResourceLocation SEED_PACKET = id("seed_particle_packet");
    public static final ResourceLocation CONTROLLED_SEED_PACKET = id("controlled_seed_particle_packet");
    public static final ResourceLocation FLOATING_SCULK_BUBBLE_PACKET = id("floating_sculk_bubble_easy_packet");
    public static final ResourceLocation TERMITE_PARTICLE_PACKET = id("termite_particle_packet");
    public static final ResourceLocation HORN_PROJECTILE_PACKET_ID = id("ancient_horn_projectile_packet");
    public static final ResourceLocation SENSOR_HICCUP_PACKET = id("sensor_hiccup_packet");
    public static final ResourceLocation JELLY_STING_PACKET = id("jelly_sting_packet");

    public static final ResourceLocation CAPTURE_FIREFLY_NOTIFY_PACKET = id("capture_firefly_notify_packet");
    public static final ResourceLocation ANCIENT_HORN_KILL_NOTIFY_PACKET = id("ancient_horn_kill_notify_packet");

    public static ResourceLocation id(String path) {
        return new ResourceLocation(WilderWild.MOD_ID, path);
    }

    public static String string(String path) {
        return id(path).toString();
    }

}
