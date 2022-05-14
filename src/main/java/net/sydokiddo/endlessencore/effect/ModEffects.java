package net.sydokiddo.endlessencore.effect;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.sydokiddo.endlessencore.EndlessEncore;

public class ModEffects {

    public static StatusEffect ANCHORED;

    public static StatusEffect registerStatusEffect(String name) {
        return Registry.register(Registry.STATUS_EFFECT, new Identifier(EndlessEncore.MOD_ID, name),
                new AnchoredEffect(StatusEffectCategory.HARMFUL, 111111));
    }

    public static void registerEffects() {
        ANCHORED = registerStatusEffect("anchored");
    }
}
