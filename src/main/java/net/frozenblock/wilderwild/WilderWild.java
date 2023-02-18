package net.frozenblock.wilderwild;

import com.mojang.logging.LogUtils;
import com.mojang.serialization.Codec;
import net.frozenblock.wilderwild.config.WilderWildConfig;
import net.frozenblock.wilderwild.events.MiscEvents;
import net.frozenblock.wilderwild.events.MobEvents;
import net.frozenblock.wilderwild.init.WWBiomeModifiers;
import net.frozenblock.wilderwild.init.WWBlockEntityTypes;
import net.frozenblock.wilderwild.init.WWBlocks;
import net.frozenblock.wilderwild.init.WWEntityTypes;
import net.frozenblock.wilderwild.init.WWFeatures;
import net.frozenblock.wilderwild.init.WWGameEvents;
import net.frozenblock.wilderwild.init.WWGamerules;
import net.frozenblock.wilderwild.init.WWInstruments;
import net.frozenblock.wilderwild.init.WWItems;
import net.frozenblock.wilderwild.init.WWNetwork;
import net.frozenblock.wilderwild.init.WWParticles;
import net.frozenblock.wilderwild.init.WWSoundEvents;
import net.frozenblock.wilderwild.init.WWStructureSets;
import net.frozenblock.wilderwild.init.WWTreeDecoratorTypes;
import net.frozenblock.wilderwild.init.WWTrunkPlacerTypes;
import net.frozenblock.wilderwild.init.WWVanillaIntegration;
import net.frozenblock.wilderwild.world.feature.WilderConfiguredFeatures;
import net.frozenblock.wilderwild.world.feature.WilderMiscConfigured;
import net.frozenblock.wilderwild.world.feature.WilderMiscPlaced;
import net.frozenblock.wilderwild.world.feature.WilderPlacedFeatures;
import net.frozenblock.wilderwild.world.feature.WilderTreeConfigured;
import net.frozenblock.wilderwild.world.feature.WilderTreePlaced;
import net.frozenblock.wilderwild.world.gen.trunk.StraightTrunkWithLogs;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(WilderWild.MOD_ID)
public class WilderWild {
    public static final String MOD_ID = "wilderwild";
    public static final Logger LOGGER = LogUtils.getLogger();
    public static boolean UNSTABLE_LOGGING = false;

    public WilderWild() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);

        IEventBus eventBus = MinecraftForge.EVENT_BUS;

        WWBlocks.BLOCKS.register(modEventBus);
        WWBiomeModifiers.BIOME_MODIFIERS.register(modEventBus);
        WWItems.ITEMS.register(modEventBus);
        WWBlockEntityTypes.BLOCK_ENTITIES.register(modEventBus);
        WWEntityTypes.ENTITY_TYPES.register(modEventBus);
        WWFeatures.FEATURES.register(modEventBus);
        WWGameEvents.GAME_EVENTS.register(modEventBus);
        WWTrunkPlacerTypes.TRUNK_PLACER_TYPES.register(modEventBus);
        WWTreeDecoratorTypes.TREE_DECORATOR_TYPES.register(modEventBus);
        WWParticles.PARTICLE_TYPES.register(modEventBus);
        WWSoundEvents.SOUND_EVENTS.register(modEventBus);
        WWStructureSets.init();

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, WilderWildConfig.COMMON);

        eventBus.register(this);
        eventBus.register(new MobEvents());
        eventBus.register(new MiscEvents());

    }

    public static ResourceLocation id(String path) {
        return new ResourceLocation(MOD_ID, path);
    }

    public static String string(String path) {
        return id(path).toString();
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            WWGamerules.init();
            WWInstruments.init();
            WWVanillaIntegration.init();
            WWNetwork.init();
            WilderConfiguredFeatures.init();
            WilderPlacedFeatures.init();
            WilderMiscConfigured.init();
            WilderMiscPlaced.init();
            WilderTreeConfigured.init();
            WilderTreePlaced.init();
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
    }

