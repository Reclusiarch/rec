package com.bixel.rec.init;

import com.bixel.rec.RecMod;
import com.bixel.rec.util.registration.FluidDeferredRegister;
import com.bixel.rec.util.registration.FluidRegistryObject;

import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.item.BucketItem;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid.Flowing;
import net.minecraftforge.fluids.ForgeFlowingFluid.Source;

public class RegisterFluids 
{
	public static final FluidDeferredRegister FLUIDS_ADVANCED = new FluidDeferredRegister(RecMod.MOD_ID);
	
	public static final FluidRegistryObject<Source, Flowing, FlowingFluidBlock, BucketItem> ALKALINE_SOLUTION = FLUIDS_ADVANCED.register("alkaline_solution",
	          FluidAttributes.builder(new ResourceLocation("block/water_still"), new ResourceLocation("block/water_flow")).color(0xFF3393FF));
	
	/**
	 * OLD CODE
	 */
	/*
	public static final ResourceLocation SALTWATER_STILL = RecMod.loc("fluid/alkaline_solution_still");
	public static final ResourceLocation SALTWATER_FLOWING = RecMod.loc("fluid/alkaline_solution_flow");
	public static final ResourceLocation SALTWATER_OVERLAY = RecMod.loc("fluid/alkaline_solution_overlay");.
	
	public static Fluid waterAttributes = Blocks.WATER.getDefaultState().getFluidState().getFluid();
	
	public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, RecMod.MOD_ID);
	

	public static final RegistryObject<FlowingFluid> ALKALINE_SOLUTION_FLUID = FLUIDS.register("alkaline_solution_fluid", 
			() -> new ForgeFlowingFluid.Source(FluidRegister.ALKALINE_SOLUTION_PROPERTIES));
	public static final RegistryObject<FlowingFluid> ALKALINE_SOLUTION_FLOWING = FLUIDS.register("alkaline_solution_flowing", 
			() -> new ForgeFlowingFluid.Flowing(FluidRegister.ALKALINE_SOLUTION_PROPERTIES));
	
	public static final ForgeFlowingFluid.Properties ALKALINE_SOLUTION_PROPERTIES = 
			new ForgeFlowingFluid.Properties(() -> ALKALINE_SOLUTION_FLUID.get(), () -> ALKALINE_SOLUTION_FLOWING.get(), 
					FluidAttributes.builder(SALTWATER_STILL, SALTWATER_FLOWING).overlay(SALTWATER_OVERLAY))
						.block(() -> FluidRegister.ALKALINE_SOLUTION.get());
	

	public static final RegistryObject<FlowingFluidBlock> ALKALINE_SOLUTION = BlockRegister.BLOCKS.register("alkaline_solution", 
			() -> new FlowingFluidBlock(() -> FluidRegister.ALKALINE_SOLUTION_FLUID.get(), Block.Properties.create(Material.WATER)
					.doesNotBlockMovement().hardnessAndResistance(100f).noDrops()));
					*/
}
