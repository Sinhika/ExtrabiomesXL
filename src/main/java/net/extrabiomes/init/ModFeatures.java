package net.extrabiomes.init;

import net.extrabiomes.ExtrabiomesXS;
import net.extrabiomes.world.features.configuration.EBTreeConfiguration;
import net.extrabiomes.world.features.tree.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModFeatures 
{
	/* FEATURES REGISTRY */
    public static final DeferredRegister<Feature<?>> FEATURES_REGISTRY =
    		DeferredRegister.create(Registries.FEATURE, ExtrabiomesXS.MODID);

	/* FEATURES */
	public static RegistryObject<Feature> FIR_TREE;
	public static RegistryObject<Feature> BIG_FIR_TREE;
	public static RegistryObject<Feature> ACACIA_TREE;
	public static RegistryObject<Feature> CYPRESS_TREE;
	public static RegistryObject<Feature> CUSTOM_SWAMP_TREE;   // maybe?
	public static RegistryObject<Feature> REDWOOD_TREE;
	public static RegistryObject<Feature> JAPANESE_MAPLE_SHRUB;
	public static RegistryObject<Feature> JAPANESE_MAPLE_TREE;
	public static RegistryObject<Feature> BALD_CYPRESS_TREE;
	public static RegistryObject<Feature> RAINBOW_EUCALYPTUS_TREE;
	public static RegistryObject<Feature> SAKURA_TREE;

	public static void setup()
	{
		FIR_TREE = FEATURES_REGISTRY.register("fir_tree", () -> new FirTreeFeature(EBTreeConfiguration.CODEC));
		BIG_FIR_TREE = FEATURES_REGISTRY.register("big_fir_tree", () -> new MegaFirTreeFeature(EBTreeConfiguration.CODEC));
		JAPANESE_MAPLE_TREE = FEATURES_REGISTRY.register("japanese_maple_tree", () -> new JapaneseMapleTreeFeature(EBTreeConfiguration.CODEC));
		JAPANESE_MAPLE_SHRUB = FEATURES_REGISTRY.register("japanese_maple_shrub", () -> new JapaneseMapleShrubFeature(EBTreeConfiguration.CODEC));
		REDWOOD_TREE = FEATURES_REGISTRY.register("redwood_tree", () -> new MegaRedwoodTreeFeature(EBTreeConfiguration.CODEC));
		ACACIA_TREE = FEATURES_REGISTRY.register("acacia_tree", () -> new AcaciaTreeFeature(EBTreeConfiguration.CODEC));
		CYPRESS_TREE = FEATURES_REGISTRY.register("cypress_tree", () -> new CypressTreeFeature(EBTreeConfiguration.CODEC));
		BALD_CYPRESS_TREE = FEATURES_REGISTRY.register("bald_cypress_tree", () -> new BaldCypressTreeFeature(EBTreeConfiguration.CODEC));
		// TODO
	} // end setup()

} // end class
