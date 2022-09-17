package net.frozenblock.wilderwild;

import com.mojang.logging.LogUtils;
import net.frozenblock.wilderwild.registry.RegisterBlocks;
import net.frozenblock.wilderwild.registry.RegisterItems;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
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


    public WilderWild() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);

        RegisterBlocks.register(modEventBus);
        RegisterItems.register(modEventBus);
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
        }
        private void setup(final FMLCommonSetupEvent event) {
            event.enqueueWork(() -> {
                ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(RegisterBlocks.CARNATION.getId(), RegisterBlocks.POTTED_CARNATION);
            });
        }
    }
}

