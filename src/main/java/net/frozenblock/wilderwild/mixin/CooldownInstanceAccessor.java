package net.frozenblock.wilderwild.mixin;

import net.minecraft.world.item.ItemCooldowns;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(ItemCooldowns.CooldownInstance.class)
public interface CooldownInstanceAccessor {
    @Invoker("<init>")
    static ItemCooldowns.CooldownInstance createCooldownInstance(int p_186358_, int p_186359_) {
        throw new UnsupportedOperationException();
    }
}
