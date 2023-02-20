package net.frozenblock.wilderwild.mixin.client;

import net.frozenblock.wilderwild.api.EntityTextureOverride;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@OnlyIn(Dist.CLIENT)
@Mixin(LivingEntityRenderer.class)
public abstract class LivingEntityRendererMixin<T extends LivingEntity, M extends EntityModel<T>> extends EntityRenderer<T> {

    @Shadow
    protected M model;

    protected LivingEntityRendererMixin(EntityRendererProvider.Context context) {
        super(context);
    }

    @Inject(method = "getRenderType", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/model/EntityModel;renderType(Lnet/minecraft/resources/ResourceLocation;)Lnet/minecraft/client/renderer/RenderType;"), cancellable = true)
    private void getEasterEgg(T livingEntity, boolean bodyVisible, boolean translucent, boolean glowing, CallbackInfoReturnable<RenderType> cir) {
        EntityTextureOverride.ENTITY_TEXTURE_OVERRIDES.forEach((key, value) -> {
            EntityTextureOverride<T> override = (EntityTextureOverride<T>) value;
            ResourceLocation texture = override.texture();
            if (override.type() == livingEntity.getType() && texture != null && override.condition().condition(livingEntity)) {
                cir.setReturnValue(this.model.renderType(texture));
            }
        });
    }

    @Inject(method = "getRenderType", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/RenderType;itemEntityTranslucentCull(Lnet/minecraft/resources/ResourceLocation;)Lnet/minecraft/client/renderer/RenderType;"), cancellable = true)
    private void getItemEasterEgg(T livingEntity, boolean bodyVisible, boolean translucent, boolean glowing, CallbackInfoReturnable<RenderType> cir) {
        EntityTextureOverride.ENTITY_TEXTURE_OVERRIDES.forEach((key, value) -> {
            EntityTextureOverride<T> override = (EntityTextureOverride<T>) value;
            ResourceLocation texture = override.texture();
            if (override.type() == livingEntity.getType() && texture != null && override.condition().condition(livingEntity)) {
                cir.setReturnValue(RenderType.itemEntityTranslucentCull(texture));
            }
        });
    }

    @Inject(method = "getRenderType", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/RenderType;outline(Lnet/minecraft/resources/ResourceLocation;)Lnet/minecraft/client/renderer/RenderType;", shift = At.Shift.BEFORE), cancellable = true)
    private void getOutlineEasterEgg(T livingEntity, boolean bodyVisible, boolean translucent, boolean glowing, CallbackInfoReturnable<RenderType> cir) {
        if (glowing) {
            EntityTextureOverride.ENTITY_TEXTURE_OVERRIDES.forEach((key, value) -> {
                EntityTextureOverride<T> override = (EntityTextureOverride<T>) value;
                ResourceLocation texture = override.texture();
                if (override.type() == livingEntity.getType() && texture != null && override.condition().condition(livingEntity)) {
                    cir.setReturnValue(RenderType.outline(texture));
                }
            });
        }
    }

}
