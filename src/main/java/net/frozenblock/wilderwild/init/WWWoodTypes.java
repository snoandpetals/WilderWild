package net.frozenblock.wilderwild.init;

import net.frozenblock.wilderwild.WilderWild;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class WWWoodTypes {

    public static WoodType BAOBAB = WoodType.register(WoodType.create(WilderWild.MOD_ID + "baobab"));
    public static WoodType CYPRESS = WoodType.register(WoodType.create(WilderWild.MOD_ID + "cypress"));

    @OnlyIn(Dist.CLIENT)
    public static void init() {
        Sheets.addWoodType(BAOBAB);
        Sheets.addWoodType(CYPRESS);
    }

}