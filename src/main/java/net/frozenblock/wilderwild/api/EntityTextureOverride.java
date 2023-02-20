package net.frozenblock.wilderwild.api;

import com.google.common.collect.Maps;
import net.frozenblock.wilderwild.WilderWild;
import net.frozenblock.wilderwild.util.interfaces.WilderWarden;
import net.minecraft.ChatFormatting;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Dolphin;
import net.minecraft.world.entity.animal.axolotl.Axolotl;
import net.minecraft.world.entity.animal.frog.Frog;
import net.minecraft.world.entity.animal.goat.Goat;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

@OnlyIn(Dist.CLIENT)
public record EntityTextureOverride<T extends LivingEntity>(EntityType<T> type, ResourceLocation texture, Condition<T> condition) {
    public static final Map<ResourceLocation, EntityTextureOverride<?>> ENTITY_TEXTURE_OVERRIDES = Maps.newLinkedHashMap();

    public static final EntityTextureOverride<Warden> OSMIOO_OVERRIDE = register(WilderWild.id("osmiooo_warden"), EntityType.WARDEN, WilderWild.id("textures/entity/warden/osmiooo_warden.png"), warden -> ((WilderWarden)warden).isOsmiooo());
    public static final EntityTextureOverride<Goat> TREE_TRAIN_1_OVERRIDE = register(WilderWild.id("treetrain1_goat"), EntityType.GOAT, WilderWild.id("textures/entity/goat/treetrain1_goat.png"), true, "Treetrain1", "Treetrain");
    public static final EntityTextureOverride<Frog> XFRTREX_OVERRIDE = register(WilderWild.id("xfrtrex_frog"), EntityType.FROG, WilderWild.id("textures/entity/frog/sus_frog.png"), "Xfrtrex");
    public static final EntityTextureOverride<Axolotl> SAISHO_OVERRIDE = register(WilderWild.id("saisho_axolotl"), EntityType.AXOLOTL, WilderWild.id("textures/entity/axolotl/saisho_axolotl.png"), true, "Saisho");
    public static final EntityTextureOverride<Dolphin> ALEX_OVERRIDE = register(WilderWild.id("alex_dolphin"), EntityType.DOLPHIN, WilderWild.id("textures/entity/dolphin/alex_dolphin.png"), "AlexTheDolphin0");

    public static <T extends LivingEntity> EntityTextureOverride<T> register(ResourceLocation key, EntityType<T> type, ResourceLocation texture, String... names) {
        return register(key, type, texture, false, names);
    }

    public static <T extends LivingEntity> EntityTextureOverride<T> register(ResourceLocation key, EntityType<T> type, ResourceLocation texture, boolean caseSensitive, String... names) {
        return register(key, type, texture, (entity) -> {
            String entityName = ChatFormatting.stripFormatting(entity.getName().getString());
            AtomicBoolean isNameCorrect = new AtomicBoolean(false);
            if (names.length == 0) {
                return true;
            } else {
                Arrays.stream(names).toList().forEach(name -> {
                    if (entityName != null) {
                        if (caseSensitive) {
                            if (entityName.equalsIgnoreCase(name)) {
                                isNameCorrect.set(true);
                            }
                        } else {
                            if (entityName.equals(name)) {
                                isNameCorrect.set(true);
                            }
                        }
                    }
                });
            }
            return isNameCorrect.get();
        });
    }

    public static <T extends LivingEntity> EntityTextureOverride<T> register(ResourceLocation key, EntityType<T> type, ResourceLocation texture, Condition<T> condition) {
        EntityTextureOverride<T> entityTextureOverride = new EntityTextureOverride<>(type, texture, condition);
        ENTITY_TEXTURE_OVERRIDES.put(key, entityTextureOverride);
        return entityTextureOverride;
    }

    @OnlyIn(Dist.CLIENT)
    @FunctionalInterface
    public interface Condition<T extends LivingEntity> {
        boolean condition(T entity);
    }
}
