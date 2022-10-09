package net.frozenblock.wilderwild.fabric.misc;

import com.mojang.serialization.Codec;
import net.frozenblock.wilderwild.fabric.WilderWildFabric;
import net.frozenblock.wilderwild.fabric.registry.WilderRegistry;
import net.minecraft.Util;
import net.minecraft.core.Registry;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.resources.ResourceLocation;

import java.util.List;

public class FireflyColor {

    public static final Codec<FireflyColor> CODEC = ResourceLocation.CODEC
            .listOf()
            .comapFlatMap(
                    list -> Util.fixedSize(list, 2).map(listx -> new FireflyColor(listx.get(0), listx.get(1))), color -> List.of(color.getKey(), color.getTexture())
            );

    public static final EntityDataSerializer<FireflyColor> SERIALIZER = EntityDataSerializer.simpleId(WilderRegistry.FIREFLY_COLOR);

    public static final FireflyColor BLACK = register(WilderWildFabric.id("black"), WilderWildFabric.id("textures/entity/firefly/firefly_black.png"));
    public static final FireflyColor BLUE = register(WilderWildFabric.id("blue"), WilderWildFabric.id("textures/entity/firefly/firefly_blue.png"));
    public static final FireflyColor BROWN = register(WilderWildFabric.id("brown"), WilderWildFabric.id("textures/entity/firefly/firefly_brown.png"));
    public static final FireflyColor CYAN = register(WilderWildFabric.id("cyan"), WilderWildFabric.id("textures/entity/firefly/firefly_cyan.png"));
    public static final FireflyColor GRAY = register(WilderWildFabric.id("gray"), WilderWildFabric.id("textures/entity/firefly/firefly_gray.png"));
    public static final FireflyColor GREEN = register(WilderWildFabric.id("green"), WilderWildFabric.id("textures/entity/firefly/firefly_green.png"));
    public static final FireflyColor LIGHT_BLUE = register(WilderWildFabric.id("light_blue"), WilderWildFabric.id("textures/entity/firefly/firefly_light_blue.png"));
    public static final FireflyColor LIGHT_GRAY = register(WilderWildFabric.id("light_gray"), WilderWildFabric.id("textures/entity/firefly/firefly_light_gray.png"));
    public static final FireflyColor LIME = register(WilderWildFabric.id("lime"), WilderWildFabric.id("textures/entity/firefly/firefly_lime.png"));
    public static final FireflyColor MAGENTA = register(WilderWildFabric.id("magenta"), WilderWildFabric.id("textures/entity/firefly/firefly_magenta.png"));
    public static final FireflyColor ON = register(WilderWildFabric.id("on"), WilderWildFabric.id("textures/entity/firefly/firefly_on.png"));
    public static final FireflyColor ORANGE = register(WilderWildFabric.id("orange"), WilderWildFabric.id("textures/entity/firefly/firefly_orange.png"));
    public static final FireflyColor PINK = register(WilderWildFabric.id("pink"), WilderWildFabric.id("textures/entity/firefly/firefly_pink.png"));
    public static final FireflyColor PURPLE = register(WilderWildFabric.id("purple"), WilderWildFabric.id("textures/entity/firefly/firefly_purple.png"));
    public static final FireflyColor RED = register(WilderWildFabric.id("red"), WilderWildFabric.id("textures/entity/firefly/firefly_red.png"));
    public static final FireflyColor WHITE = register(WilderWildFabric.id("white"), WilderWildFabric.id("textures/entity/firefly/firefly_white.png"));
    public static final FireflyColor YELLOW = register(WilderWildFabric.id("yellow"), WilderWildFabric.id("textures/entity/firefly/firefly_yellow.png"));

    private final ResourceLocation key;
    private final ResourceLocation texture;

    public FireflyColor(ResourceLocation key, ResourceLocation texture) {
        this.key = key;
        this.texture = texture;
    }

    public ResourceLocation getKey() {
        return this.key;
    }

    public ResourceLocation getTexture() {
        return this.texture;
    }

    public static FireflyColor register(ResourceLocation key, ResourceLocation texture) {
        return Registry.register(WilderRegistry.FIREFLY_COLOR, key, new FireflyColor(key, texture));
    }

    static {
        EntityDataSerializers.registerSerializer(SERIALIZER);
    }
}
