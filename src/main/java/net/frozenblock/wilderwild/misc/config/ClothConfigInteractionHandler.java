package net.frozenblock.wilderwild.misc.config;

import net.frozenblock.wilderwild.WilderWild;

public class ClothConfigInteractionHandler {

    public static boolean modifyDesertPlacement() {
        if (WilderWild.hasClothConfig()) {
            return ClothConfigCloserInteractionHandler.modifyDesertPlacement();
        }
        return true;
    }

    public static boolean modifyBadlandsPlacement() {
        if (WilderWild.hasClothConfig()) {
            return ClothConfigCloserInteractionHandler.modifyBadlandsPlacement();
        }
        return true;
    }

    public static boolean modifyJunglePlacement() {
        if (WilderWild.hasClothConfig()) {
            return ClothConfigCloserInteractionHandler.modifyJunglePlacement();
        }
        return true;
    }

    public static boolean modifySwampPlacement() {
        if (WilderWild.hasClothConfig()) {
            return ClothConfigCloserInteractionHandler.modifySwampPlacement();
        }
        return true;
    }

    public static boolean modifyMangroveSwampPlacement() {
        if (WilderWild.hasClothConfig()) {
            return ClothConfigCloserInteractionHandler.modifyMangroveSwampPlacement();
        }
        return true;
    }

    public static boolean modifyWindsweptSavannaPlacement() {
        if (WilderWild.hasClothConfig()) {
            return ClothConfigCloserInteractionHandler.modifyWindsweptSavannaPlacement();
        }
        return true;
    }

    public static boolean mcLiveSensorTendrils() {
        if (WilderWild.hasClothConfig()) {
            return ClothConfigCloserInteractionHandler.mcLiveSensorTendrils();
        }
        return false;
    }

    public static boolean betaBeaches() {
        if (WilderWild.hasClothConfig()) {
            return ClothConfigCloserInteractionHandler.betaBeaches();
        }
        return true;
    }

    public static boolean wardenEmergesFromEgg() {
        if (WilderWild.hasClothConfig()) {
            return ClothConfigCloserInteractionHandler.wardenEmergesFromEgg();
        }
        return true;
    }

    public static boolean customWardenTendrils() {
        if (WilderWild.hasClothConfig()) {
            return ClothConfigCloserInteractionHandler.customWardenTendrils();
        }
        return true;
    }

    public static boolean wardenSwimAnimation() {
        if (WilderWild.hasClothConfig()) {
            return ClothConfigCloserInteractionHandler.wardenSwimAnimation();
        }
        return true;
    }

    public static boolean shriekerGargling() {
        if (WilderWild.hasClothConfig()) {
            return ClothConfigCloserInteractionHandler.shriekerGargling();
        }
        return false;
    }

    public static boolean hornShattersGlass() {
        if (WilderWild.hasClothConfig()) {
            return ClothConfigCloserInteractionHandler.hornShattersGlass();
        }
        return false;
    }

    public static boolean fallenLogs() {
        if (WilderWild.hasClothConfig()) {
            return ClothConfigCloserInteractionHandler.fallenLogs();
        }
        return true;
    }

    public static boolean wildTrees() {
        if (WilderWild.hasClothConfig()) {
            return ClothConfigCloserInteractionHandler.wildTrees();
        }
        return true;
    }

}