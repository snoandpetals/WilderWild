package net.frozenblock.wilderwild.util.interfaces;

import net.frozenblock.wilderwild.api.MovingLoopingFadingDistanceSoundEntityManager;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.ApiStatus;

@ApiStatus.Internal
@InjectedInterface({LivingEntity.class})
public interface EntityLoopingFadingDistanceSoundInterface {
    boolean hasSyncedFadingDistanceClient();

    MovingLoopingFadingDistanceSoundEntityManager getFadingDistanceSounds();

    void addFadingDistanceSound(ResourceLocation var1, ResourceLocation var2, SoundSource var3, float var4, float var5, ResourceLocation var6, float var7, float var8);
}
