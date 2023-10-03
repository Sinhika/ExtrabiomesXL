package net.extrabiomes.entity;

import net.extrabiomes.ExtrabiomesXS;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ScarecrowModel extends HierarchicalModel<ScarecrowEntity> 
{
	public static ModelLayerLocation SCARECROW_LAYER = 
			new ModelLayerLocation(new ResourceLocation(ExtrabiomesXS.MODID, "scarecrow"), "main");
	
	private final ModelPart root;
	@SuppressWarnings("unused")
	private final ModelPart leg;
	@SuppressWarnings("unused")
	private final ModelPart body;
	@SuppressWarnings("unused")
	private final ModelPart head;
	@SuppressWarnings("unused")
	private final ModelPart arms;

	public ScarecrowModel(ModelPart pRoot)
	{
		this.root = pRoot;
		this.head = pRoot.getChild("head");
		this.arms = pRoot.getChild("arms");
		this.body = pRoot.getChild("body");
		this.leg = pRoot.getChild("leg");
	} // end ctor
	
	@Override
	public ModelPart root() {
		return this.root;
	}

	public static LayerDefinition createBodyLayer() 
	{
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
	    partdefinition.addOrReplaceChild("head", CubeListBuilder.create()
	    		.texOffs(0, 52).addBox(-3F, -6F, -3F, 6, 6, 6).mirror(true),
	    		PartPose.offset(0F, 1F, 0F));
	    partdefinition.addOrReplaceChild("body", CubeListBuilder.create()
	    		.texOffs(0, 0).addBox(-4F, -4F, -4F, 8, 8, 8).mirror(true),
	    		PartPose.offset(0F, 5F, 0F));
	    partdefinition.addOrReplaceChild("leg", CubeListBuilder.create()
	    		.texOffs(0, 16).addBox(-1F, -29F, -1F, 2, 30, 2).mirror(true),
	    		PartPose.offset(0F, 23F, 0F));
	    partdefinition.addOrReplaceChild("arms", CubeListBuilder.create()
	    		.texOffs(32, 0).addBox(-15F, -1F, -1F, 31, 2, 2).mirror(true),
	    		PartPose.offsetAndRotation(0F, 3F, 0F, 0F, 0F, 0.10F));
		return LayerDefinition.create(meshdefinition, 128, 64);
	} // end createBodyLayer
	
	
	@Override
	public void setupAnim(ScarecrowEntity pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks,
			float pNetHeadYaw, float pHeadPitch) {
		// TODO Auto-generated method stub
		
	}

} // end class
