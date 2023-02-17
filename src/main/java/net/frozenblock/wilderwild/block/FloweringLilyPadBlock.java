package net.frozenblock.wilderwild.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.WaterlilyBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

public class FloweringLilyPadBlock extends WaterlilyBlock {

    public FloweringLilyPadBlock(Properties settings) {
        super(settings);
    }

    @Override
    protected boolean mayPlaceOn(@NotNull BlockState floor, BlockGetter level, @NotNull BlockPos pos) {
        FluidState fluidState = level.getFluidState(pos);
        FluidState fluidState2 = level.getFluidState(pos.above());
        return (fluidState.getType() == Fluids.WATER || floor.getMaterial() == Material.ICE) && fluidState2.getType() == Fluids.EMPTY;
    }

    @Override
    public boolean canSurvive(BlockState p_51028_, LevelReader world, BlockPos pos) {
        BlockPos blockPos = pos.below();
        return this.mayPlaceOn(world.getBlockState(blockPos), world, blockPos);
    }

}
