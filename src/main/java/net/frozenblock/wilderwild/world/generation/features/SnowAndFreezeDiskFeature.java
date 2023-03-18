/*
 * Copyright 2022-2023 FrozenBlock
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

package net.frozenblock.wilderwild.world.generation.features;

import com.mojang.serialization.Codec;
import net.frozenblock.wilderwild.world.generation.features.config.SnowAndIceDiskFeatureConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.Heightmap.Types;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;

public class SnowAndFreezeDiskFeature extends Feature<SnowAndIceDiskFeatureConfig> {
	private static final BlockState snowState = Blocks.SNOW.defaultBlockState();
	private static final BlockState iceState = Blocks.ICE.defaultBlockState();

    public SnowAndFreezeDiskFeature(Codec<SnowAndIceDiskFeatureConfig> codec) {
        super(codec);
    }

    public boolean place(FeaturePlaceContext<SnowAndIceDiskFeatureConfig> context) {
        boolean bl = false;
        BlockPos blockPos = context.origin();
        WorldGenLevel level = context.level();
		SnowAndIceDiskFeatureConfig config = context.config();
        BlockPos s = blockPos.atY(level.getHeight(Types.MOTION_BLOCKING, blockPos.getX(), blockPos.getZ()));
		Biome biome = level.getBiome(s).value();
		if (biome.shouldSnow(level, s)) {
			RandomSource random = level.getRandom();
			int radius = config.radius.sample(random);
			//DISK
			BlockPos.MutableBlockPos mutableDisk = s.mutable();
			BlockPos.MutableBlockPos mutableDisk2 = new BlockPos.MutableBlockPos();
			int bx = s.getX();
			int bz = s.getZ();
			for (int x = bx - radius; x <= bx + radius; x++) {
				for (int z = bz - radius; z <= bz + radius; z++) {
					double distance = ((bx - x) * (bx - x) + (bz - z) * (bz - z));
					if (distance < radius * radius) {
						mutableDisk.set(x, level.getHeight(Types.MOTION_BLOCKING, x, z), z);
						mutableDisk2.set(mutableDisk).move(Direction.DOWN);
						BlockState state = level.getBlockState(mutableDisk);
						if (state != snowState) {
							boolean fade = !mutableDisk.closerThan(s, radius * config.startFadePercent);
							if (random.nextFloat() < config.placeChance && state.isAir()) {
								if (fade) {
									if (random.nextFloat() > 0.5F) {
										if (snowState.canSurvive(level, mutableDisk.move(Direction.DOWN))) {
											BlockState belowState = level.getBlockState(mutableDisk);
											if (belowState.getValue(BlockStateProperties.SNOWY)) {
												level.setBlock(mutableDisk, belowState.setValue(BlockStateProperties.SNOWY, true), 2);
											}
											mutableDisk.move(Direction.UP);
											level.setBlock(mutableDisk, snowState, 2);
											bl = true;
										} else {
											mutableDisk.move(Direction.UP);
										}
									}
								} else {
									if (snowState.canSurvive(level, mutableDisk.move(Direction.DOWN))) {
										BlockState belowState = level.getBlockState(mutableDisk);
										if (belowState.getValue(BlockStateProperties.SNOWY)) {
											level.setBlock(mutableDisk, belowState.setValue(BlockStateProperties.SNOWY, true), 2);
										}
										mutableDisk.move(Direction.UP);
										level.setBlock(mutableDisk, snowState, 2);
										bl = true;
									} else {
										mutableDisk.move(Direction.UP);
									}
								}
							}
						}
					}
				}
			}
		}
		if (biome.shouldFreeze(level, s.below(), false)) {
			RandomSource random = level.getRandom();
			int radius = config.iceRadius.sample(random);
			//DISK
			BlockPos.MutableBlockPos mutableDisk = s.mutable();
			BlockPos.MutableBlockPos mutableDisk2 = new BlockPos.MutableBlockPos();
			int bx = s.getX();
			int bz = s.getZ();
			for (int x = bx - radius; x <= bx + radius; x++) {
				for (int z = bz - radius; z <= bz + radius; z++) {
					double distance = ((bx - x) * (bx - x) + (bz - z) * (bz - z));
					if (distance < radius * radius) {
						mutableDisk.set(x, level.getHeight(Types.MOTION_BLOCKING, x, z), z);
						mutableDisk2.set(mutableDisk).move(Direction.DOWN);
						BlockState state = level.getBlockState(mutableDisk2);
						if (state != iceState) {
							boolean fade = !mutableDisk.closerThan(s, radius * config.startFadePercent);
							if (random.nextFloat() < config.placeChance && state.isAir()) {
								if (fade) {
									if (random.nextFloat() > 0.5F) {
										if (canPlaceIce(level, mutableDisk2)) {
											level.setBlock(mutableDisk2, iceState, 2);
										}
									}
								} else {
									if (canPlaceIce(level, mutableDisk2)) {
										level.setBlock(mutableDisk2, iceState, 2);
									}
								}
							}
						}
					}
				}
			}
		}
        return bl;
    }

	public static boolean canPlaceIce(LevelReader level, BlockPos water) {
		if (water.getY() >= level.getMinBuildHeight() && water.getY() < level.getMaxBuildHeight() && level.getBrightness(LightLayer.BLOCK, water) < 10) {
			BlockState blockState = level.getBlockState(water);
			FluidState fluidState = level.getFluidState(water);
			return fluidState.getType() == Fluids.WATER && blockState.getBlock() instanceof LiquidBlock;
		}
		return false;
	}

}