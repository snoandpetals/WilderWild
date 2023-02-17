package net.frozenblock.wilderwild.mixin;

import net.frozenblock.wilderwild.init.WWBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.SculkVeinBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SculkVeinBlock.SculkVeinSpreaderConfig.class)
public final class SculkVeinSpreaderConfigMixin {

    @Inject(at = @At("RETURN"), method = "stateCanBeReplaced", cancellable = true)
    private void newBlocks(BlockGetter level, BlockPos pos, BlockPos growPos, Direction direction, BlockState state, CallbackInfoReturnable<Boolean> info) {
        BlockState blockState = level.getBlockState(growPos.relative(direction));
        if (blockState.is(WWBlocks.OSSEOUS_SCULK.get()) || blockState.is(WWBlocks.SCULK_SLAB.get()) || blockState.is(WWBlocks.SCULK_STAIRS.get()) || blockState.is(WWBlocks.SCULK_WALL.get())) {
            info.setReturnValue(false);
        }
    }

}
