package net.frozenblock.wilderwild.world;

import net.frozenblock.wilderwild.init.WWBiomes;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Noises;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.VerticalAnchor;

public class WWBiomeReagentHandler {
    private static final SurfaceRules.RuleSource WATER = SurfaceRules.state(Blocks.WATER.defaultBlockState());

    public static SurfaceRules.RuleSource cypressSurfaceRules() {
        return SurfaceRules.ifTrue(
                SurfaceRules.isBiome(WWBiomes.CYPRESS_WETLANDS.getKey()),
                SurfaceRules.ifTrue(
                        SurfaceRules.ON_FLOOR,
                        SurfaceRules.ifTrue(
                                SurfaceRules.yBlockCheck(VerticalAnchor.absolute(60), 0),
                                SurfaceRules.ifTrue(
                                        SurfaceRules.not(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(63), 0)),
                                        SurfaceRules.ifTrue(SurfaceRules.noiseCondition(Noises.SWAMP, 0.0), WATER)
                                )
                        )
                )
        );
    }

}
