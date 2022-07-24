package net.sydokiddo.endlessencore.mixin.blocks.chorus_plant;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ChorusFlowerBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.sydokiddo.endlessencore.misc.EndlessEncoreTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

//@SuppressWarnings("ALL")

// Allows for Chorus Flowers to be plantable on any block in the 'Chorus Plant Can Grow On' tag

@Mixin(ChorusFlowerBlock.class)
public class ChorusFlowerCanGrowOn {

    @Inject(method = "canSurvive",at = @At("RETURN"),cancellable = true)
    public void canSurvive(BlockState blockState, LevelReader levelReader, BlockPos blockPos, CallbackInfoReturnable<Boolean> cir) {
        BlockState blockState2 = levelReader.getBlockState(blockPos.below());
        if (!blockState2.is(Blocks.CHORUS_PLANT) && !blockState2.is(EndlessEncoreTags.CHORUS_PLANT_CAN_GROW_ON)) {
            if (!blockState2.isAir()) {
                cir.setReturnValue(false);
            } else {
                boolean bl = false;

                for (Direction direction : Direction.Plane.HORIZONTAL) {
                    BlockState blockState3 = levelReader.getBlockState(blockPos.relative(direction));
                    if (blockState3.is(Blocks.CHORUS_PLANT)) {
                        if (bl) {
                            cir.setReturnValue(false);
                        }

                        bl = true;
                    } else if (!blockState3.isAir()) {
                        cir.setReturnValue(false);
                    }
                }

                cir.setReturnValue(bl);
            }
        } else {
            cir.setReturnValue(true);
        }
    }
}
