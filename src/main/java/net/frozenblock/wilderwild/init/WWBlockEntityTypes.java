package net.frozenblock.wilderwild.init;

import net.frozenblock.wilderwild.WilderWild;
import net.frozenblock.wilderwild.block.entity.DisplayLanternBlockEntity;
import net.frozenblock.wilderwild.block.entity.HangingTendrilBlockEntity;
import net.frozenblock.wilderwild.block.entity.StoneChestBlockEntity;
import net.frozenblock.wilderwild.block.entity.TermiteMoundBlockEntity;
import net.frozenblock.wilderwild.block.entity.WilderSignBlockEntity;
import net.minecraft.core.Registry;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = WilderWild.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class WWBlockEntityTypes {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, WilderWild.MOD_ID);

    public static final RegistryObject<BlockEntityType<WilderSignBlockEntity>> BAOBAB_SIGN_BLOCK_ENTITIES = BLOCK_ENTITIES.register("baobab_sign_block_entity", () -> BlockEntityType.Builder.of(WilderSignBlockEntity::new, WWBlocks.BAOBAB_WALL_SIGN.get(), WWBlocks.BAOBAB_SIGN_BLOCK.get(), WWBlocks.CYPRESS_WALL_SIGN.get(), WWBlocks.CYPRESS_SIGN_BLOCK.get()).build(null));
    public static final RegistryObject<BlockEntityType<HangingTendrilBlockEntity>> HANGING_TENDRIL = BLOCK_ENTITIES.register("hanging_tendril", () -> BlockEntityType.Builder.of(HangingTendrilBlockEntity::new, WWBlocks.HANGING_TENDRIL.get()).build(null));
    public static final RegistryObject<BlockEntityType<TermiteMoundBlockEntity>> TERMITE_MOUND = BLOCK_ENTITIES.register("termite_mound", () -> BlockEntityType.Builder.of(TermiteMoundBlockEntity::new, WWBlocks.TERMITE_MOUND.get()).build(null));
    public static final RegistryObject<BlockEntityType<DisplayLanternBlockEntity>> DISPLAY_LANTERN = BLOCK_ENTITIES.register("display_lantern", () -> BlockEntityType.Builder.of(DisplayLanternBlockEntity::new, WWBlocks.DISPLAY_LANTERN.get()).build(null));
    public static final RegistryObject<BlockEntityType<StoneChestBlockEntity>> STONE_CHEST = BLOCK_ENTITIES.register("stone_chest", () -> BlockEntityType.Builder.of(StoneChestBlockEntity::new, WWBlocks.STONE_CHEST.get()).build(null));

}