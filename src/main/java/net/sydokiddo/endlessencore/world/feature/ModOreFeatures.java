package net.sydokiddo.endlessencore.world.feature;

import net.minecraft.structure.rule.RuleTest;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.world.gen.placementmodifier.*;
import net.sydokiddo.endlessencore.misc.EndlessEncoreTags;

import java.util.List;

public class ModOreFeatures {
    public static final RuleTest BASE_STONE_END;

    public ModOreFeatures() {
    }

    static {
        BASE_STONE_END = new TagMatchRuleTest(EndlessEncoreTags.BASE_STONE_END);
    }

    public static List<PlacementModifier> modifiers(PlacementModifier countModifier, PlacementModifier heightModifier) {
        return List.of(countModifier, SquarePlacementModifier.of(), heightModifier, BiomePlacementModifier.of());
    }

    public static List<PlacementModifier> modifiersWithCount(int count, PlacementModifier heightModifier) {
        return modifiers(CountPlacementModifier.of(count), heightModifier);
    }

    public static List<PlacementModifier> modifiersWithRarity(int chance, PlacementModifier heightModifier) {
        return modifiers(RarityFilterPlacementModifier.of(chance), heightModifier);
    }
}