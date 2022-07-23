package net.sydokiddo.endlessencore.mixin.elytra_tweaks;

import net.minecraft.client.model.AgeableListModel;
import net.minecraft.client.model.ElytraModel;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.world.entity.LivingEntity;
import net.sydokiddo.endlessencore.EndlessEncore;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

// Fixes stretched UVs on the Elytra model

@Mixin(ElytraModel.class)
public abstract class MixinElytraEntityModel<T extends LivingEntity> extends AgeableListModel<T> {

    @ModifyArg(method = "createLayer", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/model/geom/builders/CubeListBuilder;addBox(FFFFFFLnet/minecraft/client/model/geom/builders/CubeDeformation;)Lnet/minecraft/client/model/geom/builders/CubeListBuilder;"), index = 6)
    private static CubeDeformation ETF_injected(CubeDeformation dilation) {
        if (EndlessEncore.getConfig().elytra_changes.fixed_elytra_uvs) {
            return new CubeDeformation(1, 1, 0.2f);
        }
        return dilation;
    }
}