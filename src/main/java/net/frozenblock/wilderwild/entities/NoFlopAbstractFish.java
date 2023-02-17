package net.frozenblock.wilderwild.entities;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.level.Level;

public abstract class NoFlopAbstractFish extends AbstractFish {
    public NoFlopAbstractFish(EntityType<? extends NoFlopAbstractFish> entityType, Level level) {
        super(entityType, level);
    }

    protected SoundEvent getFlopSound() {
        return null;
    }

    public boolean canRandomSwim() {
        return super.canRandomSwim();
    }
}
