package net.frozenblock.wilderwild.mixin.client.easter;

import com.google.common.collect.ImmutableList;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.frozenblock.wilderwild.entity.render.WilderWardenModel;
import net.minecraft.client.model.WardenModel;
import net.minecraft.client.model.geom.ModelPart;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import java.util.List;

@Environment(EnvType.CLIENT)
@Mixin(WardenModel.class)
public class OsmioooWardenModel implements WilderWardenModel {

	@Shadow
	@Final
	protected ModelPart head;
	@Shadow
	@Final
	protected ModelPart leftTendril;
	@Shadow
	@Final
	protected ModelPart rightTendril;
	@Unique
	private List<ModelPart> wilderWild$headAndTendrils;

	@Inject(method = "<init>", at = @At("TAIL"))
	private void setHeadAndTendrils(ModelPart root, CallbackInfo ci) {
		this.wilderWild$headAndTendrils = ImmutableList.of(this.head, this.leftTendril, this.rightTendril);
	}

	@Unique
	@Override
	public List<ModelPart> getHeadAndTendrils() {
		return this.wilderWild$headAndTendrils;
	}
}
