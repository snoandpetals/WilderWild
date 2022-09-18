package net.frozenblock.wilderwild;

import com.mojang.logging.LogUtils;
import com.mojang.serialization.Codec;
import net.frozenblock.wilderwild.registry.RegisterBlocks;
import net.frozenblock.wilderwild.registry.RegisterItems;
import net.frozenblock.wilderwild.registry.RegisterParticles;
import net.frozenblock.wilderwild.world.feature.WilderConfiguredFeatures;
import net.frozenblock.wilderwild.world.feature.WilderPlacedFeatures;
import net.frozenblock.wilderwild.world.feature.WilderTreeConfigured;
import net.frozenblock.wilderwild.world.feature.WilderTreePlaced;
import net.frozenblock.wilderwild.world.gen.trunk.StraightTrunkWithLogs;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
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

    public static final TrunkPlacerType<StraightTrunkWithLogs> STRAIGHT_TRUNK_WITH_LOGS_PLACER_TYPE = registerTrunk("straight_trunk_logs_placer", StraightTrunkWithLogs.CODEC);

    public WilderWild() {
        IEventBus WilderEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        WilderEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);

        RegisterBlocks.register(WilderEventBus);
        RegisterItems.register(WilderEventBus);
        RegisterParticles.register(WilderEventBus);

        WilderPlacedFeatures.init();
        WilderConfiguredFeatures.registerConfiguredFeatures();
        WilderTreeConfigured.registerTreeConfigured();
        WilderTreePlaced.registerTreePlaced();
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
            ItemBlockRenderTypes.setRenderLayer(RegisterBlocks.BAOBAB_NUT.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(RegisterBlocks.CYPRESS_SAPLING.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(RegisterBlocks.POTTED_BAOBAB_NUT.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(RegisterBlocks.POTTED_CYPRESS_SAPLING.get(), RenderType.cutout());
        }

        private void setup(final FMLCommonSetupEvent event) {
            event.enqueueWork(() -> {
                ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(RegisterBlocks.CARNATION.getId(), RegisterBlocks.POTTED_CARNATION);
                event.enqueueWork(() -> {
                    ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(RegisterBlocks.BAOBAB_NUT.getId(), RegisterBlocks.POTTED_CARNATION);
                    event.enqueueWork(() -> {
                        ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(RegisterBlocks.BAOBAB_NUT.getId(), RegisterBlocks.POTTED_CARNATION);
                    });
                });
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
        private static <P extends TrunkPlacer> TrunkPlacerType<P> registerTrunk(String id, Codec<P> codec) {
            return Registry.register(Registry.TRUNK_PLACER_TYPES, id(id), new TrunkPlacerType<>(codec));
        }
    }

