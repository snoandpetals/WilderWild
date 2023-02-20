package net.frozenblock.wilderwild.init;

import net.frozenblock.wilderwild.WilderWild;
import net.minecraft.core.Registry;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class WWItemTags {

    public static final TagKey<Item> GOAT_DROP_MUSIC_DISCS = bind("goat_drop_music_discs");
    public static final TagKey<Item> NO_USE_GAME_EVENTS = bind("dont_emit_use_game_events");
    public static final TagKey<Item> HEAVY_ITEMS = bind("heavy_items");

    private static TagKey<Item> bind(String path) {
        return TagKey.create(Registry.ITEM_REGISTRY, WilderWild.id(path));
    }

}
