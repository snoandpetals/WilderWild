package net.frozenblock.wilderwild.mixin.worldgen;

import net.frozenblock.wilderwild.WilderWild;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = BiomeDefaultFeatures.class, priority = 69420)
public class DefaultBiomeFeaturesMixin {


    @Inject(method = "addBirchTrees", at = @At("HEAD"), cancellable = true)
    private static void addBirchTrees(BiomeGenerationSettings.Builder builder, CallbackInfo info) {
            WilderWild.log("Removing " + "Birch Trees" + " in order to properly update biomes!", WilderWild.UNSTABLE_LOGGING);
            info.cancel();
        }
    }