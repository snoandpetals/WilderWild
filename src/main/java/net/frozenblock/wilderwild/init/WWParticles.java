package net.frozenblock.wilderwild.init;

import net.frozenblock.wilderwild.WilderWild;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = WilderWild.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class WWParticles {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, WilderWild.MOD_ID);

    public static final RegistryObject<SimpleParticleType> POLLEN = simpleParticle("pollen");
    public static final RegistryObject<SimpleParticleType> DANDELION_SEED = simpleParticle("dandelion_seed");
    public static final RegistryObject<SimpleParticleType> CONTROLLED_DANDELION_SEED = simpleParticle("controlled_dandelion_seed");
    public static final RegistryObject<SimpleParticleType> MILKWEED_SEED = simpleParticle("milkweed_seed");
    public static final RegistryObject<SimpleParticleType> CONTROLLED_MILKWEED_SEED = simpleParticle("controlled_milkweed_seed");
    public static final RegistryObject<SimpleParticleType> FLOATING_SCULK_BUBBLE = simpleParticle("floating_sculk_bubble");
    public static final RegistryObject<SimpleParticleType> TERMITE = simpleParticle("termite");
    public static final RegistryObject<SimpleParticleType> BLUE_PEARLESCENT_HANGING_MESOGLEA = simpleParticle("blue_pearlescent_hanging_mesoglea_drip");
    public static final RegistryObject<SimpleParticleType> BLUE_PEARLESCENT_FALLING_MESOGLEA = simpleParticle("blue_pearlescent_falling_mesoglea_drip");
    public static final RegistryObject<SimpleParticleType> BLUE_PEARLESCENT_LANDING_MESOGLEA = simpleParticle("blue_pearlescent_landing_mesoglea_drip");
    public static final RegistryObject<SimpleParticleType> PURPLE_PEARLESCENT_HANGING_MESOGLEA = simpleParticle("purple_pearlescent_hanging_mesoglea_drip");
    public static final RegistryObject<SimpleParticleType> PURPLE_PEARLESCENT_FALLING_MESOGLEA = simpleParticle("purple_pearlescent_falling_mesoglea_drip");
    public static final RegistryObject<SimpleParticleType> PURPLE_PEARLESCENT_LANDING_MESOGLEA = simpleParticle("purple_pearlescent_landing_mesoglea_drip");
    public static final RegistryObject<SimpleParticleType> PINK_HANGING_MESOGLEA = simpleParticle("pink_hanging_mesoglea_drip");
    public static final RegistryObject<SimpleParticleType> PINK_FALLING_MESOGLEA = simpleParticle("pink_falling_mesoglea_drip");
    public static final RegistryObject<SimpleParticleType> PINK_LANDING_MESOGLEA = simpleParticle("pink_landing_mesoglea_drip");
    public static final RegistryObject<SimpleParticleType> RED_HANGING_MESOGLEA = simpleParticle("red_hanging_mesoglea_drip");
    public static final RegistryObject<SimpleParticleType> RED_FALLING_MESOGLEA = simpleParticle("red_falling_mesoglea_drip");
    public static final RegistryObject<SimpleParticleType> RED_LANDING_MESOGLEA = simpleParticle("red_landing_mesoglea_drip");
    public static final RegistryObject<SimpleParticleType> YELLOW_HANGING_MESOGLEA = simpleParticle("yellow_hanging_mesoglea_drip");
    public static final RegistryObject<SimpleParticleType> YELLOW_FALLING_MESOGLEA = simpleParticle("yellow_falling_mesoglea_drip");
    public static final RegistryObject<SimpleParticleType> YELLOW_LANDING_MESOGLEA = simpleParticle("yellow_landing_mesoglea_drip");
    public static final RegistryObject<SimpleParticleType> LIME_HANGING_MESOGLEA = simpleParticle("lime_hanging_mesoglea_drip");
    public static final RegistryObject<SimpleParticleType> LIME_FALLING_MESOGLEA = simpleParticle("lime_falling_mesoglea_drip");
    public static final RegistryObject<SimpleParticleType> LIME_LANDING_MESOGLEA = simpleParticle("lime_landing_mesoglea_drip");
    public static final RegistryObject<SimpleParticleType> BLUE_HANGING_MESOGLEA = simpleParticle("blue_hanging_mesoglea_drip");
    public static final RegistryObject<SimpleParticleType> BLUE_FALLING_MESOGLEA = simpleParticle("blue_falling_mesoglea_drip");
    public static final RegistryObject<SimpleParticleType> BLUE_LANDING_MESOGLEA = simpleParticle("blue_landing_mesoglea_drip");

    private static RegistryObject<SimpleParticleType> simpleParticle(String id) {
        return PARTICLE_TYPES.register(id, () -> new SimpleParticleType(false));
    }

}