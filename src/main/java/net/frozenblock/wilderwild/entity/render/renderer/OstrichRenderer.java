/*
 * Copyright 2023 FrozenBlock
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

package net.frozenblock.wilderwild.entity.render.renderer;

import net.frozenblock.wilderwild.WilderWildClient;
import net.frozenblock.wilderwild.entity.Ostrich;
import net.frozenblock.wilderwild.entity.render.model.OstrichModel;
import net.frozenblock.wilderwild.misc.WilderSharedConstants;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.SaddleLayer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class OstrichRenderer<T extends Ostrich> extends MobRenderer<T, OstrichModel<T>> {
	private static final ResourceLocation OSTRICH_LOCATION = WilderSharedConstants.id("textures/entity/ostrich/ostrich.png");
	private static final ResourceLocation OSTRICH_SADDLE_LOCATION = WilderSharedConstants.id("textures/entity/ostrich/ostrich_saddle.png");

	public OstrichRenderer(EntityRendererProvider.Context context) {
		this(context, WilderWildClient.OSTRICH);
	}

	public OstrichRenderer(EntityRendererProvider.Context context, ModelLayerLocation layer) {
		super(context, new OstrichModel<>(context.bakeLayer(layer)), 0.75F);
		this.addLayer(new SaddleLayer<>(this, new OstrichModel<>(context.bakeLayer(WilderWildClient.OSTRICH_SADDLE)), OSTRICH_SADDLE_LOCATION));
	}

	@Override
	@NotNull
	public ResourceLocation getTextureLocation(@NotNull T entity) {
		return OSTRICH_LOCATION;
	}

}

