package net.frozenblock.wilderwild.fabric.registry;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.frozenblock.wilderwild.common.WilderWild;
import net.frozenblock.wilderwild.fabric.WilderWildFabric;
import net.frozenblock.wilderwild.fabric.entity.AncientHornProjectile;
import net.frozenblock.wilderwild.fabric.entity.Firefly;
import net.frozenblock.wilderwild.fabric.entity.Jellyfish;
import net.minecraft.core.Registry;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;

public final class RegisterEntities {

    public static final EntityType<AncientHornProjectile> ANCIENT_HORN_PROJECTILE_ENTITY = Registry.register(Registry.ENTITY_TYPE, WilderWildFabric.id("ancient_horn_projectile"), FabricEntityTypeBuilder.<AncientHornProjectile>create(MobCategory.MISC, AncientHornProjectile::new).fireImmune().dimensions(EntityDimensions.scalable(0.6F, 0.6F)).trackRangeBlocks(64).trackedUpdateRate(2).build());
    public static final EntityType<Firefly> FIREFLY = Registry.register(Registry.ENTITY_TYPE, WilderWildFabric.id("firefly"), FabricEntityTypeBuilder.createMob().spawnGroup(WilderWildFabric.FIREFLIES).entityFactory(Firefly::new).defaultAttributes(Firefly::addAttributes).dimensions(EntityDimensions.scalable(0.3F, 0.3F)).build());
    public static final EntityType<Jellyfish> JELLYFISH = Registry.register(Registry.ENTITY_TYPE, WilderWildFabric.id("jellyfish"), FabricEntityTypeBuilder.createMob().spawnGroup(WilderWildFabric.JELLYFISH).entityFactory(Jellyfish::new).defaultAttributes(Jellyfish::addAttributes).dimensions(EntityDimensions.scalable(0.4F, 0.4F)).build());

    public static void init() {
        WilderWildFabric.logWild("Registering Entities for", WilderWild.UNSTABLE_LOGGING);
        SpawnPlacements.register(FIREFLY, SpawnPlacements.Type.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING, Firefly::canSpawn);
        SpawnPlacements.register(JELLYFISH, SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Jellyfish::canSpawn);
    }
}