package net.sydokiddo.endlessencore.util;

import net.minecraft.world.item.ItemStack;

// Item Stack Utility Code for Ethereal Urn

public class ItemStackUtils {

    public static boolean equalsIgnoreCount(ItemStack stack1, ItemStack stack2) {
        if (!stack1.sameItemStackIgnoreDurability(stack2)) {
            return false;
        }

        if (stack1.getTag() == null && stack2.getTag() != null) {
            return false;
        }

        return stack1.getTag() == null || stack1.getTag().equals(stack2.getTag());
    }
}