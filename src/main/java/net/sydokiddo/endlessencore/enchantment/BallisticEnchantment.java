package net.sydokiddo.endlessencore.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ElytraItem;
import net.minecraft.item.ItemStack;

@SuppressWarnings("ALL")
public class BallisticEnchantment extends Enchantment {
    public BallisticEnchantment(Rarity weight, EnchantmentTarget type, EquipmentSlot... slotTypes) {
        super(weight, type, slotTypes);
    }

    public boolean isAcceptableItem(ItemStack stack) {
        return stack.getItem() instanceof ElytraItem;
    }

    public int getMinPower(int level) {
        return level * 25;
    }

    public int getMaxPower(int level) {
        return this.getMinPower(level) + 50;
    }

    public boolean isTreasure() {
        return true;
    }

    public boolean isAvailableForEnchantedBookOffer() {
        return false;
    }

    public boolean isAvailableForRandomSelection() {
        return false;
    }

    public int getMaxLevel() {
        return 3;
    }
}