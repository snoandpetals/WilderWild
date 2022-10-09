package net.frozenblock.wilderwild.fabric.misc.mod_compat.terrablender;

import net.frozenblock.wilderwild.fabric.WilderWildFabric;
import terrablender.api.Regions;
import terrablender.api.TerraBlenderApi;

public final class BlenderInitializer implements TerraBlenderApi {

    @Override
    public void onTerraBlenderInitialized() {
        // Set the weight to the number of biomes added by the mod.
        Regions.register(new WilderOverworldRegion(WilderWildFabric.id("overworld"), 3));

        // Register our surface rules
        //SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, WilderWild.MOD_ID, SharedWorldgen.surfaceRules());
    }
}
