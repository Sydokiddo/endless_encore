package net.sydokiddo.endlessencore.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.registry.Registry;
import net.sydokiddo.endlessencore.EndlessEncore;
import net.sydokiddo.endlessencore.block.custom_blocks.*;
import net.sydokiddo.endlessencore.item.ModItemGroup;
import net.sydokiddo.endlessencore.sound.ModSoundEvents;

public class ModBlocks {

    // List of Blocks:

    public static final Block CHISELED_END_STONE = registerBlock("chiseled_end_stone",
            new Block(FabricBlockSettings.copyOf(Blocks.END_STONE_BRICKS).sounds(BlockSoundGroup.STONE)));

    public static final Block CRACKED_END_STONE_BRICKS = registerBlock("cracked_end_stone_bricks",
            new Block(FabricBlockSettings.copyOf(Blocks.END_STONE_BRICKS).sounds(BlockSoundGroup.STONE)));

    public static final Block END_STONE_PILLAR = registerBlock("end_stone_pillar",
            new ModPillarBlock(FabricBlockSettings.copyOf(Blocks.END_STONE_BRICKS).sounds(BlockSoundGroup.STONE)));

    public static final Block CHISELED_PURPUR = registerBlock("chiseled_purpur",
            new Block(FabricBlockSettings.copyOf(Blocks.PURPUR_BLOCK).sounds(BlockSoundGroup.STONE)));

    public static final Block END_LAMP = registerBlock("end_lamp",
            new EndLampBlock(FabricBlockSettings.of(Material.REDSTONE_LAMP, MapColor.WHITE).sounds(BlockSoundGroup.GLASS).luminance(15).requiresTool().hardness(0.3f).strength(0.3f)));

    public static final Block GRONGRON = registerBlock("grongron",
            new ModPillarBlock(FabricBlockSettings.of(Material.STONE, MapColor.PALE_GREEN).sounds(BlockSoundGroup.BASALT).requiresTool().hardness(2.0f).strength(8.0f)));

    public static final Block GRONGRON_TILES = registerBlock("grongron_tiles",
            new Block(FabricBlockSettings.copyOf(ModBlocks.GRONGRON).sounds(BlockSoundGroup.BASALT)));

    public static final Block GRONGRON_TILE_STAIRS = registerBlock("grongron_tile_stairs",
            new ModStairsBlock(ModBlocks.GRONGRON_TILES.getDefaultState(),
                    FabricBlockSettings.copyOf(ModBlocks.GRONGRON_TILES).sounds(BlockSoundGroup.BASALT)));

    public static final Block GRONGRON_TILE_SLAB = registerBlock("grongron_tile_slab",
            new SlabBlock(FabricBlockSettings.copyOf(ModBlocks.GRONGRON_TILES).sounds(BlockSoundGroup.BASALT)));

    public static final Block GRONGRON_TILE_WALL = registerBlock("grongron_tile_wall",
            new WallBlock(FabricBlockSettings.copyOf(ModBlocks.GRONGRON_TILES).sounds(BlockSoundGroup.BASALT)));

    public static final Block CHISELED_GRONGRON = registerBlock("chiseled_grongron",
            new Block(FabricBlockSettings.copyOf(ModBlocks.GRONGRON_TILES).sounds(BlockSoundGroup.BASALT)));

    public static final Block GRONGRON_PILLAR = registerBlock("grongron_pillar",
            new ModPillarBlock(FabricBlockSettings.copyOf(ModBlocks.GRONGRON_TILES).sounds(BlockSoundGroup.BASALT)));

    public static final Block ETHEREAL_BRICKS = registerBlock("ethereal_bricks",
            new Block(FabricBlockSettings.of(Material.STONE, MapColor.EMERALD_GREEN).sounds(BlockSoundGroup.NETHERITE).requiresTool().hardness(3.0f).strength(9.0f)));

    public static final Block ETHEREAL_BRICK_STAIRS = registerBlock("ethereal_brick_stairs",
            new ModStairsBlock(ModBlocks.ETHEREAL_BRICKS.getDefaultState(),
                    FabricBlockSettings.copyOf(ModBlocks.ETHEREAL_BRICKS).sounds(BlockSoundGroup.NETHERITE)));

    public static final Block ETHEREAL_BRICK_SLAB = registerBlock("ethereal_brick_slab",
            new SlabBlock(FabricBlockSettings.copyOf(ModBlocks.ETHEREAL_BRICKS).sounds(BlockSoundGroup.NETHERITE)));

    public static final Block ETHEREAL_BRICK_WALL = registerBlock("ethereal_brick_wall",
            new WallBlock(FabricBlockSettings.copyOf(ModBlocks.ETHEREAL_BRICKS).sounds(BlockSoundGroup.NETHERITE)));

    public static final Block ETHEREAL_TILES = registerBlock("ethereal_tiles",
            new Block(FabricBlockSettings.copyOf(ModBlocks.ETHEREAL_BRICKS).sounds(BlockSoundGroup.NETHERITE)));

    public static final Block ETHEREAL_TILE_STAIRS = registerBlock("ethereal_tile_stairs",
            new ModStairsBlock(ModBlocks.ETHEREAL_TILES.getDefaultState(),
                    FabricBlockSettings.copyOf(ModBlocks.ETHEREAL_BRICKS).sounds(BlockSoundGroup.NETHERITE)));

    public static final Block ETHEREAL_TILE_SLAB = registerBlock("ethereal_tile_slab",
            new SlabBlock(FabricBlockSettings.copyOf(ModBlocks.ETHEREAL_BRICKS).sounds(BlockSoundGroup.NETHERITE)));

    public static final Block ETHEREAL_TILE_WALL = registerBlock("ethereal_tile_wall",
            new WallBlock(FabricBlockSettings.copyOf(ModBlocks.ETHEREAL_BRICKS).sounds(BlockSoundGroup.NETHERITE)));

    public static final Block ETHEREAL_PILLAR = registerBlock("ethereal_pillar",
            new ModPillarBlock(FabricBlockSettings.copyOf(ModBlocks.ETHEREAL_BRICKS).sounds(BlockSoundGroup.NETHERITE)));

    public static final Block BEDROCK_BRICKS = registerBlock("bedrock_bricks",
            new Block(FabricBlockSettings.copyOf(Blocks.BEDROCK).sounds(BlockSoundGroup.STONE)));

    public static final Block BEDROCK_BRICK_STAIRS = registerBlock("bedrock_brick_stairs",
            new ModStairsBlock(ModBlocks.BEDROCK_BRICKS.getDefaultState(),
                    FabricBlockSettings.copyOf(Blocks.BEDROCK).sounds(BlockSoundGroup.STONE)));

    public static final Block BEDROCK_BRICK_SLAB = registerBlock("bedrock_brick_slab",
            new SlabBlock(FabricBlockSettings.copyOf(Blocks.BEDROCK).sounds(BlockSoundGroup.STONE)));

    public static final Block BEDROCK_BRICK_WALL = registerBlock("bedrock_brick_wall",
            new WallBlock(FabricBlockSettings.copyOf(Blocks.BEDROCK).sounds(BlockSoundGroup.STONE)));

    public static final Block BEDROCK_PILLAR = registerBlock("bedrock_pillar",
            new ModPillarBlock(FabricBlockSettings.copyOf(Blocks.BEDROCK).sounds(BlockSoundGroup.STONE)));

    public static final Block PALESTONE = registerBlock("palestone",
            new Block(FabricBlockSettings.of(Material.STONE, MapColor.OFF_WHITE).requiresTool().sounds(BlockSoundGroup.TUFF).hardness(3.0f).strength(6.0f)));

    public static final Block PALESTONE_STAIRS = registerBlock("palestone_stairs",
            new ModStairsBlock(ModBlocks.PALESTONE.getDefaultState(),
                    FabricBlockSettings.copyOf(ModBlocks.PALESTONE).sounds(BlockSoundGroup.TUFF)));

    public static final Block PALESTONE_SLAB = registerBlock("palestone_slab",
            new SlabBlock(FabricBlockSettings.copyOf(ModBlocks.PALESTONE).sounds(BlockSoundGroup.TUFF)));

    public static final Block PALESTONE_WALL = registerBlock("palestone_wall",
            new WallBlock(FabricBlockSettings.copyOf(ModBlocks.PALESTONE).sounds(BlockSoundGroup.TUFF)));

    public static final Block POLISHED_PALESTONE = registerBlock("polished_palestone",
            new Block(FabricBlockSettings.copyOf(ModBlocks.PALESTONE).sounds(BlockSoundGroup.TUFF)));

    public static final Block POLISHED_PALESTONE_STAIRS = registerBlock("polished_palestone_stairs",
            new ModStairsBlock(ModBlocks.POLISHED_PALESTONE.getDefaultState(),
                    FabricBlockSettings.copyOf(ModBlocks.PALESTONE).sounds(BlockSoundGroup.TUFF)));

    public static final Block POLISHED_PALESTONE_SLAB = registerBlock("polished_palestone_slab",
            new SlabBlock(FabricBlockSettings.copyOf(ModBlocks.PALESTONE).sounds(BlockSoundGroup.TUFF)));

    public static final Block POLISHED_PALESTONE_WALL = registerBlock("polished_palestone_wall",
            new WallBlock(FabricBlockSettings.copyOf(ModBlocks.PALESTONE).sounds(BlockSoundGroup.TUFF)));

    public static final Block POLISHED_PALESTONE_BRICKS = registerBlock("polished_palestone_bricks",
            new Block(FabricBlockSettings.copyOf(ModBlocks.PALESTONE).sounds(BlockSoundGroup.TUFF)));

    public static final Block POLISHED_PALESTONE_BRICK_STAIRS = registerBlock("polished_palestone_brick_stairs",
            new ModStairsBlock(ModBlocks.POLISHED_PALESTONE.getDefaultState(),
                    FabricBlockSettings.copyOf(ModBlocks.PALESTONE).sounds(BlockSoundGroup.TUFF)));

    public static final Block POLISHED_PALESTONE_BRICK_SLAB = registerBlock("polished_palestone_brick_slab",
            new SlabBlock(FabricBlockSettings.copyOf(ModBlocks.PALESTONE).sounds(BlockSoundGroup.TUFF)));

    public static final Block POLISHED_PALESTONE_BRICK_WALL = registerBlock("polished_palestone_brick_wall",
            new WallBlock(FabricBlockSettings.copyOf(ModBlocks.PALESTONE).sounds(BlockSoundGroup.TUFF)));

    public static final Block CHISELED_POLISHED_PALESTONE = registerBlock("chiseled_polished_palestone",
            new Block(FabricBlockSettings.copyOf(ModBlocks.PALESTONE).sounds(BlockSoundGroup.TUFF)));

    public static final Block CRACKED_POLISHED_PALESTONE_BRICKS = registerBlock("cracked_polished_palestone_bricks",
            new Block(FabricBlockSettings.copyOf(ModBlocks.PALESTONE).sounds(BlockSoundGroup.TUFF)));

    public static final Block IOLITE_BLOCK = registerBlock("iolite_block",
            new Block(FabricBlockSettings.of(Material.STONE, MapColor.PURPLE).sounds(ModSoundEvents.IOLITE).requiresTool().hardness(1.5f).strength(1.5f)));

    public static final Block IOLITE_BRICKS = registerBlock("iolite_bricks",
            new Block(FabricBlockSettings.copyOf(ModBlocks.IOLITE_BLOCK).sounds(ModSoundEvents.IOLITE)));

    public static final Block IOLITE_BRICK_STAIRS = registerBlock("iolite_brick_stairs",
            new ModStairsBlock(ModBlocks.IOLITE_BRICKS.getDefaultState(),
                    FabricBlockSettings.copyOf(ModBlocks.IOLITE_BRICKS).sounds(ModSoundEvents.IOLITE)));

    public static final Block IOLITE_BRICK_SLAB = registerBlock("iolite_brick_slab",
            new SlabBlock(FabricBlockSettings.copyOf(ModBlocks.IOLITE_BRICKS).sounds(ModSoundEvents.IOLITE)));

    public static final Block IOLITE_BRICK_WALL = registerBlock("iolite_brick_wall",
            new WallBlock(FabricBlockSettings.copyOf(ModBlocks.IOLITE_BRICKS).sounds(ModSoundEvents.IOLITE)));

    public static final Block IOLITE_PILLAR = registerBlock("iolite_pillar",
            new ModPillarBlock(FabricBlockSettings.copyOf(ModBlocks.IOLITE_BLOCK).sounds(ModSoundEvents.IOLITE)));

    public static final Block NETTLED_END_STONE = registerBlock("nettled_end_stone",
            new Block(FabricBlockSettings.of(Material.STONE, MapColor.YELLOW).requiresTool().sounds(ModSoundEvents.NETTLED_END_STONE).hardness(3.0f).strength(9.0f)));

    public static final Block NETTLE_CARPET = registerBlock("nettle_carpet",
            new CarpetBlock(FabricBlockSettings.of(Material.LEAVES, MapColor.YELLOW).nonOpaque().sounds(BlockSoundGroup.AZALEA_LEAVES).hardness(0.2f).strength(0.2f)));

    public static final Block NETTLE_LEAVES = registerBlock("nettle_leaves",
            new NettleLeavesBlock(FabricBlockSettings.of(Material.LEAVES, MapColor.YELLOW).nonOpaque().sounds(BlockSoundGroup.AZALEA_LEAVES).hardness(0.2f).strength(0.2f)));

    public static final Block FLOWERING_NETTLE_LEAVES = registerBlock("flowering_nettle_leaves",
            new NettleLeavesBlock(FabricBlockSettings.of(Material.LEAVES, MapColor.YELLOW).nonOpaque().sounds(BlockSoundGroup.AZALEA_LEAVES).hardness(0.2f).strength(0.2f)));

    public static final Block CONCORD_STALK = registerBlock("concord_stalk",
            new ModPillarBlock(FabricBlockSettings.of(Material.WOOD, MapColor.PURPLE).sounds(ModSoundEvents.END_STALK).strength(2.0f)));

    public static final Block CONCORD_PETIOLE = registerBlock("concord_petiole",
            new ModPillarBlock(FabricBlockSettings.copyOf(ModBlocks.CONCORD_STALK).sounds(ModSoundEvents.END_STALK)));

    public static final Block BLIGHTED_END_STONE = registerBlock("blighted_end_stone",
            new Block(FabricBlockSettings.of(Material.STONE, MapColor.LICHEN_GREEN).requiresTool().sounds(ModSoundEvents.NETTLED_END_STONE).hardness(3.0f).strength(9.0f)));

    public static final Block BLIGHTED_CARPET = registerBlock("blighted_carpet",
            new CarpetBlock(FabricBlockSettings.of(Material.LEAVES, MapColor.LICHEN_GREEN).nonOpaque().sounds(BlockSoundGroup.AZALEA_LEAVES).hardness(0.2f).strength(0.2f)));

    public static final Block ETHEREAL_CLOUD = registerBlock("ethereal_cloud",
            new EtherealCloudBlock(FabricBlockSettings.of(Material.WOOL, MapColor.EMERALD_GREEN).sounds(BlockSoundGroup.WOOL).luminance(10).hardness(0.1f).strength(0.1f).nonOpaque()));

    public static final Block END_GATEWAY_ACTIVATOR = registerBlock("end_gateway_activator",
            new Block(FabricBlockSettings.of(Material.STONE, MapColor.BLACK).sounds(BlockSoundGroup.STONE).hardness(-1).strength(3600000f)));

    public static final Block END_IRON_ORE = registerBlock("end_iron_ore",
            new OreBlock(FabricBlockSettings.of(Material.STONE, MapColor.PALE_YELLOW).sounds(ModSoundEvents.END_ORE).requiresTool().hardness(3.0f).strength(9.0f), UniformIntProvider.create(0, 1)));

    public static final Block END_IOLITE_ORE = registerBlock("end_iolite_ore",
            new OreBlock(FabricBlockSettings.of(Material.STONE, MapColor.PALE_YELLOW).requiresTool().sounds(ModSoundEvents.END_ORE).requiresTool().hardness(3.0f).strength(9.0f), UniformIntProvider.create(2, 5)));

    public static final Block END_DIAMOND_ORE = registerBlock("end_diamond_ore",
            new OreBlock(FabricBlockSettings.of(Material.STONE, MapColor.PALE_YELLOW).sounds(ModSoundEvents.END_ORE).requiresTool().hardness(3.0f).strength(9.0f), UniformIntProvider.create(3, 7)));

    public static final Block PERORATITE_BLOCK = registerBlock("peroratite_block",
            new Block(FabricBlockSettings.of(Material.METAL, MapColor.CYAN).sounds(BlockSoundGroup.NETHERITE).requiresTool().requiresTool().hardness(50.0f).strength(50.0f)));

    // Registry for Blocks:

    private static Block registerBlock(String name, Block block){
        registerBlockItem(name, block);
        return Registry.register(Registry.BLOCK, new Identifier(EndlessEncore.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block){
        return Registry.register(Registry.ITEM, new Identifier(EndlessEncore.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings().group(ModItemGroup.ENDLESS_ENCORE)));
    }

    public static void registerModBlocks() {
        System.out.println("Registering Blocks for " + EndlessEncore.MOD_ID);
    }

}