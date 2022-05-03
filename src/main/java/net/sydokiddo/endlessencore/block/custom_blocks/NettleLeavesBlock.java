package net.sydokiddo.endlessencore.block.custom_blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

import java.util.Random;

public class NettleLeavesBlock extends LeavesBlock {
    public static final int MAX_DISTANCE = 7;
    public static final IntProperty DISTANCE;
    public static final BooleanProperty PERSISTENT;
    private static final int field_31112 = 1;

    public NettleLeavesBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(DISTANCE, 7).with(PERSISTENT, false));
    }

    public VoxelShape getSidesShape(BlockState state, BlockView world, BlockPos pos) {
        return VoxelShapes.empty();
    }

    public boolean hasRandomTicks(BlockState state) {
        return state.get(DISTANCE) == 7 && !(Boolean)state.get(PERSISTENT);
    }

    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (!(Boolean)state.get(PERSISTENT) && state.get(DISTANCE) == 7) {
            dropStacks(state, world, pos);
            world.removeBlock(pos, false);
        }

    }

    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        world.setBlockState(pos, updateDistanceFromLogs(state, world, pos), 3);
    }

    public int getOpacity(BlockState state, BlockView world, BlockPos pos) {
        return 1;
    }

    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        int i = getDistanceFromLog(neighborState) + 1;
        if (i != 1 || state.get(DISTANCE) != i) {
            world.createAndScheduleBlockTick(pos, this, 1);
        }

        return state;
    }

    private static BlockState updateDistanceFromLogs(BlockState state, WorldAccess world, BlockPos pos) {
        int i = 7;
        BlockPos.Mutable mutable = new BlockPos.Mutable();
        Direction[] var5 = Direction.values();
        int var6 = var5.length;

        for(int var7 = 0; var7 < var6; ++var7) {
            Direction direction = var5[var7];
            mutable.set(pos, direction);
            i = Math.min(i, getDistanceFromLog(world.getBlockState(mutable)) + 1);
            if (i == 1) {
                break;
            }
        }

        return state.with(DISTANCE, i);
    }

    private static int getDistanceFromLog(BlockState state) {
        if (state.isIn(BlockTags.LOGS)) {
            return 0;
        } else {
            return state.getBlock() instanceof LeavesBlock ? state.get(DISTANCE) : 7;
        }
    }

    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if (world.hasRain(pos.up())) {
            if (random.nextInt(15) == 1) {
                BlockPos blockPos = pos.down();
                BlockState blockState = world.getBlockState(blockPos);
                if (!blockState.isOpaque() || !blockState.isSideSolidFullSquare(world, blockPos, Direction.UP)) {
                    double d = (double)pos.getX() + random.nextDouble();
                    double e = (double)pos.getY() - 0.05D;
                    double f = (double)pos.getZ() + random.nextDouble();
                    world.addParticle(ParticleTypes.DRIPPING_WATER, d, e, f, 0.0D, 0.0D, 0.0D);
                }
            }
        }
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(DISTANCE, PERSISTENT);
    }

    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return updateDistanceFromLogs(this.getDefaultState().with(PERSISTENT, true), ctx.getWorld(), ctx.getBlockPos());
    }

    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
        double d = Math.abs(entity.getVelocity().y);
        if (d < 0.1D && !entity.bypassesSteppingEffects()) {
            double e = 0.4D + d * 0.2D;
            entity.setVelocity(entity.getVelocity().multiply(e, 1.0D, e));
            entity.slowMovement(state, new Vec3d(0.800000011920929D, 0.75D, 0.800000011920929D));
        }
        if (!entity.isSneaking() && entity instanceof LivingEntity && entity.getType() != EntityType.ENDERMAN && entity.getType() != EntityType.ENDERMITE) {
            ((LivingEntity) entity).addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS,20,0));
            entity.damage(DamageSource.SWEET_BERRY_BUSH, 1.0F);
        }

        super.onSteppedOn(world, pos, state, entity);
    }

    static {
        DISTANCE = Properties.DISTANCE_1_7;
        PERSISTENT = Properties.PERSISTENT;
    }

    public boolean isTranslucent(BlockState state, BlockView world, BlockPos pos) {
        return state.getFluidState().isEmpty();
    }
}