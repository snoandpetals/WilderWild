package net.frozenblock.wilderwild.mixin.accessors;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.StructurePiece;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(StructurePiece.class)
public interface StructurePieceAccessor {
    @Invoker
    BlockPos.MutableBlockPos callGetWorldPos(int p_163583_, int p_163584_, int p_163585_);

    @Invoker
    void callFillColumnDown(WorldGenLevel p_73529_, BlockState p_73530_, int p_73531_, int p_73532_, int p_73533_, BoundingBox p_73534_);

    @Invoker
    void callPlaceBlock(WorldGenLevel p_73435_, BlockState p_73436_, int p_73437_, int p_73438_, int p_73439_, BoundingBox p_73440_);

    @Invoker
    void callGenerateBox(WorldGenLevel p_73442_, BoundingBox p_73443_, int p_73444_, int p_73445_, int p_73446_, int p_73447_, int p_73448_, int p_73449_, BlockState p_73450_, BlockState p_73451_, boolean p_73452_);
}
