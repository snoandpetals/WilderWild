package net.frozenblock.wilderwild.init;

import net.frozenblock.wilderwild.WilderWild;
import net.frozenblock.wilderwild.entities.WWBoat;
import net.frozenblock.wilderwild.item.AlgaeItem;
import net.frozenblock.wilderwild.item.AncientHornItem;
import net.frozenblock.wilderwild.item.CopperHornItem;
import net.frozenblock.wilderwild.item.FireflyBottleItem;
import net.frozenblock.wilderwild.item.MilkweedPodItem;
import net.frozenblock.wilderwild.item.WWBoatItem;
import net.frozenblock.wilderwild.util.FireflyColor;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.MobBucketItem;
import net.minecraft.world.item.PlaceOnWaterBlockItem;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.RecordItem;
import net.minecraft.world.item.SignItem;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = WilderWild.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class WWItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, WilderWild.MOD_ID);

    public static final RegistryObject<Item> FLOWERING_LILY_PAD = ITEMS.register("flowering_lily_pad", () -> new PlaceOnWaterBlockItem(WWBlocks.FLOWERING_LILY_PAD.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
    public static final RegistryObject<Item> ALGAE = ITEMS.register("algae", () -> new AlgaeItem(WWBlocks.ALGAE.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));

    public static final RegistryObject<Item> MILKWEED_POD = ITEMS.register("milkweed_pod", () -> new MilkweedPodItem(new Item.Properties().tab(CreativeModeTab.TAB_MISC).stacksTo(64)));
    public static final RegistryObject<Item> MUSIC_DISC_BENEATH = ITEMS.register("music_disc_beneath", () -> new RecordItem(15, WWSoundEvents.MUSIC_DISC_BENEATH, new Item.Properties().stacksTo(1).rarity(Rarity.RARE), 169));
    public static final RegistryObject<Item> MUSIC_DISC_GOAT_HORN_SYMPHONY = ITEMS.register("music_disc_goathorn_symphony", () -> new RecordItem(15, WWSoundEvents.MUSIC_DISC_GOATHORN_SYMPHONY, new Item.Properties().tab(CreativeModeTab.TAB_MISC).stacksTo(1).rarity(Rarity.RARE), 144));
    public static final RegistryObject<Item> MUSIC_DISC_BACK = ITEMS.register("music_disc_back", () -> new RecordItem(15, WWSoundEvents.MUSIC_DISC_BACK, new Item.Properties().tab(CreativeModeTab.TAB_MISC).stacksTo(1).rarity(Rarity.RARE), 76));
    public static final RegistryObject<Item> FIREFLY_SPAWN_EGG = ITEMS.register("firefly_spawn_egg", () -> new ForgeSpawnEggItem(WWEntityTypes.FIREFLY, Integer.parseInt("2A2E2B", 16), Integer.parseInt("AAF644", 16), new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<Item> JELLYFISH_SPAWN_EGG = ITEMS.register("jellyfish_spawn_egg", () -> new ForgeSpawnEggItem(WWEntityTypes.JELLYFISH, Integer.parseInt("E484E4", 16), Integer.parseInt("DF71DC", 16), new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<Item> JELLYFISH_BUCKET = ITEMS.register("jellyfish_bucket", () -> new MobBucketItem(WWEntityTypes.JELLYFISH, () -> Fluids.WATER, WWSoundEvents.ITEM_BUCKET_EMPTY_JELLYFISH, new Item.Properties().tab(CreativeModeTab.TAB_MISC).stacksTo(1)));


    public static final RegistryObject<Item> BAOBAB_BOAT_ITEM = ITEMS.register("baobab_boat", () -> new WWBoatItem(false, WWBoat.WWBoatType.BAOBAB, new Item.Properties().stacksTo(1).tab(CreativeModeTab.TAB_TRANSPORTATION)));
    public static final RegistryObject<Item> BAOBAB_CHEST_BOAT_ITEM = ITEMS.register("baobab_chest_boat", () -> new WWBoatItem(true, WWBoat.WWBoatType.BAOBAB, new Item.Properties().stacksTo(1).tab(CreativeModeTab.TAB_TRANSPORTATION)));
    public static final RegistryObject<Item> BAOBAB_SIGN = ITEMS.register("baobab_sign", () -> new SignItem(new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS).stacksTo(16), WWBlocks.BAOBAB_SIGN_BLOCK.get(), WWBlocks.BAOBAB_WALL_SIGN.get()));
    public static final RegistryObject<Item> CYPRESS_BOAT_ITEM = ITEMS.register("cypress_boat", () -> new WWBoatItem(false, WWBoat.WWBoatType.CYPRESS, new Item.Properties().stacksTo(1).tab(CreativeModeTab.TAB_TRANSPORTATION)));
    public static final RegistryObject<Item> CYPRESS_CHEST_BOAT_ITEM = ITEMS.register("cypress_chest_boat", () -> new WWBoatItem(true, WWBoat.WWBoatType.CYPRESS, new Item.Properties().stacksTo(1).tab(CreativeModeTab.TAB_TRANSPORTATION)));
    public static final RegistryObject<Item> CYPRESS_SIGN = ITEMS.register("cypress_sign", () -> new SignItem(new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS).stacksTo(16), WWBlocks.CYPRESS_SIGN_BLOCK.get(), WWBlocks.CYPRESS_WALL_SIGN.get()));

    public static final RegistryObject<Item> POLLEN = ITEMS.register("pollen", () -> new BlockItem(WWBlocks.POLLEN_BLOCK.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));

    public static final RegistryObject<Item> BAOBAB_NUT = ITEMS.register("baobab_nut", () -> new BlockItem(WWBlocks.BAOBAB_NUT.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS).food(WWFoods.BAOBAB_NUT)));
    public static final RegistryObject<Item> FIREFLY_BOTTLE = ITEMS.register("firefly_bottle", () -> new FireflyBottleItem(new Item.Properties().tab(CreativeModeTab.TAB_MISC).stacksTo(32), FireflyColor.ON));
    public static final RegistryObject<Item> BLACK_FIREFLY_BOTTLE = ITEMS.register("black_firefly_bottle", () -> new FireflyBottleItem(new Item.Properties().tab(CreativeModeTab.TAB_MISC).stacksTo(32), FireflyColor.BLACK));
    public static final RegistryObject<Item> RED_FIREFLY_BOTTLE = ITEMS.register("red_firefly_bottle", () -> new FireflyBottleItem(new Item.Properties().tab(CreativeModeTab.TAB_MISC).stacksTo(32), FireflyColor.RED));
    public static final RegistryObject<Item> GREEN_FIREFLY_BOTTLE = ITEMS.register("green_firefly_bottle", () -> new FireflyBottleItem(new Item.Properties().tab(CreativeModeTab.TAB_MISC).stacksTo(32), FireflyColor.GREEN));
    public static final RegistryObject<Item> BROWN_FIREFLY_BOTTLE = ITEMS.register("brown_firefly_bottle", () -> new FireflyBottleItem(new Item.Properties().tab(CreativeModeTab.TAB_MISC).stacksTo(32), FireflyColor.BROWN));
    public static final RegistryObject<Item> BLUE_FIREFLY_BOTTLE = ITEMS.register("blue_firefly_bottle", () -> new FireflyBottleItem(new Item.Properties().tab(CreativeModeTab.TAB_MISC).stacksTo(32), FireflyColor.BLUE));
    public static final RegistryObject<Item> PURPLE_FIREFLY_BOTTLE = ITEMS.register("purple_firefly_bottle", () -> new FireflyBottleItem(new Item.Properties().tab(CreativeModeTab.TAB_MISC).stacksTo(32), FireflyColor.PURPLE));
    public static final RegistryObject<Item> CYAN_FIREFLY_BOTTLE = ITEMS.register("cyan_firefly_bottle", () -> new FireflyBottleItem(new Item.Properties().tab(CreativeModeTab.TAB_MISC).stacksTo(32), FireflyColor.CYAN));
    public static final RegistryObject<Item> LIGHT_GRAY_FIREFLY_BOTTLE = ITEMS.register("light_gray_firefly_bottle", () -> new FireflyBottleItem(new Item.Properties().tab(CreativeModeTab.TAB_MISC).stacksTo(32), FireflyColor.LIGHT_GRAY));
    public static final RegistryObject<Item> GRAY_FIREFLY_BOTTLE = ITEMS.register("gray_firefly_bottle", () -> new FireflyBottleItem(new Item.Properties().tab(CreativeModeTab.TAB_MISC).stacksTo(32), FireflyColor.GRAY));
    public static final RegistryObject<Item> PINK_FIREFLY_BOTTLE = ITEMS.register("pink_firefly_bottle", () -> new FireflyBottleItem(new Item.Properties().tab(CreativeModeTab.TAB_MISC).stacksTo(32), FireflyColor.PINK));
    public static final RegistryObject<Item> LIME_FIREFLY_BOTTLE = ITEMS.register("lime_firefly_bottle", () -> new FireflyBottleItem(new Item.Properties().tab(CreativeModeTab.TAB_MISC).stacksTo(32), FireflyColor.LIME));
    public static final RegistryObject<Item> YELLOW_FIREFLY_BOTTLE = ITEMS.register("yellow_firefly_bottle", () -> new FireflyBottleItem(new Item.Properties().tab(CreativeModeTab.TAB_MISC).stacksTo(32), FireflyColor.YELLOW));
    public static final RegistryObject<Item> LIGHT_BLUE_FIREFLY_BOTTLE = ITEMS.register("light_blue_firefly_bottle", () -> new FireflyBottleItem(new Item.Properties().tab(CreativeModeTab.TAB_MISC).stacksTo(32), FireflyColor.LIGHT_BLUE));
    public static final RegistryObject<Item> MAGENTA_FIREFLY_BOTTLE = ITEMS.register("magenta_firefly_bottle", () -> new FireflyBottleItem(new Item.Properties().tab(CreativeModeTab.TAB_MISC).stacksTo(32), FireflyColor.MAGENTA));
    public static final RegistryObject<Item> ORANGE_FIREFLY_BOTTLE = ITEMS.register("orange_firefly_bottle", () -> new FireflyBottleItem(new Item.Properties().tab(CreativeModeTab.TAB_MISC).stacksTo(32), FireflyColor.ORANGE));
    public static final RegistryObject<Item> WHITE_FIREFLY_BOTTLE = ITEMS.register("white_firefly_bottle", () -> new FireflyBottleItem(new Item.Properties().tab(CreativeModeTab.TAB_MISC).stacksTo(32), FireflyColor.WHITE));

    public static final RegistryObject<Item> ANCIENT_HORN_FRAGMENT = ITEMS.register("ancient_horn_fragment", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC).stacksTo(64)));

    public static final RegistryObject<Item> ANCIENT_HORN = ITEMS.register("ancient_horn", () -> new AncientHornItem(new Item.Properties().tab(CreativeModeTab.TAB_MISC).stacksTo(1).rarity(Rarity.EPIC), WWInstrumentTags.ANCIENT_HORNS));

    public static final RegistryObject<Item> COPPER_HORN = ITEMS.register("copper_horn", () -> new CopperHornItem(new Item.Properties().tab(CreativeModeTab.TAB_MISC).stacksTo(1), WWInstrumentTags.COPPER_HORNS));

}
