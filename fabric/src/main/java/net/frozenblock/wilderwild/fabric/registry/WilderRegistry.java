package net.frozenblock.wilderwild.fabric.registry;

import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.fabricmc.fabric.api.event.registry.RegistryAttribute;
import net.frozenblock.wilderwild.fabric.WilderWildFabric;
import net.frozenblock.wilderwild.fabric.misc.FireflyColor;
import net.frozenblock.wilderwild.fabric.misc.JellyfishVariant;
import net.minecraft.core.MappedRegistry;

public class WilderRegistry {

    public static final MappedRegistry<FireflyColor> FIREFLY_COLOR = FabricRegistryBuilder.createSimple(FireflyColor.class, WilderWildFabric.id("firefly_color"))
            .attribute(RegistryAttribute.SYNCED)
            .buildAndRegister();
    public static final MappedRegistry<JellyfishVariant> JELLYFISH_VARIANT = FabricRegistryBuilder.createSimple(JellyfishVariant.class, WilderWildFabric.id("jellyfish_color"))
            .attribute(RegistryAttribute.SYNCED)
            .buildAndRegister();

    public static void initRegistry() {

    }
}
