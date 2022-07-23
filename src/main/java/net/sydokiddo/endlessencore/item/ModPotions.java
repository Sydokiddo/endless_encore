package net.sydokiddo.endlessencore.item;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.Potions;
import net.sydokiddo.endlessencore.EndlessEncore;
import net.sydokiddo.endlessencore.mixin.potions.BrewingRecipeRegistryMixin;

// Potions:

public class ModPotions {
    public static Potion LEVITATION;
    public static Potion LONG_LEVITATION;

    // Potion Stats

    public static Potion registerLevitationPotion(String name) {
        return Registry.register(Registry.POTION, new ResourceLocation(EndlessEncore.MOD_ID, name),
        new Potion(new MobEffectInstance(MobEffects.LEVITATION, 400, 0)));
    }

    public static Potion registerLongLevitationPotion(String name) {
        return Registry.register(Registry.POTION, new ResourceLocation(EndlessEncore.MOD_ID, name),
        new Potion(new MobEffectInstance(MobEffects.LEVITATION, 800, 0)));
    }

    // Potion Registry

    public static void registerLevitationPotion() {
        LEVITATION = registerLevitationPotion("levitation");
        registerLevitationPotionRecipe();
    }

    public static void registerLongLevitationPotion() {
        LONG_LEVITATION = registerLongLevitationPotion("long_levitation");
        registerLongLevitationPotionRecipe();
    }

    // Potion Recipes

    private static void registerLevitationPotionRecipe() {
        if (EndlessEncore.getConfig().misc_tweaks.allow_levitation_potions) {
            BrewingRecipeRegistryMixin.invokeRegisterPotionRecipe(Potions.AWKWARD, ModItems.SHULKER_BULLET,
            ModPotions.LEVITATION);
        }
    }

    private static void registerLongLevitationPotionRecipe() {
        if (EndlessEncore.getConfig().misc_tweaks.allow_levitation_potions) {
        BrewingRecipeRegistryMixin.invokeRegisterPotionRecipe(ModPotions.LEVITATION, Items.REDSTONE,
        ModPotions.LONG_LEVITATION);
        }
    }
}