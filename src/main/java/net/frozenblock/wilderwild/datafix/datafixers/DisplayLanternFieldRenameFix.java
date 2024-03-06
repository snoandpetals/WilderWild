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

package net.frozenblock.wilderwild.datafix.datafixers;

import com.mojang.datafixers.DSL;
import com.mojang.datafixers.DataFix;
import com.mojang.datafixers.OpticFinder;
import com.mojang.datafixers.TypeRewriteRule;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.datafixers.types.Type;
import com.mojang.datafixers.types.templates.List;
import com.mojang.serialization.Dynamic;
import net.frozenblock.wilderwild.misc.WilderSharedConstants;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.datafix.ExtraDataFixUtils;
import net.minecraft.util.datafix.fixes.References;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

public class DisplayLanternFieldRenameFix extends DataFix {
	public DisplayLanternFieldRenameFix(Schema outputSchema) {
		super(outputSchema, true);
	}

	private Dynamic<?> fixDisplayLantern(Dynamic<?> dynamic) {
		return dynamic.remove("Fireflies");
	}

	private Dynamic<?> fixOccupant(Dynamic<?> dynamic) {
		AtomicReference<ResourceLocation> id = new AtomicReference<>();
		dynamic.update("color", dynamicx -> {
			Optional<ResourceLocation> optionalId = dynamicx.read(ResourceLocation.CODEC).result();
			id.set(optionalId.orElseGet(() -> WilderSharedConstants.id("on")));

			return dynamicx;
		});
		dynamic = dynamic.set("color", dynamic.createString(id.toString()));
		dynamic = ExtraDataFixUtils.renameField(dynamic, "customName", "custom_name");
		return dynamic;
	}

	@Override
	protected TypeRewriteRule makeRule() {
		Type<?> type = this.getInputSchema().getChoiceType(References.BLOCK_ENTITY, WilderSharedConstants.string("display_lantern"));
		OpticFinder<?> opticFinder = DSL.namedChoice(WilderSharedConstants.string("display_lantern"), type);
		List.ListType<?> listType = (List.ListType<?>) type.findFieldType("Fireflies");
		Type<?> type2 = listType.getElement();
		OpticFinder<?> opticFinder2 = DSL.fieldFinder("Fireflies", listType);
		OpticFinder<?> opticFinder3 = DSL.typeFinder(type2);
		Type<?> type3 = this.getInputSchema().getType(References.BLOCK_ENTITY);
		Type<?> type4 = this.getOutputSchema().getType(References.BLOCK_ENTITY);
		return this.fixTypeEverywhereTyped(
			"DisplayLanternFieldRenameFix",
			type3,
			type4,
			typed -> ExtraDataFixUtils.cast(
				type4,
				typed.updateTyped(
					opticFinder,
					typedx -> typedx.update(DSL.remainderFinder(), this::fixDisplayLantern)
						.updateTyped(opticFinder2, typedxx -> typedxx.updateTyped(opticFinder3, typedxxx -> typedxxx.update(DSL.remainderFinder(), this::fixOccupant)))
				)
			)
		);
	}
}