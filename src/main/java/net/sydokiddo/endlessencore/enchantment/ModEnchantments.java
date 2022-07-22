package net.sydokiddo.endlessencore.enchantment;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.sydokiddo.endlessencore.EndlessEncore;

@SuppressWarnings("ALL")
public class ModEnchantments {

    public static Enchantment TEMPO_SPRINT = register("tempo_sprint",
    new TempoSprintEnchantment(Enchantment.Rarity.RARE, EnchantmentCategory.ARMOR_LEGS));

    public static Enchantment BALLISTIC = register("ballistic",
    new BallisticEnchantment(Enchantment.Rarity.RARE, EnchantmentCategory.FISHING_ROD));

    public static Enchantment AERIAL_AFFINITY = register("aerial_affinity",
    new AerialAffinityEnchantment(Enchantment.Rarity.UNCOMMON, EnchantmentCategory.FISHING_ROD));

    private static Enchantment register(String name, Enchantment enchantment) {
        return Registry.register(Registry.ENCHANTMENT, new ResourceLocation(EndlessEncore.MOD_ID, name), enchantment);
    }

    public static void registerModEnchantments() {
        System.out.println("Registering Enchantments for " + EndlessEncore.MOD_ID);
    }
}
