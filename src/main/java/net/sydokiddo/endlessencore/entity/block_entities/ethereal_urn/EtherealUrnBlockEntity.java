package net.sydokiddo.endlessencore.entity.block_entities.ethereal_urn;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.sydokiddo.endlessencore.util.ImplementedInventory;
import net.sydokiddo.endlessencore.entity.block_entities.ModBlockEntities;
import net.sydokiddo.endlessencore.util.ItemStackUtils;

public class EtherealUrnBlockEntity extends BlockEntity implements ImplementedInventory {
    private DefaultedList<ItemStack> items;

    public EtherealUrnBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.ETHEREAL_URN_BLOCK_ENTITY, pos, state);
        this.items = DefaultedList.ofSize(1, ItemStack.EMPTY);
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return items;
    }

    public int size() {
        return 1;
    }

    public void setItems(DefaultedList<ItemStack> items) {
        this.items = items;
    }

    public ActionResult receive(ItemStack giver, int index, int amount) {
        ItemStack receiver = items.get(index);

        if (receiver.getCount() < receiver.getMaxCount()
                && (receiver.isEmpty() || ItemStackUtils.equalsIgnoreCount(giver, receiver))) {
            ItemStack transfer = giver.copy();
            transfer.setCount(Math.min(receiver.getCount() + amount, receiver.getMaxCount()));

            items.set(index, transfer);

            if (getWorld() != null) {
                this.getWorld().updateComparators(getPos(), getCachedState().getBlock());
            }

            return ActionResult.SUCCESS;
        }

        return ActionResult.FAIL;
    }

    @Override
    public void readNbt(NbtCompound tag) {
        super.readNbt(tag);
        Inventories.readNbt(tag, items);
    }

    @Override
    public void writeNbt(NbtCompound tag) {
        Inventories.writeNbt(tag, items);
        super.writeNbt(tag);
    }
}