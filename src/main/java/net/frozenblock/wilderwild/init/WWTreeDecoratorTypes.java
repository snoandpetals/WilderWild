package net.frozenblock.wilderwild.init;

import net.frozenblock.wilderwild.WilderWild;
import net.frozenblock.wilderwild.world.feature.decorators.HeightBasedVineTrunkDecorator;
import net.frozenblock.wilderwild.world.feature.decorators.ShelfFungusTreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = WilderWild.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class WWTreeDecoratorTypes {

    public static final DeferredRegister<TreeDecoratorType<?>> TREE_DECORATOR_TYPES = DeferredRegister.create(ForgeRegistries.TREE_DECORATOR_TYPES, WilderWild.MOD_ID);

    public static final RegistryObject<TreeDecoratorType<ShelfFungusTreeDecorator>> FUNGUS_TREE_DECORATOR = TREE_DECORATOR_TYPES.register("shelf_fungus_tree_decorator", () -> new TreeDecoratorType<>(ShelfFungusTreeDecorator.CODEC));
    public static final RegistryObject<TreeDecoratorType<HeightBasedVineTrunkDecorator>> HEIGHT_BASED_VINE_TRUNK_DECORATOR = TREE_DECORATOR_TYPES.register("height_based_vine_trunk_decorator", () -> new TreeDecoratorType<>(HeightBasedVineTrunkDecorator.CODEC));

}
