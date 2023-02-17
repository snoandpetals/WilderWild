package net.frozenblock.wilderwild.init;

import com.google.common.collect.Maps;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.block.FireBlock;

import java.util.Map;

public class WWVanillaIntegration {

    public static void init() {
        registerFlammables();
        registerCompostables();
        registerStrippables();
    }

    private static void registerStrippables() {
        Map<Block, Block> axeItem = Maps.newHashMap(AxeItem.STRIPPABLES);
        axeItem.put(WWBlocks.BAOBAB_LOG.get(), WWBlocks.STRIPPED_BAOBAB_LOG.get());
        axeItem.put(WWBlocks.BAOBAB_WOOD.get(), WWBlocks.STRIPPED_BAOBAB_WOOD.get());
        axeItem.put(WWBlocks.CYPRESS_LOG.get(), WWBlocks.STRIPPED_CYPRESS_LOG.get());
        axeItem.put(WWBlocks.CYPRESS_WOOD.get(), WWBlocks.STRIPPED_CYPRESS_WOOD.get());
    }

    private static void registerCompostables() {
        ComposterBlock.add(0.65F, WWBlocks.CARNATION.get().asItem());
        ComposterBlock.add(0.65F, WWBlocks.CATTAIL.get().asItem());
        ComposterBlock.add(0.65F, WWBlocks.DATURA.get().asItem());
        ComposterBlock.add(0.65F, WWBlocks.MILKWEED.get().asItem());
        ComposterBlock.add(0.25F, WWItems.MILKWEED_POD.get());
        ComposterBlock.add(0.65F, WWBlocks.SEEDING_DANDELION.get().asItem());
        ComposterBlock.add(0.65F, WWBlocks.FLOWERING_LILY_PAD.get().asItem());
        ComposterBlock.add(0.65F, WWBlocks.BROWN_SHELF_FUNGUS.get().asItem());
        ComposterBlock.add(0.65F, WWBlocks.RED_SHELF_FUNGUS.get().asItem());
        ComposterBlock.add(0.3F, WWBlocks.CYPRESS_LEAVES.get().asItem());
        ComposterBlock.add(0.3F, WWBlocks.BAOBAB_LEAVES.get().asItem());
        ComposterBlock.add(0.3F, WWBlocks.BAOBAB_NUT.get().asItem());
        ComposterBlock.add(0.3F, WWBlocks.CYPRESS_SAPLING.get().asItem());
        ComposterBlock.add(0.65F, WWBlocks.GLORY_OF_THE_SNOW.get().asItem());
        ComposterBlock.add(0.65F, WWBlocks.BLUE_GLORY_OF_THE_SNOW.get().asItem());
        ComposterBlock.add(0.65F, WWBlocks.WHITE_GLORY_OF_THE_SNOW.get().asItem());
        ComposterBlock.add(0.65F, WWBlocks.PINK_GLORY_OF_THE_SNOW.get().asItem());
        ComposterBlock.add(0.65F, WWBlocks.PURPLE_GLORY_OF_THE_SNOW.get().asItem());
        ComposterBlock.add(0.3F, WWBlocks.ALGAE.get().asItem());
    }

    private static void registerFlammables() {
        FireBlock fireBlock = (FireBlock) Blocks.FIRE;
        fireBlock.setFlammable(WWBlocks.POLLEN_BLOCK.get(), 100, 60);
        fireBlock.setFlammable(WWBlocks.SEEDING_DANDELION.get(), 100, 60);
        fireBlock.setFlammable(WWBlocks.CARNATION.get(), 100, 60);
        fireBlock.setFlammable(WWBlocks.CATTAIL.get(), 100, 60);
        fireBlock.setFlammable(WWBlocks.DATURA.get(), 100, 60);
        fireBlock.setFlammable(WWBlocks.MILKWEED.get(), 100, 60);

        fireBlock.setFlammable(WWBlocks.HOLLOWED_BIRCH_LOG.get(), 5, 5);
        fireBlock.setFlammable(WWBlocks.HOLLOWED_OAK_LOG.get(), 5, 5);
        fireBlock.setFlammable(WWBlocks.HOLLOWED_ACACIA_LOG.get(), 5, 5);
        fireBlock.setFlammable(WWBlocks.HOLLOWED_JUNGLE_LOG.get(), 5, 5);
        fireBlock.setFlammable(WWBlocks.HOLLOWED_DARK_OAK_LOG.get(), 5, 5);
        fireBlock.setFlammable(WWBlocks.HOLLOWED_MANGROVE_LOG.get(), 5, 5);
        fireBlock.setFlammable(WWBlocks.HOLLOWED_SPRUCE_LOG.get(), 5, 5);
        fireBlock.setFlammable(WWBlocks.HOLLOWED_BAOBAB_LOG.get(), 5, 5);
        fireBlock.setFlammable(WWBlocks.BAOBAB_LOG.get(), 5, 5);
        fireBlock.setFlammable(WWBlocks.STRIPPED_BAOBAB_LOG.get(), 5, 5);
        fireBlock.setFlammable(WWBlocks.BAOBAB_WOOD.get(), 5, 5);
        fireBlock.setFlammable(WWBlocks.STRIPPED_BAOBAB_WOOD.get(), 5, 5);
        fireBlock.setFlammable(WWBlocks.BAOBAB_PLANKS.get(), 5, 20);
        fireBlock.setFlammable(WWBlocks.BAOBAB_STAIRS.get(), 5, 20);
        fireBlock.setFlammable(WWBlocks.BAOBAB_DOOR.get(), 5, 20);
        fireBlock.setFlammable(WWBlocks.BAOBAB_FENCE.get(), 5, 20);
        fireBlock.setFlammable(WWBlocks.BAOBAB_SLAB.get(), 5, 20);
        fireBlock.setFlammable(WWBlocks.BAOBAB_FENCE_GATE.get(), 5, 20);
        fireBlock.setFlammable(WWBlocks.BAOBAB_PRESSURE_PLATE.get(), 5, 20);
        fireBlock.setFlammable(WWBlocks.BAOBAB_TRAPDOOR.get(), 5, 20);
        fireBlock.setFlammable(WWBlocks.BAOBAB_LEAVES.get(), 100, 60);
        fireBlock.setFlammable(WWBlocks.BAOBAB_BUTTON.get(), 5, 20);
        fireBlock.setFlammable(WWBlocks.BAOBAB_SIGN_BLOCK.get(), 5, 20);
        fireBlock.setFlammable(WWBlocks.BAOBAB_WALL_SIGN.get(), 5, 20);

        fireBlock.setFlammable(WWBlocks.HOLLOWED_CYPRESS_LOG.get(), 5, 5);
        fireBlock.setFlammable(WWBlocks.CYPRESS_LOG.get(), 5, 5);
        fireBlock.setFlammable(WWBlocks.STRIPPED_CYPRESS_LOG.get(), 5, 5);
        fireBlock.setFlammable(WWBlocks.CYPRESS_WOOD.get(), 5, 5);
        fireBlock.setFlammable(WWBlocks.STRIPPED_CYPRESS_WOOD.get(), 5, 5);
        fireBlock.setFlammable(WWBlocks.CYPRESS_PLANKS.get(), 5, 20);
        fireBlock.setFlammable(WWBlocks.CYPRESS_STAIRS.get(), 5, 20);
        fireBlock.setFlammable(WWBlocks.CYPRESS_DOOR.get(), 5, 20);
        fireBlock.setFlammable(WWBlocks.CYPRESS_FENCE.get(), 5, 20);
        fireBlock.setFlammable(WWBlocks.CYPRESS_SLAB.get(), 5, 20);
        fireBlock.setFlammable(WWBlocks.CYPRESS_FENCE_GATE.get(), 5, 20);
        fireBlock.setFlammable(WWBlocks.CYPRESS_PRESSURE_PLATE.get(), 5, 20);
        fireBlock.setFlammable(WWBlocks.CYPRESS_TRAPDOOR.get(), 5, 20);
        fireBlock.setFlammable(WWBlocks.CYPRESS_LEAVES.get(), 100, 60);
        fireBlock.setFlammable(WWBlocks.CYPRESS_BUTTON.get(), 5, 20);
        fireBlock.setFlammable(WWBlocks.CYPRESS_SIGN_BLOCK.get(), 5, 20);
        fireBlock.setFlammable(WWBlocks.CYPRESS_WALL_SIGN.get(), 5, 20);
    }

}
