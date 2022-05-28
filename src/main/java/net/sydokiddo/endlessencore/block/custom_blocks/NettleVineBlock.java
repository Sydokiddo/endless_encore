package net.sydokiddo.endlessencore.block.custom_blocks;

import net.minecraft.block.*;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.sydokiddo.endlessencore.block.ModBlocks;

public class NettleVineBlock extends AbstractPlantStemBlock {
    public static final VoxelShape SHAPE = Block.createCuboidShape(4.0D, 0.0D, 4.0D, 12.0D, 15.0D, 12.0D);

    public NettleVineBlock(Settings settings) {
        super(settings, Direction.UP, SHAPE, false, 0.1D);
    }

    protected int getGrowthLength(Random random) {
        return VineLogic.getGrowthLength(random);
    }

    protected Block getPlant() {
        return ModBlocks.NETTLE_VINES_PLANT;
    }

    protected boolean chooseStemState(BlockState state) {
        return VineLogic.isValidForWeepingStem(state);
    }
}
