package net.sydokiddo.endlessencore.effect;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.sydokiddo.endlessencore.EndlessEncore;

// Status Effect Registry

public class ModEffects {

    public static MobEffect AERIAL_FATIGUE;
    public static MobEffect DISORIENTATION;
    public static MobEffect VULNERABILITY;

    public static MobEffect registerAerialFatigueEffect(String name) {
        return Registry.register(Registry.MOB_EFFECT, new ResourceLocation(EndlessEncore.MOD_ID, name), new AerialFatigueEffect(MobEffectCategory.HARMFUL, 8346034));
    }
    public static MobEffect registerDisorientationEffect(String name) {
        return Registry.register(Registry.MOB_EFFECT, new ResourceLocation(EndlessEncore.MOD_ID, name), new DisorientationEffect(MobEffectCategory.HARMFUL, 3404127));
    }

    public static MobEffect registerVulnerabilityEffect(String name) {
        return Registry.register(Registry.MOB_EFFECT, new ResourceLocation(EndlessEncore.MOD_ID, name), new VulnerabilityEffect(MobEffectCategory.HARMFUL, 7892329));
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
