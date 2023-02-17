package net.frozenblock.wilderwild.mixin;

import net.frozenblock.wilderwild.init.WWSoundEvents;
import net.minecraft.ChatFormatting;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.animal.frog.Frog;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Objects;

@Mixin(Frog.class)
public final class FrogMixin {

    @Inject(at = @At("RETURN"), method = "getDeathSound", cancellable = true)
    public void newDeath(CallbackInfoReturnable<SoundEvent> cir) {
        String string = ChatFormatting.stripFormatting(Frog.class.cast(this).getName().getString());
        if (Objects.equals(string, "Xfrtrex")) {
            cir.setReturnValue(WWSoundEvents.ENTITY_FROG_SUS_DEATH.get());
        }
    }

}
