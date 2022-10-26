package net.frozenblock.wilderwild;

import com.mojang.logging.LogUtils;
import com.mojang.serialization.Codec;
import net.frozenblock.wilderwild.init.WWBlockEntityTypes;
import net.frozenblock.wilderwild.init.WWBlocks;
import net.frozenblock.wilderwild.init.WWItems;
import net.frozenblock.wilderwild.init.WWParticles;
import net.frozenblock.wilderwild.init.WWWoodTypes;
import net.frozenblock.wilderwild.world.feature.WilderConfiguredFeatures;
import net.frozenblock.wilderwild.world.feature.WilderPlacedFeatures;
import net.frozenblock.wilderwild.world.feature.WilderTreeConfigured;
import net.frozenblock.wilderwild.world.feature.WilderTreePlaced;
import net.frozenblock.wilderwild.world.gen.trunk.StraightTrunkWithLogs;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
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
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        eventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);

        WWBlocks.BLOCKS.register(eventBus);
        WWBlockEntityTypes.BLOCK_ENTITIES.register(eventBus);
        WWItems.ITEMS.register(eventBus);
        WWParticles.PARTICLE_TYPES.register(eventBus);

    }

    public static ResourceLocation id(String path) {
        return new ResourceLocation(MOD_ID, path);
    }

    public static String string(String path) {
        return id(path).toString();
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            WilderConfiguredFeatures.init();
            WilderPlacedFeatures.init();
            WilderTreeConfigured.init();
            WilderTreePlaced.init();
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(WWBlocks.CARNATION.getId(), WWBlocks.POTTED_CARNATION);
            event.enqueueWork(() -> {
                ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(WWBlocks.BAOBAB_NUT.getId(), WWBlocks.POTTED_CARNATION);
                event.enqueueWork(() -> {
                    ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(WWBlocks.BAOBAB_NUT.getId(), WWBlocks.POTTED_CARNATION);
                });
            });
        });
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

