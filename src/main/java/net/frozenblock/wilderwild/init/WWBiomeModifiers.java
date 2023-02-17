package net.frozenblock.wilderwild.init;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.frozenblock.wilderwild.WilderWild;
import net.frozenblock.wilderwild.world.feature.biomemodifier.AddFeaturesWithFilterBiomeModifier;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = WilderWild.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class WWBiomeModifiers {

    public static final DeferredRegister<Codec<? extends BiomeModifier>> BIOME_MODIFIERS = DeferredRegister.create(ForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, WilderWild.MOD_ID);

    public static final RegistryObject<Codec<AddFeaturesWithFilterBiomeModifier>> ADD_FEATURES_WITH_FILTER = BIOME_MODIFIERS.register("add_features_with_filter", () ->
            RecordCodecBuilder.create(builder -> builder.group(
                    Biome.LIST_CODEC.fieldOf("biomes").forGetter(AddFeaturesWithFilterBiomeModifier::biomes),
                    Biome.LIST_CODEC.fieldOf("filtered").forGetter(AddFeaturesWithFilterBiomeModifier::filtered),
                    PlacedFeature.LIST_CODEC.fieldOf("features").forGetter(AddFeaturesWithFilterBiomeModifier::features),
                    GenerationStep.Decoration.CODEC.fieldOf("step").forGetter(AddFeaturesWithFilterBiomeModifier::step)
            ).apply(builder, AddFeaturesWithFilterBiomeModifier::new))
    );

}
