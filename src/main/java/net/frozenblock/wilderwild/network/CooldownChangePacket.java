package net.frozenblock.wilderwild.network;

import net.frozenblock.wilderwild.util.interfaces.CooldownInterface;
import net.minecraft.client.Minecraft;
import net.minecraft.core.Registry;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.item.Item;
import net.minecraftforge.network.NetworkEvent;

import java.util.Optional;
import java.util.function.Supplier;

public class CooldownChangePacket {
    private final Item item;
    private final int additional;

    public CooldownChangePacket(Item item, int additional) {
        this.item = item;
        this.additional = additional;
    }

    public static void write(CooldownChangePacket packet, FriendlyByteBuf buf) {
        buf.writeId(Registry.ITEM, packet.item);
        buf.writeVarInt(packet.additional);
    }

    public static CooldownChangePacket read(FriendlyByteBuf buf) {
        return new CooldownChangePacket(buf.readById(Registry.ITEM), buf.readVarInt());
    }

    public static void handle(CooldownChangePacket packet, Supplier<NetworkEvent.Context> ctx) {
        Item item = packet.item;
        int additional = packet.additional;
        Minecraft client = Minecraft.getInstance();
        ctx.get().enqueueWork(() -> {
            Optional.ofNullable(client.level).ifPresent(world -> {
                if (client.player != null) {
                    ((CooldownInterface)client.player.getCooldowns()).changeCooldown(item, additional);
                }
            });
        });
        ctx.get().setPacketHandled(true);
    }

}
