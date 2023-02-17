package net.frozenblock.wilderwild.init;

import net.frozenblock.wilderwild.WilderWild;
import net.frozenblock.wilderwild.block.AlgaeBlock;
import net.frozenblock.wilderwild.block.BaobabLeavesBlock;
import net.frozenblock.wilderwild.block.BaobabNutBlock;
import net.frozenblock.wilderwild.block.DisplayLanternBlock;
import net.frozenblock.wilderwild.block.EchoGlassBlock;
import net.frozenblock.wilderwild.block.FlowerLichenBlock;
import net.frozenblock.wilderwild.block.FloweringLilyPadBlock;
import net.frozenblock.wilderwild.block.GloryOfTheSnowBlock;
import net.frozenblock.wilderwild.block.HangingTendrilBlock;
import net.frozenblock.wilderwild.block.HollowedLogBlock;
import net.frozenblock.wilderwild.block.MesogleaBlock;
import net.frozenblock.wilderwild.block.MilkweedBlock;
import net.frozenblock.wilderwild.block.NematocystBlock;
import net.frozenblock.wilderwild.block.OsseousSculkBlock;
import net.frozenblock.wilderwild.block.PollenBlock;
import net.frozenblock.wilderwild.block.SculkSlabBlock;
import net.frozenblock.wilderwild.block.SculkStairsBlock;
import net.frozenblock.wilderwild.block.SculkWallBlock;
import net.frozenblock.wilderwild.block.SeedingDandelionBlock;
import net.frozenblock.wilderwild.block.ShelfFungusBlock;
import net.frozenblock.wilderwild.block.StoneChestBlock;
import net.frozenblock.wilderwild.block.TermiteMoundBlock;
import net.frozenblock.wilderwild.block.WaterloggableSaplingBlock;
import net.frozenblock.wilderwild.block.WaterloggableTallFlowerBlock;
import net.frozenblock.wilderwild.block.WilderStandingSignBlock;
import net.frozenblock.wilderwild.block.WilderWallSignBlock;
import net.frozenblock.wilderwild.block.entity.TermiteMoundBlockEntity;
import net.frozenblock.wilderwild.item.AlgaeItem;
import net.frozenblock.wilderwild.item.FloweredLilyPadItem;
import net.frozenblock.wilderwild.util.FlowerColor;
import net.frozenblock.wilderwild.world.feature.generator.CypressSaplingGenerator;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.data.BlockFamilies;
import net.minecraft.data.BlockFamily;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.LevelEvent;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.TallFlowerBlock;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.WoodButtonBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = WilderWild.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class WWBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, WilderWild.MOD_ID);

    // OTHER(BUILDING BLOCKS)
    private static final MaterialColor BAOBAB_PLANKS_COLOR = MaterialColor.COLOR_ORANGE;
    private static final MaterialColor BAOBAB_BARK_COLOR = MaterialColor.COLOR_BROWN;
    private static final MaterialColor CYPRESS_PLANKS_COLOR = MaterialColor.COLOR_LIGHT_GRAY;
    private static final MaterialColor CYPRESS_BARK_COLOR = MaterialColor.STONE;

    // OTHER (BUILDING BLOCKS)
    public static final RegistryObject<Block> CHISELED_MUD_BRICKS = registerBlock("chiseled_mud_bricks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.CHISELED_STONE_BRICKS).strength(1.5F).requiresCorrectToolForDrops().sound(SoundType.MUD_BRICKS)), CreativeModeTab.TAB_DECORATIONS);

    // WOOD
    public static final RegistryObject<Block> BAOBAB_PLANKS = registerBlock("baobab_planks", () -> new Block(BlockBehaviour.Properties.of(Material.WOOD, BAOBAB_PLANKS_COLOR).strength(2.0F, 3.0F).sound(SoundType.WOOD)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> CYPRESS_PLANKS = registerBlock("cypress_planks", () -> new Block(BlockBehaviour.Properties.of(Material.WOOD, CYPRESS_PLANKS_COLOR).strength(2.0F, 3.0F).sound(SoundType.WOOD)), CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static final RegistryObject<Block> BAOBAB_LOG = registerBlock("baobab_log", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, state -> state.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? BAOBAB_PLANKS_COLOR : BAOBAB_BARK_COLOR).strength(2.0F).sound(SoundType.WOOD)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> CYPRESS_LOG = registerBlock("cypress_log", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, state -> state.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? CYPRESS_PLANKS_COLOR : CYPRESS_BARK_COLOR).strength(2.0F).sound(SoundType.WOOD)), CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static final RegistryObject<Block> STRIPPED_BAOBAB_LOG = registerBlock("stripped_baobab_log", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, state -> state.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? BAOBAB_PLANKS_COLOR : BAOBAB_BARK_COLOR).strength(2.0F).sound(SoundType.WOOD)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> STRIPPED_CYPRESS_LOG = registerBlock("stripped_cypress_log", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, state -> state.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? CYPRESS_PLANKS_COLOR : CYPRESS_BARK_COLOR).strength(2.0F).sound(SoundType.WOOD)), CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static final RegistryObject<Block> STRIPPED_BAOBAB_WOOD = registerBlock("stripped_baobab_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, state -> state.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? BAOBAB_PLANKS_COLOR : BAOBAB_BARK_COLOR).strength(2.0F).sound(SoundType.WOOD)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> STRIPPED_CYPRESS_WOOD = registerBlock("stripped_cypress_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, state -> state.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? CYPRESS_PLANKS_COLOR : CYPRESS_BARK_COLOR).strength(2.0F).sound(SoundType.WOOD)), CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static final RegistryObject<Block> BAOBAB_WOOD = registerBlock("baobab_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, state -> state.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? BAOBAB_PLANKS_COLOR : BAOBAB_BARK_COLOR).strength(2.0F).sound(SoundType.WOOD)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> CYPRESS_WOOD = registerBlock("cypress_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, state -> state.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? CYPRESS_PLANKS_COLOR : CYPRESS_BARK_COLOR).strength(2.0F).sound(SoundType.WOOD)), CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static final RegistryObject<Block> BAOBAB_SLAB = registerBlock("baobab_slab", () -> new SlabBlock(BlockBehaviour.Properties.of(Material.WOOD, BAOBAB_PLANKS_COLOR).strength(2.0F, 3.0F).sound(SoundType.WOOD)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> CYPRESS_SLAB = registerBlock("cypress_slab", () -> new SlabBlock(BlockBehaviour.Properties.of(Material.WOOD, CYPRESS_PLANKS_COLOR).strength(2.0F, 3.0F).sound(SoundType.WOOD)), CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static final RegistryObject<Block> BAOBAB_STAIRS = registerBlock("baobab_stairs", () -> new StairBlock(BAOBAB_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(BAOBAB_PLANKS.get())), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> CYPRESS_STAIRS = registerBlock("cypress_stairs", () -> new StairBlock(CYPRESS_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(CYPRESS_PLANKS.get())), CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static final RegistryObject<Block> BAOBAB_BUTTON = registerBlock("baobab_button", () -> new WoodButtonBlock(BlockBehaviour.Properties.copy(Blocks.OAK_BUTTON).color(BAOBAB_PLANKS_COLOR)), CreativeModeTab.TAB_REDSTONE);
    public static final RegistryObject<Block> CYPRESS_BUTTON = registerBlock("cypress_button", () -> new WoodButtonBlock(BlockBehaviour.Properties.copy(Blocks.OAK_BUTTON).color(CYPRESS_PLANKS_COLOR)), CreativeModeTab.TAB_REDSTONE);

    public static final RegistryObject<Block> BAOBAB_PRESSURE_PLATE = registerBlock("baobab_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.of(Material.WOOD, BAOBAB_PLANKS_COLOR).noCollission().strength(0.5F).sound(SoundType.WOOD)), CreativeModeTab.TAB_REDSTONE);
    public static final RegistryObject<Block> CYPRESS_PRESSURE_PLATE = registerBlock("cypress_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.of(Material.WOOD, CYPRESS_PLANKS_COLOR).noCollission().strength(0.5F).sound(SoundType.WOOD)), CreativeModeTab.TAB_REDSTONE);

    public static final RegistryObject<Block> BAOBAB_DOOR = registerBlock("baobab_door", () -> new DoorBlock(BlockBehaviour.Properties.of(Material.WOOD, BAOBAB_PLANKS_COLOR).strength(3.0F).sound(SoundType.WOOD).noOcclusion()), CreativeModeTab.TAB_REDSTONE);
    public static final RegistryObject<Block> CYPRESS_DOOR = registerBlock("cypress_door", () -> new DoorBlock(BlockBehaviour.Properties.of(Material.WOOD, CYPRESS_PLANKS_COLOR).strength(3.0F).sound(SoundType.WOOD).noOcclusion()), CreativeModeTab.TAB_REDSTONE);

    public static final RegistryObject<Block> BAOBAB_TRAPDOOR = registerBlock("baobab_trapdoor", () -> new TrapDoorBlock(BlockBehaviour.Properties.of(Material.WOOD, BAOBAB_PLANKS_COLOR).strength(3.0F).sound(SoundType.WOOD).noOcclusion().isValidSpawn(WWBlocks::never)), CreativeModeTab.TAB_REDSTONE);
    public static final RegistryObject<Block> CYPRESS_TRAPDOOR = registerBlock("cypress_trapdoor", () -> new TrapDoorBlock(BlockBehaviour.Properties.of(Material.WOOD, CYPRESS_PLANKS_COLOR).strength(3.0F).sound(SoundType.WOOD).noOcclusion().isValidSpawn(WWBlocks::never)), CreativeModeTab.TAB_REDSTONE);
    public static final RegistryObject<Block> BAOBAB_FENCE_GATE = registerBlock("baobab_fence_gate", () -> new FenceGateBlock(BlockBehaviour.Properties.of(Material.WOOD, BAOBAB_PLANKS.get().defaultMaterialColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD)), CreativeModeTab.TAB_REDSTONE);
    public static final RegistryObject<Block> CYPRESS_FENCE_GATE = registerBlock("cypress_fence_gate", () -> new FenceGateBlock(BlockBehaviour.Properties.of(Material.WOOD, CYPRESS_PLANKS.get().defaultMaterialColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD)), CreativeModeTab.TAB_REDSTONE);

    public static final RegistryObject<Block> BAOBAB_NUT = registerBlock("baobab_nut", () -> new BaobabNutBlock(BlockBehaviour.Properties.of(Material.PLANT).randomTicks().instabreak().sound(WWBlockSoundGroups.BAOBAB_NUT).dynamicShape().offsetType(BlockBehaviour.OffsetType.XZ)));
    public static final RegistryObject<Block> POTTED_BAOBAB_NUT = registerBlock("potted_cypress_nut", () -> new FlowerPotBlock(WWBlocks.BAOBAB_NUT.get(), BlockBehaviour.Properties.of(Material.DECORATION).instabreak().noOcclusion()));
    public static final RegistryObject<Block> CYPRESS_SAPLING = registerBlock("cypress_sapling", () -> new WaterloggableSaplingBlock(new CypressSaplingGenerator(), BlockBehaviour.Properties.copy(Blocks.BIRCH_SAPLING)), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> POTTED_CYPRESS_SAPLING = registerBlock("potted_cypress_sapling", () -> new FlowerPotBlock(WWBlocks.CYPRESS_SAPLING.get(), BlockBehaviour.Properties.of(Material.DECORATION).instabreak().noOcclusion()));

    public static final RegistryObject<Block> BAOBAB_LEAVES = registerBlock("baobab_leaves", () -> new BaobabLeavesBlock(BlockBehaviour.Properties.of(Material.LEAVES, MaterialColor.COLOR_GREEN).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion().isValidSpawn(WWBlocks::canSpawnOnLeaves).isSuffocating(WWBlocks::never).isViewBlocking(WWBlocks::never)), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> CYPRESS_LEAVES = registerBlock("cypress_leaves", () -> new LeavesBlock(BlockBehaviour.Properties.of(Material.LEAVES, MaterialColor.COLOR_GREEN).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion().isValidSpawn(WWBlocks::canSpawnOnLeaves).isSuffocating(WWBlocks::never).isViewBlocking(WWBlocks::never)), CreativeModeTab.TAB_DECORATIONS);

    public static final RegistryObject<Block> BAOBAB_FENCE = registerBlock("baobab_fence", () -> new FenceBlock(BlockBehaviour.Properties.of(Material.WOOD, BAOBAB_PLANKS_COLOR).strength(2.0F, 3.0F).sound(SoundType.WOOD)), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> CYPRESS_FENCE = registerBlock("cypress_fence", () -> new FenceBlock(BlockBehaviour.Properties.of(Material.WOOD, CYPRESS_PLANKS_COLOR).strength(2.0F, 3.0F).sound(SoundType.WOOD)), CreativeModeTab.TAB_DECORATIONS);

    public static final RegistryObject<Block> BAOBAB_SIGN_BLOCK = registerBlock("baobab_sign", () -> new WilderStandingSignBlock(BlockBehaviour.Properties.of(Material.WOOD, BAOBAB_LOG.get().defaultMaterialColor()).noCollission().strength(1.0F).sound(SoundType.WOOD), WWWoodTypes.BAOBAB));
    public static final RegistryObject<Block> BAOBAB_WALL_SIGN = registerBlock("baobab_wall_sign", () -> new WilderWallSignBlock(BlockBehaviour.Properties.of(Material.WOOD, BAOBAB_LOG.get().defaultMaterialColor()).noCollission().strength(1.0F).sound(SoundType.WOOD).dropsLike(BAOBAB_SIGN_BLOCK.get()), WWWoodTypes.BAOBAB));

    public static final RegistryObject<Block> CYPRESS_SIGN_BLOCK = registerBlock("cypress_sign", () -> new WilderStandingSignBlock(BlockBehaviour.Properties.of(Material.WOOD, CYPRESS_LOG.get().defaultMaterialColor()).noCollission().strength(1.0F).sound(SoundType.WOOD), WWWoodTypes.CYPRESS));
    public static final RegistryObject<Block> CYPRESS_WALL_SIGN = registerBlock("cypress_wall_sign", () -> new WilderWallSignBlock(BlockBehaviour.Properties.of(Material.WOOD, CYPRESS_LOG.get().defaultMaterialColor()).noCollission().strength(1.0F).sound(SoundType.WOOD).dropsLike(CYPRESS_SIGN_BLOCK.get()), WWWoodTypes.CYPRESS));

    public static final RegistryObject<Block> HOLLOWED_OAK_LOG = registerBlock("hollowed_oak_log", () -> createHollowedLogBlock(MaterialColor.WOOD, MaterialColor.PODZOL), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> HOLLOWED_SPRUCE_LOG = registerBlock("hollowed_spruce_log", () -> createHollowedLogBlock(MaterialColor.PODZOL, MaterialColor.COLOR_BROWN), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> HOLLOWED_BIRCH_LOG = registerBlock("hollowed_birch_log", () -> createHollowedLogBlock(MaterialColor.SAND, MaterialColor.QUARTZ), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> HOLLOWED_JUNGLE_LOG = registerBlock("hollowed_jungle_log", () -> createHollowedLogBlock(MaterialColor.DIRT, MaterialColor.PODZOL), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> HOLLOWED_ACACIA_LOG = registerBlock("hollowed_acacia_log", () -> createHollowedLogBlock(MaterialColor.COLOR_ORANGE, MaterialColor.STONE), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> HOLLOWED_DARK_OAK_LOG = registerBlock("hollowed_dark_oak_log", () -> createHollowedLogBlock(MaterialColor.COLOR_BROWN, MaterialColor.COLOR_BROWN), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> HOLLOWED_MANGROVE_LOG = registerBlock("hollowed_mangrove_log", () -> createHollowedLogBlock(MaterialColor.COLOR_RED, MaterialColor.PODZOL), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> HOLLOWED_BAOBAB_LOG = registerBlock("hollowed_baobab_log", () -> createHollowedLogBlock(MaterialColor.COLOR_ORANGE, MaterialColor.COLOR_BROWN), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> HOLLOWED_CYPRESS_LOG = registerBlock("hollowed_cypress_log", () -> createHollowedLogBlock(MaterialColor.COLOR_LIGHT_GRAY, MaterialColor.STONE), CreativeModeTab.TAB_DECORATIONS);

    // SCULK
    public static final RegistryObject<Block> SCULK_STAIRS = registerBlock("sculk_stairs", () -> new SculkStairsBlock(Blocks.SCULK.defaultBlockState(), BlockBehaviour.Properties.of(Material.SCULK).strength(0.2F).sound(SoundType.SCULK).dropsLike(Blocks.SCULK)), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> SCULK_SLAB = registerBlock("sculk_slab", () -> new SculkSlabBlock(BlockBehaviour.Properties.of(Material.SCULK).strength(0.2F).sound(SoundType.SCULK).dropsLike(Blocks.SCULK)), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> SCULK_WALL = registerBlock("sculk_wall", () -> new SculkWallBlock(BlockBehaviour.Properties.of(Material.SCULK).strength(0.2F).sound(SoundType.SCULK).dropsLike(Blocks.SCULK)), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> OSSEOUS_SCULK = registerBlock("osseous_sculk", () -> new OsseousSculkBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.SAND).requiresCorrectToolForDrops().strength(2.0F).sound(WWBlockSoundGroups.OSSEOUS_SCULK)), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> HANGING_TENDRIL = registerBlock("hanging_tendril", () -> new HangingTendrilBlock(BlockBehaviour.Properties.copy(Blocks.SCULK_SENSOR).strength(0.7F).noCollission().lightLevel((state) -> 1).sound(WWBlockSoundGroups.HANGING_TENDRIL).emissiveRendering((state, level, pos) -> HangingTendrilBlock.shouldHavePogLighting(state)), 4), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> ECHO_GLASS = registerBlock("echo_glass", () -> new EchoGlassBlock(BlockBehaviour.Properties.of(Material.GLASS, MaterialColor.COLOR_CYAN).strength(0.3F).noOcclusion().sound(WWBlockSoundGroups.ECHO_GLASS)), CreativeModeTab.TAB_DECORATIONS);

    private static boolean always(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) {
        return true;
    }

    public static final Material MESOGLEA_MATERIAL = new Material.Builder(MaterialColor.CLAY)
            .notSolidBlocking()
            .nonSolid()
            .destroyOnPush()
            .build();

    public static final Material NEMATOCYST_MATERIAL = new Material.Builder(MaterialColor.CLAY)
            .noCollider()
            .notSolidBlocking()
            .nonSolid()
            .destroyOnPush()
            .build();

    // Mesoglea
    public static final RegistryObject<Block> BLUE_PEARLESCENT_MESOGLEA = registerBlock("blue_pearlescent_mesoglea", () -> new MesogleaBlock(BlockBehaviour.Properties.of(MESOGLEA_MATERIAL, MaterialColor.QUARTZ).noOcclusion().strength(0.2F).friction(0.8F).emissiveRendering(WWBlocks::always).lightLevel((state) -> 7).sound(WWBlockSoundGroups.MESOGLEA).isSuffocating(WWBlocks::never).isViewBlocking(WWBlocks::never)), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> PURPLE_PEARLESCENT_MESOGLEA = registerBlock("purple_pearlescent_mesoglea", () -> new MesogleaBlock(BlockBehaviour.Properties.of(MESOGLEA_MATERIAL, MaterialColor.COLOR_PURPLE).noOcclusion().strength(0.2F).friction(0.8F).emissiveRendering(WWBlocks::always).lightLevel((state) -> 7).sound(WWBlockSoundGroups.MESOGLEA).isSuffocating(WWBlocks::never).isViewBlocking(WWBlocks::never)), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> YELLOW_MESOGLEA = registerBlock("yellow_mesoglea", () -> new MesogleaBlock(BlockBehaviour.Properties.of(MESOGLEA_MATERIAL, MaterialColor.COLOR_YELLOW).noOcclusion().strength(0.2F).friction(0.8F).emissiveRendering(WWBlocks::always).lightLevel((state) -> 7).sound(WWBlockSoundGroups.MESOGLEA).isSuffocating(WWBlocks::never).isViewBlocking(WWBlocks::never)), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> BLUE_MESOGLEA = registerBlock("blue_mesoglea", () -> new MesogleaBlock(BlockBehaviour.Properties.of(MESOGLEA_MATERIAL, MaterialColor.COLOR_LIGHT_BLUE).noOcclusion().strength(0.2F).friction(0.8F).emissiveRendering(WWBlocks::always).lightLevel((state) -> 7).sound(WWBlockSoundGroups.MESOGLEA).isSuffocating(WWBlocks::never).isViewBlocking(WWBlocks::never)), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> LIME_MESOGLEA = registerBlock("lime_mesoglea", () -> new MesogleaBlock(BlockBehaviour.Properties.of(MESOGLEA_MATERIAL, MaterialColor.COLOR_LIGHT_GREEN).noOcclusion().strength(0.2F).friction(0.8F).emissiveRendering(WWBlocks::always).lightLevel((state) -> 7).sound(WWBlockSoundGroups.MESOGLEA).isSuffocating(WWBlocks::never).isViewBlocking(WWBlocks::never)), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> RED_MESOGLEA = registerBlock("red_mesoglea", () -> new MesogleaBlock(BlockBehaviour.Properties.of(MESOGLEA_MATERIAL, MaterialColor.COLOR_RED).noOcclusion().strength(0.2F).friction(0.8F).emissiveRendering(WWBlocks::always).lightLevel((state) -> 7).sound(WWBlockSoundGroups.MESOGLEA).isSuffocating(WWBlocks::never).isViewBlocking(WWBlocks::never)), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> PINK_MESOGLEA = registerBlock("pink_mesoglea", () -> new MesogleaBlock(BlockBehaviour.Properties.of(MESOGLEA_MATERIAL, MaterialColor.COLOR_PINK).noOcclusion().strength(0.2F).friction(0.8F).emissiveRendering(WWBlocks::always).lightLevel((state) -> 7).sound(WWBlockSoundGroups.MESOGLEA).isSuffocating(WWBlocks::never).isViewBlocking(WWBlocks::never)), CreativeModeTab.TAB_DECORATIONS);

    public static final RegistryObject<Block> BLUE_PEARLESCENT_NEMATOCYST = registerBlock("blue_pearlescent_nematocyst", () -> new NematocystBlock(BlockBehaviour.Properties.of(NEMATOCYST_MATERIAL, MaterialColor.QUARTZ).noCollission().noOcclusion().emissiveRendering(WWBlocks::always).lightLevel((state) -> 4).sound(WWBlockSoundGroups.NEMATOCYST)), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> PURPLE_PEARLESCENT_NEMATOCYST = registerBlock("purple_pearlescent_nematocyst", () -> new NematocystBlock(BlockBehaviour.Properties.of(NEMATOCYST_MATERIAL, MaterialColor.COLOR_PURPLE).noCollission().noOcclusion().emissiveRendering(WWBlocks::always).lightLevel((state) -> 4).sound(WWBlockSoundGroups.NEMATOCYST)), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> YELLOW_NEMATOCYST = registerBlock("yellow_nematocyst", () -> new NematocystBlock(BlockBehaviour.Properties.of(NEMATOCYST_MATERIAL, MaterialColor.COLOR_YELLOW).noCollission().noOcclusion().emissiveRendering(WWBlocks::always).lightLevel((state) -> 4).sound(WWBlockSoundGroups.NEMATOCYST)), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> BLUE_NEMATOCYST = registerBlock("blue_nematocyst", () -> new NematocystBlock(BlockBehaviour.Properties.of(NEMATOCYST_MATERIAL, MaterialColor.COLOR_LIGHT_BLUE).noCollission().noOcclusion().emissiveRendering(WWBlocks::always).lightLevel((state) -> 4).sound(WWBlockSoundGroups.NEMATOCYST)), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> LIME_NEMATOCYST = registerBlock("lime_nematocyst", () -> new NematocystBlock(BlockBehaviour.Properties.of(NEMATOCYST_MATERIAL, MaterialColor.COLOR_LIGHT_GREEN).noCollission().noOcclusion().emissiveRendering(WWBlocks::always).lightLevel((state) -> 4).sound(WWBlockSoundGroups.NEMATOCYST)), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> RED_NEMATOCYST = registerBlock("red_nematocyst", () -> new NematocystBlock(BlockBehaviour.Properties.of(NEMATOCYST_MATERIAL, MaterialColor.COLOR_RED).noCollission().noOcclusion().emissiveRendering(WWBlocks::always).lightLevel((state) -> 4).sound(WWBlockSoundGroups.NEMATOCYST)), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> PINK_NEMATOCYST = registerBlock("pink_nematocyst", () -> new NematocystBlock(BlockBehaviour.Properties.of(NEMATOCYST_MATERIAL, MaterialColor.COLOR_PINK).noCollission().noOcclusion().emissiveRendering(WWBlocks::always).lightLevel((state) -> 4).sound(WWBlockSoundGroups.NEMATOCYST)), CreativeModeTab.TAB_DECORATIONS);

    // MISC
    private static final Material ALGAE_MATERIAL = new Material.Builder(MaterialColor.PLANT)
            .noCollider()
            .notSolidBlocking()
            .nonSolid()
            .destroyOnPush()
            .build();

    public static final RegistryObject<Block> TERMITE_MOUND = registerBlock("termite_mound", () -> new TermiteMoundBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_BROWN).strength(0.3F).sound(WWBlockSoundGroups.COARSEDIRT)), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> STONE_CHEST = registerBlock("stone_chest", () -> new StoneChestBlock(BlockBehaviour.Properties.copy(Blocks.CHEST).sound(SoundType.DEEPSLATE).strength(35.0F, 12.0F), WWBlockEntityTypes.STONE_CHEST::get), CreativeModeTab.TAB_DECORATIONS);

    // PLANTS
    public static final RegistryObject<Block> SEEDING_DANDELION = registerBlock("seeding_dandelion", () -> new SeedingDandelionBlock(MobEffects.SLOW_FALLING, 12, BlockBehaviour.Properties.copy(Blocks.DANDELION).sound(WWBlockSoundGroups.FLOWER).strength(0.0F).noOcclusion()), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> POTTED_SEEDING_DANDELION = registerBlock("potted_seeding_dandelion", () -> new FlowerPotBlock(WWBlocks.SEEDING_DANDELION.get(), BlockBehaviour.Properties.of(Material.DECORATION).instabreak().noOcclusion()));
    public static final RegistryObject<Block> CARNATION = registerBlock("carnation", () -> new FlowerBlock(MobEffects.REGENERATION, 12, BlockBehaviour.Properties.copy(Blocks.DANDELION).sound(WWBlockSoundGroups.FLOWER).strength(0.0F).noOcclusion()), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> POTTED_CARNATION = registerBlock("potted_carnation", () -> new FlowerPotBlock(WWBlocks.CARNATION.get(), BlockBehaviour.Properties.of(Material.DECORATION).instabreak().noOcclusion()));
    public static final RegistryObject<Block> GLORY_OF_THE_SNOW = registerBlock("glory_of_the_snow", () -> new GloryOfTheSnowBlock(BlockBehaviour.Properties.copy(Blocks.DANDELION).sound(WWBlockSoundGroups.FLOWER).strength(0.0F).noOcclusion().randomTicks(), List.of(FlowerColor.BLUE, FlowerColor.PINK, FlowerColor.PURPLE, FlowerColor.WHITE)), CreativeModeTab.TAB_DECORATIONS);

    public static final RegistryObject<Block> WHITE_GLORY_OF_THE_SNOW = registerBlock("alba_glory_of_the_snow", () -> new FlowerLichenBlock(BlockBehaviour.Properties.copy(Blocks.GRASS).noCollission().offsetType(BlockBehaviour.OffsetType.NONE).color(MaterialColor.QUARTZ).sound(SoundType.VINE)), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> PINK_GLORY_OF_THE_SNOW = registerBlock("pink_giant_glory_of_the_snow", () -> new FlowerLichenBlock(BlockBehaviour.Properties.copy(Blocks.GRASS).noCollission().offsetType(BlockBehaviour.OffsetType.NONE).color(MaterialColor.CRIMSON_STEM).sound(SoundType.VINE)), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> PURPLE_GLORY_OF_THE_SNOW = registerBlock("violet_beauty_glory_of_the_snow", () -> new FlowerLichenBlock(BlockBehaviour.Properties.copy(Blocks.GRASS).noCollission().offsetType(BlockBehaviour.OffsetType.NONE).color(MaterialColor.COLOR_PURPLE).sound(SoundType.VINE)), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> BLUE_GLORY_OF_THE_SNOW = registerBlock("blue_giant_glory_of_the_snow", () -> new FlowerLichenBlock(BlockBehaviour.Properties.copy(Blocks.GRASS).noCollission().offsetType(BlockBehaviour.OffsetType.NONE).color(MaterialColor.COLOR_BLUE).sound(SoundType.VINE)), CreativeModeTab.TAB_DECORATIONS);

    public static final RegistryObject<Block> DATURA = registerBlock("datura", () -> new TallFlowerBlock(BlockBehaviour.Properties.of(Material.REPLACEABLE_PLANT).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ)), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> MILKWEED = registerBlock("milkweed", () -> new MilkweedBlock(BlockBehaviour.Properties.of(Material.REPLACEABLE_PLANT).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ)), CreativeModeTab.TAB_DECORATIONS);

    public static final RegistryObject<Block> CATTAIL = registerBlock("cattail", () -> new WaterloggableTallFlowerBlock(BlockBehaviour.Properties.copy(Blocks.ROSE_BUSH).sound(SoundType.WET_GRASS).strength(0.0F).noOcclusion()), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> FLOWERING_LILY_PAD = registerBlock("flowering_lily_pad", () -> new FloweringLilyPadBlock(BlockBehaviour.Properties.copy(Blocks.LILY_PAD).sound(WWBlockSoundGroups.LILYPAD)));
    public static final RegistryObject<Block> ALGAE = registerBlock("algae", () -> new AlgaeBlock(BlockBehaviour.Properties.of(ALGAE_MATERIAL).instabreak().noOcclusion().noCollission().sound(SoundType.SLIME_BLOCK)));

    public static final RegistryObject<Block> POTTED_BIG_DRIPLEAF = registerBlock("potted_big_dripleaf", () -> new FlowerPotBlock(Blocks.BIG_DRIPLEAF, BlockBehaviour.Properties.of(Material.DECORATION).instabreak().noOcclusion()));
    public static final RegistryObject<Block> POTTED_SMALL_DRIPLEAF = registerBlock("potted_small_dripleaf", () -> new FlowerPotBlock(Blocks.SMALL_DRIPLEAF, BlockBehaviour.Properties.of(Material.DECORATION).instabreak().noOcclusion()));
    public static final RegistryObject<Block> POTTED_GRASS = registerBlock("potted_grass", () -> new FlowerPotBlock(Blocks.GRASS, BlockBehaviour.Properties.of(Material.DECORATION).instabreak().noOcclusion()));

    public static final RegistryObject<Block> BROWN_SHELF_FUNGUS = registerBlock("brown_shelf_fungus", () -> new ShelfFungusBlock(BlockBehaviour.Properties.copy(Blocks.BROWN_MUSHROOM_BLOCK).lightLevel(state -> 1).noCollission().noOcclusion().sound(WWBlockSoundGroups.MUSHROOM)), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> RED_SHELF_FUNGUS = registerBlock("red_shelf_fungus", () -> new ShelfFungusBlock(BlockBehaviour.Properties.copy(Blocks.RED_MUSHROOM_BLOCK).noCollission().noOcclusion().sound(WWBlockSoundGroups.MUSHROOM)), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> POLLEN_BLOCK = registerBlock("pollen", () -> new PollenBlock(BlockBehaviour.Properties.copy(Blocks.GRASS).noCollission().offsetType(BlockBehaviour.OffsetType.NONE).color(MaterialColor.SAND).sound(SoundType.VINE)));
    public static final RegistryObject<Block> NULL_BLOCK = registerBlock("null_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).sound(WWBlockSoundGroups.NULL_BLOCK)), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> DISPLAY_LANTERN = registerBlock("display_lantern", () -> new DisplayLanternBlock(BlockBehaviour.Properties.of(Material.METAL).strength(3.5F).sound(SoundType.LANTERN).lightLevel((state) -> state.getValue(WWBlockStateProperties.DISPLAY_LIGHT))), CreativeModeTab.TAB_DECORATIONS);

    private static HollowedLogBlock createHollowedLogBlock(MaterialColor topMapColor, MaterialColor sideMapColor) {
        return new HollowedLogBlock(BlockBehaviour.Properties.of(Material.WOOD,
                        (state) -> state.getValue(HollowedLogBlock.AXIS) == Direction.Axis.Y ? topMapColor : sideMapColor)
                .strength(2.0F).sound(WWBlockSoundGroups.HOLLOWED_LOG));
    }

    private static boolean never(BlockState state, BlockGetter level, BlockPos pos) {
        return false;
    }

    private static boolean never(BlockState state, BlockGetter blockView, BlockPos blockPos, EntityType<?> entityType) {
        return false;
    }

    private static Boolean canSpawnOnLeaves(BlockState state, BlockGetter level, BlockPos pos, EntityType<?> type) {
        return type == EntityType.OCELOT || type == EntityType.PARROT;
    }

    public static <B extends Block> RegistryObject<B> registerBlock(String id, Supplier<B> supplier, CreativeModeTab creativeModeTab) {
        RegistryObject<B> block = BLOCKS.register(id, supplier);
        WWItems.ITEMS.register(id, () -> new BlockItem(block.get(), new Item.Properties().tab(creativeModeTab)));
        return block;
    }

    public static <B extends Block> RegistryObject<B> registerBlock(String id, Supplier<B> supplier) {
        return BLOCKS.register(id, supplier);
    }

}

