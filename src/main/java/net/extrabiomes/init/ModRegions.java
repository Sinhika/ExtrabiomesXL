package net.extrabiomes.init;

import net.extrabiomes.ExtrabiomesXS;
import net.extrabiomes.world.regions.TemperateHillyRegion;
import net.minecraft.resources.ResourceLocation;
import terrablender.api.Regions;

/** 
 * this is where we put terrablender stuff.
 */
public final class ModRegions 
{
    public static void setup()
    {
        // Register our regions
    	Regions.register(new TemperateHillyRegion(new ResourceLocation(ExtrabiomesXS.MODID, "temperate_hilly_region"), 2));
    	
        // Register our surface rules
    	// TODO
    } // end setupTerraBlender()
    

}
