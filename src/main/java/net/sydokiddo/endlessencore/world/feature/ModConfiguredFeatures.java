package net.sydokiddo.endlessencore.world.feature;

import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.sydokiddo.endlessencore.EndlessEncore;
import net.sydokiddo.endlessencore.block.ModBlocks;
import java.util.List;

@SuppressWarnings("ALL")
public class ModConfiguredFeatures {

    public ModConfiguredFeatures() {}

// List of Configured Features:

    public static final List<OreConfiguration.TargetBlockState> ORE_END_IOLITE = List.of(
            OreConfiguration.target(new BlockMatchTest(Blocks.END_STONE),
                    ModBlocks.END_IOLITE_ORE.defaultBlockState()));

    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> IOLITE_ORE =
            FeatureUtils.register("iolite_ore", Feature.ORE,
                    new OreConfiguration(ORE_END_IOLITE, 10));

    public static final List<OreConfiguration.TargetBlockState> ORE_END_DIAMOND = List.of(
            OreConfiguration.target(new BlockMatchTest(Blocks.END_STONE),
                    ModBlocks.END_DIAMOND_ORE.defaultBlockState()));

    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> END_DIAMOND_ORE =
            FeatureUtils.register("end_diamond_ore", Feature.ORE,
                    new OreConfiguration(ORE_END_DIAMOND, 5));


// Registry for Configured Features:

    public static void registerConfiguredFeatures() {
        System.out.println("Registering Configured Features for " + EndlessEncore.MOD_ID);
    }
}