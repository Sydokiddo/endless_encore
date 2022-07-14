package net.sydokiddo.endlessencore.client.render;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.feature.EyesFeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.util.Identifier;
import net.sydokiddo.endlessencore.client.entity.model.EtherealEntityModel;

@Environment(EnvType.CLIENT)
public class EtherealEyesFeatureRenderer<T extends MobEntity, M extends EtherealEntityModel<T>> extends EyesFeatureRenderer<T, M> {
    private static final RenderLayer SKIN = RenderLayer.getEyes(new Identifier("textures/entity/ethereal/ethereal_eyes.png"));

    public EtherealEyesFeatureRenderer(FeatureRendererContext<T, M> featureRendererContext) {
        super(featureRendererContext);
    }

    public RenderLayer getEyesTexture() {
        return SKIN;
    }
}