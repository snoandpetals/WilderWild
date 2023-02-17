package net.frozenblock.wilderwild.events;

import com.google.common.collect.ImmutableMap;
import net.frozenblock.wilderwild.WilderWild;
import net.frozenblock.wilderwild.init.WWBlocks;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.entity.player.BonemealEvent;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = WilderWild.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class MiscEvents {

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
