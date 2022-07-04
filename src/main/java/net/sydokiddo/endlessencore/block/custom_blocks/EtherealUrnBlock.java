package net.sydokiddo.endlessencore.block.custom_blocks;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.sydokiddo.endlessencore.entity.block_entities.ethereal_urn.EtherealUrnBlockEntity;
import net.sydokiddo.endlessencore.sound.ModSoundEvents;
import org.jetbrains.annotations.Nullable;

public class EtherealUrnBlock extends FallingBlock implements BlockEntityProvider {
    public static final DirectionProperty FACING;
    private static final VoxelShape SHAPE = VoxelShapes.union(Block.createCuboidShape(3.0D, 0.0D, 3.0D, 13.0D, 10.0D, 13.0D), Block.createCuboidShape(5.0D, 10.0D, 5.0D, 11.0D, 12.0D, 11.0D));

    public EtherealUrnBlock(Settings settings) {
        super(settings);
    }

    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player,
        Hand hand, BlockHitResult hit) {
        ItemStack playerStack = player.getMainHandStack();
        if (!world.isClient && (!playerStack.isEmpty())) {
            EtherealUrnBlockEntity blockEntity = (EtherealUrnBlockEntity) world.getBlockEntity(pos);
            assert blockEntity != null;

            if (!playerStack.isEmpty()) {
                if (blockEntity.receive(playerStack, 0, 1) == ActionResult.SUCCESS) {
                    playerStack.decrement(1);
                    world.playSound(null, pos, ModSoundEvents.BLOCK_ETHEREAL_URN_INSERT, SoundCategory.BLOCKS, 0.8F, 0.8F + world.random.nextFloat() * 1.2F);
                    player.swingHand(hand);
                }
            }
            if (playerStack.isEmpty()) {
                return ActionResult.FAIL;
            }
        }
        return ActionResult.SUCCESS;
    }

    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getPlayerFacing().rotateYClockwise());
    }

    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    protected void configureFallingBlockEntity(FallingBlockEntity entity) {
        entity.setHurtEntities(2.0F, 30);
    }

    public void onLanding(World world, BlockPos pos, BlockState fallingBlockState, BlockState currentStateInPos, FallingBlockEntity fallingBlockEntity) {
        if (!world.isClient) {
            BlockPos blockPos = fallingBlockEntity.getBlockPos();
            world.playSound(null, blockPos, ModSoundEvents.BLOCK_ETHEREAL_URN_BREAK, SoundCategory.BLOCKS, 1.0F, 1.0F + world.random.nextFloat() * 1.2F);
            world.breakBlock(blockPos, false);
        }
    }

    public PistonBehavior getPistonBehavior(BlockState state) {
        return PistonBehavior.PUSH_ONLY;
    }

    public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
        return false;
    }

    public void onProjectileHit(World world, BlockState state, BlockHitResult hit, ProjectileEntity projectile) {
        if (!world.isClient) {
            BlockPos blockPos = hit.getBlockPos();
            world.playSound(null, blockPos, ModSoundEvents.BLOCK_ETHEREAL_URN_BREAK, SoundCategory.BLOCKS, 1.0F, 1.0F + world.random.nextFloat() * 1.2F);
            world.breakBlock(blockPos, false);
        }
    }

    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (!state.isOf(newState.getBlock())) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof Inventory) {
                ItemScatterer.spawn(world, pos, (Inventory)blockEntity);
                world.updateComparators(pos, this);
            }

            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    public DamageSource getDamageSource() {
        return DamageSource.FALLING_BLOCK;
    }

    static {
        FACING = HorizontalFacingBlock.FACING;
    }

    @Nullable
    @Override
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