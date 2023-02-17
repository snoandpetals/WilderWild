package net.frozenblock.wilderwild.item;

import net.frozenblock.wilderwild.WilderWild;
import net.frozenblock.wilderwild.entities.AncientHornProjectile;
import net.frozenblock.wilderwild.init.WWItems;
import net.frozenblock.wilderwild.util.interfaces.CooldownInterface;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.core.Holder;
import net.minecraft.core.NonNullList;
import net.minecraft.core.Registry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Instrument;
import net.minecraft.world.item.InstrumentItem;
import net.minecraft.world.item.ItemCooldowns;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;
import java.util.Optional;

public class AncientHornItem extends InstrumentItem {
    private static final String TAG_INSTRUMENT = "instrument";
    private final TagKey<Instrument> instrumentTag;

    public static final int DEFAULT_COOLDOWN = 300;
    public static final int CREATIVE_COOLDOWN = 5;
    public static final int SHRIEKER_COOLDOWN = 900;
    public static final int SENSOR_COOLDOWN = 400;
    public static final int TENDRIL_COOLDOWN = 380;
    public static final int MIN_BUBBLES = 10;
    public static final int MAX_BUBBLES = 25;

    public AncientHornItem(Properties settings, TagKey<Instrument> tag) {
        super(settings, tag);
        this.instrumentTag = tag;
    }

    @Override
    public void fillItemCategory(@NotNull CreativeModeTab creativeModeTab, @NotNull NonNullList<ItemStack> nonNullList) {
        if (this.allowedIn(creativeModeTab)) {
            for (Holder<Instrument> holder : Registry.INSTRUMENT.getTagOrEmpty(this.instrumentTag)) {
                nonNullList.add(create(WWItems.ANCIENT_HORN.get(), holder));
            }
        }

    }

    @Override
    @NotNull
    public InteractionResultHolder<ItemStack> use(@NotNull Level level, @NotNull Player user, @NotNull InteractionHand hand) {
        ItemStack itemStack = user.getItemInHand(hand);
        Optional<Holder<Instrument>> optional = this.getInstrument(itemStack);
        if (optional.isPresent()) {
            Instrument instrument = optional.get().value();
            user.startUsingItem(hand);
            play(level, user, instrument);
            user.getCooldowns().addCooldown(WWItems.ANCIENT_HORN.get(), getCooldown(user, DEFAULT_COOLDOWN));
            if (level instanceof ServerLevel server) {
                AncientHornProjectile projectileEntity = new AncientHornProjectile(level, user.getX(), user.getEyeY(), user.getZ());
                projectileEntity.shootFromRotation(user, user.getXRot(), user.getYRot(), 0.0F, 1.0F, 0.0F);
                projectileEntity.setShotByPlayer(true);
                server.addFreshEntity(projectileEntity);
//                NetworkUtil.createMovingRestrictionLoopingSound(server, projectileEntity, WWSoundEvents.ENTITY_ANCIENT_HORN_PROJECTILE_LOOP.get(), SoundSource.NEUTRAL, 1.0F, 1.0F, new ResourceLocation("frozenlib", "default"));
                ItemStack mainHand = user.getItemInHand(InteractionHand.MAIN_HAND);
                ItemStack offHand = user.getItemInHand(InteractionHand.OFF_HAND);
                if (mainHand.is(Items.WATER_BUCKET) || mainHand.is(Items.POTION) || offHand.is(Items.WATER_BUCKET) || offHand.is(Items.POTION)) {
                    projectileEntity.setBubbles(level.random.nextIntBetweenInclusive(MIN_BUBBLES, MAX_BUBBLES));
                }
            }
            return InteractionResultHolder.consume(itemStack);
        } else {
            WilderWild.LOGGER.error("Ancient Horn use failed");
            return InteractionResultHolder.fail(itemStack);
        }
    }

    @Override
    public int getUseDuration(@NotNull ItemStack stack) {
        Optional<Holder<Instrument>> optional = this.getInstrument(stack);
        return optional.map(instrumentRegistryEntry -> instrumentRegistryEntry.value().useDuration()).orElse(0);
    }

    @Override
    public Optional<Holder<Instrument>> getInstrument(ItemStack stack) {
        CompoundTag nbtCompound = stack.getTag();
        if (nbtCompound != null) {
            ResourceLocation resourceLocation = ResourceLocation.tryParse(nbtCompound.getString(TAG_INSTRUMENT));
            if (resourceLocation != null) {
                return Registry.INSTRUMENT.getHolder(ResourceKey.create(Registry.INSTRUMENT_REGISTRY, resourceLocation));
            }
        }

        Iterator<Holder<Instrument>> iterator = Registry.INSTRUMENT.getTagOrEmpty(this.instrumentTag).iterator();
        return iterator.hasNext() ? Optional.of(iterator.next()) : Optional.empty();
    }

    public static int getCooldown(@Nullable Entity entity, int cooldown) {
        if (entity instanceof Player player && player.isCreative()) {
            return CREATIVE_COOLDOWN;
        }
        return cooldown;
    }

    public static int decreaseCooldown(Player user, int time) {
        if (!user.isCreative()) {
            ItemCooldowns manager = user.getCooldowns();
            ItemCooldowns.CooldownInstance entry = manager.cooldowns.get(WWItems.ANCIENT_HORN.get());
            if (entry != null) {
                int between = entry.endTime - entry.startTime;
                if (between > 140 && between >= time) {
                    ((CooldownInterface) user.getCooldowns()).changeCooldown(WWItems.ANCIENT_HORN.get(), -time);
                    return time;
                }
            }
        }
        return -1;
    }

    @Override
    @NotNull
    public UseAnim getUseAnimation(@NotNull ItemStack stack) {
        return UseAnim.TOOT_HORN;
    }

    private static void play(Level level, Player player, Instrument instrument) {
        SoundEvent soundEvent = instrument.soundEvent();
        float range = instrument.range() / LevelRenderer.CHUNK_SIZE;
        level.playSound(player, player, soundEvent, SoundSource.RECORDS, range, 1.0F);
    }

}
