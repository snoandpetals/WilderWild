package net.frozenblock.wilderwild.config;

import net.frozenblock.wilderwild.WilderWild;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = WilderWild.MOD_ID)
public class WilderWildConfig {

    public static final ForgeConfigSpec.Builder BUILDER;
    public static ForgeConfigSpec COMMON;
    public static ForgeConfigSpec.IntValue STONE_CHEST_TIMER;
    public static ForgeConfigSpec.IntValue HORN_LIFE_SPAN;
    public static ForgeConfigSpec.DoubleValue HORN_SIZE_MULTIPLIER;
    public static ForgeConfigSpec.BooleanValue HORNS_SHATTERS_GLASS;
    public static ForgeConfigSpec.IntValue HORN_PLAYER_DAMAGE;
    public static ForgeConfigSpec.IntValue HORN_MOB_DAMAGE;
    public static ForgeConfigSpec.BooleanValue HORN_CAN_SUMMON_WARDEN;
    public static ForgeConfigSpec.BooleanValue PROJECTILE_BREAK_PARTICLES;
    public static ForgeConfigSpec.BooleanValue KEYFRAME_ALLAY_DANCE;
    public static ForgeConfigSpec.BooleanValue MOVING_STARE_SOUND;
    public static ForgeConfigSpec.BooleanValue ANGER_LOOP_SOUND;
    public static ForgeConfigSpec.BooleanValue UNPASSABLE_RAIL;
    public static ForgeConfigSpec.BooleanValue WARDEN_DYING_ANIMATION;
    public static ForgeConfigSpec.BooleanValue WARDEN_EMERGES_FROM_EGG;
    public static ForgeConfigSpec.BooleanValue WARDEN_EMERGES_FROM_COMMAND;
    public static ForgeConfigSpec.BooleanValue WARDEN_ATTACKS_IMMEDIATELY;
    public static ForgeConfigSpec.BooleanValue SOUL_FIRE_SOUNDS;
    public static ForgeConfigSpec.BooleanValue MC_LIVE_SENSOR_TENDRILS;

    static {
        BUILDER = new ForgeConfigSpec.Builder();

        STONE_CHEST_TIMER = BUILDER.comment("Stone Chest Timer").defineInRange("stoneChestTimer", 100, 0, Integer.MAX_VALUE);
        HORN_LIFE_SPAN = BUILDER.comment("Horn Life Span").defineInRange("hornLifeSpan", 300, 0, Integer.MAX_VALUE);
        HORN_SIZE_MULTIPLIER = BUILDER.comment("Horn Size Multiplier").defineInRange("hornSizeMultiplier", 0, 0, Float.MAX_VALUE);
        HORNS_SHATTERS_GLASS = BUILDER.comment("Horns shatters glass").define("hornsShattersGlass", false);
        HORN_PLAYER_DAMAGE = BUILDER.comment("Horn Player Damage").defineInRange("hornPlayerDamage", 15, 0, Integer.MAX_VALUE);
        HORN_MOB_DAMAGE = BUILDER.comment("Horn Mob Damage").defineInRange("hornMobDamage", 22, 0, Integer.MAX_VALUE);
        HORN_CAN_SUMMON_WARDEN = BUILDER.comment("Horn can summon warden").define("hornCanSummonWarden", true);
        PROJECTILE_BREAK_PARTICLES = BUILDER.comment("Projectile break particles").define("projectileBreakParticles", true);
        KEYFRAME_ALLAY_DANCE = BUILDER.comment("Keyframe allay dance").define("keyframeAllayDance", true);
        MOVING_STARE_SOUND = BUILDER.comment("Moving stare sound").define("movingStareSound", true);
        ANGER_LOOP_SOUND = BUILDER.comment("Anger loop sound").define("angerLoopSound", true);
        UNPASSABLE_RAIL = BUILDER.comment("Unpassable rail").define("unpassableRail", false);
        WARDEN_DYING_ANIMATION = BUILDER.comment("Warden dyning animation").define("wardenDyingAnimation", true);
        WARDEN_EMERGES_FROM_EGG = BUILDER.comment("Warden emerges from egg").define("wardenEmergesFromEgg", false);
        WARDEN_EMERGES_FROM_COMMAND = BUILDER.comment("Warden emerges from command").define("wardenEmergesFromCommand", false);
        WARDEN_ATTACKS_IMMEDIATELY = BUILDER.comment("Warden attacks immediately").define("wardenAttacksImmediately", true);
        SOUL_FIRE_SOUNDS = BUILDER.comment("Soul fire sounds").define("soulFireSounds", true);
        MC_LIVE_SENSOR_TENDRILS = BUILDER.comment("Minecraft Live Sensor Tendrils").define("minecraftLiveSensorTendrils", false);

        COMMON = BUILDER.build();
    }

}
