package net.frozenblock.wilderwild.registry;

import net.frozenblock.wilderwild.WilderWild;
import net.frozenblock.wilderwild.block.entity.WilderSignBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = WilderWild.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class RegisterBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, WilderWild.MOD_ID);

    public static final RegistryObject<BlockEntityType<WilderSignBlockEntity>> BAOBAB_SIGN_BLOCK_ENTITIES =
            BLOCK_ENTITIES.register("baobab_sign_block_entity", () ->
                    BlockEntityType.Builder.of(WilderSignBlockEntity::new,
                            RegisterBlocks.BAOBAB_WALL_SIGN.get(),
                            RegisterBlocks.BAOBAB_SIGN_BLOCK.get()).build(null));

}