package net.frozenblock.wilderwild.datagen;

import com.google.common.collect.Maps;
import com.mojang.serialization.JsonOps;
import net.frozenblock.wilderwild.WilderWild;
import net.frozenblock.wilderwild.init.WWBiomeTags;
import net.frozenblock.wilderwild.world.feature.WilderPlacedFeatures;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.RegistryOps;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.JsonCodecProvider;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WilderWildBiomeModifierProvider {
    private static final RegistryAccess ACCESS = RegistryAccess.builtinCopy();
    private static final Registry<Biome> BIOME_REGISTRY = ACCESS.registryOrThrow(Registry.BIOME_REGISTRY);
    private static final Registry<PlacedFeature> PLACED_FEATURES = ACCESS.registryOrThrow(Registry.PLACED_FEATURE_REGISTRY);
    private static final HashMap<ResourceLocation, BiomeModifier> MODIFIERS = Maps.newHashMap();

    public static JsonCodecProvider<BiomeModifier> bootstrap(DataGenerator dataGenerator, ExistingFileHelper existingFileHelper) {
        addFeature("add_brown_mushrooms", WWBiomeTags.HAS_BROWN_MUSHROOMS, GenerationStep.Decoration.VEGETAL_DECORATION, WilderPlacedFeatures.BROWN_MUSHROOM_PLACED);
        return JsonCodecProvider.forDatapackRegistry(dataGenerator, existingFileHelper, WilderWild.MOD_ID, RegistryOps.create(JsonOps.INSTANCE, ACCESS), ForgeRegistries.Keys.BIOME_MODIFIERS, MODIFIERS);
    }

    private static void addFeature(String name, TagKey<Biome> tagKey, GenerationStep.Decoration step, Holder<PlacedFeature> features) {
        MODIFIERS.put(new ResourceLocation(WilderWild.MOD_ID, name), new ForgeBiomeModifiers.AddFeaturesBiomeModifier(new HolderSet.Named<>(BIOME_REGISTRY, tagKey), featureSet(features), step));
    }

    private static HolderSet<PlacedFeature> featureSet(Holder<PlacedFeature>... features) {
        return HolderSet.direct(Stream.of(features).map(holder -> PLACED_FEATURES.getOrCreateHolderOrThrow(holder.unwrapKey().get())).collect(Collectors.toList()));
    }

}
