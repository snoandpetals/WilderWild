package net.frozenblock.wilderwild.networking.packet;

import net.fabricmc.fabric.api.networking.v1.FabricPacket;
import net.fabricmc.fabric.api.networking.v1.PacketType;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.frozenblock.wilderwild.misc.WilderSharedConstants;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import org.jetbrains.annotations.NotNull;

public record WilderJellyfishStingPacket(boolean isBaby) implements FabricPacket {

	public static final PacketType<WilderJellyfishStingPacket> PACKET_TYPE = PacketType.create(
			WilderSharedConstants.id("jellyfish_sting_packet"),
			WilderJellyfishStingPacket::new
	);

	public WilderJellyfishStingPacket(@NotNull FriendlyByteBuf buf) {
		this(buf.readBoolean());
	}

	public static void sendTo(ServerPlayer serverPlayer, boolean isBaby) {
		WilderJellyfishStingPacket jellyfishStingPacket = new WilderJellyfishStingPacket(isBaby);
		ServerPlayNetworking.send(serverPlayer, jellyfishStingPacket);
	}

	@Override
	public void write(@NotNull FriendlyByteBuf buf) {
		buf.writeBoolean(this.isBaby());
	}

	@Override
	public PacketType<?> getType() {
		return PACKET_TYPE;
	}
}
