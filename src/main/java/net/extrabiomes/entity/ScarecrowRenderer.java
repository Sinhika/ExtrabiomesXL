package net.extrabiomes.entity;

import com.mojang.blaze3d.vertex.PoseStack;

import net.extrabiomes.ExtrabiomesXS;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class ScarecrowRenderer extends MobRenderer<ScarecrowEntity, ScarecrowModel> 
{
	private static final ResourceLocation SCARECROW_TEXTURES = 
			new ResourceLocation(ExtrabiomesXS.MODID, "textures/entity/scarecrow2.png");
	
	public ScarecrowRenderer(Context pContext) 
	{
		super(pContext, new ScarecrowModel(pContext.bakeLayer(ScarecrowModel.SCARECROW_LAYER)), 0.7F);
	}

	@Override
	public ResourceLocation getTextureLocation(ScarecrowEntity pEntity) 
	{
		return SCARECROW_TEXTURES;
	}

	@Override
	protected void scale(ScarecrowEntity pLivingEntity, PoseStack pPoseStack, float pPartialTickTime) 
	{
		pPoseStack.scale(1.25F, 1.25F, 1.25F);
	}

		
} // end class
