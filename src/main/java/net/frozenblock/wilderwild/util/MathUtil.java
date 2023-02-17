package net.frozenblock.wilderwild.util;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;

public class MathUtil {

    public static RandomSource random() {
        return RandomSource.create();
    }

    public static boolean squareBetween(int x, int z, int between1, int between2) {
        boolean cond1 = x > between1 && x < between2;
        boolean cond2 = z > between1 && z < between2;
        return cond1 && cond2;
    }

    public static BlockPos offset(BlockPos pos, Direction dir, int a) {
        BlockPos var10000;
        switch (dir) {
            case WEST:
                var10000 = pos.west(a);
                break;
            case EAST:
                var10000 = pos.east(a);
                break;
            case SOUTH:
                var10000 = pos.south(a);
                break;
            case NORTH:
                var10000 = pos.north(a);
                break;
            case UP:
                var10000 = pos.above(a);
                break;
            case DOWN:
                var10000 = pos.below(a);
                break;
            default:
                throw new IncompatibleClassChangeError();
        }

        return var10000;
    }

    public static double randomPosNeg() {
        return random().nextDouble() * (double)(random().nextDouble() >= 0.5 ? 1 : -1);
    }

}
