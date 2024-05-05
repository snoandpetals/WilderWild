/*
 * Copyright 2023-2024 FrozenBlock
 * This file is part of Wilder Wild.
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, see <https://www.gnu.org/licenses/>.
 */

package net.frozenblock.wilderwild.block;

import net.frozenblock.wilderwild.block.entity.TermiteMoundBlockEntity;
import net.frozenblock.wilderwild.entity.ai.TermiteManager;
import net.frozenblock.wilderwild.registry.RegisterBlockEntities;
import net.frozenblock.wilderwild.registry.RegisterProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TermiteMoundBlock extends BaseEntityBlock {
	public static final int MIN_PLACEMENT_TICK_DELAY = 40;
	public static final int MAX_PLACEMENT_TICK_DELAY = 200;
	public static final int MIN_TICK_DELAY = 90;
	public static final int MAX_TICK_DELAY = 150;
	public static final int MIN_AWAKE_LIGHT_LEVEL = 7;

	public TermiteMoundBlock(@NotNull Properties settings) {
		super(settings);
		this.registerDefaultState(
			this.stateDefinition.any()
				.setValue(RegisterProperties.NATURAL, false)
				.setValue(RegisterProperties.TERMITES_AWAKE, false)
				.setValue(RegisterProperties.CAN_SPAWN_TERMITE, false)
		);
	}

	public static boolean canTermitesWaken(@NotNull Level level, @NotNull BlockPos pos) {
		return !shouldTermitesSleep(level, getLightLevel(level, pos));
	}

	public static boolean shouldTermitesSleep(@NotNull Level level, int light) {
		return level.isNight() && light < MIN_AWAKE_LIGHT_LEVEL;
	}

	public static int getLightLevel(@NotNull Level level, @NotNull BlockPos blockPos) {
		BlockPos.MutableBlockPos mutableBlockPos = blockPos.mutable();
		int finalLight = 0;
		for (Direction direction : Direction.values()) {
			mutableBlockPos.move(direction);
			int newLight = !level.isRaining() ? level.getMaxLocalRawBrightness(mutableBlockPos) : level.getBrightness(LightLayer.BLOCK, mutableBlockPos);
			finalLight = Math.max(finalLight, newLight);
			mutableBlockPos.move(direction, -1);
		}
		return finalLight;
	}

	@Nullable
	@Override
	public BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
		return new TermiteMoundBlockEntity(pos, state);
	}

	@Override
	protected void createBlockStateDefinition(@NotNull StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(RegisterProperties.NATURAL, RegisterProperties.TERMITES_AWAKE, RegisterProperties.CAN_SPAWN_TERMITE);
	}

	@Override
	@NotNull
	public BlockState updateShape(@NotNull BlockState state, @NotNull Direction direction, @NotNull BlockState neighborState, @NotNull LevelAccessor level, @NotNull BlockPos currentPos, @NotNull BlockPos neighborPos) {
		boolean isSafe = TermiteManager.isPosSafeForTermites(level, neighborPos, neighborState);
		if (isSafe != state.getValue(RegisterProperties.TERMITES_AWAKE)) {
			state = state.setValue(RegisterProperties.TERMITES_AWAKE, isSafe);
		}
		if (isSafe != state.getValue(RegisterProperties.CAN_SPAWN_TERMITE)) {
			state = state.setValue(RegisterProperties.CAN_SPAWN_TERMITE, isSafe);
		}
		return state;
	}

	@Override
	public void onPlace(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull BlockState oldState, boolean movedByPiston) {
		level.scheduleTick(pos, this, level.random.nextInt(MIN_PLACEMENT_TICK_DELAY, MAX_PLACEMENT_TICK_DELAY));
	}

	@Override
	public void onRemove(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull BlockState newState, boolean movedByPiston) {
		if (!state.is(newState.getBlock())) {
			if (level.getBlockEntity(pos) instanceof TermiteMoundBlockEntity termiteMoundBlockEntity) {
				termiteMoundBlockEntity.termiteManager.clearTermites(level);
			}
		}
		super.onRemove(state, level, pos, newState, movedByPiston);
	}

	@Override
	public void tick(@NotNull BlockState state, @NotNull ServerLevel level, @NotNull BlockPos pos, @NotNull RandomSource random) {
		boolean areTermitesSafe = TermiteManager.areTermitesSafe(level, pos);
		boolean canAwaken = canTermitesWaken(level, pos) && areTermitesSafe;
		if (canAwaken != state.getValue(RegisterProperties.TERMITES_AWAKE)) {
			level.setBlock(pos, state.setValue(RegisterProperties.TERMITES_AWAKE, canAwaken), UPDATE_ALL);
		}
		if (areTermitesSafe != state.getValue(RegisterProperties.CAN_SPAWN_TERMITE)) {
			level.setBlock(pos, state.setValue(RegisterProperties.CAN_SPAWN_TERMITE, areTermitesSafe), UPDATE_ALL);
		}
		level.scheduleTick(pos, this, random.nextInt(MIN_TICK_DELAY, MAX_TICK_DELAY));
	}

	@Override
	@NotNull
	public RenderShape getRenderShape(@NotNull BlockState blockState) {
		return RenderShape.MODEL;
	}

	@Nullable
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(@NotNull Level level, @NotNull BlockState state, @NotNull BlockEntityType<T> type) {
		return !level.isClientSide ?
			createTickerHelper(type, RegisterBlockEntities.TERMITE_MOUND, (worldx, pos, statex, blockEntity) ->
				blockEntity.tickServer(
					worldx,
					pos,
					statex.getValue(RegisterProperties.NATURAL),
					statex.getValue(RegisterProperties.TERMITES_AWAKE),
					statex.getValue(RegisterProperties.CAN_SPAWN_TERMITE)
				)
			)
			: createTickerHelper(type, RegisterBlockEntities.TERMITE_MOUND, (worldx, pos, statex, blockEntity) -> blockEntity.tickClient());
	}
}
