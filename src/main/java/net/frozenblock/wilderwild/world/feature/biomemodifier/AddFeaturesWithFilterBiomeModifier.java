package net.frozenblock.wilderwild.world.feature.biomemodifier;

import com.mojang.serialization.Codec;
import net.frozenblock.wilderwild.init.WWBiomeModifiers;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ModifiableBiomeInfo;

public record AddFeaturesWithFilterBiomeModifier(HolderSet<Biome> biomes, HolderSet<Biome> filtered, HolderSet<PlacedFeature> features, GenerationStep.Decoration step) implements BiomeModifier {
    @Override
    public void modify(Holder<Biome> biome, Phase phase, ModifiableBiomeInfo.BiomeInfo.Builder builder) {
        if (phase == Phase.ADD && this.biomes.contains(biome)) {
            if (this.filtered.contains(biome)) {
                return;
            }
            this.features.forEach(holder -> builder.getGenerationSettings().addFeature(this.step, holder));
        }
    }

    @Override
    public Codec<? extends BiomeModifier> codec() {
        return WWBiomeModifiers.ADD_FEATURES_WITH_FILTER.get();
    }
}
