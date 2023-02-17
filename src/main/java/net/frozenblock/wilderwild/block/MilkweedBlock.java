package net.frozenblock.wilderwild.block;

import net.frozenblock.wilderwild.init.WWBlocks;
import net.frozenblock.wilderwild.init.WWItems;
import net.frozenblock.wilderwild.util.NetworkUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.TallFlowerBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

public class MilkweedBlock extends TallFlowerBlock {

    public MilkweedBlock(Properties settings) {
        super(settings);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.@NotNull Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(BlockStateProperties.AGE_3);
    }

    @Override
    public void randomTick(@NotNull BlockState state, @NotNull ServerLevel level, @NotNull BlockPos pos, RandomSource random) {
        if (random.nextFloat() > 0.8F) {
            if (state.is(WWBlocks.MILKWEED.get())) {
                if (state.getValue(BlockStateProperties.DOUBLE_BLOCK_HALF) == DoubleBlockHalf.LOWER) {
                    if (state.getValue(BlockStateProperties.AGE_3) < 3) {
                        if (level.getBlockState(pos).is(WWBlocks.MILKWEED.get())) {
                            level.setBlockAndUpdate(pos, state.setValue(BlockStateProperties.AGE_3, state.getValue(BlockStateProperties.AGE_3) + 1));
                        }
                        if (level.getBlockState(pos.above()).is(WWBlocks.MILKWEED.get())) {
                            level.setBlockAndUpdate(pos.above(), level.getBlockState(pos.above()).setValue(BlockStateProperties.AGE_3, state.getValue(BlockStateProperties.AGE_3) + 1));
                        }
                    }
                }
            }
        }
    }

    @Override
    public InteractionResult use(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult hit) {
        if (!level.isClientSide) {
            if (state.getValue(BlockStateProperties.AGE_3) == 3) {
                ItemStack itemStack = player.getItemInHand(hand);
                if (itemStack.is(Items.SHEARS)) {
                    ItemStack stack = new ItemStack(WWItems.MILKWEED_POD.get());
                    stack.setCount(level.random.nextIntBetweenInclusive(2, 7));
                    popResource(level, pos, stack);
                    level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.GROWING_PLANT_CROP, SoundSource.BLOCKS, 1.0F, 1.0F);
                    itemStack.hurtAndBreak(1, player, (playerx) -> playerx.broadcastBreakEvent(hand));
                    level.gameEvent(player, GameEvent.SHEAR, pos);
                    if (state.getValue(BlockStateProperties.DOUBLE_BLOCK_HALF) == DoubleBlockHalf.LOWER) {
                        level.setBlockAndUpdate(pos, state.setValue(BlockStateProperties.AGE_3, 0));
                        level.setBlockAndUpdate(pos.above(), level.getBlockState(pos.above()).setValue(BlockStateProperties.AGE_3, 0));
                    } else if (state.getValue(BlockStateProperties.DOUBLE_BLOCK_HALF) == DoubleBlockHalf.UPPER) {
                        level.setBlockAndUpdate(pos, state.setValue(BlockStateProperties.AGE_3, 0));
                        level.setBlockAndUpdate(pos.below(), level.getBlockState(pos.below()).setValue(BlockStateProperties.AGE_3, 0));
                    }
                } else {
                    NetworkUtil.sendSeedParticlePacket(level, Vec3.atCenterOf(pos).add(0, 0.3, 0), level.random.nextIntBetweenInclusive(14, 28), true, 48);
                    if (state.getValue(BlockStateProperties.DOUBLE_BLOCK_HALF) == DoubleBlockHalf.LOWER) {
                        level.setBlockAndUpdate(pos, state.setValue(BlockStateProperties.AGE_3, 1));
                        level.setBlockAndUpdate(pos.above(), level.getBlockState(pos.above()).setValue(BlockStateProperties.AGE_3, 1));
                    } else if (state.getValue(BlockStateProperties.DOUBLE_BLOCK_HALF) == DoubleBlockHalf.UPPER) {
                        level.setBlockAndUpdate(pos, state.setValue(BlockStateProperties.AGE_3, 1));
                        level.setBlockAndUpdate(pos.below(), level.getBlockState(pos.below()).setValue(BlockStateProperties.AGE_3, 1));
                    }
                }
                return InteractionResult.SUCCESS;
            }
        }
        return super.use(state, level, pos, player, hand, hit);
    }
}
