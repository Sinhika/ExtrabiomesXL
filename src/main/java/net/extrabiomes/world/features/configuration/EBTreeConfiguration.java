package net.extrabiomes.world.features.configuration;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.FeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.rootplacers.RootPlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;

import java.util.List;

public class EBTreeConfiguration implements FeatureConfiguration
{

    public static final Codec<EBTreeConfiguration> CODEC = RecordCodecBuilder.create( (p_codec) -> {
        return p_codec.group(BlockStateProvider.CODEC.fieldOf("trunk_provider").forGetter((p_tp) -> {
            return p_tp.trunk_provider;
        }), BlockStateProvider.CODEC.fieldOf("foliage_provider").forGetter((p_fp) -> {
            return p_fp.foliage_provider;
        }), BlockStateProvider.CODEC.fieldOf("root_provider").forGetter((p_dp) -> {
            return p_dp.root_provider;
        }), BlockStateProvider.CODEC.fieldOf("dirt_provider").forGetter((p_dirtp) -> {
            return p_dirtp.dirt_provider;
        }), Codec.INT.fieldOf("base_height").forGetter((pbh) -> {
            return pbh.base_height;
        }), Codec.INT.fieldOf("base_height_variance").forGetter((p_bhv) -> {
            return p_bhv.base_height_variance;
        }), Codec.INT.fieldOf("canopy_start_height").forGetter((p_csh) ->{
            return p_csh.canopy_start_height;
        }), Codec.INT.fieldOf("canopy_start_variance").forGetter((p_csv) ->{
                    return p_csv.canopy_start_variance;
        }), Codec.INT.fieldOf("canopy_width").forGetter((p_cw) ->{
                    return p_cw.canopy_width;
        }),Codec.INT.fieldOf("canopy_width_variance").forGetter((p_cw) ->{
                    return p_cw.canopy_width_variance;
        }), TreeDecorator.CODEC.listOf().fieldOf("decorators").forGetter((p_cw) -> {
            return p_cw.decorators;
        })
        ).apply(p_codec, EBTreeConfiguration::new);
    }); // end CODEC

    public final BlockStateProvider trunk_provider;
    public final BlockStateProvider foliage_provider;
    public final BlockStateProvider root_provider;
    public final BlockStateProvider dirt_provider;
    public final int base_height;
    public final int base_height_variance;
    public final int canopy_start_height;
    public final int canopy_start_variance;
    public final int canopy_width;
    public final int canopy_width_variance;
    public final List<TreeDecorator> decorators;

    public EBTreeConfiguration(BlockStateProvider log, BlockStateProvider leaves, BlockStateProvider root,
                               BlockStateProvider dirt, int baseHeight, int baseHeightVariance, int canopyStartHeight,
                               int canopyStartHeightVariance, int canopyWidth, int canopyWidthVariance,
                               List<TreeDecorator> pDecorators)
    {
        this.trunk_provider = log;
        this.foliage_provider = leaves;
        this.root_provider = root;
        this.dirt_provider = dirt;
        this.base_height = baseHeight;
        this.base_height_variance = baseHeightVariance;
        this.canopy_start_height = canopyStartHeight;
        this.canopy_start_variance = canopyStartHeightVariance;
        this.canopy_width = canopyWidth;
        this.canopy_width_variance = canopyWidthVariance;
        this.decorators = pDecorators;
    } // end ctor

} // end class
