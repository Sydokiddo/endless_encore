package net.sydokiddo.endlessencore.item;

import java.util.function.Supplier;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;

@SuppressWarnings("ALL")
public enum ModToolMaterial implements Tier {
    PERORATITE(5, 3125, 11.0F, 5.0F, 18, () -> {
        return Ingredient.of(ModItems.PERORATITE_INGOT);
    });

    private final int miningLevel;
    private final int itemDurability;
    private final float miningSpeed;
    private final float attackDamage;
    private final int enchantability;
    private final LazyLoadedValue repairIngredient;

    ModToolMaterial(int miningLevel, int itemDurability, float miningSpeed, float attackDamage, int enchantability, Supplier<Ingredient> repairIngredient) {
        this.miningLevel = miningLevel;
        this.itemDurability = itemDurability;
        this.miningSpeed = miningSpeed;
        this.attackDamage = attackDamage;
        this.enchantability = enchantability;
        this.repairIngredient = new LazyLoadedValue(repairIngredient);
    }

    public int getUses() {
        return this.itemDurability;
    }

    public float getSpeed() {
        return this.miningSpeed;
    }

    public float getAttackDamageBonus() {
        return this.attackDamage;
    }

    public int getLevel() {
        return this.miningLevel;
    }

    public int getEnchantmentValue() {
        return this.enchantability;
    }

    public Ingredient getRepairIngredient() {
        return (Ingredient)this.repairIngredient.get();
    }
}
