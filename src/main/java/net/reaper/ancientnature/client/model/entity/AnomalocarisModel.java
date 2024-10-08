package net.reaper.ancientnature.client.model.entity;// Made with Blockbench 4.10.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.reaper.ancientnature.AncientNature;
import net.reaper.ancientnature.client.animations.entity.AnomalocarisAnimations;
import net.reaper.ancientnature.common.entity.water.Anomalocaris;

@OnlyIn(Dist.CLIENT)
public class AnomalocarisModel extends HierarchicalModel<Anomalocaris> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation ANOMALOCARIS_LAYER = new ModelLayerLocation(new ResourceLocation(
			AncientNature.MOD_ID, "anomalocaris"), "main");

	ResourceLocation EMO = new ResourceLocation(AncientNature.MOD_ID, "textures/entity/anomalocaris/emolocaris_texture.png");
	private final ModelPart body;

	public AnomalocarisModel(ModelPart root) {
		this.body = root.getChild("body");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-2.8889F, -1.6111F, -10.3333F, 6.0F, 3.0F, 18.0F, new CubeDeformation(0.0F))
				.texOffs(30, 9).addBox(-5.8889F, -1.6111F, -10.3333F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(0, 28).addBox(3.1111F, -1.6111F, -10.3333F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.1111F, 21.6111F, 0.3333F));

		PartDefinition mouth = body.addOrReplaceChild("mouth", CubeListBuilder.create().texOffs(0, 9).addBox(-1.0F, 0.0F, -5.0F, 2.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(2.1111F, -0.6111F, -10.3333F));

		PartDefinition mouth2 = body.addOrReplaceChild("mouth2", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, 0.0F, -5.0F, 2.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.8889F, -0.6111F, -10.3333F));

		PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(27, 0).addBox(-6.0F, -0.2615F, -0.3447F, 12.0F, 0.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(14, 28).addBox(-2.0F, -0.7615F, -0.3447F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.1111F, -0.1111F, 8.0F));

		PartDefinition tail2 = tail.addOrReplaceChild("tail2", CubeListBuilder.create().texOffs(0, 21).addBox(-6.0F, 0.0F, -0.5F, 12.0F, 0.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -0.2615F, 3.1553F));

		PartDefinition finn = body.addOrReplaceChild("finn", CubeListBuilder.create().texOffs(24, 37).addBox(0.0F, -0.5F, -1.0F, 3.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(3.1111F, -0.1111F, -5.3333F));

		PartDefinition finn2 = body.addOrReplaceChild("finn2", CubeListBuilder.create().texOffs(26, 34).addBox(0.0F, -0.5F, -1.0F, 4.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(3.1111F, -0.1111F, -3.3333F));

		PartDefinition finn3 = body.addOrReplaceChild("finn3", CubeListBuilder.create().texOffs(33, 27).addBox(0.0F, -0.5F, -1.0F, 5.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(3.1111F, -0.1111F, -1.3333F));

		PartDefinition finn4 = body.addOrReplaceChild("finn4", CubeListBuilder.create().texOffs(30, 6).addBox(0.0F, -0.5F, -1.0F, 6.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(3.1111F, -0.1111F, 0.6667F));

		PartDefinition finn5 = body.addOrReplaceChild("finn5", CubeListBuilder.create().texOffs(33, 24).addBox(0.0F, -0.5F, -1.0F, 5.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(3.1111F, -0.1111F, 2.6667F));

		PartDefinition finn6 = body.addOrReplaceChild("finn6", CubeListBuilder.create().texOffs(14, 34).addBox(0.0F, -0.5F, -1.0F, 4.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(3.1111F, -0.1111F, 4.6667F));

		PartDefinition finn7 = body.addOrReplaceChild("finn7", CubeListBuilder.create().texOffs(14, 37).addBox(0.0F, -0.5F, -1.0F, 3.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(3.1111F, -0.1111F, 6.6667F));

		PartDefinition finn8 = body.addOrReplaceChild("finn8", CubeListBuilder.create().texOffs(0, 37).addBox(-3.0F, -0.5F, -1.0F, 3.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.8889F, -0.1111F, -5.3333F));

		PartDefinition finn9 = body.addOrReplaceChild("finn9", CubeListBuilder.create().texOffs(0, 34).addBox(-4.0F, -0.5F, -1.0F, 4.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.8889F, -0.1111F, -3.3333F));

		PartDefinition finn10 = body.addOrReplaceChild("finn10", CubeListBuilder.create().texOffs(33, 21).addBox(-5.0F, -0.5F, -1.0F, 5.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.8889F, -0.1111F, -1.3333F));

		PartDefinition finn11 = body.addOrReplaceChild("finn11", CubeListBuilder.create().texOffs(30, 3).addBox(-6.0F, -0.5F, -1.0F, 6.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.8889F, -0.1111F, 0.6667F));

		PartDefinition finn12 = body.addOrReplaceChild("finn12", CubeListBuilder.create().texOffs(30, 15).addBox(-5.0F, -0.5F, -1.0F, 5.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.8889F, -0.1111F, 2.6667F));

		PartDefinition finn13 = body.addOrReplaceChild("finn13", CubeListBuilder.create().texOffs(33, 30).addBox(-4.0F, -0.5F, -1.0F, 4.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.8889F, -0.1111F, 4.6667F));

		PartDefinition finn14 = body.addOrReplaceChild("finn14", CubeListBuilder.create().texOffs(36, 35).addBox(-3.0F, -0.5F, -1.0F, 3.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.8889F, -0.1111F, 6.6667F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(Anomalocaris entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);

		this.animateWalk(AnomalocarisAnimations.SWIMMING, limbSwing, limbSwingAmount, 4.0F, 4.0F);
		this.animate(entity.idleAnimationState, AnomalocarisAnimations.SWIMMING, ageInTicks, 1.0F);
		this.animate(entity.attackAnimationState, AnomalocarisAnimations.ATTACK, ageInTicks, 1.0F);
		this.animate(entity.eatingAnimationState, AnomalocarisAnimations.EATING, ageInTicks, 1.0F);
		this.animate(entity.floppingAnimationState, AnomalocarisAnimations.FLOP, ageInTicks, 1.0F);

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {

		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return body;
	}
}