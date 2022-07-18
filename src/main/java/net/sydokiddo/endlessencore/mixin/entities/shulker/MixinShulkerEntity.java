package net.sydokiddo.endlessencore.mixin.entities.shulker;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.AbstractGolem;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.Shulker;
import net.minecraft.world.level.Level;
import net.sydokiddo.endlessencore.effect.ModEffects;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Shulker.class)
public abstract class MixinShulkerEntity extends AbstractGolem implements Enemy {
    protected MixinShulkerEntity(EntityType<? extends Shulker> entityType, Level level) {
        super(entityType, level);
    }

    // Shulkers cannot be affected by Vulnerability

    public boolean canBeAffected(MobEffectInstance mobEffectInstance) {
        return mobEffectInstance.getEffect() != ModEffects.VULNERABILITY && super.canBeAffected(mobEffectInstance);
    }
}