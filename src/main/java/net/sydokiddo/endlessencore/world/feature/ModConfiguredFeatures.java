package net.sydokiddo.endlessencore.world.feature;

import net.minecraft.block.Blocks;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.sydokiddo.endlessencore.EndlessEncore;
import net.sydokiddo.endlessencore.block.ModBlocks;

import java.util.List;

public class ModConfiguredFeatures {

    public ModConfiguredFeatures() {
    }

// List of Configured Features:

    public static final List<OreFeatureConfig.Target> ORE_END_IOLITE = List.of(
            OreFeatureConfig.createTarget(new BlockMatchRuleTest(Blocks.END_STONE),
                    ModBlocks.END_IOLITE_ORE.getDefaultState()));

    public static final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> IOLITE_ORE =
            ConfiguredFeatures.register("iolite_ore", Feature.ORE,
                    new OreFeatureConfig(ORE_END_IOLITE, 10));

    public static final List<OreFeatureConfig.Target> ORE_END_DIAMOND = List.of(
            OreFeatureConfig.createTarget(new BlockMatchRuleTest(Blocks.END_STONE),
                    ModBlocks.END_DIAMOND_ORE.getDefaultState()));

    public static final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> END_DIAMOND_ORE =
            ConfiguredFeatures.register("end_diamond_ore", Feature.ORE,
                    new OreFeatureConfig(ORE_END_DIAMOND, 5));

//    public static final RegistryEntry<ConfiguredFeature<RandomPatchFeatureConfig, ?>> NETTLE_ROOTS =
//            ConfiguredFeatures.register("nettle_roots", Feature.FLOWER,
//            ConfiguredFeatures.createRandomPatchFeatureConfig(64, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK,
//            new SimpleBlockFeatureConfig(BlockStateProvider.of(ModBlocks.NETTLE_ROOTS)))));


// Registry for Configured Features:

    public static void registerConfiguredFeatures() {
        System.out.println("Registering Configured Features for " + EndlessEncore.MOD_ID);
    }
}