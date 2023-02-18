package net.frozenblock.wilderwild.init;

import net.frozenblock.wilderwild.WilderWild;
import net.frozenblock.wilderwild.entities.AncientHornProjectile;
import net.frozenblock.wilderwild.entities.Firefly;
import net.frozenblock.wilderwild.entities.Jellyfish;
import net.frozenblock.wilderwild.entities.WWBoat;
import net.frozenblock.wilderwild.entities.WWChestBoat;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = WilderWild.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class WWEntityTypes {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, WilderWild.MOD_ID);

    public static final MobCategory FIREFLIES_MOB_CATEGORY = MobCategory.create("fireflies", "fireflies", 56, true, false, 80);
    public static final MobCategory JELLYFISH_MOB_CATEGORY = MobCategory.create("jellyfish", "jellyfish", 30, true, false, 64);

    public static final RegistryObject<EntityType<WWBoat>> BOAT = ENTITY_TYPES.register("boat", () -> EntityType.Builder.<WWBoat>of(WWBoat::new, MobCategory.MISC).sized(1.375F, 0.5625F).clientTrackingRange(10).build(WilderWild.string("boat")));
    public static final RegistryObject<EntityType<WWChestBoat>> CHEST_BOAT = ENTITY_TYPES.register("chest_boat", () -> EntityType.Builder.<WWChestBoat>of(WWChestBoat::new, MobCategory.MISC).sized(1.375f, 0.5625f).clientTrackingRange(10).build(WilderWild.string("chest_boat")));
    public static final RegistryObject<EntityType<AncientHornProjectile>> ANCIENT_HORN_PROJECTILE_ENTITY = ENTITY_TYPES.register(
            "ancient_horn_projectile",
            () -> EntityType.Builder.<AncientHornProjectile>of(AncientHornProjectile::new, MobCategory.MISC)
                    .fireImmune()
                    .sized(0.6F, 0.6F)
                    .clientTrackingRange(5)
                    .updateInterval(2)
                    .build(WilderWild.string("ancient_horn_projectile"))
    );

    public static final RegistryObject<EntityType<Firefly>> FIREFLY = ENTITY_TYPES.register(
            "firefly",
            () -> EntityType.Builder.of(Firefly::new, FIREFLIES_MOB_CATEGORY)
                    .sized(0.3F, 0.3F)
                    .build(WilderWild.string("firefly"))
    );

    public static final RegistryObject<EntityType<Jellyfish>> JELLYFISH = ENTITY_TYPES.register(
            "jellyfish",
            () -> EntityType.Builder.of(Jellyfish::new, JELLYFISH_MOB_CATEGORY)
                    .sized(0.4F, 0.4F)
                    .build(WilderWild.string("jellyfish"))
    );

}
