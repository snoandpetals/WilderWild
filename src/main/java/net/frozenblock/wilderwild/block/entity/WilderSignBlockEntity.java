package net.frozenblock.wilderwild.block.entity;

import net.frozenblock.wilderwild.init.WWBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class WilderSignBlockEntity extends SignBlockEntity {
    public WilderSignBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(pWorldPosition, pBlockState);
    }

    @Override
    public BlockEntityType<?> getType() {
        return WWBlockEntityTypes.BAOBAB_SIGN_BLOCK_ENTITIES.get();
    }
}
