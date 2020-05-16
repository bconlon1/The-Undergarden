package quek.undergarden;

import io.netty.buffer.Unpooled;
import net.minecraft.data.DataGenerator;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.carver.WorldCarver;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.minecraft.world.gen.placement.IPlacementConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.world.RegisterDimensionsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ObjectHolder;

import quek.undergarden.biome.UndergardenBiome;
import quek.undergarden.data.*;
import quek.undergarden.registry.*;
import quek.undergarden.world.gen.carver.*;

import java.util.UUID;

@Mod(UndergardenMod.MODID)
public class UndergardenMod {
	
	public static final String MODID = "undergarden";

	public static DimensionType undergarden_dimension;

	public UndergardenMod() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

		bus.addListener(this::setup);
		bus.addListener(this::clientSetup);
		bus.addListener(this::gatherData);

		UndergardenEntities.ENTITIES.register(bus);
		UndergardenBlocks.BLOCKS.register(bus);
		UndergardenItems.ITEMS.register(bus);
		UndergardenDimensions.MOD_DIMENSIONS.register(bus);
		UndergardenBiomes.BIOMES.register(bus);
		UndergardenFeatures.FEATURES.register(bus);
		UndergardenWorldCarvers.CARVERS.register(bus);
	}

	public void setup(FMLCommonSetupEvent event) {
		UndergardenBiomes.addBiomeTypes();
		UndergardenBiomes.addBiomeFeatures();
		UndergardenEntities.spawnPlacements();
		for(Biome biome : ForgeRegistries.BIOMES) {
			if(biome.getCategory() == Biome.Category.EXTREME_HILLS && !(biome instanceof UndergardenBiome)) {
				biome.addStructure(UndergardenFeatures.OVERWORLD_PORTAL_RUIN.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
				biome.addFeature(GenerationStage.Decoration.UNDERGROUND_STRUCTURES, UndergardenFeatures.OVERWORLD_PORTAL_RUIN.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG)
						.withPlacement(Placement.NOPE.configure(IPlacementConfig.NO_PLACEMENT_CONFIG)));
			}
		}
	}

	public void clientSetup(FMLClientSetupEvent event) {
		ClientStuff.registerBlockRenderers();
		ClientStuff.registerEntityRenderers();
	}


	public void gatherData(GatherDataEvent event) {
		DataGenerator generator = event.getGenerator();
		generator.addProvider(new UndergardenLang(generator));
		generator.addProvider(new UndergardenRecipes(generator));
		generator.addProvider(new UndergardenLootTables(generator));
		generator.addProvider(new UndergardenBlockStates(generator, event.getExistingFileHelper()));
		generator.addProvider(new UndergardenItemModels(generator, event.getExistingFileHelper()));
	}

	@Mod.EventBusSubscriber(modid = MODID)
	public static class ForgeEventBus {

		@SubscribeEvent
		@OnlyIn(Dist.CLIENT)
		public static void renderPlayerEvent(RenderPlayerEvent event) {
			if(event.getEntity() instanceof PlayerEntity && UUID.fromString("353a859b-ba16-4e6a-8f63-9a8c79ab0071").equals(event.getEntity().getUniqueID())) {
				event.getMatrixStack().scale(.5F, .5F, .5F);
			}
			if(event.getEntity() instanceof PlayerEntity && UUID.fromString("925e5f40-b7d2-4614-8491-c1bc13d8223d").equals(event.getEntity().getUniqueID())) {
				event.getMatrixStack().scale(1.5F, 1F, 1F);
			}
		}

		@SubscribeEvent
		public static void registerModDimension(final RegisterDimensionsEvent event) {
			ResourceLocation undergarden = new ResourceLocation(UndergardenMod.MODID, "undergarden");

			if (DimensionType.byName(undergarden) == null) {
				undergarden_dimension = DimensionManager.registerDimension(undergarden, UndergardenDimensions.UNDERGARDEN.get(), new PacketBuffer(Unpooled.buffer()), false);
				DimensionManager.keepLoaded(undergarden_dimension, false);
			} else {
				undergarden_dimension = DimensionType.byName(undergarden);
			}
		}

		@SubscribeEvent
		public static void registerWorldCarver(final RegistryEvent.Register<WorldCarver<?>> event) {
			event.getRegistry().register(new UndergardenCaveWorldCarver(ProbabilityConfig::deserialize));
		}

		@ObjectHolder("undergarden:undergarden_cave")
		public static UndergardenCaveWorldCarver UNDERGARDEN_CAVE;

		@SubscribeEvent
		public static void registerFeatures(final RegistryEvent.Register<Feature<?>> event) {

			Registry.register(Registry.STRUCTURE_PIECE, "depthrock_ruin_piece", UndergardenFeatures.DEPTHROCK_RUIN_TYPE);
			Registry.register(Registry.STRUCTURE_PIECE, "overworld_portal_ruin_piece", UndergardenFeatures.OVERWORLD_PORTAL_RUIN_TYPE);
		}
	}
}
