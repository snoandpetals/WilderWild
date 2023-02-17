package net.frozenblock.wilderwild.init;

import net.frozenblock.wilderwild.WilderWild;
import net.frozenblock.wilderwild.entities.Firefly;
import net.frozenblock.wilderwild.entities.Jellyfish;
import net.frozenblock.wilderwild.util.FireflyColor;
import net.frozenblock.wilderwild.util.JellyfishVariant;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.CatVariant;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryBuilder;

import java.util.function.Supplier;

public class WWRegistries {

    public static final ResourceKey<Registry<FireflyColor>> FIREFLY_COLOR_REGISTRY = createRegistryKey("firefly_color");
    public static final Registry<FireflyColor> FIREFLY_COLOR = Registry.registerSimple(FIREFLY_COLOR_REGISTRY, (firefly) -> FireflyColor.BLACK);

    public static final ResourceKey<Registry<JellyfishVariant>> JELLYFISH_VARIANT_REGISTRY = createRegistryKey("jellyfish_variant");
    public static final Registry<JellyfishVariant> JELLYFISH_VARIANT = Registry.registerSimple(JELLYFISH_VARIANT_REGISTRY, (variant) -> JellyfishVariant.BLUE);

    private static <T> ResourceKey<Registry<T>> createRegistryKey(String name) {
        return ResourceKey.createRegistryKey(new ResourceLocation(WilderWild.MOD_ID,  name));
    }



}
