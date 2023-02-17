package net.frozenblock.wilderwild.util;

import com.mojang.serialization.Codec;
import net.frozenblock.wilderwild.WilderWild;
import net.frozenblock.wilderwild.init.WWRegistries;
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

    public static final EntityDataSerializer<FireflyColor> SERIALIZER = EntityDataSerializer.simpleId(WWRegistries.FIREFLY_COLOR);

    public static final FireflyColor BLACK = register(new ResourceLocation(WilderWild.MOD_ID, "black"), new ResourceLocation(WilderWild.MOD_ID, "textures/entity/firefly/firefly_black.png"));
    public static final FireflyColor BLUE = register(new ResourceLocation(WilderWild.MOD_ID, "blue"), new ResourceLocation(WilderWild.MOD_ID, "textures/entity/firefly/firefly_blue.png"));
    public static final FireflyColor BROWN = register(new ResourceLocation(WilderWild.MOD_ID, "brown"), new ResourceLocation(WilderWild.MOD_ID, "textures/entity/firefly/firefly_brown.png"));
    public static final FireflyColor CYAN = register(new ResourceLocation(WilderWild.MOD_ID, "cyan"), new ResourceLocation(WilderWild.MOD_ID, "textures/entity/firefly/firefly_cyan.png"));
    public static final FireflyColor GRAY = register(new ResourceLocation(WilderWild.MOD_ID, "gray"), new ResourceLocation(WilderWild.MOD_ID, "textures/entity/firefly/firefly_gray.png"));
    public static final FireflyColor GREEN = register(new ResourceLocation(WilderWild.MOD_ID, "green"), new ResourceLocation(WilderWild.MOD_ID, "textures/entity/firefly/firefly_green.png"));
    public static final FireflyColor LIGHT_BLUE = register(new ResourceLocation(WilderWild.MOD_ID, "light_blue"), new ResourceLocation(WilderWild.MOD_ID, "textures/entity/firefly/firefly_light_blue.png"));
    public static final FireflyColor LIGHT_GRAY = register(new ResourceLocation(WilderWild.MOD_ID, "light_gray"), new ResourceLocation(WilderWild.MOD_ID, "textures/entity/firefly/firefly_light_gray.png"));
    public static final FireflyColor LIME = register(new ResourceLocation(WilderWild.MOD_ID, "lime"), new ResourceLocation(WilderWild.MOD_ID, "textures/entity/firefly/firefly_lime.png"));
    public static final FireflyColor MAGENTA = register(new ResourceLocation(WilderWild.MOD_ID, "magenta"), new ResourceLocation(WilderWild.MOD_ID, "textures/entity/firefly/firefly_magenta.png"));
    public static final FireflyColor ON = register(new ResourceLocation(WilderWild.MOD_ID, "on"), new ResourceLocation(WilderWild.MOD_ID, "textures/entity/firefly/firefly_on.png"));
    public static final FireflyColor ORANGE = register(new ResourceLocation(WilderWild.MOD_ID, "orange"), new ResourceLocation(WilderWild.MOD_ID, "textures/entity/firefly/firefly_orange.png"));
    public static final FireflyColor PINK = register(new ResourceLocation(WilderWild.MOD_ID, "pink"), new ResourceLocation(WilderWild.MOD_ID, "textures/entity/firefly/firefly_pink.png"));
    public static final FireflyColor PURPLE = register(new ResourceLocation(WilderWild.MOD_ID, "purple"), new ResourceLocation(WilderWild.MOD_ID, "textures/entity/firefly/firefly_purple.png"));
    public static final FireflyColor RED = register(new ResourceLocation(WilderWild.MOD_ID, "red"), new ResourceLocation(WilderWild.MOD_ID, "textures/entity/firefly/firefly_red.png"));
    public static final FireflyColor WHITE = register(new ResourceLocation(WilderWild.MOD_ID, "white"), new ResourceLocation(WilderWild.MOD_ID, "textures/entity/firefly/firefly_white.png"));
    public static final FireflyColor YELLOW = register(new ResourceLocation(WilderWild.MOD_ID, "yellow"), new ResourceLocation(WilderWild.MOD_ID, "textures/entity/firefly/firefly_yellow.png"));

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
        return Registry.register(WWRegistries.FIREFLY_COLOR, key, new FireflyColor(key, texture));
    }

    static {
        EntityDataSerializers.registerSerializer(SERIALIZER);
    }

}
