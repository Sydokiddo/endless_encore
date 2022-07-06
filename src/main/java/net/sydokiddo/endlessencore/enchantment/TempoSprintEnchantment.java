package net.sydokiddo.endlessencore.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;

@SuppressWarnings("ALL")
public class TempoSprintEnchantment extends Enchantment {
    public TempoSprintEnchantment(Rarity weight, EnchantmentTarget type, EquipmentSlot... slotTypes) {
        super(weight, type, slotTypes);
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