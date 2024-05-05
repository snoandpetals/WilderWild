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

package net.frozenblock.wilderwild.registry;

import net.fabricmc.fabric.api.registry.FabricBrewingRecipeRegistry;
import net.frozenblock.wilderwild.misc.WilderSharedConstants;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public final class RegisterPotions {

	public static final Potion REACH = register("reach", new Potion(new MobEffectInstance(RegisterMobEffects.REACH_BOOST, 3600)));
	public static final Potion LONG_REACH = register("long_reach", new Potion("reach", new MobEffectInstance(RegisterMobEffects.REACH_BOOST, 9600)));
	public static final Potion STRONG_REACH = register("strong_reach", new Potion("reach", new MobEffectInstance(RegisterMobEffects.REACH_BOOST, 2700, 1)));
	public static final Potion SCORCHING = register("scorching", new Potion("scorching", new MobEffectInstance(RegisterMobEffects.SCORCHING, 2700)));

	private RegisterPotions() {
		throw new UnsupportedOperationException("RegisterPotions contains only static declarations.");
	}

	public static void init() {
		WilderSharedConstants.logWithModId("Registering Potions for", WilderSharedConstants.UNSTABLE_LOGGING);

		FabricBrewingRecipeRegistry.registerPotionRecipe(Potions.AWKWARD, Ingredient.of(RegisterItems.CRAB_CLAW), REACH);
		FabricBrewingRecipeRegistry.registerPotionRecipe(REACH, Ingredient.of(Items.REDSTONE), LONG_REACH);
		FabricBrewingRecipeRegistry.registerPotionRecipe(REACH, Ingredient.of(Items.GLOWSTONE_DUST), STRONG_REACH);

		FabricBrewingRecipeRegistry.registerPotionRecipe(Potions.AWKWARD, Ingredient.of(RegisterItems.FERMENTED_SCORCHED_EYE), SCORCHING);
	}

	private static @NotNull Potion register(String key, Potion potion) {
		return Registry.register(BuiltInRegistries.POTION, WilderSharedConstants.id(key), potion);
	}

	@Contract("_, _ -> param2")
	private static @NotNull Potion register(ResourceKey<Potion> key, Potion potion) {
		return Registry.register(BuiltInRegistries.POTION, key, potion);
	}
}
