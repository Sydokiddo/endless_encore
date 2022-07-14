package net.sydokiddo.endlessencore.block.custom_blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Container;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.sydokiddo.endlessencore.entity.block_entities.ethereal_urn.EtherealUrnBlockEntity;
import net.sydokiddo.endlessencore.misc.ModGameEvents;
import net.sydokiddo.endlessencore.sound.ModSoundEvents;
import net.sydokiddo.endlessencore.util.ModProperties;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("ALL")
public class EtherealUrnBlock extends FallingBlock implements EntityBlock, SimpleWaterloggedBlock {
    public static final DirectionProperty FACING;
    public static final BooleanProperty CONTAINS_ZEAL;
    public static final BooleanProperty WATERLOGGED;
    private static final VoxelShape SHAPE = Shapes.or(Block.box(3.0D, 0.0D, 3.0D, 13.0D, 10.0D, 13.0D), Block.box(5.0D, 10.0D, 5.0D, 11.0D, 12.0D, 11.0D));

    public EtherealUrnBlock(Properties settings) {
        super(settings);
        this.registerDefaultState(this.stateDefinition.any().setValue(WATERLOGGED, false).setValue(CONTAINS_ZEAL, false));
    }

    // Gets the hitbox shape for the block

    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    // Allows players to store items in the Ethereal Urn when right-clicking it with an item while sneaking

    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player,
        InteractionHand hand, BlockHitResult hit) {
        ItemStack playerStack = player.getMainHandItem();
        if (!world.isClientSide && (!playerStack.isEmpty()) && (!state.getValue(CONTAINS_ZEAL))) {
            EtherealUrnBlockEntity blockEntity = (EtherealUrnBlockEntity) world.getBlockEntity(pos);
            assert blockEntity != null;
            if (!playerStack.isEmpty()) {
                if ((blockEntity.receive(playerStack, 0, 1) == InteractionResult.SUCCESS)) {
                    playerStack.shrink(1);
                    world.playSound(null, pos, ModSoundEvents.BLOCK_ETHEREAL_URN_INSERT, SoundSource.BLOCKS, 0.8F, 0.8F + world.random.nextFloat() * 1.2F);
                    player.swing(hand);
                    world.gameEvent(null, GameEvent.CONTAINER_OPEN, pos);
                }
            }
            if (playerStack.isEmpty()) {
                return InteractionResult.FAIL;
            }
        }
        return InteractionResult.SUCCESS;
    }

    // Breaks the Ethereal Urn if an entity falls onto it from 5 blocks or higher

    public void fallOn(Level world, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
        if (!world.isClientSide && world.random.nextFloat() < fallDistance - 5.0F && entity instanceof LivingEntity && (entity instanceof Player || world.getGameRules().getBoolean(GameRules.RULE_MOBGRIEFING)) && entity.getBbWidth() * entity.getBbWidth() * entity.getBbHeight() > 0.512F) {
            world.playSound(null, pos, ModSoundEvents.BLOCK_ETHEREAL_URN_BREAK, SoundSource.BLOCKS, 1.0F, 1.0F + world.random.nextFloat() * 1.2F);
            world.destroyBlock(pos, false);
            world.gameEvent(null, ModGameEvents.ETHEREAL_URN_BREAK, pos);
        }

        super.fallOn(world, state, pos, entity, fallDistance);
    }

    public void destroy(LevelAccessor world, BlockPos pos, BlockState state) {
        world.gameEvent(null, ModGameEvents.ETHEREAL_URN_BREAK, pos);
    }

    // Gets the rotation block states for the block

    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        return this.defaultBlockState().setValue(FACING, ctx.getHorizontalDirection().getClockWise());
    }

    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor world, BlockPos pos, BlockPos neighborPos) {
        if (state.getValue(WATERLOGGED)) {
            world.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(world));
        }

        return super.updateShape(state, direction, neighborState, world, pos, neighborPos);
    }

    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    public BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));    }

    // Damages entities when the block falls onto them

    protected void falling(FallingBlockEntity entity) {
        entity.setHurtsEntities(2.0F, 30);
    }

    // Breaks the Ethereal Urn when it falls

    public void onLand(Level world, BlockPos pos, BlockState fallingBlockState, BlockState currentStateInPos, FallingBlockEntity fallingBlockEntity) {
        if (!world.isClientSide) {
            BlockPos blockPos = fallingBlockEntity.blockPosition();
            world.playSound(null, blockPos, ModSoundEvents.BLOCK_ETHEREAL_URN_BREAK, SoundSource.BLOCKS, 1.0F, 1.0F + world.random.nextFloat() * 1.2F);
            world.destroyBlock(blockPos, false);
            world.gameEvent(null, ModGameEvents.ETHEREAL_URN_BREAK, blockPos);
        }
    }

    // Pistons can only push the Ethereal Urn but not pull it

    public PushReaction getPistonPushReaction(BlockState state) {
        return PushReaction.PUSH_ONLY;
    }

    public boolean isPathfindable(BlockState state, BlockGetter world, BlockPos pos, PathComputationType type) {
        return false;
    }

    // Breaks the Ethereal Urn if a projectile hits it

    public void onProjectileHit(Level world, BlockState state, BlockHitResult hit, Projectile projectile) {
        if (!world.isClientSide && world.getGameRules().getBoolean(GameRules.RULE_MOBGRIEFING)) {
            BlockPos blockPos = hit.getBlockPos();
            world.playSound(null, blockPos, ModSoundEvents.BLOCK_ETHEREAL_URN_BREAK, SoundSource.BLOCKS, 1.0F, 1.0F + world.random.nextFloat() * 1.2F);
            world.destroyBlock(blockPos, false);
            world.gameEvent(null, ModGameEvents.ETHEREAL_URN_BREAK, blockPos);
            }
        }

    // Drops any stored items if the Ethereal Urn is broken

    public void onRemove(BlockState state, Level world, BlockPos pos, BlockState newState, boolean moved) {
        if (!state.is(newState.getBlock())) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof Container) {
                Containers.dropContents(world, pos, (Container)blockEntity);
                world.updateNeighbourForOutputSignal(pos, this);
            }
            super.onRemove(state, world, pos, newState, moved);
        }
        if (state.getValue(CONTAINS_ZEAL) && world.getGameRules().getBoolean(GameRules.RULE_DOMOBSPAWNING)) {
            world.playSound(null, pos, SoundEvents.SOUL_ESCAPE, SoundSource.BLOCKS, 1.0F, 1.0F + world.random.nextFloat() * 1.2F);
            world.gameEvent(null, GameEvent.ENTITY_PLACE, pos);
        }
        super.onRemove(state, world, pos, newState, moved);
    }

    // Damage Source for an Ethereal Urn falling on an entity

    public DamageSource getFallDamageSource() {
        return DamageSource.FALLING_BLOCK;
    }

    static {
        FACING = HorizontalDirectionalBlock.FACING;
        CONTAINS_ZEAL = ModProperties.CONTAINS_ZEAL;
        WATERLOGGED = BlockStateProperties.WATERLOGGED;
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, CONTAINS_ZEAL, WATERLOGGED);
    }

    @Nullable @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new EtherealUrnBlockEntity(pos, state);
    }

    @Override
    public boolean hasAnalogOutputSignal(BlockState state) {
        return true;
    }

    @Override
    public int getAnalogOutputSignal(BlockState state, Level world, BlockPos pos) {
        return AbstractContainerMenu.getRedstoneSignalFromBlockEntity(world.getBlockEntity(pos));
    }
}