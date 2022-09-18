package net.frozenblock.wilderwild.registry;

import net.frozenblock.wilderwild.WilderWild;
import net.frozenblock.wilderwild.block.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.grower.OakTreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class RegisterBlocks {

    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, WilderWild.MOD_ID);
    // OTHER(BUILDING BLOCKS)
    public static final RegistryObject<Block> CHISELED_MUD_BRICKS = registerBlock("chiseled_mud_bricks", () ->
            new Block(BlockBehaviour.Properties.copy(Blocks.CHISELED_STONE_BRICKS)
                    .strength(1.5f).requiresCorrectToolForDrops().sound(SoundType.MUD_BRICKS)), CreativeModeTab.TAB_BUILDING_BLOCKS);

    //WOOD
    private static final MaterialColor BAOBAB_PLANKS_COLOR = MaterialColor.COLOR_ORANGE;
    private static final MaterialColor BAOBAB_BARK_COLOR = MaterialColor.COLOR_BROWN;
    private static final MaterialColor CYPRESS_PLANKS_COLOR = MaterialColor.COLOR_LIGHT_GRAY;
    private static final MaterialColor CYPRESS_BARK_COLOR = MaterialColor.STONE;

    public static final RegistryObject<Block> BAOBAB_PLANKS = registerBlock("baobab_planks", () ->
            new Block(BlockBehaviour.Properties.of(Material.WOOD, BAOBAB_PLANKS_COLOR).strength(2.0F, 3.0F).sound(SoundType.WOOD)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> CYPRESS_PLANKS = registerBlock("cypress_planks", () ->
            new Block(BlockBehaviour.Properties.of(Material.WOOD, CYPRESS_PLANKS_COLOR).strength(2.0F, 3.0F).sound(SoundType.WOOD)), CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static final RegistryObject<Block> BAOBAB_LOG = registerBlock("baobab_log", () ->
            new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, state -> state.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? BAOBAB_PLANKS_COLOR : BAOBAB_BARK_COLOR).strength(2.0F).sound(SoundType.WOOD)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> CYPRESS_LOG = registerBlock("cypress_log", () ->
            new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, state -> state.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? CYPRESS_PLANKS_COLOR : CYPRESS_BARK_COLOR).strength(2.0F).sound(SoundType.WOOD)), CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static final RegistryObject<Block> STRIPPED_BAOBAB_LOG = registerBlock("stripped_baobab_log", () ->
            new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, state -> state.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? BAOBAB_PLANKS_COLOR : BAOBAB_BARK_COLOR).strength(2.0F).sound(SoundType.WOOD)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> STRIPPED_CYPRESS_LOG = registerBlock("stripped_cypress_log", () ->
            new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, state -> state.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? CYPRESS_PLANKS_COLOR : CYPRESS_BARK_COLOR).strength(2.0F).sound(SoundType.WOOD)), CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static final RegistryObject<Block> STRIPPED_BAOBAB_WOOD = registerBlock("stripped_baobab_wood", () ->
            new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, state -> state.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? BAOBAB_PLANKS_COLOR : BAOBAB_BARK_COLOR).strength(2.0F).sound(SoundType.WOOD)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> STRIPPED_CYPRESS_WOOD = registerBlock("stripped_cypress_wood", () ->
            new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, state -> state.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? CYPRESS_PLANKS_COLOR : CYPRESS_BARK_COLOR).strength(2.0F).sound(SoundType.WOOD)), CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static final RegistryObject<Block> BAOBAB_WOOD = registerBlock("baobab_wood", () ->
            new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, state -> state.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? BAOBAB_PLANKS_COLOR : BAOBAB_BARK_COLOR).strength(2.0F).sound(SoundType.WOOD)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> CYPRESS_WOOD = registerBlock("cypress_wood", () ->
            new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, state -> state.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? CYPRESS_PLANKS_COLOR : CYPRESS_BARK_COLOR).strength(2.0F).sound(SoundType.WOOD)), CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static final RegistryObject<Block> BAOBAB_SLAB = registerBlock("baobab_slab", () ->
            new SlabBlock(BlockBehaviour.Properties.of(Material.WOOD, BAOBAB_PLANKS_COLOR).strength(2.0F, 3.0F).sound(SoundType.WOOD)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> CYPRESS_SLAB = registerBlock("cypress_slab", () ->
            new SlabBlock(BlockBehaviour.Properties.of(Material.WOOD, CYPRESS_PLANKS_COLOR).strength(2.0F, 3.0F).sound(SoundType.WOOD)), CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static final RegistryObject<Block> BAOBAB_STAIRS = registerBlock("baobab_stairs", () ->
                    new StairBlock(() -> RegisterBlocks.BAOBAB_PLANKS.get().defaultBlockState(),
                            BlockBehaviour.Properties.of(Material.WOOD, BAOBAB_PLANKS_COLOR).strength(2.0F, 3.0F).sound(SoundType.WOOD)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> CYPRESS_STAIRS = registerBlock("cypress_stairs", () ->
            new StairBlock(() -> RegisterBlocks.CYPRESS_PLANKS.get().defaultBlockState(),
                    BlockBehaviour.Properties.of(Material.WOOD, CYPRESS_PLANKS_COLOR).strength(2.0F, 3.0F).sound(SoundType.WOOD)), CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static final RegistryObject<Block> BAOBAB_BUTTON = registerBlock("baobab_button",
            () -> new WoodButtonBlock(BlockBehaviour.Properties.copy(Blocks.OAK_BUTTON).color(BAOBAB_PLANKS_COLOR).noCollission()), CreativeModeTab.TAB_REDSTONE);
    public static final RegistryObject<Block> CYPRESS_BUTTON = registerBlock("cypress_button",
            () -> new WoodButtonBlock(BlockBehaviour.Properties.copy(Blocks.OAK_BUTTON).color(CYPRESS_PLANKS_COLOR).noCollission()), CreativeModeTab.TAB_REDSTONE);

    public static final RegistryObject<Block> BAOBAB_PRESSURE_PLATE = registerBlock("baobab_pressure_plate",
            () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.of(Material.WOOD, BAOBAB_PLANKS_COLOR)
                    .noCollission().strength(0.5F).sound(SoundType.WOOD)), CreativeModeTab.TAB_REDSTONE);
    public static final RegistryObject<Block> CYPRESS_PRESSURE_PLATE = registerBlock("cypress_pressure_plate",
            () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.of(Material.WOOD, CYPRESS_PLANKS_COLOR)
                    .noCollission().strength(0.5F).sound(SoundType.WOOD)), CreativeModeTab.TAB_REDSTONE);

    public static final RegistryObject<Block> BAOBAB_DOOR = registerBlock("baobab_door",
            () -> new DoorBlock(BlockBehaviour.Properties.of(Material.WOOD, BAOBAB_PLANKS_COLOR)
                    .strength(3f).noOcclusion()), CreativeModeTab.TAB_REDSTONE);
    public static final RegistryObject<Block> CYPRESS_DOOR = registerBlock("cypress_door",
            () -> new DoorBlock(BlockBehaviour.Properties.of(Material.WOOD, CYPRESS_PLANKS_COLOR)
                    .strength(3f).noOcclusion()), CreativeModeTab.TAB_REDSTONE);

    public static final RegistryObject<Block> BAOBAB_TRAPDOOR = registerBlock("baobab_trapdoor",
            () -> new TrapDoorBlock(BlockBehaviour.Properties.of(Material.WOOD, BAOBAB_PLANKS_COLOR)
                    .strength(3f).requiresCorrectToolForDrops().noOcclusion()), CreativeModeTab.TAB_REDSTONE);
    public static final RegistryObject<Block> CYPRESS_TRAPDOOR = registerBlock("cypress_trapdoor",
            () -> new TrapDoorBlock(BlockBehaviour.Properties.of(Material.WOOD, CYPRESS_PLANKS_COLOR)
                    .strength(3f).requiresCorrectToolForDrops().noOcclusion()), CreativeModeTab.TAB_REDSTONE);

    public static final RegistryObject<Block> BAOBAB_FENCE_GATE = registerBlock("baobab_fence_gate",
            () -> new FenceGateBlock(BlockBehaviour.Properties.of(Material.WOOD, BAOBAB_PLANKS.get().defaultMaterialColor())
                    .strength(2.0F, 3.0F)), CreativeModeTab.TAB_REDSTONE);
    public static final RegistryObject<Block> CYPRESS_FENCE_GATE = registerBlock("cypress_fence_gate",
            () -> new FenceGateBlock(BlockBehaviour.Properties.of(Material.WOOD, CYPRESS_PLANKS.get().defaultMaterialColor())
                    .strength(2.0F, 3.0F)), CreativeModeTab.TAB_REDSTONE);

    public static final RegistryObject<Block> BAOBAB_NUT = registerBlock("baobab_nut",
            () -> new BaobabNutBlock(BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak()), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> POTTED_BAOBAB_NUT = registerBlockWithoutBlockItem("potted_baobab_nut",
            () -> new FlowerPotBlock(null, RegisterBlocks.BAOBAB_NUT,
                    BlockBehaviour.Properties.copy(Blocks.POTTED_DANDELION).noOcclusion()));

    public static final RegistryObject<Block> CYPRESS_SAPLING = registerBlock("cypress_sapling",
            () -> new WaterloggableSaplingBlock(new OakTreeGrower(), BlockBehaviour.Properties.copy(Blocks.BIRCH_SAPLING)), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> POTTED_CYPRESS_SAPLING = registerBlockWithoutBlockItem("potted_cypress_sapling",
            () -> new FlowerPotBlock(null, RegisterBlocks.CYPRESS_SAPLING,
                    BlockBehaviour.Properties.copy(Blocks.POTTED_DANDELION).noOcclusion()));

    public static final RegistryObject<Block> BAOBAB_LEAVES = registerBlock("baobab_leaves",
            () -> new BaobabLeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)) {
                @Override
                public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
                    return 60;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
                    return 30;
                }
            }, CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> CYPRESS_LEAVES = registerBlock("cypress_leaves",
            () -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)) {
                @Override
                public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
                    return 60;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
                    return 30;
                }
            }, CreativeModeTab.TAB_DECORATIONS);





                    //FLOWERS AND PLANTS
    public static final RegistryObject<Block> CARNATION = registerBlock("carnation",
            () -> new FlowerBlock(MobEffects.REGENERATION,12, BlockBehaviour.Properties.copy(Blocks.DANDELION)
                    .strength(0.0f).sound(SoundType.GRASS)), CreativeModeTab.TAB_DECORATIONS);

    public static final RegistryObject<Block> POTTED_CARNATION = registerBlockWithoutBlockItem("potted_carnation",
            () -> new FlowerPotBlock(null, RegisterBlocks.CARNATION,
                    BlockBehaviour.Properties.copy(Blocks.POTTED_DANDELION).noOcclusion()));


    public static final RegistryObject<Block> CATTAIL = registerBlock("cattail",
            () -> new WaterloggableTallFlowerBlock(BlockBehaviour.Properties.copy(Blocks.ROSE_BUSH).sound(SoundType.WET_GRASS).strength(0.0F)), CreativeModeTab.TAB_DECORATIONS);

    public static final RegistryObject<Block> POLLEN_BLOCK = registerBlockWithoutBlockItem("pollen",
            () -> new PollenBlock(BlockBehaviour.Properties.copy(Blocks.GRASS).noCollission().offsetType(BlockBehaviour.OffsetType.NONE).color(MaterialColor.SAND).sound(SoundType.VINE)));


    private static boolean never(BlockState state, BlockGetter world, BlockPos pos) {
        return false;
    }

    private static boolean never(BlockState state, BlockGetter blockView, BlockPos blockPos, EntityType<?> entityType) {
        return false;
    }

    private static Boolean canSpawnOnLeaves(BlockState state, BlockGetter world, BlockPos pos, EntityType<?> type) {
        return type == EntityType.OCELOT || type == EntityType.PARROT;
    }
    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);
        return toReturn;
    }
    private static <T extends Block> RegistryObject<T> registerBlockWithoutBlockItem(String name, Supplier<T> block) {
        return BLOCKS.register(name, block);
    }
    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block,
                                                                            CreativeModeTab tab) {
        return RegisterItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}

