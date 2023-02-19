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

package net.frozenblock.wilderwild.mixin.server.sculk;

import net.frozenblock.wilderwild.misc.WilderSharedConstants;
import net.frozenblock.wilderwild.misc.interfaces.SculkSensorTickInterface;
import net.frozenblock.wilderwild.registry.RegisterProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SculkSensorBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.SculkSensorBlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SculkSensorBlock.class)
public abstract class SculkSensorBlockMixin extends BaseEntityBlock implements SimpleWaterloggedBlock {

    private SculkSensorBlockMixin(Properties properties) {
        super(properties);
    }

    @Inject(at = @At("TAIL"), method = "createBlockStateDefinition")
    private void wilderWild$addHiccuppingState(StateDefinition.Builder<Block, BlockState> builder, CallbackInfo info) {
        builder.add(RegisterProperties.HICCUPPING);
    }

    @Inject(method = "<init>", at = @At("TAIL"))
    private void wilderWild$registerDefaultHiccupping(BlockBehaviour.Properties settings, int range, CallbackInfo info) {
        SculkSensorBlock sculkSensor = SculkSensorBlock.class.cast(this);
        sculkSensor.registerDefaultState(sculkSensor.defaultBlockState().setValue(RegisterProperties.HICCUPPING, false));
    }

    @Inject(at = @At("HEAD"), method = "getTicker", cancellable = true)
    public <T extends BlockEntity> void wilderWild$overrideTicker(Level level, BlockState state, BlockEntityType<T> type, CallbackInfoReturnable<BlockEntityTicker<T>> info) {
        if (level.isClientSide) {
            info.setReturnValue(createTickerHelper(type, BlockEntityType.SCULK_SENSOR, (worldx, pos, statex, blockEntity) -> {
                ((SculkSensorTickInterface) blockEntity).wilderWild$tickClient(worldx, pos, statex);
            }));
        } else {
            info.setReturnValue(createTickerHelper(type, BlockEntityType.SCULK_SENSOR, (worldx, pos, statex, blockEntity) -> {
                ((SculkSensorTickInterface) blockEntity).wilderWild$tickServer((ServerLevel) worldx, pos, statex);
            }));
        }
    }

    @Inject(at = @At("HEAD"), method = "activate")
    private static void wilderWild$activate(@Nullable Entity entity, Level level, BlockPos pos, BlockState state, int power, CallbackInfo info) {
        if (level.getBlockEntity(pos) instanceof SculkSensorBlockEntity blockEntity) {
            ((SculkSensorTickInterface) blockEntity).wilderWild$setActive(true);
            ((SculkSensorTickInterface) blockEntity).wilderWild$setAnimTicks(10);
        }
    }

    @Inject(at = @At("HEAD"), method = "getRenderShape", cancellable = true)
    public void wilderWild$getRenderShape(BlockState state, CallbackInfoReturnable<RenderShape> info) {
        info.setReturnValue(WilderSharedConstants.config().mcLiveSensorTendrils() ? RenderShape.INVISIBLE : RenderShape.MODEL);
    }

}