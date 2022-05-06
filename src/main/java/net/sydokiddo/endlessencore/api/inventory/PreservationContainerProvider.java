package net.sydokiddo.endlessencore.api.inventory;

import net.minecraft.entity.LivingEntity;
import org.jetbrains.annotations.Nullable;

public interface PreservationContainerProvider<T extends PreservationContainer> {

    @Nullable T getContainer(LivingEntity entity);
}