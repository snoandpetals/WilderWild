package net.frozenblock.wilderwild.init;

import net.frozenblock.wilderwild.WilderWild;
import net.minecraft.core.Registry;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;

public class WWEntityTypesTags {
    public static final TagKey<EntityType<?>> CAN_SWIM_IN_ALGAE = bind("can_swim_in_algae");
    public static final TagKey<EntityType<?>> STAYS_IN_MESOGLEA = bind("stays_in_mesoglea");
    public static final TagKey<EntityType<?>> ANCIENT_HORN_IMMUNE = bind("ancient_horn_immune");
    public static final TagKey<EntityType<?>> JELLYFISH_CANT_STING = bind("jellyfish_cant_sting");

    private static TagKey<EntityType<?>> bind(String path) {
        return TagKey.create(Registry.ENTITY_TYPE_REGISTRY, WilderWild.id(path));
    }

}
