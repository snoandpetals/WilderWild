package net.frozenblock.wilderwild.mixin;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.ScatteredFeaturePiece;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(ScatteredFeaturePiece.class)
public interface ScatteredFeaturePieceAccessor {
    @Invoker
    boolean callUpdateAverageGroundHeight(LevelAccessor p_72804_, BoundingBox p_72805_, int p_72806_);
}
