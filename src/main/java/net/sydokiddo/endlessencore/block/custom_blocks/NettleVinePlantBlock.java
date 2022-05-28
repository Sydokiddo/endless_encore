package net.sydokiddo.endlessencore.block.custom_blocks;

import net.minecraft.block.*;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.sydokiddo.endlessencore.block.ModBlocks;

public class NettleVinePlantBlock extends AbstractPlantBlock {
    public static final VoxelShape SHAPE = Block.createCuboidShape(4.0D, 0.0D, 4.0D, 12.0D, 16.0D, 12.0D);

    public NettleVinePlantBlock(Settings settings) {
        super(settings, Direction.UP, SHAPE, false);
    }

    protected AbstractPlantStemBlock getStem() {
        return (AbstractPlantStemBlock) ModBlocks.NETTLE_VINES;
    }
}
