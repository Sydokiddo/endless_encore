package net.sydokiddo.endlessencore.api.inventory;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;

import java.util.function.UnaryOperator;

public interface PreservationContainer {

    PreservationContainerProvider<? extends PreservationContainer> getProvider();

    LivingEntity getEntity();

    void storeToNbt(NbtCompound nbt);

    void restoreFromNbt(NbtCompound nbt, UnaryOperator<ItemStack> itemProcessor);
}