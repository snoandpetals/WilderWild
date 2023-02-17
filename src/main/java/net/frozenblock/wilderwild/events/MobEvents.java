package net.frozenblock.wilderwild.events;

import net.frozenblock.wilderwild.WilderWild;
import net.frozenblock.wilderwild.entities.Firefly;
import net.frozenblock.wilderwild.entities.Jellyfish;
import net.frozenblock.wilderwild.init.WWEntityTypes;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = WilderWild.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class MobEvents {

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(WWEntityTypes.FIREFLY.get(), Firefly.addAttributes().build());
        event.put(WWEntityTypes.JELLYFISH.get(), Jellyfish.addAttributes().build());
    }

    @SubscribeEvent
    public static void register(SpawnPlacementRegisterEvent event) {
        event.register(WWEntityTypes.FIREFLY.get(), SpawnPlacements.Type.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING, Firefly::canSpawn, SpawnPlacementRegisterEvent.Operation.OR);
        event.register(WWEntityTypes.JELLYFISH.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Jellyfish::canSpawn, SpawnPlacementRegisterEvent.Operation.OR);
    }

}
