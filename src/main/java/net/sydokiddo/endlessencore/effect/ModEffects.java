package net.sydokiddo.endlessencore.effect;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.sydokiddo.endlessencore.EndlessEncore;

// Status Effect Registry

public class ModEffects {

    public static StatusEffect AERIAL_FATIGUE;
    public static StatusEffect DISORIENTATION;
    public static StatusEffect VULNERABILITY;

    public static StatusEffect registerAerialFatigueEffect(String name) {
        return Registry.register(Registry.STATUS_EFFECT, new Identifier(EndlessEncore.MOD_ID, name), new AerialFatigueEffect(StatusEffectCategory.HARMFUL, 8346034));
    }
    public static StatusEffect registerDisorientationEffect(String name) {
        return Registry.register(Registry.STATUS_EFFECT, new Identifier(EndlessEncore.MOD_ID, name), new DisorientationEffect(StatusEffectCategory.HARMFUL, 3404127));
    }

    public static StatusEffect registerVulnerabilityEffect(String name) {
        return Registry.register(Registry.STATUS_EFFECT, new Identifier(EndlessEncore.MOD_ID, name), new VulnerabilityEffect(StatusEffectCategory.HARMFUL, 7892329));
    }

    public static void registerAerialFatigueEffect() {
        AERIAL_FATIGUE = registerAerialFatigueEffect("aerial_fatigue");
    }

    public static void registerDisorientationEffect() {
        DISORIENTATION = registerDisorientationEffect("disorientation");
    }

    public static void registerVulnerabilityEffect() {
        VULNERABILITY = registerVulnerabilityEffect("vulnerability");
    }
}
