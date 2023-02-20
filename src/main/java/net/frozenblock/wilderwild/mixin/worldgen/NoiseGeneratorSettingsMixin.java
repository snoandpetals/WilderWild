package net.frozenblock.wilderwild.mixin.worldgen;

import net.frozenblock.wilderwild.init.WWBiomes;
import net.frozenblock.wilderwild.util.interfaces.NoiseGeneratorInterface;
import net.frozenblock.wilderwild.world.WWBiomeReagentHandler;
import net.minecraft.core.Holder;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.Noises;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;

@Mixin(NoiseGeneratorSettings.class)
public class NoiseGeneratorSettingsMixin implements NoiseGeneratorInterface {
    @Shadow
    @Final
    @Mutable
    private SurfaceRules.RuleSource surfaceRule;

    @Unique
    private Holder<DimensionType> frozenLib$dimension;
    @Unique
    private boolean frozenLib$hasCheckedOverworldEntrypoints;

    @Inject(method = "surfaceRule", at = @At("HEAD"))
    private void overworld(CallbackInfoReturnable<SurfaceRules.RuleSource> cir) {
        if (this.frozenLib$dimension != null) {
            if (!(this.frozenLib$dimension.is(BuiltinDimensionTypes.OVERWORLD) || this.frozenLib$dimension.is(BuiltinDimensionTypes.OVERWORLD_CAVES))) {
                this.frozenLib$hasCheckedOverworldEntrypoints = true;
            }
            if (!this.frozenLib$hasCheckedOverworldEntrypoints) {
                ArrayList<SurfaceRules.RuleSource> sourceHolders = new ArrayList<>();

                sourceHolders.add(WWBiomeReagentHandler.cypressSurfaceRules());

                SurfaceRules.RuleSource newSource = null;
                for (SurfaceRules.RuleSource ruleSource : sourceHolders) {
                    if (newSource == null) {
                        newSource = ruleSource;
                    } else {
                        newSource = SurfaceRules.sequence(newSource, ruleSource);
                    }
                }

                if (newSource != null) {
                    newSource = SurfaceRules.ifTrue(SurfaceRules.abovePreliminarySurface(), newSource);
                }

                this.frozenLib$hasCheckedOverworldEntrypoints = true;

                if (newSource != null) {
                    this.surfaceRule = SurfaceRules.sequence(newSource, this.surfaceRule);
                }
            }
        }
    }

    @Override
    public void setDimension(Holder<DimensionType> dimension) {
        this.frozenLib$dimension = dimension;
    }

}
