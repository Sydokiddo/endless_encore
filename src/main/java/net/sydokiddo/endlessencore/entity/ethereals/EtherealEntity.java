package net.sydokiddo.endlessencore.entity.ethereals;

import EntityData;
import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer.Builder;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.RangedWeaponItem;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.annotation.Debug;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import net.sydokiddo.endlessencore.sound.ModSoundEvents;
import org.jetbrains.annotations.Nullable;

public class EtherealEntity extends AbstractEtherealEntity implements CrossbowUser, InventoryOwner {
    private final SimpleInventory inventory = new SimpleInventory(8);

    public EtherealEntity(EntityType<? extends AbstractEtherealEntity> entityType, World world) {
        super(entityType, world);
        this.experiencePoints = 5;
    }

    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.put("Inventory", this.inventory.toNbtList());
    }

    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.inventory.readNbtList(nbt.getList("Inventory", 10));
    }

    @Debug
    public SimpleInventory getInventory() {
        return this.inventory;
    }

    protected void dropEquipment(DamageSource source, int lootingMultiplier, boolean allowDrops) {
        super.dropEquipment(source, lootingMultiplier, allowDrops);
        this.inventory.clearToList().forEach(this::dropStack);
    }

    protected ItemStack addItem(ItemStack stack) {
        return this.inventory.addStack(stack);
    }

    protected boolean canInsertIntoInventory(ItemStack stack) {
        return this.inventory.canInsert(stack);
    }

    protected void initDataTracker() {
        super.initDataTracker();
    }

    public void onTrackedDataSet(TrackedData<?> data) {
        super.onTrackedDataSet(data);
    }

    public static Builder createEtherealAttributes() {
        return HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 20.0D).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3499999940395355D).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 5.0D);
    }

    @Nullable
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData, @Nullable NbtCompound entityNbt) {
        Random random = world.getRandom();
        if (spawnReason != SpawnReason.STRUCTURE) {
            if (random.nextFloat() < 0.2F) {
                this.setBaby(true);
            } else if (this.isAdult()) {
                this.equipStack(EquipmentSlot.MAINHAND, this.makeInitialWeapon());
            }
        }

        this.initEquipment(random, difficulty);
        this.updateEnchantments(random, difficulty);
        return super.initialize(world, difficulty, spawnReason, entityData, entityNbt);
    }

    protected boolean isDisallowedInPeaceful() {
        return false;
    }

    public boolean canImmediatelyDespawn(double distanceSquared) {
        return !this.isPersistent();
    }

    protected void initEquipment(Random random, LocalDifficulty localDifficulty) {
        if (this.isAdult()) {
            this.equipAtChance(EquipmentSlot.HEAD, new ItemStack(Items.DIAMOND_HELMET), random);
            this.equipAtChance(EquipmentSlot.CHEST, new ItemStack(Items.DIAMOND_CHESTPLATE), random);
            this.equipAtChance(EquipmentSlot.LEGS, new ItemStack(Items.DIAMOND_LEGGINGS), random);
            this.equipAtChance(EquipmentSlot.FEET, new ItemStack(Items.DIAMOND_BOOTS), random);
        }

    }

    private void equipAtChance(EquipmentSlot slot, ItemStack stack, Random random) {
        if (random.nextFloat() < 0.1F) {
            this.equipStack(slot, stack);
        }

    }

    protected float getActiveEyeHeight(EntityPose pose, EntityDimensions dimensions) {
        return this.isBaby() ? 0.93F : 1.74F;
    }

    public double getMountedHeightOffset() {
        return (double)this.getHeight() * 0.92D;
    }

    public int getXpToDrop() {
        return this.experiencePoints;
    }

    private ItemStack makeInitialWeapon() {
        return (double)this.random.nextFloat() < 0.5D ? new ItemStack(Items.CROSSBOW) : new ItemStack(Items.DIAMOND_SWORD);
    }

    public void postShoot() {
        this.despawnCounter = 0;
    }

    public void attack(LivingEntity target, float pullProgress) {
        this.shoot(this, 1.6F);
    }

    @Override
    public void setCharging(boolean charging) {
    }

    public void shoot(LivingEntity target, ItemStack crossbow, ProjectileEntity projectile, float multiShotSpray) {
        this.shoot(this, target, projectile, multiShotSpray, 1.6F);
    }

    public boolean canUseRangedWeapon(RangedWeaponItem weapon) {
        return weapon == Items.CROSSBOW;
    }

    protected void equipToMainHand(ItemStack stack) {
        this.equipLootStack(EquipmentSlot.MAINHAND, stack);
    }

    protected boolean canEquipStack(ItemStack stack) {
        EquipmentSlot equipmentSlot = MobEntity.getPreferredEquipmentSlot(stack);
        ItemStack itemStack = this.getEquippedStack(equipmentSlot);
        return this.prefersNewEquipment(stack, itemStack);
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
        this.playSound(SoundEvents.ENTITY_PIGLIN_STEP, 0.15F, 1.0F);
    }

    protected void playSound(SoundEvent sound) {
        this.playSound(sound, this.getSoundVolume(), this.getSoundPitch());
    }
}
