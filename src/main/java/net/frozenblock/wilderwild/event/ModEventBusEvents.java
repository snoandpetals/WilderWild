package net.frozenblock.wilderwild.event;

import net.frozenblock.wilderwild.WilderWild;
import net.frozenblock.wilderwild.particle.PollenParticle;
import net.frozenblock.wilderwild.registry.RegisterParticles;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nonnull;

@Mod.EventBusSubscriber(modid = WilderWild.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerParticleFactories(final RegisterParticleProvidersEvent event) {
        Minecraft.getInstance().particleEngine.register(RegisterParticles.POLLEN_PARTICLE.get(),
                PollenParticle.PollenFactory::new);
    }
}