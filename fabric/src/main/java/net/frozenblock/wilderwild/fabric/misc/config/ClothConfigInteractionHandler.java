package net.frozenblock.wilderwild.fabric.misc.config;

import net.frozenblock.wilderwild.fabric.WilderWildFabric;

public final class ClothConfigInteractionHandler {

    public static boolean betaBeaches() {
        if (WilderWildFabric.hasCloth) {
            return ClothConfigCloserInteractionHandler.betaBeaches();
        }
        return true;
    }

    /*
        public static boolean modifyDesertPlacement() {
            if (WilderWild.hasCloth) {
                return ClothConfigCloserInteractionHandler.modifyDesertPlacement();
            }
            return true;
        }

        public static boolean modifyBadlandsPlacement() {
            if (WilderWild.hasCloth) {
                return ClothConfigCloserInteractionHandler.modifyBadlandsPlacement();
            }
            return true;
        }
    */
    public static boolean modifyJunglePlacement() {
        if (WilderWildFabric.hasCloth) {
            return ClothConfigCloserInteractionHandler.modifyJunglePlacement();
        }
        return true;
    }

    public static boolean modifySwampPlacement() {
        if (WilderWildFabric.hasCloth) {
            return ClothConfigCloserInteractionHandler.modifySwampPlacement();
        }
        return true;
    }

    public static boolean modifyMangroveSwampPlacement() {
        if (WilderWildFabric.hasCloth) {
            return ClothConfigCloserInteractionHandler.modifyMangroveSwampPlacement();
        }
        return true;
    }

    public static boolean modifyWindsweptSavannaPlacement() {
        if (WilderWildFabric.hasCloth) {
            return ClothConfigCloserInteractionHandler.modifyWindsweptSavannaPlacement();
        }
        return true;
    }

    public static boolean dyingTrees() {
        if (WilderWildFabric.hasCloth) {
            return ClothConfigCloserInteractionHandler.dyingTrees();
        }
        return true;
    }

    public static boolean fallenLogs() {
        if (WilderWildFabric.hasCloth) {
            return ClothConfigCloserInteractionHandler.fallenLogs();
        }
        return true;
    }

    public static boolean wildTrees() {
        if (WilderWildFabric.hasCloth) {
            return ClothConfigCloserInteractionHandler.wildTrees();
        }
        return true;
    }

    public static boolean wildGrass() {
        if (WilderWildFabric.hasCloth) {
            return ClothConfigCloserInteractionHandler.wildGrass();
        }
        return true;
    }

    public static boolean hornShattersGlass() {
        if (WilderWildFabric.hasCloth) {
            return ClothConfigCloserInteractionHandler.hornShattersGlass();
        }
        return false;
    }

    public static boolean projectileBreakParticles() {
        if (WilderWildFabric.hasCloth) {
            return ClothConfigCloserInteractionHandler.projectileBreakParticles();
        }
        return false;
    }

    public static boolean hornCanSummonWarden() {
        if (WilderWildFabric.hasCloth) {
            return ClothConfigCloserInteractionHandler.hornCanSummonWarden();
        }
        return false;
    }

    public static boolean mcLiveSensorTendrils() {
        if (WilderWildFabric.hasCloth) {
            return ClothConfigCloserInteractionHandler.mcLiveSensorTendrils();
        }
        return false;
    }

    public static boolean unpassableRail() {
        if (WilderWildFabric.hasCloth) {
            return ClothConfigCloserInteractionHandler.unpassableRail();
        }
        return true;
    }

    public static boolean wardenCustomTendrils() {
        if (WilderWildFabric.hasCloth) {
            return ClothConfigCloserInteractionHandler.wardenCustomTendrils();
        }
        return true;
    }

    public static boolean wardenDyingAnimation() {
        if (WilderWildFabric.hasCloth) {
            return ClothConfigCloserInteractionHandler.wardenDyingAnimation();
        }
        return true;
    }

    public static boolean wardenEmergesFromEgg() {
        if (WilderWildFabric.hasCloth) {
            return ClothConfigCloserInteractionHandler.wardenEmergesFromEgg();
        }
        return true;
    }

    public static boolean wardenSwimAnimation() {
        if (WilderWildFabric.hasCloth) {
            return ClothConfigCloserInteractionHandler.wardenSwimAnimation();
        }
        return true;
    }

    public static boolean shriekerGargling() {
        if (WilderWildFabric.hasCloth) {
            return ClothConfigCloserInteractionHandler.shriekerGargling();
        }
        return false;
    }

    public static boolean soulFireSounds() {
        if (WilderWildFabric.hasCloth) {
            return ClothConfigCloserInteractionHandler.soulFireSounds();
        }
        return true;
    }

}