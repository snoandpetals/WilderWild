package net.frozenblock.wilderwild.datafix.wilderwild;

import com.mojang.datafixers.schemas.Schema;
import com.mojang.datafixers.types.templates.TypeTemplate;
import net.frozenblock.wilderwild.misc.WilderSharedConstants;
import net.minecraft.util.datafix.schemas.NamespacedSchema;
import java.util.Map;
import java.util.function.Supplier;

public class WWV18 extends NamespacedSchema {
	public WWV18(int versionKey, Schema parent) {
		super(versionKey, parent);
	}

	@Override
	public Map<String, Supplier<TypeTemplate>> registerBlockEntities(Schema schema) {
		Map<String, Supplier<TypeTemplate>> map = super.registerBlockEntities(schema);

		map.put(WilderSharedConstants.string("metal_chest"), map.remove(WilderSharedConstants.string("stone_chest")));

		return map;
	}
}
