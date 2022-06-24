package net.sydokiddo.endlessencore.effect;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.sydokiddo.endlessencore.EndlessEncore;

public class ModEffects {

    public static StatusEffect ANCHORED;
    public static StatusEffect CONFUSION;

    public static StatusEffect registerAnchoredEffect(String name) {
        return Registry.register(Registry.STATUS_EFFECT, new Identifier(EndlessEncore.MOD_ID, name), new AnchoredEffect(StatusEffectCategory.HARMFUL, 15750875));
    }
    public static StatusEffect registerConfusionEffect(String name) {
        return Registry.register(Registry.STATUS_EFFECT, new Identifier(EndlessEncore.MOD_ID, name), new ConfusionEffect(StatusEffectCategory.HARMFUL, 3404127));
    }

    public static void registerAnchoredEffect() {
        ANCHORED = registerAnchoredEffect("anchored");
    }

    public static void registerConfusionEffect() {
        CONFUSION = registerConfusionEffect("confusion");
    }
}
