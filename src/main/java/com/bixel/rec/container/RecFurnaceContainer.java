package com.bixel.rec.container;

import java.util.Objects;

import javax.annotation.Nonnull;

import com.bixel.rec.init.RegisterBlocks;
import com.bixel.rec.init.RegisterContainers;
import com.bixel.rec.objects.tiles.RecFurnaceTileEntity;
import com.bixel.rec.util.FunctionalIntReferenceHolder;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IWorldPosCallable;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.items.SlotItemHandler;

public class RecFurnaceContainer extends Container
{
	public RecFurnaceTileEntity tileEntity;
	private IWorldPosCallable canInteractWithCallable;
	public FunctionalIntReferenceHolder currentSmeltTime;
	/**
	 * Server Constructor
	 * @param windowId
	 * @param playerInv
	 * @param entity
	 */
	public RecFurnaceContainer(final int windowId, final PlayerInventory playerInv, final RecFurnaceTileEntity tile) 
	{
		super(RegisterContainers.REC_FURNACE_CONTAINER.get(), windowId);
		this.tileEntity = tile;
		this.canInteractWithCallable = IWorldPosCallable.of(tile.getWorld(), tile.getPos());
		
		final int slotSizePlus2 = 18;
		final int startX = 8;
		// Hotbar
		int hotbarY = 142;
		for (int column = 0; column < 9; column++) 
		{
			this.addSlot(new Slot(playerInv, column, startX + (column * slotSizePlus2), hotbarY));
		}

		// Main Player Inventory
		final int startY = 84;

		for (int row = 0; row < 3; row++) 
		{
			for (int column = 0; column < 9; column++) 
			{
				this.addSlot(new Slot(playerInv, 9 + (row * 9) + column, startX + (column * slotSizePlus2),
						startY + (row * slotSizePlus2)));
			}
		}

		// Furnace Slots
		this.addSlot(new SlotItemHandler(tile.getInventory(), 0, 56, 34));
		this.addSlot(new SlotItemHandler(tile.getInventory(), 1, 116, 35));

		this.trackInt(currentSmeltTime = new FunctionalIntReferenceHolder(() -> this.tileEntity.currentSmeltTime,
				value -> this.tileEntity.currentSmeltTime = value));
	}
	
	/**
	 * Client Constructor
	 * @param windowId
	 * @param playerInv
	 * @param data
	 */
	public RecFurnaceContainer(final int windowId, final PlayerInventory playerInv, final PacketBuffer data)
	{
		this(windowId, playerInv, getTileEntity(playerInv, data));
	}
	
	private static RecFurnaceTileEntity getTileEntity(final PlayerInventory playerInv, final PacketBuffer data) 
	{
        Objects.requireNonNull(playerInv, "playerInv cannot be null");
        Objects.requireNonNull(data, "data cannot be null");
        final TileEntity tileAtPos = playerInv.player.world.getTileEntity(data.readBlockPos());
        if (tileAtPos instanceof RecFurnaceTileEntity) 
        {
            return (RecFurnaceTileEntity) tileAtPos;
        }
        throw new IllegalStateException("TileEntity is not correct " + tileAtPos);
    }
	@Override
	public boolean canInteractWith(PlayerEntity playerIn) 
	{
		return isWithinUsableDistance(canInteractWithCallable, playerIn, RegisterBlocks.FURNACE_BLOCK.get());
	}
	
	@Nonnull
	@Override
	public ItemStack transferStackInSlot(final PlayerEntity player, final int index) 
	{
		ItemStack returnStack = ItemStack.EMPTY;
		final Slot slot = this.inventorySlots.get(index);
		if (slot != null && slot.getHasStack()) 
		{
			final ItemStack slotStack = slot.getStack();
			returnStack = slotStack.copy();

			final int containerSlots = this.inventorySlots.size() - player.inventory.mainInventory.size();
			if (index < containerSlots) 
			{
				if (!mergeItemStack(slotStack, containerSlots, this.inventorySlots.size(), true)) 
				{
					return ItemStack.EMPTY;
				}
			} 
			else if (!mergeItemStack(slotStack, 0, containerSlots, false)) 
			{
				return ItemStack.EMPTY;
			}
			if (slotStack.getCount() == 0) 
			{
				slot.putStack(ItemStack.EMPTY);
			} 
			else 
			{
				slot.onSlotChanged();
			}
			if (slotStack.getCount() == returnStack.getCount()) 
			{
				return ItemStack.EMPTY;
			}
			slot.onTake(player, slotStack);
		}
		return returnStack;
	}
	
	@OnlyIn(Dist.CLIENT)
	public int getSmeltProgressionScaled() 
	{
		return this.currentSmeltTime.get() != 0 && this.tileEntity.maxSmeltTime != 0
				? this.currentSmeltTime.get() * 24 / this.tileEntity.maxSmeltTime
				: 0;
	}
}
