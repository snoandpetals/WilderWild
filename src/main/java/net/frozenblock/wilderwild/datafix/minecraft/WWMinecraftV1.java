package net.frozenblock.wilderwild.datafix.minecraft;

import com.mojang.datafixers.DSL;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.datafixers.types.templates.TypeTemplate;
import net.frozenblock.wilderwild.misc.WilderSharedConstants;
import net.minecraft.util.datafix.fixes.References;
import net.minecraft.util.datafix.schemas.NamespacedSchema;
import net.minecraft.util.datafix.schemas.V100;
import java.util.Map;
import java.util.function.Supplier;

public class WWMinecraftV1 extends NamespacedSchema {
	public WWMinecraftV1(int versionKey, Schema parent) {
		super(versionKey, parent);
	}

	@Override
	public Map<String, Supplier<TypeTemplate>> registerBlockEntities(Schema schema) {
		var map = super.registerBlockEntities(schema);
		schema.register(
			map,
			WilderSharedConstants.string("display_lantern"),
			() -> DSL.optionalFields("Items", DSL.list(References.ITEM_STACK.in(schema)))
		);
		schema.register(
			map,
			WilderSharedConstants.string("hanging_tendril"),
			DSL::remainder
		);
		schema.register(
			map,
			WilderSharedConstants.string("scorched_block"),
			DSL::remainder
		);
		schema.register(
			map,
			WilderSharedConstants.string("stone_chest"),
			() -> DSL.optionalFields("Items", DSL.list(References.ITEM_STACK.in(schema)))
		);
		schema.register(
			map,
			WilderSharedConstants.string("termite_mound"),
			DSL::remainder
		);
		schema.register(
			map,
			WilderSharedConstants.string("geyser"),
			DSL::remainder
		);
		return map;
	}

	@Override
	public Map<String, Supplier<TypeTemplate>> registerEntities(Schema schema) {
		var map = super.registerEntities(schema);
		schema.register(
			map,
			WilderSharedConstants.string("jellyfish"),
			() -> V100.equipment(schema)
		);
		schema.register(
			map,
			WilderSharedConstants.string("ostrich"),
			() -> V100.equipment(schema)
		);
		schema.register(
			map,
			WilderSharedConstants.string("crab"),
			() -> V100.equipment(schema)
		);
		schema.register(
			map,
			WilderSharedConstants.string("firefly"),
			() -> V100.equipment(schema)
		);
		schema.register(
			map,
			WilderSharedConstants.string("tumbleweed"),
			(string) -> DSL.optionalFields("Items", References.ITEM_STACK.in(schema), V100.equipment(schema))
		);
		schema.register(
			map,
			WilderSharedConstants.string("ancient_horn_vibration"),
			DSL::remainder
		);
		schema.register(
			map,
			WilderSharedConstants.string("coconut"),
			DSL::remainder
		);
		schema.register(
			map,
			WilderSharedConstants.string("chest_bubbler"),
			DSL::remainder
		);
		schema.register(
			map,
			WilderSharedConstants.string("sculk_spreader"),
			DSL::remainder
		);
		schema.register(
			map,
			WilderSharedConstants.string("scorched"),
			() -> V100.equipment(schema)
		);
		return map;
	}
}
