package net.frozenblock.wilderwild.util.interfaces;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemCooldowns;
import net.minecraft.world.item.ServerItemCooldowns;

@InjectedInterface({ItemCooldowns.class, ServerItemCooldowns.class})
public interface CooldownInterface {
    void changeCooldown(Item var1, int var2);

    void onCooldownChanged(Item var1, int var2);
}
