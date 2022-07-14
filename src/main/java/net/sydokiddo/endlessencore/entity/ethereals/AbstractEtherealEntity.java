package net.sydokiddo.endlessencore.entity.ethereals;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.DebugPackets;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.ai.util.GoalUtils;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import org.jetbrains.annotations.Nullable;

public abstract class AbstractEtherealEntity extends Monster {

    public AbstractEtherealEntity(EntityType<? extends AbstractEtherealEntity> entityType, Level world) {
        super(entityType, world);
        this.setCanPickUpLoot(true);
        this.setCanPathThroughDoors();
        this.setPathfindingMalus(BlockPathTypes.DANGER_FIRE, 16.0F);
        this.setPathfindingMalus(BlockPathTypes.DAMAGE_FIRE, -1.0F);
    }


    private void setCanPathThroughDoors() {
        if (GoalUtils.hasGroundPathNavigation(this)) {
            ((GroundPathNavigation)this.getNavigation()).setCanOpenDoors(true);
        }

    }

    public void addAdditionalSaveData(CompoundTag nbt) {
        super.addAdditionalSaveData(nbt);
    }

    public double getMyRidingOffset() {
        return this.isBaby() ? -0.05D : -0.45D;
    }

    public void readAdditionalSaveData(CompoundTag nbt) {
        super.readAdditionalSaveData(nbt);
    }

    protected void customServerAiStep() {
        super.customServerAiStep();
    }

    public boolean isAdult() {
        return !this.isBaby();
    }

    @Nullable
    public LivingEntity getTarget() {
        return (LivingEntity)this.brain.getMemory(MemoryModuleType.ATTACK_TARGET).orElse((LivingEntity) null);
    }

    protected boolean isHoldingTool() {
        return this.getMainHandItem().getItem() instanceof TieredItem;
    }

    public void playAmbientSound() {
        super.playAmbientSound();
    }

    protected void sendDebugPackets() {
        super.sendDebugPackets();
        DebugPackets.sendEntityBrain(this);
    }
}
