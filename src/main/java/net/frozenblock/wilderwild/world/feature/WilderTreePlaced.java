package net.frozenblock.wilderwild.world.feature;

import net.frozenblock.wilderwild.WilderWild;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public final class WilderTreePlaced {
    //BIRCH
    public static final Holder<PlacedFeature> NEW_BIRCH_CHECKED = PlacementUtils.register("new_birch_checked", WilderTreeConfigured.NEW_BIRCH_TREE, PlacementUtils.filteredByBlockSurvival(Blocks.BIRCH_SAPLING));
    public static final Holder<PlacedFeature> NEW_BIRCH_BEES_0004 = PlacementUtils.register("new_birch_bees_0004", WilderTreeConfigured.NEW_BIRCH_BEES_0004, PlacementUtils.filteredByBlockSurvival(Blocks.BIRCH_SAPLING));
    public static final Holder<PlacedFeature> SHORT_BIRCH = PlacementUtils.register("short_birch", WilderTreeConfigured.SHORT_BIRCH, PlacementUtils.filteredByBlockSurvival(Blocks.BIRCH_SAPLING));
    public static final Holder<PlacedFeature> NEW_SHORT_BIRCH_BEES_0004 = PlacementUtils.register("new_short_birch_bees_0004", WilderTreeConfigured.NEW_SHORT_BIRCH_BEES_0004, PlacementUtils.filteredByBlockSurvival(Blocks.BIRCH_SAPLING));
    public static final Holder<PlacedFeature> NEW_SUPER_BIRCH_BEES_0004 = PlacementUtils.register("new_super_birch_bees_0004", WilderTreeConfigured.NEW_SUPER_BIRCH_BEES_0004, PlacementUtils.filteredByBlockSurvival(Blocks.BIRCH_SAPLING));
    public static final Holder<PlacedFeature> NEW_SUPER_BIRCH_BEES = PlacementUtils.register("new_super_birch_bees", WilderTreeConfigured.NEW_SUPER_BIRCH_BEES, PlacementUtils.filteredByBlockSurvival(Blocks.BIRCH_SAPLING));

    public static void registerTreePlaced() {
        WilderWild.logWild("Registering WilderTreePlaced for", true);
    }
}
