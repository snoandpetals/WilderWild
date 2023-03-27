/*
 * Copyright 2022-2023 FrozenBlock
 * This file is part of Wilder Wild.
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, see <https://www.gnu.org/licenses/>.
 */

package net.frozenblock.wilderwild.tag;

import net.frozenblock.wilderwild.misc.WilderSharedConstants;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;

public final class WilderBiomeTags {
	private WilderBiomeTags() {
		throw new UnsupportedOperationException("WilderBiomeTags contains only static declarations.");
	}

    public static final TagKey<Biome> FIREFLY_SPAWNABLE_DURING_DAY = bind("firefly_spawnable_during_day");
    public static final TagKey<Biome> FIREFLY_SPAWNABLE_CAVE = bind("firefly_spawnable_cave");
    public static final TagKey<Biome> FIREFLY_SPAWNABLE = bind("firefly_spawnable");
    public static final TagKey<Biome> ABANDONED_CABIN_HAS_STRUCTURE = bind("has_structure/abandoned_cabin");
    public static final TagKey<Biome> HAS_JELLYFISH = bind("has_jellyfish");
    public static final TagKey<Biome> PEARLESCENT_JELLYFISH = bind("pearlescent_jellyfish");
	public static final TagKey<Biome> JELLYFISH_SPECIAL_SPAWN = bind("jellyfish_special_spawn");
	public static final TagKey<Biome> HAS_TUMBLEWEED_ENTITY = bind("has_tumbleweed_entity");
	public static final TagKey<Biome> HAS_TUMBLEWEED_PLANT = bind("has_tumbleweed_plant");
    public static final TagKey<Biome> NO_POOLS = bind("no_pools");
    public static final TagKey<Biome> NON_FROZEN_PLAINS = bind("non_frozen_plains");
    public static final TagKey<Biome> SWAMP_TREES = bind("swamp_trees");
    public static final TagKey<Biome> SHORT_TAIGA = bind("short_taiga");
    public static final TagKey<Biome> TALL_PINE_TAIGA = bind("tall_pine_taiga");
    public static final TagKey<Biome> TALL_SPRUCE_TAIGA = bind("tall_spruce_taiga");
    public static final TagKey<Biome> GROVE = bind("grove");
    public static final TagKey<Biome> NORMAL_SAVANNA = bind("normal_savanna");
    public static final TagKey<Biome> WINDSWEPT_SAVANNA = bind("windswept_savanna");
    public static final TagKey<Biome> SNOWY_PLAINS = bind("snowy_plains");
    public static final TagKey<Biome> WINDSWEPT_HILLS = bind("windswept_hills");
    public static final TagKey<Biome> WINDSWEPT_FOREST = bind("windswept_forest");
	public static final TagKey<Biome> RAINFOREST = bind("rainforest");
    public static final TagKey<Biome> HAS_FALLEN_BIRCH_TREES = bind("has_fallen_birch_trees");
	public static final TagKey<Biome> HAS_FALLEN_CHERRY_TREES = bind("has_fallen_cherry_trees");
	public static final TagKey<Biome> HAS_FALLEN_OAK_AND_BIRCH_TREES = bind("has_fallen_oak_and_birch_trees");
	public static final TagKey<Biome> HAS_FALLEN_OAK_AND_SPRUCE_TREES = bind("has_fallen_oak_and_spruce_trees");
	public static final TagKey<Biome> HAS_MOSSY_FALLEN_MIXED_TREES = bind("has_mossy_fallen_mixed_trees");
	public static final TagKey<Biome> HAS_MOSSY_FALLEN_OAK_AND_BIRCH = bind("has_mossy_fallen_oak_and_birch");
	public static final TagKey<Biome> HAS_FALLEN_ACACIA_AND_OAK = bind("has_fallen_acacia_and_oak");
	public static final TagKey<Biome> HAS_FALLEN_PALM = bind("has_fallen_palm");
	public static final TagKey<Biome> HAS_FALLEN_PALM_RARE = bind("has_fallen_palm_rare");
	public static final TagKey<Biome> HAS_FALLEN_PALM_AND_JUNGLE_AND_OAK = bind("has_fallen_palm_and_jungle_and_oak");
	public static final TagKey<Biome> HAS_FALLEN_BIRCH_AND_OAK_DARK_FOREST = bind("has_fallen_birch_and_oak_dark_forest");
	public static final TagKey<Biome> HAS_FALLEN_SPRUCE_TREES = bind("has_fallen_spruce_trees");
	public static final TagKey<Biome> HAS_FALLEN_SWAMP_OAK_TREES = bind("has_fallen_swamp_oak_trees");
	public static final TagKey<Biome> HAS_FALLEN_MANGROVE_TREES = bind("has_fallen_mangrove_trees");
    public static final TagKey<Biome> DARK_FOREST = bind("dark_forest");
    public static final TagKey<Biome> MEADOW = bind("meadow");
	public static final TagKey<Biome> OAK_SAPLINGS_GROW_SWAMP_VARIANT = bind("oak_saplings_grow_swamp_variant");
	public static final TagKey<Biome> FOREST_GRASS = bind("forest_grass");
	public static final TagKey<Biome> HAS_SMALL_SPONGE = bind("has_small_sponge");
	public static final TagKey<Biome> HAS_SMALL_SPONGE_RARE = bind("has_small_sponge_rare");
	public static final TagKey<Biome> HAS_HUGE_RED_MUSHROOM = bind("has_huge_red_mushroom");
	public static final TagKey<Biome> HAS_HUGE_BROWN_MUSHROOM = bind("has_huge_brown_mushroom");
	public static final TagKey<Biome> HAS_BIG_MUSHROOMS = bind("has_big_mushrooms");
	public static final TagKey<Biome> HAS_COMMON_BROWN_MUSHROOM = bind("has_common_brown_mushroom");
	public static final TagKey<Biome> HAS_COMMON_RED_MUSHROOM = bind("has_common_red_mushroom");
	public static final TagKey<Biome> HAS_SWAMP_MUSHROOM = bind("has_swamp_mushroom");
	public static final TagKey<Biome> HAS_BIG_MUSHROOM_PATCH = bind("has_big_mushroom_patch");
	public static final TagKey<Biome> HAS_DATURA = bind("has_datura");
	public static final TagKey<Biome> HAS_COMMON_DATURA = bind("has_common_datura");
	public static final TagKey<Biome> HAS_CARNATION = bind("has_carnation");
	public static final TagKey<Biome> HAS_CATTAIL = bind("has_cattail");
	public static final TagKey<Biome> HAS_CATTAIL_COMMON = bind("has_cattail_common");
	public static final TagKey<Biome> HAS_SEEDING_DANDELION = bind("has_seeding_dandelion");
	public static final TagKey<Biome> HAS_COMMON_SEEDING_DANDELION = bind("has_common_seeding_dandelion");
	public static final TagKey<Biome> HAS_MILKWEED = bind("has_milkweed");
	public static final TagKey<Biome> HAS_PALMS = bind("has_palms");
	public static final TagKey<Biome> HAS_SHORT_SPRUCE = bind("has_short_spruce");
	public static final TagKey<Biome> HAS_SHORT_MEGA_SPRUCE = bind("has_short_mega_spruce");
	public static final TagKey<Biome> HAS_SHORT_MEGA_SPRUCE_SNOWY = bind("has_short_mega_spruce_snowy");
	public static final TagKey<Biome> HAS_BIG_SHRUB = bind("has_big_shrub");
	public static final TagKey<Biome> HAS_POLLEN = bind("has_pollen");
	public static final TagKey<Biome> HAS_FIELD_FLOWERS = bind("has_field_flowers");
	public static final TagKey<Biome> HAS_RED_SHELF_FUNGUS = bind("has_red_shelf_fungus");
	public static final TagKey<Biome> HAS_BROWN_SHELF_FUNGUS = bind("has_brown_shelf_fungus");
	public static final TagKey<Biome> HAS_GLORY_OF_THE_SNOW = bind("has_glory_of_the_snow");
	public static final TagKey<Biome> HAS_FLOWERING_WATER_LILY = bind("has_flowering_water_lily");
	public static final TagKey<Biome> HAS_BERRY_PATCH = bind("has_berry_patch");
	public static final TagKey<Biome> HAS_BUSH = bind("has_bush");
	public static final TagKey<Biome> HAS_LARGE_FERN_AND_GRASS = bind("has_large_fern_and_grass");
	public static final TagKey<Biome> HAS_LARGE_FERN_AND_GRASS_RARE = bind("has_large_fern_and_grass_rare");
	public static final TagKey<Biome> HAS_NEW_RARE_GRASS = bind("has_new_rare_grass");
	public static final TagKey<Biome> HAS_SPARSE_JUNGLE_FLOWERS = bind("has_sparse_jungle_flowers");
	public static final TagKey<Biome> HAS_JUNGLE_FLOWERS = bind("has_jungle_flowers");
	public static final TagKey<Biome> HAS_JUNGLE_BUSH = bind("has_jungle_bush");
	public static final TagKey<Biome> HAS_SPARSE_JUNGLE_BUSH = bind("has_sparse_jungle_bush");
	public static final TagKey<Biome> HAS_MOSS_PILE = bind("has_moss_pile");
	public static final TagKey<Biome> HAS_DECORATIVE_MUD = bind("has_decorative_mud");
	public static final TagKey<Biome> HAS_PACKED_MUD_ORE = bind("has_packed_mud_ore");
	public static final TagKey<Biome> HAS_COARSE_DIRT_PATH = bind("has_coarse_dirt_path");
	public static final TagKey<Biome> HAS_COARSE_DIRT_PATH_SMALL = bind("has_coarse_dirt_path_small");
	public static final TagKey<Biome> HAS_PACKED_MUD_PATH_BADLANDS = bind("has_packed_mud_path_badlands");
	public static final TagKey<Biome> HAS_SANDSTONE_PATH = bind("has_sandstone_path");
	public static final TagKey<Biome> HAS_SCORCHED_SAND = bind("has_scorched_sand");
	public static final TagKey<Biome> HAS_SCORCHED_RED_SAND = bind("has_scorched_red_sand");
	public static final TagKey<Biome> HAS_SMALL_SAND_TRANSITION = bind("has_small_sand_transition");
	public static final TagKey<Biome> HAS_SAND_TRANSITION = bind("has_sand_transition");
	public static final TagKey<Biome> HAS_RED_SAND_TRANSITION = bind("has_red_sand_transition");
	public static final TagKey<Biome> HAS_STONE_TRANSITION = bind("has_stone_transition");
	public static final TagKey<Biome> HAS_BETA_BEACH_SAND_TRANSITION = bind("has_beta_beach_sand_transition");
	public static final TagKey<Biome> HAS_GRAVEL_TRANSITION = bind("has_gravel_transition");
	public static final TagKey<Biome> HAS_MUD_TRANSITION = bind("has_mud_transition");
	public static final TagKey<Biome> HAS_TERMITE_MOUND = bind("has_termite_mound");
	public static final TagKey<Biome> HAS_TAIGA_FOREST_ROCK = bind("has_taiga_forest_rock");
	public static final TagKey<Biome> HAS_MOSS_PATH = bind("has_moss_path");
	public static final TagKey<Biome> HAS_MOSS_LAKE = bind("has_moss_lake");
	public static final TagKey<Biome> HAS_MOSS_LAKE_RARE = bind("has_moss_lake_rare");
	public static final TagKey<Biome> HAS_MOSS_BASIN = bind("has_moss_basin");
	public static final TagKey<Biome> HAS_PODZOL_BASIN = bind("has_podzol_basin");
	public static final TagKey<Biome> HAS_MOSS_CARPET = bind("has_moss_carpet");
	public static final TagKey<Biome> HAS_PACKED_MUD_PATH = bind("has_packed_mud_path");
	public static final TagKey<Biome> HAS_CLAY_PATH = bind("has_clay_path");

	public static final TagKey<Biome> GRAVEL_BEACH = bind("gravel_beaches");
	public static final TagKey<Biome> SAND_BEACHES = bind("sand_beaches");
	public static final TagKey<Biome> MULTI_LAYER_SAND_BEACHES = bind("multi_layer_sand_beaches");

	public static final TagKey<Biome> WILDER_WILD_BIOMES = bind("wilder_wild_biomes");

    private static TagKey<Biome> bind(String path) {
        return TagKey.create(Registries.BIOME, WilderSharedConstants.id(path));
    }
}
