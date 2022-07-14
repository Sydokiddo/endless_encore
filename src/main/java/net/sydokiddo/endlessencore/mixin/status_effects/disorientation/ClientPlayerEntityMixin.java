package net.sydokiddo.endlessencore.mixin.status_effects.disorientation;

import com.mojang.authlib.GameProfile;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.player.Input;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.player.ProfilePublicKey;
import net.sydokiddo.endlessencore.effect.ModEffects;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

// Inverts the player's controls if they have the Disorientation status effect applied

@Mixin(LocalPlayer.class)
public abstract class ClientPlayerEntityMixin extends AbstractClientPlayer {

    @Shadow
    public Input input;

    public ClientPlayerEntityMixin(ClientLevel world, GameProfile profile, @Nullable ProfilePublicKey publicKey) {
        super(world, profile, publicKey);
    }

    @Inject(method = "aiStep", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/tutorial/Tutorial;onInput(Lnet/minecraft/client/player/Input;)V"))
    void injectTickNewAi(CallbackInfo ci) {
        if (getActiveEffectsMap().get(ModEffects.DISORIENTATION) != null) {
            this.input.leftImpulse *= -1;
            this.input.forwardImpulse *= -1;
        }
    }
}