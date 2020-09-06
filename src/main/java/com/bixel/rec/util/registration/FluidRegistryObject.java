package com.bixel.rec.util.registration;

import java.util.Objects;

import javax.annotation.Nonnull;

import com.bixel.rec.RecMod;

import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.BucketItem;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;

//https://github.com/mekanism/Mekanism/blob/a8572791196d30e9108d9db7679c634a42bd7838/src/main/java/mekanism/common/registration/impl/FluidRegistryObject.java#L14
public class FluidRegistryObject<STILL extends Fluid, FLOWING extends Fluid, BLOCK extends FlowingFluidBlock, BUCKET extends BucketItem> implements IFluidProvider
{
	private RegistryObject<STILL> stillRO;
	private RegistryObject<FLOWING> flowingRO;
	private RegistryObject<BLOCK> blockRO;
	private RegistryObject<BUCKET> bucketRO;
	
	public STILL getStillFluid() { return stillRO.get(); }
    public FLOWING getFlowingFluid() { return flowingRO.get(); }
    public BLOCK getBlock() { return blockRO.get(); }
    public BUCKET getBucket() { return bucketRO.get(); }
    
    public FluidRegistryObject(String name) 
    {
        this.stillRO = RegistryObject.of(new ResourceLocation(RecMod.MOD_ID, name), ForgeRegistries.FLUIDS);
        this.flowingRO = RegistryObject.of(new ResourceLocation(RecMod.MOD_ID, "flowing_" + name), ForgeRegistries.FLUIDS);
        this.blockRO = RegistryObject.of(new ResourceLocation(RecMod.MOD_ID, name), ForgeRegistries.BLOCKS);
        this.bucketRO = RegistryObject.of(new ResourceLocation(RecMod.MOD_ID, "bucket_" + name), ForgeRegistries.ITEMS);
    }
    
  //Make sure these update methods are package local as only the FluidDeferredRegister should be messing with them
    void updateStill(RegistryObject<STILL> stillRO) 
    {
        this.stillRO = Objects.requireNonNull(stillRO);
    }

    void updateFlowing(RegistryObject<FLOWING> flowingRO) 
    {
        this.flowingRO = Objects.requireNonNull(flowingRO);
    }

    void updateBlock(RegistryObject<BLOCK> blockRO) 
    {
        this.blockRO = Objects.requireNonNull(blockRO);
    }

    void updateBucket(RegistryObject<BUCKET> bucketRO) 
    {
        this.bucketRO = Objects.requireNonNull(bucketRO);
    }

    @Nonnull
    @Override
    public STILL getFluid() 
    {
        //Default our fluid to being the still variant
        return getStillFluid();
    }
}
