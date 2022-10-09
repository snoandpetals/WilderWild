package net.frozenblock.wilderwild.fabric.misc;

import net.fabricmc.loader.api.FabricLoader;
import net.frozenblock.wilderwild.fabric.registry.RegisterBlockSoundGroups;
import net.frozenblock.wilderwild.fabric.registry.RegisterBlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;

import java.util.ArrayList;
import java.util.List;

import static net.minecraft.world.level.block.Blocks.*;


public class BlockSoundGroupOverwrites {

    public static void init() {
        addBlock(WITHER_ROSE, SoundType.SWEET_BERRY_BUSH);
        addBlock(DEAD_BUSH, SoundType.NETHER_SPROUTS);
        addBlock(CACTUS, SoundType.SWEET_BERRY_BUSH);
        addBlock(PODZOL, SoundType.ROOTED_DIRT);

        addBlockTag(BlockTags.LEAVES, RegisterBlockSoundGroups.LEAVES);
        addBlocks(new Block[]{ACACIA_LEAVES, BIRCH_LEAVES, DARK_OAK_LEAVES, JUNGLE_LEAVES, MANGROVE_LEAVES, OAK_LEAVES, SPRUCE_LEAVES, RegisterBlocks.BAOBAB_LEAVES, RegisterBlocks.CYPRESS_LEAVES}, RegisterBlockSoundGroups.LEAVES);
        addBlocks(new Block[]{DANDELION, POPPY, BLUE_ORCHID, ALLIUM, AZURE_BLUET, RED_TULIP, ORANGE_TULIP, WHITE_TULIP, PINK_TULIP, OXEYE_DAISY, CORNFLOWER, LILY_OF_THE_VALLEY}, RegisterBlockSoundGroups.FLOWER);
        addBlocks(new Block[]{RED_MUSHROOM, BROWN_MUSHROOM}, RegisterBlockSoundGroups.MUSHROOM);
        addBlocks(new Block[]{RED_MUSHROOM_BLOCK, BROWN_MUSHROOM_BLOCK, MUSHROOM_STEM}, RegisterBlockSoundGroups.MUSHROOM_BLOCK);
        addBlocks(new Block[]{FROSTED_ICE}, RegisterBlockSoundGroups.ICE_BLOCKS);
        addBlock(COBWEB, RegisterBlockSoundGroups.WEB);
        addBlock(CLAY, RegisterBlockSoundGroups.CLAY_BLOCK);
        addBlock(REINFORCED_DEEPSLATE, RegisterBlockSoundGroups.REINFORCEDDEEPSLATE);
        addBlock(LILY_PAD, RegisterBlockSoundGroups.LILYPAD);
        addBlock(SUGAR_CANE, RegisterBlockSoundGroups.SUGARCANE);
        addBlock(COARSE_DIRT, RegisterBlockSoundGroups.COARSEDIRT);
        addBlock(GRAVEL, RegisterBlockSoundGroups.GRAVELSOUNDS);

        if (FabricLoader.getInstance().isModLoaded("betternether")) {
            addBlock("betternether", "willow_leaves", RegisterBlockSoundGroups.LEAVES);
            addBlock("betternether", "rubeous_leaves", RegisterBlockSoundGroups.LEAVES);
            addBlock("betternether", "anchor_tree_leaves", RegisterBlockSoundGroups.LEAVES);
            addBlock("betternether", "nether_sakura_leaves", RegisterBlockSoundGroups.LEAVES);
        }

        if (FabricLoader.getInstance().isModLoaded("betterend")) {
            addBlock("betterend", "pythadendron_leaves", RegisterBlockSoundGroups.LEAVES);
            addBlock("betterend", "lacugrove_leaves", RegisterBlockSoundGroups.LEAVES);
            addBlock("betterend", "dragon_tree_leaves", RegisterBlockSoundGroups.LEAVES);
            addBlock("betterend", "tenanea_leaves", RegisterBlockSoundGroups.LEAVES);
            addBlock("betterend", "helix_tree_leaves", RegisterBlockSoundGroups.LEAVES);
            addBlock("betterend", "lucernia_leaves", RegisterBlockSoundGroups.LEAVES);
        }

        if (FabricLoader.getInstance().isModLoaded("blockus")) {
            addBlock("blockus", "white_oak_leaves", RegisterBlockSoundGroups.LEAVES);
            addBlock("blockus", "legacy_leaves", RegisterBlockSoundGroups.LEAVES);
        }

        if (FabricLoader.getInstance().isModLoaded("edenring")) {
            addBlock("edenring", "auritis_leaves", RegisterBlockSoundGroups.LEAVES);
        }

        if (FabricLoader.getInstance().isModLoaded("techreborn")) {
            addBlock("techreborn", "rubber_leaves", RegisterBlockSoundGroups.LEAVES);
        }
    }

    /**
     * You can add any block by either adding its registry (Blocks.STONE) or its ID ("stone").
     * If you want to add a modded block, make sure to put the nameSpaceID (wilderwild) in the first field, then the ID and soundGroup.
     * Or you could just be normal and add the block itself instead of the ID.
     * You can also add a LIST of blocks (IDs not allowed,) by using new Block[]{block1, block2}.
     */

    public static void addBlock(String id, SoundType sounds) {
        ids.add(new ResourceLocation(id));
        soundGroups.add(sounds);
    }

    public static void addBlock(String nameSpace, String id, SoundType sounds) {
        ids.add(new ResourceLocation(nameSpace, id));
        soundGroups.add(sounds);
    }

    public static void addBlock(Block block, SoundType sounds) {
        ids.add(Registry.BLOCK.getKey(block));
        soundGroups.add(sounds);
    }

    public static void addBlocks(Block[] blocks, SoundType sounds) {
        for (Block block : blocks) {
            ids.add(Registry.BLOCK.getKey(block));
            soundGroups.add(sounds);
        }
    }

    public static void addBlockTag(TagKey<Block> tag, SoundType sounds) {
        for (Holder<Block> block : Registry.BLOCK.getTagOrEmpty(tag)) {
            ids.add(block.unwrapKey().orElseThrow().location());
            soundGroups.add(sounds);
        }
    }

    public static void addNamespace(String nameSpace, SoundType sounds) {
        namespaces.add(nameSpace);
        namespaceSoundGroups.add(sounds);
    }

    public static final List<ResourceLocation> ids = new ArrayList<>();
    public static final List<SoundType> soundGroups = new ArrayList<>();
    public static final List<String> namespaces = new ArrayList<>();
    public static final List<SoundType> namespaceSoundGroups = new ArrayList<>();
}
