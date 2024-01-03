package net.extrabiomes.world.features.tree;

import com.mojang.serialization.Codec;
import net.extrabiomes.world.features.configuration.EBTreeConfiguration;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.TreeFeature;

public class MegaFirTreeFeature extends EBBaseTreeFeature
{
    protected int canopy_depth;
    protected int min_leaf_height;

    public MegaFirTreeFeature(Codec<EBTreeConfiguration> pCodec)
    {
        super(pCodec);
    }

    /**
     * Places the given feature at the given location.
     * During world generation, features are provided with a 3x3 region of chunks, centered on the chunk being generated,
     * that they can safely generate into.
     *
     * @param pContext A context object with a reference to the level and the position the feature is being placed at
     */
    @Override public boolean place(FeaturePlaceContext<EBTreeConfiguration> pContext)
    {
        BlockPos pos = pContext.origin().immutable();
        int max_tree_altitude = pos.getY() + this.actual_height + 1;

        // height check
        if (pos.getY() < level.getMinBuildHeight() + 1 || max_tree_altitude > level.getMaxBuildHeight())
        {
            return false;
        }

        // probably min_leaf_height - was 1 + rand(12)
        this.min_leaf_height = treeConfig.canopy_start_height + sourceRand.nextInt(treeConfig.canopy_start_variance);
        // probably depth of canopy
        this.canopy_depth = this.actual_height - min_leaf_height;
        // canopy radius -- originally 2+rand(9). Clamp to total 6.
        this.actual_radius = Math.min((CANOPY_WIDTH + sourceRand.nextInt(CANOPY_WIDTH_VARIANCE)), 6);

        // make sure space for tree is clear or replaceable.
        for (int y1 = pos.getY(); y1 <= max_tree_altitude; ++y1)
        {
            int k1;
            if (y1 - pos.getY() < min_leaf_height) {
                k1 = 0;
            }
            else {
                k1 = (int) actual_radius;
            }
            for (int x1 = pos.getX() - k1; x1 <= pos.getX() + k1; x1++)
            {
                for (int z1 = pos.getZ() - k1; z1 <= pos.getZ() + k1; z1++)
                {
                    BlockPos newpos = new BlockPos(x1,y1,z1);
                    if (!TreeFeature.validTreePos(level, newpos)) {
                        return false;
                    }
                } // end-for z1
            } // end-for x1
        } // end-for y1

        // START PLACING BLOCKS
        BlockPos.MutableBlockPos placePos = pos.mutable();

        // place the 'dirt' block.
        placePos.move(Direction.DOWN);
        level.setBlock(placePos, treeConfig.dirt_provider.getState(sourceRand, placePos), 2);
        placePos.move(Direction.NORTH);
        level.setBlock(placePos, treeConfig.dirt_provider.getState(sourceRand, placePos), 2);
        placePos.move(Direction.WEST);
        level.setBlock(placePos, treeConfig.dirt_provider.getState(sourceRand, placePos), 2);
        placePos.move(Direction.SOUTH);
        level.setBlock(placePos, treeConfig.dirt_provider.getState(sourceRand, placePos), 2);

        // place leaves
        generateCanopy(this.level, sourceRand, placePos, this.actual_radius, this.actual_height,
                        treeConfig.foliage_provider.getState(sourceRand, placePos));

        // place trunk blocks
        final int j3 = sourceRand.nextInt(3);

        for (int l3 = 0; l3 < this.actual_height - j3; l3++)
        {
            //final Block block = world.getBlock(x, y + l3, z);
            BlockPos se_pos = new BlockPos(pos.getX(), pos.getY() + l3, pos.getZ());

            if (TreeFeature.validTreePos(level, se_pos))
            {
                BlockPos sw_pos = new BlockPos( pos.getX() - 1, pos.getY() + l3, pos.getZ());
                BlockPos ne_pos = new BlockPos(pos.getX(), pos.getY() + l3, pos.getZ() - 1);
                BlockPos nw_pos = new BlockPos(pos.getX() - 1, pos.getY() + l3, pos.getZ() - 1);

                // TODO revise for quarter blocks
                level.setBlock(se_pos, treeConfig.trunk_provider.getState(sourceRand, se_pos), 2);
                this.posTrunks.add(se_pos.immutable());
                level.setBlock(sw_pos, treeConfig.trunk_provider.getState(sourceRand, sw_pos), 2);
                this.posTrunks.add(sw_pos.immutable());
                level.setBlock(ne_pos, treeConfig.trunk_provider.getState(sourceRand, ne_pos), 2);
                this.posTrunks.add(ne_pos.immutable());
                level.setBlock(nw_pos, treeConfig.trunk_provider.getState(sourceRand, nw_pos), 2);
                this.posTrunks.add(nw_pos.immutable());
            } // end-if
        } // end-for l3

        placeDecorators();
        return true;
    } // end place()

    /**
     * Generate the tree's canopy.
     *
     * @param world  LevelAccessor
     * @param rand   a RandomSource
     * @param pos    BlockPos of center of canopy
     * @param radius canopy radius
     * @param height canopy height
     * @param leaves leaf BlockState
     */
    @Override public void generateCanopy(LevelAccessor world, RandomSource rand, BlockPos pos, double radius,
                                         int height, BlockState leaves)
    {
        int l1 = rand.nextInt(2);  // TODO: do these constants need to be config VARIABLES?
        int j2 = 1;
        boolean flag1 = false;

        // place leaves.
        for (int i3 = 0; i3 <= canopy_depth; i3++)
        {
            final int k3 = pos.getY() + this.actual_height - i3;
            for (int i4 = pos.getX() - l1; i4 <= pos.getX() + l1; i4++)
            {
                final int k4 = i4 - pos.getX();
                for (int l4 = pos.getZ() - l1; l4 <= pos.getZ() + l1; l4++)
                {
                    final int i5 = l4 - pos.getZ();
                    if (Math.abs(k4) != l1 || Math.abs(i5) != l1 || l1 <= 0)
                    {
                        BlockPos newpos = new BlockPos(i4, k3, l4);
                        if (TreeFeature.validTreePos(level, newpos))
                        {
                            world.setBlock(newpos, leaves, 2);
                            this.posLeaves.add(newpos.immutable());
                        }
                        newpos = new BlockPos(i4 - 1, k3, l4);
                        if (TreeFeature.validTreePos(level, newpos))
                        {
                            world.setBlock(newpos, leaves, 2);
                            this.posLeaves.add(newpos.immutable());
                        }

                        newpos = new BlockPos(i4, k3, l4 - 1);
                        if (TreeFeature.validTreePos(level, newpos))
                        {
                            world.setBlock(newpos, leaves, 2);
                            this.posLeaves.add(newpos.immutable());
                        }

                        newpos = new BlockPos(i4 - 1, k3, l4 - 1);
                        if (TreeFeature.validTreePos(level, newpos))
                        {
                            world.setBlock(newpos, leaves, 2);
                            this.posLeaves.add(newpos.immutable());
                        }
                    } // end-if
                } // end-for l4
            } // end-for i4

            if (l1 >= j2)
            {
                l1 = flag1 ? 1 : 0;
                flag1 = true;

                if (++j2 > radius)
                {
                    j2 = (int) radius;
                }
            }
            else
            {
                l1++;
            }
        } // end-for i3

    } // end generateCanopy()

    /**
     * Actually place the branches.
     *
     * @param world     current LevalAccessor
     * @param rand      current RandomSource
     * @param branchpos Starting position of branches
     * @param height    canopy height
     * @param radius    canopy radius
     * @return true if successfully placed, false if not.
     */
    @Override public boolean generateBranches(LevelAccessor world, RandomSource rand, BlockPos branchpos, int height,
                                              double radius)
    {
        return false;
    }


} // end-class
