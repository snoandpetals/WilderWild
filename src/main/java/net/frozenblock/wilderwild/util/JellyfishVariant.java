package net.frozenblock.wilderwild.util;

import net.frozenblock.wilderwild.WilderWild;
import net.frozenblock.wilderwild.init.WWRegistries;
import net.minecraft.core.Registry;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.resources.ResourceLocation;

public class JellyfishVariant {

    public static final EntityDataSerializer<JellyfishVariant> SERIALIZER = EntityDataSerializer.simpleId(WWRegistries.JELLYFISH_VARIANT);

    public static final JellyfishVariant BLUE = register(new ResourceLocation(WilderWild.MOD_ID, "blue"), new ResourceLocation(WilderWild.MOD_ID, "textures/entity/jellyfish/blue.png"), false);
    public static final JellyfishVariant LIME = register(new ResourceLocation(WilderWild.MOD_ID, "lime"), new ResourceLocation(WilderWild.MOD_ID, "textures/entity/jellyfish/lime.png"), false);
    public static final JellyfishVariant PINK = register(new ResourceLocation(WilderWild.MOD_ID, "pink"), new ResourceLocation(WilderWild.MOD_ID, "textures/entity/jellyfish/pink.png"), false);
    public static final JellyfishVariant RED = register(new ResourceLocation(WilderWild.MOD_ID, "red"), new ResourceLocation(WilderWild.MOD_ID, "textures/entity/jellyfish/red.png"), false);
    public static final JellyfishVariant YELLOW = register(new ResourceLocation(WilderWild.MOD_ID, "yellow"), new ResourceLocation(WilderWild.MOD_ID, "textures/entity/jellyfish/yellow.png"), false);

    public static final JellyfishVariant PEARLESCENT_BLUE = register(new ResourceLocation(WilderWild.MOD_ID, "pearlescent_blue"), new ResourceLocation(WilderWild.MOD_ID, "textures/entity/jellyfish/pearlescent_blue.png"), true);
    public static final JellyfishVariant PEARLESCENT_PURPLE = register(new ResourceLocation(WilderWild.MOD_ID, "pearlescent_purple"), new ResourceLocation(WilderWild.MOD_ID, "textures/entity/jellyfish/pearlescent_purple.png"), true);

    private final ResourceLocation key;
    private final ResourceLocation texture;
    private final boolean pearlescent;

    public JellyfishVariant(ResourceLocation key, ResourceLocation texture, boolean pearlescent) {
        this.key = key;
        this.texture = texture;
        this.pearlescent = pearlescent;
    }

    public ResourceLocation getKey() {
        return this.key;
    }

    public ResourceLocation getTexture() {
        return this.texture;
    }

    public boolean isNormal() {
        return !this.pearlescent;
    }

    public boolean isPearlescent() {
        return this.pearlescent;
    }

    public static JellyfishVariant register(ResourceLocation key, ResourceLocation texture, boolean pearlescent) {
        return Registry.register(WWRegistries.JELLYFISH_VARIANT, key, new JellyfishVariant(key, texture, pearlescent));
    }

    static {
        EntityDataSerializers.registerSerializer(SERIALIZER);
    }

}
