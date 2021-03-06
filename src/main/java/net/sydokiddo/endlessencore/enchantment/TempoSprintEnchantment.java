package net.sydokiddo.endlessencore.enchantment;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

@SuppressWarnings("ALL")

// Not functional as of yet!

public class TempoSprintEnchantment extends Enchantment {
    public TempoSprintEnchantment(Rarity weight, EnchantmentCategory type, EquipmentSlot... slotTypes) {
        super(weight, type, slotTypes);
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
        return false;
    }

    public boolean isDiscoverable() {
        return false;
    }

    public int getMaxLevel() {
        return 3;
    }
}