package net.sydokiddo.endlessencore.client.render;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Mob;
import net.sydokiddo.endlessencore.client.entity.model.EtherealEntityModel;
import net.sydokiddo.endlessencore.entity.ethereals.EtherealEntity;

@Environment(EnvType.CLIENT)
public class EtherealRenderer extends HumanoidMobRenderer<Mob, EtherealEntityModel<Mob>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("textures/entity/ethereal/ethereal.png");
    private static final ResourceLocation FRENZIED_TEXTURE = new ResourceLocation("textures/entity/ethereal/ethereal_frenzied.png");
    private static final ResourceLocation EYES_TEXTURE = new ResourceLocation("textures/entity/ethereal/ethereal_eyes.png");

    public EtherealRenderer(Context ctx, ModelLayerLocation layer, ModelLayerLocation innerArmorLayer, ModelLayerLocation outerArmorLayer) {
        super(ctx, new EtherealEntityModel<>(ctx.bakeLayer(layer)), 0.5F);
        this.addLayer(new HumanoidArmorLayer<>(this, new HumanoidModel<>(ctx.bakeLayer(innerArmorLayer)), new HumanoidModel<>(ctx.bakeLayer(outerArmorLayer))));
    }

    public ResourceLocation getTexture(EtherealEntity etherealEntity) {
        return TEXTURE;
    }
}
