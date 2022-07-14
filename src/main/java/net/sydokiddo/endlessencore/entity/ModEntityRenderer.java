package net.sydokiddo.endlessencore.entity;

import com.cursedcauldron.unvotedandshelved.client.entity.model.CopperGolemModel;
import com.cursedcauldron.unvotedandshelved.client.entity.model.FrozenCopperGolemModel;
import com.cursedcauldron.unvotedandshelved.client.entity.model.GlareModel;
import com.cursedcauldron.unvotedandshelved.client.entity.render.CopperGolemRenderer;
import com.cursedcauldron.unvotedandshelved.client.entity.render.FrozenCopperGolemRenderer;
import com.cursedcauldron.unvotedandshelved.client.entity.render.GlareRenderer;
import com.cursedcauldron.unvotedandshelved.core.UnvotedAndShelved;
import com.cursedcauldron.unvotedandshelved.core.registries.USEntities;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

@Environment(EnvType.CLIENT)
public class ModEntityRenderer {
    public static final ModelLayerLocation GLARE = new ModelLayerLocation(new ResourceLocation(UnvotedAndShelved.MODID, "glare"), "main");
    public static final ModelLayerLocation COPPER_GOLEM = new ModelLayerLocation(new ResourceLocation(UnvotedAndShelved.MODID, "copper_golem"), "main");
    public static final ModelLayerLocation FROZEN_COPPER_GOLEM = new ModelLayerLocation(new ResourceLocation(UnvotedAndShelved.MODID, "oxidized_copper_golem"), "main");

    public static void registerRenderers() {
        EntityRendererRegistry.register(USEntities.GLARE, GlareRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(GLARE, GlareModel::getTexturedModelData);
        EntityRendererRegistry.register(USEntities.COPPER_GOLEM, CopperGolemRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(COPPER_GOLEM, CopperGolemModel::getLayerDefinition);
        EntityRendererRegistry.register(USEntities.FROZEN_COPPER_GOLEM, FrozenCopperGolemRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(FROZEN_COPPER_GOLEM, FrozenCopperGolemModel::getLayerDefinition);
    }
}