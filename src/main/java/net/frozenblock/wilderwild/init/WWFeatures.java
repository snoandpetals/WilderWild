package net.frozenblock.wilderwild.init;

import net.frozenblock.wilderwild.WilderWild;
import net.frozenblock.wilderwild.world.feature.features.AlgaeFeature;
import net.frozenblock.wilderwild.world.feature.features.CattailFeature;
import net.frozenblock.wilderwild.world.feature.features.ColumnWithDiskFeature;
import net.frozenblock.wilderwild.world.feature.features.DownwardsPillarFeature;
import net.frozenblock.wilderwild.world.feature.features.NematocystFeature;
import net.frozenblock.wilderwild.world.feature.features.NoisePathFeature;
import net.frozenblock.wilderwild.world.feature.features.NoisePathUnderWaterFeature;
import net.frozenblock.wilderwild.world.feature.features.ShelfFungusFeature;
import net.frozenblock.wilderwild.world.feature.features.UpwardsPillarFeature;
import net.frozenblock.wilderwild.world.feature.features.config.ColumnWithDiskFeatureConfig;
import net.frozenblock.wilderwild.world.feature.features.config.PathFeatureConfig;
import net.frozenblock.wilderwild.world.feature.features.config.PillarFeatureConfig;
import net.frozenblock.wilderwild.world.feature.features.config.ShelfFungusFeatureConfig;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.MultifaceGrowthConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.ProbabilityFeatureConfiguration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = WilderWild.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class WWFeatures {

    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, WilderWild.MOD_ID);

    public static final RegistryObject<Feature<MultifaceGrowthConfiguration>> NEMATOCYST_FEATURE = FEATURES.register("nematocyst_feature", () -> new NematocystFeature(MultifaceGrowthConfiguration.CODEC));
    public static final RegistryObject<Feature<ShelfFungusFeatureConfig>> SHELF_FUNGUS_FEATURE = FEATURES.register("shelf_fungus_feature", () -> new ShelfFungusFeature(ShelfFungusFeatureConfig.CODEC));
    public static final RegistryObject<Feature<ProbabilityFeatureConfiguration>> CATTAIL_FEATURE = FEATURES.register("cattail_feature", () -> new CattailFeature(ProbabilityFeatureConfiguration.CODEC));
    public static final RegistryObject<Feature<ProbabilityFeatureConfiguration>> ALGAE_FEATURE = FEATURES.register("algae_feature", () -> new AlgaeFeature(ProbabilityFeatureConfiguration.CODEC));
    public static final RegistryObject<Feature<ColumnWithDiskFeatureConfig>> COLUMN_WITH_DISK_FEATURE = FEATURES.register("column_with_disk_feature", () -> new ColumnWithDiskFeature(ColumnWithDiskFeatureConfig.CODEC));
    public static final RegistryObject<Feature<PathFeatureConfig>> NOISE_PATH_FEATURE = FEATURES.register("noise_path_feature", () -> new NoisePathFeature(PathFeatureConfig.CODEC));
    public static final RegistryObject<Feature<PathFeatureConfig>> NOISE_PATH_UNDER_WATER_FEATURE = FEATURES.register("noise_path_under_water_feature", () -> new NoisePathUnderWaterFeature(PathFeatureConfig.CODEC));
    public static final RegistryObject<Feature<PillarFeatureConfig>> UPWARDS_PILLAR_FEATURE = FEATURES.register("upwards_pillar", () -> new UpwardsPillarFeature(PillarFeatureConfig.CODEC));
    public static final RegistryObject<Feature<PillarFeatureConfig>> DOWNWARDS_PILLAR_FEATURE = FEATURES.register("downwards_pillar", () -> new DownwardsPillarFeature(PillarFeatureConfig.CODEC));

}
