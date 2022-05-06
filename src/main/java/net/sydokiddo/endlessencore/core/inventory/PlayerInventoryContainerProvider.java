package net.sydokiddo.endlessencore.core.inventory;

import net.minecraft.entity.LivingEntity;
import net.sydokiddo.endlessencore.api.inventory.PreservationContainerProvider;
import org.jetbrains.annotations.Nullable;

public class PlayerInventoryContainerProvider implements PreservationContainerProvider<PlayerInventoryContainer> {
    @Override
    public @Nullable PlayerInventoryContainer getContainer(LivingEntity entity) {
        return entity instanceof PlayerInventoryContainer ? (PlayerInventoryContainer) entity : null;
    }
}