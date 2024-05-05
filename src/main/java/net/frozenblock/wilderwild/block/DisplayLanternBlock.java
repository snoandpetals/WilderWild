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

import java.util.Objects;
import java.util.Optional;
import net.frozenblock.lib.math.api.AdvancedMath;
import net.frozenblock.wilderwild.block.entity.DisplayLanternBlockEntity;
import net.frozenblock.wilderwild.entity.variant.FireflyColor;
import net.frozenblock.wilderwild.item.FireflyBottle;
import net.frozenblock.wilderwild.registry.RegisterBlockEntities;
import net.frozenblock.wilderwild.registry.RegisterItems;
import net.frozenblock.wilderwild.registry.RegisterProperties;
import net.frozenblock.wilderwild.registry.RegisterSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.lighting.LightEngine;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("deprecation")
public class DisplayLanternBlock extends BaseEntityBlock implements SimpleWaterloggedBlock {
	public static final int MAX_FIREFLIES = 4;
	public static final int LIGHT_PER_FIREFLY = 3;
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	public static final BooleanProperty HANGING = BlockStateProperties.HANGING;
	public static final IntegerProperty DISPLAY_LIGHT = RegisterProperties.DISPLAY_LIGHT;
	protected static final VoxelShape STANDING_SHAPE = Shapes.or(Block.box(5.0D, 0.0D, 5.0D, 11.0D, 7.0D, 11.0D), Block.box(6.0D, 7.0D, 6.0D, 10.0D, 8.0D, 10.0D));
	protected static final VoxelShape HANGING_SHAPE = Shapes.or(Block.box(5.0D, 2.0D, 5.0D, 11.0D, 9.0D, 11.0D), Block.box(6.0D, 9.0D, 6.0D, 10.0D, 10.0D, 10.0D));

	public DisplayLanternBlock(@NotNull Properties settings) {
		super(settings.pushReaction(PushReaction.DESTROY));
		this.registerDefaultState(this.stateDefinition.any().setValue(HANGING, false).setValue(WATERLOGGED, false).setValue(DISPLAY_LIGHT, 0));
	}

	private static Direction attachedDirection(@NotNull BlockState state) {
		return state.getValue(HANGING) ? Direction.DOWN : Direction.UP;
	}

	@Override
	@NotNull
	public InteractionResult use(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult hit) {
		if (level.isClientSide) {
			return InteractionResult.SUCCESS;
		}
		BlockEntity entity = level.getBlockEntity(pos);
		if (entity instanceof DisplayLanternBlockEntity lantern) {
			ItemStack itemStack = player.getItemInHand(hand);
			if (lantern.invEmpty()) {
				if (itemStack.getItem() instanceof FireflyBottle bottle) {
					if (lantern.getFireflies().size() < MAX_FIREFLIES) {
						String name = "";
						if (itemStack.hasCustomHoverName()) {
							name = itemStack.getHoverName().getString();
						}
						lantern.addFirefly(level, bottle, name);
						if (!player.isCreative()) {
							player.getItemInHand(hand).shrink(1);
						}
						player.getInventory().placeItemBackInInventory(new ItemStack(Items.GLASS_BOTTLE));
						level.setBlockAndUpdate(pos, state.setValue(DISPLAY_LIGHT, Mth.clamp(lantern.getFireflies().size() * LIGHT_PER_FIREFLY, 0, LightEngine.MAX_LEVEL)));
						level.playSound(null, pos, RegisterSounds.ITEM_BOTTLE_PUT_IN_LANTERN_FIREFLY, SoundSource.BLOCKS, 1F, level.random.nextFloat() * 0.2F + 0.9F);
						lantern.setChanged();
						level.updateNeighbourForOutputSignal(pos, this);
						return InteractionResult.SUCCESS;
					}
				}
				if (itemStack.is(Items.GLASS_BOTTLE)) {
					if (!lantern.getFireflies().isEmpty()) {
						DisplayLanternBlockEntity.Occupant fireflyInLantern = lantern.getFireflies().get(AdvancedMath.random().nextInt(lantern.getFireflies().size()));
						Optional<Item> optionalItem = BuiltInRegistries.ITEM.getOptional(new ResourceLocation(fireflyInLantern.color.key().getNamespace(), Objects.equals(fireflyInLantern.color, FireflyColor.ON) ? "firefly_bottle" : fireflyInLantern.color.key().getPath() + "_firefly_bottle"));
						Item item = RegisterItems.FIREFLY_BOTTLE;
						if (optionalItem.isPresent()) {
							item = optionalItem.get();
						}
						level.playSound(null, pos, RegisterSounds.ITEM_BOTTLE_CATCH_FIREFLY, SoundSource.BLOCKS, 1F, level.random.nextFloat() * 0.2F + 0.9F);
						if (!player.isCreative()) {
							player.getItemInHand(hand).shrink(1);
						}
						ItemStack bottleStack = new ItemStack(item);
						if (!Objects.equals(fireflyInLantern.customName, "")) {
							bottleStack.setHoverName(Component.nullToEmpty(fireflyInLantern.customName));
						}
						player.getInventory().placeItemBackInInventory(bottleStack);
						((DisplayLanternBlockEntity) entity).removeFirefly(fireflyInLantern);
						level.setBlockAndUpdate(pos, state.setValue(DISPLAY_LIGHT, Mth.clamp(lantern.getFireflies().size() * LIGHT_PER_FIREFLY, 0, LightEngine.MAX_LEVEL)));
						lantern.setChanged();
						level.updateNeighbourForOutputSignal(pos, this);
						return InteractionResult.SUCCESS;
					}
				}
				if (!itemStack.isEmpty() && lantern.noFireflies()) {
					int light = 0;
					if (itemStack.getItem() instanceof BlockItem blockItem) {
						light = blockItem.getBlock().defaultBlockState().getLightEmission();
					} else if (itemStack.isEnchanted()) {
						light = (int) Math.round(itemStack.getEnchantmentTags().size() * 0.5);
					}
					level.setBlockAndUpdate(pos, state.setValue(DISPLAY_LIGHT, Mth.clamp(light, 0, LightEngine.MAX_LEVEL)));
					lantern.inventory.set(0, itemStack.split(1));
					lantern.setChanged();
					level.updateNeighbourForOutputSignal(pos, this);
					return InteractionResult.SUCCESS;
				}
			} else if (lantern.noFireflies()) {
				Optional<ItemStack> stack1 = lantern.inventory.stream().findFirst();
				if (stack1.isPresent()) {
					popResource(level, pos, stack1.get());
					lantern.inventory.clear();
					lantern.setChanged();
					level.setBlockAndUpdate(pos, state.setValue(DISPLAY_LIGHT, 0));
					level.updateNeighbourForOutputSignal(pos, this);
					return InteractionResult.SUCCESS;
				}
			}
		}
		return InteractionResult.PASS;
	}

	@Nullable
	@Override
	public BlockState getStateForPlacement(@NotNull BlockPlaceContext ctx) {
		FluidState fluidState = ctx.getLevel().getFluidState(ctx.getClickedPos());
		for (Direction direction : ctx.getNearestLookingDirections()) {
			if (direction.getAxis() == Direction.Axis.Y) {
				BlockState blockState = this.defaultBlockState().setValue(HANGING, direction == Direction.UP);
				if (blockState.canSurvive(ctx.getLevel(), ctx.getClickedPos())) {
					return blockState.setValue(WATERLOGGED, fluidState.getType() == Fluids.WATER);
				}
			}
		}
		return null;
	}

	@Override
	@NotNull
	public VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull CollisionContext context) {
		return state.getValue(HANGING) ? HANGING_SHAPE : STANDING_SHAPE;
	}

	@Override
	protected void createBlockStateDefinition(@NotNull StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(HANGING, WATERLOGGED, DISPLAY_LIGHT);
	}

	@Override
	public boolean canSurvive(@NotNull BlockState state, @NotNull LevelReader level, @NotNull BlockPos pos) {
		Direction direction = attachedDirection(state).getOpposite();
		return Block.canSupportCenter(level, pos.relative(direction), direction.getOpposite());
	}

	@Override
	@NotNull
	public RenderShape getRenderShape(@NotNull BlockState blockState) {
		return RenderShape.MODEL;
	}

	@Override
	@NotNull
	public BlockState updateShape(@NotNull BlockState state, @NotNull Direction direction, @NotNull BlockState neighborState, @NotNull LevelAccessor level, @NotNull BlockPos currentPos, @NotNull BlockPos neighborPos) {
		if (state.getValue(WATERLOGGED)) {
			level.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
		}

		if (attachedDirection(state).getOpposite() == direction && !state.canSurvive(level, currentPos)) {
			BlockEntity entity = level.getBlockEntity(currentPos);
			if (entity instanceof DisplayLanternBlockEntity lanternEntity) {
				lanternEntity.spawnFireflies();
			}
			return Blocks.AIR.defaultBlockState();
		}
		return super.updateShape(state, direction, neighborState, level, currentPos, neighborPos);
	}

	@Override
	public void onRemove(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull BlockState newState, boolean movedByPiston) {
		if (!state.is(newState.getBlock())) {
			if (level.getBlockEntity(pos) instanceof DisplayLanternBlockEntity lantern) {
				for (ItemStack item : lantern.inventory) {
					popResource(level, pos, item);
				}
				lantern.inventory.clear();
				level.updateNeighbourForOutputSignal(pos, this);
			}
		}
		super.onRemove(state, level, pos, newState, movedByPiston);
	}

	@Override
	@NotNull
	public FluidState getFluidState(@NotNull BlockState state) {
		return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
	}

	@Override
	public boolean isPathfindable(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull PathComputationType type) {
		return false;
	}

	@Nullable
	@Override
	public BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
		return new DisplayLanternBlockEntity(pos, state);
	}

	@Override
	@Nullable
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(@NotNull Level level, @NotNull BlockState state, @NotNull BlockEntityType<T> type) {
		return !level.isClientSide ?
			createTickerHelper(type, RegisterBlockEntities.DISPLAY_LANTERN, (worldx, pos, statex, blockEntity) -> blockEntity.serverTick(level, pos)) :
			createTickerHelper(type, RegisterBlockEntities.DISPLAY_LANTERN, (worldx, pos, statex, blockEntity) -> blockEntity.clientTick(level, pos));
	}

	@Override
	public boolean hasAnalogOutputSignal(@NotNull BlockState state) {
		return true;
	}

	@Override
	public int getAnalogOutputSignal(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos) {
		BlockEntity blockEntity = level.getBlockEntity(pos);
		if (blockEntity instanceof DisplayLanternBlockEntity displayLanternBlockEntity) {
			return displayLanternBlockEntity.getComparatorOutput();
		}
		return 0;
	}

	@Override
	public void playerDestroy(@NotNull Level level, @NotNull Player player, @NotNull BlockPos pos, @NotNull BlockState state, @Nullable BlockEntity blockEntity, @NotNull ItemStack stack) {
		if (!level.isClientSide && blockEntity instanceof DisplayLanternBlockEntity lanternEntity) {
			if (EnchantmentHelper.getItemEnchantmentLevel(Enchantments.SILK_TOUCH, stack) == 0) {
				lanternEntity.spawnFireflies(level);
			}
		}
		super.playerDestroy(level, player, pos, state, blockEntity, stack);
	}

}
