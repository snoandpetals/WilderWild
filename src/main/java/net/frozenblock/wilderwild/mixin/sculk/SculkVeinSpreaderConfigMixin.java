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

package net.frozenblock.wilderwild.mixin.sculk;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.frozenblock.wilderwild.registry.RegisterBlocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SculkVeinBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(SculkVeinBlock.SculkVeinSpreaderConfig.class)
public final class SculkVeinSpreaderConfigMixin {

	@WrapOperation(
		method = "stateCanBeReplaced",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/world/level/block/state/BlockState;is(Lnet/minecraft/world/level/block/Block;)Z",
			ordinal = 0
		)
	)
	private boolean wilderWild$restrictGrowthOnNewBlocks(BlockState state, Block block, Operation<Boolean> operation) {
		return state.is(RegisterBlocks.OSSEOUS_SCULK) ||
			state.is(RegisterBlocks.SCULK_SLAB) ||
			state.is(RegisterBlocks.SCULK_STAIRS) ||
			state.is(RegisterBlocks.SCULK_WALL) ||
			operation.call(state, block);
	}

}
