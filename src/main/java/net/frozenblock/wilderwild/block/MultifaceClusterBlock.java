package net.frozenblock.wilderwild.block;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.MultifaceBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Objects;

public abstract class MultifaceClusterBlock extends MultifaceBlock implements SimpleWaterloggedBlock {
    public static final BooleanProperty WATERLOGGED;
    public static final BooleanProperty UP;
    protected final VoxelShape northAabb;
    protected final VoxelShape southAabb;
    protected final VoxelShape eastAabb;
    protected final VoxelShape westAabb;
    protected final VoxelShape upAabb;
    protected final VoxelShape downAabb;
    private final Map<Direction, VoxelShape> shapeByDirection;
    private final ImmutableMap<BlockState, VoxelShape> shapesCache;

    public MultifaceClusterBlock(int height, int xzOffset, BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(WATERLOGGED, false));
        this.upAabb = Block.box(xzOffset, 0.0, xzOffset, (16 - xzOffset), height, (16 - xzOffset));
        this.downAabb = Block.box(xzOffset, (16 - height), xzOffset, (16 - xzOffset), 16.0, (16 - xzOffset));
        this.northAabb = Block.box(xzOffset, xzOffset, (16 - height), (16 - xzOffset), (16 - xzOffset), 16.0);
        this.southAabb = Block.box(xzOffset, xzOffset, 0.0, (16 - xzOffset), (16 - xzOffset), height);
        this.eastAabb = Block.box(0.0, xzOffset, xzOffset, height, (16 - xzOffset), (16 - xzOffset));
        this.westAabb = Block.box((16 - height), xzOffset, xzOffset, 16.0, (16 - xzOffset), (16 - xzOffset));
        this.shapeByDirection = Util.make(Maps.newEnumMap(Direction.class), (shapes) -> {
            shapes.put(Direction.NORTH, this.southAabb);
            shapes.put(Direction.EAST, this.westAabb);
            shapes.put(Direction.SOUTH, this.northAabb);
            shapes.put(Direction.WEST, this.eastAabb);
            shapes.put(Direction.UP, this.downAabb);
            shapes.put(Direction.DOWN, this.upAabb);
        });
        this.shapesCache = this.getShapeForEachState(this::calculateMultifaceShape);
    }

    public @NotNull VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter world, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return Objects.requireNonNull(this.shapesCache.get(state));
    }

    public VoxelShape calculateMultifaceShape(BlockState state) {
        VoxelShape voxelShape = Shapes.empty();
        Direction[] var3 = DIRECTIONS;
        int var4 = var3.length;

        for (Direction direction : var3) {
            if (hasFace(state, direction)) {
                voxelShape = Shapes.or(voxelShape, this.shapeByDirection.get(direction));
            }
        }

        return voxelShape.isEmpty() ? Shapes.block() : voxelShape;
    }

    public @NotNull BlockState updateShape(BlockState state, @NotNull Direction direction, @NotNull BlockState neighborState, @NotNull LevelAccessor world, @NotNull BlockPos pos, @NotNull BlockPos neighborPos) {
        if ((Boolean)state.getValue(WATERLOGGED)) {
            world.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(world));
        }

        return super.updateShape(state, direction, neighborState, world, pos, neighborPos);
    }

    public @NotNull FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    protected void createBlockStateDefinition(StateDefinition.@NotNull Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(WATERLOGGED);
    }

    public @NotNull PushReaction getPistonPushReaction(BlockState blockState) {
        return PushReaction.DESTROY;
    }

    public boolean propagatesSkylightDown(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) {
        return blockState.getFluidState().isEmpty();
    }

    static {
        WATERLOGGED = BlockStateProperties.WATERLOGGED;
        UP = BlockStateProperties.UP;
    }
}
