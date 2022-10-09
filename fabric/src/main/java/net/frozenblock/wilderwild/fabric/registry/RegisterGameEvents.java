package net.frozenblock.wilderwild.fabric.registry;

import net.frozenblock.wilderwild.common.WilderWild;
import net.frozenblock.wilderwild.fabric.WilderWildFabric;
import net.minecraft.core.Registry;
import net.minecraft.world.level.gameevent.GameEvent;

public final class RegisterGameEvents {

    public static final GameEvent SCULK_SENSOR_ACTIVATE = new GameEvent("sculk_sensor_activate", 16);
    public static final GameEvent TENDRIL_EXTRACT_XP = new GameEvent("hanging_tendril_extract_xp", 16);


    public static void registerEvents() {
        WilderWildFabric.logWild("Registering GameEvents for", WilderWild.UNSTABLE_LOGGING);
        Registry.register(Registry.GAME_EVENT, WilderWildFabric.id("sculk_sensor_activate"), SCULK_SENSOR_ACTIVATE);
        Registry.register(Registry.GAME_EVENT, WilderWildFabric.id("hanging_tendril_extract_xp"), TENDRIL_EXTRACT_XP);
    }
}
