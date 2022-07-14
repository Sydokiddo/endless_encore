package net.sydokiddo.endlessencore.entity.ethereals;

import net.minecraft.core.BlockPos;
import net.minecraft.entity.*;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.util.VisibleForDebug;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier.Builder;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.CrossbowAttackMob;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.npc.InventoryCarrier;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.sydokiddo.endlessencore.sound.ModSoundEvents;
import org.jetbrains.annotations.Nullable;

public class EtherealEntity extends AbstractEtherealEntity implements CrossbowAttackMob, InventoryCarrier {
    private final SimpleContainer inventory = new SimpleContainer(8);

    public EtherealEntity(EntityType<? extends AbstractEtherealEntity> entityType, Level world) {
        super(entityType, world);
        this.xpReward = 5;
    }

    public void addAdditionalSaveData(CompoundTag nbt) {
        super.addAdditionalSaveData(nbt);
        nbt.put("Inventory", this.inventory.createTag());
    }

    public void readAdditionalSaveData(CompoundTag nbt) {
        super.readAdditionalSaveData(nbt);
        this.inventory.fromTag(nbt.getList("Inventory", 10));
    }

    @VisibleForDebug
    public SimpleContainer getInventory() {
        return this.inventory;
    }

    protected void dropCustomDeathLoot(DamageSource source, int lootingMultiplier, boolean allowDrops) {
        super.dropCustomDeathLoot(source, lootingMultiplier, allowDrops);
        this.inventory.removeAllItems().forEach(this::spawnAtLocation);
    }

    protected ItemStack addItem(ItemStack stack) {
        return this.inventory.addItem(stack);
    }

    protected boolean canInsertIntoInventory(ItemStack stack) {
        return this.inventory.canAddItem(stack);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
    }

    public void onSyncedDataUpdated(EntityDataAccessor<?> data) {
        super.onSyncedDataUpdated(data);
    }

    public static Builder createEtherealAttributes() {
        return Monster.createMonsterAttributes().add(Attributes.MAX_HEALTH, 20.0D).add(Attributes.MOVEMENT_SPEED, 0.3499999940395355D).add(Attributes.ATTACK_DAMAGE, 5.0D);
    }

    @Nullable
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor world, DifficultyInstance difficulty, MobSpawnType spawnReason, @Nullable SpawnGroupData entityData, @Nullable CompoundTag entityNbt) {
        RandomSource random = world.getRandom();
        if (spawnReason != MobSpawnType.STRUCTURE) {
            if (random.nextFloat() < 0.2F) {
                this.setBaby(true);
            } else if (this.isAdult()) {
                this.setItemSlot(EquipmentSlot.MAINHAND, this.makeInitialWeapon());
            }
        }

        this.populateDefaultEquipmentSlots(random, difficulty);
        this.populateDefaultEquipmentEnchantments(random, difficulty);
        return super.finalizeSpawn(world, difficulty, spawnReason, entityData, entityNbt);
    }

    protected boolean shouldDespawnInPeaceful() {
        return false;
    }

    public boolean removeWhenFarAway(double distanceSquared) {
        return !this.isPersistenceRequired();
    }

    protected void populateDefaultEquipmentSlots(RandomSource random, DifficultyInstance localDifficulty) {
        if (this.isAdult()) {
            this.equipAtChance(EquipmentSlot.HEAD, new ItemStack(Items.DIAMOND_HELMET), random);
            this.equipAtChance(EquipmentSlot.CHEST, new ItemStack(Items.DIAMOND_CHESTPLATE), random);
            this.equipAtChance(EquipmentSlot.LEGS, new ItemStack(Items.DIAMOND_LEGGINGS), random);
            this.equipAtChance(EquipmentSlot.FEET, new ItemStack(Items.DIAMOND_BOOTS), random);
        }

    }

    private void equipAtChance(EquipmentSlot slot, ItemStack stack, RandomSource random) {
        if (random.nextFloat() < 0.1F) {
            this.setItemSlot(slot, stack);
        }

    }

    protected float getStandingEyeHeight(Pose pose, EntityDimensions dimensions) {
        return this.isBaby() ? 0.93F : 1.74F;
    }

    public double getPassengersRidingOffset() {
        return (double)this.getBbHeight() * 0.92D;
    }

    public int getExperienceReward() {
        return this.xpReward;
    }

    private ItemStack makeInitialWeapon() {
        return (double)this.random.nextFloat() < 0.5D ? new ItemStack(Items.CROSSBOW) : new ItemStack(Items.DIAMOND_SWORD);
    }

    public void onCrossbowAttackPerformed() {
        this.noActionTime = 0;
    }

    public void performRangedAttack(LivingEntity target, float pullProgress) {
        this.performCrossbowAttack(this, 1.6F);
    }

    @Override
    public void setChargingCrossbow(boolean charging) {
    }

    public void shootCrossbowProjectile(LivingEntity target, ItemStack crossbow, Projectile projectile, float multiShotSpray) {
        this.shootCrossbowProjectile(this, target, projectile, multiShotSpray, 1.6F);
    }

    public boolean canFireProjectileWeapon(ProjectileWeaponItem weapon) {
        return weapon == Items.CROSSBOW;
    }

    protected void equipToMainHand(ItemStack stack) {
        this.setItemSlotAndDropWhenKilled(EquipmentSlot.MAINHAND, stack);
    }

    protected boolean canEquipStack(ItemStack stack) {
        EquipmentSlot equipmentSlot = Mob.getEquipmentSlotForItem(stack);
        ItemStack itemStack = this.getItemBySlot(equipmentSlot);
        return this.canReplaceCurrentItem(stack, itemStack);
    }

    protected SoundEvent getAmbientSound() {
        return ModSoundEvents.ETHEREAL_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource source) {
        return ModSoundEvents.ETHEREAL_HURT;
    }

    protected SoundEvent getDeathSound() {
        return ModSoundEvents.ETHEREAL_DEATH;
    }

    protected void playStepSound(BlockPos pos, BlockState state) {
        this.playSound(SoundEvents.PIGLIN_STEP, 0.15F, 1.0F);
    }

    protected void playSound(SoundEvent sound) {
        this.playSound(sound, this.getSoundVolume(), this.getVoicePitch());
    }
}
