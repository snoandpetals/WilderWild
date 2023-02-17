package net.frozenblock.wilderwild.block;

import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class NematocystBlock extends FaceClusterBlock {
    private final NematocystSpreader spreader = new NematocystSpreader(this);

    public NematocystBlock(int height, int xzOffset, BlockBehaviour.Properties properties) {
        super(height, xzOffset, properties);
    }

    public NematocystBlock(BlockBehaviour.Properties properties) {
        this(7, 3, properties);
    }

    @Override
    public boolean skipRendering(@NotNull BlockState blockState, BlockState blockState2, @NotNull Direction direction) {
        return blockState2.is(this) || super.skipRendering(blockState, blockState2, direction);
    }

    @Override
    @NotNull
    public NematocystSpreader getSpreader() {
        return this.spreader;
    }
}
