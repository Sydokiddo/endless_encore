package net.sydokiddo.endlessencore.block.custom_blocks;

import java.util.Random;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

@SuppressWarnings("ALL")
public class EndLampBlock extends Block {
    public EndLampBlock(Properties settings) {
        super(settings);
    }

    public void randomDisplayTick(BlockState state, Level world, BlockPos pos, Random random) {
        if (random.nextInt(5) == 0) {
            Direction direction = Direction.getRandom((net.minecraft.util.RandomSource) random);
            if (direction != Direction.UP) {
                BlockPos blockPos = pos.relative(direction);
                BlockState blockState = world.getBlockState(blockPos);
                if (!state.canOcclude() || !blockState.isFaceSturdy(world, blockPos, direction.getOpposite())) {
                    double d = direction.getStepX() == 0 ? random.nextDouble() : 0.5D + (double)direction.getStepX() * 0.6D;
                    double e = direction.getStepY() == 0 ? random.nextDouble() : 0.5D + (double)direction.getStepY() * 0.6D;
                    double f = direction.getStepZ() == 0 ? random.nextDouble() : 0.5D + (double)direction.getStepZ() * 0.6D;
                    world.addParticle(ParticleTypes.END_ROD, (double)pos.getX() + d, (double)pos.getY() + e, (double)pos.getZ() + f, 0.0D, 0.0D, 0.0D);
                }
            }
        }
    }
}
