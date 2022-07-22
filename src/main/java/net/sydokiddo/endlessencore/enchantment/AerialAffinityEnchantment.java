package net.sydokiddo.endlessencore.enchantment;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ElytraItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

@SuppressWarnings("ALL")
public class AerialAffinityEnchantment extends Enchantment {
    public AerialAffinityEnchantment(Rarity weight, EnchantmentCategory type, EquipmentSlot... slotTypes) {
        super(weight, type, slotTypes);
    }

    public boolean canEnchant(ItemStack stack) {
        return stack.getItem() instanceof ElytraItem;
    }

    public int getMinCost(int level) {
        return level * 25;
    }

    public int getMaxCost(int level) {
        return this.getMinCost(level) + 50;
    }

    public boolean isTreasureOnly() {
        return true;
    }

    public boolean isTradeable() {
        return true;
    }

    public boolean isDiscoverable() {
        return false;
    }

    public int getMaxLevel() {
        return 1;
    }
}