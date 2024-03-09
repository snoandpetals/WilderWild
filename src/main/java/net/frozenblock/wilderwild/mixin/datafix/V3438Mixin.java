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

package net.frozenblock.wilderwild.mixin.datafix;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.mojang.datafixers.DSL;
import com.mojang.datafixers.schemas.Schema;
import java.util.Map;
import net.frozenblock.wilderwild.misc.WilderSharedConstants;
import net.minecraft.util.datafix.fixes.References;
import net.minecraft.util.datafix.schemas.V3438;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(V3438.class)
public class V3438Mixin {

	@WrapOperation(
		method = "registerBlockEntities",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/util/datafix/schemas/NamespacedSchema;registerBlockEntities(Lcom/mojang/datafixers/schemas/Schema;)Ljava/util/Map;",
			ordinal = 0
		),
		remap = false
	)
	public Map wilderWild$registerBlockEntities(V3438 instance, Schema schema, Operation<Map> original) {
		Map map = original.call(instance, schema);
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
		return map;
	}
}
