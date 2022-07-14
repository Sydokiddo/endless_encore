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
    private final PartPose bodyDefault;
    private final PartPose headDefault;
    private final PartPose leftArmDefault;
    private final PartPose rightArmDefault;

    public EtherealEntityModel(ModelPart modelPart) {
        super(modelPart, true);
        this.rightEar = this.head.getChild("right_ear");
        this.leftEar = this.head.getChild("left_ear");
        this.bodyDefault = this.body.storePose();
        this.headDefault = this.head.storePose();
        this.leftArmDefault = this.leftArm.storePose();
        this.rightArmDefault = this.rightArm.storePose();
    }

    public static MeshDefinition createMesh(CubeDeformation cubeDeformation) {
        MeshDefinition meshDefinition = PlayerModel.createMesh(cubeDeformation, true);
        PartDefinition partDefinition = meshDefinition.getRoot();
        partDefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, cubeDeformation), PartPose.ZERO);
        PartDefinition modelPartData2 = partDefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
        .texOffs(32, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.5F)), PartPose.offset(0.0F, 0.0F, 0.0F));
        modelPartData2.addOrReplaceChild("left_ear", CubeListBuilder.create().texOffs(24, 5).mirror().addBox(0.0F, -1.5F, 0.0F, 6.0F, 3.0F, 0.0F, new CubeDeformation(0.01F)).mirror(false), PartPose.offset(4.0F, -3.5F, 0.0F));
        modelPartData2.addOrReplaceChild("right_ear", CubeListBuilder.create().texOffs(24, 2).mirror().addBox(-6.0F, -1.5F, 0.0F, 6.0F, 3.0F, 0.0F, new CubeDeformation(0.01F)).mirror(false), PartPose.offset(-4.0F, -3.5F, 0.0F));
        return meshDefinition;
    }
}
