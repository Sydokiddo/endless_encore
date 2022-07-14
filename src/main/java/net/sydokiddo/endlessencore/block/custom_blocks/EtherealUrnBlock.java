package net.sydokiddo.endlessencore.block.custom_blocks;

import BlockState;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.event.GameEvent;
import net.sydokiddo.endlessencore.entity.block_entities.ethereal_urn.EtherealUrnBlockEntity;
import net.sydokiddo.endlessencore.misc.ModGameEvents;
import net.sydokiddo.endlessencore.sound.ModSoundEvents;
import net.sydokiddo.endlessencore.util.ModProperties;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("ALL")
public class EtherealUrnBlock extends FallingBlock implements BlockEntityProvider, Waterloggable {
    public static final DirectionProperty FACING;
    public static final BooleanProperty CONTAINS_ZEAL;
    public static final BooleanProperty WATERLOGGED;
    private static final VoxelShape SHAPE = VoxelShapes.union(Block.createCuboidShape(3.0D, 0.0D, 3.0D, 13.0D, 10.0D, 13.0D), Block.createCuboidShape(5.0D, 10.0D, 5.0D, 11.0D, 12.0D, 11.0D));

    public EtherealUrnBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(WATERLOGGED, false).with(CONTAINS_ZEAL, false));
    }

    // Gets the hitbox shape for the block

    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    // Allows players to store items in the Ethereal Urn when right-clicking it with an item while sneaking

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player,
        Hand hand, BlockHitResult hit) {
        ItemStack playerStack = player.getMainHandStack();
        if (!world.isClient && (!playerStack.isEmpty()) && (!state.get(CONTAINS_ZEAL))) {
            EtherealUrnBlockEntity blockEntity = (EtherealUrnBlockEntity) world.getBlockEntity(pos);
            assert blockEntity != null;
            if (!playerStack.isEmpty()) {
                if ((blockEntity.receive(playerStack, 0, 1) == ActionResult.SUCCESS)) {
                    playerStack.decrement(1);
                    world.playSound(null, pos, ModSoundEvents.BLOCK_ETHEREAL_URN_INSERT, SoundCategory.BLOCKS, 0.8F, 0.8F + world.random.nextFloat() * 1.2F);
                    player.swingHand(hand);
                    world.emitGameEvent(null, GameEvent.CONTAINER_OPEN, pos);
                }
            }
            if (playerStack.isEmpty()) {
                return ActionResult.FAIL;
            }
        }
        return ActionResult.SUCCESS;
    }

    // Breaks the Ethereal Urn if an entity falls onto it from 5 blocks or higher

    public void onLandedUpon(World world, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
        if (!world.isClient && world.random.nextFloat() < fallDistance - 5.0F && entity instanceof LivingEntity && (entity instanceof PlayerEntity || world.getGameRules().getBoolean(GameRules.DO_MOB_GRIEFING)) && entity.getWidth() * entity.getWidth() * entity.getHeight() > 0.512F) {
            world.playSound(null, pos, ModSoundEvents.BLOCK_ETHEREAL_URN_BREAK, SoundCategory.BLOCKS, 1.0F, 1.0F + world.random.nextFloat() * 1.2F);
            world.breakBlock(pos, false);
            world.emitGameEvent(null, ModGameEvents.ETHEREAL_URN_BREAK, pos);
        }

        super.onLandedUpon(world, state, pos, entity, fallDistance);
    }

    public void onBroken(WorldAccess world, BlockPos pos, BlockState state) {
        world.emitGameEvent(null, ModGameEvents.ETHEREAL_URN_BREAK, pos);
    }

    // Gets the rotation block states for the block

    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getPlayerFacing().rotateYClockwise());
    }

    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (state.get(WATERLOGGED)) {
            world.createAndScheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }

        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));    }

    // Damages entities when the block falls onto them

    protected void configureFallingBlockEntity(FallingBlockEntity entity) {
        entity.setHurtEntities(2.0F, 30);
    }

    // Breaks the Ethereal Urn when it falls

    public void onLanding(World world, BlockPos pos, BlockState fallingBlockState, BlockState currentStateInPos, FallingBlockEntity fallingBlockEntity) {
        if (!world.isClient) {
            BlockPos blockPos = fallingBlockEntity.getBlockPos();
            world.playSound(null, blockPos, ModSoundEvents.BLOCK_ETHEREAL_URN_BREAK, SoundCategory.BLOCKS, 1.0F, 1.0F + world.random.nextFloat() * 1.2F);
            world.breakBlock(blockPos, false);
            world.emitGameEvent(null, ModGameEvents.ETHEREAL_URN_BREAK, blockPos);
        }
    }

    // Pistons can only push the Ethereal Urn but not pull it

    public PistonBehavior getPistonBehavior(BlockState state) {
        return PistonBehavior.PUSH_ONLY;
    }

    public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
        return false;
    }

    // Breaks the Ethereal Urn if a projectile hits it

    public void onProjectileHit(World world, BlockState state, BlockHitResult hit, ProjectileEntity projectile) {
        if (!world.isClient && world.getGameRules().getBoolean(GameRules.DO_MOB_GRIEFING)) {
            BlockPos blockPos = hit.getBlockPos();
            world.playSound(null, blockPos, ModSoundEvents.BLOCK_ETHEREAL_URN_BREAK, SoundCategory.BLOCKS, 1.0F, 1.0F + world.random.nextFloat() * 1.2F);
            world.breakBlock(blockPos, false);
            world.emitGameEvent(null, ModGameEvents.ETHEREAL_URN_BREAK, blockPos);
            }
        }

    // Drops any stored items if the Ethereal Urn is broken

    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (!state.isOf(newState.getBlock())) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof Inventory) {
                ItemScatterer.spawn(world, pos, (Inventory)blockEntity);
                world.updateComparators(pos, this);
            }
            super.onStateReplaced(state, world, pos, newState, moved);
        }
        if (state.get(CONTAINS_ZEAL) && world.getGameRules().getBoolean(GameRules.DO_MOB_SPAWNING)) {
            world.playSound(null, pos, SoundEvents.PARTICLE_SOUL_ESCAPE, SoundCategory.BLOCKS, 1.0F, 1.0F + world.random.nextFloat() * 1.2F);
            world.emitGameEvent(null, GameEvent.ENTITY_PLACE, pos);
        }
        super.onStateReplaced(state, world, pos, newState, moved);
    }

    // Damage Source for an Ethereal Urn falling on an entity

    public DamageSource getDamageSource() {
        return DamageSource.FALLING_BLOCK;
    }

    static {
        FACING = HorizontalFacingBlock.FACING;
        CONTAINS_ZEAL = ModProperties.CONTAINS_ZEAL;
        WATERLOGGED = Properties.WATERLOGGED;
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, CONTAINS_ZEAL, WATERLOGGED);
    }

    @Nullable @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new EtherealUrnBlockEntity(pos, state);
    }

    @Override
    public boolean hasComparatorOutput(BlockState state) {
        return true;
    }

    @Override
    public int getComparatorOutput(BlockState state, World world, BlockPos pos) {
        return ScreenHandler.calculateComparatorOutput(world.getBlockEntity(pos));
    }
}