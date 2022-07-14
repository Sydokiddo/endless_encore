package net.sydokiddo.endlessencore.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.entity.EntityType;
import net.sydokiddo.endlessencore.EndlessEncore;
import net.sydokiddo.endlessencore.entity.ethereals.EtherealEntity;
import org.checkerframework.checker.signature.qual.Identifier;

public class ModEntities {

    public static final EntityType<EtherealEntity> ETHEREAL = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier("endlessencore", "ethereal"),
            FabricEntityTypeBuilder.create(SpawnGroup.MISC, EtherealEntity::new).dimensions(EntityDimensions.fixed(0.6f, 1.95f)).build()
    );

    public static void registerAttributes() {
        FabricDefaultAttributeRegistry.register(ETHEREAL, EtherealEntity.createEtherealAttributes());
    }

    public static void registerModEntities() {
        System.out.println("Registering Entities for " + EndlessEncore.MOD_ID);
    }
}