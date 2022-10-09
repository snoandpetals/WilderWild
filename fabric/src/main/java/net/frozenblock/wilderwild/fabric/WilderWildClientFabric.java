package net.frozenblock.wilderwild.fabric;

import dev.architectury.networking.NetworkManager;
import dev.architectury.registry.client.level.entity.EntityModelLayerRegistry;
import dev.architectury.registry.client.level.entity.EntityRendererRegistry;
import dev.architectury.registry.client.particle.ParticleProviderRegistry;
import dev.architectury.registry.client.rendering.BlockEntityRendererRegistry;
import dev.architectury.registry.client.rendering.ColorHandlerRegistry;
import dev.architectury.registry.client.rendering.RenderTypeRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.event.client.ClientSpriteRegistryCallback;
import net.frozenblock.lib.common.mathematics.AdvancedMath;
import net.frozenblock.lib.common.sound.FlyBySoundHub;
import net.frozenblock.wilderwild.common.WilderWild;
import net.frozenblock.wilderwild.fabric.entity.AncientHornProjectile;
import net.frozenblock.wilderwild.fabric.entity.render.*;
import net.frozenblock.wilderwild.fabric.misc.CompetitionCounter;
import net.frozenblock.wilderwild.fabric.particle.FloatingSculkBubbleParticle;
import net.frozenblock.wilderwild.fabric.particle.MesogleaDripParticle;
import net.frozenblock.wilderwild.fabric.particle.PollenParticle;
import net.frozenblock.wilderwild.fabric.particle.TermiteParticle;
import net.frozenblock.wilderwild.fabric.registry.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.phys.Vec3;

import java.util.UUID;

@Environment(EnvType.CLIENT)
public final class WilderWildClientFabric implements ClientModInitializer {
    public static final ModelLayerLocation ANCIENT_HORN_PROJECTILE_LAYER = new ModelLayerLocation(WilderWildFabric.id("ancient_horn_projectile"), "main");
    public static final ModelLayerLocation SCULK_SENSOR = new ModelLayerLocation(WilderWildFabric.id("sculk_sensor"), "main");
    public static final ModelLayerLocation DISPLAY_LANTERN = new ModelLayerLocation(WilderWildFabric.id("display_lantern"), "main");
    public static final ModelLayerLocation STONE_CHEST = new ModelLayerLocation(WilderWildFabric.id("stone_chest"), "main");
    public static final ModelLayerLocation DOUBLE_STONE_CHEST_LEFT = new ModelLayerLocation(WilderWildFabric.id("double_stone_chest_left"), "main");
    public static final ModelLayerLocation DOUBLE_STONE_CHEST_RIGHT = new ModelLayerLocation(WilderWildFabric.id("double_stone_chest_right"), "main");
    public static final ModelLayerLocation JELLYFISH = new ModelLayerLocation(WilderWildFabric.id("jellyfish"), "main");

    @Override
    public void onInitializeClient() {
        RenderTypeRegistry.register(RenderType.cutout(), RegisterBlocks.CARNATION);
        RenderTypeRegistry.register(RenderType.cutout(), RegisterBlocks.SEEDING_DANDELION);
        RenderTypeRegistry.register(RenderType.cutout(), RegisterBlocks.POTTED_CARNATION);
        RenderTypeRegistry.register(RenderType.cutout(), RegisterBlocks.POTTED_SEEDING_DANDELION);
        RenderTypeRegistry.register(RenderType.cutout(), RegisterBlocks.POTTED_BAOBAB_NUT);
        RenderTypeRegistry.register(RenderType.cutout(), RegisterBlocks.POTTED_CYPRESS_SAPLING);
        RenderTypeRegistry.register(RenderType.cutout(), RegisterBlocks.POTTED_BIG_DRIPLEAF);
        RenderTypeRegistry.register(RenderType.cutout(), RegisterBlocks.POTTED_SMALL_DRIPLEAF);
        RenderTypeRegistry.register(RenderType.cutout(), RegisterBlocks.POTTED_GRASS);
        RenderTypeRegistry.register(RenderType.cutout(), RegisterBlocks.DATURA);
        RenderTypeRegistry.register(RenderType.cutout(), RegisterBlocks.CATTAIL);
        RenderTypeRegistry.register(RenderType.cutout(), RegisterBlocks.ALGAE);
        RenderTypeRegistry.register(RenderType.cutout(), RegisterBlocks.MILKWEED);
        RenderTypeRegistry.register(RenderType.cutout(), RegisterBlocks.POLLEN_BLOCK);
        RenderTypeRegistry.register(RenderType.translucent(), RegisterBlocks.ECHO_GLASS);
        RenderTypeRegistry.register(RenderType.cutout(), RegisterBlocks.HANGING_TENDRIL);
        RenderTypeRegistry.register(RenderType.cutout(), RegisterBlocks.FLOWERING_LILY_PAD);
        RenderTypeRegistry.register(RenderType.cutout(), RegisterBlocks.BROWN_SHELF_FUNGUS);
        RenderTypeRegistry.register(RenderType.cutout(), RegisterBlocks.RED_SHELF_FUNGUS);
        RenderTypeRegistry.register(RenderType.cutout(), RegisterBlocks.BAOBAB_DOOR);
        RenderTypeRegistry.register(RenderType.cutout(), RegisterBlocks.CYPRESS_DOOR);
        RenderTypeRegistry.register(RenderType.cutout(), RegisterBlocks.BAOBAB_TRAPDOOR);
        RenderTypeRegistry.register(RenderType.cutout(), RegisterBlocks.CYPRESS_TRAPDOOR);
        RenderTypeRegistry.register(RenderType.cutout(), RegisterBlocks.BAOBAB_NUT);
        RenderTypeRegistry.register(RenderType.cutout(), RegisterBlocks.CYPRESS_SAPLING);
        RenderTypeRegistry.register(RenderType.cutout(), RegisterBlocks.GLORY_OF_THE_SNOW);
        RenderTypeRegistry.register(RenderType.cutout(), RegisterBlocks.WHITE_GLORY_OF_THE_SNOW);
        RenderTypeRegistry.register(RenderType.cutout(), RegisterBlocks.BLUE_GLORY_OF_THE_SNOW);
        RenderTypeRegistry.register(RenderType.cutout(), RegisterBlocks.PINK_GLORY_OF_THE_SNOW);
        RenderTypeRegistry.register(RenderType.cutout(), RegisterBlocks.PURPLE_GLORY_OF_THE_SNOW);
        RenderTypeRegistry.register(RenderType.solid(), RegisterBlocks.TERMITE_MOUND);
        RenderTypeRegistry.register(RenderType.cutout(), RegisterBlocks.DISPLAY_LANTERN);
        RenderTypeRegistry.register(RenderType.cutout(), RegisterBlocks.HOLLOWED_ACACIA_LOG);
        RenderTypeRegistry.register(RenderType.cutout(), RegisterBlocks.HOLLOWED_BAOBAB_LOG);
        RenderTypeRegistry.register(RenderType.cutout(), RegisterBlocks.HOLLOWED_BIRCH_LOG);
        RenderTypeRegistry.register(RenderType.cutout(), RegisterBlocks.HOLLOWED_CYPRESS_LOG);
        RenderTypeRegistry.register(RenderType.cutout(), RegisterBlocks.HOLLOWED_DARK_OAK_LOG);
        RenderTypeRegistry.register(RenderType.cutout(), RegisterBlocks.HOLLOWED_JUNGLE_LOG);
        RenderTypeRegistry.register(RenderType.cutout(), RegisterBlocks.HOLLOWED_MANGROVE_LOG);
        RenderTypeRegistry.register(RenderType.cutout(), RegisterBlocks.HOLLOWED_OAK_LOG);
        RenderTypeRegistry.register(RenderType.cutout(), RegisterBlocks.HOLLOWED_SPRUCE_LOG);
        RenderTypeRegistry.register(RenderType.translucent(), RegisterBlocks.MESOGLEA);
        RenderTypeRegistry.register(RenderType.translucent(), RegisterBlocks.PURPLE_MESOGLEA);
        RenderTypeRegistry.register(RenderType.translucent(), RegisterBlocks.BLUE_MESOGLEA);
        RenderTypeRegistry.register(RenderType.translucent(), RegisterBlocks.LIME_MESOGLEA);
        RenderTypeRegistry.register(RenderType.translucent(), RegisterBlocks.PINK_MESOGLEA);
        RenderTypeRegistry.register(RenderType.translucent(), RegisterBlocks.RED_MESOGLEA);
        RenderTypeRegistry.register(RenderType.translucent(), RegisterBlocks.YELLOW_MESOGLEA);
        RenderTypeRegistry.register(RenderType.translucent(), RegisterBlocks.BLUE_PEARLESCENT_NEMATOCYST);
        RenderTypeRegistry.register(RenderType.translucent(), RegisterBlocks.PURPLE_PEARLESCENT_NEMATOCYST);
        RenderTypeRegistry.register(RenderType.translucent(), RegisterBlocks.BLUE_NEMATOCYST);
        RenderTypeRegistry.register(RenderType.translucent(), RegisterBlocks.LIME_NEMATOCYST);
        RenderTypeRegistry.register(RenderType.translucent(), RegisterBlocks.PINK_NEMATOCYST);
        RenderTypeRegistry.register(RenderType.translucent(), RegisterBlocks.RED_NEMATOCYST);
        RenderTypeRegistry.register(RenderType.translucent(), RegisterBlocks.YELLOW_NEMATOCYST);

        ClientSpriteRegistryCallback.event(InventoryMenu.BLOCK_ATLAS).register((atlasTexture, registry) -> {
            registry.register(WilderWildFabric.id("particle/floating_sculk_bubble_0"));
            registry.register(WilderWildFabric.id("particle/floating_sculk_bubble_1"));
            registry.register(WilderWildFabric.id("particle/floating_sculk_bubble_2"));
            registry.register(WilderWildFabric.id("particle/floating_sculk_bubble_3"));
            registry.register(WilderWildFabric.id("particle/floating_sculk_bubble_4"));
            registry.register(WilderWildFabric.id("particle/floating_sculk_bubble_5"));
            registry.register(WilderWildFabric.id("particle/floating_sculk_bubble_6"));
            registry.register(WilderWildFabric.id("particle/termite_0"));
            registry.register(WilderWildFabric.id("particle/termite_1"));
            registry.register(WilderWildFabric.id("particle/termite_2"));
            registry.register(WilderWildFabric.id("particle/termite_3"));
            registry.register(WilderWildFabric.id("particle/termite_4"));
            registry.register(WilderWildFabric.id("particle/termite_5"));
            registry.register(WilderWildFabric.id("particle/termite_6"));
            registry.register(WilderWildFabric.id("particle/termite_7"));
            registry.register(WilderWildFabric.id("particle/termite_8"));
            registry.register(WilderWildFabric.id("particle/termite_9"));
        });

        ClientSpriteRegistryCallback.event(Sheets.CHEST_SHEET).register((atlasTexture, registry) -> {
            registry.register(WilderWildFabric.id("entity/stone_chest/stone"));
            registry.register(WilderWildFabric.id("entity/stone_chest/stone_left"));
            registry.register(WilderWildFabric.id("entity/stone_chest/stone_right"));
            registry.register(WilderWildFabric.id("entity/stone_chest/ancient"));
            registry.register(WilderWildFabric.id("entity/stone_chest/ancient_left"));
            registry.register(WilderWildFabric.id("entity/stone_chest/ancient_right"));
        });

        ParticleProviderRegistry.register(RegisterParticles.POLLEN, PollenParticle.PollenFactory::new);
        ParticleProviderRegistry.register(RegisterParticles.DANDELION_SEED, PollenParticle.DandelionFactory::new);
        ParticleProviderRegistry.register(RegisterParticles.CONTROLLED_DANDELION_SEED, PollenParticle.ControlledDandelionFactory::new);
        ParticleProviderRegistry.register(RegisterParticles.MILKWEED_SEED, PollenParticle.MilkweedFactory::new);
        ParticleProviderRegistry.register(RegisterParticles.CONTROLLED_MILKWEED_SEED, PollenParticle.ControlledMilkweedFactory::new);
        ParticleProviderRegistry.register(RegisterParticles.FLOATING_SCULK_BUBBLE, FloatingSculkBubbleParticle.BubbleFactory::new);
        ParticleProviderRegistry.register(RegisterParticles.TERMITE, TermiteParticle.Factory::new);
        ParticleProviderRegistry.register(RegisterParticles.BLUE_PEARLESCENT_HANGING_MESOGLEA, MesogleaDripParticle.BPMesogleaHangProvider::new);
        ParticleProviderRegistry.register(RegisterParticles.BLUE_PEARLESCENT_FALLING_MESOGLEA, MesogleaDripParticle.BPMesogleaFallProvider::new);
        ParticleProviderRegistry.register(RegisterParticles.BLUE_PEARLESCENT_LANDING_MESOGLEA, MesogleaDripParticle.BPMesogleaLandProvider::new);
        ParticleProviderRegistry.register(RegisterParticles.PURPLE_PEARLESCENT_HANGING_MESOGLEA, MesogleaDripParticle.PPMesogleaHangProvider::new);
        ParticleProviderRegistry.register(RegisterParticles.PURPLE_PEARLESCENT_FALLING_MESOGLEA, MesogleaDripParticle.PPMesogleaFallProvider::new);
        ParticleProviderRegistry.register(RegisterParticles.PURPLE_PEARLESCENT_LANDING_MESOGLEA, MesogleaDripParticle.PPMesogleaLandProvider::new);
        ParticleProviderRegistry.register(RegisterParticles.BLUE_HANGING_MESOGLEA, MesogleaDripParticle.BMesogleaHangProvider::new);
        ParticleProviderRegistry.register(RegisterParticles.BLUE_FALLING_MESOGLEA, MesogleaDripParticle.BMesogleaFallProvider::new);
        ParticleProviderRegistry.register(RegisterParticles.BLUE_LANDING_MESOGLEA, MesogleaDripParticle.BMesogleaLandProvider::new);
        ParticleProviderRegistry.register(RegisterParticles.YELLOW_HANGING_MESOGLEA, MesogleaDripParticle.YMesogleaHangProvider::new);
        ParticleProviderRegistry.register(RegisterParticles.YELLOW_FALLING_MESOGLEA, MesogleaDripParticle.YMesogleaFallProvider::new);
        ParticleProviderRegistry.register(RegisterParticles.YELLOW_LANDING_MESOGLEA, MesogleaDripParticle.YMesogleaLandProvider::new);
        ParticleProviderRegistry.register(RegisterParticles.LIME_HANGING_MESOGLEA, MesogleaDripParticle.LMesogleaHangProvider::new);
        ParticleProviderRegistry.register(RegisterParticles.LIME_FALLING_MESOGLEA, MesogleaDripParticle.LMesogleaFallProvider::new);
        ParticleProviderRegistry.register(RegisterParticles.LIME_LANDING_MESOGLEA, MesogleaDripParticle.LMesogleaLandProvider::new);
        ParticleProviderRegistry.register(RegisterParticles.PINK_HANGING_MESOGLEA, MesogleaDripParticle.PMesogleaHangProvider::new);
        ParticleProviderRegistry.register(RegisterParticles.PINK_FALLING_MESOGLEA, MesogleaDripParticle.PMesogleaFallProvider::new);
        ParticleProviderRegistry.register(RegisterParticles.PINK_LANDING_MESOGLEA, MesogleaDripParticle.PMesogleaLandProvider::new);
        ParticleProviderRegistry.register(RegisterParticles.RED_HANGING_MESOGLEA, MesogleaDripParticle.RMesogleaHangProvider::new);
        ParticleProviderRegistry.register(RegisterParticles.RED_FALLING_MESOGLEA, MesogleaDripParticle.RMesogleaFallProvider::new);
        ParticleProviderRegistry.register(RegisterParticles.RED_LANDING_MESOGLEA, MesogleaDripParticle.RMesogleaLandProvider::new);

        EntityRendererRegistry.register(() -> RegisterEntities.FIREFLY, FireflyRenderer::new);
        EntityRendererRegistry.register(() -> RegisterEntities.ANCIENT_HORN_PROJECTILE_ENTITY, AncientHornProjectileRenderer::new);
        EntityModelLayerRegistry.register(ANCIENT_HORN_PROJECTILE_LAYER, AncientHornProjectileModel::createBodyLayer);
        EntityRendererRegistry.register(() -> RegisterEntities.JELLYFISH, JellyfishRenderer::new);
        EntityModelLayerRegistry.register(JELLYFISH, JellyfishModel::createBodyLayer);

        BlockEntityRendererRegistry.register(BlockEntityType.SCULK_SENSOR, SculkSensorBlockEntityRenderer::new);
        EntityModelLayerRegistry.register(SCULK_SENSOR, SculkSensorBlockEntityRenderer::getTexturedModelData);

        BlockEntityRendererRegistry.register(RegisterBlockEntities.DISPLAY_LANTERN, DisplayLanternBlockEntityRenderer::new);
        EntityModelLayerRegistry.register(DISPLAY_LANTERN, DisplayLanternBlockEntityRenderer::getTexturedModelData);

        BlockEntityRendererRegistry.register(RegisterBlockEntities.STONE_CHEST, StoneChestBlockEntityRenderer::new);
        EntityModelLayerRegistry.register(STONE_CHEST, StoneChestBlockEntityRenderer::createSingleBodyLayer);
        EntityModelLayerRegistry.register(DOUBLE_STONE_CHEST_LEFT, StoneChestBlockEntityRenderer::createDoubleBodyLeftLayer);
        EntityModelLayerRegistry.register(DOUBLE_STONE_CHEST_RIGHT, StoneChestBlockEntityRenderer::createDoubleBodyRightLayer);

        receiveAncientHornProjectilePacket();
        receiveEasyEchoerBubblePacket();
        receiveSeedPacket();
        receiveControlledSeedPacket();
        receiveTermitePacket();
        receiveSensorHiccupPacket();
        receiveJellyStingPacket();

        receiveFireflyCaptureInfoPacket();
        receiveAncientHornKillInfoPacket();
        FlyBySoundHub.autoEntitiesAndSounds.put(RegisterEntities.ANCIENT_HORN_PROJECTILE_ENTITY, new FlyBySoundHub.FlyBySound(1.0F, 0.5F, SoundSource.PLAYERS, RegisterSounds.ENTITY_ANCIENT_HORN_PROJECTILE_FLYBY));

        ItemProperties.register(RegisterItems.ANCIENT_HORN, new ResourceLocation("tooting"), (itemStack, clientLevel, livingEntity, seed) -> livingEntity != null && livingEntity.isUsingItem() && livingEntity.getUseItem() == itemStack ? 1.0F : 0.0F);
        ItemProperties.register(RegisterItems.COPPER_HORN, new ResourceLocation("tooting"), (itemStack, clientLevel, livingEntity, seed) -> livingEntity != null && livingEntity.isUsingItem() && livingEntity.getUseItem() == itemStack ? 1.0F : 0.0F);

        ColorHandlerRegistry.registerBlockColors(((state, level, pos, tintIndex) -> {
            if (level == null || pos == null) {
                return 7455580;
            }
            return 2129968;
        }), RegisterBlocks.FLOWERING_LILY_PAD);

        ColorHandlerRegistry.registerItemColors(((state, tintIndex) -> 5877296), RegisterBlocks.BAOBAB_LEAVES);
        ColorHandlerRegistry.registerItemColors(((state, tintIndex) -> 5877296), RegisterBlocks.CYPRESS_LEAVES);

        ColorHandlerRegistry.registerBlockColors(((state, level, pos, tintIndex) -> {
            assert level != null;
            return BiomeColors.getAverageFoliageColor(level, pos);
        }), RegisterBlocks.BAOBAB_LEAVES);
        ColorHandlerRegistry.registerBlockColors(((state, level, pos, tintIndex) -> {
            assert level != null;
            return BiomeColors.getAverageFoliageColor(level, pos);
        }), RegisterBlocks.CYPRESS_LEAVES);
        ColorHandlerRegistry.registerBlockColors(((state, level, pos, tintIndex) -> {
            assert level != null;
            return BiomeColors.getAverageFoliageColor(level, pos);
        }), RegisterBlocks.POTTED_GRASS);
    }

    private static void receiveAncientHornProjectilePacket() {
        NetworkManager.registerReceiver(NetworkManager.s2c(), WilderWildFabric.HORN_PROJECTILE_PACKET_ID, (buf, context) -> {
            EntityType<?> et = Registry.ENTITY_TYPE.byId(buf.readVarInt());
            UUID uuid = buf.readUUID();
            int entityId = buf.readVarInt();
            Vec3 pos = AncientHornProjectile.EntitySpawnPacket.PacketBufUtil.readVec3d(buf);
            float pitch = AncientHornProjectile.EntitySpawnPacket.PacketBufUtil.readAngle(buf);
            float yaw = AncientHornProjectile.EntitySpawnPacket.PacketBufUtil.readAngle(buf);
            WilderWildFabric.log("Receiving Ancient Horn Projectile Packet At " + pos, WilderWild.DEV_LOGGING);
            context.queue(() -> {
                if (Minecraft.getInstance().level == null)
                    throw new IllegalStateException("Tried to spawn entity in a null world!");
                Entity e = et.create(Minecraft.getInstance().level);
                if (e == null)
                    throw new IllegalStateException("Failed to create instance of entity \"" + Registry.ENTITY_TYPE.getKey(et) + "\"!");
                e.syncPacketPositionCodec(pos.x, pos.y, pos.z);
                e.setPosRaw(pos.x, pos.y, pos.z);
                e.setXRot(pitch);
                e.setYRot(yaw);
                e.setId(entityId);
                e.setUUID(uuid);
                Minecraft.getInstance().level.putNonPlayerEntity(entityId, e);
                WilderWildFabric.log("Spawned Ancient Horn Projectile", WilderWild.UNSTABLE_LOGGING);
            });
        });
    }

    private static void receiveEasyEchoerBubblePacket() {
        NetworkManager.registerReceiver(NetworkManager.s2c(), WilderWildFabric.FLOATING_SCULK_BUBBLE_PACKET, (buf, context) -> {
            Vec3 pos = new Vec3(buf.readDouble(), buf.readDouble(), buf.readDouble());
            int size = buf.readVarInt();
            int age = buf.readVarInt();
            double yVel = buf.readDouble();
            int count = buf.readVarInt();
            context.queue(() -> {
                if (Minecraft.getInstance().level == null)
                    throw new IllegalStateException("why is your world null");
                for (int i = 0; i < count; i++) {
                    Minecraft.getInstance().level.addParticle(RegisterParticles.FLOATING_SCULK_BUBBLE, pos.x, pos.y, pos.z, size, age, yVel);
                }
            });
        });
    }

    private static void receiveSeedPacket() {
        NetworkManager.registerReceiver(NetworkManager.s2c(), WilderWildFabric.SEED_PACKET, (buf, context) -> {
            Vec3 pos = new Vec3(buf.readDouble(), buf.readDouble(), buf.readDouble());
            int count = buf.readVarInt();
            ParticleOptions particle = buf.readBoolean() ? RegisterParticles.MILKWEED_SEED : RegisterParticles.DANDELION_SEED;
            context.queue(() -> {
                if (Minecraft.getInstance().level == null)
                    throw new IllegalStateException("why is your world null");
                for (int i = 0; i < count; i++) {
                    Minecraft.getInstance().level.addParticle(particle, pos.x, pos.y, pos.z, 0, 0, 0);
                }
            });
        });
    }

    private static void receiveControlledSeedPacket() {
        NetworkManager.registerReceiver(NetworkManager.s2c(), WilderWildFabric.CONTROLLED_SEED_PACKET, (buf, context) -> {
            Vec3 pos = new Vec3(buf.readDouble(), buf.readDouble(), buf.readDouble());
            double velx = buf.readDouble();
            double vely = buf.readDouble();
            double velz = buf.readDouble();
            int count = buf.readVarInt();
            ParticleOptions particle = buf.readBoolean() ? RegisterParticles.CONTROLLED_MILKWEED_SEED : RegisterParticles.CONTROLLED_DANDELION_SEED;
            context.queue(() -> {
                if (Minecraft.getInstance().level == null)
                    throw new IllegalStateException("why is your world null");
                for (int i = 0; i < count; i++) {
                    Minecraft.getInstance().level.addParticle(particle, pos.x, pos.y, pos.z, velx, vely, velz);
                }
            });
        });
    }

    private static void receiveTermitePacket() {
        NetworkManager.registerReceiver(NetworkManager.s2c(), WilderWildFabric.TERMITE_PARTICLE_PACKET, (buf, context) -> {
            Vec3 pos = new Vec3(buf.readDouble(), buf.readDouble(), buf.readDouble());
            int count = buf.readVarInt();
            context.queue(() -> {
                if (Minecraft.getInstance().level == null)
                    throw new IllegalStateException("why is your world null");
                for (int i = 0; i < count; i++) {
                    Minecraft.getInstance().level.addParticle(RegisterParticles.TERMITE, pos.x, pos.y, pos.z, AdvancedMath.randomPosNeg() / 14, AdvancedMath.randomPosNeg() / 14, AdvancedMath.randomPosNeg() / 14);
                }
            });
        });
    }

    private static void receiveSensorHiccupPacket() {
        NetworkManager.registerReceiver(NetworkManager.s2c(), WilderWildFabric.SENSOR_HICCUP_PACKET, (buf, context) -> {
            Vec3 pos = new Vec3(buf.readDouble(), buf.readDouble(), buf.readDouble());
            context.queue(() -> {
                if (Minecraft.getInstance().level == null)
                    throw new IllegalStateException("why is your world null");
                ClientLevel level = Minecraft.getInstance().level;
                int i = 5578058;
                boolean bl2 = level.random.nextBoolean();
                if (bl2) {
                    double d = (double) (i >> 16 & 255) / 255.0D;
                    double e = (double) (i >> 8 & 255) / 255.0D;
                    double f = (double) (i & 255) / 255.0D;
                    level.addParticle(ParticleTypes.ENTITY_EFFECT, pos.x, pos.y, pos.z, d, e, f);
                }
            });
        });
    }

    private static void receiveFireflyCaptureInfoPacket() {
        NetworkManager.registerReceiver(NetworkManager.s2c(), WilderWildFabric.CAPTURE_FIREFLY_NOTIFY_PACKET, (buf, context) -> {
            boolean creative = buf.readBoolean();
            boolean natural = buf.readBoolean();
            context.queue(() -> {
                if (Minecraft.getInstance().level == null)
                    throw new IllegalStateException("why is your world null");
                CompetitionCounter.addFireflyCapture(creative, natural);
            });
        });
    }

    private static void receiveAncientHornKillInfoPacket() {
        NetworkManager.registerReceiver(NetworkManager.s2c(), WilderWildFabric.ANCIENT_HORN_KILL_NOTIFY_PACKET, (buf, context) -> {
            boolean creative = buf.readBoolean();
            boolean natural = buf.readBoolean();
            context.queue(() -> {
                if (Minecraft.getInstance().level == null)
                    throw new IllegalStateException("why is your world null");
                CompetitionCounter.addAncientHornKill(creative, natural);
            });
        });
    }

    private static void receiveJellyStingPacket() {
        NetworkManager.registerReceiver(NetworkManager.s2c(), WilderWildFabric.JELLY_STING_PACKET, (buf, context) -> {
            context.queue(() -> {
                if (Minecraft.getInstance().level != null) {
                    LocalPlayer player = (LocalPlayer) context.getPlayer();
                    if (player != null) {
                        Minecraft.getInstance().level.playSound(player, player.getX(), player.getY(), player.getZ(), RegisterSounds.ENTITY_JELLYFISH_STING, SoundSource.NEUTRAL, 1.0f, Minecraft.getInstance().level.random.nextFloat() * 0.2f + 0.9f);
                    }
                }
            });
        });
    }
}
