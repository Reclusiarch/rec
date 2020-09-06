package com.bixel.rec.objects.tileentities;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.Nullable;

import com.bixel.rec.RecMod;
import com.bixel.rec.container.RecFurnaceContainer;
import com.bixel.rec.init.RegisterRecipeSerializer;
import com.bixel.rec.init.RegisterTileEntities;
import com.bixel.rec.objects.blocks.RecFurnaceBlock;
import com.bixel.rec.recipes.ModRecipe;
import com.bixel.rec.util.handler.FurnaceItemHandler;

import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.wrapper.RecipeWrapper;

public class RecFurnaceTileEntity extends TileEntity implements ITickableTileEntity, INamedContainerProvider
{
	private ITextComponent customName;
	public int currentSmeltTime;
	public final int maxSmeltTime = 100; //ticks
	private FurnaceItemHandler inventory;
	
	public RecFurnaceTileEntity(TileEntityType<?> tileEntityTypeIn) 
	{
		super(tileEntityTypeIn);
		this.inventory = new FurnaceItemHandler(2);
	}

	
	public RecFurnaceTileEntity() 
	{
		this(RegisterTileEntities.FURNACE_BLOCK.get());
	}
	
	@Override
	public Container createMenu(final int windowId, final PlayerInventory playerInv, final PlayerEntity playerIn)
	{
		return new RecFurnaceContainer(windowId, playerInv, this);
	}
	
	@Override
	public ITextComponent getDisplayName()
	{
		return this.getName();
	}
	
	@Override
	public void tick()
	{
		boolean dirty = false; //can be expensive - 
		if(this.world != null &&!this.world.isRemote)
		{
			if(this.world.isBlockPowered(this.getPos())) //redstone activated?
			{
				if(this.getRecipe(this.inventory.getStackInSlot(0)) != null) //0 slot = input slot
				{
					if(this.currentSmeltTime != this.maxSmeltTime) 
					{ 
						//currently smelting - set blockstate smelting
						this.world.setBlockState(this.pos, this.getBlockState().with(RecFurnaceBlock.LIT, true));
						this.currentSmeltTime++;
						dirty = true;
					}
					else
					{
						//NOT smelting - set blockstate off
						this.world.setBlockState(this.pos, this.getBlockState().with(RecFurnaceBlock.LIT, false));
						this.currentSmeltTime = 0;
						ItemStack output = this.getRecipe(this.inventory.getStackInSlot(0)).getRecipeOutput();
						this.inventory.insertItem(1, output.copy(), false);
						this.inventory.decrStackSize(0, 1);
						dirty = true;
					}
				}
			}
		}
		
		if(dirty)
		{
			this.markDirty();
			//notifiy world to update block state
			this.world.notifyBlockUpdate(this.pos, this.getBlockState(), this.getBlockState(), Constants.BlockFlags.BLOCK_UPDATE);
		}
	}
	
	@Nullable
	public ITextComponent getCustomName()
	{
		return this.customName;
	}
	
	public void setCustomName(ITextComponent name)
	{
		this.customName = name;
	}
	
	public ITextComponent getName()
	{
		return this.customName != null ? this.customName : this.getDefaultName();
	}
	
	private ITextComponent getDefaultName()
	{
		return new TranslationTextComponent("container" + RecMod.MOD_ID + "rec_furnace_block");
	}
	
	@Override
	public void read(BlockState stateIn, CompoundNBT compound)
	{
		super.read(stateIn, compound);
		if(compound.contains("CustomName", Constants.NBT.TAG_STRING))
		{
			this.customName = ITextComponent.Serializer.func_240643_a_(compound.getString("CustomName"));
		}
		
		NonNullList<ItemStack> inv = NonNullList.<ItemStack>withSize(this.inventory.getSlots(), ItemStack.EMPTY);
		ItemStackHelper.loadAllItems(compound, inv);
		this.inventory.setNonNullList(inv);
		
		this.currentSmeltTime = compound.getInt("currentSmeltTime");
	}
	
	@Override
	public CompoundNBT write(CompoundNBT compound)
	{
		super.write(compound);
		if(this.customName != null)
		{
			compound.putString("CustomName", ITextComponent.Serializer.toJson(this.customName));
		}
		
		ItemStackHelper.saveAllItems(compound, this.inventory.toNonNullList());
		compound.putInt("currentSmeltTime", this.currentSmeltTime);
		return compound;
	}
	
	@Nullable
	private ModRecipe getRecipe(ItemStack stack)
	{
		if(stack == null) { return null; }
		
		Set<IRecipe<?>> recipes = findRecipesByType(RegisterRecipeSerializer.MOD_RECIPE, this.world);
		for(IRecipe<?> iRecipe : recipes)
		{
			ModRecipe recipe = (ModRecipe)iRecipe;
			if(recipe.matches(new RecipeWrapper(this.inventory), this.world))
			{
				return recipe;
			}
		}
		return null;
	}
	
	public static Set<IRecipe<?>> findRecipesByType(IRecipeType<?> typeIn, World world) 
	{
		return world != null ? world.getRecipeManager().getRecipes().stream().filter(recipe -> recipe.getType() == typeIn).collect(Collectors.toSet()) : Collections.emptySet();
	}

	@OnlyIn(Dist.CLIENT)
	public static Set<IRecipe<?>> findRecipesByType(IRecipeType<?> typeIn) 
	{
		ClientWorld world = Minecraft.getInstance().world;
		return world != null ? world.getRecipeManager().getRecipes().stream().filter(recipe -> recipe.getType() == typeIn).collect(Collectors.toSet()) : Collections.emptySet();
	}
	
	public static Set<ItemStack> getAllRecipeInputs(IRecipeType<?> typeIn, World world)
	{
		Set<ItemStack> inputs = new HashSet<ItemStack>();
		Set<IRecipe<?>>recipes = findRecipesByType(typeIn, world);
		for(IRecipe<?> iRecipe : recipes)
		{
			NonNullList<Ingredient> ingredients = iRecipe.getIngredients();
			ingredients.forEach(ingredient -> {
				for(ItemStack stack : ingredient.getMatchingStacks())
				{
					inputs.add(stack);
				}
			});
		}
		return inputs;
	}
	
	public final IItemHandlerModifiable getInventory()
	{
		return this.inventory;
	}
	
	@Nullable
	@Override
	public SUpdateTileEntityPacket getUpdatePacket() 
	{
		CompoundNBT nbt = new CompoundNBT();
		this.write(nbt);
		return new SUpdateTileEntityPacket(this.pos, 0, nbt);
	}
 
	@Override
	public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) 
	{
		this.read(null, pkt.getNbtCompound());
	}

	@Override
	public CompoundNBT getUpdateTag() 
	{
		CompoundNBT nbt = new CompoundNBT();
		this.write(nbt);
		return nbt;
	}

	@Override
	public void handleUpdateTag(BlockState stateIn, CompoundNBT nbt) 
	{
		this.read(stateIn, nbt);
	}

	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
		return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.orEmpty(cap, LazyOptional.of(() -> this.inventory));
	}
}
