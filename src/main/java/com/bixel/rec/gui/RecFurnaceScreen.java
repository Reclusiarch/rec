package com.bixel.rec.gui;

import com.bixel.rec.RecMod;
import com.bixel.rec.container.RecFurnaceContainer;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class RecFurnaceScreen extends ContainerScreen<RecFurnaceContainer>
{
	private static final ResourceLocation TEXTURE = RecMod.loc("textures/gui/example_furnace.png");

	public RecFurnaceScreen(RecFurnaceContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) 
	{
		super(screenContainer, inv, titleIn);
		this.guiLeft = 0;
		this.guiTop = 0;
		this.xSize = 176;
		this.ySize = 166;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(MatrixStack matrixStack, int mouseX, int mouseY) 
	{
		//super.func_230459_a_(p_230430_1_, mouseX, mouseY); 
		super.drawGuiContainerForegroundLayer(matrixStack, mouseX, mouseY); 
		//this.font.drawString(this.title.getFormattedText(), 8.0f, 6.0f, 4210752);
		this.font.drawString(matrixStack, this.getTitle().getUnformattedComponentText(), 8.0f, 8.0f, 0x404040);
		//this.font.drawString(this.playerInventory.getDisplayName().getFormattedText(), 8.0f, 90.0f, 4210752);
		this.font.drawString(matrixStack, this.playerInventory.getDisplayName().getUnformattedComponentText(), 8.0f, 69.0f, 0x404040);
	}

	@Override
	public void render(MatrixStack p_230430_1_, int mouseX, int mouseY, float partialTicks) 
	{
		this.renderBackground(p_230430_1_);
		super.render(p_230430_1_, mouseX, mouseY, partialTicks);
		//renderHoveredToolTip
		//this.render(p_230430_1_, mouseX, mouseY);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, float partialTicks, int x, int y) 
	{
		RenderSystem.color4f(1.0f, 1.0f, 1.0f, 1.0f);
		this.minecraft.getTextureManager().bindTexture(TEXTURE);
		this.blit(matrixStack, this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
		//draws the progress bar
		this.blit(matrixStack, this.guiLeft + 79, this.guiTop + 35, 176, 0, this.container.getSmeltProgressionScaled(), 16);
	}
}
