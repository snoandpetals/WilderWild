package net.frozenblock.wilderwild.misc.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import net.frozenblock.lib.FrozenBools;
import net.minecraft.client.gui.screens.Screen;
import org.quiltmc.loader.api.minecraft.ClientOnly;

@ClientOnly
public final class ModMenuIntegration implements ModMenuApi {

    @Override
    public ConfigScreenFactory<Screen> getModConfigScreenFactory() {
        if (FrozenBools.hasCloth) {
            return ClientOnlyConfigInteractionHandler.buildScreen();
        }
        return (screen -> null);
    }

}
