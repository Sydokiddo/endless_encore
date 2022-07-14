package net.sydokiddo.endlessencore.client.entity.model;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.entity.mob.MobEntity;

@Environment(EnvType.CLIENT)
public class EtherealEntityModel<T extends MobEntity> extends PlayerEntityModel<T> {
    public final ModelPart rightEar;
    public final ModelPart leftEar;

    public EtherealEntityModel(ModelPart modelPart) {
        super(modelPart, true);
        this.rightEar = this.head.getChild("right_ear");
        this.leftEar = this.head.getChild("left_ear");
        ModelTransform bodyRotation = this.body.getTransform();
        ModelTransform headRotation = this.head.getTransform();
        ModelTransform leftArmRotation = this.leftArm.getTransform();
        ModelTransform rightArmRotation = this.rightArm.getTransform();
    }

    public static ModelData getTexturedModelData(Dilation dilation) {
        ModelData modelData = PlayerEntityModel.getTexturedModelData(dilation, true);
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData modelPartData2 = modelPartData.addChild("head", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.0F))
        .uv(32, 0).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.5F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
        modelPartData2.addChild("left_ear", ModelPartBuilder.create().uv(24, 5).mirrored().cuboid(0.0F, -1.5F, 0.0F, 6.0F, 3.0F, 0.0F, new Dilation(0.01F)).mirrored(false), ModelTransform.pivot(4.0F, -3.5F, 0.0F));
        modelPartData2.addChild("right_ear", ModelPartBuilder.create().uv(24, 2).mirrored().cuboid(-6.0F, -1.5F, 0.0F, 6.0F, 3.0F, 0.0F, new Dilation(0.01F)).mirrored(false), ModelTransform.pivot(-4.0F, -3.5F, 0.0F));
        return modelData;
    }
}
