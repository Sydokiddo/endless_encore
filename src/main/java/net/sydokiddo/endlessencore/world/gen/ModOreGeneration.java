package net.sydokiddo.endlessencore.world.gen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.sydokiddo.endlessencore.world.feature.ModPlacedFeatures;

@SuppressWarnings("ALL")
public class ModOreGeneration {

public static void generateOres() {

// Generation End Iolite Ore:

        BiomeModifications.addFeature(BiomeSelectors.foundInTheEnd(),
                GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.IOLITE_ORE_PLACED.unwrapKey().get());

// Generation End Diamond Ore:

    BiomeModifications.addFeature(BiomeSelectors.foundInTheEnd(),
            GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.END_DIAMOND_ORE_PLACED.unwrapKey().get());

    }
}