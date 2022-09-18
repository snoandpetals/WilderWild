package net.frozenblock.wilderwild.world.feature;

import net.frozenblock.wilderwild.WilderWild;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraftforge.eventbus.api.IEventBus;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static net.frozenblock.wilderwild.world.feature.WilderTreePlaced.NEW_BIRCH_BEES_0004;


public final class WilderConfiguredFeatures {

    public static void registerConfiguredFeatures() {
        WilderWild.logWild("Registering WilderConfiguredFeatures for", true);
    }

    private static RandomPatchConfiguration createRandomPatchFeatureConfig(BlockStateProvider block, int tries) {
        return FeatureUtils.simpleRandomPatchConfiguration(tries, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(block)));
    }

    public static Holder<ConfiguredFeature<NoneFeatureConfiguration, ?>> register(String id, Feature<NoneFeatureConfiguration> feature) {
        return register(id, feature, FeatureConfiguration.NONE);
    }

    public static <FC extends FeatureConfiguration, F extends Feature<FC>> Holder<ConfiguredFeature<FC, ?>> register(@NotNull String id, F feature, @NotNull FC config) {
        return registerExact(BuiltinRegistries.CONFIGURED_FEATURE, id, new ConfiguredFeature<>(feature, config));
    }

    public static <V extends T, T> Holder<V> registerExact(Registry<T> registry, String id, V value) {
        return (Holder<V>) BuiltinRegistries.register(registry, WilderWild.id(id), value);
    }
}