package net.frozenblock.wilderwild.event;

import net.frozenblock.wilderwild.WilderWild;
import net.frozenblock.wilderwild.init.WWBlockEntityTypes;
import net.frozenblock.wilderwild.init.WWBlocks;
import net.frozenblock.wilderwild.init.WWWoodTypes;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.world.level.FoliageColor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = WilderWild.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEvents {

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(WWBlockEntityTypes.BAOBAB_SIGN_BLOCK_ENTITIES.get(), SignRenderer::new);
    }

    @SubscribeEvent
    public static void registerBlockColors(RegisterColorHandlersEvent.Block event) {
        event.register((state, world, pos, tintIndex) -> {
            if (world == null || pos == null) {
                return FoliageColor.getDefaultColor();
            }
            return BiomeColors.getAverageFoliageColor(world, pos);
        }, WWBlocks.BAOBAB_LEAVES.get(), WWBlocks.CYPRESS_LEAVES.get());
    }

    @SubscribeEvent
    public static void registerItemColors(RegisterColorHandlersEvent.Item event) {
        event.register((stack, tintIndex) -> 5877296, WWBlocks.BAOBAB_LEAVES.get(), WWBlocks.CYPRESS_LEAVES.get());
    }

    @SubscribeEvent
    public static void onClientSetup(final FMLClientSetupEvent event) {
        event.enqueueWork(WWWoodTypes::init);
    }

}
