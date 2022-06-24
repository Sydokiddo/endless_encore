package net.sydokiddo.endlessencore.entity.ethereals.ethereal;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.world.World;

public class EtherealEntity extends HostileEntity {
    protected EtherealEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }
}
