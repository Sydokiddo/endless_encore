package net.sydokiddo.endlessencore.entity.block_entities.ethereal_urn;

import net.sydokiddo.endlessencore.util.ImplementedInventory;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.sydokiddo.endlessencore.entity.block_entities.ModBlockEntities;
import net.sydokiddo.endlessencore.util.ItemStackUtils;

public class EtherealUrnBlockEntity extends BlockEntity implements ImplementedInventory {
    private NonNullList<ItemStack> items;

    public EtherealUrnBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.ETHEREAL_URN_BLOCK_ENTITY, pos, state);
        this.items = NonNullList.withSize(1, ItemStack.EMPTY);
    }

    @Override
    public NonNullList<ItemStack> getItems() {
        return items;
    }

    public int getContainerSize() {
        return 1;
    }

    public void setItems(NonNullList<ItemStack> items) {
        this.items = items;
    }

    public InteractionResult receive(ItemStack giver, int index, int amount) {
        ItemStack receiver = items.get(index);

        if (receiver.getCount() < receiver.getMaxStackSize()
                && (receiver.isEmpty() || ItemStackUtils.equalsIgnoreCount(giver, receiver))) {
            ItemStack transfer = giver.copy();
            transfer.setCount(Math.min(receiver.getCount() + amount, receiver.getMaxStackSize()));

            items.set(index, transfer);

            if (getLevel() != null) {
                this.getLevel().updateNeighbourForOutputSignal(getBlockPos(), getBlockState().getBlock());
            }

            return InteractionResult.SUCCESS;
        }

        return InteractionResult.FAIL;
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        ContainerHelper.loadAllItems(tag, items);
    }

    @Override
    public void saveAdditional(CompoundTag tag) {
        ContainerHelper.saveAllItems(tag, items);
        super.saveAdditional(tag);
    }
}