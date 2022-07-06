package net.sydokiddo.endlessencore.util;

import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.Entity;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;

// Player Attack Packets for Sickles

public class PlayerAttackPacket {

    public static final Identifier ATTACK_PACKET = new Identifier("endlessencore", "attack_entity");

    public static Packet<?> attackPacket(Entity entity) {
        PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
        buf.writeInt(entity.getId());
        return ClientPlayNetworking.createC2SPacket(ATTACK_PACKET, buf);
    }

    public static void init() {
        ServerPlayNetworking.registerGlobalReceiver(ATTACK_PACKET, (server, player, handler, buffer, sender) -> {
            int entityId = buffer.readInt();
            server.execute(() -> {
                ((PlayerAccess) player).setOffhandAttack();
                player.updateLastActionTime();

                if (player.world.getEntityById(entityId) != null) {
                    player.attack(player.world.getEntityById(entityId));
                }
            });
        });
    }
}