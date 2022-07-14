package net.sydokiddo.endlessencore.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.core.Registry;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.sydokiddo.endlessencore.EndlessEncore;
import net.sydokiddo.endlessencore.entity.ethereals.EtherealEntity;
import net.sydokiddo.endlessencore.util.CoreRegistry;

public class ModEntities {
    public static final CoreRegistry<EntityType<?>> ENTITIES = CoreRegistry.create(Registry.ENTITY_TYPE_REGISTRY, EndlessEncore.MOD_ID);

    public static final EntityType<EtherealEntity> ETHEREAL = register("ethereal", EntityType.Builder.of(EtherealEntity::new, MobCategory.MISC).sized(0.6F, 1.95F));

    public static <T extends Entity> EntityType<T> register(String id, EntityType.Builder<T> type) {
        return ENTITIES.register(id, type.build(id));
    }

    public static void registerAttributes() {
        FabricDefaultAttributeRegistry.register(ETHEREAL, EtherealEntity.createEtherealAttributes());
    }
}