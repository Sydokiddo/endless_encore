package net.sydokiddo.endlessencore.client.entity.model;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Mob;

@Environment(EnvType.CLIENT)
public class EtherealEntityModel<T extends Mob> extends PlayerModel<T> {
    public final ModelPart rightEar;
    public final ModelPart leftEar;

    public EtherealEntityModel(ModelPart modelPart) {
        super(modelPart, true);
        this.rightEar = this.head.getChild("right_ear");
        this.leftEar = this.head.getChild("left_ear");
        PartPose bodyRotation = this.body.storePose();
        PartPose headRotation = this.head.storePose();
        PartPose leftArmRotation = this.leftArm.storePose();
        PartPose rightArmRotation = this.rightArm.storePose();
    }

    public static MeshDefinition getTexturedModelData(CubeDeformation dilation) {
        MeshDefinition modelData = PlayerModel.createMesh(dilation, true);
        PartDefinition modelPartData = modelData.getRoot();
        PartDefinition modelPartData2 = modelPartData.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
        .texOffs(32, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.5F)), PartPose.offset(0.0F, 0.0F, 0.0F));
        modelPartData2.addOrReplaceChild("left_ear", CubeListBuilder.create().texOffs(24, 5).mirror().addBox(0.0F, -1.5F, 0.0F, 6.0F, 3.0F, 0.0F, new CubeDeformation(0.01F)).mirror(false), PartPose.offset(4.0F, -3.5F, 0.0F));
        modelPartData2.addOrReplaceChild("right_ear", CubeListBuilder.create().texOffs(24, 2).mirror().addBox(-6.0F, -1.5F, 0.0F, 6.0F, 3.0F, 0.0F, new CubeDeformation(0.01F)).mirror(false), PartPose.offset(-4.0F, -3.5F, 0.0F));
        return modelData;
    }
}
