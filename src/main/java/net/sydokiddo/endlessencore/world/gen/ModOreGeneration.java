package net.sydokiddo.endlessencore.world.gen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.gen.GenerationStep;
import net.sydokiddo.endlessencore.world.feature.ModPlacedFeatures;

public class ModOreGeneration {

public static void generateOres() {

// Generation End Iolite Ore:

        BiomeModifications.addFeature(BiomeSelectors.foundInTheEnd(),
                GenerationStep.Feature.UNDERGROUND_ORES, ModPlacedFeatures.IOLITE_ORE_PLACED.getKey().get());

// Generation End Diamond Ore:

    BiomeModifications.addFeature(BiomeSelectors.foundInTheEnd(),
            GenerationStep.Feature.UNDERGROUND_ORES, ModPlacedFeatures.END_DIAMOND_ORE_PLACED.getKey().get());

    }
}