package net.sydokiddo.endlessencore.client.render;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.BipedEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory.Context;
import net.minecraft.client.render.entity.feature.ArmorFeatureRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.util.Identifier;
import net.sydokiddo.endlessencore.client.entity.model.EtherealEntityModel;
import net.sydokiddo.endlessencore.entity.ethereals.EtherealEntity;

@Environment(EnvType.CLIENT)
public class EtherealEntityRenderer extends BipedEntityRenderer<MobEntity, EtherealEntityModel<MobEntity>> {
    private static final Identifier TEXTURE = new Identifier("textures/entity/ethereal/ethereal.png");
    private static final Identifier FRENZIED_TEXTURE = new Identifier("textures/entity/ethereal/ethereal_frenzied.png");

    public EtherealEntityRenderer(Context ctx, EntityModelLayer layer, EntityModelLayer innerArmorLayer, EntityModelLayer outerArmorLayer) {
        super(ctx, new EtherealEntityModel<>(ctx.getPart(layer)), 0.5F);
        this.addFeature(new ArmorFeatureRenderer<>(this, new BipedEntityModel<>(ctx.getPart(innerArmorLayer)), new BipedEntityModel<>(ctx.getPart(outerArmorLayer))));
        this.addFeature(new EtherealEyesFeatureRenderer<>(this));
    }

    public Identifier getTexture(EtherealEntity etherealEntity) {
        return TEXTURE;
    }
}
