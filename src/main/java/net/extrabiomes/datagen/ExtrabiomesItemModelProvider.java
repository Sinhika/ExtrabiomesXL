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
		this.singleTexture("food_strawberry", mcLoc("generated"), "layer0", modLoc("item/strawberry"));
		this.singleTexture("seed_strawberry", mcLoc("generated"), "layer0", modLoc("item/seed_strawberry"));
		this.singleTexture("food_chocolate", mcLoc("generated"), "layer0", modLoc("item/chocolate"));
		this.singleTexture("food_chocolate_strawberry", mcLoc("generated"), "layer0", modLoc("item/ch_strawberry"));
		this.singleTexture("paste", mcLoc("generated"), "layer0", modLoc("item/cactuspaste"));
		this.singleTexture("log_turner", mcLoc("handheld"), "layer0", modLoc("item/logturner"));
		
	}

} // end class
