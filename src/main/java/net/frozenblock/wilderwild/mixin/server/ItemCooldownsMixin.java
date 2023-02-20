package net.frozenblock.wilderwild.mixin.server;

import net.frozenblock.wilderwild.mixin.CooldownInstanceAccessor;
import net.frozenblock.wilderwild.util.interfaces.CooldownInterface;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemCooldowns;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

import java.util.Map;

@Mixin(ItemCooldowns.class)
public class ItemCooldownsMixin implements CooldownInterface {
    @Final
    @Shadow
    public Map<Item, ItemCooldowns.CooldownInstance> cooldowns;

    public ItemCooldownsMixin() {
    }

    @Unique
    public void changeCooldown(Item item, int additional) {
        if (this.cooldowns.containsKey(item)) {
            ItemCooldowns.CooldownInstance cooldown = this.cooldowns.get(item);
            this.cooldowns.put(item, CooldownInstanceAccessor.createCooldownInstance(cooldown.startTime, cooldown.endTime + additional));
            this.onCooldownChanged(item, additional);
        }

    }

    @Unique
    public void onCooldownChanged(Item item, int additional) {
    }
}
