package net.sydokiddo.endlessencore.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.util.Identifier;
import net.sydokiddo.endlessencore.EndlessEncore;
import net.minecraft.util.registry.Registry;

@SuppressWarnings("ALL")
public class ModEnchantments {

    public static Enchantment TEMPO_SPRINT = register("tempo_sprint",
    new TempoSprintEnchantment(Enchantment.Rarity.RARE, EnchantmentTarget.ARMOR_LEGS));

    public static Enchantment BALLISTIC = register("ballistic",
    new BallisticEnchantment(Enchantment.Rarity.RARE, EnchantmentTarget.WEARABLE));

    private static Enchantment register(String name, Enchantment enchantment) {
        return Registry.register(Registry.ENCHANTMENT, new Identifier(EndlessEncore.MOD_ID, name), enchantment);
    }

    public static void registerModEnchantments() {
        System.out.println("Registering Enchantments for " + EndlessEncore.MOD_ID);
    }
}
