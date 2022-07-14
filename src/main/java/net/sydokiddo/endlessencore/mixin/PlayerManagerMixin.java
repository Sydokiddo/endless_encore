package net.sydokiddo.endlessencore.mixin;

import com.mojang.logging.LogUtils;
import net.minecraft.network.Connection;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.players.PlayerList;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.At;

// Puts a message in the server console to let the user know if a player has the mod installed

@Mixin(PlayerList.class)
public class PlayerManagerMixin {

    @Shadow
    @Mutable
    @Final
    private static final Logger LOGGER = LogUtils.getLogger();

    @Inject(method = "onPlayerConnect", at = @At(value = "TAIL"))
    public void onPlayerConnectMixin(Connection connection, ServerPlayer player, CallbackInfo info) {
        LOGGER.info(player.getName().getString() + " has Endless Encore installed");
    }
}