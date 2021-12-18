package quek.undergarden.registry;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HugeMushroomBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BushFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.DarkOakFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.ForkingTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.MegaJungleTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.minecraft.world.level.material.Fluids;
import quek.undergarden.Undergarden;
import quek.undergarden.block.BlisterberryBushBlock;
import quek.undergarden.block.DitchbulbBlock;
import quek.undergarden.block.UnderbeanBushBlock;
import quek.undergarden.world.gen.treedecorator.GrongleLeafDecorator;
import quek.undergarden.world.gen.trunkplacer.SmogstemTrunkPlacer;

import java.util.List;

public class UGConfiguredFeatures {
    //ore tags
    public static final RuleTest BASE_STONE_UNDERGARDEN = new TagMatchTest(UGTags.Blocks.BASE_STONE_UNDERGARDEN);
    public static final RuleTest DEPTHROCK_ORE_REPLACEABLES = new TagMatchTest(UGTags.Blocks.DEPTHROCK_ORE_REPLACEABLES);
    public static final RuleTest SHIVERSTONE_ORE_REPLACEABLES = new TagMatchTest(UGTags.Blocks.SHIVERSTONE_ORE_REPLACEABLES);
    public static final RuleTest TREMBLECRUST_ORE_REPLACEABLES = new TagMatchTest(UGTags.Blocks.TREMBLECRUST_ORE_REPLACEABLES);

    //ore targets
    private static final ImmutableList<OreConfiguration.TargetBlockState> COAL_ORE_TARGETS = ImmutableList.of(OreConfiguration.target(DEPTHROCK_ORE_REPLACEABLES, UGBlocks.DEPTHROCK_COAL_ORE.get().defaultBlockState()), OreConfiguration.target(SHIVERSTONE_ORE_REPLACEABLES, UGBlocks.SHIVERSTONE_COAL_ORE.get().defaultBlockState()));
    private static final ImmutableList<OreConfiguration.TargetBlockState> IRON_ORE_TARGETS = ImmutableList.of(OreConfiguration.target(DEPTHROCK_ORE_REPLACEABLES, UGBlocks.DEPTHROCK_IRON_ORE.get().defaultBlockState()), OreConfiguration.target(SHIVERSTONE_ORE_REPLACEABLES, UGBlocks.SHIVERSTONE_IRON_ORE.get().defaultBlockState()));
    private static final ImmutableList<OreConfiguration.TargetBlockState> GOLD_ORE_TARGETS = ImmutableList.of(OreConfiguration.target(DEPTHROCK_ORE_REPLACEABLES, UGBlocks.DEPTHROCK_GOLD_ORE.get().defaultBlockState()));
    private static final ImmutableList<OreConfiguration.TargetBlockState> DIAMOND_ORE_TARGETS = ImmutableList.of(OreConfiguration.target(DEPTHROCK_ORE_REPLACEABLES, UGBlocks.DEPTHROCK_DIAMOND_ORE.get().defaultBlockState()), OreConfiguration.target(SHIVERSTONE_ORE_REPLACEABLES, UGBlocks.SHIVERSTONE_DIAMOND_ORE.get().defaultBlockState()));
    private static final ImmutableList<OreConfiguration.TargetBlockState> CLOGGRUM_ORE_TARGETS = ImmutableList.of(OreConfiguration.target(DEPTHROCK_ORE_REPLACEABLES, UGBlocks.DEPTHROCK_CLOGGRUM_ORE.get().defaultBlockState()), OreConfiguration.target(SHIVERSTONE_ORE_REPLACEABLES, UGBlocks.SHIVERSTONE_CLOGGRUM_ORE.get().defaultBlockState()));
    private static final ImmutableList<OreConfiguration.TargetBlockState> FROSTSTEEL_ORE_TARGETS = ImmutableList.of(OreConfiguration.target(SHIVERSTONE_ORE_REPLACEABLES, UGBlocks.SHIVERSTONE_FROSTSTEEL_ORE.get().defaultBlockState()));
    private static final ImmutableList<OreConfiguration.TargetBlockState> UTHERIUM_ORE_TARGETS = ImmutableList.of(OreConfiguration.target(DEPTHROCK_ORE_REPLACEABLES, UGBlocks.DEPTHROCK_UTHERIUM_ORE.get().defaultBlockState()), OreConfiguration.target(SHIVERSTONE_ORE_REPLACEABLES, UGBlocks.SHIVERSTONE_UTHERIUM_ORE.get().defaultBlockState()), OreConfiguration.target(TREMBLECRUST_ORE_REPLACEABLES, UGBlocks.TREMBLECRUST_UTHERIUM_ORE.get().defaultBlockState()));
    private static final ImmutableList<OreConfiguration.TargetBlockState> REGALIUM_ORE_TARGETS = ImmutableList.of(OreConfiguration.target(DEPTHROCK_ORE_REPLACEABLES, UGBlocks.DEPTHROCK_REGALIUM_ORE.get().defaultBlockState()), OreConfiguration.target(SHIVERSTONE_ORE_REPLACEABLES, UGBlocks.SHIVERSTONE_REGALIUM_ORE.get().defaultBlockState()));

    //ores
    public static final ConfiguredFeature<?, ?> COAL_ORE = register("coal_ore", Feature.ORE.configured(new OreConfiguration(COAL_ORE_TARGETS, 17)));
    public static final ConfiguredFeature<?, ?> IRON_ORE = register("iron_ore", Feature.ORE.configured(new OreConfiguration(IRON_ORE_TARGETS, 9, 0.5F)));
    public static final ConfiguredFeature<?, ?> GOLD_ORE = register("gold_ore", Feature.ORE.configured(new OreConfiguration(GOLD_ORE_TARGETS, 9, 0.5F)));
    public static final ConfiguredFeature<?, ?> DIAMOND_ORE = register("diamond_ore", Feature.ORE.configured(new OreConfiguration(DIAMOND_ORE_TARGETS, 8, 0.5F)));
    public static final ConfiguredFeature<?, ?> CLOGGRUM_ORE = register("cloggrum_ore", Feature.ORE.configured(new OreConfiguration(CLOGGRUM_ORE_TARGETS, 9)));
    public static final ConfiguredFeature<?, ?> FROSTSTEEL_ORE = register("froststeel_ore", Feature.ORE.configured(new OreConfiguration(FROSTSTEEL_ORE_TARGETS, 9)));
    public static final ConfiguredFeature<?, ?> UTHERIUM_ORE = register("utherium_ore", Feature.ORE.configured(new OreConfiguration(UTHERIUM_ORE_TARGETS, 8, 0.5F)));
    public static final ConfiguredFeature<?, ?> REGALIUM_ORE = register("regalium_ore", Feature.ORE.configured(new OreConfiguration(REGALIUM_ORE_TARGETS, 4)));
    public static final ConfiguredFeature<?, ?> SHIVERSTONE_ORE = register("shiverstone_ore", Feature.ORE.configured(new OreConfiguration(BASE_STONE_UNDERGARDEN, UGBlocks.SHIVERSTONE.get().defaultBlockState(), 33)));
    public static final ConfiguredFeature<?, ?> DEEPSOIL_ORE = register("deepsoil_ore", Feature.ORE.configured(new OreConfiguration(BASE_STONE_UNDERGARDEN, UGBlocks.DEEPSOIL.get().defaultBlockState(), 33)));
    public static final ConfiguredFeature<?, ?> ICE_ORE = register("ice_ore", Feature.ORE.configured(new OreConfiguration(BASE_STONE_UNDERGARDEN, Blocks.PACKED_ICE.defaultBlockState(), 33)));
    public static final ConfiguredFeature<?, ?> SEDIMENT_ORE = register("sediment_ore", Feature.ORE.configured(new OreConfiguration(BASE_STONE_UNDERGARDEN, UGBlocks.SEDIMENT.get().defaultBlockState(), 33)));

    //springs
    public static final ConfiguredFeature<?, ?> SPRING = register("spring", Feature.SPRING.configured(new SpringConfiguration(Fluids.WATER.defaultFluidState(), false, 4, 1, ImmutableSet.of(UGBlocks.DEPTHROCK.get(), UGBlocks.SHIVERSTONE.get(), UGBlocks.DEEPSOIL.get()))));
    public static final ConfiguredFeature<?, ?> VIRULENT_MIX_SPRING = register("virulent_mix_spring", Feature.SPRING.configured(new SpringConfiguration(UGFluids.VIRULENT_MIX_SOURCE.get().defaultFluidState(), false, 4, 1, ImmutableSet.of(UGBlocks.DEPTHROCK.get(), UGBlocks.SHIVERSTONE.get(), UGBlocks.DEEPSOIL.get()))));

    //deltas
    public static final ConfiguredFeature<?, ?> BOG_DELTA = register("bog_delta", Feature.DELTA_FEATURE.configured(new DeltaFeatureConfiguration(UGBlocks.VIRULENT_MIX.get().defaultBlockState(), UGBlocks.COARSE_DEEPSOIL.get().defaultBlockState(), UniformInt.of(6, 8), UniformInt.of(2, 4))));
    public static final ConfiguredFeature<?, ?> GRONGLEGROWTH_DELTA = register("gronglegrowth_delta", Feature.DELTA_FEATURE.configured(new DeltaFeatureConfiguration(Blocks.WATER.defaultBlockState(), UGBlocks.SEDIMENT.get().defaultBlockState(), UniformInt.of(3, 4), UniformInt.of(2, 4))));

    //vegetation
    public static final ConfiguredFeature<RandomPatchConfiguration, ?> DEEPTURF_PATCH = register("deepturf_patch", Feature.RANDOM_PATCH.configured(patch(UGBlocks.DEEPTURF.get(), 64)));
    public static final ConfiguredFeature<RandomPatchConfiguration, ?> ASHEN_DEEPTURF_PATCH = register("ashen_deepturf_patch", Feature.RANDOM_PATCH.configured(patch(UGBlocks.ASHEN_DEEPTURF.get(), 64)));
    public static final ConfiguredFeature<RandomPatchConfiguration, ?> FROZEN_DEEPTURF_PATCH = register("frozen_deepturf_patch", Feature.RANDOM_PATCH.configured(patch(UGBlocks.FROZEN_DEEPTURF.get(), 64)));
    public static final ConfiguredFeature<RandomPatchConfiguration, ?> SHIMMERWEED_PATCH = register("shimmerweed_patch", Feature.RANDOM_PATCH.configured(patch(UGBlocks.SHIMMERWEED.get(), 32)));
    public static final ConfiguredFeature<RandomPatchConfiguration, ?> DEPTHROCK_PEBBLE_PATCH = register("depthrock_pebble_patch", Feature.RANDOM_PATCH.configured(patch(UGBlocks.DEPTHROCK_PEBBLES.get(), 32, List.of(UGBlocks.DEEPTURF_BLOCK.get(), UGBlocks.ASHEN_DEEPTURF_BLOCK.get(), UGBlocks.DEPTHROCK.get(), UGBlocks.SHIVERSTONE.get(), UGBlocks.SEDIMENT.get(), UGBlocks.COARSE_DEEPSOIL.get()))));
    public static final ConfiguredFeature<RandomPatchConfiguration, ?> DITCHBULB_PATCH = register("ditchbulb_patch", Feature.RANDOM_PATCH.configured(patch(UGBlocks.DITCHBULB_PLANT.get().defaultBlockState().setValue(DitchbulbBlock.AGE, 1), 16, List.of(UGBlocks.DEPTHROCK.get()))));
    public static final ConfiguredFeature<RandomPatchConfiguration, ?> TALL_DEEPTURF_PATCH = register("tall_deepturf_patch", Feature.RANDOM_PATCH.configured(patch(UGBlocks.TALL_DEEPTURF.get(), 32)));
    public static final ConfiguredFeature<RandomPatchConfiguration, ?> TALL_SHIMMERWEED_PATCH = register("tall_shimmerweed_patch", Feature.RANDOM_PATCH.configured(patch(UGBlocks.TALL_SHIMMERWEED.get(), 32)));
    public static final ConfiguredFeature<RandomPatchConfiguration, ?> INDIGO_MUSHROOM_PATCH = register("indigo_mushroom_patch", Feature.RANDOM_PATCH.configured(patch(UGBlocks.INDIGO_MUSHROOM.get(), 64)));
    public static final ConfiguredFeature<RandomPatchConfiguration, ?> VEIL_MUSHROOM_PATCH = register("veil_mushroom_patch", Feature.RANDOM_PATCH.configured(patch(UGBlocks.VEIL_MUSHROOM.get(), 64)));
    public static final ConfiguredFeature<RandomPatchConfiguration, ?> INK_MUSHROOM_PATCH = register("ink_mushroom_patch", Feature.RANDOM_PATCH.configured(patch(UGBlocks.DEEPTURF.get(), 64)));
    public static final ConfiguredFeature<RandomPatchConfiguration, ?> BLOOD_MUSHROOM_PATCH = register("blood_mushroom_patch", Feature.RANDOM_PATCH.configured(patch(UGBlocks.DEEPTURF.get(), 64)));
    public static final ConfiguredFeature<RandomPatchConfiguration, ?> UNDERBEAN_BUSH_PATCH = register("underbean_bush_patch", Feature.RANDOM_PATCH.configured(patch(UGBlocks.UNDERBEAN_BUSH.get().defaultBlockState().setValue(UnderbeanBushBlock.AGE, 3), 64, List.of(UGBlocks.DEEPTURF_BLOCK.get()))));
    public static final ConfiguredFeature<RandomPatchConfiguration, ?> BLISTERBERRY_BUSH_PATCH = register("blisterberry_bush_patch", Feature.RANDOM_PATCH.configured(patch(UGBlocks.BLISTERBERRY_BUSH.get().defaultBlockState().setValue(BlisterberryBushBlock.AGE, 3), 64, List.of(UGBlocks.ASHEN_DEEPTURF_BLOCK.get()))));
    public static final ConfiguredFeature<RandomPatchConfiguration, ?> GLOOMGOURD_PATCH = register("gloomgourd_patch", Feature.RANDOM_PATCH.configured(patch(UGBlocks.GLOOMGOURD.get(), 16, List.of(UGBlocks.DEEPTURF_BLOCK.get()))));
    public static final ConfiguredFeature<?, ?> DROOPVINE = register("droopvine", UGFeatures.DROOPVINE.get().configured(FeatureConfiguration.NONE));
    public static final ConfiguredFeature<?, ?> GLITTERKELP = register("glitterkelp", UGFeatures.GLOWING_KELP.get().configured(FeatureConfiguration.NONE));

    //tree
    public static final ConfiguredFeature<TreeConfiguration, ?> SMOGSTEM_TREE = register("smogstem_tree", Feature.TREE.configured(new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(UGBlocks.SMOGSTEM_LOG.get()), new SmogstemTrunkPlacer(10, 2, 2), BlockStateProvider.simple(UGBlocks.SMOGSTEM_LEAVES.get()), new BlobFoliagePlacer(ConstantInt.of(3), ConstantInt.of(0), 2), new TwoLayersFeatureSize(1, 1, 2)).ignoreVines().dirt(BlockStateProvider.simple(UGBlocks.DEEPSOIL.get())).build()));
    public static final ConfiguredFeature<TreeConfiguration, ?> TALL_SMOGSTEM_TREE = register("tall_smogstem_tree", Feature.TREE.configured(new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(UGBlocks.SMOGSTEM_LOG.get()), new SmogstemTrunkPlacer(15, 4, 4), BlockStateProvider.simple(UGBlocks.SMOGSTEM_LEAVES.get()), new BlobFoliagePlacer(ConstantInt.of(3), ConstantInt.of(0), 2), new TwoLayersFeatureSize(1, 1, 2)).ignoreVines().dirt(BlockStateProvider.simple(UGBlocks.DEEPSOIL.get())).build()));
    public static final ConfiguredFeature<TreeConfiguration, ?> WIGGLEWOOD_TREE = register("wigglewood_tree", Feature.TREE.configured(new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(UGBlocks.WIGGLEWOOD_LOG.get()), new ForkingTrunkPlacer(3, 1, 1), BlockStateProvider.simple(UGBlocks.WIGGLEWOOD_LEAVES.get()), new BushFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 0), new TwoLayersFeatureSize(1, 0, 2)).ignoreVines().dirt(BlockStateProvider.simple(UGBlocks.DEEPSOIL.get())).build()));
    public static final ConfiguredFeature<TreeConfiguration, ?> TALL_WIGGLEWOOD_TREE = register("tall_wigglewood_tree", Feature.TREE.configured(new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(UGBlocks.WIGGLEWOOD_LOG.get()), new ForkingTrunkPlacer(6, 1, 1), BlockStateProvider.simple(UGBlocks.WIGGLEWOOD_LEAVES.get()), new BushFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 0), new TwoLayersFeatureSize(1, 0, 2)).ignoreVines().dirt(BlockStateProvider.simple(UGBlocks.DEEPSOIL.get())).build()));
    public static final ConfiguredFeature<TreeConfiguration, ?> GRONGLE_TREE = register("grongle_tree", Feature.TREE.configured(new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(UGBlocks.GRONGLE_LOG.get()), new MegaJungleTrunkPlacer(10, 2, 19), BlockStateProvider.simple(UGBlocks.GRONGLE_LEAVES.get()), new DarkOakFoliagePlacer(ConstantInt.of(0), ConstantInt.of(0)), new TwoLayersFeatureSize(1, 1, 2)).ignoreVines().dirt(BlockStateProvider.simple(UGBlocks.DEEPSOIL.get())).decorators(ImmutableList.of(GrongleLeafDecorator.INSTANCE)).build()));
    public static final ConfiguredFeature<TreeConfiguration, ?> SMALL_GRONGLE_TREE = register("small_grongle_tree", Feature.TREE.configured(new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(UGBlocks.GRONGLE_LOG.get()), new StraightTrunkPlacer(5, 2, 19), BlockStateProvider.simple(UGBlocks.GRONGLE_LEAVES.get()), new DarkOakFoliagePlacer(ConstantInt.of(0), ConstantInt.of(0)), new TwoLayersFeatureSize(1, 0, 1)).ignoreVines().dirt(BlockStateProvider.simple(UGBlocks.DEEPSOIL.get())).decorators(ImmutableList.of(GrongleLeafDecorator.INSTANCE)).build()));
    public static final ConfiguredFeature<TreeConfiguration, ?> GRONGLE_BUSH = register("grongle_bush", Feature.TREE.configured(new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(UGBlocks.GRONGLE_LOG.get()), new StraightTrunkPlacer(1, 0, 0), BlockStateProvider.simple(UGBlocks.GRONGLE_LEAVES.get()), new DarkOakFoliagePlacer(ConstantInt.of(0), ConstantInt.of(0)), new TwoLayersFeatureSize(0, 0, 0)).ignoreVines().dirt(BlockStateProvider.simple(UGBlocks.DEEPSOIL.get())).build()));

    //huge mushrooms
    public static final ConfiguredFeature<HugeMushroomFeatureConfiguration, ?> HUGE_INDIGO_MUSHROOM = register("huge_indigo_mushroom", Feature.HUGE_BROWN_MUSHROOM.configured(new HugeMushroomFeatureConfiguration(BlockStateProvider.simple(UGBlocks.INDIGO_MUSHROOM_CAP.get().defaultBlockState().setValue(HugeMushroomBlock.UP, true)), BlockStateProvider.simple(UGBlocks.INDIGO_MUSHROOM_STALK.get().defaultBlockState().setValue(HugeMushroomBlock.UP, false).setValue(HugeMushroomBlock.DOWN, false)), 3)));
    public static final ConfiguredFeature<HugeMushroomFeatureConfiguration, ?> HUGE_VEIL_MUSHROOM = register("huge_veil_mushroom", UGFeatures.VEIL_MUSHROOM.get().configured(new HugeMushroomFeatureConfiguration(BlockStateProvider.simple(UGBlocks.VEIL_MUSHROOM_CAP.get().defaultBlockState().setValue(HugeMushroomBlock.DOWN, false)), BlockStateProvider.simple(UGBlocks.VEIL_MUSHROOM_STALK.get().defaultBlockState().setValue(HugeMushroomBlock.UP, false).setValue(HugeMushroomBlock.DOWN, false)), 2)));
    public static final ConfiguredFeature<HugeMushroomFeatureConfiguration, ?> HUGE_INK_MUSHROOM = register("huge_ink_mushroom", UGFeatures.INK_MUSHROOM.get().configured(new HugeMushroomFeatureConfiguration(BlockStateProvider.simple(UGBlocks.INK_MUSHROOM_CAP.get().defaultBlockState().setValue(HugeMushroomBlock.UP, true)), BlockStateProvider.simple(Blocks.MUSHROOM_STEM.defaultBlockState().setValue(HugeMushroomBlock.UP, false).setValue(HugeMushroomBlock.DOWN, false)), 5)));
    public static final ConfiguredFeature<HugeMushroomFeatureConfiguration, ?> HUGE_BLOOD_MUSHROOM = register("huge_blood_mushroom", UGFeatures.BLOOD_MUSHROOM.get().configured(new HugeMushroomFeatureConfiguration(BlockStateProvider.simple(UGBlocks.BLOOD_MUSHROOM_CAP.get().defaultBlockState().setValue(HugeMushroomBlock.DOWN, false)), BlockStateProvider.simple(UGBlocks.BLOOD_MUSHROOM_STALK.get().defaultBlockState().setValue(HugeMushroomBlock.UP, false).setValue(HugeMushroomBlock.DOWN, false)), 3)));

    //rocks
    public static final ConfiguredFeature<BlockStateConfiguration, ?> DEPTHROCK_ROCK = register("depthrock_rock", Feature.FOREST_ROCK.configured(new BlockStateConfiguration(UGBlocks.DEPTHROCK.get().defaultBlockState())));
    public static final ConfiguredFeature<BlockStateConfiguration, ?> SHIVERSTONE_ROCK = register("shiverstone_rock", Feature.FOREST_ROCK.configured(new BlockStateConfiguration(UGBlocks.SHIVERSTONE.get().defaultBlockState())));

    //misc
    public static final ConfiguredFeature<?, ?> SMOG_VENT = register("smog_vent", UGFeatures.SMOG_VENT.get().configured(FeatureConfiguration.NONE));
    public static final ConfiguredFeature<?, ?> ICE_PILLAR = register("ice_pillar", UGFeatures.ICE_PILLAR.get().configured(FeatureConfiguration.NONE));

    private static RandomPatchConfiguration patch(Block block, int tries) {
        return FeatureUtils.simpleRandomPatchConfiguration(tries, Feature.SIMPLE_BLOCK.configured(new SimpleBlockConfiguration(BlockStateProvider.simple(block))).onlyWhenEmpty());
    }

    private static RandomPatchConfiguration patch(BlockState block, int tries) {
        return FeatureUtils.simpleRandomPatchConfiguration(tries, Feature.SIMPLE_BLOCK.configured(new SimpleBlockConfiguration(BlockStateProvider.simple(block))).onlyWhenEmpty());
    }

    private static RandomPatchConfiguration patch(Block block, int tries, List<Block> whitelist) {
        return FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK.configured(new SimpleBlockConfiguration(BlockStateProvider.simple(block))), whitelist, tries);
    }

    private static RandomPatchConfiguration patch(BlockState block, int tries, List<Block> whitelist) {
        return FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK.configured(new SimpleBlockConfiguration(BlockStateProvider.simple(block))), whitelist, tries);
    }

    private static <FC extends FeatureConfiguration> ConfiguredFeature<FC, ?> register(String name, ConfiguredFeature<FC, ?> feature) {
        return Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new ResourceLocation(Undergarden.MODID, name), feature);
    }

    public static void init() {}
}