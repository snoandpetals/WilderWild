package net.frozenblock.wilderwild.entity.render.blockentity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Quaternion;
import com.mojang.math.Vector3f;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.frozenblock.wilderwild.WilderWildClient;
import net.frozenblock.wilderwild.block.entity.HangingTendrilBlockEntity;
import net.frozenblock.wilderwild.misc.config.ClothConfigInteractionHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider.Context;
import org.jetbrains.annotations.NotNull;

@Environment(EnvType.CLIENT)
public class HangingTendrilBlockEntityRenderer<T extends HangingTendrilBlockEntity> implements BlockEntityRenderer<T> {
    private final ModelPart base;

	private final Quaternion rotation = new Quaternion(0F, 0F, 0F, 1F);

    public HangingTendrilBlockEntityRenderer(Context ctx) {
        ModelPart root = ctx.bakeLayer(WilderWildClient.HANGING_TENDRIL);
        this.base = root.getChild("base");
    }

    public static LayerDefinition getTexturedModelData() {
        MeshDefinition modelData = new MeshDefinition();
        PartDefinition modelPartData = modelData.getRoot();
        modelPartData.addOrReplaceChild("base", CubeListBuilder.create().texOffs(0, 0).addBox(-8.0F, -16.0F, 0.0F, 16.0F, 16.0F, 0.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, (float) Math.PI, 0.0F, 0.0F));
		return LayerDefinition.create(modelData, 16, 16);
    }

    public void render(@NotNull T entity, float tickDelta, @NotNull PoseStack poseStack, @NotNull MultiBufferSource vertexConsumers, int light, int overlay) {
        if (ClothConfigInteractionHandler.billboardTendrils()) {
			this.rotation.set(0.0f, 0.0f, 0.0f, 1.0f);
			this.rotation.mul(Vector3f.YP.rotationDegrees(-Minecraft.getInstance().gameRenderer.getMainCamera().getYRot()));
			poseStack.translate(0.5, 0, 0.5);
			poseStack.pushPose();
			poseStack.mulPose(this.rotation);
            this.base.render(poseStack, vertexConsumers.getBuffer(RenderType.entityCutout(entity.texture)), light, overlay, 1.0F, 1.0F, 1.0F, 1.0F);
        	poseStack.popPose();
		}
    }

}