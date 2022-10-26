package net.frozenblock.wilderwild.init;

import net.frozenblock.wilderwild.WilderWild;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SignItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = WilderWild.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class WWItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, WilderWild.MOD_ID);

    public static final RegistryObject<Item> BAOBAB_SIGN = ITEMS.register("baobab_sign",
            () -> new SignItem(new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS).stacksTo(16),
                    WWBlocks.BAOBAB_SIGN_BLOCK.get(), WWBlocks.BAOBAB_WALL_SIGN.get()));


    public static final RegistryObject<Item> POLLEN = ITEMS.register("pollen",
            () -> new BlockItem(WWBlocks.POLLEN_BLOCK.get(),
                    new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));

}
