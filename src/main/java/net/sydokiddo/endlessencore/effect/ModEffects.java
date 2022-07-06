package net.sydokiddo.endlessencore.effect;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.sydokiddo.endlessencore.EndlessEncore;

// Status Effect Registry

public class ModEffects {

    public static StatusEffect STAGNANT;
    public static StatusEffect CONFUSION;
    public static StatusEffect VULNERABILITY;

    public static StatusEffect registerStagnantEffect(String name) {
        return Registry.register(Registry.STATUS_EFFECT, new Identifier(EndlessEncore.MOD_ID, name), new StagnantEffect(StatusEffectCategory.HARMFUL, 8346034));
    }
    public static StatusEffect registerConfusionEffect(String name) {
        return Registry.register(Registry.STATUS_EFFECT, new Identifier(EndlessEncore.MOD_ID, name), new ConfusionEffect(StatusEffectCategory.HARMFUL, 3404127));
    }

    public static StatusEffect registerVulnerabilityEffect(String name) {
        return Registry.register(Registry.STATUS_EFFECT, new Identifier(EndlessEncore.MOD_ID, name), new VulnerabilityEffect(StatusEffectCategory.HARMFUL, 7892329));
    }

    public static void registerStagnantEffect() {
        STAGNANT = registerStagnantEffect("stagnant");
    }

    public static void registerConfusionEffect() {
        CONFUSION = registerConfusionEffect("confusion");
    }

    public static void registerVulnerabilityEffect() {
        VULNERABILITY = registerVulnerabilityEffect("vulnerability");
    }
}
