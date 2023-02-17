package net.frozenblock.wilderwild.util.interfaces;

import net.minecraft.world.entity.ai.behavior.Behavior;

@InjectedInterface({Behavior.class})
public interface FrozenBehavior {
    int getDuration();
}
