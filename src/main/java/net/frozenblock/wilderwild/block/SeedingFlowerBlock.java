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

import net.frozenblock.wilderwild.particle.options.SeedParticleOptions;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("deprecation")
public class SeedingFlowerBlock extends FlowerBlock {
	public static final float SEED_SPAWN_CHANCE = 0.95F;
	public static final double SEED_SPAWN_HEIGHT = 0.3D;
	public static final int MIN_SEEDS = 1;
	public static final int MAX_SEEDS = 3;
	public static final int MIN_SEEDS_DESTROY = 3;
	public static final int MAX_SEEDS_DESTROY = 7;

	public SeedingFlowerBlock(@NotNull MobEffect suspiciousStewEffect, int effectDuration, @NotNull Properties settings) {
		super(suspiciousStewEffect, effectDuration, settings);
	}

	@Override
	public void animateTick(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull RandomSource random) {
		if (random.nextFloat() > SEED_SPAWN_CHANCE) {
			level.addParticle(
				SeedParticleOptions.unControlled(false),
				pos.getX() + 0.5D,
				pos.getY() + SEED_SPAWN_HEIGHT,
				pos.getZ() + 0.5D,
				0D,
				0D,
				0D
			);
		}
	}

	@Override
	public void entityInside(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull Entity entity) {
		if (level instanceof ServerLevel server && server.getRandom().nextFloat() > SEED_SPAWN_CHANCE) {
			server.sendParticles(
				SeedParticleOptions.unControlled(false),
				pos.getX() + 0.5D,
				pos.getY() + SEED_SPAWN_HEIGHT,
				pos.getZ() + 0.5D,
				server.getRandom().nextIntBetweenInclusive(MIN_SEEDS, MAX_SEEDS),
				0D,
				0D,
				0D,
				0D
			);
		}
	}

	@Override
	public void playerWillDestroy(@NotNull Level level, @NotNull BlockPos pos, @NotNull BlockState state, @NotNull Player player) {
		super.playerWillDestroy(level, pos, state, player);
		if (level instanceof ServerLevel server) {
			server.sendParticles(
				SeedParticleOptions.unControlled(false),
				pos.getX() + 0.5D,
				pos.getY() + SEED_SPAWN_HEIGHT,
				pos.getZ() + 0.5D,
				server.getRandom().nextIntBetweenInclusive(MIN_SEEDS_DESTROY, MAX_SEEDS_DESTROY),
				0D,
				0D,
				0D,
				0D
			);
		}
	}
}
