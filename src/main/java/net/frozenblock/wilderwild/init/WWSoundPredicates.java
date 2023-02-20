package net.frozenblock.wilderwild.init;

import net.frozenblock.wilderwild.WilderWild;
import net.frozenblock.wilderwild.api.SoundPredicate;
import net.frozenblock.wilderwild.entities.Firefly;
import net.frozenblock.wilderwild.util.interfaces.WilderEnderman;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.InstrumentItem;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class WWSoundPredicates {

    public static void init() {
    }

    public static final SoundPredicate<Player> INSTRUMENT = SoundPredicate.register(WilderWild.id("instrument"), player -> player.getUseItem().getItem() instanceof InstrumentItem);
    public static final SoundPredicate<Firefly> NECTAR = SoundPredicate.register(WilderWild.id("nectar"), firefly -> !firefly.isSilent() && firefly.hasCustomName() && Objects.requireNonNull(firefly.getCustomName()).getString().toLowerCase().contains("nectar"));
    public static final SoundPredicate<EnderMan> ENDERMAN_ANGER = SoundPredicate.register(WilderWild.id("enderman_anger"), new SoundPredicate.LoopPredicate<>() {
        @Override
        public boolean test(EnderMan entity) {
            if (entity.isSilent() || !entity.isAlive() || entity.isRemoved()) {
                return false;
            }
            return entity.isCreepy();
        }
        @Override
        public void onStart(@Nullable EnderMan entity) {
            if (entity != null) {
                ((WilderEnderman) entity).setCanPlayLoopingSound(false);
            }
        }

        @Override
        public void onStop(@Nullable EnderMan entity) {
            if (entity != null) {
                ((WilderEnderman) entity).setCanPlayLoopingSound(true);
            }
        }
    });

}
