package net.frozenblock.wilderwild.util;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.levelgen.LegacyRandomSource;
import net.minecraft.world.level.levelgen.SingleThreadedRandomSource;
import net.minecraft.world.level.levelgen.ThreadSafeLegacyRandomSource;
import net.minecraft.world.level.levelgen.XoroshiroRandomSource;
import net.minecraft.world.level.levelgen.synth.ImprovedNoise;
import net.minecraft.world.phys.Vec3;

public final class EasyNoiseSampler {
    public static long seed = 0L;
    public static RandomSource checkedRandom;
    public static RandomSource threadSafeRandom;
    public static RandomSource localRandom;
    public static XoroshiroRandomSource xoroRandom;
    public static ImprovedNoise perlinChecked;
    public static ImprovedNoise perlinThreadSafe;
    public static ImprovedNoise perlinLocal;
    public static ImprovedNoise perlinXoro;

    private EasyNoiseSampler() {
        throw new UnsupportedOperationException("EasyNoiseSampler contains only static declarations.");
    }

    public static double sample(ImprovedNoise sampler, BlockPos pos, double multiplier, boolean multiplyY, boolean useY) {
        if (useY) {
            return multiplyY ? sampler.noise((double)pos.getX() * multiplier, (double)pos.getY() * multiplier, (double)pos.getZ() * multiplier) : sampler.noise((double)pos.getX() * multiplier, (double)pos.getY(), (double)pos.getZ() * multiplier);
        } else {
            return sampler.noise((double)pos.getX() * multiplier, 64.0, (double)pos.getZ() * multiplier);
        }
    }

    public static double samplePositive(ImprovedNoise sampler, BlockPos pos, double multiplier, boolean multiplyY, boolean useY) {
        double ret = 0.0;
        if (useY) {
            if (multiplyY) {
                ret = sampler.noise((double)pos.getX() * multiplier, (double)pos.getY() * multiplier, (double)pos.getZ() * multiplier);
            } else {
                ret = sampler.noise((double)pos.getX() * multiplier, (double)pos.getY(), (double)pos.getZ() * multiplier);
            }
        } else {
            ret = sampler.noise((double)pos.getX() * multiplier, 64.0, (double)pos.getZ() * multiplier);
        }

        return ret < 0.0 ? ret * -1.0 : ret;
    }

    public static Vec3 sampleVec3(ImprovedNoise sampler, double x, double y, double z) {
        double windX = sampler.noise(x, 0.0, 0.0);
        double windY = sampler.noise(0.0, y, 0.0);
        double windZ = sampler.noise(0.0, 0.0, z);
        return new Vec3(windX, windY, windZ);
    }

    public static void setSeed(long newSeed) {
        if (newSeed != seed) {
            seed = newSeed;
            checkedRandom = new LegacyRandomSource(seed);
            threadSafeRandom = new ThreadSafeLegacyRandomSource(seed);
            localRandom = new SingleThreadedRandomSource(seed);
            xoroRandom = new XoroshiroRandomSource(seed);
            perlinChecked = new ImprovedNoise(checkedRandom);
            perlinThreadSafe = new ImprovedNoise(threadSafeRandom);
            perlinLocal = new ImprovedNoise(localRandom);
            perlinXoro = new ImprovedNoise(xoroRandom);
        }

    }

    static {
        checkedRandom = new LegacyRandomSource(seed);
        threadSafeRandom = new ThreadSafeLegacyRandomSource(seed);
        localRandom = new SingleThreadedRandomSource(seed);
        xoroRandom = new XoroshiroRandomSource(seed);
        perlinChecked = new ImprovedNoise(checkedRandom);
        perlinThreadSafe = new ImprovedNoise(threadSafeRandom);
        perlinLocal = new ImprovedNoise(localRandom);
        perlinXoro = new ImprovedNoise(xoroRandom);
    }

}
