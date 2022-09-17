package net.frozenblock.wilderwild;

import com.mojang.logging.LogUtils;
import net.frozenblock.wilderwild.registry.RegisterBlocks;
import net.frozenblock.wilderwild.registry.RegisterItems;
import net.frozenblock.wilderwild.registry.RegisterParticles;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(WilderWild.MOD_ID)
public class WilderWild {
    public static final String MOD_ID = "wilderwild";
    private static final Logger LOGGER = LogUtils.getLogger();
    public static boolean UNSTABLE_LOGGING = false;


    public WilderWild() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);

        RegisterBlocks.register(modEventBus);
        RegisterItems.register(modEventBus);
        RegisterParticles.register(modEventBus);
    }
    public static ResourceLocation id(String path) {
        return new ResourceLocation(MOD_ID, path);
    }
    public static String string(String path) {
        return id(path).toString();
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            ItemBlockRenderTypes.setRenderLayer(RegisterBlocks.CARNATION.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(RegisterBlocks.POTTED_CARNATION.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(RegisterBlocks.CATTAIL.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(RegisterBlocks.POLLEN_BLOCK.get(), RenderType.cutout());
        }
        private void setup(final FMLCommonSetupEvent event) {
            event.enqueueWork(() -> {
                ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(RegisterBlocks.CARNATION.getId(), RegisterBlocks.POTTED_CARNATION);
            });
        }
    }
    public static void log(String string, boolean shouldLog) {
        if (shouldLog) {
            LOGGER.info(string);
        }
    }

    public static void logInsane(String string, boolean shouldLog) {
        if (shouldLog) {
            for (int i = 0; i < Math.random() * 5; i++) {
                LOGGER.warn(string);
                LOGGER.error(string);
                LOGGER.warn(string);
                LOGGER.error(string);
                LOGGER.warn(string);
                LOGGER.error(string);
                LOGGER.warn(string);
                LOGGER.error(string);
            }
        }
    }

    public static void log(Entity entity, String string, boolean shouldLog) {
        if (shouldLog) {
            LOGGER.info(entity.toString() + " : " + string + " : " + entity.position());
        }
    }

    public static void log(Block block, String string, boolean shouldLog) {
        if (shouldLog) {
            LOGGER.info(block.toString() + " : " + string + " : ");
        }
    }

    public static void log(Block block, BlockPos pos, String string, boolean shouldLog) {
        if (shouldLog) {
            LOGGER.info(block.toString() + " : " + string + " : " + pos);
        }
    }

    public static void logWild(String string, boolean shouldLog) {
        if (shouldLog) {
            LOGGER.info(string + " " + MOD_ID);
        }
    }
}

