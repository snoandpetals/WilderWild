package net.frozenblock.wilderwild.events;

import com.google.common.collect.ImmutableMap;
import net.frozenblock.wilderwild.WilderWild;
import net.frozenblock.wilderwild.init.WWBlocks;
import net.frozenblock.wilderwild.init.WWItemTags;
import net.frozenblock.wilderwild.init.WWItems;
import net.minecraft.Util;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.TagEntry;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.entity.player.BonemealEvent;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = WilderWild.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class MiscEvents {

    @SubscribeEvent
    public void onLootTableLoad(LootTableLoadEvent event) {
        ResourceLocation name = event.getName();
        if (name.equals(BuiltInLootTables.ANCIENT_CITY)) {
            LootPool.Builder pool = LootPool.lootPool()
                    .add(LootItem.lootTableItem(WWItems.ANCIENT_HORN_FRAGMENT.get()).setWeight(2).setQuality(Rarity.EPIC.ordinal() + 1)).
                    apply(SetItemCountFunction.setCount(UniformGenerator.between(-0.5F, 1.15F)));
            event.getTable().addPool(pool.build());
        }
        if (name.equals(BuiltInLootTables.SHIPWRECK_SUPPLY)) {
            LootPool.Builder pool = LootPool.lootPool()
                    .add(LootItem.lootTableItem(WWBlocks.ALGAE.get().asItem()).setWeight(5).setQuality(Rarity.COMMON.ordinal() + 1)).apply(SetItemCountFunction.setCount(UniformGenerator.between(-1.0F, 1.0F)));
            event.getTable().addPool(pool.build());
        }
        if (name.equals(BuiltInLootTables.VILLAGE_SAVANNA_HOUSE)) {
            LootPool.Builder pool = LootPool.lootPool()
                    .add(LootItem.lootTableItem(WWItems.BAOBAB_NUT.get()).setWeight(2).setQuality(Rarity.COMMON.ordinal() + 1)).apply(SetItemCountFunction.setCount(UniformGenerator.between(-1.0F, 1.0F)));
            event.getTable().addPool(pool.build());
        }
        if (name.equals(BuiltInLootTables.VILLAGE_SAVANNA_HOUSE)) {
            LootPool.Builder pool = LootPool.lootPool()
                    .add(LootItem.lootTableItem(WWBlocks.BAOBAB_LOG.get().asItem()).setWeight(2).setQuality(Rarity.COMMON.ordinal() + 1)).apply(SetItemCountFunction.setCount(UniformGenerator.between(-1.0F, 1.0F)));

            event.getTable().addPool(pool.build());
        }
        if (EntityType.GOAT.getDefaultLootTable().equals(name)) {
            LootPool.Builder pool = LootPool.lootPool().add(TagEntry.expandTag(WWItemTags.GOAT_DROP_MUSIC_DISCS)).when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.KILLER, EntityPredicate.Builder.entity().of(EntityTypeTags.SKELETONS)));
            event.getTable().addPool(pool.build());
        }
    }

    @SubscribeEvent
    public void onBonemeal(BonemealEvent event) {
        Level world = event.getLevel();
        BlockPos blockPos = event.getPos();
        if (!world.isClientSide && event.getBlock().is(Blocks.LILY_PAD)) {
            world.levelEvent(1505, blockPos, 0);
            world.setBlockAndUpdate(blockPos, WWBlocks.FLOWERING_LILY_PAD.get().defaultBlockState());
            event.getEntity().swing(event.getEntity().getUsedItemHand());
        }
    }

    @SubscribeEvent
    public void onFuelBurn(FurnaceFuelBurnTimeEvent event) {
        Util.make(ImmutableMap.<Item, Integer>builder(), map -> {
            map.put(WWBlocks.BAOBAB_FENCE.get().asItem(), 300);
            map.put(WWBlocks.BAOBAB_FENCE_GATE.get().asItem(), 300);
            map.put(WWBlocks.CYPRESS_FENCE.get().asItem(), 300);
            map.put(WWBlocks.CYPRESS_FENCE_GATE.get().asItem(), 300);
        }).build().forEach((item, integer) -> {
            if (event.getItemStack().is(item)) {
                event.setBurnTime(integer);
            }
        });
    }

}
