package net.frozenblock.wilderwild.init;

import net.frozenblock.wilderwild.WilderWild;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Instrument;

public class WWInstrumentTags {

    public static final TagKey<Instrument> ANCIENT_HORNS = bind("ancient_horns");
    public static final TagKey<Instrument> COPPER_HORNS = bind("copper_horns");

    private static TagKey<Instrument> bind(String path) {
        return TagKey.create(Registry.INSTRUMENT_REGISTRY, new ResourceLocation(WilderWild.MOD_ID, path));
    }

}
