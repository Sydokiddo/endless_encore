package net.sydokiddo.endlessencore.mixin;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;
import net.sydokiddo.endlessencore.EndlessEncore;
import net.sydokiddo.endlessencore.api.inventory.PreservationContainer;
import net.sydokiddo.endlessencore.api.inventory.PreservationContainerProvider;
import net.sydokiddo.endlessencore.core.PreservationHooks;
import net.sydokiddo.endlessencore.core.PreservationPersistentState;
import net.sydokiddo.endlessencore.core.inventory.PlayerInventoryContainer;
import org.spongepowered.asm.mixin.Implements;
import org.spongepowered.asm.mixin.Interface;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;
import java.util.function.UnaryOperator;

@Mixin(value = PlayerEntity.class, priority = 6969)
@Implements(@Interface(iface = PlayerInventoryContainer.class, prefix = "sb$"))
public abstract class MixinPlayerEntity extends LivingEntity {

    private MixinPlayerEntity(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
        throw new UnsupportedOperationException("mixin not transformed");
    }

    @Inject(method = "dropInventory", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerInventory;dropAll()V"))
    private void preservation_dropInventory(CallbackInfo callbackInfo) {
        if ((Object) this instanceof ServerPlayerEntity && PreservationHooks.isRealPlayer((ServerPlayerEntity) (Object) this)) {
            PreservationPersistentState persistentState = PreservationPersistentState.get(this.getServer());
            persistentState.storePlayer((PlayerEntity) (Object) this);
        }
    }

    public PreservationContainerProvider<? extends PreservationContainer> sb$getProvider() {
        return EndlessEncore.PLAYER_CONTAINER_PROVIDER;
    }

    public LivingEntity sb$getEntity() {
        return this;
    }

    public void sb$storeToNbt(NbtCompound nbt) {
        nbt.put("main", PreservationHooks.getFilteredItemList(this.getInventory().main, (Random) this.getRandom()));
        nbt.put("off_hand", PreservationHooks.getFilteredItemList(this.getInventory().offHand, (Random) this.getRandom()));
        nbt.put("armor", PreservationHooks.getFilteredItemList(this.getInventory().armor, (Random) this.getRandom()));
    }

    @Shadow
    public abstract PlayerInventory getInventory();

    public void sb$restoreFromNbt(NbtCompound nbt, UnaryOperator<ItemStack> itemProcessor) {
        PreservationHooks.processPlayerDrops((PlayerEntity) (Object) this, this.getInventory().main, PreservationHooks.readItemList(nbt.getList("main", NbtElement.COMPOUND_TYPE)), itemProcessor);
        PreservationHooks.processPlayerDrops((PlayerEntity) (Object) this, this.getInventory().offHand, PreservationHooks.readItemList(nbt.getList("off_hand", NbtElement.COMPOUND_TYPE)), itemProcessor);
        PreservationHooks.processPlayerDrops((PlayerEntity) (Object) this, this.getInventory().armor, PreservationHooks.readItemList(nbt.getList("armor", NbtElement.COMPOUND_TYPE)), itemProcessor);
    }
}