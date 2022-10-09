package net.frozenblock.wilderwild.forge;

import dev.architectury.platform.forge.EventBuses;
import net.frozenblock.wilderwild.common.WilderWild;
import net.frozenblock.wilderwild.common.WilderWildClient;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(WilderWild.MOD_ID)
public class WilderWildForge {

    public WilderWildForge() {
        // Submit our event bus to let architectury register our content on the right time
        EventBuses.registerModEventBus(WilderWild.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        WilderWild.init();
    }

    @Mod.EventBusSubscriber(modid = WilderWild.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class WilderWildClientForge {

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            WilderWildClient.init();
        }
    }
}
