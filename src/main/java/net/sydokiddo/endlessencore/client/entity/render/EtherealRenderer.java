package net.sydokiddo.endlessencore.client.entity.render;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;

public class EtherealRenderer extends MobEntityRenderer {
    public EtherealRenderer(EntityRendererFactory.Context context, EntityModel<? extends Entity> entityModel, float f) {
        super(context, entityModel, f);
    }

    @Override
    public Identifier getTexture(Entity entity) {
        return null;
    }
}
