package net.frozenblock.wilderwild.util.interfaces;

import net.frozenblock.wilderwild.api.MovingLoopingSoundEntityManager;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.ApiStatus;

@ApiStatus.Internal
@InjectedInterface(LivingEntity.class)
public interface EntityLoopingSoundInterface {
    boolean hasSyncedClient();

    MovingLoopingSoundEntityManager getSounds();

    void addSound(ResourceLocation var1, SoundSource var2, float var3, float var4, ResourceLocation var5);
}
