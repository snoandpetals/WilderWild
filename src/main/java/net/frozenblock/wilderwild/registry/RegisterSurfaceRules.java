package net.frozenblock.wilderwild.registry;

import java.util.List;
import net.frozenblock.lib.worldgen.surface.api.FrozenSurfaceRules;
import net.frozenblock.lib.worldgen.surface.api.SurfaceRuleEvents;
import net.frozenblock.lib.worldgen.surface.impl.OptimizedBiomeTagConditionSource;
import net.frozenblock.wilderwild.misc.WilderSharedConstants;
import net.frozenblock.wilderwild.tag.WilderBiomeTags;
import net.frozenblock.wilderwild.world.generation.conditionsource.BetaBeachConditionSource;
import net.frozenblock.wilderwild.world.generation.noise.WilderNoise;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Noises;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import org.jetbrains.annotations.NotNull;

public final class RegisterSurfaceRules implements SurfaceRuleEvents.OverworldSurfaceRuleCallback {

	@NotNull
	public static SurfaceRules.RuleSource cypressSurfaceRules() {
		return SurfaceRules.ifTrue(
			SurfaceRules.isBiome(RegisterWorldgen.CYPRESS_WETLANDS),
			SurfaceRules.ifTrue(
				SurfaceRules.ON_FLOOR,
				SurfaceRules.ifTrue(
					SurfaceRules.yBlockCheck(
						VerticalAnchor.absolute(60),
						0
					),
					SurfaceRules.ifTrue(
						SurfaceRules.not(
							SurfaceRules.yBlockCheck(
								VerticalAnchor.absolute(63),
								0
							)
						),
						SurfaceRules.ifTrue(
							SurfaceRules.noiseCondition(Noises.SWAMP, 0.0),
							FrozenSurfaceRules.WATER
						)
					)
				)
			)
		);
	}

	@NotNull
	public static SurfaceRules.RuleSource warmRiverRules() {
		return SurfaceRules.ifTrue(
			SurfaceRules.isBiome(RegisterWorldgen.WARM_RIVER),
			SurfaceRules.ifTrue(
				SurfaceRules.yBlockCheck(VerticalAnchor.absolute(32), 0),
				SurfaceRules.sequence(
					SurfaceRules.ifTrue(
						SurfaceRules.DEEP_UNDER_FLOOR,
						FrozenSurfaceRules.SAND
					),
					FrozenSurfaceRules.SANDSTONE
				)
			)
		);
	}

	@NotNull
	public static SurfaceRules.RuleSource desertRules() {
		return SurfaceRules.sequence(
			SurfaceRules.ifTrue(
				SurfaceRules.ON_FLOOR,
				SurfaceRules.ifTrue(
					SurfaceRules.waterBlockCheck(-1, 0),
					SurfaceRules.sequence(
						SurfaceRules.ifTrue(
							SurfaceRules.ON_CEILING,
							FrozenSurfaceRules.SANDSTONE
						),
						FrozenSurfaceRules.SAND
					)
				)
			),
			SurfaceRules.ifTrue(
				SurfaceRules.waterStartCheck(-6, -1),
				SurfaceRules.sequence(
					SurfaceRules.ifTrue(
						SurfaceRules.UNDER_FLOOR,
						SurfaceRules.sequence(
							SurfaceRules.ifTrue(
								SurfaceRules.ON_CEILING,
								FrozenSurfaceRules.SANDSTONE
							),
							FrozenSurfaceRules.SAND
						)
					),
					SurfaceRules.ifTrue(
						SurfaceRules.VERY_DEEP_UNDER_FLOOR,
						FrozenSurfaceRules.SANDSTONE
					)
				)
			)
		);
	}

	@NotNull
	public static SurfaceRules.RuleSource oasisRules() {
		return SurfaceRules.ifTrue(
			SurfaceRules.isBiome(RegisterWorldgen.OASIS),
			desertRules()
		);
	}

	@NotNull
	public static SurfaceRules.RuleSource aridGrass() {
		return SurfaceRules.ifTrue(
			SurfaceRules.isBiome(RegisterWorldgen.ARID_SAVANNA, RegisterWorldgen.ARID_FOREST),
			SurfaceRules.sequence(
				SurfaceRules.ifTrue(
					SurfaceRules.ON_FLOOR,
					SurfaceRules.ifTrue(
						SurfaceRules.noiseCondition(Noises.SURFACE, 0.155, 0.3666),
						SurfaceRules.sequence(
							SurfaceRules.ifTrue(
								SurfaceRules.ON_CEILING,
								FrozenSurfaceRules.DIRT
							),
							FrozenSurfaceRules.GRASS_BLOCK
						)
					)
				),
				SurfaceRules.ifTrue(
					SurfaceRules.noiseCondition(Noises.SURFACE, 0.155, 0.3666),
					SurfaceRules.sequence(
						SurfaceRules.ifTrue(
							SurfaceRules.UNDER_FLOOR,
							SurfaceRules.sequence(
								SurfaceRules.ifTrue(
									SurfaceRules.ON_CEILING,
									FrozenSurfaceRules.DIRT
								),
								FrozenSurfaceRules.DIRT
							)
						),
						SurfaceRules.ifTrue(
							SurfaceRules.DEEP_UNDER_FLOOR,
							FrozenSurfaceRules.DIRT
						)
					)
				)
			)
		);
	}

	@NotNull
	public static SurfaceRules.RuleSource aridRules() {
		return SurfaceRules.ifTrue(
			SurfaceRules.isBiome(RegisterWorldgen.ARID_SAVANNA, RegisterWorldgen.ARID_FOREST),
			desertRules()
		);
	}

	@NotNull
	public static SurfaceRules.RuleSource oldGrowthSnowyTaigaRules() {
		return SurfaceRules.ifTrue(
			SurfaceRules.isBiome(RegisterWorldgen.SNOWY_OLD_GROWTH_PINE_TAIGA),
			SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR,
				SurfaceRules.ifTrue(
					SurfaceRules.waterBlockCheck(-1, 0),
					SurfaceRules.sequence(
						SurfaceRules.ifTrue(
							SurfaceRules.noiseCondition(Noises.SURFACE, 1.75D / 8.25D, Double.MAX_VALUE),
							FrozenSurfaceRules.COARSE_DIRT
						),
						SurfaceRules.ifTrue(
							SurfaceRules.noiseCondition(Noises.SURFACE, -0.95D / 8.25D, Double.MAX_VALUE),
							FrozenSurfaceRules.PODZOL
						),
						SurfaceRules.ifTrue(
							SurfaceRules.noiseCondition(Noises.SURFACE, 0.0222, 0.055),
							FrozenSurfaceRules.POWDER_SNOW
						),
						SurfaceRules.ifTrue(
							SurfaceRules.noiseCondition(Noises.SURFACE, 0.065, 0.12),
							FrozenSurfaceRules.SNOW_BLOCK
						)
					)
				)
			)
		);
	}

	@NotNull
	public static SurfaceRules.RuleSource oldGrowthDarkForestRules() {
		return SurfaceRules.ifTrue(
			SurfaceRules.isBiome(RegisterWorldgen.OLD_GROWTH_DARK_FOREST),
			SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR,
				SurfaceRules.ifTrue(
					SurfaceRules.waterBlockCheck(-1, 0),
					SurfaceRules.sequence(
						SurfaceRules.ifTrue(
							SurfaceRules.noiseCondition(Noises.SURFACE, -0.0667, 0.04),
							FrozenSurfaceRules.PODZOL
						)
					)
				)
			)
		);
	}

	@NotNull
	public static SurfaceRules.RuleSource temperateRainforestRules() {
		return SurfaceRules.ifTrue(
			SurfaceRules.isBiome(RegisterWorldgen.TEMPERATE_RAINFOREST),
			SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR,
				SurfaceRules.ifTrue(
					SurfaceRules.waterBlockCheck(-1, 0),
					SurfaceRules.sequence(
						SurfaceRules.ifTrue(
							SurfaceRules.noiseCondition(Noises.SURFACE, 0.095, 0.2),
							FrozenSurfaceRules.makeStateRule(Blocks.PODZOL)
						),
						SurfaceRules.ifTrue(
							SurfaceRules.noiseCondition(Noises.POWDER_SNOW, 0.065, 0.15),
							FrozenSurfaceRules.makeStateRule(Blocks.MOSS_BLOCK)
						),
						SurfaceRules.ifTrue(
							SurfaceRules.noiseCondition(Noises.SURFACE_SECONDARY, 0.0667, 0.4),
							FrozenSurfaceRules.COARSE_DIRT
						)
					)
				)
			)
		);
	}

	@NotNull
	public static SurfaceRules.RuleSource rainforestRules() {
		return SurfaceRules.ifTrue(
			SurfaceRules.isBiome(RegisterWorldgen.RAINFOREST),
			SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR,
				SurfaceRules.ifTrue(
					SurfaceRules.waterBlockCheck(-1, 0),
					SurfaceRules.sequence(
						SurfaceRules.ifTrue(
							SurfaceRules.noiseCondition(Noises.POWDER_SNOW, 0.065, 0.15),
							FrozenSurfaceRules.makeStateRule(Blocks.MOSS_BLOCK)
						)
					)
				)
			)
		);
	}

	@NotNull
	public static SurfaceRules.RuleSource gravelBetaBeach() {
		return SurfaceRules.ifTrue(
			SurfaceRules.UNDER_FLOOR,
			SurfaceRules.ifTrue(
				SurfaceRules.yStartCheck(VerticalAnchor.absolute(58), 0),
				SurfaceRules.ifTrue(
					SurfaceRules.not(SurfaceRules.yStartCheck(VerticalAnchor.absolute(65), 0)),
					SurfaceRules.ifTrue(
						SurfaceRules.noiseCondition(WilderNoise.GRAVEL_BEACH_KEY, 0.12, 1.7976931348623157E308),
						FrozenSurfaceRules.GRAVEL
					)
				)
			)
		);
	}

	@NotNull
	public static SurfaceRules.RuleSource sandBetaBeach() {
		return SurfaceRules.ifTrue(
			SurfaceRules.DEEP_UNDER_FLOOR,
			SurfaceRules.ifTrue(
				SurfaceRules.yStartCheck(VerticalAnchor.absolute(58), 0),
				SurfaceRules.ifTrue(
					SurfaceRules.not(SurfaceRules.yStartCheck(VerticalAnchor.absolute(65), 0)),
					SurfaceRules.ifTrue(
						SurfaceRules.noiseCondition(WilderNoise.SAND_BEACH_KEY, 0.12, 1.7976931348623157E308),
						FrozenSurfaceRules.SAND
					)
				)
			)
		);
	}

	@NotNull
	public static SurfaceRules.RuleSource multiLayerSandBetaBeach() {
		return SurfaceRules.ifTrue(
			SurfaceRules.DEEP_UNDER_FLOOR,
			SurfaceRules.ifTrue(
				SurfaceRules.yStartCheck(VerticalAnchor.absolute(58), 0),
				SurfaceRules.ifTrue(
					SurfaceRules.not(SurfaceRules.yStartCheck(VerticalAnchor.absolute(64), 0)),
					SurfaceRules.ifTrue(
						SurfaceRules.noiseCondition(WilderNoise.SAND_BEACH_KEY, 0.12, 1.7976931348623157E308),
						FrozenSurfaceRules.SAND
					)
				)
			)
		);
	}

	@NotNull
	public static SurfaceRules.RuleSource gravelBetaBeaches() {
		return SurfaceRules.ifTrue(
			OptimizedBiomeTagConditionSource.isBiomeTag(WilderBiomeTags.GRAVEL_BEACH),
			gravelBetaBeach()
		);
	}

	@NotNull
	public static SurfaceRules.RuleSource sandBetaBeaches() {
		return SurfaceRules.ifTrue(
			OptimizedBiomeTagConditionSource.isBiomeTag(WilderBiomeTags.SAND_BEACHES),
			sandBetaBeach()
		);
	}

	@NotNull
	public static SurfaceRules.RuleSource multiLayerSandBetaBeaches() {
		return SurfaceRules.ifTrue(
			OptimizedBiomeTagConditionSource.isBiomeTag(WilderBiomeTags.MULTI_LAYER_SAND_BEACHES),
			multiLayerSandBetaBeach()
		);
	}

	@NotNull
	public static SurfaceRules.RuleSource betaBeaches() {
		return SurfaceRules.ifTrue(
			BetaBeachConditionSource.betaBeachConditionSource(),
			SurfaceRules.sequence(
				gravelBetaBeaches(),
				sandBetaBeaches(),
				multiLayerSandBetaBeaches()
			)
		);
	}

	@Override
	public void addOverworldSurfaceRules(@NotNull List<SurfaceRules.RuleSource> context) {
		context.add(
			SurfaceRules.sequence(
				betaBeaches(),
				cypressSurfaceRules(),
				warmRiverRules(),
				oasisRules(),
				aridGrass(),
				aridRules(),
				oldGrowthSnowyTaigaRules(),
				oldGrowthDarkForestRules(),
				temperateRainforestRules(),
				rainforestRules()
			)
		);
		WilderSharedConstants.log("Wilder Wild's Overworld Surface Rules have been added!", true);
	}
}
