package net.frozenblock.wilderwild.util;

import net.frozenblock.wilderwild.init.WWBlockTags;
import net.frozenblock.wilderwild.init.WWBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.SculkBehaviour;
import net.minecraft.world.level.block.SculkSpreader;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

public class SlabWallStairSculkBehavior implements SculkBehaviour {
    @Override
    public int attemptUseCharge(SculkSpreader.ChargeCursor cursor, LevelAccessor level, BlockPos p_222041_, RandomSource random, SculkSpreader spreadManager, boolean p_222044_) {
        BlockState placementState = null;
        BlockPos cursorPos = cursor.getPos();
        BlockState currentState = level.getBlockState(cursorPos);
        if (currentState.is(WWBlockTags.SCULK_STAIR_REPLACEABLE_WORLDGEN) || currentState.is(WWBlockTags.SCULK_STAIR_REPLACEABLE)) {
            placementState = WWBlocks.SCULK_STAIRS.get().defaultBlockState().setValue(StairBlock.FACING, currentState.getValue(StairBlock.FACING)).setValue(StairBlock.HALF, currentState.getValue(StairBlock.HALF)).setValue(StairBlock.SHAPE, currentState.getValue(StairBlock.SHAPE)).setValue(StairBlock.WATERLOGGED, currentState.getValue(StairBlock.WATERLOGGED));
        } else if (currentState.is(WWBlockTags.SCULK_WALL_REPLACEABLE_WORLDGEN) || currentState.is(WWBlockTags.SCULK_WALL_REPLACEABLE)) {
            placementState = WWBlocks.SCULK_WALL.get().defaultBlockState().setValue(WallBlock.UP, currentState.getValue(WallBlock.UP)).setValue(WallBlock.NORTH_WALL, currentState.getValue(WallBlock.NORTH_WALL))
                    .setValue(WallBlock.EAST_WALL, currentState.getValue(WallBlock.EAST_WALL)).setValue(WallBlock.WEST_WALL, currentState.getValue(WallBlock.WEST_WALL))
                    .setValue(WallBlock.SOUTH_WALL, currentState.getValue(WallBlock.SOUTH_WALL)).setValue(WallBlock.WATERLOGGED, currentState.getValue(WallBlock.WATERLOGGED));
        } else if (currentState.is(WWBlockTags.SCULK_SLAB_REPLACEABLE_WORLDGEN) || currentState.is(WWBlockTags.SCULK_SLAB_REPLACEABLE)) {
            placementState = WWBlocks.SCULK_SLAB.get().defaultBlockState().setValue(SlabBlock.WATERLOGGED, currentState.getValue(SlabBlock.WATERLOGGED)).setValue(SlabBlock.TYPE, currentState.getValue(SlabBlock.TYPE));
        }

        if (placementState != null) {
            level.setBlock(cursorPos, placementState, 3);
            return cursor.getCharge() - 1;
        }
        return random.nextInt(spreadManager.chargeDecayRate()) == 0 ? Mth.floor((float) cursor.getCharge() * 0.5F) : cursor.getCharge();
    }

    @Override
    public boolean attemptSpreadVein(LevelAccessor level, BlockPos pos, BlockState state, @Nullable Collection<Direction> directions, boolean markForPostProcessing) {
        BlockState placementState = null;
        BlockState currentState = level.getBlockState(pos);
        if (currentState.is(WWBlockTags.SCULK_STAIR_REPLACEABLE_WORLDGEN) || currentState.is(WWBlockTags.SCULK_STAIR_REPLACEABLE)) {
            placementState = WWBlocks.SCULK_STAIRS.get().defaultBlockState().setValue(StairBlock.FACING, currentState.getValue(StairBlock.FACING)).setValue(StairBlock.HALF, currentState.getValue(StairBlock.HALF)).setValue(StairBlock.SHAPE, currentState.getValue(StairBlock.SHAPE)).setValue(StairBlock.WATERLOGGED, currentState.getValue(StairBlock.WATERLOGGED));
        } else if (currentState.is(WWBlockTags.SCULK_WALL_REPLACEABLE_WORLDGEN) || currentState.is(WWBlockTags.SCULK_WALL_REPLACEABLE)) {
            placementState = WWBlocks.SCULK_WALL.get().defaultBlockState().setValue(WallBlock.UP, currentState.getValue(WallBlock.UP)).setValue(WallBlock.NORTH_WALL, currentState.getValue(WallBlock.NORTH_WALL))
                    .setValue(WallBlock.EAST_WALL, currentState.getValue(WallBlock.EAST_WALL)).setValue(WallBlock.WEST_WALL, currentState.getValue(WallBlock.WEST_WALL))
                    .setValue(WallBlock.SOUTH_WALL, currentState.getValue(WallBlock.SOUTH_WALL)).setValue(WallBlock.WATERLOGGED, currentState.getValue(WallBlock.WATERLOGGED));
        } else if (currentState.is(WWBlockTags.SCULK_SLAB_REPLACEABLE_WORLDGEN) || currentState.is(WWBlockTags.SCULK_SLAB_REPLACEABLE)) {
            placementState = WWBlocks.SCULK_SLAB.get().defaultBlockState().setValue(SlabBlock.WATERLOGGED, currentState.getValue(SlabBlock.WATERLOGGED)).setValue(SlabBlock.TYPE, currentState.getValue(SlabBlock.TYPE));
        }

        if (placementState != null) {
            level.setBlock(pos, placementState, 3);
            return true;
        }
        return false;
    }

}
