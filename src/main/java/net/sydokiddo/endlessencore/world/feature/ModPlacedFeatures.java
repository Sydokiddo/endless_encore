package net.sydokiddo.endlessencore.world.feature;

import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.placementmodifier.HeightRangePlacementModifier;

public class ModPlacedFeatures {

    public static final RegistryEntry<PlacedFeature> IOLITE_ORE_PLACED = PlacedFeatures.register("ore_iolite_end",
            ModConfiguredFeatures.IOLITE_ORE, ModOreFeatures.modifiersWithCount(10,
                    HeightRangePlacementModifier.uniform(YOffset.aboveBottom(-80), YOffset.aboveBottom(120))));

    public static final RegistryEntry<PlacedFeature> END_DIAMOND_ORE_PLACED = PlacedFeatures.register("ore_diamond_end",
            ModConfiguredFeatures.END_DIAMOND_ORE, ModOreFeatures.modifiersWithCount(5,
                    HeightRangePlacementModifier.uniform(YOffset.aboveBottom(-80), YOffset.aboveBottom(120))));
}