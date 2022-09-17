package net.frozenblock.wilderwild.registry;

import net.frozenblock.wilderwild.WilderWild;
import net.frozenblock.wilderwild.block.WaterloggableTallFlowerBlock;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class RegisterBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, WilderWild.MOD_ID);

    public static final RegistryObject<Block> CARNATION = registerBlock("carnation",
            () -> new FlowerBlock(MobEffects.REGENERATION,12, BlockBehaviour.Properties.copy(Blocks.DANDELION)
                    .strength(0.0f).sound(SoundType.GRASS)), CreativeModeTab.TAB_DECORATIONS);

    public static final RegistryObject<Block> POTTED_CARNATION = registerBlockWithoutBlockItem("potted_carnation",
            () -> new FlowerPotBlock(null, RegisterBlocks.CARNATION,
                    BlockBehaviour.Properties.copy(Blocks.POTTED_DANDELION).noOcclusion()));

    public static final RegistryObject<Block> CATTAIL = registerBlock("cattail",
            () -> new WaterloggableTallFlowerBlock(BlockBehaviour.Properties.copy(Blocks.ROSE_BUSH).sound(SoundType.WET_GRASS).strength(0.0F)), CreativeModeTab.TAB_DECORATIONS);

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);
        return toReturn;
    }
    private static <T extends Block> RegistryObject<T> registerBlockWithoutBlockItem(String name, Supplier<T> block) {
        return BLOCKS.register(name, block);
    }
    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block,
                                                                            CreativeModeTab tab) {
        return RegisterItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}

