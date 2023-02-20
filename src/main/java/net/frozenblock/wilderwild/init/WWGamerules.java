package net.frozenblock.wilderwild.init;

import net.frozenblock.wilderwild.mixin.accessors.BooleanValueAccessor;
import net.minecraft.world.level.GameRules;

public class WWGamerules {

    public static void init() { }

    public static final GameRules.Key<GameRules.BooleanValue> STONE_CHEST_CLOSES = GameRules.register("stoneChestCloses", GameRules.Category.MISC, BooleanValueAccessor.callCreate(true));

}
