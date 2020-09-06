package com.bixel.rec.init;

import com.bixel.rec.RecMod;
import com.bixel.rec.objects.tileentities.BaseTileEntity;
import com.bixel.rec.objects.tileentities.QuarryTileEntity;
import com.bixel.rec.objects.tileentities.RecFurnaceTileEntity;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RegisterTileEntities 
{
	public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, RecMod.MOD_ID);
	
	public static final RegistryObject<TileEntityType<QuarryTileEntity>> QUARRY = TILE_ENTITY_TYPES.register("quarry",
			() -> TileEntityType.Builder.create(QuarryTileEntity::new, RegisterBlocks.QUARRY.get()).build(null));
	
	public static final RegistryObject<TileEntityType<BaseTileEntity>> FIRST_BLOCK = TILE_ENTITY_TYPES.register("first_block",
			() -> TileEntityType.Builder.create(BaseTileEntity::new, RegisterBlocks.FIRST_BLOCK.get()).build(null));
	
	public static final RegistryObject<TileEntityType<RecFurnaceTileEntity>> FURNACE_BLOCK = TILE_ENTITY_TYPES.register("rec_furnace_block",
			() -> TileEntityType.Builder.create(RecFurnaceTileEntity::new, RegisterBlocks.FURNACE_BLOCK.get()).build(null));
}
