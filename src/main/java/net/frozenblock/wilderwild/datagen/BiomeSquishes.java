package net.frozenblock.wilderwild.datagen;

import dev.lukebemish.biomesquisher.BiomeSquisherRegistries;
import dev.lukebemish.biomesquisher.DimensionBehaviour;
import dev.lukebemish.biomesquisher.Injection;
import dev.lukebemish.biomesquisher.Relative;
import dev.lukebemish.biomesquisher.Series;
import dev.lukebemish.biomesquisher.Squisher;
import net.frozenblock.wilderwild.misc.WilderSharedConstants;
import net.frozenblock.wilderwild.world.biome.CypressWetlands;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.OverworldBiomeBuilder;
import net.minecraft.world.level.dimension.LevelStem;
import org.jetbrains.annotations.NotNull;
import java.util.List;
import java.util.Set;

public class BiomeSquishes {

	@SuppressWarnings("unchecked")
	public static void bootstrapSeries(@NotNull BootstapContext<Series> ctx) {
		ResourceKey<Registry<Series>> registryKey = BiomeSquisherRegistries.SERIES;
		ctx.register(
			ResourceKey.create(registryKey, WilderSharedConstants.id(WilderSharedConstants.MOD_ID)),
			new Series(
				(List) ((RegistrySetBuilder.UniversalLookup) (Object) ctx.lookup(BiomeSquisherRegistries.SQUISHER))
					.holders
					.keySet()
					.stream()
					.filter(key -> {
						var reg = key.registry();
						return reg.equals(BiomeSquisherRegistries.SQUISHER.location()) && key.location().getNamespace().equals(WilderSharedConstants.MOD_ID);
					})
					.toList(),
				Set.of(
					LevelStem.OVERWORLD
				)
			)
		);
	}

	public static void bootstrapSquishers(@NotNull BootstapContext<Squisher> ctx) {
		register(
			ctx,
			Set.of(
				Slice.VALLEY,
				Slice.LOW,
				Slice.MID
			),
			CypressWetlands.INSTANCE.getKey(),
			0.5,
			Relative.DEFAULT,
			true,
			new DimensionBehaviour.Squish(
				0.5,
				1
			),
			new DimensionBehaviour.Squish(
				0.5,
				1
			),
			new DimensionBehaviour.Range(
				-0.2,
				0.5
			),
			new DimensionBehaviour.Range(
				0.5,
				1.0
			),
			new DimensionBehaviour.Range(
				-1.0,
				1.0
			)
		);
	}

	private static void register(
		@NotNull BootstapContext<Squisher> ctx,
		Set<Slice> slices,
		ResourceKey<Biome> biome,
		double radius,
		Relative relative,
		boolean snap,
		DimensionBehaviour.Squish temperature,
		DimensionBehaviour.Squish humidity,
		DimensionBehaviour.Range continentalness,
		DimensionBehaviour.Range erosion,
		DimensionBehaviour.Range depth
	) {
		int i = 0;
		for (Slice slice : slices) {
			i += slice.register(
				ctx,
				biome,
				radius,
				relative,
				snap,
				temperature,
				humidity,
				continentalness,
				erosion,
				depth,
				i
			);
		}
	}

	private enum Slice {
		VALLEY(List.of(
			new DimensionBehaviour.Range(
				-OverworldBiomeBuilder.VALLEY_SIZE,
				OverworldBiomeBuilder.VALLEY_SIZE
			)
		)),
		LOW(List.of(
			new DimensionBehaviour.Range(
				-OverworldBiomeBuilder.LOW_START,
				-OverworldBiomeBuilder.VALLEY_SIZE
			),
			new DimensionBehaviour.Range(
				OverworldBiomeBuilder.VALLEY_SIZE,
				OverworldBiomeBuilder.LOW_START
			)
		)),
		MID(List.of(
			new DimensionBehaviour.Range(
				-1.0,
				-OverworldBiomeBuilder.HIGH_END
			),
			new DimensionBehaviour.Range(
				-OverworldBiomeBuilder.HIGH_START,
				-OverworldBiomeBuilder.LOW_START
			),
			new DimensionBehaviour.Range(
				OverworldBiomeBuilder.LOW_START,
				OverworldBiomeBuilder.HIGH_START
			),
			new DimensionBehaviour.Range(
				OverworldBiomeBuilder.HIGH_END,
				1.0
			)
		)),
		HIGH(List.of(
			new DimensionBehaviour.Range(
				-OverworldBiomeBuilder.HIGH_END,
				-OverworldBiomeBuilder.PEAK_END
			),
			new DimensionBehaviour.Range(
				-OverworldBiomeBuilder.PEAK_START,
				-OverworldBiomeBuilder.HIGH_START
			),
			new DimensionBehaviour.Range(
				OverworldBiomeBuilder.HIGH_START,
				OverworldBiomeBuilder.PEAK_START
			),
			new DimensionBehaviour.Range(
				OverworldBiomeBuilder.PEAK_END,
				OverworldBiomeBuilder.HIGH_END
			)
		)),
		PEAK(List.of(
			new DimensionBehaviour.Range(
				-OverworldBiomeBuilder.PEAK_END,
				-OverworldBiomeBuilder.PEAK_START
			),
			new DimensionBehaviour.Range(
				OverworldBiomeBuilder.PEAK_START,
				OverworldBiomeBuilder.PEAK_END
			)
		));

		public final List<DimensionBehaviour.Range> weirdnesses;

		Slice(List<DimensionBehaviour.Range> weirdnesses) {
			this.weirdnesses = weirdnesses;
		}

		int register(
			@NotNull BootstapContext<Squisher> ctx,
			ResourceKey<Biome> biome,
			double radius,
			Relative relative,
			boolean snap,
			DimensionBehaviour.Squish temperature,
			DimensionBehaviour.Squish humidity,
			DimensionBehaviour.Range continentalness,
			DimensionBehaviour.Range erosion,
			DimensionBehaviour.Range depth
		) {
			return register(ctx, biome, radius, relative, snap, temperature, humidity, continentalness, erosion, depth, 0);
		}

		int register(
			@NotNull BootstapContext<Squisher> ctx,
			ResourceKey<Biome> biome,
			double radius,
			Relative relative,
			boolean snap,
			DimensionBehaviour.Squish temperature,
			DimensionBehaviour.Squish humidity,
			DimensionBehaviour.Range continentalness,
			DimensionBehaviour.Range erosion,
			DimensionBehaviour.Range depth,
			int iteration
		) {
			int i = 0;
			for (; i < this.weirdnesses.size(); ++i) {
				ctx.register(
					ResourceKey.create(BiomeSquisherRegistries.SQUISHER, WilderSharedConstants.id(biome.location().getPath() + "_" + (i + iteration))),
					new Squisher(
						Injection.of(
							temperature,
							humidity,
							continentalness,
							erosion,
							depth,
							this.weirdnesses.get(i),
							radius
						),
						ctx.lookup(Registries.BIOME).getOrThrow(biome),
						relative,
						snap
					)
				);
			}
			return i;
		}
	}
}
