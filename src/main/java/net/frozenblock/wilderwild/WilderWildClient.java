package net.frozenblock.wilderwild;

import java.util.UUID;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.event.client.ClientSpriteRegistryCallback;
import net.frozenblock.lib.mathematics.AdvancedMath;
import net.frozenblock.lib.sound.FlyBySoundHub;
import net.frozenblock.wilderwild.entity.AncientHornProjectile;
import net.frozenblock.wilderwild.entity.render.AncientHornProjectileModel;
import net.frozenblock.wilderwild.entity.render.AncientHornProjectileRenderer;
import net.frozenblock.wilderwild.entity.render.DisplayLanternBlockEntityRenderer;
import net.frozenblock.wilderwild.entity.render.FireflyRenderer;
import net.frozenblock.wilderwild.entity.render.JellyfishModel;
import net.frozenblock.wilderwild.entity.render.JellyfishRenderer;
import net.frozenblock.wilderwild.entity.render.SculkSensorBlockEntityRenderer;
import net.frozenblock.wilderwild.entity.render.StoneChestBlockEntityRenderer;
import net.frozenblock.wilderwild.misc.CompetitionCounter;
import net.frozenblock.wilderwild.particle.FloatingSculkBubbleParticle;
import net.frozenblock.wilderwild.particle.MesogleaDripParticle;
import net.frozenblock.wilderwild.particle.PollenParticle;
import net.frozenblock.wilderwild.particle.TermiteParticle;
import net.frozenblock.wilderwild.registry.RegisterBlockEntities;
import net.frozenblock.wilderwild.registry.RegisterBlocks;
import net.frozenblock.wilderwild.registry.RegisterEntities;
import net.frozenblock.wilderwild.registry.RegisterItems;
import net.frozenblock.wilderwild.registry.RegisterParticles;
import net.frozenblock.wilderwild.registry.RegisterSounds;
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
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.loader.api.minecraft.ClientOnly;
import org.quiltmc.qsl.base.api.entrypoint.client.ClientModInitializer;
import org.quiltmc.qsl.block.extensions.api.client.BlockRenderLayerMap;

@ClientOnly
public final class WilderWildClient implements ClientModInitializer {
    public static final ModelLayerLocation ANCIENT_HORN_PROJECTILE_LAYER = new ModelLayerLocation(WilderWild.id("ancient_horn_projectile"), "main");
    public static final ModelLayerLocation SCULK_SENSOR = new ModelLayerLocation(WilderWild.id("sculk_sensor"), "main");
    public static final ModelLayerLocation DISPLAY_LANTERN = new ModelLayerLocation(WilderWild.id("display_lantern"), "main");
    public static final ModelLayerLocation STONE_CHEST = new ModelLayerLocation(WilderWild.id("stone_chest"), "main");
    public static final ModelLayerLocation DOUBLE_STONE_CHEST_LEFT = new ModelLayerLocation(WilderWild.id("double_stone_chest_left"), "main");
    public static final ModelLayerLocation DOUBLE_STONE_CHEST_RIGHT = new ModelLayerLocation(WilderWild.id("double_stone_chest_right"), "main");
    public static final ModelLayerLocation JELLYFISH = new ModelLayerLocation(WilderWild.id("jellyfish"), "main");

    @Override
    public void onInitializeClient(ModContainer mod) {
        BlockRenderLayerMap.put(RenderType.cutout(), RegisterBlocks.CARNATION);
        BlockRenderLayerMap.put(RenderType.cutout(), RegisterBlocks.SEEDING_DANDELION);
        BlockRenderLayerMap.put(RenderType.cutout(), RegisterBlocks.POTTED_CARNATION);
        BlockRenderLayerMap.put(RenderType.cutout(), RegisterBlocks.POTTED_SEEDING_DANDELION);
        BlockRenderLayerMap.put(RenderType.cutout(), RegisterBlocks.POTTED_BAOBAB_NUT);
        BlockRenderLayerMap.put(RenderType.cutout(), RegisterBlocks.POTTED_CYPRESS_SAPLING);
        BlockRenderLayerMap.put(RenderType.cutout(), RegisterBlocks.POTTED_BIG_DRIPLEAF);
        BlockRenderLayerMap.put(RenderType.cutout(), RegisterBlocks.POTTED_SMALL_DRIPLEAF);
        BlockRenderLayerMap.put(RenderType.cutout(), RegisterBlocks.POTTED_GRASS);
        BlockRenderLayerMap.put(RenderType.cutout(), RegisterBlocks.DATURA);
        BlockRenderLayerMap.put(RenderType.cutout(), RegisterBlocks.CATTAIL);
        BlockRenderLayerMap.put(RenderType.cutout(), RegisterBlocks.ALGAE);
        BlockRenderLayerMap.put(RenderType.cutout(), RegisterBlocks.MILKWEED);
        BlockRenderLayerMap.put(RenderType.cutout(), RegisterBlocks.POLLEN_BLOCK);
        BlockRenderLayerMap.put(RenderType.translucent(), RegisterBlocks.ECHO_GLASS);
        BlockRenderLayerMap.put(RenderType.cutout(), RegisterBlocks.HANGING_TENDRIL);
        BlockRenderLayerMap.put(RenderType.cutout(), RegisterBlocks.FLOWERING_LILY_PAD);
        BlockRenderLayerMap.put(RenderType.cutout(), RegisterBlocks.BROWN_SHELF_FUNGUS);
        BlockRenderLayerMap.put(RenderType.cutout(), RegisterBlocks.RED_SHELF_FUNGUS);
        BlockRenderLayerMap.put(RenderType.cutout(), RegisterBlocks.BAOBAB_DOOR);
        BlockRenderLayerMap.put(RenderType.cutout(), RegisterBlocks.CYPRESS_DOOR);
        BlockRenderLayerMap.put(RenderType.cutout(), RegisterBlocks.BAOBAB_TRAPDOOR);
        BlockRenderLayerMap.put(RenderType.cutout(), RegisterBlocks.CYPRESS_TRAPDOOR);
        BlockRenderLayerMap.put(RenderType.cutout(), RegisterBlocks.BAOBAB_NUT);
        BlockRenderLayerMap.put(RenderType.cutout(), RegisterBlocks.CYPRESS_SAPLING);
        BlockRenderLayerMap.put(RenderType.cutout(), RegisterBlocks.GLORY_OF_THE_SNOW);
        BlockRenderLayerMap.put(RenderType.cutout(), RegisterBlocks.WHITE_GLORY_OF_THE_SNOW);
        BlockRenderLayerMap.put(RenderType.cutout(), RegisterBlocks.BLUE_GLORY_OF_THE_SNOW);
        BlockRenderLayerMap.put(RenderType.cutout(), RegisterBlocks.PINK_GLORY_OF_THE_SNOW);
        BlockRenderLayerMap.put(RenderType.cutout(), RegisterBlocks.PURPLE_GLORY_OF_THE_SNOW);
        //BlockRenderLayerMap.put(RenderType.cutout(), RegisterBlocks.CYPRESS_ROOTS);
        BlockRenderLayerMap.put(RenderType.solid(), RegisterBlocks.TERMITE_MOUND);
        BlockRenderLayerMap.put(RenderType.cutout(), RegisterBlocks.DISPLAY_LANTERN);
        BlockRenderLayerMap.put(RenderType.cutout(), RegisterBlocks.HOLLOWED_ACACIA_LOG);
        BlockRenderLayerMap.put(RenderType.cutout(), RegisterBlocks.HOLLOWED_BAOBAB_LOG);
        BlockRenderLayerMap.put(RenderType.cutout(), RegisterBlocks.HOLLOWED_BIRCH_LOG);
        BlockRenderLayerMap.put(RenderType.cutout(), RegisterBlocks.HOLLOWED_CYPRESS_LOG);
        BlockRenderLayerMap.put(RenderType.cutout(), RegisterBlocks.HOLLOWED_DARK_OAK_LOG);
        BlockRenderLayerMap.put(RenderType.cutout(), RegisterBlocks.HOLLOWED_JUNGLE_LOG);
        BlockRenderLayerMap.put(RenderType.cutout(), RegisterBlocks.HOLLOWED_MANGROVE_LOG);
        BlockRenderLayerMap.put(RenderType.cutout(), RegisterBlocks.HOLLOWED_OAK_LOG);
        BlockRenderLayerMap.put(RenderType.cutout(), RegisterBlocks.HOLLOWED_SPRUCE_LOG);
        BlockRenderLayerMap.put(RenderType.translucent(), RegisterBlocks.MESOGLEA);
        BlockRenderLayerMap.put(RenderType.translucent(), RegisterBlocks.PURPLE_MESOGLEA);
        BlockRenderLayerMap.put(RenderType.translucent(), RegisterBlocks.BLUE_MESOGLEA);
        BlockRenderLayerMap.put(RenderType.translucent(), RegisterBlocks.LIME_MESOGLEA);
        BlockRenderLayerMap.put(RenderType.translucent(), RegisterBlocks.PINK_MESOGLEA);
        BlockRenderLayerMap.put(RenderType.translucent(), RegisterBlocks.RED_MESOGLEA);
        BlockRenderLayerMap.put(RenderType.translucent(), RegisterBlocks.YELLOW_MESOGLEA);
        BlockRenderLayerMap.put(RenderType.translucent(), RegisterBlocks.BLUE_PEARLESCENT_NEMATOCYST);
        BlockRenderLayerMap.put(RenderType.translucent(), RegisterBlocks.PURPLE_PEARLESCENT_NEMATOCYST);
        BlockRenderLayerMap.put(RenderType.translucent(), RegisterBlocks.BLUE_NEMATOCYST);
        BlockRenderLayerMap.put(RenderType.translucent(), RegisterBlocks.LIME_NEMATOCYST);
        BlockRenderLayerMap.put(RenderType.translucent(), RegisterBlocks.PINK_NEMATOCYST);
        BlockRenderLayerMap.put(RenderType.translucent(), RegisterBlocks.RED_NEMATOCYST);
        BlockRenderLayerMap.put(RenderType.translucent(), RegisterBlocks.YELLOW_NEMATOCYST);
        //BlockRenderLayerMap.put(RegisterBlocks.NEMATOCYST);

        ClientSpriteRegistryCallback.event(InventoryMenu.BLOCK_ATLAS).register((atlasTexture, registry) -> {
            registry.register(WilderWild.id("particle/floating_sculk_bubble_0"));
            registry.register(WilderWild.id("particle/floating_sculk_bubble_1"));
            registry.register(WilderWild.id("particle/floating_sculk_bubble_2"));
            registry.register(WilderWild.id("particle/floating_sculk_bubble_3"));
            registry.register(WilderWild.id("particle/floating_sculk_bubble_4"));
            registry.register(WilderWild.id("particle/floating_sculk_bubble_5"));
            registry.register(WilderWild.id("particle/floating_sculk_bubble_6"));
            registry.register(WilderWild.id("particle/termite_0"));
            registry.register(WilderWild.id("particle/termite_1"));
            registry.register(WilderWild.id("particle/termite_2"));
            registry.register(WilderWild.id("particle/termite_3"));
            registry.register(WilderWild.id("particle/termite_4"));
            registry.register(WilderWild.id("particle/termite_5"));
            registry.register(WilderWild.id("particle/termite_6"));
            registry.register(WilderWild.id("particle/termite_7"));
            registry.register(WilderWild.id("particle/termite_8"));
            registry.register(WilderWild.id("particle/termite_9"));
        });

        ClientSpriteRegistryCallback.event(Sheets.CHEST_SHEET).register((atlasTexture, registry) -> {
            registry.register(WilderWild.id("entity/stone_chest/stone"));
            registry.register(WilderWild.id("entity/stone_chest/stone_left"));
            registry.register(WilderWild.id("entity/stone_chest/stone_right"));
            registry.register(WilderWild.id("entity/stone_chest/ancient"));
            registry.register(WilderWild.id("entity/stone_chest/ancient_left"));
            registry.register(WilderWild.id("entity/stone_chest/ancient_right"));
        });

        ParticleFactoryRegistry.getInstance().register(RegisterParticles.POLLEN, PollenParticle.PollenFactory::new);
        ParticleFactoryRegistry.getInstance().register(RegisterParticles.DANDELION_SEED, PollenParticle.DandelionFactory::new);
        ParticleFactoryRegistry.getInstance().register(RegisterParticles.CONTROLLED_DANDELION_SEED, PollenParticle.ControlledDandelionFactory::new);
        ParticleFactoryRegistry.getInstance().register(RegisterParticles.MILKWEED_SEED, PollenParticle.MilkweedFactory::new);
        ParticleFactoryRegistry.getInstance().register(RegisterParticles.CONTROLLED_MILKWEED_SEED, PollenParticle.ControlledMilkweedFactory::new);
        ParticleFactoryRegistry.getInstance().register(RegisterParticles.FLOATING_SCULK_BUBBLE, FloatingSculkBubbleParticle.BubbleFactory::new);
        ParticleFactoryRegistry.getInstance().register(RegisterParticles.TERMITE, TermiteParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(RegisterParticles.BLUE_PEARLESCENT_HANGING_MESOGLEA, MesogleaDripParticle.BPMesogleaHangProvider::new);
        ParticleFactoryRegistry.getInstance().register(RegisterParticles.BLUE_PEARLESCENT_FALLING_MESOGLEA, MesogleaDripParticle.BPMesogleaFallProvider::new);
        ParticleFactoryRegistry.getInstance().register(RegisterParticles.BLUE_PEARLESCENT_LANDING_MESOGLEA, MesogleaDripParticle.BPMesogleaLandProvider::new);
        ParticleFactoryRegistry.getInstance().register(RegisterParticles.PURPLE_PEARLESCENT_HANGING_MESOGLEA, MesogleaDripParticle.PPMesogleaHangProvider::new);
        ParticleFactoryRegistry.getInstance().register(RegisterParticles.PURPLE_PEARLESCENT_FALLING_MESOGLEA, MesogleaDripParticle.PPMesogleaFallProvider::new);
        ParticleFactoryRegistry.getInstance().register(RegisterParticles.PURPLE_PEARLESCENT_LANDING_MESOGLEA, MesogleaDripParticle.PPMesogleaLandProvider::new);
        ParticleFactoryRegistry.getInstance().register(RegisterParticles.BLUE_HANGING_MESOGLEA, MesogleaDripParticle.BMesogleaHangProvider::new);
        ParticleFactoryRegistry.getInstance().register(RegisterParticles.BLUE_FALLING_MESOGLEA, MesogleaDripParticle.BMesogleaFallProvider::new);
        ParticleFactoryRegistry.getInstance().register(RegisterParticles.BLUE_LANDING_MESOGLEA, MesogleaDripParticle.BMesogleaLandProvider::new);
        ParticleFactoryRegistry.getInstance().register(RegisterParticles.YELLOW_HANGING_MESOGLEA, MesogleaDripParticle.YMesogleaHangProvider::new);
        ParticleFactoryRegistry.getInstance().register(RegisterParticles.YELLOW_FALLING_MESOGLEA, MesogleaDripParticle.YMesogleaFallProvider::new);
        ParticleFactoryRegistry.getInstance().register(RegisterParticles.YELLOW_LANDING_MESOGLEA, MesogleaDripParticle.YMesogleaLandProvider::new);
        ParticleFactoryRegistry.getInstance().register(RegisterParticles.LIME_HANGING_MESOGLEA, MesogleaDripParticle.LMesogleaHangProvider::new);
        ParticleFactoryRegistry.getInstance().register(RegisterParticles.LIME_FALLING_MESOGLEA, MesogleaDripParticle.LMesogleaFallProvider::new);
        ParticleFactoryRegistry.getInstance().register(RegisterParticles.LIME_LANDING_MESOGLEA, MesogleaDripParticle.LMesogleaLandProvider::new);
        ParticleFactoryRegistry.getInstance().register(RegisterParticles.PINK_HANGING_MESOGLEA, MesogleaDripParticle.PMesogleaHangProvider::new);
        ParticleFactoryRegistry.getInstance().register(RegisterParticles.PINK_FALLING_MESOGLEA, MesogleaDripParticle.PMesogleaFallProvider::new);
        ParticleFactoryRegistry.getInstance().register(RegisterParticles.PINK_LANDING_MESOGLEA, MesogleaDripParticle.PMesogleaLandProvider::new);
        ParticleFactoryRegistry.getInstance().register(RegisterParticles.RED_HANGING_MESOGLEA, MesogleaDripParticle.RMesogleaHangProvider::new);
        ParticleFactoryRegistry.getInstance().register(RegisterParticles.RED_FALLING_MESOGLEA, MesogleaDripParticle.RMesogleaFallProvider::new);
        ParticleFactoryRegistry.getInstance().register(RegisterParticles.RED_LANDING_MESOGLEA, MesogleaDripParticle.RMesogleaLandProvider::new);

        EntityRendererRegistry.register(RegisterEntities.FIREFLY, FireflyRenderer::new);
        EntityRendererRegistry.register(RegisterEntities.ANCIENT_HORN_PROJECTILE_ENTITY, AncientHornProjectileRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ANCIENT_HORN_PROJECTILE_LAYER, AncientHornProjectileModel::createBodyLayer);
        EntityRendererRegistry.register(RegisterEntities.JELLYFISH, JellyfishRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(JELLYFISH, JellyfishModel::createBodyLayer);

        BlockEntityRendererRegistry.register(BlockEntityType.SCULK_SENSOR, SculkSensorBlockEntityRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(SCULK_SENSOR, SculkSensorBlockEntityRenderer::getTexturedModelData);

        BlockEntityRendererRegistry.register(RegisterBlockEntities.DISPLAY_LANTERN, DisplayLanternBlockEntityRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(DISPLAY_LANTERN, DisplayLanternBlockEntityRenderer::getTexturedModelData);

        BlockEntityRendererRegistry.register(RegisterBlockEntities.STONE_CHEST, StoneChestBlockEntityRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(STONE_CHEST, StoneChestBlockEntityRenderer::createSingleBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(DOUBLE_STONE_CHEST_LEFT, StoneChestBlockEntityRenderer::createDoubleBodyLeftLayer);
        EntityModelLayerRegistry.registerModelLayer(DOUBLE_STONE_CHEST_RIGHT, StoneChestBlockEntityRenderer::createDoubleBodyRightLayer);

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

        ColorProviderRegistry.BLOCK.register(((state, level, pos, tintIndex) -> {
            if (level == null || pos == null) {
                return 7455580;
            }
            return 2129968;
        }), RegisterBlocks.FLOWERING_LILY_PAD);

        ColorProviderRegistry.ITEM.register(((state, tintIndex) -> 5877296), RegisterBlocks.BAOBAB_LEAVES);
        ColorProviderRegistry.ITEM.register(((state, tintIndex) -> 5877296), RegisterBlocks.CYPRESS_LEAVES);

        ColorProviderRegistry.BLOCK.register(((state, level, pos, tintIndex) -> {
            assert level != null;
            return BiomeColors.getAverageFoliageColor(level, pos);
        }), RegisterBlocks.BAOBAB_LEAVES);
        ColorProviderRegistry.BLOCK.register(((state, level, pos, tintIndex) -> {
            assert level != null;
            return BiomeColors.getAverageFoliageColor(level, pos);
        }), RegisterBlocks.CYPRESS_LEAVES);
        ColorProviderRegistry.BLOCK.register(((state, level, pos, tintIndex) -> {
            assert level != null;
            return BiomeColors.getAverageFoliageColor(level, pos);
        }), RegisterBlocks.POTTED_GRASS);
    }

    private static void receiveAncientHornProjectilePacket() {
        ClientPlayNetworking.registerGlobalReceiver(WilderWild.HORN_PROJECTILE_PACKET_ID, (ctx, handler, byteBuf, responseSender) -> {
            EntityType<?> et = Registry.ENTITY_TYPE.byId(byteBuf.readVarInt());
            UUID uuid = byteBuf.readUUID();
            int entityId = byteBuf.readVarInt();
            Vec3 pos = AncientHornProjectile.EntitySpawnPacket.PacketBufUtil.readVec3d(byteBuf);
            float pitch = AncientHornProjectile.EntitySpawnPacket.PacketBufUtil.readAngle(byteBuf);
            float yaw = AncientHornProjectile.EntitySpawnPacket.PacketBufUtil.readAngle(byteBuf);
            WilderWild.log("Receiving Ancient Horn Projectile Packet At " + pos, WilderWild.DEV_LOGGING);
            ctx.execute(() -> {
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
                WilderWild.log("Spawned Ancient Horn Projectile", WilderWild.UNSTABLE_LOGGING);
            });
        });
    }

    private static void receiveEasyEchoerBubblePacket() {
        ClientPlayNetworking.registerGlobalReceiver(WilderWild.FLOATING_SCULK_BUBBLE_PACKET, (ctx, handler, byteBuf, responseSender) -> {
            Vec3 pos = new Vec3(byteBuf.readDouble(), byteBuf.readDouble(), byteBuf.readDouble());
            int size = byteBuf.readVarInt();
            int age = byteBuf.readVarInt();
            double yVel = byteBuf.readDouble();
            int count = byteBuf.readVarInt();
            ctx.execute(() -> {
                if (Minecraft.getInstance().level == null)
                    throw new IllegalStateException("why is your world null");
                for (int i = 0; i < count; i++) {
                    Minecraft.getInstance().level.addParticle(RegisterParticles.FLOATING_SCULK_BUBBLE, pos.x, pos.y, pos.z, size, age, yVel);
                }
            });
        });
    }

    private static void receiveSeedPacket() {
        ClientPlayNetworking.registerGlobalReceiver(WilderWild.SEED_PACKET, (ctx, handler, byteBuf, responseSender) -> {
            Vec3 pos = new Vec3(byteBuf.readDouble(), byteBuf.readDouble(), byteBuf.readDouble());
            int count = byteBuf.readVarInt();
            ParticleOptions particle = byteBuf.readBoolean() ? RegisterParticles.MILKWEED_SEED : RegisterParticles.DANDELION_SEED;
            ctx.execute(() -> {
                if (Minecraft.getInstance().level == null)
                    throw new IllegalStateException("why is your world null");
                for (int i = 0; i < count; i++) {
                    Minecraft.getInstance().level.addParticle(particle, pos.x, pos.y, pos.z, 0, 0, 0);
                }
            });
        });
    }

    private static void receiveControlledSeedPacket() {
        ClientPlayNetworking.registerGlobalReceiver(WilderWild.CONTROLLED_SEED_PACKET, (ctx, handler, byteBuf, responseSender) -> {
            Vec3 pos = new Vec3(byteBuf.readDouble(), byteBuf.readDouble(), byteBuf.readDouble());
            double velx = byteBuf.readDouble();
            double vely = byteBuf.readDouble();
            double velz = byteBuf.readDouble();
            int count = byteBuf.readVarInt();
            ParticleOptions particle = byteBuf.readBoolean() ? RegisterParticles.CONTROLLED_MILKWEED_SEED : RegisterParticles.CONTROLLED_DANDELION_SEED;
            ctx.execute(() -> {
                if (Minecraft.getInstance().level == null)
                    throw new IllegalStateException("why is your world null");
                for (int i = 0; i < count; i++) {
                    Minecraft.getInstance().level.addParticle(particle, pos.x, pos.y, pos.z, velx, vely, velz);
                }
            });
        });
    }

    private static void receiveTermitePacket() {
        ClientPlayNetworking.registerGlobalReceiver(WilderWild.TERMITE_PARTICLE_PACKET, (ctx, handler, byteBuf, responseSender) -> {
            Vec3 pos = new Vec3(byteBuf.readDouble(), byteBuf.readDouble(), byteBuf.readDouble());
            int count = byteBuf.readVarInt();
            ctx.execute(() -> {
                if (Minecraft.getInstance().level == null)
                    throw new IllegalStateException("why is your world null");
                for (int i = 0; i < count; i++) {
                    Minecraft.getInstance().level.addParticle(RegisterParticles.TERMITE, pos.x, pos.y, pos.z, AdvancedMath.randomPosNeg() / 14, AdvancedMath.randomPosNeg() / 14, AdvancedMath.randomPosNeg() / 14);
                }
            });
        });
    }

    private static void receiveSensorHiccupPacket() {
        ClientPlayNetworking.registerGlobalReceiver(WilderWild.SENSOR_HICCUP_PACKET, (ctx, handler, byteBuf, responseSender) -> {
            Vec3 pos = new Vec3(byteBuf.readDouble(), byteBuf.readDouble(), byteBuf.readDouble());
            ctx.execute(() -> {
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
        ClientPlayNetworking.registerGlobalReceiver(WilderWild.CAPTURE_FIREFLY_NOTIFY_PACKET, (ctx, handler, byteBuf, responseSender) -> {
            boolean creative = byteBuf.readBoolean();
            boolean natural = byteBuf.readBoolean();
            ctx.execute(() -> {
                if (Minecraft.getInstance().level == null)
                    throw new IllegalStateException("why is your world null");
                CompetitionCounter.addFireflyCapture(creative, natural);
            });
        });
    }

    private static void receiveAncientHornKillInfoPacket() {
        ClientPlayNetworking.registerGlobalReceiver(WilderWild.ANCIENT_HORN_KILL_NOTIFY_PACKET, (ctx, handler, byteBuf, responseSender) -> {
            boolean creative = byteBuf.readBoolean();
            boolean natural = byteBuf.readBoolean();
            ctx.execute(() -> {
                if (Minecraft.getInstance().level == null)
                    throw new IllegalStateException("why is your world null");
                CompetitionCounter.addAncientHornKill(creative, natural);
            });
        });
    }

    private static void receiveJellyStingPacket() {
        ClientPlayNetworking.registerGlobalReceiver(WilderWild.JELLY_STING_PACKET, (ctx, handler, byteBuf, responseSender) -> {
            ctx.execute(() -> {
                if (Minecraft.getInstance().level != null) {
                    LocalPlayer player = ctx.player;
                    if (player != null) {
                        Minecraft.getInstance().level.playSound(player, player.getX(), player.getY(), player.getZ(), RegisterSounds.ENTITY_JELLYFISH_STING, SoundSource.NEUTRAL, 1.0F, Minecraft.getInstance().level.random.nextFloat() * 0.2F + 0.9F);
                    }
                }
            });
        });
    }
}
