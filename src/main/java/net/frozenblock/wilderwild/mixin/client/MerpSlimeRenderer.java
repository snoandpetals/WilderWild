package net.frozenblock.wilderwild.mixin.client;

import net.frozenblock.wilderwild.WilderWild;
import net.minecraft.ChatFormatting;
import net.minecraft.client.renderer.entity.SlimeRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Slime;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Objects;

@OnlyIn(Dist.CLIENT)
@Mixin(SlimeRenderer.class)
public final class MerpSlimeRenderer {

    @Unique
    private static final ResourceLocation WILDERWILD$MERP_SLIME = WilderWild.id("textures/entity/slime/merp_slime.png");

    @Inject(method = "getTextureLocation(Lnet/minecraft/world/entity/monster/Slime;)Lnet/minecraft/resources/ResourceLocation;", at = @At("RETURN"), cancellable = true)
    public void getTextureLocation(Slime slimeEntity, CallbackInfoReturnable<ResourceLocation> cir) {
        String string = ChatFormatting.stripFormatting(slimeEntity.getName().getString());
        if (Objects.equals(string, "Merp")) {
            cir.setReturnValue(WILDERWILD$MERP_SLIME);
        }
    }
}
