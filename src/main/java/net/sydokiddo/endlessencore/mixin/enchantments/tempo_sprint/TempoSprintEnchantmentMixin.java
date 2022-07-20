package net.sydokiddo.endlessencore.mixin.enchantments.tempo_sprint;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.*;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.sydokiddo.endlessencore.enchantment.ModEnchantments;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import java.util.UUID;

// Highly work in progress!
// Why it no work :(

@SuppressWarnings("ALL")

// Currently disabled!

@Nullable
@Mixin(LivingEntity.class)
public abstract class TempoSprintEnchantmentMixin {

    @Nullable
    private final AttributeMap attributes;
    private static final UUID SPEED_MODIFIER_TEMPO_SPRINT_UUID = UUID.fromString("662A6B8D-DA3E-4C1C-8813-96EA6097278D");

    public TempoSprintEnchantmentMixin(EntityType<? extends LivingEntity> entityType) {
        this.attributes = new AttributeMap(DefaultAttributes.getSupplier(entityType));
    }

    @Inject(method = "baseTick", at = @At("RETURN"))
    private void tempoSprint(CallbackInfo ci) {
        LivingEntity player = (LivingEntity) (Object) this;
        EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.TEMPO_SPRINT, player.getItemBySlot(EquipmentSlot.LEGS));
        if (player instanceof ServerPlayer && player.isSprinting()) {
            this.tryAddTempoSprint();
        }
        if (player instanceof ServerPlayer && !player.isSprinting()) {
            this.removeTempoSprint();
        }
    }

    @Nullable
    public AttributeInstance getAttribute(Attribute attribute) {
        assert this.getAttributes() != null;
        return this.getAttributes().getInstance(attribute);
    }

    public @Nullable AttributeMap getAttributes() {
        assert this.attributes != null;
        return this.attributes;
    }

    protected void tryAddTempoSprint() {
            LivingEntity player = (LivingEntity) (Object) this;
            int i = EnchantmentHelper.getEnchantmentLevel(ModEnchantments.TEMPO_SPRINT, player);
            if (i > 0) {
                AttributeInstance attributeInstance = this.getAttribute(Attributes.MOVEMENT_SPEED);
                assert attributeInstance != null;
                attributeInstance.addTransientModifier(new AttributeModifier(SPEED_MODIFIER_TEMPO_SPRINT_UUID, "Tempo sprint boost", 0.03F * (1.0F + (float)i * 0.35F), AttributeModifier.Operation.ADDITION));
            }
        }

    protected void removeTempoSprint() {
        AttributeInstance attributeInstance = this.getAttribute(Attributes.MOVEMENT_SPEED);
        if (attributeInstance != null) {
            if (attributeInstance.getModifier(SPEED_MODIFIER_TEMPO_SPRINT_UUID) != null) {
                attributeInstance.removeModifier(SPEED_MODIFIER_TEMPO_SPRINT_UUID);
            }
        }
    }
}