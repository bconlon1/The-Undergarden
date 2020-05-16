package quek.undergarden.world.gen.feature;

import com.mojang.datafixers.Dynamic;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.KelpTopBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import quek.undergarden.block.world.GlowingKelpTopBlock;
import quek.undergarden.registry.UndergardenBlocks;

import java.util.Random;
import java.util.function.Function;

public class GlowingKelpFeature extends Feature<NoFeatureConfig> {

    public GlowingKelpFeature(Function<Dynamic<?>, ? extends NoFeatureConfig> configFactoryIn) {
        super(configFactoryIn);
    }

    @Override
    public boolean place(IWorld worldIn, ChunkGenerator<? extends GenerationSettings> generator, Random rand, BlockPos pos, NoFeatureConfig config) {
        int i = 0;
        int ocean_y = worldIn.getSeaLevel();
        BlockPos blockpos = new BlockPos(pos.getX(), pos.getY(), pos.getZ());
        if(pos.getY() < ocean_y) {
            if (worldIn.getBlockState(blockpos).getBlock() == Blocks.WATER) {
                BlockState kelp = UndergardenBlocks.glowing_kelp.get().getDefaultState();
                BlockState kelp_top = UndergardenBlocks.glowing_kelp_plant.get().getDefaultState();
                int k = 1 + rand.nextInt(10);

                for(int l = 0; l <= k; ++l) {
                    if (worldIn.getBlockState(blockpos).getBlock() == Blocks.WATER && worldIn.getBlockState(blockpos.up()).getBlock() == Blocks.WATER && kelp_top.isValidPosition(worldIn, blockpos)) {
                        if (l == k) {
                            worldIn.setBlockState(blockpos, kelp.with(GlowingKelpTopBlock.AGE, rand.nextInt(4) + 20), 2);
                            ++i;
                        } else {
                            worldIn.setBlockState(blockpos, kelp_top, 2);
                        }
                    } else if (l > 0) {
                        BlockPos blockpos1 = blockpos.down();
                        if (kelp.isValidPosition(worldIn, blockpos1) && worldIn.getBlockState(blockpos1.down()).getBlock() != UndergardenBlocks.glowing_kelp.get()) {
                            worldIn.setBlockState(blockpos1, kelp.with(GlowingKelpTopBlock.AGE, rand.nextInt(4) + 20), 2);
                            ++i;
                        }
                        break;
                    }

                    blockpos = blockpos.up();
                }
            }
        }
        return i > 0;
    }
}
