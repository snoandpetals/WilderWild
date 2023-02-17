package net.frozenblock.wilderwild.world.feature.features.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryCodecs;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

public class PillarFeatureConfig implements FeatureConfiguration {
    public static final Codec<PillarFeatureConfig> CODEC = RecordCodecBuilder.create((instance) -> {
        return instance.group(BlockState.CODEC.fieldOf("columnBlock").forGetter((config) -> {
            return config.columnBlock;
        }), IntProvider.NON_NEGATIVE_CODEC.fieldOf("height").forGetter((config) -> {
            return config.height;
        }), RegistryCodecs.homogeneousList(Registry.BLOCK_REGISTRY).fieldOf("replaceable").forGetter((config) -> {
            return config.replaceable;
        })).apply(instance, PillarFeatureConfig::new);
    });
    public final BlockState columnBlock;
    public final IntProvider height;
    public final HolderSet<Block> replaceable;

    private static DataResult<Block> validateBlock(Block block) {
        return DataResult.success(block);
    }

    public PillarFeatureConfig(BlockState columnBlock, IntProvider height, HolderSet<Block> replaceable) {
        this.columnBlock = columnBlock;
        this.height = height;
        this.replaceable = replaceable;
    }
}
