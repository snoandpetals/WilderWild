package net.frozenblock.wilderwild.mixin.client;

import com.google.common.collect.ImmutableList;
import net.frozenblock.wilderwild.util.interfaces.WilderWardenModel;
import net.minecraft.client.model.WardenModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@OnlyIn(Dist.CLIENT)
@Mixin(WardenModel.class)
public abstract class OsmioooWardenModel<T extends Warden> implements WilderWardenModel {

    @Final
    @Shadow
    protected ModelPart body;

    @Final
    @Shadow
    protected ModelPart head;

    @Final
    @Shadow
    protected ModelPart rightTendril;

    @Final
    @Shadow
    protected ModelPart leftTendril;

	@Unique
    private List<ModelPart> wilderWild$headAndTendrils;

    @Inject(method = "<init>", at = @At("TAIL"))
    private void setHeadAndTendrils(ModelPart root, CallbackInfo ci) {
        this.wilderWild$headAndTendrils = ImmutableList.of(this.head, this.leftTendril, this.rightTendril);
    }

    @Override
    public List<ModelPart> getHeadAndTendrils() {
        return this.wilderWild$headAndTendrils;
    }
}
