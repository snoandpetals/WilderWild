package net.frozenblock.wilderwild.mixin.server;

import io.netty.buffer.Unpooled;
import net.frozenblock.wilderwild.init.WWNetwork;
import net.frozenblock.wilderwild.mixin.CooldownInstanceAccessor;
import net.frozenblock.wilderwild.network.CooldownChangePacket;
import net.frozenblock.wilderwild.util.interfaces.CooldownInterface;
import net.minecraft.core.Registry;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemCooldowns;
import net.minecraft.world.item.ServerItemCooldowns;
import net.minecraftforge.network.PacketDistributor;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(ServerItemCooldowns.class)
public class ServerItemCooldownsMixin extends ItemCooldowns implements CooldownInterface {
    @Shadow
    @Final
    private ServerPlayer player;

    public ServerItemCooldownsMixin() {
    }

    @Unique
    public void changeCooldown(Item item, int additional) {
        if (this.cooldowns.containsKey(item)) {
            ItemCooldowns.CooldownInstance cooldown = this.cooldowns.get(item);
            this.cooldowns.put(item, CooldownInstanceAccessor.createCooldownInstance(cooldown.startTime, cooldown.endTime + additional));
            this.onCooldownChanged(item, additional);
        }

    }

    @Unique
    public void onCooldownChanged(Item item, int additional) {
        FriendlyByteBuf byteBuf = new FriendlyByteBuf(Unpooled.buffer());
        byteBuf.writeId(Registry.ITEM, item);
        byteBuf.writeVarInt(additional);
        WWNetwork.INSTANCE.send(PacketDistributor.PLAYER.with(() -> this.player), new CooldownChangePacket(item, additional));
    }
}
