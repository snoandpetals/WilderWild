package net.frozenblock.wilderwild.mixin.accessors;

import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.world.entity.AnimationState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(HierarchicalModel.class)
public interface HierarchicalModelAccessor {
    @Invoker
    void callAnimate(AnimationState p_233382_, AnimationDefinition p_233383_, float p_233384_);
}
