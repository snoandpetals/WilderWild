package net.frozenblock.wilderwild.world.feature;

import com.google.common.collect.ImmutableList;
import net.frozenblock.wilderwild.WilderWild;
import net.frozenblock.wilderwild.world.gen.trunk.StraightTrunkWithLogs;
import net.minecraft.core.Holder;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.BeehiveDecorator;

public class WilderTreeConfigured {
    //DECORATORS
    private static final BeehiveDecorator NEW_BEES_0004 = new BeehiveDecorator(0.004F);
    private static final BeehiveDecorator NEW_BEES = new BeehiveDecorator(1.0F);
    //BIRCH
    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> NEW_BIRCH_TREE = WilderConfiguredFeatures.register("new_birch_tree", Feature.TREE, new_birch().dirt(BlockStateProvider.simple(Blocks.DIRT)).build());
    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> NEW_BIRCH_BEES_0004 = WilderConfiguredFeatures.register("new_birch_bees_0002", Feature.TREE, new_birch().decorators(ImmutableList.of(NEW_BEES_0004)).ignoreVines().build());
    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> NEW_SHORT_BIRCH_BEES_0004 = WilderConfiguredFeatures.register("new_short_birch_bees_0004", Feature.TREE, new_short_birch().decorators(ImmutableList.of(NEW_BEES_0004)).ignoreVines().build());
    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> NEW_SUPER_BIRCH_BEES_0004 = WilderConfiguredFeatures.register("new_super_birch_bees_0004", Feature.TREE, new_superBirch().decorators(ImmutableList.of(NEW_BEES_0004)).build());
    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> SHORT_BIRCH = WilderConfiguredFeatures.register("short_birch", Feature.TREE, new_short_birch().ignoreVines().build());
    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> NEW_SUPER_BIRCH_BEES = WilderConfiguredFeatures.register("new_super_birch_bees", Feature.TREE, new_superBirch().decorators(ImmutableList.of(NEW_BEES)).build());

    public WilderTreeConfigured() {
    }

    private static TreeConfiguration.TreeConfigurationBuilder builder(Block log, Block leaves, int baseHeight, int firstRandomHeight, int secondRandomHeight, float logChance, IntProvider maxLogs, IntProvider logHeightFromTop, IntProvider extraBranchLength, int radius) {
        return new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(log), new StraightTrunkWithLogs(baseHeight, firstRandomHeight, secondRandomHeight, logChance, maxLogs, logHeightFromTop, extraBranchLength),
                BlockStateProvider.simple(leaves), new BlobFoliagePlacer(ConstantInt.of(radius), ConstantInt.of(0), 3),
                new TwoLayersFeatureSize(1, 0, 1));
    }

    private static TreeConfiguration.TreeConfigurationBuilder new_birch() {
        return builder(Blocks.BIRCH_LOG, Blocks.BIRCH_LEAVES, 8, 5, 4, 0.15F, UniformInt.of(1, 2), UniformInt.of(1, 3), ConstantInt.of(1), 2).ignoreVines();
    }

    private static TreeConfiguration.TreeConfigurationBuilder new_superBirch() {
        return builder(Blocks.BIRCH_LOG, Blocks.BIRCH_LEAVES, 8, 6, 6, 0.15F, UniformInt.of(1, 2), UniformInt.of(1, 3), ConstantInt.of(1), 2).ignoreVines();
    }

    private static TreeConfiguration.TreeConfigurationBuilder new_short_birch() {
        return builder(Blocks.BIRCH_LOG, Blocks.BIRCH_LEAVES, 6, 2, 2, 0.12F, UniformInt.of(1, 2), UniformInt.of(1, 3), ConstantInt.of(1), 2).ignoreVines();
    }
    public static void registerTreeConfigured() {
        WilderWild.logWild("Registering WilderTreeConfigured for", true);
    }
}

