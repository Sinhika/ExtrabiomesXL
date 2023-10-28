package net.extrabiomes.init;

import net.extrabiomes.ExtrabiomesXS;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModFeatures 
{
	/* FEATURES REGISTRY */
    public static final DeferredRegister<Feature<?>> FEATURES_REGISTRY =
    		DeferredRegister.create(Registries.FEATURE, ExtrabiomesXS.MODID);

	/* FEATURES */
	public static RegistryObject<Feature<?>> FIR_TREE;
	public static RegistryObject<Feature<?>> BIG_FIR_TREE;
	public static RegistryObject<Feature<?>> ACACIA_TREE;
	public static RegistryObject<Feature<?>> CYPRESS_TREE;
	public static RegistryObject<Feature<?>> CUSTOM_SWAMP_TREE;   // maybe?
	public static RegistryObject<Feature<?>> REDWOOD_TREE;
	public static RegistryObject<Feature<?>> JAPANESE_MAPLE_SHRUB;
	public static RegistryObject<Feature<?>> JAPANESE_MAPLE_TREE;
	public static RegistryObject<Feature<?>> BALD_CYPRESS_TREE;
	public static RegistryObject<Feature<?>> RAINBOW_EUCALYPTUS_TREE;
	public static RegistryObject<Feature<?>> SAKURA_TREE;

	public static void setup()
	{

		// TODO
	} // end setup()

} // end class
