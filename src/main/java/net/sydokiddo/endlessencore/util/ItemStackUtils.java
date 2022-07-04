package net.sydokiddo.endlessencore.util;

import net.minecraft.item.ItemStack;

public class ItemStackUtils {

    public static boolean equalsIgnoreCount(ItemStack stack1, ItemStack stack2) {
        if (!stack1.isItemEqual(stack2)) {
            return false;
        }

        if (stack1.getNbt() == null && stack2.getNbt() != null) {
            return false;
        }

        return stack1.getNbt() == null || stack1.getNbt().equals(stack2.getNbt());
    }
}