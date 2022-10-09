package net.frozenblock.wilderwild.fabric.tag;

import net.frozenblock.wilderwild.fabric.WilderWildFabric;
import net.minecraft.core.Registry;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public final class WilderItemTags {

    public static final TagKey<Item> GOAT_DROP_MUSIC_DISCS = bind("goat_drop_music_discs");

    private WilderItemTags() {
    }

    private static TagKey<Item> bind(String path) {
        return TagKey.create(Registry.ITEM_REGISTRY, WilderWildFabric.id(path));
    }
}
