package net.sydokiddo.endlessencore.world.feature;

import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class ModPlacedFeatures {

    public static final Holder<PlacedFeature> IOLITE_ORE_PLACED = PlacementUtils.register("ore_iolite_end",
            ModConfiguredFeatures.IOLITE_ORE, ModOreFeatures.modifiersWithCount(10,
                    HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(120))));

    public static final Holder<PlacedFeature> END_DIAMOND_ORE_PLACED = PlacementUtils.register("ore_diamond_end",
            ModConfiguredFeatures.END_DIAMOND_ORE, ModOreFeatures.modifiersWithCount(5,
                    HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(120))));
}