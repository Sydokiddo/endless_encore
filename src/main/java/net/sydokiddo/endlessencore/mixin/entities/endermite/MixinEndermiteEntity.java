package net.sydokiddo.endlessencore.mixin.entities.endermite;

import net.minecraft.world.Difficulty;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Endermite;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.sydokiddo.endlessencore.effect.ModEffects;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import net.minecraft.world.entity.Entity;

// Endermites can now give players the Vulnerability status effect

@Mixin(Endermite.class)
public abstract class MixinEndermiteEntity extends Monster {

    protected MixinEndermiteEntity(EntityType<? extends Endermite> entityType, Level level) {
        super(entityType, level);
    }

    public boolean doHurtTarget(@NotNull Entity entity) {
        if (super.doHurtTarget(entity)) {
            if (entity instanceof LivingEntity) {
                int i = 0;
                if (this.level.getDifficulty() == Difficulty.NORMAL) {
                    i = 7;
                } else if (this.level.getDifficulty() == Difficulty.HARD) {
                    i = 15;
                }

                if (i > 0) {
                    ((LivingEntity)entity).addEffect(new MobEffectInstance(ModEffects.VULNERABILITY, i * 20, 0), this);
                }
            }

            return true;
        } else {
            return false;
        }
    }
}