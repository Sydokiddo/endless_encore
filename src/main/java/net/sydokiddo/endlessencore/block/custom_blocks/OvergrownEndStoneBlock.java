package net.sydokiddo.endlessencore.block.custom_blocks;

import net.minecraft.block.*;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.world.chunk.light.ChunkLightProvider;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.sydokiddo.endlessencore.block.ModBlocks;
import net.sydokiddo.endlessencore.world.feature.ModConfiguredFeatures;

public class OvergrownEndStoneBlock extends Block implements Fertilizable {
    public OvergrownEndStoneBlock(Settings settings) {
        super(settings);
    }

    private static boolean stayAlive(BlockState state, WorldView world, BlockPos pos) {
        BlockPos blockPos = pos.up();
        BlockState blockState = world.getBlockState(blockPos);
        int i = ChunkLightProvider.getRealisticOpacity(world, state, pos, blockState, blockPos, Direction.UP, blockState.getOpacity(world, blockPos));
        return i < world.getMaxLightLevel();
    }

    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (!stayAlive(state, world, pos)) {
            world.setBlockState(pos, Blocks.END_STONE.getDefaultState());
        }

    }

    public boolean isFertilizable(BlockView world, BlockPos pos, BlockState state, boolean isClient) {
        return world.getBlockState(pos.up()).isAir();
    }

    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        return true;
    }

    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        BlockState blockState = world.getBlockState(pos);
        BlockPos blockPos = pos.up();
        ChunkGenerator chunkGenerator = world.getChunkManager().getChunkGenerator();
        if (blockState.isOf(ModBlocks.NETTLED_END_STONE)) {
            ModConfiguredFeatures.NETTLE_ROOTS.value().generate(world, chunkGenerator, random, blockPos);
        } else if (blockState.isOf(ModBlocks.BLIGHTED_END_STONE)) {
            ModConfiguredFeatures.NETTLE_ROOTS.value().generate(world, chunkGenerator, random, blockPos);
            }
        }

    }