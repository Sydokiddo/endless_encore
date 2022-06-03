package net.sydokiddo.endlessencore.block.custom_blocks;

import net.minecraft.block.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.sydokiddo.endlessencore.block.ModBlocks;
import net.sydokiddo.endlessencore.misc.EndlessEncoreTags;

public class EndPlantBlock extends PlantBlock {
    protected static final VoxelShape SHAPE = Block.createCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 13.0D, 14.0D);

    public EndPlantBlock(Settings settings) {
        super(settings);
    }

    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
        return floor.isIn(EndlessEncoreTags.OVERGROWN_END_STONE) || floor.isOf(ModBlocks.LILOAM) || super.canPlantOnTop(floor, world, pos);
    }
}