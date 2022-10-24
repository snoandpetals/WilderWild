package net.frozenblock.wilderwild.misc;

import net.frozenblock.lib.sound.MovingSoundWithRestriction;
import net.frozenblock.lib.sound.SoundPredicate.SoundPredicate;
import net.minecraft.client.Minecraft;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.monster.EnderMan;
import org.quiltmc.loader.api.minecraft.ClientOnly;

@ClientOnly
public class ClientMethods {

    public static void playClientEnderManSound(EnderMan enderMan) {
        Minecraft client = Minecraft.getInstance();
        if (client.level != null && enderMan.isAlive()) {
            client.getSoundManager().play(new MovingSoundWithRestriction(enderMan, SoundEvents.ENDERMAN_STARE, SoundSource.HOSTILE, 2.5F, 1F, SoundPredicate.NOT_SILENT_AND_ALIVE));
        }
    }

}
