package net.extrabiomes.datagen;

import net.extrabiomes.ExtrabiomesXS;
import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ExtrabiomesItemModelProvider extends ItemModelProvider {

	public ExtrabiomesItemModelProvider(PackOutput output,ExistingFileHelper existingFileHelper) 
	{
		super(output, ExtrabiomesXS.MODID, existingFileHelper);
	}

	@Override
	protected void registerModels() 
	{
		this.singleTexture("crop.strawberry", mcLoc("generated"), "layer0", modLoc("item/strawberry"));

	}

} // end class
