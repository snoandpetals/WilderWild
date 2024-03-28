/*
 * Copyright 2024 FrozenBlock
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

package net.frozenblock.wilderwild.datafix.minecraft;

import com.mojang.datafixers.DSL;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.datafixers.types.templates.TypeTemplate;
import net.frozenblock.wilderwild.misc.WilderSharedConstants;
import net.minecraft.util.datafix.fixes.References;
import net.minecraft.util.datafix.schemas.NamespacedSchema;
import net.minecraft.util.datafix.schemas.V100;
import java.util.Map;
import java.util.function.Supplier;

public class WWMinecraftV1 extends NamespacedSchema {
	public WWMinecraftV1(int versionKey, Schema parent) {
		super(versionKey, parent);
	}

	@Override
	public Map<String, Supplier<TypeTemplate>> registerBlockEntities(Schema schema) {
		var map = super.registerBlockEntities(schema);
		schema.register(
			map,
			WilderSharedConstants.string("display_lantern"),
			() -> DSL.optionalFields("Items", DSL.list(References.ITEM_STACK.in(schema)))
		);
		schema.register(
			map,
			WilderSharedConstants.string("hanging_tendril"),
			DSL::remainder
		);
		schema.register(
			map,
			WilderSharedConstants.string("scorched_block"),
			DSL::remainder
		);
		schema.register(
			map,
			WilderSharedConstants.string("stone_chest"),
			() -> DSL.optionalFields("Items", DSL.list(References.ITEM_STACK.in(schema)))
		);
		schema.register(
			map,
			WilderSharedConstants.string("termite_mound"),
			DSL::remainder
		);
		schema.register(
			map,
			WilderSharedConstants.string("geyser"),
			DSL::remainder
		);
		return map;
	}

	@Override
	public Map<String, Supplier<TypeTemplate>> registerEntities(Schema schema) {
		var map = super.registerEntities(schema);
		schema.register(
			map,
			WilderSharedConstants.string("jellyfish"),
			() -> V100.equipment(schema)
		);
		schema.register(
			map,
			WilderSharedConstants.string("ostrich"),
			() -> V100.equipment(schema)
		);
		schema.register(
			map,
			WilderSharedConstants.string("crab"),
			() -> V100.equipment(schema)
		);
		schema.register(
			map,
			WilderSharedConstants.string("firefly"),
			() -> V100.equipment(schema)
		);
		schema.register(
			map,
			WilderSharedConstants.string("tumbleweed"),
			(string) -> DSL.optionalFields("Items", References.ITEM_STACK.in(schema), V100.equipment(schema))
		);
		schema.register(
			map,
			WilderSharedConstants.string("ancient_horn_vibration"),
			DSL::remainder
		);
		schema.register(
			map,
			WilderSharedConstants.string("coconut"),
			DSL::remainder
		);
		schema.register(
			map,
			WilderSharedConstants.string("chest_bubbler"),
			DSL::remainder
		);
		schema.register(
			map,
			WilderSharedConstants.string("sculk_spreader"),
			DSL::remainder
		);
		schema.register(
			map,
			WilderSharedConstants.string("scorched"),
			() -> V100.equipment(schema)
		);
		return map;
	}
}
