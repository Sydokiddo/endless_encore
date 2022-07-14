package net.sydokiddo.endlessencore.client.render;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Mob;
import net.sydokiddo.endlessencore.client.entity.model.EtherealEntityModel;

@Environment(EnvType.CLIENT)
public class EtherealEyesFeatureRenderer<T extends Mob, M extends EtherealEntityModel<T>> extends EyesLayer<T, M> {
    private static final RenderType SKIN = RenderType.eyes(new ResourceLocation("textures/entity/ethereal/ethereal_eyes.png"));

    public EtherealEyesFeatureRenderer(RenderLayerParent<T, M> featureRendererContext) {
        super(featureRendererContext);
    }

    public RenderType renderType() {
        return SKIN;
    }
}