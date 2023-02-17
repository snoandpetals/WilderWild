package net.frozenblock.wilderwild.init;

import net.frozenblock.wilderwild.WilderWild;
import net.minecraft.core.Registry;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = WilderWild.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class WWGameEvents {

    public static final DeferredRegister<GameEvent> GAME_EVENTS = DeferredRegister.create(Registry.GAME_EVENT_REGISTRY, WilderWild.MOD_ID);

    public static final RegistryObject<GameEvent> SCULK_SENSOR_ACTIVATE = GAME_EVENTS.register("sculk_sensor_activate", () -> new GameEvent("sculk_sensor_activate", 16));
    public static final RegistryObject<GameEvent> TENDRIL_EXTRACT_XP = GAME_EVENTS.register("hanging_tendril_extract_xp", () -> new GameEvent("hanging_tendril_extract_xp", 16));

}
