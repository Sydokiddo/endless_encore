package net.sydokiddo.endlessencore.entity.ethereals;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.NavigationConditions;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.ai.pathing.MobNavigation;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.entity.mob.*;
import net.minecraft.item.ToolItem;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.DebugInfoSender;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public abstract class AbstractEtherealEntity extends HostileEntity {

    public AbstractEtherealEntity(EntityType<? extends AbstractEtherealEntity> entityType, World world) {
        super(entityType, world);
        this.setCanPickUpLoot(true);
        this.setCanPathThroughDoors();
        this.setPathfindingPenalty(PathNodeType.DANGER_FIRE, 16.0F);
        this.setPathfindingPenalty(PathNodeType.DAMAGE_FIRE, -1.0F);
    }


    private void setCanPathThroughDoors() {
        if (NavigationConditions.hasMobNavigation(this)) {
            ((MobNavigation)this.getNavigation()).setCanPathThroughDoors(true);
        }

    }

    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
    }

    public double getHeightOffset() {
        return this.isBaby() ? -0.05D : -0.45D;
    }

    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
    }

    protected void mobTick() {
        super.mobTick();
    }

    public boolean isAdult() {
        return !this.isBaby();
    }

    @Nullable
    public LivingEntity getTarget() {
        return (LivingEntity)this.brain.getOptionalMemory(MemoryModuleType.ATTACK_TARGET).orElse((LivingEntity) null);
    }

    protected boolean isHoldingTool() {
        return this.getMainHandStack().getItem() instanceof ToolItem;
    }

    public void playAmbientSound() {
        super.playAmbientSound();
    }

    protected void sendAiDebugData() {
        super.sendAiDebugData();
        DebugInfoSender.sendBrainDebugData(this);
    }
}
