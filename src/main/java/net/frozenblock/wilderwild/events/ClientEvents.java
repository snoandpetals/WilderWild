package net.frozenblock.wilderwild.events;

import net.frozenblock.wilderwild.WilderWild;
import net.frozenblock.wilderwild.client.models.entity.AncientHornProjectileModel;
import net.frozenblock.wilderwild.client.models.entity.JellyfishModel;
import net.frozenblock.wilderwild.client.renderers.entity.AncientHornProjectileRenderer;
import net.frozenblock.wilderwild.client.renderers.entity.FireflyRenderer;
import net.frozenblock.wilderwild.client.renderers.entity.JellyfishRenderer;
import net.frozenblock.wilderwild.init.WWBlockEntityTypes;
import net.frozenblock.wilderwild.init.WWBlocks;
import net.frozenblock.wilderwild.init.WWEntityTypes;
import net.frozenblock.wilderwild.init.WWModelLayers;
import net.frozenblock.wilderwild.init.WWParticles;
import net.frozenblock.wilderwild.init.WWWoodTypes;
import net.frozenblock.wilderwild.particle.FloatingSculkBubbleParticle;
import net.frozenblock.wilderwild.particle.MesogleaDripParticle;
import net.frozenblock.wilderwild.particle.PollenParticle;
import net.frozenblock.wilderwild.particle.TermiteParticle;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.world.level.FoliageColor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = WilderWild.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEvents {

    @SubscribeEvent
    public static void onClientSetup(final FMLClientSetupEvent event) {
        ItemBlockRenderTypes.setRenderLayer(WWBlocks.CARNATION.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(WWBlocks.SEEDING_DANDELION.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(WWBlocks.POTTED_CARNATION.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(WWBlocks.POTTED_SEEDING_DANDELION.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(WWBlocks.POTTED_BAOBAB_NUT.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(WWBlocks.POTTED_CYPRESS_SAPLING.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(WWBlocks.POTTED_BIG_DRIPLEAF.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(WWBlocks.POTTED_SMALL_DRIPLEAF.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(WWBlocks.POTTED_GRASS.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(WWBlocks.DATURA.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(WWBlocks.CATTAIL.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(WWBlocks.ALGAE.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(WWBlocks.MILKWEED.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(WWBlocks.POLLEN_BLOCK.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(WWBlocks.ECHO_GLASS.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(WWBlocks.HANGING_TENDRIL.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(WWBlocks.FLOWERING_LILY_PAD.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(WWBlocks.BROWN_SHELF_FUNGUS.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(WWBlocks.RED_SHELF_FUNGUS.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(WWBlocks.BAOBAB_DOOR.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(WWBlocks.CYPRESS_DOOR.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(WWBlocks.BAOBAB_TRAPDOOR.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(WWBlocks.CYPRESS_TRAPDOOR.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(WWBlocks.BAOBAB_NUT.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(WWBlocks.CYPRESS_SAPLING.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(WWBlocks.GLORY_OF_THE_SNOW.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(WWBlocks.WHITE_GLORY_OF_THE_SNOW.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(WWBlocks.BLUE_GLORY_OF_THE_SNOW.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(WWBlocks.PINK_GLORY_OF_THE_SNOW.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(WWBlocks.PURPLE_GLORY_OF_THE_SNOW.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(WWBlocks.TERMITE_MOUND.get(), RenderType.solid());
        ItemBlockRenderTypes.setRenderLayer(WWBlocks.DISPLAY_LANTERN.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(WWBlocks.HOLLOWED_ACACIA_LOG.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(WWBlocks.HOLLOWED_BAOBAB_LOG.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(WWBlocks.HOLLOWED_BIRCH_LOG.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(WWBlocks.HOLLOWED_CYPRESS_LOG.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(WWBlocks.HOLLOWED_DARK_OAK_LOG.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(WWBlocks.HOLLOWED_JUNGLE_LOG.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(WWBlocks.HOLLOWED_MANGROVE_LOG.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(WWBlocks.HOLLOWED_OAK_LOG.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(WWBlocks.HOLLOWED_SPRUCE_LOG.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(WWBlocks.BLUE_PEARLESCENT_MESOGLEA.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(WWBlocks.PURPLE_PEARLESCENT_MESOGLEA.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(WWBlocks.BLUE_MESOGLEA.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(WWBlocks.LIME_MESOGLEA.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(WWBlocks.PINK_MESOGLEA.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(WWBlocks.RED_MESOGLEA.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(WWBlocks.YELLOW_MESOGLEA.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(WWBlocks.BLUE_PEARLESCENT_NEMATOCYST.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(WWBlocks.PURPLE_PEARLESCENT_NEMATOCYST.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(WWBlocks.BLUE_NEMATOCYST.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(WWBlocks.LIME_NEMATOCYST.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(WWBlocks.PINK_NEMATOCYST.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(WWBlocks.RED_NEMATOCYST.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(WWBlocks.YELLOW_NEMATOCYST.get(), RenderType.translucent());
        event.enqueueWork(WWWoodTypes::init);
    }

    @SubscribeEvent
    public static void registerParticle(RegisterParticleProvidersEvent event) {
        event.register(WWParticles.POLLEN.get(), PollenParticle.PollenFactory::new);
        event.register(WWParticles.DANDELION_SEED.get(), PollenParticle.DandelionFactory::new);
        event.register(WWParticles.CONTROLLED_DANDELION_SEED.get(), PollenParticle.ControlledDandelionFactory::new);
        event.register(WWParticles.MILKWEED_SEED.get(), PollenParticle.MilkweedFactory::new);
        event.register(WWParticles.CONTROLLED_MILKWEED_SEED.get(), PollenParticle.ControlledMilkweedFactory::new);
        event.register(WWParticles.FLOATING_SCULK_BUBBLE.get(), FloatingSculkBubbleParticle.BubbleFactory::new);
        event.register(WWParticles.TERMITE.get(), TermiteParticle.Factory::new);
        event.register(WWParticles.BLUE_PEARLESCENT_HANGING_MESOGLEA.get(), MesogleaDripParticle.BPMesogleaHangProvider::new);
        event.register(WWParticles.BLUE_PEARLESCENT_FALLING_MESOGLEA.get(), MesogleaDripParticle.BPMesogleaFallProvider::new);
        event.register(WWParticles.BLUE_PEARLESCENT_LANDING_MESOGLEA.get(), MesogleaDripParticle.BPMesogleaLandProvider::new);
        event.register(WWParticles.PURPLE_PEARLESCENT_HANGING_MESOGLEA.get(), MesogleaDripParticle.PPMesogleaHangProvider::new);
        event.register(WWParticles.PURPLE_PEARLESCENT_FALLING_MESOGLEA.get(), MesogleaDripParticle.PPMesogleaFallProvider::new);
        event.register(WWParticles.PURPLE_PEARLESCENT_LANDING_MESOGLEA.get(), MesogleaDripParticle.PPMesogleaLandProvider::new);
        event.register(WWParticles.BLUE_HANGING_MESOGLEA.get(), MesogleaDripParticle.BMesogleaHangProvider::new);
        event.register(WWParticles.BLUE_FALLING_MESOGLEA.get(), MesogleaDripParticle.BMesogleaFallProvider::new);
        event.register(WWParticles.BLUE_LANDING_MESOGLEA.get(), MesogleaDripParticle.BMesogleaLandProvider::new);
        event.register(WWParticles.YELLOW_HANGING_MESOGLEA.get(), MesogleaDripParticle.YMesogleaHangProvider::new);
        event.register(WWParticles.YELLOW_FALLING_MESOGLEA.get(), MesogleaDripParticle.YMesogleaFallProvider::new);
        event.register(WWParticles.YELLOW_LANDING_MESOGLEA.get(), MesogleaDripParticle.YMesogleaLandProvider::new);
        event.register(WWParticles.LIME_HANGING_MESOGLEA.get(), MesogleaDripParticle.LMesogleaHangProvider::new);
        event.register(WWParticles.LIME_FALLING_MESOGLEA.get(), MesogleaDripParticle.LMesogleaFallProvider::new);
        event.register(WWParticles.LIME_LANDING_MESOGLEA.get(), MesogleaDripParticle.LMesogleaLandProvider::new);
        event.register(WWParticles.PINK_HANGING_MESOGLEA.get(), MesogleaDripParticle.PMesogleaHangProvider::new);
        event.register(WWParticles.PINK_FALLING_MESOGLEA.get(), MesogleaDripParticle.PMesogleaFallProvider::new);
        event.register(WWParticles.PINK_LANDING_MESOGLEA.get(), MesogleaDripParticle.PMesogleaLandProvider::new);
        event.register(WWParticles.RED_HANGING_MESOGLEA.get(), MesogleaDripParticle.RMesogleaHangProvider::new);
        event.register(WWParticles.RED_FALLING_MESOGLEA.get(), MesogleaDripParticle.RMesogleaFallProvider::new);
        event.register(WWParticles.RED_LANDING_MESOGLEA.get(), MesogleaDripParticle.RMesogleaLandProvider::new);
    }

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(WWBlockEntityTypes.BAOBAB_SIGN_BLOCK_ENTITIES.get(), SignRenderer::new);
    }

    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(WWModelLayers.JELLYFISH, JellyfishModel::createBodyLayer);
        event.registerLayerDefinition(WWModelLayers.ANCIENT_HORN_PROJECTILE_LAYER, AncientHornProjectileModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(WWEntityTypes.JELLYFISH.get(), JellyfishRenderer::new);
        event.registerEntityRenderer(WWEntityTypes.FIREFLY.get(), FireflyRenderer::new);
        event.registerEntityRenderer(WWEntityTypes.ANCIENT_HORN_PROJECTILE_ENTITY.get(), AncientHornProjectileRenderer::new);
    }

    @SubscribeEvent
    public static void registerBlockColors(RegisterColorHandlersEvent.Block event) {
        event.register((state, world, pos, tintIndex) -> {
            if (world == null || pos == null) {
                return FoliageColor.getDefaultColor();
            }
            return BiomeColors.getAverageFoliageColor(world, pos);
        }, WWBlocks.BAOBAB_LEAVES.get(), WWBlocks.CYPRESS_LEAVES.get());
        event.register((state, world, pos, tintIndex) -> {
            if (world == null || pos == null) {
                return 7455580;
            }
            return 2129968;
        }, WWBlocks.FLOWERING_LILY_PAD.get());
    }

    @SubscribeEvent
    public static void registerItemColors(RegisterColorHandlersEvent.Item event) {
        event.register((stack, tintIndex) -> 5877296, WWBlocks.BAOBAB_LEAVES.get(), WWBlocks.CYPRESS_LEAVES.get());
    }

}
