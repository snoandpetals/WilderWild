/*
 * Copyright 2023-2024 FrozenBlock
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

package net.frozenblock.wilderwild.datafix.wilderwild;

import com.mojang.datafixers.schemas.Schema;
import net.fabricmc.fabric.api.datafixer.v1.FabricDataFixerBuilder;
import net.fabricmc.fabric.api.datafixer.v1.FabricDataFixes;
import net.fabricmc.fabric.api.datafixer.v1.SimpleFixes;
import net.fabricmc.loader.api.ModContainer;
import net.frozenblock.wilderwild.misc.WilderSharedConstants;
import net.minecraft.util.datafix.schemas.NamespacedSchema;
import org.jetbrains.annotations.NotNull;

public class TempDataFixer {

	public static final int DATA_VERSION = 1;

	public static void applyDataFixes(final @NotNull ModContainer mod) {
		var builder = new FabricDataFixerBuilder(DATA_VERSION);
		builder.addSchema(0, FabricDataFixes.getBaseSchema());

		Schema schemaV1 = builder.addSchema(1, NamespacedSchema::new);
		SimpleFixes.addBlockRenameFix(builder, "Rename baobab sign to wood sign", WilderSharedConstants.id("baobab_sign"), WilderSharedConstants.id("wood_sign"), schemaV1);
		SimpleFixes.addItemRenameFix(builder, "Rename baobab sign item to wood sign", WilderSharedConstants.id("baobab_sign"), WilderSharedConstants.id("wood_sign"), schemaV1);

		FabricDataFixes.buildAndRegisterFixer(mod, "temp", builder);
	}
}
