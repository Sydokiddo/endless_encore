package net.sydokiddo.endlessencore.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.sydokiddo.endlessencore.EndlessEncore;
import net.sydokiddo.endlessencore.block.custom_blocks.*;
import net.sydokiddo.endlessencore.item.ModItemGroup;
import net.sydokiddo.endlessencore.sound.ModSoundEvents;

@SuppressWarnings("ALL")
public class ModBlocks {

// List of Blocks:

    public static final Block CHISELED_END_STONE = registerBlock("chiseled_end_stone",
    new Block(FabricBlockSettings.copyOf(Blocks.END_STONE_BRICKS).sound(SoundType.STONE)));

    public static final Block CRACKED_END_STONE_BRICKS = registerBlock("cracked_end_stone_bricks",
    new Block(FabricBlockSettings.copyOf(Blocks.END_STONE_BRICKS).sound(SoundType.STONE)));

    public static final Block END_STONE_PILLAR = registerBlock("end_stone_pillar",
    new ModPillarBlock(FabricBlockSettings.copyOf(Blocks.END_STONE_BRICKS).sound(SoundType.STONE)));

    public static final Block CHISELED_PURPUR = registerBlock("chiseled_purpur",
    new Block(FabricBlockSettings.copyOf(Blocks.PURPUR_BLOCK).sound(SoundType.STONE)));

    public static final Block END_LAMP = registerBlock("end_lamp",
    new EndLampBlock(FabricBlockSettings.of(Material.BUILDABLE_GLASS, MaterialColor.SNOW).sound(SoundType.GLASS).
            requiresCorrectToolForDrops().destroyTime(0.3f).strength(0.3f).lightLevel((blockStatex) -> { return 15; })));

    public static final Block GRONGRON = registerBlock("grongron",
    new ModPillarBlock(FabricBlockSettings.of(Material.STONE, MaterialColor.GLOW_LICHEN).sound(SoundType.BASALT)
    .requiresCorrectToolForDrops().destroyTime(2.0f).strength(8.0f)));

    public static final Block GRONGRON_TILES = registerBlock("grongron_tiles",
    new Block(FabricBlockSettings.copyOf(ModBlocks.GRONGRON).sound(SoundType.BASALT)));

    public static final Block GRONGRON_TILE_STAIRS = registerBlock("grongron_tile_stairs",
    new ModStairsBlock(ModBlocks.GRONGRON_TILES.defaultBlockState(),
    FabricBlockSettings.copyOf(ModBlocks.GRONGRON_TILES).sound(SoundType.BASALT)));

    public static final Block GRONGRON_TILE_SLAB = registerBlock("grongron_tile_slab",
    new SlabBlock(FabricBlockSettings.copyOf(ModBlocks.GRONGRON_TILES).sound(SoundType.BASALT)));

    public static final Block GRONGRON_TILE_WALL = registerBlock("grongron_tile_wall",
    new WallBlock(FabricBlockSettings.copyOf(ModBlocks.GRONGRON_TILES).sound(SoundType.BASALT)));

    public static final Block CHISELED_GRONGRON = registerBlock("chiseled_grongron",
    new Block(FabricBlockSettings.copyOf(ModBlocks.GRONGRON_TILES).sound(SoundType.BASALT)));

    public static final Block GRONGRON_PILLAR = registerBlock("grongron_pillar",
    new ModPillarBlock(FabricBlockSettings.copyOf(ModBlocks.GRONGRON_TILES).sound(SoundType.BASALT)));

    public static final Block ETHEREAL_URN = registerBlock("ethereal_urn",
    new EtherealUrnBlock(FabricBlockSettings.of(Material.STONE, MaterialColor.GRASS).sound(ModSoundEvents.ETHEREAL_URN)
    .noOcclusion().strength(1.0f)));

    public static final Block ETHEREAL_BRICKS = registerBlock("ethereal_bricks",
    new Block(FabricBlockSettings.of(Material.STONE, MaterialColor.WARPED_STEM).sound(SoundType.NETHERITE_BLOCK)
    .requiresCorrectToolForDrops().destroyTime(3.0f).strength(9.0f)));

    public static final Block ETHEREAL_BRICK_STAIRS = registerBlock("ethereal_brick_stairs",
    new ModStairsBlock(ModBlocks.ETHEREAL_BRICKS.defaultBlockState(),
    FabricBlockSettings.copyOf(ModBlocks.ETHEREAL_BRICKS).sound(SoundType.NETHERITE_BLOCK)));

    public static final Block ETHEREAL_BRICK_SLAB = registerBlock("ethereal_brick_slab",
    new SlabBlock(FabricBlockSettings.copyOf(ModBlocks.ETHEREAL_BRICKS).sound(SoundType.NETHERITE_BLOCK)));

    public static final Block ETHEREAL_BRICK_WALL = registerBlock("ethereal_brick_wall",
    new WallBlock(FabricBlockSettings.copyOf(ModBlocks.ETHEREAL_BRICKS).sound(SoundType.NETHERITE_BLOCK)));

    public static final Block ETHEREAL_TILES = registerBlock("ethereal_tiles",
    new Block(FabricBlockSettings.copyOf(ModBlocks.ETHEREAL_BRICKS).sound(SoundType.NETHERITE_BLOCK)));

    public static final Block ETHEREAL_TILE_STAIRS = registerBlock("ethereal_tile_stairs",
    new ModStairsBlock(ModBlocks.ETHEREAL_TILES.defaultBlockState(),
    FabricBlockSettings.copyOf(ModBlocks.ETHEREAL_BRICKS).sound(SoundType.NETHERITE_BLOCK)));

    public static final Block ETHEREAL_TILE_SLAB = registerBlock("ethereal_tile_slab",
    new SlabBlock(FabricBlockSettings.copyOf(ModBlocks.ETHEREAL_BRICKS).sound(SoundType.NETHERITE_BLOCK)));

    public static final Block ETHEREAL_TILE_WALL = registerBlock("ethereal_tile_wall",
    new WallBlock(FabricBlockSettings.copyOf(ModBlocks.ETHEREAL_BRICKS).sound(SoundType.NETHERITE_BLOCK)));

    public static final Block ETHEREAL_PILLAR = registerBlock("ethereal_pillar",
    new ModPillarBlock(FabricBlockSettings.copyOf(ModBlocks.ETHEREAL_BRICKS).sound(SoundType.NETHERITE_BLOCK)));

    public static final Block BEDROCK_BRICKS = registerBlock("bedrock_bricks",
    new Block(FabricBlockSettings.copyOf(Blocks.BEDROCK).sound(SoundType.STONE)));

    public static final Block BEDROCK_BRICK_STAIRS = registerBlock("bedrock_brick_stairs",
    new ModStairsBlock(ModBlocks.BEDROCK_BRICKS.defaultBlockState(),
    FabricBlockSettings.copyOf(Blocks.BEDROCK).sound(SoundType.STONE)));

    public static final Block BEDROCK_BRICK_SLAB = registerBlock("bedrock_brick_slab",
    new SlabBlock(FabricBlockSettings.copyOf(Blocks.BEDROCK).sound(SoundType.STONE)));

    public static final Block BEDROCK_BRICK_WALL = registerBlock("bedrock_brick_wall",
    new WallBlock(FabricBlockSettings.copyOf(Blocks.BEDROCK).sound(SoundType.STONE)));

    public static final Block BEDROCK_PILLAR = registerBlock("bedrock_pillar",
    new ModPillarBlock(FabricBlockSettings.copyOf(Blocks.BEDROCK).sound(SoundType.STONE)));

    public static final Block PALESTONE = registerBlock("palestone",
    new Block(FabricBlockSettings.of(Material.STONE, MaterialColor.QUARTZ).requiresCorrectToolForDrops()
    .sound(SoundType.TUFF).destroyTime(1.5f).strength(6.0f)));

    public static final Block PALESTONE_STAIRS = registerBlock("palestone_stairs",
    new ModStairsBlock(ModBlocks.PALESTONE.defaultBlockState(),
    FabricBlockSettings.copyOf(ModBlocks.PALESTONE).sound(SoundType.TUFF)));

    public static final Block PALESTONE_SLAB = registerBlock("palestone_slab",
    new SlabBlock(FabricBlockSettings.copyOf(ModBlocks.PALESTONE).sound(SoundType.TUFF)));

    public static final Block PALESTONE_WALL = registerBlock("palestone_wall",
    new WallBlock(FabricBlockSettings.copyOf(ModBlocks.PALESTONE).sound(SoundType.TUFF)));

    public static final Block POLISHED_PALESTONE = registerBlock("polished_palestone",
    new Block(FabricBlockSettings.copyOf(ModBlocks.PALESTONE).sound(SoundType.TUFF)));

    public static final Block POLISHED_PALESTONE_STAIRS = registerBlock("polished_palestone_stairs",
    new ModStairsBlock(ModBlocks.POLISHED_PALESTONE.defaultBlockState(),
    FabricBlockSettings.copyOf(ModBlocks.PALESTONE).sound(SoundType.TUFF)));

    public static final Block POLISHED_PALESTONE_SLAB = registerBlock("polished_palestone_slab",
    new SlabBlock(FabricBlockSettings.copyOf(ModBlocks.PALESTONE).sound(SoundType.TUFF)));

    public static final Block POLISHED_PALESTONE_WALL = registerBlock("polished_palestone_wall",
    new WallBlock(FabricBlockSettings.copyOf(ModBlocks.PALESTONE).sound(SoundType.TUFF)));

    public static final Block POLISHED_PALESTONE_BRICKS = registerBlock("polished_palestone_bricks",
    new Block(FabricBlockSettings.copyOf(ModBlocks.PALESTONE).sound(SoundType.TUFF)));

    public static final Block POLISHED_PALESTONE_BRICK_STAIRS = registerBlock("polished_palestone_brick_stairs",
    new ModStairsBlock(ModBlocks.POLISHED_PALESTONE.defaultBlockState(),
    FabricBlockSettings.copyOf(ModBlocks.PALESTONE).sound(SoundType.TUFF)));

    public static final Block POLISHED_PALESTONE_BRICK_SLAB = registerBlock("polished_palestone_brick_slab",
    new SlabBlock(FabricBlockSettings.copyOf(ModBlocks.PALESTONE).sound(SoundType.TUFF)));

    public static final Block POLISHED_PALESTONE_BRICK_WALL = registerBlock("polished_palestone_brick_wall",
    new WallBlock(FabricBlockSettings.copyOf(ModBlocks.PALESTONE).sound(SoundType.TUFF)));

    public static final Block CHISELED_POLISHED_PALESTONE = registerBlock("chiseled_polished_palestone",
    new Block(FabricBlockSettings.copyOf(ModBlocks.PALESTONE).sound(SoundType.TUFF)));

    public static final Block CRACKED_POLISHED_PALESTONE_BRICKS = registerBlock("cracked_polished_palestone_bricks",
    new Block(FabricBlockSettings.copyOf(ModBlocks.PALESTONE).sound(SoundType.TUFF)));

    public static final Block IOLITE_BLOCK = registerBlock("iolite_block",
    new IoliteBlock(FabricBlockSettings.of(Material.STONE, MaterialColor.COLOR_PURPLE).sound(ModSoundEvents.IOLITE)
    .requiresCorrectToolForDrops().destroyTime(1.5f).strength(1.5f)));

    public static final Block IOLITE_BRICKS = registerBlock("iolite_bricks",
    new IoliteBlock(FabricBlockSettings.copyOf(ModBlocks.IOLITE_BLOCK).sound(ModSoundEvents.IOLITE)));

    public static final Block IOLITE_BRICK_STAIRS = registerBlock("iolite_brick_stairs",
    new IoliteStairsBlock(ModBlocks.IOLITE_BRICKS.defaultBlockState(),
    FabricBlockSettings.copyOf(ModBlocks.IOLITE_BRICKS).sound(ModSoundEvents.IOLITE)));

    public static final Block IOLITE_BRICK_SLAB = registerBlock("iolite_brick_slab",
    new IoliteSlabBlock(FabricBlockSettings.copyOf(ModBlocks.IOLITE_BRICKS).sound(ModSoundEvents.IOLITE)));

    public static final Block IOLITE_BRICK_WALL = registerBlock("iolite_brick_wall",
    new IoliteWallBlock(FabricBlockSettings.copyOf(ModBlocks.IOLITE_BRICKS).sound(ModSoundEvents.IOLITE)));

    public static final Block IOLITE_PILLAR = registerBlock("iolite_pillar",
    new IolitePillarBlock(FabricBlockSettings.copyOf(ModBlocks.IOLITE_BLOCK).sound(ModSoundEvents.IOLITE)));

    public static final Block LILOAM = registerBlock("liloam",
    new LiloamBlock(FabricBlockSettings.of(Material.SAND, MaterialColor.TERRACOTTA_PURPLE).sound(SoundType.SAND).strength(0.5F)));

    public static final Block ETHEREAL_CLOUD = registerBlock("ethereal_cloud",
    new EtherealCloudBlock(FabricBlockSettings.of(Material.WOOL, MaterialColor.EMERALD).sound(ModSoundEvents.ETHEREAL_CLOUD)
    .destroyTime(0.1f).strength(0.1f).noOcclusion().lightLevel((blockStatex) -> { return 10; })));

    public static final Block END_IOLITE_ORE = registerBlock("end_iolite_ore",
    new DropExperienceBlock(FabricBlockSettings.of(Material.STONE, MaterialColor.SAND).requiresCorrectToolForDrops().sound(ModSoundEvents.END_ORE)
    .requiresCorrectToolForDrops().destroyTime(3.0f).strength(9.0f), UniformInt.of(2, 5)));

    public static final Block END_DIAMOND_ORE = registerBlock("end_diamond_ore",
    new DropExperienceBlock(FabricBlockSettings.of(Material.STONE, MaterialColor.SAND).sound(ModSoundEvents.END_ORE).requiresCorrectToolForDrops()
    .destroyTime(3.0f).strength(9.0f), UniformInt.of(3, 7)));

    public static final Block PERORATITE_BLOCK = registerBlock("peroratite_block",
    new Block(FabricBlockSettings.of(Material.METAL, MaterialColor.GLOW_LICHEN).sound(SoundType.NETHERITE_BLOCK)
    .requiresCorrectToolForDrops().destroyTime(50.0f).strength(50.0f)));

    public static final Block DRAGON_OMELETTE = registerBlock("dragon_omelette",
    new DragonOmeletteBlock(FabricBlockSettings.of(Material.CAKE, MaterialColor.TERRACOTTA_PURPLE)
    .sound(SoundType.WOOL).strength(1.0f)));

// Registry for Blocks:

    private static Block registerBlock(String name, Block block){
        registerBlockItem(name, block);
        return Registry.register(Registry.BLOCK, new ResourceLocation(EndlessEncore.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block){
        return Registry.register(Registry.ITEM, new ResourceLocation(EndlessEncore.MOD_ID, name),
        new BlockItem(block, new FabricItemSettings().tab(ModItemGroup.ENDLESS_ENCORE)));
    }

    public static void registerModBlocks() {
        System.out.println("Registering Blocks for " + EndlessEncore.MOD_ID);
    }
}