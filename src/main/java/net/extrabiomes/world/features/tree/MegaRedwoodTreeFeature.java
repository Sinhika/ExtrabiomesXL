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

public class MegaRedwoodTreeFeature extends EBBaseTreeFeature
{

    public MegaRedwoodTreeFeature(Codec<EBTreeConfiguration> pCodec)
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
        super.place(pContext);
        BlockPos pos = pContext.origin().immutable();
        int max_tree_altitude = pos.getY() + actual_height + 5;

        // height check
        if (pos.getY() < level.getMinBuildHeight() + 1 || max_tree_altitude > level.getMaxBuildHeight())
        {
            return false;
        }
        // Trunk check
        if (!check2x2Trunk(pos, actual_height + 1, level, false)) {
            return false;
        }

        // place the 'dirt' blocks.
        BlockPos.MutableBlockPos placePos = pos.mutable();
        BlockState dirt = treeConfig.dirt_provider.getState(sourceRand, placePos);
        placePos.move(Direction.DOWN);
        this.setBlock(level, placePos, dirt);
        placePos.move(Direction.NORTH);
        this.setBlock(level, placePos, dirt);
        placePos.move(Direction.WEST);
        this.setBlock(level, placePos, dirt);
        placePos.move(Direction.SOUTH);
        this.setBlock(level, placePos, dirt);

        // place the trunk -- TODO adapt to quarter logs.
        BlockState log_nw = treeConfig.foliage_provider.getState(sourceRand, pos);
        BlockState log_ne = treeConfig.foliage_provider.getState(sourceRand, pos);
        BlockState log_se = treeConfig.foliage_provider.getState(sourceRand, pos);
        BlockState log_sw = treeConfig.foliage_provider.getState(sourceRand, pos);
        BlockPos.MutableBlockPos nw_pos = new BlockPos.MutableBlockPos();
        BlockPos.MutableBlockPos ne_pos = new BlockPos.MutableBlockPos();
        BlockPos.MutableBlockPos se_pos = new BlockPos.MutableBlockPos();
        BlockPos.MutableBlockPos sw_pos = new BlockPos.MutableBlockPos();

        for (int offset = 0; offset <= actual_height - 4; offset++)
        {
            nw_pos.set(pos.getX() - 1, pos.getY() + offset, pos.getZ() - 1);
            ne_pos.set(pos.getX(), pos.getY() + offset, pos.getZ() - 1);
            se_pos.set(pos.getX(), pos.getY() + offset, pos.getZ());
            sw_pos.set(pos.getX() - 1, pos.getY() + offset, pos.getZ());
            this.setBlock(level, nw_pos, log_nw);
            this.setBlock(level, ne_pos, log_ne);
            this.setBlock(level, se_pos, log_se);
            this.setBlock(level, sw_pos, log_sw);
        }

        // place leaves and branches.
        for (int j3 = actual_height / 2; j3 <= actual_height - 6; j3++)
        {
            if (sourceRand.nextInt(4) == 0)
            {
                placePos.set(pos.getX(), pos.getY() + j3, pos.getZ()+1);
                generateBranches(level, sourceRand, placePos, -1, 0);
            }

            if (sourceRand.nextInt(4) == 0)
            {
                placePos.set(pos.getX()+1, pos.getY() + j3, pos.getZ()+1);
                generateBranches(level, sourceRand, placePos, 1, 0);
            }

            if (sourceRand.nextInt(4) == 0)
            {
                placePos.set(pos.getX()+1, pos.getY() + j3, pos.getZ());
                generateBranches(level, sourceRand, placePos, 0, -1);
            }

            if (sourceRand.nextInt(4) == 0)
            {
                placePos.set(pos.getX()+1, pos.getY() + j3, pos.getZ()+1);
                generateBranches(level, sourceRand, placePos, 0, 1);
            }

            if (sourceRand.nextInt(4) == 0)
            {
                placePos.set(pos.getX(), pos.getY() + j3, pos.getZ());
                generateBranches(level, sourceRand, placePos, -1, 1);
            }

            if (sourceRand.nextInt(4) == 0)
            {
                placePos.set(pos.getX(), pos.getY() + j3, pos.getZ());
                generateBranches(level, sourceRand, placePos, -1, -1);
            }

            if (sourceRand.nextInt(4) == 0)
            {
                placePos.set(pos.getX(), pos.getY() + j3, pos.getZ()+1);
                generateBranches(level, sourceRand, placePos, 1, 1);
            }

            if (sourceRand.nextInt(4) == 0)
            {
                placePos.set(pos.getX(), pos.getY() + j3, pos.getZ());
                generateBranches(level, sourceRand, placePos, 1, -1);
            }
        } // end-for

        return true;
    } // end place()

    /**
     * Actually place the branches.
     *
     * @param world     current LevalAccessor
     * @param random      current RandomSource
     * @param pos Starting position of branches
     * @param xD
     * @param dzD
     */
    @Override public boolean generateBranches(LevelAccessor world, RandomSource random, BlockPos pos, int xD,
                                              double dzD)
    {
        int zD = (int) dzD;
        BlockPos.MutableBlockPos branchpos = pos.mutable();
        int xx = pos.getX();
        int zz = pos.getZ();
        int yy = pos.getY();

        for (int br = 0; br < 9; br++)
        {
            if ((xD == -1) && (random.nextInt(3) == 0))
            {
                xx--;
            }

            if ((xD == 1) && (random.nextInt(3) == 0))
            {
                xx++;
            }

            if ((zD == -1) && (random.nextInt(3) == 0))
            {
                zz--;
            }

            if ((zD == 1) && (random.nextInt(3) == 0))
            {
                zz++;
            }

            branchpos.set(xx,yy,zz);

            if (TreeFeature.isAirOrLeaves(level, branchpos))
            {
                this.setBlock(level, branchpos, treeConfig.trunk_provider.getState(random, branchpos));
            }

            if ((br == 8) || (random.nextInt(6) == 0))
            {
                generateLeaves(world, branchpos);
            }

            yy++;
        } // end-for br

        return false;
    } // end generateBranches()

    /**
     * 
     * @param world
     * @param pos
     */
    public void generateLeaves(LevelAccessor world, BlockPos pos)
    {
        int xx = pos.getX();
        int zz = pos.getZ();
        int yy = pos.getY();
        BlockPos.MutableBlockPos leafpos = pos.mutable();
        BlockState leaves = treeConfig.foliage_provider.getState(sourceRand, pos);
        BlockState block;

        for (int ii = -3; ii <= 3; ii++)
        {
            for (int jj = -3; jj <= 3; jj++)
            {

                if (((Math.abs(ii) != 3) || (Math.abs(jj) != 3)) && ((Math.abs(ii) != 2) || (Math.abs(jj) != 3))
                        && ((Math.abs(ii) != 3) || (Math.abs(jj) != 2)))
                {
                    leafpos.set(xx + ii, yy, zz + jj);
                    block = level.getBlockState(leafpos);
                    if(block.isAir() || block.canBeReplaced())
                    {
                        this.setBlock(world, leafpos, leaves);
                    }
                }

                if ((Math.abs(ii) >= 3) || (Math.abs(jj) >= 3) || ((Math.abs(ii) == 2) && (Math.abs(jj) == 2)))
                    continue;

                leafpos.set(xx + ii, yy + 1, zz + jj);
                block = level.getBlockState(leafpos);
                //block = world.getBlock(xx + ii, yy + 1, zz + jj);
                if( block.isAir() || block.canBeReplaced())
                {
                    this.setBlock(world, leafpos, leaves);
                }

                leafpos.set(xx + ii, yy - 1, zz + jj);
                block = level.getBlockState(leafpos);

                if( block.isAir() || block.canBeReplaced())
                {
                    this.setBlock(world, leafpos, leaves);
                }
            } // end-for jj
        } // end-for ii

    } // end generateLeaves()
    
    // unused
    @Override public void generateCanopy(LevelAccessor world, RandomSource rand, BlockPos pos, double radius,
                                         int height, BlockState leaves)
    { }

} // end class
