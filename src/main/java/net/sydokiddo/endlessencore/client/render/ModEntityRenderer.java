package net.sydokiddo.endlessencore.client.render;

import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.sydokiddo.endlessencore.EndlessEncore;
import net.sydokiddo.endlessencore.client.entity.model.EtherealEntityModel;
import net.sydokiddo.endlessencore.entity.ModEntities;

public class ModEntityRenderer {
    public static final ModelLayerLocation ETHEREAL = new ModelLayerLocation(new ResourceLocation(EndlessEncore.MOD_ID, "ethereal"), "main");

    public static void registerRenderers() {
        EntityRendererRegistry.register(ModEntities.ETHEREAL, EtherealRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ETHEREAL, EtherealEntityModel::getTexturedModelData);
    }
}
