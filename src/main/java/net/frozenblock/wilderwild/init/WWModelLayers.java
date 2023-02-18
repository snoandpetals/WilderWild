package net.frozenblock.wilderwild.init;

import net.frozenblock.wilderwild.WilderWild;
import net.frozenblock.wilderwild.entities.WWBoat;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

public class WWModelLayers {
    public static final ModelLayerLocation ANCIENT_HORN_PROJECTILE_LAYER = create("ancient_horn_projectile", "main");
    public static final ModelLayerLocation JELLYFISH = create("jellyfish", "main");

    public static ModelLayerLocation createChestBoat(WWBoat.WWBoatType type) {
        return create("chest_boat/" + type.getName(), "main");
    }

    public static ModelLayerLocation createBoat(WWBoat.WWBoatType type) {
        return create("boat/" + type.getName(), "main");
    }

    private static ModelLayerLocation create(String id, String layer) {
        return new ModelLayerLocation(WilderWild.id(id), layer);
    }

}
