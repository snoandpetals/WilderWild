package net.frozenblock.wilderwild.init;

import net.frozenblock.wilderwild.WilderWild;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Instrument;

public class WWInstruments {

    public static final ResourceKey<Instrument> ANCIENT_HORN_INSTRUMENT = ResourceKey.create(Registry.INSTRUMENT_REGISTRY, WilderWild.id("ancient_horn"));

    public static final ResourceKey<Instrument> SAX_COPPER_HORN = ResourceKey.create(Registry.INSTRUMENT_REGISTRY, WilderWild.id("sax_copper_horn"));
    public static final ResourceKey<Instrument> TUBA_COPPER_HORN = ResourceKey.create(Registry.INSTRUMENT_REGISTRY, WilderWild.id("tuba_copper_horn"));
    public static final ResourceKey<Instrument> FLUTE_COPPER_HORN = ResourceKey.create(Registry.INSTRUMENT_REGISTRY, WilderWild.id("flute_copper_horn"));
    public static final ResourceKey<Instrument> OBOE_COPPER_HORN = ResourceKey.create(Registry.INSTRUMENT_REGISTRY, WilderWild.id("oboe_copper_horn"));
    public static final ResourceKey<Instrument> CLARINET_COPPER_HORN = ResourceKey.create(Registry.INSTRUMENT_REGISTRY, WilderWild.id("clarinet_copper_horn"));
    public static final ResourceKey<Instrument> TRUMPET_COPPER_HORN = ResourceKey.create(Registry.INSTRUMENT_REGISTRY, WilderWild.id("trumpet_copper_horn"));
    public static final ResourceKey<Instrument> TROMBONE_COPPER_HORN = ResourceKey.create(Registry.INSTRUMENT_REGISTRY, WilderWild.id("trombone_copper_horn"));

    public static void init() {
        Registry.register(Registry.INSTRUMENT, ANCIENT_HORN_INSTRUMENT, new Instrument(WWSoundEvents.ITEM_ANCIENT_HORN_CALL.get(), 300, 256.0F));
        Registry.register(Registry.INSTRUMENT, SAX_COPPER_HORN, new Instrument(WWSoundEvents.ITEM_COPPER_HORN_SAX_LOOP.get(), 32767, 64.0F));
        Registry.register(Registry.INSTRUMENT, TUBA_COPPER_HORN, new Instrument(WWSoundEvents.ITEM_COPPER_HORN_TUBA_LOOP.get(), 32767, 64.0F));
        Registry.register(Registry.INSTRUMENT, FLUTE_COPPER_HORN, new Instrument(WWSoundEvents.ITEM_COPPER_HORN_FLUTE_LOOP.get(), 32767, 64.0F));
        Registry.register(Registry.INSTRUMENT, OBOE_COPPER_HORN, new Instrument(WWSoundEvents.ITEM_COPPER_HORN_OBOE_LOOP.get(), 32767, 64.0F));
        Registry.register(Registry.INSTRUMENT, CLARINET_COPPER_HORN, new Instrument(WWSoundEvents.ITEM_COPPER_HORN_CLARINET_LOOP.get(), 32767, 64.0F));
        Registry.register(Registry.INSTRUMENT, TRUMPET_COPPER_HORN, new Instrument(WWSoundEvents.ITEM_COPPER_HORN_TRUMPET_LOOP.get(), 32767, 64.0F));
        Registry.register(Registry.INSTRUMENT, TROMBONE_COPPER_HORN, new Instrument(WWSoundEvents.ITEM_COPPER_HORN_TROMBONE_LOOP.get(), 32767, 64.0F));
    }

}
