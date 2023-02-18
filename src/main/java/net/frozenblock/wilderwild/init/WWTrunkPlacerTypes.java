package net.frozenblock.wilderwild.init;

import net.frozenblock.wilderwild.WilderWild;
import net.frozenblock.wilderwild.world.feature.trunk.BaobabTrunkPlacer;
import net.frozenblock.wilderwild.world.feature.trunk.FallenTrunkWithLogs;
import net.frozenblock.wilderwild.world.gen.trunk.StraightTrunkWithLogs;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = WilderWild.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class WWTrunkPlacerTypes {

    public static final DeferredRegister<TrunkPlacerType<?>> TRUNK_PLACER_TYPES = DeferredRegister.create(Registry.TRUNK_PLACER_TYPE_REGISTRY, WilderWild.MOD_ID);

    public static final RegistryObject<TrunkPlacerType<FallenTrunkWithLogs>> FALLEN_TRUNK_WITH_LOGS_PLACER_TYPE = TRUNK_PLACER_TYPES.register("fallen_trunk_logs_placer", () -> new TrunkPlacerType<>(FallenTrunkWithLogs.CODEC));
    public static final RegistryObject<TrunkPlacerType<BaobabTrunkPlacer>> BAOBAB_TRUNK_PLACER = TRUNK_PLACER_TYPES.register("baobab_trunk_placer", () -> new TrunkPlacerType<>(BaobabTrunkPlacer.CODEC));
    public static final RegistryObject<TrunkPlacerType<StraightTrunkWithLogs>> STRAIGHT_TRUNK_WITH_LOGS_PLACER_TYPE = TRUNK_PLACER_TYPES.register("straight_trunk_logs_placer", () -> new TrunkPlacerType<>(StraightTrunkWithLogs.CODEC));

}
