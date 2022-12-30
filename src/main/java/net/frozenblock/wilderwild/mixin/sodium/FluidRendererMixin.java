package net.frozenblock.wilderwild.mixin.sodium;

import me.jellysquid.mods.sodium.client.render.pipeline.FluidRenderer;
import net.frozenblock.wilderwild.block.MesogleaBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.material.Fluid;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FluidRenderer.class)
public class FluidRendererMixin {

	@Inject(method = "isFluidOccluded", at = @At(value = "HEAD"), cancellable = true)
	private static void isFluidOccluded(BlockAndTintGetter world, int x, int y, int z, Direction dir, Fluid fluid, CallbackInfoReturnable<Boolean> info) {
		if (world.getBlockState(new BlockPos(x, y, z)).getBlock() instanceof MesogleaBlock && dir != Direction.UP) {
			info.setReturnValue(false);
		}
	}

}
