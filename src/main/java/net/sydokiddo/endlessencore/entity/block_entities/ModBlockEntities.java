package net.sydokiddo.endlessencore.entity.block_entities;

import static net.sydokiddo.endlessencore.block.ModBlocks.ETHEREAL_URN;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.registry.Registry;
import net.sydokiddo.endlessencore.EndlessEncore;
import net.sydokiddo.endlessencore.entity.block_entities.ethereal_urn.EtherealUrnBlockEntity;

public class ModBlockEntities {

    public static BlockEntityType<EtherealUrnBlockEntity> ETHEREAL_URN_BLOCK_ENTITY = FabricBlockEntityTypeBuilder.create(EtherealUrnBlockEntity::new, ETHEREAL_URN).build(null);

    public static void registerBlockEntities() {
        Registry.register(Registry.BLOCK_ENTITY_TYPE, EndlessEncore.MOD_ID + ":ethereal_urn", ETHEREAL_URN_BLOCK_ENTITY);
    }
}