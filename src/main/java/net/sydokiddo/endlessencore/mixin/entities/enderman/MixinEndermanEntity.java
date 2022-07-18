package net.sydokiddo.endlessencore.mixin.entities.enderman;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.NeutralMob;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.sydokiddo.endlessencore.effect.ModEffects;
import org.spongepowered.asm.mixin.Mixin;

@SuppressWarnings("ALL")

@Mixin(EnderMan.class)
public abstract class MixinEndermanEntity extends Monster implements NeutralMob {
    protected MixinEndermanEntity(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
    }

    // Endermen cannot be affected by Vulnerability

    public boolean canBeAffected(MobEffectInstance mobEffectInstance) {
        return mobEffectInstance.getEffect() == ModEffects.VULNERABILITY ? false : super.canBeAffected(mobEffectInstance);
    }
}