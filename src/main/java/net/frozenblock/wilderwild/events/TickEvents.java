package net.frozenblock.wilderwild.events;

import net.frozenblock.wilderwild.WilderWild;
import net.frozenblock.wilderwild.api.FlyBySoundHub;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = WilderWild.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class TickEvents {

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        Minecraft client = Minecraft.getInstance();
        if (event.phase == TickEvent.Phase.START && client.level != null) {
            FlyBySoundHub.update(client, client.player, true);
        }
    }

}
