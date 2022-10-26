package net.frozenblock.wilderwild.mixin;

import net.caffeinemc.caffeineconfig.AbstractCaffeineConfigMixinPlugin;
import net.caffeinemc.caffeineconfig.CaffeineConfig;
import net.fabricmc.loader.api.FabricLoader;
import net.frozenblock.wilderwild.misc.WilderSharedConstants;

public class WilderWildMixinConfigPlugin extends AbstractCaffeineConfigMixinPlugin {

	private static final String MIXIN_PACKAGE_ROOT = "net.frozenblock.wilderwild.mixin";
	private static final String MIXIN_CONFIG_FILE = "wilderwild.mixins.properties";

	@Override
	protected CaffeineConfig createConfig() {
		return CaffeineConfig.builder(WilderSharedConstants.MOD_ID)
				.addMixinOption("client.easter", true)
				.addMixinOption("client.allay", true)
				.addMixinOption("client.liquid_block_renderer", true)
				.addMixinOption("client.warden", true)
				.addMixinOption("server.dripleaf_powering", true)
				.addMixinOption("server.enderman_sound", true)
				.addMixinOption("server.instrument_sound", true)
				.addMixinOption("server.projectile_particle", true)
				.addMixinOption("server.reinforced_deepslate_pillar", true)
				.addMixinOption("server.sculk_spreading", true)
				.addMixinOption("server.slime_algae_spawn", true)
				.addMixinOption("server.sonic_boom", true)
				.addOptionDependency("server.allay", "client.allay", true)
				.addOptionDependency("server.easter", "client.easter", true)
				.build(FabricLoader.getInstance().getConfigDir().resolve(MIXIN_CONFIG_FILE));
	}

	@Override
	protected String mixinPackageRoot() {
		return MIXIN_PACKAGE_ROOT;
	}
}
