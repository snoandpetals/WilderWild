package net.frozenblock.wilderwild.mixin.accessors;

import net.minecraft.world.level.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(GameRules.BooleanValue.class)
public interface BooleanValueAccessor {
    @Invoker
    static GameRules.Type<GameRules.BooleanValue> callCreate(boolean p_46251_) {
        throw new UnsupportedOperationException();
    }
}
