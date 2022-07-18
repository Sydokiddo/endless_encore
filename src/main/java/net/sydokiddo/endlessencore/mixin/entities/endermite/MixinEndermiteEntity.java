package net.sydokiddo.endlessencore.mixin.entities.endermite;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.IndirectEntityDamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.monster.Endermite;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.projectile.ThrownPotion;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.Vec3;
import net.sydokiddo.endlessencore.effect.ModEffects;
import net.sydokiddo.endlessencore.sound.ModSoundEvents;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import java.util.List;

@SuppressWarnings("ALL")

@Mixin(Endermite.class)
public abstract class MixinEndermiteEntity extends Monster {
    private final int targetChangeTime;
    private static final float ENDERMITE_SPECIAL_EFFECT_CHANCE = 0.1F;

    protected MixinEndermiteEntity(EntityType<? extends Endermite> entityType, Level level, int targetChangeTime) {
        super(entityType, level);
        this.targetChangeTime = targetChangeTime;
        this.setPathfindingMalus(BlockPathTypes.WATER, -1.0F);
    }
    // Endermites can now give players the Vulnerability status effect

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

    // Endermites are now hurt by water

    public boolean isSensitiveToWater() {
        return true;
    }

    // Endermite Teleportation

    protected boolean teleport() {
        if (!this.level.isClientSide() && this.isAlive()) {
            double d = this.getX() + (this.random.nextDouble() - 0.5D) * 64.0D;
            double e = this.getY() + (double)(this.random.nextInt(64) - 32);
            double f = this.getZ() + (this.random.nextDouble() - 0.5D) * 64.0D;
            return this.teleport(d, e, f);
        } else {
            return false;
        }
    }

    private boolean teleport(double d, double e, double f) {
        BlockPos.MutableBlockPos mutableBlockPos = new BlockPos.MutableBlockPos(d, e, f);

        while(mutableBlockPos.getY() > this.level.getMinBuildHeight() && !this.level.getBlockState(mutableBlockPos).getMaterial().blocksMotion()) {
            mutableBlockPos.move(Direction.DOWN);
        }

        BlockState blockState = this.level.getBlockState(mutableBlockPos);
        boolean bl = blockState.getMaterial().blocksMotion();
        boolean bl2 = blockState.getFluidState().is(FluidTags.WATER);
        if (bl && !bl2) {
            Vec3 vec3 = this.position();
            boolean bl3 = this.randomTeleport(d, e, f, true);
            if (bl3) {
                this.level.gameEvent(GameEvent.TELEPORT, vec3, GameEvent.Context.of(this));
                if (!this.isSilent()) {
                    this.level.playSound(null, this.xo, this.yo, this.zo, SoundEvents.ENDERMAN_TELEPORT, this.getSoundSource(), 1.0F, 1.0F);
                    this.playSound(SoundEvents.ENDERMAN_TELEPORT, 1.0F, 1.0F);
                }
            }

            return bl3;
        } else {
            return false;
        }
    }

    private boolean hurtWithCleanWater(DamageSource damageSource, ThrownPotion thrownPotion, float f) {
        ItemStack itemStack = thrownPotion.getItem();
        Potion potion = PotionUtils.getPotion(itemStack);
        List<MobEffectInstance> list = PotionUtils.getMobEffects(itemStack);
        boolean bl = potion == Potions.WATER && list.isEmpty();
        return bl && super.hurt(damageSource, f);
    }

    public boolean hurt(@NotNull DamageSource damageSource, float f) {
        if (this.isInvulnerableTo(damageSource)) {
            return false;
        } else if (damageSource instanceof IndirectEntityDamageSource) {
            Entity entity = damageSource.getDirectEntity();
            boolean bl;
            if (entity instanceof ThrownPotion) {
                bl = this.hurtWithCleanWater(damageSource, (ThrownPotion)entity, f);
            } else {
                bl = false;
            }

            for(int i = 0; i < 64; ++i) {
                if (this.teleport()) {
                    return true;
                }
            }

            return bl;
        } else {
            boolean bl2 = super.hurt(damageSource, f);
            if (!this.level.isClientSide() && !(damageSource.getEntity() instanceof LivingEntity) && this.random.nextInt(10) != 0) {
                this.teleport();
            }

            return bl2;
        }
    }

    // Avoid Water Goal

    @Inject(method = "registerGoals", at = @At("TAIL"))
    protected void registerGoals(CallbackInfo ci) {
        this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1.0D, 0.0F));
    }

    // New Endermite Sounds

    @Override
    public SoundEvent getAmbientSound() {
        return ModSoundEvents.ENDERMITE_AMBIENT;
    }

    @Override
    public SoundEvent getHurtSound(DamageSource damageSource) {
        return ModSoundEvents.ENDERMITE_HURT;
    }

    @Override
    public SoundEvent getDeathSound() {
        return ModSoundEvents.ENDERMITE_DEATH;
    }

    @Override
    public void playStepSound(BlockPos blockPos, BlockState blockState) {
        this.playSound(SoundEvents.ENDERMITE_STEP, 0.15F, 1.0F);
    }

    // Endermites can now spawn with random effects

    public static class EndermiteEffectsGroupData implements SpawnGroupData {
        @Nullable
        public MobEffect effect;

        public EndermiteEffectsGroupData() {
        }

        public void setRandomEffect(RandomSource randomSource) {
            int i = randomSource.nextInt(5);
            if (i <= 1) {
                this.effect = MobEffects.MOVEMENT_SPEED;
            } else if (i <= 2) {
                this.effect = MobEffects.DAMAGE_BOOST;
            } else if (i <= 3) {
                this.effect = MobEffects.REGENERATION;
            }
        }
    }

    // Endermites cannot be affected by Vulnerability

    public boolean canBeAffected(MobEffectInstance mobEffectInstance) {
        return mobEffectInstance.getEffect() == ModEffects.VULNERABILITY ? false : super.canBeAffected(mobEffectInstance);
    }
}